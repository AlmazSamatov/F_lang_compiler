package me.team.f.ast

import me.team.f.parsing.Analyser
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap

//fun Node.process(operation: (Node) -> Unit) {
//    operation(this)
//    this.javaClass.kotlin.memberProperties.forEach { p ->
//        val v = p.get(this)
//        when (v) {
//            is Node -> v.process(operation)
//            is Collection<*> -> v.forEach { if (it is Node) it.process(operation) }
//        }
//    }
//}
//
//fun Node.transform(operation: (Node) -> Node): Node {
//    operation(this)
//    val changes = HashMap<String, Any>()
//    this.javaClass.kotlin.memberProperties.forEach { p ->
//        val v = p.get(this)
//        when (v) {
//            is Node -> {
//                val newValue = v.transform(operation)
//                if (newValue != v) changes[p.name] = newValue
//            }
//            is Collection<*> -> {
//                val newValue = v.map { if (it is Node) it.transform(operation) else it }
//                if (newValue != v) changes[p.name] = newValue
//            }
//        }
//    }
//    var instanceToTransform = this
//    if (!changes.isEmpty()) {
//        val constructor = this.javaClass.kotlin.primaryConstructor!!
//        val params = HashMap<KParameter, Any?>()
//        constructor.parameters.forEach { param ->
//            if (changes.containsKey(param.name)) {
//                params[param] = changes[param.name]
//            } else {
//                params[param] = this.javaClass.kotlin.memberProperties.find { param.name == it.name }!!.get(this)
//            }
//        }
//        instanceToTransform = constructor.callBy(params)
//    }
//    return operation(instanceToTransform)
//}

//@Suppress("UNCHECKED_CAST")
//fun <T : Node> Node.specificProcess(type: Class<T>, operation: (T) -> Unit) {
//    process {
//        if (type.isInstance(it)) operation(it as T)
//    }
//}

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