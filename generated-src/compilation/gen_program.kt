fun main(args: Array<String>) {
	var g: (Int) -> (Int) = fun(x: Int) = x + 1
	var h: (Int) -> (Int) = fun(x: Int) = x * x
	var f: (Int) -> (Int) = fun(y: Int): Int {
	var x: Int = 5
	return g(x) + h(x)
	}
	var main: () -> (Int) = fun(): Int{
	print(f(5))
	return 0
	}
	var res: Int = main()
}
