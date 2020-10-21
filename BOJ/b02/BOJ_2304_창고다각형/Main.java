package b02.BOJ_2304_창고다각형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, pillar[][];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		pillar = new int[N][2];

		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				pillar[i][j] = Integer.parseInt(st.nextToken());
			}
			max = Math.max(max, pillar[i][0]);
		}

		int roof[] = new int[max + 1];
		for (int i = 0; i < N; i++) {
			roof[pillar[i][0]] = pillar[i][1];
		}

		max = 0;
		int top = 0;
		for (int i = 0; i < roof.length; i++) {
			if (max < roof[i]) {
				top = i;
				max = roof[i];
			}
			roof[i] = max;
		}

		if (top != roof.length - 1) {
			Arrays.fill(roof, top + 1, roof.length - 1, 0);

			for (int i = 0; i < N; i++) {
				if (pillar[i][0] > top)
					roof[pillar[i][0]] = pillar[i][1];
			}

			max = 0;
			for (int i = roof.length - 1; i > top; i--) {
				if (max < roof[i]) {
					max = roof[i];
				}
				roof[i] = max;
			}
		}
		int answer = 0;
		for (int i = 0; i < roof.length; i++) {
			answer += roof[i];
		}

		System.out.println(answer);
	}

}
