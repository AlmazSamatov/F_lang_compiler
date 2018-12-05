package me.team.f.ast

import me.team.f.parsing.Analyser
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap

data class Error(val message: String, val position: LineCol)

fun Program.validate(): List<Error> {
    val errors = LinkedList<Error>()

    // check a variable is not duplicated
    val varsByName = HashMap<String, VarDeclaration>()

    fun type(expression: Expression): String {
        return when (expression) {
            is BoolLit -> "boolean"
            is IntLit -> "integer"
            is RealLit -> "real"
            is RatLit -> "rational"
            is CompLit -> "complex"
            is StrLit -> "string"
            is VarReference -> type(varsByName[expression.name]?.value!!)
            is Map -> "map"
            is Array -> "array"
            is Pair -> "pair"
            is Tuple -> "tuple"
            is Parameter -> {
                val type = expression.type
                return when (type) {
                    is BooleanType -> "boolean"
                    is IntegerType -> "integer"
                    is RealType -> "real"
                    is RationalType -> "rational"
                    is ComplexType -> "complex"
                    is StringType -> "string"
                    else -> "null"
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
                                ""
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
                                    ""
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
            else -> "null"
        }
    }

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

    if (errors.size > 0)
        return errors

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
        val i = 0
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
                                errors.add(
                                    Error(
                                        "Non-compatible type of parameters. Parameter in call $nameOfVarInCall is " +
                                                "not same as in function declaration ${expression.parameters[i].parName}",
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

    this.specificProcess(Call::class.java) {
        val i = 0
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
                            Error (
                                "Wrong number of parameters when call function ${it.secondary.name}",
                                it.secondary.position?.start!!
                            )
                        )
                    } else {
                        for (i in 0..expression.parameters.lastIndex) {
                            val l = type(expression.parameters[i])
                            if (type(expression.parameters[i]) != type(it.expressions[i])) {
                                val function = it.expressions[i]
                                val nameOfVarInCall = when(function)
                                    {
                                        is VarReference -> function.name
                                        is BoolLit -> function.value
                                        is StrLit -> function.value
                                        is RealLit -> function.value
                                        is RatLit -> function.value
                                        is CompLit -> function.value
                                        is IntLit -> function.value
                                        else -> function
                                    }
                                errors.add(
                                    Error (
                                        "Non-compatible type of parameters. Parameter in call with name $nameOfVarInCall of type ${type(function)} is " +
                                                "not same as in function declaration parameter with name ${expression.parameters[i].parName} of type ${type(expression.parameters[i])}",
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

    return errors
}


fun Node.isBefore(varDeclaration: VarDeclaration): Boolean {
    return this.position!!.start.line < varDeclaration.position!!.start.line ||
            this.position!!.start.line == varDeclaration.position.start.line &&
            this.position!!.start.col < varDeclaration.position.start.col
}

object Facade {

    fun parse(code: String): Result = parse(ByteArrayInputStream(code.toByteArray(Charsets.UTF_8)))

    fun parse(file: File): Result = parse(FileInputStream(file))

    fun parse(inputStream: InputStream): Result {
        val antlrParsingResult = Analyser.parse(inputStream)
        val lexicalAnsSyntaticErrors = antlrParsingResult.errors
        val antlrRoot = antlrParsingResult.root
        val astRoot = antlrRoot
        val semanticErrors = astRoot?.validate() ?: emptyList()
        return Result(astRoot, lexicalAnsSyntaticErrors + semanticErrors)
    }

}

data class Result(
    val root: Program?,
    val errors: List<Error>
) {
    fun isCorrect(): Boolean = root != null && errors.isEmpty()
}

fun main(args: kotlin.Array<String>) {
    Facade.parse(File("./src/main/resources/simple.f"))
        .errors.forEach { println("Error:\n${it.message}\nPosition:${it.position}") }
}