import me.team.fproject.FLangLexer
import org.antlr.v4.runtime.ANTLRInputStream
import java.io.StringReader
import java.util.*

fun lexerForCode(code: String) = FLangLexer(ANTLRInputStream(StringReader(code)))

fun getTokens(lexer: FLangLexer): List<String> {
    val tokens = LinkedList<String>()
    do {
        val t = lexer.nextToken()
        when (t.type) {
            -1 -> tokens.add("EOF")
            else -> if (t.type != FLangLexer.WS) tokens.add(lexer.ruleNames[t.type - 1])
        }
    } while (t.type != -1)
    return tokens
}

fun main(args: Array<String>) {
    print(getTokens(lexerForCode("a is 5; b is a^1;")))
}