package b02.BOJ_2666_벽장문의이동;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int N, open1, open2, M, move[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		open1 = sc.nextInt();
		open2 = sc.nextInt();
		M = sc.nextInt();
		move = new int[M + 1];

		for (int i = 1; i <= M; i++) {
			move[i] = sc.nextInt();
		}

		int D[][][] = new int[M + 1][N + 1][N + 1];

		for (int i = 0; i < M + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				Arrays.fill(D[i][j], INF);
			}
		}

		if (open1 < open2)
			D[0][open1][open2] = 0;
		else
			D[0][open2][open1] = 0;

		for (int step = 1; step <= M; step++) {

			for (int i = 1; i < N; i++) {
				for (int j = i; j <= N; j++) {

					if (D[step - 1][i][j] == INF)
						continue;

					int moveL = Math.abs(i - move[step]);
					int moveR = Math.abs(j - move[step]);

					if (move[step] <= i) {
						D[step][move[step]][j] = Math.min(D[step][move[step]][j], D[step - 1][i][j] + moveL);
					} else if (move[step] >= j) {
						D[step][i][move[step]] = Math.min(D[step][i][move[step]], D[step - 1][i][j] + moveR);
					} else {
						D[step][move[step]][j] = Math.min(D[step][move[step]][j], D[step - 1][i][j] + moveL);
						D[step][i][move[step]] = Math.min(D[step][i][move[step]], D[step - 1][i][j] + moveR);
					}
				}
			}
		}

		int answer = INF;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				answer = Math.min(answer, D[M][i][j]);
			}
		}
		System.out.println(answer);
	}

}
