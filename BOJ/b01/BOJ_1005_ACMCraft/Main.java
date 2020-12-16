package b01.BOJ_1005_ACMCraft;

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
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] buildtime = new int[N + 1];
			int[] dgr = new int[N + 1];
			int[] max = new int[N + 1];
			List<Integer> adj[] = new ArrayList[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				buildtime[i] = Integer.parseInt(st.nextToken());
				adj[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a].add(b);
				dgr[b]++;
			}

			int W = Integer.parseInt(br.readLine());

			Queue<Integer> que = new LinkedList<>();
			for (int i = 1; i <= N; i++) {
				if (dgr[i] == 0)
					que.offer(i);
			}

			while (!que.isEmpty()) {
				int cur = que.poll();
				if (cur == W)
					break;

				for (int target : adj[cur]) {
					max[target] = Math.max(max[target], max[cur] + buildtime[cur]);
					if (--dgr[target] == 0)
						que.offer(target);
				}
			}

			answer.append((max[W] + buildtime[W]) + "\n");
		}
		System.out.println(answer);
	}

}
