package me.team.f.ast

import java.lang.UnsupportedOperationException
import java.util.*
import kotlin.Pair
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import me.team.f.ast.Error as Error

object Validator {
    val symbolTable = HashMap<Pair<String, String>, Type>()//Class<out Type>>()
    val scope = Stack<String>()
    val errors = mutableListOf<Error>()
    var funcId = 0
    var loopId = 0

    fun validate(ast: Program): List<Error> {
        scope.push("main")

        ast.declarations.map {
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
            symbolTable[Pair(declaration.varName, scope.peek())] = getO(getType(declaration.value))
    }

    private fun getType(value: Expression): Type {
        return when (value) {
            is BinaryExpression -> getBinaryType(value)

            // Secondary - Primary - Elementary
            is BoolLit -> BooleanType()
            is IntLit -> IntegerType()
            is RealLit -> RealType()
            is RatLit -> RationalType()
            is CompLit -> ComplexType()
            is StrLit -> StringType()

            // Secondary - Primary - Conditional
            is Conditional -> getConditionalType(value)

            // Secondary - Primary - Function
            is Function -> {
                scope.add("function${funcId++}")
                val res = getFunctionType(value)
                scope.pop()
                res
            }

            // Secondary - Primary - Array
            is Array -> {
                val types = value.expressions.map { getType(it) }
                val uniqueTypes = HashSet<Type>()
                uniqueTypes.addAll(types)

                if (uniqueTypes.size == 1)
                    ArrayType(types[0])
                else {
                    errors.add(Error("Array elements have different types " +
                            "${types.map { it.javaClass.simpleName }}",
                        value.position!!.start)
                    )
                    UndefinedType()
                }
            }
            // Secondary - Primary - Tuple
            is Tuple -> {
                TupleType(value.elements.map { getType(it.expression) })
            }

            // Secondary - Primary - Map
            is Map -> {
                val uniqueTypes = HashSet<Pair<Type, Type>>()
                val elementTypes = value.pairs.map {
                    val element = Pair(getType(it.expressions[0]), getType(it.expressions[1]))
                    uniqueTypes.add(element)
                    element
                }

                if (uniqueTypes.size == 1)
                    MapType(listOf(elementTypes[0].first, elementTypes[0].second))
                else {
                    errors.add(Error("Map elements have different types " +
                            "${elementTypes.map { "(${it.first.javaClass.simpleName}, " +
                                    "${it.second.javaClass.simpleName})" }}",
                        value.position!!.start)
                    )
                    UndefinedType()
                }
            }

            // Secondary
            is Call -> getCallType(value)

            is ElementOf -> getElementType(value)

            is NamedTupleElement -> getNamedTupleElement(value)
            is UnnamedTupleElement -> getUnnamedTupleElement(value)

            is VarReference -> {
                var type: Type = UndefinedType()
                scope.forEach {
                    if (symbolTable.containsKey(Pair(value.name, it)))
                        type = symbolTable[Pair(value.name, it)]!!
                }
                if (type == UndefinedType())
                    errors.add(Error("Variable ${value.name} is " +
                            "referenced before assignment", value.position!!.start))
                type
            }
            else -> throw UnsupportedOperationException("Type can't be deduced")
        }
    }

    private fun getNamedTupleElement(value: NamedTupleElement): Type {
        val callerType = getType(value.secondary)

        return if (callerType.javaClass == TupleType::class.java) {
            val name = (value.secondary as VarReference).name
            symbolTable[Pair(name, scope.peek())]!!
        } else {
            errors.add(Error("${callerType.javaClass.simpleName} " +
                    "is not a TupleType",
                value.position!!.start)
            )
            UndefinedType()
        }
    }

    private fun getUnnamedTupleElement(value: UnnamedTupleElement): Type {
        val named = NamedTupleElement(secondary = value.secondary, fieldName = value.fieldNum)
        return getNamedTupleElement(named)
    }

