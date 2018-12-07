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
