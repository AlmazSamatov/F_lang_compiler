package me.team.f.ast

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType
//import jdk.internal.org.objectweb.asm.ClassWriter
//import jdk.internal.org.objectweb.asm.Label
//import jdk.internal.org.objectweb.asm.Opcodes.*
import java.io.File
import java.io.FileInputStream
//import java.io.FileOutputStream
import java.io.InputStream

//class JvmCompiler {
//
//    fun compile(root: Program, name: String) : ByteArray {
//        // this is how we tell ASM that we want to start writing a new class. We ask it to calculate some values for us
//        val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES or ClassWriter.COMPUTE_MAXS)
//        // here we specify that the class is in the format introduced with Java 8 (so it would require a JRE >= 8 to run)
//        // we also specify the name of the class, the fact it extends Object and it implements no interfaces
//        cw.visit(V1_8, ACC_PUBLIC, name, null, "java/lang/Object", null)
//        // our class will have just one method: the main method. We have to specify its signature
//        // this string just says that it takes an array of Strings and return nothing (void)
//        val mainMethodWriter = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
//        mainMethodWriter.visitCode()
//        // labels are used by ASM to mark points in the code
//        val methodStart = Label()
//        val methodEnd = Label()
//        // with this call we indicate to what point in the method the label methodStart corresponds
//        mainMethodWriter.visitLabel(methodStart)
//
//        // Variable declarations:
//        // we find all variable declarations in our code and we assign to them an index value
//        // our vars map will tell us which variable name corresponds to which index
//        var nextVarIndex = 0
//        val vars = HashMap<String, Var>()
//        root.specificProcess(VarDeclaration::class.java) {
//            val index = nextVarIndex++
//            vars[it.varName] = Var(it.type(vars), index)
//            mainMethodWriter.visitLocalVariable(it.varName, it.type(vars).jvmDescription, null, methodStart, methodEnd, index)
//        }
//
//        // time to generate bytecode for all the statements
//        root.declarations.forEach { s ->
//            when (s) {
//                is VarDeclaration -> {
//                    // we calculate the type of the variable (more details later)
//                    val type = vars[s.varName]!!.type
//                    // the JVM is a stack based machine: it operated with values we have put on the stack
//                    // so as first thing when we meet a variable declaration we put its value on the stack
//                    s.value.pushAs(mainMethodWriter, vars, type)
//                    // now, depending on the type of the variable we use different operations to store the value
//                    // we put on the stack into the variable. Note that we refer to the variable using its index, not its name
//                    when (type) {
//                        IntType -> mainMethodWriter.visitVarInsn(ISTORE, vars[s.varName]!!.index)
//                        DecimalType -> mainMethodWriter.visitVarInsn(DSTORE, vars[s.varName]!!.index)
//                        else -> throw UnsupportedOperationException(type.javaClass.canonicalName)
//                    }
//                }
//                is Print -> {
//                    // this means that we access the field "out" of "java.lang.System" which is of type "java.io.PrintStream"
//                    mainMethodWriter.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
//                    // we push the value we want to print on the stack
//                    s.value.push(mainMethodWriter, vars)
//                    // we call the method println of System.out to print the value. It will take its parameter from the stack
//                    // note that we have to tell the JVM which variant of println to call. To do that we describe the signature of the method,
//                    // depending on the type of the value we want to print. If we want to print an int we will produce the signature "(I)V",
//                    // we will produce "(D)V" for a double
//                    mainMethodWriter.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(${s.value.type(vars).jvmDescription})V", false)
//                }
//                is Assignment -> {
//                    val type = vars[s.varName]!!.type
//                    // This code is the same we have seen for variable declarations
//                    s.value.pushAs(mainMethodWriter, vars, type)
//                    when (type) {
//                        IntType -> mainMethodWriter.visitVarInsn(ISTORE, vars[s.varName]!!.index)
//                        DecimalType -> mainMethodWriter.visitVarInsn(DSTORE, vars[s.varName]!!.index)
//                        else -> throw UnsupportedOperationException(type.javaClass.canonicalName)
//                    }
//                }
//                else -> throw UnsupportedOperationException(s.javaClass.canonicalName)
//            }
//        }
//
//        // We just says that here is the end of the method
//        mainMethodWriter.visitLabel(methodEnd)
//        // And we had the return instruction
//        mainMethodWriter.visitInsn(RETURN)
//        mainMethodWriter.visitEnd()
//        mainMethodWriter.visitMaxs(-1, -1)
//        cw.visitEnd()
//        return cw.toByteArray()
//    }
//
//}

fun main(args: Array<String>) {
    val code : InputStream? = when (args.size) {
        0 -> System.`in`
        1 -> FileInputStream(File(args[0]))
        else -> {
            System.err.println("Pass 0 arguments or 1")
            System.exit(1)
            null
        }
    }
    val parsingResult = FLangParserFacade.parse(code!!)
    if (!parsingResult.isCorrect()) {
        println("ERRORS:")
        parsingResult.errors.forEach { println(" * L${it.position.line}: ${it.message}") }
        return
    }
    val root = parsingResult.root!!
    println(root)
    val errors = root.validate()
    if (errors.isNotEmpty()) {
        println("ERRORS:")
        errors.forEach { println(" * L${it.position.line}: ${it.message}") }
        return
    }
//    val bytes = JvmCompiler().compile(root, "MyClass")
//    val fos = FileOutputStream("MyClass.class")
//    fos.write(bytes)
//    fos.close()
}