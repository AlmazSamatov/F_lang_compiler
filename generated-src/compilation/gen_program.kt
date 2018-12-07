fun main(args: Array<String>) {
	var lengthOfArray: (List<Int>) -> (Int) = fun(arr: List<Int>): Int{
	var count: Int = 0
	for (i in arr) {
	count = count + 1
	} 
	return count
	}
	var telephone_book: Map<String, Int> = mutableMapOf("John Lord" to 1000125, "Ian Gillan" to 2200330)
	var info: Map<String, Any> = mapOf("name" to "John", "age" to 21)
	var arr: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	var printTuple: (Map<String, Any>) -> () = fun(t: Map<String, Any>){
	for (e in t) {
	print(e)
	print(" ")
	} 
	print("\n")
	}
	var printArray: (List<Int>) -> () = fun(array: List<Int>){
	for (e in array) {
	print(e)
	print(" ")
	} 
	}
	var main: () -> () = fun(){
	print("Tuple elements: ")
	printTuple (info) 
	print("Array length is: ")
	print(lengthOfArray(arr))
	print("\n")
	print("Array elements are: ")
	printArray (arr) 
	}
	var t: Map<String, Any> = main()
}
