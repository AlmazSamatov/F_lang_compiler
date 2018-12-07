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
