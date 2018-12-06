package me.team.f.generator

import me.team.f.ast.*
import me.team.f.ast.Array
import me.team.f.ast.Function
import me.team.f.ast.Map

var used = HashSet<Node>()

fun declarationToKotlin(it: VarDeclaration): String {
    return if (!used.contains(it)) {
        used.add(it)

        if (it.type != null) {
            ("var " + it.varName + ": " + typeToKotlin(it.type)
                    + " = " + exprToKotlin(it.value))
        } else {
            ("var " + it.varName + " = " + exprToKotlin(it.value))
        }
    } else ""
}

fun typeToKotlin(type: Type): String {
    val resultBuilder = StringBuilder()

    when (type) {
        is BooleanType -> resultBuilder.append("Boolean")
        is IntegerType -> resultBuilder.append("Int")
        is RealType -> resultBuilder.append("Double")
        is RationalType -> resultBuilder.append("Rational")
        is ComplexType -> resultBuilder.append("Complex")
        is StringType -> resultBuilder.append("String")
        is FunctionType -> {
            resultBuilder.append("(")
            for (i in 0..type.types.lastIndex) {

                if (i == type.types.lastIndex) {
                    resultBuilder.append(") -> (")
                    resultBuilder.append(typeToKotlin(type.types[i]))
                    resultBuilder.append(")")
                } else {
                    resultBuilder.append(typeToKotlin(type.types[i]))

                    if (i + 1 != type.types.lastIndex)
                        resultBuilder.append(", ")
                }
            }
        }
        is ArrayType -> {
            resultBuilder.append("List<${typeToKotlin(type.type)}>")
        }
        is MapType -> resultBuilder.append("Map<${typeToKotlin(type.types[0])}, " +
                "${typeToKotlin(type.types[1])}>")
        is TupleType -> resultBuilder.append("Map<Any, Any>()")

    }

    return resultBuilder.toString()
}

