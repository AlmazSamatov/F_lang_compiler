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
        // TODO(fix problems with scopes)
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

    return errors
}


fun Node.isBefore(varDeclaration: VarDeclaration): Boolean {
    return this.position!!.start.line < varDeclaration.position!!.start.line ||
            this.position!!.start.line == varDeclaration.position.start.line &&
            this.position!!.start.col < varDeclaration.position.start.col
}