package CT.NHN2020;

import java.util.Scanner;
import java.util.Stack;

public class test03 {

	private static void solution(int numOfOrder, String[] orderArr) {

		StringBuilder answer = new StringBuilder();
		Stack<StringBuilder> stack = new Stack<>();

		for (int i = 0; i < numOfOrder; i++) {
			stack.clear();

			StringBuilder cur = new StringBuilder();
			for (int j = 0; j < orderArr[i].length(); j++) {
				switch (orderArr[i].charAt(j)) {
				case '(':
					stack.add(cur);
					cur = new StringBuilder();
					break;
				case ')':
					StringBuilder pre = stack.pop();
					char last = pre.charAt(pre.length() - 1);
					if (last == 'R' || last == 'G' || last == 'B') {
						pre.append(cur.charAt(0));
						for (int k = 1; k < cur.length(); k++) {
							pre.append("" + last + cur.charAt(k));
						}
					} else {
						pre = new StringBuilder(pre.substring(0, pre.length() - 1));
						for (int k = 0; k < last - '0'; k++) {
							pre.append(cur);
						}
					}
					cur = pre;
					break;
				case 'R':
				case 'G':
				case 'B':
					cur.append(orderArr[i].charAt(j));
					break;
				default:
					if (j < orderArr[i].length() - 1 && orderArr[i].charAt(j + 1) != '(') {
						for (int k = 0; k < orderArr[i].charAt(j) - '0'; k++) {
							cur.append(orderArr[i].charAt(j + 1));
						}
						j++;
					} else {
						cur.append(orderArr[i].charAt(j));
					}
					break;
				}
				System.out.println(j + "--------");
				System.out.println(stack.toString());
				System.out.println(cur);
				System.out.println();
			}

			System.out.println();
			System.out.println();
			answer.append(cur + "\n");
		}
		System.out.println(answer);
	}

	private static class InputData {
		int numOfOrder;
		String[] orderArr;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfOrder = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.orderArr = new String[inputData.numOfOrder];
			for (int i = 0; i < inputData.numOfOrder; i++) {
				inputData.orderArr[i] = scanner.nextLine().replaceAll("\\s+", "");
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.numOfOrder, inputData.orderArr);
	}
}

/*
 * 
 * stack B
 * 
 * 2 B2(RG) 3(R2(GB)) BRGRG RGBGBRGBGBRGBGB
 * 
 * 3 3(BR2(R)) B(RGB(RG)) 1B2R3G
 * 
 * BRRRBRRRBRRR BRBGBBBRBBBG BRRGGG
 * 
 * BRRRBRRRBRRR BRBGBBBRBBBG BRRGGG
 * 
 * 2(B(R)2(G))1(B2(B(R)))
 * 
 * BRGGBRGGBBRBR
 * 
 */
