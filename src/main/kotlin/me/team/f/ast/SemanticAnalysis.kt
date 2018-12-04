package me.team.f.ast

import java.util.*

data class Error(val message: String, val position: LineCol)

fun Program.validate() : List<Error> {

    val errors = LinkedList<Error>()
    val vars = HashMap<String, VarDeclaration>()

//    this.specificProcess(VarDeclaration::class.java) {
//        if (vars.containsKey(it.varName)) {
//            errors.add(Error("A variable named '${it.varName}' has been " +
//                    "already declared at ${vars[it.varName]!!.position!!.start}",
//                it.position!!.start))
//        } else {
//            vars[it.varName] = it
//        }
//    }

    // check a variable is not referred before being declared
//    this.specificProcess(VarReference::class.java) {
//        if (!vars.containsKey(it.name)) {
//            errors.add(Error("No variable named '${it.name}'", it.position!!.start))
//        } else if (it.isBefore(vars[it.name]!!)) {
//            errors.add(Error("Variable '${it.name}' was referenced " +
//                    "before its declaration", it.position!!.start))
//        }
//    }
//    this.specificProcess(Assignment::class.java) {
//        if (!varsByName.containsKey(it.)) {
//            errors.add(Error("There is no variable named '${it.varName}'", it.position!!.start))
//        } else if (it.isBefore(varsByName[it.varName]!!)) {
//            errors.add(Error("You cannot refer to variable '${it.varName}' before its declaration", it.position!!.start))
//        }
//    }

    return errors
}