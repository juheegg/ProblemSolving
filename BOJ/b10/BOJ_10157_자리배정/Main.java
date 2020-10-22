package b10.BOJ_10157_자리배정;

import java.util.Scanner;

public class Main {

	static int C, R, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		R = sc.nextInt();
		K = sc.nextInt();

		String answer = "0";

		if (C * R >= K) {
			answer = calc();
		}

		System.out.println(answer);
	}

	private static String calc() {
		int x = 1, y = 0;
		int cnt = 0;

		int[] len = { R, C - 1, R - 1, C - 2 };
		int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		while (cnt < K) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < len[i]; j++) {
					x += d[i][0];
					y += d[i][1];
					if (++cnt == K) {
						return x + " " + y;
					}
				}
				len[i] -= 2;
			}
		}
		return "0";
	}

}
