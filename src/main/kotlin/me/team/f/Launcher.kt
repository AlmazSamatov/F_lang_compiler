package me.team.f

import me.team.f.ast.*
import me.team.f.parsing.Analyser
import me.team.f.generator.*

fun main(args: Array<String>) {
//    val caller = Caller()
//    println(caller.getTokens(caller.lexerForCode("a is 5; b is a^1;")))
//        println(toParseTree(caller.parseFile("simple")).getString())
    //val code = """l = 0;""".trimMargin()
    //print(Analyser.parse(code))

//    val code = "a is 5; b is 7 * 2; res is a + b"
    val code = "a: boolean is 5 + 7"
    val ast = Analyser.parse(code).root!!.toAst()
    ast.declarations.map{
        it.specificProcess(VarDeclaration::class.java){ line ->
            println(line)
            declarationToKotlin(line)
        }
    }
}