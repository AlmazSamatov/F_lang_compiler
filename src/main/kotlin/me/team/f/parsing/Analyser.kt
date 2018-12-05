package me.team.f.parsing

import me.team.f.ast.*
import me.team.fproject.FLangLexer
import me.team.fproject.FLangParser
import me.team.fproject.FLangParserBaseListener
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import java.io.*
import java.util.*

object ANTLRAnalyser {

    fun parse(inputStream: InputStream): ANTLRAnalysisResult {
        val lexParseErrors = LinkedList<Error>()

        // Creating listener for lexer and parser errors
        val errorListener = object: ANTLRErrorListener {
            override fun syntaxError(p0: Recognizer<*, *>?, p1: Any?, p2: Int, p3: Int,
                                     p4: String, p5: RecognitionException?) {
                lexParseErrors.add(Error(p4, LineCol(p2, p3)))
            }

            override fun reportAmbiguity(p0: Parser?, p1: DFA?, p2: Int, p3: Int,
                                         p4: Boolean, p5: BitSet?, p6: ATNConfigSet?) {}

            override fun reportContextSensitivity(p0: Parser?, p1: DFA?, p2: Int,
                                                  p3: Int, p4: Int, p5: ATNConfigSet?) {}

            override fun reportAttemptingFullContext(p0: Parser?, p1: DFA?, p2: Int, p3: Int,
                                                     p4: BitSet?, p5: ATNConfigSet?) {}
        }

        val lex = FLangLexer(ANTLRInputStream(inputStream))
        lex.removeErrorListeners()
        lex.addErrorListener(errorListener)
        val parser = FLangParser(CommonTokenStream(lex))
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        val parseListener = FLangParserBaseListener()
        parser.addParseListener(parseListener)
        val parseResult = parser.program()
        return ANTLRAnalysisResult(parseResult, lexParseErrors)
    }

}

object Analyser {

    fun parse(code: String): AnalysisResult {
        return parse(ByteArrayInputStream(code.toByteArray(Charsets.UTF_8)))
    }

    fun parse(file: File): AnalysisResult {
        return parse(FileInputStream(file))
    }

    fun parse(inputStream: InputStream): AnalysisResult {
        val result = ANTLRAnalyser.parse(inputStream)
        val lexParseErrors = result.errors

        val parseTree = result.root

        val ast = if (lexParseErrors.isEmpty()) {
            parseTree?.toAst(true)
        } else {
            null
        }

        val semanticErrors: List<Error> = ast?.validate() ?: emptyList()

        val errors = mutableListOf<Error>()
        errors.addAll(lexParseErrors)
        errors.addAll(semanticErrors)
        return AnalysisResult(ast, errors)
    }

}

data class ANTLRAnalysisResult(val root : FLangParser.ProgramContext?,
                               val errors: List<Error>)

data class AnalysisResult(val root: Program?,
                          val errors: List<Error>) {
    fun correct() = errors.isEmpty() && root != null
}