fun exprToKotlin(expr: Expression): String {
    val resultBuilder = StringBuilder()

    if (!used.contains(expr)) {
        if (expr !is BinaryExpression)
            used.add(expr)

        when (expr) {
            is BinaryExpression -> expr.specificProcess(BinaryExpression::class.java) {
                resultBuilder.append(binaryToKotlin(it))
            }

            // Elementary
            is VarReference -> expr.specificProcess(VarReference::class.java) { resultBuilder.append(it.name) }
            is BoolLit -> expr.specificProcess(BoolLit::class.java) { resultBuilder.append(it.value.toBoolean()) }
            is IntLit -> expr.specificProcess(IntLit::class.java) { resultBuilder.append(it.value.toInt()) }
            is RealLit -> expr.specificProcess(RealLit::class.java) { resultBuilder.append(it.value.toDouble()) }
            is RatLit -> expr.specificProcess(RatLit::class.java) {
                val parts = it.value.split("\\")
                resultBuilder.append("Rational(" + parts[0] + ", " + parts[1] + ")")
            }
            is CompLit -> expr.specificProcess(CompLit::class.java) {
                val parts = it.value.split("i")
                resultBuilder.append("Complex(" + parts[0].toDouble() + ", " + parts[1].toDouble() + ")")
            }
            is StrLit -> expr.specificProcess(StrLit::class.java) { resultBuilder.append(it.value) }

            // Secondary
            is Call -> expr.specificProcess(Call::class.java) {
                val call = StringBuilder()

                call.append((it.secondary as VarReference).name)
                call.append("(")

                var was = false
                for (parameter in it.expressions) {
                    if (was) {
                        call.append(", ")
                    } else {
                        was = true
                    }
                    call.append(exprToKotlin(parameter))
                }

                call.append(")")

                resultBuilder.append(call)
            }

            is ElementOf -> expr.specificProcess(ElementOf::class.java) {
                val elementOf = StringBuilder()

                elementOf.append((it.varName as VarReference).name)
                elementOf.append("[")
                elementOf.append(exprToKotlin(it.index))
                elementOf.append("]")

                resultBuilder.append(elementOf)
            }

            is NamedTupleElement -> expr.specificProcess(NamedTupleElement::class.java) {
                val call = StringBuilder()

                call.append((it.secondary as VarReference).name)
                call.append("[\"${it.fieldName}\"]!!")

                resultBuilder.append(call)
            }

            is UnnamedTupleElement -> expr.specificProcess(UnnamedTupleElement::class.java) {
                val call = StringBuilder()

                call.append((it.secondary as VarReference).name)
                call.append("[\"${it.fieldNum}\"]!!")

                resultBuilder.append(call)
            }

            // Conditional
            is Conditional -> expr.specificProcess(Conditional::class.java) {
                resultBuilder.append(
                    "if (${exprToKotlin(it.predicate)}) {" +
                            "\n\t${exprToKotlin(it.thenExpr)}\n\t} else {" +
                            "\n\t${exprToKotlin(it.elseExpr)}\n\t}"
                )
            }

            // Function
            is Function -> expr.specificProcess(Function::class.java) {
                val parameters = StringBuilder()
                var was = false
                println(it.parameters)
                for (p in it.parameters) {
                    if (was) {
                        parameters.append(", ")
                    } else {
                        was = true
                    }
                    parameters.append(p.parName + ": " + typeToKotlin(p.type))
                }

                val type = if (it.type == null) "" else ": ${typeToKotlin(it.type)}"

                val body = if (it.body.expression != null) {
                    " = " + exprToKotlin(it.body.expression)
                } else {
                    val statements = StringBuilder()
                    it.body.statements?.map { statements.append("\n\t${stmtToKotlin(it)}") }
                    "{$statements\n\t}"
                }

                resultBuilder.append(
                    "fun($parameters)$type$body"
                )
            }

            // Composed types
            is Array -> expr.specificProcess(Array::class.java) {
                val elements = StringBuilder()
                var was = false
                for (p in it.expressions) {
                    if (was) {
                        elements.append(", ")
                    } else {
                        was = true
                    }
                    elements.append(exprToKotlin(p))
                }
                resultBuilder.append("mutableListOf($elements)")
            }

            is Tuple -> expr.specificProcess(Tuple::class.java) {
                val elements = StringBuilder()
                val declarations = StringBuilder()
                var was = false
                var count = 1
                for (element in it.elements) {
                    if (was) {
                        elements.append(", ")
                    } else {
                        was = true
                    }
                    if (element.name != null) {
                        elements.append('"')
                        elements.append(element.name)
                        elements.append('"')
                    } else {
                        elements.append(count)
                    }
                    elements.append(" to ")
                    elements.append(exprToKotlin(element.expression))
                    count++
                }
                resultBuilder.append("mapOf($elements)")
            }

            is Map -> expr.specificProcess(Map::class.java) {
                val elements = StringBuilder()
                var was = false
                for (pair in it.pairs) {
                    if (was) {
                        elements.append(", ")
                    } else {
                        was = true
                    }
                    elements.append(exprToKotlin(pair.expressions[0]))
                    elements.append(" to ")
                    elements.append(exprToKotlin(pair.expressions[1]))
                }
                resultBuilder.append("mutableMapOf($elements)")
            }

        }
    }

    return resultBuilder.toString()
}

