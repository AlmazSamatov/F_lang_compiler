package me.team.f


fun main(args: Array<String>) {
    val caller = Caller()
//    println(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
    println(toParseTree(caller.parseFile("simple")).toString())
//    val code = """a is 5 + 5"""
//    print(Analyser.parse(code))
}