fun main(args: Array<String>) {
	var a = mapOf("1" to "Hello", "2" to "World")
	var printer = fun(str: String): Int{
	print(str)
	return 0
	}
	var b = printer(a["1"]!!)
}
