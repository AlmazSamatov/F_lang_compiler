package me.team.f.compilation

import me.team.f.ast.Type
import java.io.*

interface Source {
    val name: String
    val inputStream: InputStream
}

interface FType {
    val jvmDesc: String
}

object BooleanType: FType {
    override val jvmDesc: String
        get() = "I" // TODO: check
}

object IntegerType: FType {
    override val jvmDesc: String
        get() = "I"
}

object RealType: FType {
    override val jvmDesc: String
        get() = "D"
}

object RationalType: FType {
    override val jvmDesc: String
        get() = ""
}

object ComplexType: FType {
    override val jvmDesc: String
        get() = ""
}

object StringType: FType {
    override val jvmDesc: String
        get() = ""
}

object FunctionType: FType {
    override val jvmDesc: String
        get() = ""
}

object TupleType: FType {
    override val jvmDesc: String
        get() = ""
}

object ArrayType: FType {
    override val jvmDesc: String
        get() = "A"
}

object MapType: FType {
    override val jvmDesc: String
        get() = ""
}

fun Type.toFType(): FType {
    return when (this) {
        is me.team.f.ast.BooleanType -> BooleanType
        is me.team.f.ast.IntegerType -> IntegerType
        is me.team.f.ast.RealType -> RealType
        is me.team.f.ast.RationalType -> RationalType
        is me.team.f.ast.ComplexType-> ComplexType
        is me.team.f.ast.StringType -> StringType
        is me.team.f.ast.FunctionType -> FunctionType
        is me.team.f.ast.TupleType -> TupleType
        is me.team.f.ast.ArrayType -> ArrayType
        is me.team.f.ast.MapType -> MapType
        else -> throw UnsupportedOperationException()
    }
}
