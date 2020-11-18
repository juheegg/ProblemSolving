package b01.BOJ_1003_피보나치함수;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] N = new int[T];

		int max = 0;
		for (int i = 0; i < T; i++) {
			N[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, N[i]);
		}

		int[][] call = new int[max + 1][2];
		for (int i = 0; i <= max; i++) {
			if (i == 0) {
				call[i][0] = 1;
				call[i][1] = 0;
			} else if (i == 1) {
				call[i][0] = 0;
				call[i][1] = 1;
			} else {
				call[i][0] = call[i - 1][0] + call[i - 2][0];
				call[i][1] = call[i - 1][1] + call[i - 2][1];
			}
		}

		for (int i = 0; i < T; i++) {
			System.out.println(call[N[i]][0] + " " + call[N[i]][1]);
		}
	}

}