fun main(args: Array<String>) {
	var a: (Int, Int) -> (Int) = fun(f: Int, s: Int) = 5 * 7 + s - f
	var b: String = if (5 + 1 > 2) {
	"Hello"
	} else {
	"World"
	}
	var t: List<Int> = mutableListOf(5, 7, 9)
	var r: Int = t[1]
	var printer: (Int) -> (Int) = fun(value: Int): Int {
	print("Result is ")
	print(value)
	return 5
	}
	var printed_r: Int = printer(r)
}
