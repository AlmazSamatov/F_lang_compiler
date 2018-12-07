fun main(args: Array<String>) {
	fun fibb (v: Int): Int{
	if (v == 1 || v == 2) {
	return 1
	} else {
	return fibb(v - 1) + fibb(v - 2)
	} 
	}
	var number: Int = 8
	fun calculateFibb (){
	print(number)
	print("-th fibbonacci number is ")
	print(fibb(number))
	}
	var main = calculateFibb()
}
