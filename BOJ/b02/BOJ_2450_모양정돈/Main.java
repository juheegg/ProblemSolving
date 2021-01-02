package b02.BOJ_2450_모양정돈;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		int[] output = new int[N];
		int[] cnt = new int[4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			cnt[input[i]]++;
		}

		int answer = Integer.MAX_VALUE;
		int[][] perm = { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 } };
		for (int step = 0; step < 6; step++) {
			int one = cnt[perm[step][0]];
			int two = one + cnt[perm[step][1]];
			int three = two + cnt[perm[step][2]];
			Arrays.fill(output, 0, one, perm[step][0]);
			Arrays.fill(output, one, two, perm[step][1]);
			Arrays.fill(output, two, three, perm[step][2]);

			int[][] D = new int[4][4];
			for (int i = 0; i < N; i++) {
				D[output[i]][input[i]]++;
			}

			answer = Math.min(answer, D[2][1] + D[3][1] + Math.max(D[2][3], D[3][2]));
		}

		System.out.println(answer);
	}

}
