import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
	val n = readLine().toInt()
	val strList = ArrayList<String>()
	val stack = Stack<Char>()
	val result = StringBuilder()

	for (i in 0 until n) {
		strList.add(readLine())
	}

	for (str in strList) {
		val target = str.toCharArray()
		stack.clear()
		for (c in target) {
			when (c) {
				'(' -> stack.add('(')
				')' -> if (stack.empty()) {
					stack.add('(')
					break
				} else stack.pop()
			}
		}
		if (stack.empty())
			result.append("YES\n")
		else
			result.append("NO\n")
	}

	print(result)
}
