package b02.BOJ_2780_비밀번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, len[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		len = new int[N];

		int maxlen = 0;
		for (int i = 0; i < N; i++) {
			len[i] = Integer.parseInt(br.readLine());
			maxlen = Math.max(maxlen, len[i]);
		}

		int[][] adj = { { 7 }, { 2, 4 }, { 1, 3, 5 }, { 2, 6 }, { 1, 5, 7 }, { 2, 4, 6, 8 }, { 3, 5, 9 }, { 4, 8, 0 },
				{ 5, 7, 9 }, { 6, 8 } };

		// depth 버튼
		int[][] D = new int[maxlen + 1][10];
		Arrays.fill(D[1], 1);

		int[] answer = new int[maxlen + 1];
		answer[1] = 10;

		for (int depth = 2; depth <= maxlen; depth++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < adj[i].length; j++) {
					D[depth][i] += D[depth - 1][adj[i][j]];
				}
				D[depth][i] %= 1234567;
				answer[depth] += D[depth][i];
				answer[depth] %= 1234567;
			}
		}

		for (int i : len)
			System.out.println(answer[i]);
	}

}
