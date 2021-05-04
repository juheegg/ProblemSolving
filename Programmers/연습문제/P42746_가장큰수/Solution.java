package 연습문제.P42746_가장큰수;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

	static public String solution(int[] numbers) {
		StringBuilder answer = new StringBuilder();

		String[] n = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			n[i] = "" + numbers[i];
		}

		Arrays.sort(n, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2);
			}
		});

		if (n[0].equals("0"))
			return "0";

		for (int i = 0; i < n.length; i++) {
			answer.append(n[i]);
		}

		return answer.toString();
	}

	public static void main(String[] args) {
		int[] n = { 6, 10, 2 };
		System.out.println(solution(n));
		int[] n2 = { 3, 30, 34, 5, 9 };
		System.out.println(solution(n2));
		int[] n3 = { 21, 212 };
		System.out.println(solution(n3));
	}

}
