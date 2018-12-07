package me.team.f.ast

import java.util.*
import kotlin.collections.HashMap

data class Error(val message: String, val position: LineCol)

fun Program.validate(): List<Error> {
    val errors = LinkedList<Error>()

    // check a variable is not duplicated
    val varsByName = HashMap<String, VarDeclaration>()
    this.specificProcess(VarDeclaration::class.java) {
        if (varsByName.containsKey(it.varName)) {
            errors.add(
                Error(
                    "A variable named '${it.varName}' has been already declared at ${varsByName[it.varName]!!.position!!.start}",
                    it.position!!.start
                )
            )
        } else {
            varsByName[it.varName] = it
        }
    }

    this.specificProcess(Parameter::class.java) {
        // TODO(fix problems with scopes)
        varsByName[it.parName] = VarDeclaration(it.parName, it.type, VarReference(it.parName), it.position)
    }

    this.specificProcess(ForLoopHeader::class.java) {
        if (it.id != null)
            varsByName[it.id] = VarDeclaration(it.id, null, VarReference(it.id), it.position)
    }

    // check a variable is not referred before being declared
    this.specificProcess(VarReference::class.java) {
        if (!varsByName.containsKey(it.name)) {
            errors.add(Error("There is no variable named '${it.name}'", it.position!!.start))
        } else if (it.isBefore(varsByName[it.name]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${it.name}' before its declaration",
                    it.position!!.start
                )
            )
        }
    }

    fun type(expression: Expression): String {
        return when (expression) {
            is BoolLit -> "boolean"
            is IntLit -> "integer"
            is RealLit -> "real"
            is RatLit -> "rational"
            is CompLit -> "complex"
            is StrLit -> "string"
            is VarReference -> {
                if (expression.position == null)
                    return "any"
                else
                    type(varsByName[expression.name]?.value!!)
            }
            is Map -> "map"
            is Array -> "array"
            is Pair -> "pair"
            is Tuple -> {
                var tupleType = "tuple("
                expression.elements.forEach { element -> tupleType += type(element.expression) + "," }
                tupleType += ")"
                tupleType
            }
            is Parameter -> {
                val type = expression.type
                when (type) {
                    is BooleanType -> "boolean"
                    is IntegerType -> "integer"
                    is RealType -> "real"
                    is RationalType -> "rational"
                    is ComplexType -> "complex"
                    is StringType -> "string"
                    else -> "any"
                }
            }
            is BinaryExpression -> {
                val leftType = type(expression.left)
                val rightType = type(expression.right)
                val typesPair = Pair(leftType, rightType)
                when (expression) {
                    is LessExpression, is EqualExpression, is GreaterExpression, is LessEqExpression, is NotEqExpression, is GreaterEqExpression -> {

                        when (typesPair) {
                            Pair("integer", "integer") -> "boolean"
                            Pair("integer", "real") -> "boolean"
                            Pair("integer", "rational") -> "boolean"
                            Pair("real", "integer") -> "boolean"
                            Pair("real", "real") -> "boolean"
                            Pair("rational", "integer") -> "boolean"
                            Pair("rational", "rational") -> "boolean"
                            Pair("complex", "complex") -> "boolean"
                            else -> {
                                errors.add(
                                    Error(
                                        "Cannot use comparison operator to operands of types ($leftType, $rightType)",
                                        expression.position?.start!!
                                    )
                                )
                                "any"
                            }
                        }
                    }
                    is SumExpression, is SubExpression, is MultExpression, is DivExpression -> {
                        when (typesPair) {
                            Pair("integer", "integer") -> "integer"
                            Pair("integer", "real") -> "real"
                            Pair("integer", "rational") -> "rational"
                            Pair("integer", "complex") -> "complex"
                            Pair("real", "integer") -> "real"
                            Pair("real", "real") -> "real"
                            Pair("real", "complex") -> "complex"
                            Pair("rational", "integer") -> "rational"
                            Pair("rational", "rational") -> "rational"
                            Pair("complex", "integer") -> "complex"
                            Pair("complex", "real") -> "complex"
                            Pair("complex", "complex") -> "complex"
                            Pair("map", "map") -> {
                                if (expression is SumExpression) {
                                    "map"
                                } else {
                                    errors.add(
                                        Error(
                                            "Unsupported operator for expressions of type map.",
                                            expression.position?.start!!
                                        )
                                    )
                                    "any"
                                }
                            }
                            Pair("array", "array") -> {
                                if (expression is SumExpression) {
                                    "array"
                                } else {
                                    errors.add(
                                        Error(
                                            "Unsupported operator for expressions of type array.",
                                            expression.position?.start!!
                                        )
                                    )
                                    "any"
                                }
                            }
                            else -> {
                                errors.add(
                                    Error(
                                        "Operator cannot be applied on operands of types ($leftType, $rightType)",
                                        expression.position?.start!!
                                    )
                                )
                                "any"
                            }
                        }
                    }
                    is AndExpression, is OrExpression, is XorExpression -> {
                        if (typesPair != Pair("boolean", "boolean")) {
                            errors.add(
                                Error(
                                    "Cannot use logical operator on values of types ($leftType, $rightType)",
                                    expression.position?.start!!
                                )
                            )
                            "any"
                        } else "boolean"
                    }
                    else -> {
                        "any"
                    }
                }
            }
            is Function -> {
                when (expression.type) {
                    is BooleanType -> "boolean"
                    is IntegerType -> "integer"
                    is RealType -> "real"
                    is RationalType -> "rational"
                    is ComplexType -> "complex"
                    is StringType -> "string"
                    is MapType -> "map"
                    is ArrayType -> "array"
                    is TupleType -> "tuple"
                    else -> "func"
                }
            }
            is Call -> {
                return type(expression.secondary)
            }
            is ElementOf -> {
                val element = varsByName[(expression.varName as VarReference).name]
                val e = element?.value
                return when (e) {
                    is Array -> {
                        if ((expression.index as Int) < e.expressions.size){
                            return type(e.expressions[expression.index])
                        }
                        return "any"
                    }
                    is Map -> {
                        if ((expression.index as Int) < e.pairs.size){
                            for (i in 0..e.pairs.lastIndex) {
                                if (e.pairs[0].expressions[0] == expression.index) {
                                    return type(e.pairs[0].expressions[1])
                                }
                            }
                        }
                        return "any"
                    }
                    else -> "any"
                }
            }
            is NamedTupleElement -> {
                val element = varsByName[(expression.secondary as VarReference).name]
                for (e in (element?.value as Tuple).elements) {
                    if (e.name == expression.fieldName)
                        return type(e.expression)
                }
                return "any"
            }
            is UnnamedTupleElement -> {
                val element = varsByName[(expression.secondary as VarReference).name]
                for (e in (element?.value as Tuple).elements) {
                    if (e.name == expression.fieldNum)
                        return type(e.expression)
                }
                return "any"
            }
            else -> "any"
        }
    }

    this.specificProcess(Assignment::class.java) {
        it.secondary as VarReference
        if (!varsByName.containsKey(it.secondary.name)) {
            errors.add(
                Error(
                    "There is no variable named '${it.secondary.name}'",
                    it.position!!.start
                )
            )
        } else if (it.isBefore(varsByName[it.secondary.name]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${it.secondary.name}' before its declaration",
                    it.position!!.start
                )
            )
        }

        val varType = type(varsByName[it.secondary.name]?.value!!)
        val expressionType = type(it.expression)
        if (varType != expressionType) {
            errors.add(
                Error(
                    "Cannot assign value of type $expressionType to variable of type $varType",
                    it.expression.position?.start!!
                )
            )
        }

    }


    this.specificProcess(FunctionCall::class.java) {
        when (it.secondary) {
            is VarReference -> {
                if (!varsByName.containsKey(it.secondary.name)) {
                    errors.add(
                        Error(
                            "Function ${it.secondary.name} is not declared yet",
                            it.secondary.position?.start!!
                        )
                    )
                }

                val expression = varsByName[it.secondary.name]?.value

                if (expression is Function && expression.type != null) {
                    if (it.expressions.size != expression.parameters.size) {
                        errors.add(
                            Error(
                                "Wrong number of parameters when call function ${it.secondary.name}",
                                it.secondary.position?.start!!
                            )
                        )
                    } else {
                        for (i in 0..expression.parameters.lastIndex) {
                            if (type(expression.parameters[i]) != type(it.expressions[i])) {
                                val function = it.expressions[i]
                                val nameOfVarInCall = when (function) {
                                    is VarReference -> function.name
                                    is BoolLit -> function.value
                                    is StrLit -> function.value
                                    is RealLit -> function.value
                                    is RatLit -> function.value
                                    is CompLit -> function.value
                                    is IntLit -> function.value
                                    else -> function
                                }
                                if (nameOfVarInCall is String) {
                                    errors.add(
                                        Error(
                                            "Non-compatible type of parameters. Parameter in call with name $nameOfVarInCall of type ${type(function)} is " +
                                                    "not same as in function declaration with name ${expression.parameters[i].parName} of type ${type(expression.parameters[i])}",
                                            it.expressions[i].position?.start!!
                                        )
                                    )
                                } else {
                                    errors.add(
                                        Error(
                                            "Non-compatible type of parameters. Parameter in call of type ${type(function)} is " +
                                                    "not same as in function declaration with name ${expression.parameters[i].parName} of type ${type(expression.parameters[i])}",
                                            it.expressions[i].position?.start!!
                                        )
                                    )
                                }
                            }
                        }
                    }

                }


            }

        }
    }

    this.specificProcess(Call::class.java) {
        when (it.secondary) {
            is VarReference -> {
                if (!varsByName.containsKey(it.secondary.name)) {
                    errors.add(
                        Error(
                            "Function ${it.secondary.name} is not declared yet",
                            it.secondary.position?.start!!
                        )
                    )
                }

                val expression = varsByName[it.secondary.name]?.value

                if (expression is Function && expression.type != null) {
                    if (it.expressions.size != expression.parameters.size) {
                        errors.add(
                            Error(
                                "Wrong number of parameters when call function ${it.secondary.name}",
                                it.secondary.position?.start!!
                            )
                        )
                    } else {
                        for (i in 0..expression.parameters.lastIndex) {
                            val l = type(expression.parameters[i])
                            if (type(expression.parameters[i]) != type(it.expressions[i])) {
                                val function = it.expressions[i]
                                val nameOfVarInCall = when (function) {
                                    is VarReference -> function.name
                                    is BoolLit -> function.value
                                    is StrLit -> function.value
                                    is RealLit -> function.value
                                    is RatLit -> function.value
                                    is CompLit -> function.value
                                    is IntLit -> function.value
                                    else -> function
                                }
                                if (nameOfVarInCall is String) {
                                    errors.add(
                                        Error(
                                            "Non-compatible type of parameters. Parameter in call with name $nameOfVarInCall of type ${type(function)} is " +
                                                    "not same as in function declaration with name ${expression.parameters[i].parName} of type ${type(expression.parameters[i])}",
                                            it.expressions[i].position?.start!!
                                        )
                                    )
                                } else {
                                    errors.add(
                                        Error(
                                            "Non-compatible type of parameters. Parameter in call of type ${type(function)} is " +
                                                    "not same as in function declaration with name ${expression.parameters[i].parName} of type ${type(expression.parameters[i])}",
                                            it.expressions[i].position?.start!!
                                        )
                                    )
                                }
                            }
                        }
                    }

                }


            }

        }

    }

    this.specificProcess(Function::class.java) { function ->
        val functionType = type(function)
        var returnStatementType = ""
        function.body.statements?.forEach { statement ->
            if (statement is ReturnStatement) {
                val someType = type(statement.expression)
                if (returnStatementType.isEmpty())
                    returnStatementType = someType
                else if (returnStatementType != someType) {
                    errors.add(
                        me.team.f.ast.Error(
                            "Function return type $returnStatementType doesn't match with declared $functionType",
                            statement.position?.start!!
                        )
                    )
                }
            }
        }

    }
    return errors
}


fun Node.isBefore(varDeclaration: VarDeclaration): Boolean {
    return this.position!!.start.line < varDeclaration.position!!.start.line ||
            this.position!!.start.line == varDeclaration.position.start.line &&
            this.position!!.start.col < varDeclaration.position.start.col
}