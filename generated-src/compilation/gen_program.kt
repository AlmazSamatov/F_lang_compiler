fun main(args: Array<String>) {
	var max_value: (List<Int>) -> (Int) = fun(arr: List<Int>): Int{
	var max: Int = 0
	for (i in arr) {
	if (max < i) {
	max = i
	} else {
	} 
	} 
	return max
	}
	var main: () -> (Int) = fun(): Int{
	var max: Int = max_value(mutableListOf(1, 2, 3, 4, 7, 11, 5, 9))
	print("Max value is ")
	print(max)
	return 0
	}
	var res: Int = main()
}
