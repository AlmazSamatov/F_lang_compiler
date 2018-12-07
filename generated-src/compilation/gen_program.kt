fun main(args: Array<String>) {
	var swap: (Int, Int) -> (Int) = fun(l: Int, r: Int): Int{
	var tmp: Int = l
	l = r
	r = tmp
	return 0
	}
	var main: () -> (Int) = fun(): Int{
	var k: Int = 5
	var m: Int = 7
	swap (k, m) 
	print(k)
	print(m)
	return 0
	}
	var res: Int = main()
}