fun stmtToKotlin(stmt: Statement): String {
    val resultBuilder = StringBuilder()

    if(!used.contains(stmt)) {
        if (stmt !is VarDeclaration)
            used.add(stmt)

        when (stmt) {
            is Assignment -> stmt.specificProcess(Assignment::class.java) {
                resultBuilder.append(exprToKotlin(it.secondary) + " = " + exprToKotlin(it.expression) + "")
            }

            is FunctionCall -> stmt.specificProcess(FunctionCall::class.java) {
                val code = StringBuilder()

                code.append(exprToKotlin(it.secondary) + " (")
                var i = 0
                while (i < it.expressions.size - 1) {
                    code.append(exprToKotlin(it.expressions[i]))
                    code.append(", ")
                    i += 1
                }
                code.append(exprToKotlin(it.expressions[it.expressions.size - 1]))
                code.append(") ")

                resultBuilder.append(code)
            }

            is IfStatement -> stmt.specificProcess(IfStatement::class.java) {
                val code = StringBuilder()

                code.append("if (" + exprToKotlin(it.predicate) + ") {\n\t")
                for (expression in it.thenStatements) {
                    code.append("${stmtToKotlin(expression)}\n\t")
                }
                code.append("}")
                if (!it.thenStatements.isEmpty()) {
                    code.append(" else {\n\t")
                    for (expression in it.elseStatements) {
                        code.append("${stmtToKotlin(expression)}\n\t")
                    }
                    code.append("} ")
                }

                resultBuilder.append(code)
            }

            is LoopStatement -> stmt.specificProcess(LoopStatement::class.java) {
                val code = StringBuilder()

                when (it.loopHeader) {
                    is ForLoopHeader -> it.loopHeader.specificProcess(ForLoopHeader::class.java) {
                        code.append("for (" + it.id + " in " + exprToKotlin(it.expressions[0]))
                        if (it.needRange) {
                            code.append(".." + exprToKotlin(it.expressions[1]))
                        }
                        code.append(") ")
                    }

                    is WhileLoopHeader -> it.loopHeader.specificProcess(WhileLoopHeader::class.java) {
                        code.append("while (" + exprToKotlin(it.expressions[0]) + ") ")
                    }
                }

                code.append("{\n\t")
                for (statement in it.statements) {
                    code.append("${stmtToKotlin(statement)}\n\t")
                }
                code.append("} ")

                resultBuilder.append(code)
            }

            is ReturnStatement -> stmt.specificProcess(ReturnStatement::class.java) {
                resultBuilder.append("return " + exprToKotlin(it.expression) + "")
            }

            is BreakStatement -> stmt.specificProcess(BreakStatement::class.java) {
                resultBuilder.append("break ")
            }

            is PrintStatement -> stmt.specificProcess(PrintStatement::class.java) {
                val code = StringBuilder()

                var i = 0
                while (i < it.expressions.size) {
                    code.append("print(${exprToKotlin(it.expressions[i])})")
                    if (i < it.expressions.size - 1)
                        code.append("\n\t")
                    i += 1
                }

                resultBuilder.append(code)
            }

            is VarDeclaration -> stmt.specificProcess((VarDeclaration::class.java)) {
                resultBuilder.append(declarationToKotlin(it))
            }
        }
    }

    return resultBuilder.toString()
}

fun binaryToKotlin(expr: BinaryExpression): String {
    val resultBuilder = StringBuilder()

    if (!used.contains(expr)) {
        used.add(expr)

        when (expr) {
            is SumExpression -> expr.specificProcess(SumExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " + " + exprToKotlin(it.right))
            }
            is SubExpression -> expr.specificProcess(SubExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " - " + exprToKotlin(it.right))
            }
            is MultExpression -> expr.specificProcess(MultExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " * " + exprToKotlin(it.right))
            }
            is DivExpression -> expr.specificProcess(DivExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " / " + exprToKotlin(it.right))
            }
            is LessExpression -> expr.specificProcess(LessExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " < " + exprToKotlin(it.right))
            }
            is GreaterExpression -> expr.specificProcess(GreaterExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " > " + exprToKotlin(it.right))
            }
            is LessEqExpression -> expr.specificProcess(LessEqExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " <= " + exprToKotlin(it.right))
            }
            is GreaterEqExpression -> expr.specificProcess(GreaterEqExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " >= " + exprToKotlin(it.right))
            }
            is EqualExpression -> expr.specificProcess(EqualExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " = " + exprToKotlin(it.right))
            }
            is NotEqExpression -> expr.specificProcess(NotEqExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " /= " + exprToKotlin(it.right))
            }
            is AndExpression -> expr.specificProcess(AndExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " & " + exprToKotlin(it.right))
            }
            is OrExpression -> expr.specificProcess(OrExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " | " + exprToKotlin(it.right))
            }
            is XorExpression -> expr.specificProcess(XorExpression::class.java) {
                resultBuilder.append(exprToKotlin(it.left) + " ^ " + exprToKotlin(it.right))
            }
        }
    }

    return resultBuilder.toString()
}