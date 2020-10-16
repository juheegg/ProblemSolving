package b02.BOJ_2688_줄어들지않아;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int T, n[];

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		n = new int[T];

		int max = 0;
		for (int i = 0; i < T; i++) {
			n[i] = sc.nextInt();
			if (max < n[i])
				max = n[i];
		}

		long dp[][] = new long[max + 1][10];

		Arrays.fill(dp[0], 1);

		for (int i = 1; i <= max; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
				}
			}
		}

		for (int i = 0; i < T; i++) {
			System.out.println(dp[n[i]][9]);
		}

	}

}
