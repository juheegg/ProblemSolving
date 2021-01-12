package b01.BOJ_1766_문제집;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer> list[] = new ArrayList[N + 1];
		int[] indegree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			indegree[b]++;
		}

		Queue<Integer> que = new PriorityQueue<>();
		boolean[] visit = new boolean[N + 1];
		StringBuilder answer = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				visit[i] = true;
				que.offer(i);
			}
		}

		while (!que.isEmpty()) {
			int cur = que.poll();
			answer.append(cur + " ");

			for (int i = 0; i < list[cur].size(); i++) {
				int target = list[cur].get(i);
				if (visit[target] || --indegree[target] > 0)
					continue;
				visit[target] = true;
				que.offer(target);
			}
		}

		System.out.println(answer);
	}
}
