fun main(args: Array<String>) {
	var fibb = fun(v: Int): Int{
	if (v = 1 | v = 2) {
	return 1
	} else {
	return fibb(v - 1) + fibb(v - 2)
	} 
	}
	var u: Int = 3
	var f = fun(){
	print("Fibbonacci of ")
	print(u)
	print(" is ")
	print(fibb(u))
	}
	var main:  = f()
}