    private fun getBinaryType(value: BinaryExpression): Type {
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
                               mutableMap: MutableMap<Pair<Type, Type>, Type>): Type {

        val leftType = getType(value.left)
        val rightType = getType(value.right)

        return if (mutableMap.containsKey(Pair(leftType, rightType))) {
            mutableMap[Pair(leftType, rightType)]!!
        } else {
            errors.add(Error(
                "${value.javaClass.simpleName} can't be applied to values " +
                        "of ${leftType.javaClass.simpleName} and ${rightType.javaClass.simpleName}",
                value.left.position!!.start)
            )
            UndefinedType()
        }
    }

    private fun getConditionalType(value: Conditional): Type {
        getType(value.predicate)
        val thenType = getType(value.thenExpr)
        val elseType = getType(value.elseExpr)
        return if (thenType == elseType) {
            thenType
        } else {
            errors.add(Error(
                "If expression returns different values of " +
                        "${thenType.javaClass.simpleName} and ${elseType.javaClass.simpleName}",
                value.thenExpr.position!!.start)
            )
            UndefinedType()
        }
    }

    private fun getFunctionType(value: Function): Type {
        val parameters = value.parameters
        parameters.map { symbolTable[Pair(it.parName, scope.peek())] = getO(it.type) }

        val returnType: Type = getO(value.type)

        val bodyType = if (value.body.expression != null) {
            getType(value.body.expression)
        } else {
            var result: Type = UndefinedType()
            value.body.statements!!.forEach {
                getStatementType(it)
                if (it is ReturnStatement)
                    result = getType(it.expression)
            }
            result
        }

        val types = mutableListOf<Type>()
        types.addAll(parameters.map { getO(it.type) })
        types.add(bodyType)

        return if (returnType == UndefinedType()) {
            FunctionType(types)
        } else {
            if (returnType == bodyType) {
                FunctionType(types)
            } else {
                errors.add(Error("Function defined return type ${returnType.javaClass.simpleName} " +
                                "doesn't correspond to actual return type ${bodyType.javaClass.simpleName}",
                        value.body.position!!.start)
                )
                UndefinedType()
            }
        }
    }

    private fun getCallType(value: Call): Type {
        val callerType = getType(value.secondary)
        return if (callerType.javaClass == FunctionType::class.java) {
            val funcTypes = (callerType as FunctionType).types
            funcTypes[funcTypes.size - 1]
        } else {
            errors.add(Error("Value of type " +
                    "${callerType.javaClass.simpleName} can't be called",
                value.position!!.start)
            )
            UndefinedType()
        }
    }

    private fun getElementType(value: ElementOf): Type {
        val callerType = getType(value.varName)

        return if (callerType.javaClass == ArrayType::class.java) {
            val indexType = getType(value.index)

            if (indexType == (callerType as ArrayType).type)
                return indexType
            else {
                errors.add(Error("Array index should be of IntegerType, " +
                        "but received ${indexType.javaClass.simpleName}",
                    value.position!!.start)
                )
                UndefinedType()
            }
        } else if (callerType.javaClass == MapType::class.java) {
            val indexType = getType(value.index)

            if (indexType == (callerType as MapType).types[0])
                return indexType
            else {
                errors.add(Error("Map index should be of " +
                        "${callerType.types[0].javaClass.simpleName}, " +
                        "but received ${indexType.javaClass.simpleName}",
                    value.position!!.start)
                )
                UndefinedType()
            }
        } else {
            errors.add(Error("Variable of type " +
                    "${callerType.javaClass.simpleName} is not subcriptable, " +
                    "should be one of [MapType, ArrayType]",
                value.position!!.start)
            )
            UndefinedType()
        }
    }

    private fun getStatementType(it: Statement): Type {
        when (it) {

        }
        return UndefinedType() // TODO(check)
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

fun createMapPair(l: Type, r: Type, res: Type): Pair<Pair<Type, Type>, Type> {
//Pair<Pair<Class<Type>, Class<Type>>, Class<Type>> {
    return Pair(createTypePair(l, r), res)
}

fun createTypePair(l: Type, r: Type): Pair<Type, Type> {//Pair<Class<Type>, Class<Type>> {
    return Pair(l, r)
}

fun getO(node: Type?): Type {
    return if (node == null)
        UndefinedType()
    else {
        val tmpNode: Type = node
        tmpNode.position = null
        tmpNode
    }
}