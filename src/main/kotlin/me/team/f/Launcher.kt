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
//    val code = "a is func(f: integer, s: integer): integer => 5 * 7 + (s - f)"

//    val code = "a is 5;\n" +
//            "b is 7 * 2;\n" +
//            "res is a + b;\n" +
//            "isOk: boolean is a > b;\n" +
//            "inc is func(v: integer) => v + 1;\n" +
//            "arr is [1, 2, 3];\n" +
//            "c is func(v: integer) do while a > b loop print 1, 2 end end;\n" +
//            "z is func(v: integer) do for gg in 1..10 loop print 1, 2 break end end;\n" +
//            "e is func(v: integer) : boolean do if a > b then o is 0 return true else print \"Hello!\" return false end end;\n" +
//            "d is func(v: integer) do a := 1 inc(b) print a, b, res end"

    // TODO: fix generator for loop range, e.x for i in 1..(2*2)

    val code = "fibb is func(v: integer): integer do\n" +
            "            if (v = 1) | (v = 2) then\n" +
            "                return 1\n" +
            "            else\n" +
            "                return fibb(v-1) + fibb(v-2)\n" +
            "            end\n" +
            "        end;\n" +
            "        u: integer is 3;\n" +
            "        f is func() do\n" +
            "            print \"Fibbonacci of \", u, \" is \", fibb(u)\n" +
            "        end;\n" +
            "        main is f()"
//    val code = "a is (1 is \"Hello\", 2 is \"World\")"

    val parseResult = Analyser.parse(code)

    if (parseResult.correct()) {
        val ast = parseResult.root!!
        println(ast)
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

