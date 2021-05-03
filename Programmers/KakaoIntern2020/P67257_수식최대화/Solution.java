package KakaoIntern2020.P67257_수식최대화;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static public long solution(String expression) {
		long answer = 0;

		List<String> exp = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(expression, "*+-", true);
		while (st.hasMoreTokens()) {
			exp.add(st.nextToken());
		}

		String[] priority = { "*+-", "*-+", "+*-", "+-*", "-*+", "-+*" };
		for (int p = 0; p < priority.length; p++) {
			List<String> from = copy(exp);
			for (int op = 0; op < 3; op++) {
				if (from.size() == 1)
					break;
				String cur = priority[p].charAt(op) + "";
				Stack<String> stack = new Stack<>();
				for (int i = 0; i < from.size(); i++) {
					if (cur.equals(from.get(i))) {
						String first = stack.pop();
						String second = from.get(++i);
						stack.push(calc(cur, first, second));
						continue;
					}
					stack.add(from.get(i));
				}
				from = new ArrayList<>(stack);
			}
			long value = Long.parseLong(from.get(0));
			value = value < 0 ? -value : value;
			answer = Math.max(answer, value);
		}

		return answer;
	}

	private static String calc(String cur, String first, String second) {
		long f = Long.parseLong(first);
		long s = Long.parseLong(second);
		long result = 0L;
		switch (cur) {
		case "+":
			result = f + s;
			break;
		case "-":
			result = f - s;
			break;
		case "*":
			result = f * s;
			break;

		}
		return result + "";
	}

	private static List<String> copy(List<String> exp) {
		List<String> copied = new ArrayList<>();
		for (int i = 0; i < exp.size(); i++) {
			copied.add(exp.get(i));
		}
		return copied;
	}

	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
		System.out.println(solution("50*6-3*2"));
	}

}
