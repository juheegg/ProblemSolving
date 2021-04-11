package b01.BOJ_1325_효율적인해킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
		}

		int[] hackCnt = new int[N + 1];
		for (int start = 1; start <= N; start++) {
			boolean[] visit = new boolean[N + 1];
			Queue<Integer> que = new LinkedList<>();
			visit[start] = true;
			que.add(start);
			while (!que.isEmpty()) {
				int cur = que.poll();
				for (int i = 0; i < adj[cur].size(); i++) {
					int target = adj[cur].get(i);
					if (visit[target])
						continue;
					visit[target] = true;
					hackCnt[target]++;
					que.add(target);
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, hackCnt[i]);
		}
		for (int i = 1; i <= N; i++) {
			if (hackCnt[i] == max)
				System.out.print(i + " ");
		}
	}

}