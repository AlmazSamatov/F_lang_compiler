package me.team.flang

import me.team.fproject.FLangLexer
import org.antlr.v4.runtime.ANTLRInputStream
import java.io.*
import java.util.*
import org.junit.Test as test
import kotlin.test.*

class FLangLexerTest {

    fun lexerForCode(code: String) = FLangLexer(ANTLRInputStream(StringReader(code)))

    fun lexerForResource(resourceName: String) =
        FLangLexer(ANTLRInputStream(this.javaClass.getResourceAsStream("/${resourceName}.sandy")))

    fun tokens(lexer: FLangLexer): List<String> {
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

    @test
    fun parseType() {
        assertEquals(
            listOf("ID", "IS", "LPAR", "INT_LIT", "XOR", "INT_LIT", "RPAR", "SEMI", "EOF"),
            tokens(lexerForCode("a is (1 ^ 2);"))
        )
    }

    @test
    fun parseRational() {
        assertEquals(
            listOf("ID", "IS", "RAT_LIT", "SEMI", "EOF"),
            tokens(lexerForCode("r is 4\\5;"))
        )
    }

    @test
    fun parseComplexl() {
        assertEquals(
            listOf("ID", "IS", "COMP_LIT", "SEMI", "EOF"),
            tokens(lexerForCode("c is 1.5i2;"))
        )
    }

    @test
    fun parseString() {
        assertEquals(
            listOf("ID", "IS", "STR_LIT", "SEMI", "EOF"),
            tokens(lexerForCode("c is \"Hello, world\";"))
        )
    }

    @test
    fun parseLineFunction() {
        assertEquals(
            listOf(
                "ID", "IS", "FUNC", "LPAR", "ID", "COLUMN", "INTEGER", "RPAR",
                "LINE_FUN", "ID", "PLUS", "INT_LIT", "EOF"
            ),
            tokens(lexerForCode("inc is func(p: integer) => p+1"))
        )
    }
}
