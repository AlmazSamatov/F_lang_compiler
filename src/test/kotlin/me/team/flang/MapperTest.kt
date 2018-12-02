package me.team.flang

//import me.tomassetti.sandy.sandy.ast.*
import me.team.f.ast.*
import me.team.f.parsing.Analyser
//import me.tomassetti.sandy.sandy.parsing.SandyParserFacade
import kotlin.test.assertEquals
import org.junit.Test as test

class MapperTest {
    @test
    fun mapDeclaration() {
        val code = """a is 5 + 5"""
        val ast = Analyser.parse(code).root!!.toAst()
        val expectedAst = Program(listOf(VarDeclaration("a",
            SumExpression(IntLit("5"), IntLit("5")))))
        println(ast)
        println(expectedAst)
        assertEquals(ast, expectedAst)
    }
}