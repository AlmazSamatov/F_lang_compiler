package me.team.f.compilation

import me.team.f.ast.*
import java.util.*
//import me.team.f.ast.Array
//import me.team.f.ast.Function
//import me.team.f.ast.Map
//import java.util.LinkedList
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


//data class Error(var msg: String?, var position: LineCol?)
//
//fun Program.isCorrect(): Boolean {
//    return this.getErrors()!!.isEmpty()
//}
//
//fun Program.getErrors(): LinkedList<Error>? {
//    val errors = LinkedList<Error>()
//    this.declarations.forEach { errors.addAll(it.getErrors() as LinkedList<Error>) }
//    return errors
//}
//
//private fun Declaration.getErrors(): LinkedList<Error>? {
//    return when (this) {
//        is VarDeclaration -> {
//            this.getErrors()
//        }
//        else ->
//            LinkedList()
//    }
//}
//
//fun VarDeclaration.getErrors(): LinkedList<Error>? {
//    return this.value.getErrors()
//}
//
//fun Expression.getErrors(): LinkedList<Error>? {
//    return when (this) {
//        is BinaryExpression -> {
//            (this.left.getErrors()!! + this.right.getErrors()!!) as LinkedList
//        }
//        is Call -> {
//            val errors = this.secondary.getErrors()
//            expressions.forEach { errors?.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is ElementOf -> {
//            val errors = this.index.getErrors()
//            errors?.addAll(this.varName.getErrors() as LinkedList)
//            errors
//        }
//        is NamedTupleElement -> {
//            this.secondary.getErrors()
//        }
//        is UnnamedTupleElement -> {
//            this.secondary.getErrors()
//        }
//        is Conditional -> {
//            val errors = this.predicate.getErrors()
//            errors?.addAll(this.thenExpr.getErrors() as LinkedList)
//            errors?.addAll(this.elseExpr.getErrors() as LinkedList)
//            errors
//        }
//        is Function -> {
//            val errors = this.body.expression?.getErrors()
//            this.body.statements?.forEach { errors?.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is Array -> {
//            val errors = LinkedList<Error>()
//            this.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is Map -> {
//            val errors = LinkedList<Error>()
//            this.pairs.forEach { it -> it.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) } }
//            errors
//        }
//        is Tuple -> {
//            val errors = LinkedList<Error>()
//            this.elements.forEach { it -> errors.addAll(it.type.getErrors() as LinkedList) }
//            errors
//        }
//        else -> {
//            LinkedList()
//        }
//    }
//}
//
//fun Statement.getErrors(): LinkedList<Error>? {
//    return when (this) {
//        is Assignment -> {
//            val errors = this.expression.getErrors()
//            errors?.addAll(this.secondary.getErrors() as LinkedList)
//            errors
//        }
//        is FunctionCall -> {
//            val errors = this.secondary.getErrors()
//            this.expressions.forEach { errors?.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is IfStatement -> {
//            val errors = this.predicate.getErrors()
//            this.thenStatements.forEach { errors?.addAll(it.getErrors() as LinkedList) }
//            this.elseStatements.forEach { errors?.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is LoopStatement -> {
//            val errors = LinkedList<Error>()
//            this.loopHeader.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
//            this.statements.forEach { errors.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        is ReturnStatement -> {
//            this.expression.getErrors()
//        }
//        is BreakStatement -> {
//            val errors = LinkedList<Error>()
//            errors.add(Error("Incorrect break statement @(${this.position?.start})", this.position?.start))
//            errors
//        }
//        is PrintStatement -> {
//            val errors = LinkedList<Error>()
//            this.expressions.forEach { errors.addAll(it.getErrors() as LinkedList) }
//            errors
//        }
//        else -> {
//            LinkedList()
//        }
//    }
//}


fun Node.process(operation: (Node) -> Unit) {
    operation(this)
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> v.process(operation)
            is Collection<*> -> v.forEach { if (it is Node) it.process(operation) }
        }
    }
}

fun Node.transform(operation: (Node) -> Node): Node {
    operation(this)
    val changes = HashMap<String, Any>()
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> {
                val newValue = v.transform(operation)
                if (newValue != v) changes[p.name] = newValue
            }
            is Collection<*> -> {
                val newValue = v.map { if (it is Node) it.transform(operation) else it }
                if (newValue != v) changes[p.name] = newValue
            }
        }
    }
    var instanceToTransform = this
    if (!changes.isEmpty()) {
        val constructor = this.javaClass.kotlin.primaryConstructor!!
        val params = HashMap<KParameter, Any?>()
        constructor.parameters.forEach { param ->
            if (changes.containsKey(param.name)) {
                params[param] = changes[param.name]
            } else {
                params[param] = this.javaClass.kotlin.memberProperties.find { param.name == it.name }!!.get(this)
            }
        }
        instanceToTransform = constructor.callBy(params)
    }
    return operation(instanceToTransform)
}

fun <T : Node> Node.specificProcess(klass: Class<T>, operation: (T) -> Unit) {
    process {
        if (klass.isInstance(it)) {
            operation(it as T)
        }
    }
}

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

    // check a variable is not referred before being declared
    this.specificProcess(VarReference::class.java) {
        if (!varsByName.containsKey(it.referenceName)) {
            errors.add(Error("There is no variable named '${it.referenceName}'", it.position!!.start))
        } else if (it.isBefore(varsByName[it.referenceName]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${it.referenceName}' before its declaration",
                    it.position!!.start
                )
            )
        }
    }
    this.specificProcess(Assignment::class.java) {
        it.secondary as VarReference
        if (!varsByName.containsKey(it.secondary.referenceName)) {
            errors.add(Error("There is no variable named '${it.secondary.referenceName}'", it.position!!.start))
        } else if (it.isBefore(varsByName[it.secondary.referenceName]!!)) {
            errors.add(Error("You cannot refer to variable '$it' before its declaration", it.position!!.start))
        }
    }

    return errors
}

fun Node.isBefore(varDeclaration: VarDeclaration): Boolean {
    return this.position!!.start.line < varDeclaration.position!!.start.line ||
            this.position!!.start.line == varDeclaration.position.start.line &&
            this.position!!.start.col < varDeclaration.position.start.col
}

