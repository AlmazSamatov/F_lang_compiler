import me.team.fproject.FLangLexer
import me.team.fproject.FLangParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.StringReader
import java.util.*

class Caller {

    fun lexerForCode(code: String) = FLangLexer(ANTLRInputStream(StringReader(code)))

    fun lexerForFile(resourceName: String) =
        FLangLexer(ANTLRInputStream(javaClass.getResourceAsStream("/${resourceName}.f")))

    fun parseFile(resourceName: String) : FLangParser.ProgramContext =
        FLangParser(CommonTokenStream(lexerForFile(resourceName))).program()

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

}