package me.team.f

import me.team.f.ast.toAst
import me.team.f.parsing.Analyser

fun main(args: Array<String>) {
    val code = """
            id is func() do
                a is ["A"];
                b is 45.6;
                if a = 123 then a := 1 else b := 0 end
            end
        """.trimIndent()
    val ast = Analyser.parse(code).root!!.toAst()
    println(ast)
}


