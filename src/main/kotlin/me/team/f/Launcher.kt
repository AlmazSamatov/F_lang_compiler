package me.team.f

fun main(args: Array<String>) {
    val caller = Caller()
    println(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
    println(toParseTree(caller.parseFile("code")).getString())
}