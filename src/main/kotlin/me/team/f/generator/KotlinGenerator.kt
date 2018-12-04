package me.team.f.generator

import me.team.f.ast.*
import java.lang.StringBuilder

fun declarationToKotlin(ast: VarDeclaration) {
    if (ast.type != null) {
        println("val " + ast.varName + ": " + typeToKotlin(ast.type)
                + " = " + exprToKotlin(ast.value))
    } else {
        println("val " + ast.varName + " = " + exprToKotlin(ast.value))
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
    }

    return resultBuilder.toString()
}
