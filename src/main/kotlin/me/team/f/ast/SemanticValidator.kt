package me.team.f.ast

import java.lang.UnsupportedOperationException
import java.util.*
import kotlin.Pair
import kotlin.collections.HashMap
import me.team.f.ast.Error as Error

object Validator {
    val symbolTable = HashMap<Pair<String, String>, Class<out Type>>()
    val scope = Stack<String>()
    val errors = mutableListOf<Error>()
    var funcId = 0
    var loopId = 0

    fun validate(ast: Program): List<Error> {
        scope.push("main")

        val map = ast.declarations.map {
//            errors.addAll(validateDeclaration(it as VarDeclaration))
            validateDeclaration(it as VarDeclaration)
        }

//        println(allowedLogical.containsKey(createTypePair(BooleanType(), BooleanType())))
//        println(allowedLogical[createTypePair(BooleanType(), BooleanType())]?.simpleName)
        println("***** Symbol table start *****")
        symbolTable.map {
            println(it)
        }
        println("***** Symbol table end *****")

        return errors
    }

    private fun validateDeclaration(declaration: VarDeclaration) {
        // Check if variable already exists
        if (symbolTable.containsKey(Pair(declaration.varName, scope.peek())))
            errors.add(Error("Variable ${declaration.varName} is already declared.",
                declaration.position!!.start))
        else
            symbolTable[Pair(declaration.varName, scope.peek())] = getType(declaration.value)
    }

    private fun getType(value: Expression): Class<out Type> {
        return when (value) {
            is BinaryExpression -> getBinaryType(value)

            // Secondary - Primary - Elementary
            is BoolLit -> BooleanType().javaClass
            is IntLit -> IntegerType().javaClass
            is RealLit -> RealType().javaClass
            is RatLit -> RationalType().javaClass
            is CompLit -> ComplexType().javaClass
            is StrLit -> StringType().javaClass

            // Secondary - Primary - Conditional
            is Conditional -> getConditionalType(value)

            // Secondary - Primary - Function
            is Function -> { scope.add("function$funcId"); funcId++; getFunctionType(value)}

            // Secondary - Primary - Array
            // Secondary - Primary - Tuple
            // Secondary - Primary - Map
            // Secondary
            is Call -> getType(value.secondary)
            is ElementOf -> getType(value.varName)
            is NamedTupleElement -> getType(value.secondary)
            is UnnamedTupleElement -> getType(value.secondary)
            else -> throw UnsupportedOperationException("Type can't be deduced")
        }
    }

    private fun getBinaryType(value: BinaryExpression): Class<out Type> {
        return when (value) {
            is SumExpression, is SubExpression, is MultExpression -> {
                getBinaryClass(value, allowedPlusMinusMult)
            }
            is DivExpression -> {
                getBinaryClass(value, allowedDiv)
            }
            is LessExpression, is LessEqExpression, is GreaterExpression,
                is GreaterEqExpression, is EqualExpression, is NotEqExpression -> {
                    getBinaryClass(value, allowedRelational)
            }
            is AndExpression, is OrExpression, is XorExpression -> {
                getBinaryClass(value, allowedLogical)
            }
            else -> throw UnsupportedOperationException("Type can't be deduced")
        }
    }

    private fun getBinaryClass(value: BinaryExpression,
                               mutableMap: MutableMap<Pair<Class<Type>, Class<Type>>, Class<Type>>)
            : Class<out Type> {

        val leftType = getType(value.left)
        val rightType = getType(value.right)
        return if (mutableMap.containsKey(Pair(leftType, rightType))) {
            mutableMap[Pair(leftType, rightType)]!!
        } else {
            errors.add(Error(
                "${value.javaClass.simpleName} can't be applied to values " +
                        "of ${leftType.simpleName} and ${rightType.simpleName}",
                value.left.position!!.start)
            )
            UndefinedType().javaClass
        }
    }

    private fun getConditionalType(value: Conditional): Class<out Type> {
        getType(value.predicate)
        val thenType = getType(value.thenExpr)
        val elseType = getType(value.elseExpr)
        return if (thenType == elseType) {
            thenType
        } else {
            errors.add(Error(
                "If expression returns different values of " +
                        "${thenType.simpleName} and ${elseType.simpleName}",
                value.thenExpr.position!!.start)
            )
            UndefinedType().javaClass
        }
    }

    private fun getFunctionType(value: Function): Class<out Type> {
        val parameters = value.parameters
        parameters.map { symbolTable[Pair(it.parName, scope.peek())] = it.type.javaClass }
        val returnType = value.type ?: UndefinedType()
    }

}


val allowedPlusMinusMult = mutableMapOf(
    createMapPair(IntegerType(), IntegerType(), IntegerType()),
    createMapPair(IntegerType(), RealType(), RealType()),
    createMapPair(RealType(), IntegerType(), RealType()),
    createMapPair(RealType(), RealType(), RealType()),
    createMapPair(IntegerType(), RationalType(), RationalType()),
    createMapPair(RationalType(), IntegerType(), RationalType()),
    createMapPair(RationalType(), RationalType(), RationalType()),
    createMapPair(IntegerType(), ComplexType(), ComplexType()),
    createMapPair(RealType(), ComplexType(), ComplexType()),
    createMapPair(ComplexType(), IntegerType(), ComplexType()),
    createMapPair(ComplexType(), RealType(), ComplexType()),
    createMapPair(ComplexType(), ComplexType(), ComplexType())
)

val allowedDiv = mutableMapOf(
    createMapPair(IntegerType(), IntegerType(), RealType()),
    createMapPair(IntegerType(), RealType(), RealType()),
    createMapPair(RealType(), IntegerType(), RealType()),
    createMapPair(RealType(), RealType(), RealType()),
    createMapPair(IntegerType(), RationalType(), RationalType()),
    createMapPair(RationalType(), IntegerType(), RationalType()),
    createMapPair(RationalType(), RationalType(), RationalType()),
    createMapPair(ComplexType(), IntegerType(), ComplexType()),
    createMapPair(ComplexType(), RealType(), ComplexType()),
    createMapPair(ComplexType(), ComplexType(), ComplexType())
)

val allowedRelational = mutableMapOf(
    createMapPair(IntegerType(), IntegerType(), BooleanType()),
    createMapPair(IntegerType(), RealType(), BooleanType()),
    createMapPair(RealType(), IntegerType(), BooleanType()),
    createMapPair(RealType(), RealType(), BooleanType()),
    createMapPair(IntegerType(), RationalType(), BooleanType()),
    createMapPair(RationalType(), IntegerType(), BooleanType()),
    createMapPair(RationalType(), RationalType(), BooleanType()),
    createMapPair(ComplexType(), ComplexType(), BooleanType())
)

val allowedLogical = mutableMapOf(
    createMapPair(BooleanType(), BooleanType(), BooleanType())
)

fun createMapPair(l: Type, r: Type, res: Type): Pair<Pair<Class<Type>, Class<Type>>, Class<Type>> {
    return Pair(createTypePair(l, r), res.javaClass)
}

fun createTypePair(l: Type, r: Type): Pair<Class<Type>, Class<Type>> {
    return Pair(l.javaClass, r.javaClass)
}