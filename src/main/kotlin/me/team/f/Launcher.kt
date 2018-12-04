package me.team.f

import me.team.f.ast.toAst
import me.team.f.parsing.Analyser

fun main(args: Array<String>) {
    val caller = Caller()

//    val code = """
//            inc is func(p: integer): integer => p+1
//        """.trimIndent()
    val code = """
            a is 123;
            b is 45.6;
            max is func(p1:integer, p2:integer) => if p1 >= p2 then p1 else p2 end;
            main1 is func() do print "max = ",max(a,b),"\n" end
        """.trimIndent()
    val ast = Analyser.parse(code).root!!.toAst()
    println(ast)
}


