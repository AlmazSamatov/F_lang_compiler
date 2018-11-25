package me.team.flang

import me.team.fproject.FLangLexer
import me.team.fproject.FLangParser
import me.team.flang.FLangLexerTest
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.StringReader
import java.util.LinkedList
import org.junit.Test as test
import kotlin.test.*

class FLangParserTest {

    fun lexerForCode(code: String) =
        FLangLexer(ANTLRInputStream(StringReader(code)))

    fun lexerForFile(resourceName: String) =
        FLangLexer(ANTLRInputStream(this.javaClass.getResourceAsStream("/${resourceName}.f")))

    fun parse(lexer: FLangLexer) : FLangParser.ProgramContext =
        FLangParser(CommonTokenStream(lexer)).program()

    fun parseFile(resourceName: String) : FLangParser.ProgramContext =
        FLangParser(CommonTokenStream(lexerForFile(resourceName))).program()


    @test fun parseAssignmentFunction() {
        assertEquals("Program\n" +
                "  Declaration\n" +
                "    T[min]: ID\n" +
                "    T[is]: IS\n" +
                "    Expression\n" +
                "      Secondary\n" +
                "        Primary\n" +
                "          Function\n" +
                "            T[func]: FUNC\n" +
                "            T[(]: LPAR\n" +
                "            Parameter\n" +
                "              T[p1]: ID\n" +
                "              T[:]: COLUMN\n" +
                "              Type\n" +
                "                IntegerType\n" +
                "                  T[integer]: INTEGER\n" +
                "            T[,]: COMMA\n" +
                "            Parameter\n" +
                "              T[p2]: ID\n" +
                "              T[:]: COLUMN\n" +
                "              Type\n" +
                "                IntegerType\n" +
                "                  T[integer]: INTEGER\n" +
                "            T[)]: RPAR\n" +
                "            Body\n" +
                "              T[=>]: LINE_FUN\n" +
                "              Expression\n" +
                "                Secondary\n" +
                "                  Primary\n" +
                "                    Conditional\n" +
                "                      T[if]: IF\n" +
                "                      Expression\n" +
                "                        Expression\n" +
                "                          Secondary\n" +
                "                            Primary\n" +
                "                              Elementary\n" +
                "                                T[p1]: ID\n" +
                "                        OperatorSign\n" +
                "                          T[<=]: LESS_EQ\n" +
                "                        Expression\n" +
                "                          Secondary\n" +
                "                            Primary\n" +
                "                              Elementary\n" +
                "                                T[p2]: ID\n" +
                "                      T[then]: THEN\n" +
                "                      Expression\n" +
                "                        Secondary\n" +
                "                          Primary\n" +
                "                            Elementary\n" +
                "                              T[p1]: ID\n" +
                "                      T[else]: ELSE\n" +
                "                      Expression\n" +
                "                        Secondary\n" +
                "                          Primary\n" +
                "                            Elementary\n" +
                "                              T[p2]: ID\n" +
                "                      T[end]: END\n",
            toParseTree(parseFile("assignment_func")).getString())
    }

}