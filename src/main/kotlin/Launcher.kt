fun main(args: Array<String>) {
    val caller = Caller()
    print(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
    print(toParseTree(caller.parseFile("code")).getString())
}