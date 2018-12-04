package me.team.f.compilation

import kotlin.math.hypot

class Rational(var numerator: Int, var denominator: Int) {
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

    operator fun minus(other: Rational): Rational {
        return this + Rational(-1 * other.numerator, other.denominator)
    }

    operator fun minusAssign(other: Rational) {
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

    operator fun div(other: Rational): Rational {
        return this * other.inverse()
    }

    operator fun divAssign(other: Rational) {
        val aux = this / other
        this.numerator = aux.numerator
        this.denominator = aux.denominator
    }

    fun inverse(): Rational {
        return Rational(this.denominator, this.numerator)
    }

    fun toDouble(): Double {
        return this.numerator * 1.0 / this.denominator
    }

    fun toFloat(): Float {
        return this.numerator * 1.0f / this.denominator
    }
}

class Complex(var real: Double, var imaginary: Double) {
    fun modulus(): Double {
        return hypot(this.real, this.imaginary)
    }

    operator fun plus(other: Complex): Complex {
        return (Complex(this.real + other.real, this.imaginary + other.imaginary))
    }

    operator fun plusAssign(other: Complex) {
        val aux = this + other
        this.real = aux.real
        this.imaginary = other.imaginary
    }

    operator fun minus(other: Complex): Complex {
        return this + (-1.0) * other
    }

    fun minusAssign(other: Complex) {
        val aux = this - other
        this.real = aux.real
        this.imaginary = other.imaginary
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
        this.imaginary = other.imaginary
    }

    operator fun times(other: Double): Complex {
        return this * Complex(other, 0.0)
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

    fun inverse(): Complex {
        return 1.0 / (this.modulus() * this.modulus()) * conjugate()
    }

    fun conjugate() = Complex(this.real, -1 * this.imaginary)
}

private operator fun Double.times(complex: Complex): Complex {
    return complex * this
}
