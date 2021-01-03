package b01.BOJ_1918_후위표기식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();

		Stack<Character> oper = new Stack<>();
		StringBuilder answer = new StringBuilder();
		for (char ch : line) {
			if (ch >= 'A' && ch <= 'Z') {
				answer.append(ch);
			} else {
				switch (ch) {
				case '*':
				case '/':
					while (!oper.isEmpty() && (oper.peek() == '*' || oper.peek() == '/'))
						answer.append(oper.pop());
					oper.push(ch);
					break;
				case '+':
				case '-':
					while (!oper.isEmpty() && oper.peek() != '(')
						answer.append(oper.pop());
					oper.push(ch);
					break;
				case '(':
					oper.push(ch);
					break;
				case ')':
					while (!oper.isEmpty() && oper.peek() != '(')
						answer.append(oper.pop());
					oper.pop();
					break;
				}
			}
		}
		while (!oper.isEmpty())
			answer.append(oper.pop());
		System.out.println(answer);
	}

}
