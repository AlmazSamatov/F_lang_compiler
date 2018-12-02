package me.team.f.parsing

import me.team.fproject.FLangLexer
import me.team.fproject.FLangParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import java.io.*
import java.lang.Error
import java.util.*

object Analyser {

    fun parse(code: String): AnalysisResult {
        return parse(ByteArrayInputStream(code.toByteArray(Charsets.UTF_8)))
    }

    fun parse(file: File): AnalysisResult {
        return parse(FileInputStream(file))
    }

    fun parse(inputStream: InputStream): AnalysisResult {
        val errors = LinkedList<Error>()

        val errorListener = object: ANTLRErrorListener {
            override fun syntaxError(p0: Recognizer<*, *>?, p1: Any?, p2: Int, p3: Int,
                                     p4: String?, p5: RecognitionException?) {
//                TODO("not implemented")
            }

            override fun reportAmbiguity(p0: Parser?, p1: DFA?, p2: Int, p3: Int,
                                         p4: Boolean, p5: BitSet?, p6: ATNConfigSet?) {
//                TODO("not implemented")
            }

            override fun reportContextSensitivity(p0: Parser?, p1: DFA?, p2: Int,
                                                  p3: Int, p4: Int, p5: ATNConfigSet?) {
//                TODO("not implemented")
            }

            override fun reportAttemptingFullContext(p0: Parser?, p1: DFA?, p2: Int, p3: Int,
                                                     p4: BitSet?, p5: ATNConfigSet?) {
//                TODO("not implemented")
            }
        }

        val lex = FLangLexer(ANTLRInputStream(inputStream))
        lex.removeErrorListeners()
        lex.addErrorListener(errorListener)
        val parser = FLangParser(CommonTokenStream(lex))
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        val parseResult = parser.program()
        return AnalysisResult(parseResult, errors)
    }

}

data class AnalysisResult(val root: FLangParser.ProgramContext?,
                          val errors: List<Error>) {
    fun correct() = errors.isEmpty() && root != null
}