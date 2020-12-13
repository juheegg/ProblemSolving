package b16.BOJ_16228_GCC의유산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expr = br.readLine();
		List<String> post = new ArrayList<>();
		Stack<String> op = new Stack<>();
		Stack<Integer> nums = new Stack<>();

		expr = expr.replace("<?", "i");
		expr = expr.replace(">?", "a");
		StringTokenizer st = new StringTokenizer(expr, "ai+-()", true);

		Hashtable<String, Integer> hash = new Hashtable<>();
		hash.put("+", 2);
		hash.put("-", 2);
		hash.put("i", 1);
		hash.put("a", 1);

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if ('0' <= token.charAt(0) && token.charAt(0) <= '9') {
				post.add(token);
			} else {
				if ("(".equals(token)) {
					op.push(token);
				} else if (")".equals(token)) {
					while (!"(".equals(op.peek()))
						post.add(op.pop());
					op.pop();
				} else {
					while (!op.isEmpty() && hash.containsKey(op.peek()) && hash.get(op.peek()) <= hash.get(token)) {
						post.add(op.pop());
					}
					op.push(token);
				}
			}
		}

		while (!op.isEmpty())
			post.add(op.pop());

		for (String str : post) {
			if ('0' <= str.charAt(0) && str.charAt(0) <= '9') {
				nums.push(Integer.parseInt(str));
			} else {
				int b = nums.pop();
				int a = nums.pop();
				switch (str) {
				case "+":
					nums.push(a + b);
					break;
				case "-":
					nums.push(a - b);
					break;
				case "i":
					nums.push(Math.min(a, b));
					break;
				case "a":
					nums.push(Math.max(a, b));
					break;
				}
			}
		}

		System.out.println(nums.pop());
	}

}
