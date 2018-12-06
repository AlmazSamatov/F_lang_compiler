package me.team.f

import me.team.f.parsing.Analyser
import me.team.f.generator.*
import java.io.File
import java.io.FileInputStream

fun main(args: Array<String>) {

//    if (args.size != 1) {
//        throw InputMismatchException("Please, specify filename as only argument")
//    }

//    var t: Map<String, Any> = mapOf("name" to "Dima", "surname" to 5)
//    println(t["surname"])

    val code = FileInputStream("test_code.f")

    val parseResult = Analyser.parse(code)

    if (parseResult.correct()) {
        val ast = parseResult.root!!

        val kotlinProgram = mutableListOf("fun main(args: Array<String>) {")
        kotlinProgram.addAll(programToKotlin(ast))
        kotlinProgram.add("}")

        kotlinProgram.map { println(it) }

        saveToFile(kotlinProgram)

    } else {
        val errors = parseResult.errors
        println("ERRORS:")
        errors.forEach {
            println(
                "[Line: ${it.position.line}, " +
                        "Column: ${it.position.col}] -> ${it.message}"
            )
        }
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

