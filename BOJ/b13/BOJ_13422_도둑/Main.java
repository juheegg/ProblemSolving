package b13.BOJ_13422_도둑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] money = new int[N];
			int sum = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				money[i] = Integer.parseInt(st.nextToken());
				if (i < M)
					sum += money[i];
			}

			int cnt = 0;
			if (sum < K)
				cnt++;

			for (int i = 1; i < N; i++) {
				if (N == M)
					break;

				sum -= money[i - 1];

				int rear = i + M - 1;
				sum += money[rear % N];

				if (sum < K)
					cnt++;
			}

			answer.append(cnt + "\n");
		}
		System.out.println(answer);
	}

}
