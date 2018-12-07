fun main(args: Array<String>) {
	var a: Map<Any, Any> = mapOf("1" to "Hello", "2" to "World")
	fun printer (str: String): Int{
	print(str)
	return 0
	}
	var b = printer(a["1"]!!)
}
