package me.team.flang

import me.team.fproject.FLangLexer
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.LinkedList

abstract class ParseTreeElement {
    abstract fun getString(indent : String = ""): String
}

class ParseTreeTerminal(private val token: Token) : ParseTreeElement() {
    override fun getString(indent : String): String {
        val terminalName = FLangLexer.ruleNames[token.type - 1]
        val terminalVal = token.text
        return "${indent}T[$terminalVal]: $terminalName\n"
    }
}

class ParseTreeNode(private val name: String) : ParseTreeElement() {
    val children = LinkedList<ParseTreeElement>()

    fun child(c : ParseTreeElement) : ParseTreeNode {
        children.add(c)
        return this
    }

    override fun getString(indent : String): String {
        val sb = StringBuilder()
        sb.append("$indent$name\n")
        children.forEach { c -> sb.append(c.getString("$indent  ")) }
        return sb.toString()
    }
}

fun toParseTree(node: ParserRuleContext) : ParseTreeNode {
    val res = ParseTreeNode(node.javaClass.simpleName.removeSuffix("Context"))
    node.children.forEach { child ->
        when (child) {
            is ParserRuleContext -> res.child(toParseTree(child))
            is TerminalNode -> res.child(ParseTreeTerminal(child.symbol))
        }
    }
    return res
}