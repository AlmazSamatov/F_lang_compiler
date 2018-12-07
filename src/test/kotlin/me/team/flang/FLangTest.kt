package me.team.flang

import me.team.f.generator.programToKotlin
import me.team.f.parsing.Analyser
import java.lang.StringBuilder
import org.junit.Test as test
import kotlin.test.*


class FLangTest {

    @test
    fun test_code_1() {
        val code = """
                lengthOfArray is func(arr: [integer]): integer do
                    count is 0

                    for i in arr loop
                        count := count + 1
                    end

                    return count
                end;

                telephone_book is { "John Lord": 1000125, "Ian Gillan": 2200330 };

                info is (name is "John", age is 21);

                arr is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

                printTuple is func(t:(string,integer)) do
                     for e in t loop
                        print e, " "
                     end
                     print "\n"
                end;

                printArray is func(array: [integer]) do
                     for e in array loop
                        print e, " "
                     end
                end;

                main is func()
                do
                     print "Tuple elements: "
                     printTuple(info)
                     print "Array length is: ", lengthOfArray(arr), "\n"
                     print "Array elements are: "
                     printArray(arr)

                end;

                t is main()
        """.trimIndent()

        val correctCode = """
            fun main(args: Array<String>) {
	fun lengthOfArray (arr: List<Int>): Int{
	var count = 0
	for (i in arr) {
	count = count + 1
	}
	return count
	}
	var telephone_book: Map<Any, Any> = mutableMapOf("John Lord" to 1000125, "Ian Gillan" to 2200330)
	var info: Map<Any, Any> = mapOf("name" to "John", "age" to 21)
	var arr: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	fun printTuple (t: Map<Any, Any>){
	for (e in t) {
	print(e)
	print(" ")
	}
	print("\n")
	}
	fun printArray (array: List<Int>){
	for (e in array) {
	print(e)
	print(" ")
	}
	}
	fun main (){
	print("Tuple elements: ")
	printTuple (info)
	print("Array length is: ")
	print(lengthOfArray(arr))
	print("\n")
	print("Array elements are: ")
	printArray (arr)
	}
	var t = main()
}
        """.trimIndent()

        val parseResult = Analyser.parse(code)

        if (parseResult.correct()) {
            val ast = parseResult.root!!

            val kotlinProgram = mutableListOf("fun main(args: Array<String>) {")
            kotlinProgram.addAll(programToKotlin(ast))
            kotlinProgram.add("}")

            var s = StringBuilder()

            kotlinProgram.map { s.append(it) }

            assertEquals(s.toString().trimIndent().replace(" ","").replace("\t", "").replace("\n", ""), correctCode.replace(" ", "").replace("\t", "").replace("\n", ""))
        }
    }


    @test
    fun test_code_2() {
        val code = """
            array is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

            max is array[0];

            find_max is func(arr: [integer]) : integer do
                for i in arr loop
                    if i > max then
                        max := i
                    end
                end
                return max
            end;

            main is func() do
                print "Max is ", find_max(array), "\n"
            end;

            res is main()
        """.trimIndent()

        val correctCode = """
            fun main(args: Array<String>) {
            var array: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
            var max = array[0]
            fun find_max (arr: List<Int>): Int{
            for (i in arr) {
            if (i > max) {
            max = i
            } else {
            }
            }
            return max
            }
            fun main (){
            print("Max is ")
            print(find_max(array))
            print("\n")
            }
            var res = main()
        }
        """.trimIndent()

        val parseResult = Analyser.parse(code)

        if (parseResult.correct()) {
            val ast = parseResult.root!!

            val kotlinProgram = mutableListOf("fun main(args: Array<String>) {")
            kotlinProgram.addAll(programToKotlin(ast))
            kotlinProgram.add("}")

            var s = StringBuilder()

            kotlinProgram.map { s.append(it) }

            assertEquals(s.toString().trimIndent().replace(" ","").replace("\t", "").replace("\n", ""), correctCode.replace(" ", "").replace("\t", "").replace("\n", ""))
        }
    }

    @test
    fun test_code_3() {
        val code = """
            fibb is func(v: integer): integer do
                if (v = 1) | (v = 2) then
                    return 1
                else
                    return fibb(v-1) + fibb(v-2)
                end
            end;

            number: integer is 8;

            calculateFibb is func() do
                print number, "-th fibbonacci number is ", fibb(number)
            end;

            main is calculateFibb()
        """.trimIndent()

        val correctCode = """
            fun main(args: Array<String>) {
            fun fibb (v: Int): Int{
            if (v == 1 || v == 2) {
            return 1
            } else {
            return fibb(v - 1) + fibb(v - 2)
            }
            }
            var number: Int = 8
            fun calculateFibb (){
            print(number)
            print("-th fibbonacci number is ")
            print(fibb(number))
            }
            var main = calculateFibb()
        }
        """.trimIndent()

        val parseResult = Analyser.parse(code)

        if (parseResult.correct()) {
            val ast = parseResult.root!!

            val kotlinProgram = mutableListOf("fun main(args: Array<String>) {")
            kotlinProgram.addAll(programToKotlin(ast))
            kotlinProgram.add("}")

            var s = StringBuilder()

            kotlinProgram.map { s.append(it) }

            assertEquals(s.toString().trimIndent().replace(" ","").replace("\t", "").replace("\n", ""), correctCode.replace(" ", "").replace("\t", "").replace("\n", ""))
        }
    }

}