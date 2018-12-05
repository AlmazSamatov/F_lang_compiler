package me.team.f

import me.team.f.ast.*
import me.team.f.parsing.Analyser
import me.team.f.generator.*
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {

//    if (args.size != 1) {
//        throw InputMismatchException("Please, specify filename as only argument")
//    }
//    val code = FileInputStream(args[0])

    val code = "a is 5;" +
            "b is 7 * 2;" +
            "res is a + b;" +
            "isOk: boolean is if a > b then true else false end;" +
            "inc is func(v: integer) => v + 1;" +
            "arr is [1, 2, 3];" +
            "c is func(v: integer) do while a > b loop print 1, 2 end end;" +
            "z is func(v: integer) do for gg in 1..10 loop print 1, 2 break end end;" +
            "e is func(v: integer) : boolean do if a > b then o is 0 return true else print \"Hello!\" return false end end;" +
            "d is func(v: integer) do a := 1 inc(b) print a, b, res end"

//    val code = "d is func(v: integer) do j is 1 for i in 1..2 loop print i end end"

    val parseResult = Analyser.parse(code)

    if (parseResult.correct()) {
        val ast = parseResult.root!!
        val kotlinProgram = mutableListOf("fun main(args: Array<String>) {")

        ast.declarations.map {
            kotlinProgram.add("\t" + declarationToKotlin(it as VarDeclaration))
        }

        kotlinProgram.add("}")

        kotlinProgram.map { println(it) }

        saveToFile(kotlinProgram)

    } else {
        val errors = parseResult.errors
        println("ERRORS:")
        errors.forEach { println("[Line: ${it.position.line}, " +
                "Column: ${it.position.col}] -> ${it.message}") }
    }

}

fun saveToFile(kotlinProgram: List<String>) {
    val outPath = "generated-src/compilation/gen_program.kt"

    File(outPath).createNewFile()

    File(outPath).printWriter().use { out ->
        kotlinProgram.map {
            out.println(it)
        }
    }
}

