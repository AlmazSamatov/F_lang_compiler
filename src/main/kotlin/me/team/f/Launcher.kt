package me.team.f

import me.team.f.ast.toAst
import me.team.f.parsing.Analyser

fun main(args: Array<String>) {
    val caller = Caller()
//    println(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
//        println(toParseTree(caller.parseFile("simple")).getString())
    //val code = """l = 0;""".trimMargin()
    //print(Analyser.parse(code))

    val code = """a is if 5 > 1 then 5 else 1""".trimMargin()
    val ast = Analyser.parse(code).root!!.toAst()
    println(ast)
}