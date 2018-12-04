package me.team.f.ast

import me.team.f.Caller
import me.team.fproject.FLangLexer
import me.team.fproject.FLangParser
import me.team.fproject.FLangParser.ProgramContext
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

fun Node.process(operation: (Node) -> Unit) {
    operation(this)
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> v.process(operation)
            is Collection<*> -> v.forEach { if (it is Node) it.process(operation) }
        }
    }
}

fun Node.transform(operation: (Node) -> Node) : Node {
    operation(this)
    val changes = HashMap<String, Any>()
    this.javaClass.kotlin.memberProperties.forEach { p ->
        val v = p.get(this)
        when (v) {
            is Node -> {
                val newValue = v.transform(operation)
                if (newValue != v) changes[p.name] = newValue
            }
            is Collection<*> -> {
                val newValue = v.map { if (it is Node) it.transform(operation) else it }
                if (newValue != v) changes[p.name] = newValue
            }
        }
    }
    var instanceToTransform = this
    if (!changes.isEmpty()) {
        val constructor = this.javaClass.kotlin.primaryConstructor!!
        val params = HashMap<KParameter, Any?>()
        constructor.parameters.forEach { param ->
            if (changes.containsKey(param.name)) {
                params[param] = changes[param.name]
            } else {
                params[param] = this.javaClass.kotlin.memberProperties.find { param.name == it.name }!!.get(this)
            }
        }
        instanceToTransform = constructor.callBy(params)
    }
    return operation(instanceToTransform)
}

fun <T: Node> Node.specificProcess(klass: Class<T>, operation: (T) -> Unit) {
    process { if (klass.isInstance(it)) { operation(it as T) } }
}



data class Error(val message:String?, val position: LineCol)

fun Program.validate() : List<Error> {
    val errors = LinkedList<Error>()

    // check a variable is not duplicated
    val varsByName = HashMap<String, VarDeclaration>()
    this.specificProcess(VarDeclaration::class.java) {
        if (varsByName.containsKey(it.varName)) {
            errors.add(Error("A variable named '${it.varName}' has been already declared at ${varsByName[it.varName]!!.position!!.start}",
                it.position!!.start
            ))
        } else {
            varsByName[it.varName] = it
        }
    }

    // check a variable is not referred before being declared
    this.specificProcess(VarReference::class.java) {
        if (!varsByName.containsKey(it.name)) {
            errors.add(Error("There is no variable named '${it.name}'", it.position!!.start))
        } else if (varsByName[it.name] != null) {
            errors.add(Error("You cannot refer to variable '${it.name}' before its declaration", it.position!!.start))
        }
    }
    this.specificProcess(Assignment::class.java) {
        if (!varsByName.containsKey(it.varName)) {
            errors.add(Error("There is no variable named '${it.varName}'", it.position!!.start))
        } else if (varsByName[it.varName] != null) {
            errors.add(Error("You cannot refer to variable '${it.varName}' before its declaration", it.position!!.start))
        }
    }

    return errors
}
fun String.toStream(charset: Charset = Charsets.UTF_8) = ByteArrayInputStream(toByteArray(charset))

data class Validator(val program: ProgramContext?,val errors: List<Error>){
    fun isCorrect() = errors.isEmpty() && program != null
}

object FLangParserAntlrFacade{
    private val caller: Caller = Caller()
    fun parseCode(code:String){
        parse(code.toStream())
    }
    fun parseFile(name:String){
        parse(FileInputStream(File(name)))

    }
    fun parse(stream: InputStream): Validator {
        val lexicalAndSyntacticErrors = LinkedList<Error>()
        val errorListener = object : ANTLRErrorListener{
            override fun syntaxError(
                p0: Recognizer<*, *>?,
                p1: Any?,
                p2: Int,
                p3: Int,
                p4: String?,
                p5: RecognitionException?
            ) {
                lexicalAndSyntacticErrors.add(Error(p4, LineCol(p2, p3)))
            }

            override fun reportAmbiguity(
                p0: Parser?,
                p1: DFA?,
                p2: Int,
                p3: Int,
                p4: Boolean,
                p5: BitSet?,
                p6: ATNConfigSet?
            ) {

            }

            override fun reportContextSensitivity(p0: Parser?, p1: DFA?, p2: Int, p3: Int, p4: Int, p5: ATNConfigSet?) {

            }

            override fun reportAttemptingFullContext(
                p0: Parser?,
                p1: DFA?,
                p2: Int,
                p3: Int,
                p4: BitSet?,
                p5: ATNConfigSet?
            ) {

            }

        }
        val lexer = FLangLexer(ANTLRInputStream(stream))
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)
        val parser = FLangParser(CommonTokenStream(lexer))
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        val antlrRoot = parser.program()
        return Validator(antlrRoot, lexicalAndSyntacticErrors)
    }


}

data class ParsingResult(val root : Program?, val errors: List<Error>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

object FLangParserFacade {

    fun parse(code: String) : ParsingResult = parse(code.toStream())

    fun parse(file: File) : ParsingResult = parse(FileInputStream(file))

    fun parse(inputStream: InputStream) : ParsingResult {
        val antlrParsingResult = FLangParserAntlrFacade.parse(inputStream)
        val lexicalAnsSyntaticErrors = antlrParsingResult.errors
        val antlrRoot = antlrParsingResult.program
        val astRoot = antlrRoot?.toAst(considerPosition = true)
        val semanticErrors = astRoot?.validate() ?: emptyList()
        return ParsingResult(astRoot, lexicalAnsSyntaticErrors + semanticErrors)
    }

}