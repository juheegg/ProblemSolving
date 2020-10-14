package b01.BOJ_1949_우수마을;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, pop[];
	static List<Integer> adj[];
	static int dp[][];
	static boolean visit[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		pop = new int[N + 1];
		adj = new ArrayList[N + 1];
		dp = new int[N + 1][2];
		visit = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}

		int a, b;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}

		visit[1] = true;
		dfs(1);

		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	private static void dfs(int cur) {
		int child = 0;
		for (int i = 0; i < adj[cur].size(); i++) {
			child = adj[cur].get(i);
			if (visit[child])
				continue;
			visit[child] = true;
			dfs(child);
			dp[cur][1] += dp[child][0];
			dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
		}
		dp[cur][1] += pop[cur];
	}
}
