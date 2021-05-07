package KakaoBlindRecruitment2020.P60058_괄호변환;

import java.util.Stack;

public class Solution {

	static public String solution(String p) {
		return recur(p);
	}

	private static String recur(String p) {
		if (p.length() == 0)
			return "";

		int left = 0;
		int right = 0;
		int idx = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(')
				left++;
			else
				right++;
			if (left != 0 && left == right) {
				idx = i + 1;
				break;
			}
		}

		String u = p.substring(0, idx);
		String v = p.substring(idx);

		if (right(u))
			return u + recur(v);

		if (u.length() == 2)
			return "(" + recur(v) + ")";

		return "(" + recur(v) + ")" + reverse(u.substring(1, u.length() - 1));
	}

	private static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(')
				sb.append(')');
			else
				sb.append('(');
		}
		return sb.toString();
	}

	private static boolean right(String str) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.add('(');
			} else {
				if (stack.isEmpty())
					return false;
				stack.pop();
			}
		}
		if (!stack.isEmpty())
			return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(solution("()))((()"));
	}

}
