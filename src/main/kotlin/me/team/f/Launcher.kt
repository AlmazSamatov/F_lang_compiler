package me.team.f

import me.team.f.parsing.Analyser

fun main(args: Array<String>) {
    val caller = Caller()
//    println(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
    println(toParseTree(caller.parseFile("simple")).getString())
//    val code = """a is 5 + 5"""
//    print(Analyser.parse(code))
}