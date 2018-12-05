package me.team.f.ast

import java.util.*

data class Error(val message: String, val position: LineCol)

fun Program.validate(): List<Error> {
    val errors = LinkedList<Error>()

    // check a variable is not duplicated
    val varsByName = HashMap<String, VarDeclaration>()
    this.specificProcess(VarDeclaration::class.java) {
        if (varsByName.containsKey(it.varName)) {
            errors.add(
                Error(
                    "A variable named '${it.varName}' has been already declared at ${varsByName[it.varName]!!.position!!.start}",
                    it.position!!.start
                )
            )
        } else {
            varsByName[it.varName] = it
        }
    }

    this.specificProcess(Parameter::class.java) {

    }

    // check a variable is not referred before being declared
    this.specificProcess(VarReference::class.java) {
        if (!varsByName.containsKey(it.name)) {
            errors.add(Error("There is no variable named '${it.name}'", it.position!!.start))
        } else if (it.isBefore(varsByName[it.name]!!)) {
            errors.add(
                Error(
                    "You cannot refer to variable '${it.name}' before its declaration",
                    it.position!!.start
                )
            )
        }
    }
    this.specificProcess(Assignment::class.java) {
        it.secondary as VarReference
        if (!varsByName.containsKey(it.secondary.name)) {
            errors.add(Error("There is no variable named '${it.secondary.name}'", it.position!!.start))
        } else if (it.isBefore(varsByName[it.secondary.name]!!)) {
            errors.add(Error("You cannot refer to variable '$it' before its declaration", it.position!!.start))
        }
    }

    return errors
}