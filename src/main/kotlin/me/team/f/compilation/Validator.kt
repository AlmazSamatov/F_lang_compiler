package me.team.f.compilation

import me.team.f.ast.*
import me.team.f.ast.Array
import me.team.f.ast.Function
import me.team.f.ast.Map
import java.util.LinkedList

data class Error(var msg: String?, var position: LineCol?)

fun Program.isCorrect(): Boolean {
    return this.getErrors()!!.isEmpty()
}

fun Program.getErrors(): LinkedList<Error>? {
    val errors = LinkedList<Error>()
    this.declarations.forEach { errors.addAll(it.getErrors() as LinkedList<Error>) }
    return errors
}

private fun Declaration.getErrors(): LinkedList<Error>? {
    return when (this) {
        is VarDeclaration -> {
            this.getErrors()
        }
        else ->
            LinkedList()
    }
}

fun VarDeclaration.getErrors(): LinkedList<Error>? {
    return this.value.getErrors()
}

fun Expression.getType(): Type? {
    return when (this) {
        is BinaryExpression ->{
            when (this) {
                is SumExpression -> {
                    this.left.getType()
                }

                else->{
                    null
                }
            }
        }
        else -> {
            null
        }
    }
}

fun Expression.getErrors(): LinkedList<Error>? {
    return when (this) {
        is BinaryExpression -> {
            (this.left.getErrors()!! + this.right.getErrors()!!) as LinkedList
        }
        is Call -> {
            val errors = this.secondary.getErrors()
            expressions.forEach { errors?.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is ElementOf -> {
            val errors = this.index.getErrors()
            errors?.addAll(this.varName.getErrors() as LinkedList)
            errors
        }
        is NamedTupleElement -> {
            this.secondary.getErrors()
        }
        is UnnamedTupleElement -> {
            this.secondary.getErrors()
        }
        is Conditional -> {
            val errors = this.predicate.getErrors()
            errors?.addAll(this.thenExpr.getErrors() as LinkedList)
            errors?.addAll(this.elseExpr.getErrors() as LinkedList)
            errors
        }
        is FunctionExpression -> {
            this.function.getErrors()
        }
        is ArrayExpression -> {
            this.array.getErrors()
        }
        is TupleExpression -> {
            this.tuple.getErrors()
        }
        is MapExpression -> {
            this.map.getErrors()
        }
        is ParenExpression -> {
            this.expression.getErrors()
        }
        else -> {
            return LinkedList()
        }
    }
}

fun Primary.getErrors(): LinkedList<Error>? {
    return when (this) {
        is Function -> {
            val errors = this.body.expression?.getErrors()
            this.body.statements?.forEach { errors?.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is Array -> {
            val errors = LinkedList<Error>()
            this.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is Map -> {
            val errors = LinkedList<Error>()
            this.pairs.forEach { it -> it.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) } }
            errors
        }
        is Tuple -> {
            val errors = LinkedList<Error>()
            this.elements.forEach { it -> errors.addAll(it.type.getErrors() as LinkedList) }
            errors
        }
        else -> {
            LinkedList()
        }
    }
}

fun Statement.getErrors(): LinkedList<Error>? {
    return when (this) {
        is Assignment -> {
            val errors = this.expression.getErrors()
            errors?.addAll(this.secondary.getErrors() as LinkedList)
            errors
        }
        is FunctionCall -> {
            val errors = this.secondary.getErrors()
            this.expressions.forEach { errors?.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is IfStatement -> {
            val errors = this.expression.getErrors()
            this.statements.forEach { errors?.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is LoopStatement -> {
            val errors = LinkedList<Error>()
            this.loopHeader.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
            this.statements.forEach { errors.addAll(it.getErrors() as LinkedList) }
            errors
        }
        is ReturnStatement -> {
            this.expression.getErrors()
        }
        is BreakStatement -> {
            val errors = LinkedList<Error>()
            errors.add(Error("Incorrect break statement @(${this.position?.start})", this.position?.start))
            errors
        }
        is PrintStatement -> {
            val errors = LinkedList<Error>()
            this.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
            errors
        }
        else -> {
            LinkedList()
        }
    }
}

