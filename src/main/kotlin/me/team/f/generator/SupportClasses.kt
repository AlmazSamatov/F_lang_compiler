package me.team.f.generator

import kotlin.math.sqrt

class Rational(var numerator: Int, var denominator: Int) : Comparable<Rational> {
    override fun compareTo(other: Rational): Int {
        return when {
            this.less(other) -> -1
            this.equals(other) -> 0
            else -> 1
        }
    }

    fun compareTo(other: Int): Int {
        return this.compareTo(Rational(other, 1))
    }

    init {
        check(this.denominator == 0) {
            "Denominator cannot be 0."
        }
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    fun normalize(other: Rational): Rational {
        val gcd = gcd(other.numerator, other.denominator)
        return Rational(other.numerator / gcd, other.denominator / gcd)
    }

    operator fun plus(other: Rational): Rational {
        return Rational(
            this.numerator * other.denominator + this.denominator * other.numerator,
            this.denominator * other.denominator
        )
    }


    operator fun plusAssign(other: Rational) {
        val aux = this + other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun plus(other: Int): Rational {
        return this + Rational(other, 1)
    }

    operator fun plusAssign(other: Int) {
        val aux = this + other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun minus(other: Rational): Rational {
        return this + Rational(-1 * other.numerator, other.denominator)
    }

    operator fun minusAssign(other: Rational) {
        val aux = this - other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun minus(other: Int): Rational {
        return this + Rational(other, 1)
    }

    operator fun minusAssign(other: Int) {
        val aux = this - other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun times(other: Rational): Rational {
        return Rational(this.numerator * other.numerator, this.denominator * other.denominator)
    }

    operator fun timesAssign(other: Rational) {
        val aux = this * other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun times(other: Int): Rational {
        return Rational(this.numerator * other, this.denominator)
    }

    operator fun timesAssign(other: Int) {
        val aux = this * other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun div(other: Rational): Rational {
        return this * other.inverse()
    }

    operator fun divAssign(other: Rational) {
        val aux = this / other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    operator fun div(other: Int): Rational {
        return this * Rational(1, other)
    }

    operator fun divAssign(other: Int) {
        val aux = this / other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    fun inverse(): Rational {
        check(this.numerator == 0) {
            "Number 0 has no inverse."
        }
        return Rational(this.denominator, this.numerator)
    }

    fun toDouble(): Double {
        return this.numerator * 1.0 / this.denominator
    }

    fun toFloat(): Float {
        return this.numerator * 1.0f / this.denominator
    }

    private fun less(other: Rational): Boolean {
        return this.numerator * other.denominator < this.denominator * other.numerator
    }

    fun equals(other: Rational): Boolean {
        return this.numerator * other.denominator == this.denominator * other.numerator
    }
}

operator fun Int.plus(other: Rational): Rational {
    return other + this
}

operator fun Int.minus(other: Rational): Rational {
    return Rational(this, 1) - other
}

operator fun Int.times(other: Rational): Rational {
    return other * this
}

operator fun Int.div(other: Rational): Rational {
    return this * other.inverse()
}

operator fun Int.compareTo(other: Rational): Int {
    return -1 * other.compareTo(this)
}

class Complex(var real: Double, var imaginary: Double) : Comparable<Complex> {
    override fun compareTo(other: Complex): Int {
        return when {
            this.real < other.real -> -1
            this.real == other.real && this.imaginary < other.imaginary -> -1
            this.real == other.real && this.imaginary == other.imaginary -> 0
            this.real == other.real && this.imaginary > other.imaginary -> 1
            else -> 1
        }
    }

    fun modulus(): Double {
        return sqrt((this * this.conjugate()).real)
    }

    operator fun plus(other: Complex): Complex {
        return (Complex(this.real + other.real, this.imaginary + other.imaginary))
    }

    operator fun plusAssign(other: Complex) {
        val aux = this + other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun plus(other: Double): Complex {
        return (Complex(this.real + other, this.imaginary))
    }

    operator fun plusAssign(other: Double) {
        val aux = this + other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun plus(other: Int): Complex {
        return (Complex(this.real + other, this.imaginary))
    }

    operator fun plusAssign(other: Int) {
        val aux = this + other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun minus(other: Complex): Complex {
        return this + (-1.0) * other
    }

    fun minusAssign(other: Complex) {
        val aux = this - other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun minus(other: Double): Complex {
        return this + (-1.0) * other
    }

    fun minusAssign(other: Double) {
        val aux = this - other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun minus(other: Int): Complex {
        return this + (-1.0) * other
    }

    fun minusAssign(other: Int) {
        val aux = this - other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun times(other: Complex): Complex {
        return Complex(
            this.real * other.real - this.imaginary * other.imaginary,
            this.real * other.imaginary + this.imaginary * other.real
        )
    }

    operator fun timesAssign(other: Complex) {
        val aux = this * other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun times(other: Double): Complex {
        return this * Complex(other, 0.0)
    }

    operator fun timesAssign(other: Double) {
        val aux = this * other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun times(other: Int): Complex {
        return this * other.toDouble()
    }

    operator fun timesAssign(other: Int) {
        val aux = this * other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun div(other: Complex): Complex {
        return this * other.inverse()
    }

    operator fun divAssign(other: Complex) {
        val aux = this / other
        this.real = aux.real
        this.imaginary = other.imaginary
    }

    operator fun div(other: Double): Complex {
        return 1 / other * this
    }

    operator fun divAssign(other: Double) {
        val aux = this / other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    operator fun div(other: Int): Complex {
        return 1.0 / other * this
    }

    operator fun divAssign(other: Int) {
        val aux = this / other
        this.real = aux.real
        this.imaginary = aux.imaginary
    }

    fun inverse(): Complex {
        check(this.real == 0.0 && this.imaginary == 0.0) {
            "Number 0 has no inverse."
        }
        return 1.0 / (this.modulus() * this.modulus()) * conjugate()
    }

    fun conjugate() = Complex(this.real, -1 * this.imaginary)
}

operator fun Double.plus(other: Complex): Complex {
    return other + this
}

operator fun Double.minus(other: Complex): Complex {
    return Complex(this - other.real, other.imaginary)
}

operator fun Double.times(complex: Complex): Complex {
    return complex * this
}

operator fun Double.div(other: Complex): Complex {
    return this * other.inverse()
}

operator fun Int.plus(other: Complex): Complex {
    return other + this
}

operator fun Int.minus(other: Complex): Complex {
    return Complex(this - other.real, other.imaginary)
}

operator fun Int.times(complex: Complex): Complex {
    return complex * this
}

operator fun Int.div(other: Complex): Complex {
    return this * other.inverse()
}
