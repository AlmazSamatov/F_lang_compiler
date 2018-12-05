package me.team.f.ast

import me.team.f.ast.Error as Error

class Validator() {
    val symbolTable = HashMap<String, Type>()

    fun validate(ast: Program): List<Error> {
        val errors = mutableListOf<Error>()

        ast.declarations.map {
            errors.addAll(validateDeclaration(it as VarDeclaration))
        }

        return errors
    }

    private fun validateDeclaration(declaration: VarDeclaration): List<Error> {
        val errors = mutableListOf<Error>()

        // Check if variable already exists
        if (symbolTable.containsKey(declaration.varName))
            errors.add(Error("Variable ${declaration.varName} is already declared.",
                declaration.position!!.start))
        else
            symbolTable[declaration.varName]

        return errors
    }
}