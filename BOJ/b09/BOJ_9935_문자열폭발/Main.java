package b09.BOJ_9935_문자열폭발;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

		static String target, pattern;
	static char first;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		target = br.readLine();
		pattern = br.readLine();

		int plen = pattern.length();
		char last = pattern.charAt(plen - 1);

		StringBuilder answer = new StringBuilder();

		for (int c = 0; c < target.length(); c++) {
			answer.append(target.charAt(c));
			if (answer.charAt(answer.length() - 1) == last && answer.length() >= plen) {
				if (answer.substring(answer.length() - plen, answer.length()).toString().equals(pattern)) {
					answer.delete(answer.length() - plen, answer.length());
				}
			}
		}

		if (answer.length() == 0)
			answer.append("FRULA");

		System.out.println(answer);

	}
}
