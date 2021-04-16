package b11.BOJ_11866_요세푸스문제0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] people = new boolean[N + 1];

		StringBuilder answer = new StringBuilder();
		answer.append("<");

		int pointer = 1;
		for (int delCnt = 0; delCnt < N; delCnt++) {
			int cnt = 0;
			while (cnt < K) {
				pointer = pointer == N + 1 ? 1 : pointer;
				if (!people[pointer++]) {
					cnt++;
				}
			}
			people[--pointer] = true;
			answer.append(pointer);
			if (delCnt < N - 1)
				answer.append(", ");
		} // 1 2 3 4 5 6 7

		answer.append(">");
		System.out.println(answer);
	}

}
