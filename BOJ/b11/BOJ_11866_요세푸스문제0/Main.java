package b11.BOJ_11866_요세푸스문제0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		StringBuilder answer = new StringBuilder();
		answer.append("<");

		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		int cnt = 0;
		while (!que.isEmpty()) {
			if (++cnt == K) {
				cnt = 0;
				answer.append(que.poll());
				if (que.size() > 0)
					answer.append(", ");
				continue;
			}
			que.offer(que.poll());
		}
		
		answer.append(">");
		System.out.println(answer);
	}

}
