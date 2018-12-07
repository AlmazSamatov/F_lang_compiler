fun main(args: Array<String>) {
	var array: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
	var max: Int = array[0]
	var find_max: (List<Int>) -> (Int) = fun(arr: List<Int>): Int{
	for (i in arr) {
	if (i > max) {
	max = i
	} else {
	} 
	} 
	return max
	}
	var main: () -> (Int) = fun(): Int{
	print("Max is ")
	print(find_max(array))
	print("\n")
	return 0
	}
	var res: Int = main()
}
