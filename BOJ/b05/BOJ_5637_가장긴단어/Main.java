package b05.BOJ_5637_가장긴단어;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		String answer = "";

		while (sc.hasNext()) {
			String token = sc.next();

			if (token.equals("E-N-D"))
				break;

			StringBuilder word = new StringBuilder();
			for (int i = 0; i < token.length(); i++) {
				char ch = token.charAt(i);

				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '-') {
					word.append(ch);
				} else {
					if (answer.length() < word.length())
						answer = word.toString();
					word = new StringBuilder();
				}
			}
			if (answer.length() < word.length())
				answer = word.toString();
		}
		
		System.out.println(answer.toLowerCase());
	}

}
