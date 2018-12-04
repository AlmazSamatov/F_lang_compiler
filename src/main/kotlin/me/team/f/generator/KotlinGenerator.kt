package me.team.f.generator

import me.team.f.ast.*
import java.lang.StringBuilder

fun declarationToKotlin(ast: VarDeclaration): String {
    return if (ast.type != null) {
        ("val " + ast.varName + ": " + typeToKotlin(ast.type)
                + " = " + exprToKotlin(ast.value))
    } else {
        ("val " + ast.varName + " = " + exprToKotlin(ast.value))
    }
}

fun typeToKotlin(type: Type): String {
    val resultBuilder = StringBuilder()

    when (type) {
        is BooleanType -> resultBuilder.append("Boolean")
        is IntegerType -> resultBuilder.append("Int")
        is RealType -> resultBuilder.append("Double")
        is RationalType -> resultBuilder.append("Rational")
        is ComplexType -> resultBuilder.append("Complex")
        is StringType -> resultBuilder.append("String")
    }

    return resultBuilder.toString()
}

fun exprToKotlin(expr: Expression): String {
    val resultBuilder = StringBuilder()

    when (expr) {
        is BinaryExpression -> expr.specificProcess(BinaryExpression::class.java) {
            resultBuilder.append(binaryToKotlin(it))
        }

        is VarReference -> expr.specificProcess(VarReference::class.java) {
            resultBuilder.append(it.name)
        }

        is IntLit -> expr.specificProcess(IntLit::class.java) { resultBuilder.append(it.value) }
    }

    return resultBuilder.toString()
}

fun binaryToKotlin(expr: BinaryExpression): String {
    val resultBuilder = StringBuilder()
    when (expr) {
        is SumExpression -> expr.specificProcess(SumExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " + " + exprToKotlin(it.right))
        }
        is SubExpression -> expr.specificProcess(SubExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " - " + exprToKotlin(it.right))
        }
        is MultExpression -> expr.specificProcess(MultExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " * " + exprToKotlin(it.right))
        }
        is DivExpression -> expr.specificProcess(DivExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " / " + exprToKotlin(it.right))
        }
        is LessExpression -> expr.specificProcess(LessExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " < " + exprToKotlin(it.right))
        }
        is GreaterExpression -> expr.specificProcess(GreaterExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " > " + exprToKotlin(it.right))
        }
        is LessEqExpression -> expr.specificProcess(LessEqExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " <= " + exprToKotlin(it.right))
        }
        is GreaterEqExpression -> expr.specificProcess(GreaterEqExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " >= " + exprToKotlin(it.right))
        }
        is EqualExpression -> expr.specificProcess(EqualExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " = " + exprToKotlin(it.right))
        }
        is NotEqExpression -> expr.specificProcess(NotEqExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " /= " + exprToKotlin(it.right))
        }
        is AndExpression -> expr.specificProcess(AndExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " & " + exprToKotlin(it.right))
        }
        is OrExpression -> expr.specificProcess(OrExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " | " + exprToKotlin(it.right))
        }
        is XorExpression -> expr.specificProcess(XorExpression::class.java) {
            resultBuilder.append(exprToKotlin(it.left) + " ^ " + exprToKotlin(it.right))
        }
    }
    return resultBuilder.toString()
}
