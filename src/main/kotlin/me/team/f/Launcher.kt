package me.team.f

import me.team.f.ast.toAst
import me.team.f.parsing.Analyser

fun main(args: Array<String>) {
    val caller = Caller()

//    val code = """
//            inc is func(p: integer): integer => p+1
//        """.trimIndent()
    val code = """
            b is if a = true then a := true else a := false end
        """.trimIndent()
    val ast = Analyser.parse(code).root!!.toAst()
    println(ast)
}


