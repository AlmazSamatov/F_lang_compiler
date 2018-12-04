package me.team.f

import me.team.f.ast.*
import me.team.f.parsing.Analyser
import me.team.f.generator.*
import java.io.File

fun main(args: Array<String>) {

    val code = "a is 5 * 3; b is 7 * 2; res is a + b"
//    val code = "a: integer is 5 + 7; b is a + 2"

    val ast = Analyser.parse(code).root!!.toAst()
    val kotlinProgram = mutableListOf<String>(
        "fun main(args: Array<String>) {"
    )

    ast.declarations.map{
        it.specificProcess(VarDeclaration::class.java){ line ->
            println(line)
            kotlinProgram.add(declarationToKotlin(line))
        }
    }

    kotlinProgram.add("}")

    kotlinProgram.map { println(it) }

    saveToFile(kotlinProgram)

}

fun saveToFile(kotlinProgram: List<String>) {
    val outPath = "generated-src/compilation/kotlin_src.kt"

    File(outPath).createNewFile()

    File(outPath).printWriter().use { out ->
        kotlinProgram.map {
            out.println(it)
        }
    }
}