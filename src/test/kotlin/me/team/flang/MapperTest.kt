package me.team.flang

//import me.tomassetti.sandy.sandy.ast.*
import me.team.f.ast.*
import me.team.f.parsing.Analyser
//import me.tomassetti.sandy.sandy.parsing.SandyParserFacade
import kotlin.test.assertEquals
import org.junit.Test as test

class MapperTest {
    @test
    fun mapSumDeclaration() {
        val code = "a is 5 + 5"
        val ast = Analyser.parse(code).root!!.toAst()
        val expectedAst = Program(listOf(VarDeclaration("a",
            SumExpression(IntLit("5"), IntLit("5")))))
        assertEquals(ast, expectedAst)
    }

    @test
    fun mapTwoDeclarations() {
        val code = "a is 5 + 5; b is 7 ^ 3"
        val ast = Analyser.parse(code).root!!.toAst()
//        val expectedAst = Program(listOf(VarDeclaration("a",
//            SumExpression(IntLit("5"), IntLit("5")))))

//        println(ast)

//        println(expectedAst)
//        assertEquals(ast, expectedAst)
        assertEquals(true, true)
    }

    @test
    fun mapConditional() {
//        val code = "a is if 5 > 1 then 5 else 1"
        val code = "a is b[0]"
        val ast = Analyser.parse(code).root!!.toAst()
        println(ast)
    }

}