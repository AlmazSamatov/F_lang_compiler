package me.team.f.parsing

import me.team.f.ast.*
import me.team.f.ast.Array
import me.team.f.ast.Map
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


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

@Suppress("UNCHECKED_CAST")
fun <T : Node> Node.specificProcess(type: Class<T>, operation: (T) -> Unit) {
    process {
        if (type.isInstance(it)) operation(it as T)
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
            errors.add(
                Error(
                    "There is no variable named '${it.secondary.referenceName}'",
                    it.position!!.start
                )
            )
        } else if (it.isBefore(varsByName[it.secondary.referenceName]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${it.secondary.referenceName}' before its declaration",
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
                is VarReference -> type(varsByName[expression.referenceName]?.value!!)
                is Map -> "map"
                is Array -> "array"
                is Pair -> "pair"
                is BinaryOperation -> {
                    val leftType = type(expression.left)
                    val rightType = type(expression.right)
                    when (expression.operatorSign) {
                        in listOf("<", "=", ">", "<=", "/=", ">=") -> {
                            when (Pair(leftType, rightType)) {
                                Pair("integer", "integer") -> "boolean"
                                Pair("integer", "real") -> "boolean"
                                Pair("integer", "rational") -> "boolean"
                                else -> {
                                    errors.add(
                                        me.team.f.parsing.Error(
                                            "Cannot use comparison operator to operands of types ($leftType, $rightType)",
                                            expression.position?.start!!
                                        )
                                    )
                                    ""
                                }
                            }
                        }

                        else -> {
                            errors.add(Error("Unknown operand", expression.position?.start!!))
                            ""
                        }
                    }
                }
                else -> "null"
            }
        }

        val varType = type(varsByName[it.secondary.referenceName]?.value!!)
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

