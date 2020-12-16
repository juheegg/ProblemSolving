package b17.BOJ_17270_연예인은힘들어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int V;
	static List<Edge> adj[];
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(a, b, w));
			adj[b].add(new Edge(b, a, w));
		}

		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] Jdis = new int[V + 1];
		int[] Sdis = new int[V + 1];
		getDijkstra(J, Jdis);
		getDijkstra(S, Sdis);

		int tot = INF;
		for (int i = 1; i <= V; i++) {
			if (i == J || i == S)
				continue;
			tot = Math.min(tot, Jdis[i] + Sdis[i]);
		}

		int answer = 0;
		int Jmin = INF;
		for (int i = 1; i <= V; i++) {
			if (i == J || i == S)
				continue;
			if (Jdis[i] + Sdis[i] != tot)
				continue;
			if (Jdis[i] > Sdis[i])
				continue;
			if (Jmin > Jdis[i]) {
				answer = i;
				Jmin = Jdis[i];
			}
		}

		System.out.println(answer == 0 ? -1 : answer);
	}

	private static void getDijkstra(int start, int[] dis) {
		boolean[] visit = new boolean[V + 1];

		Arrays.fill(dis, INF);
		dis[start] = 0;

		int val, idx;
		for (int step = 0; step < V; step++) {
			val = INF;
			idx = 0;
			for (int i = 1; i <= V; i++) {
				if (visit[i])
					continue;
				if (val > dis[i]) {
					val = dis[i];
					idx = i;
				}
			}

			visit[idx] = true;

			for (int i = 0; i < adj[idx].size(); i++) {
				Edge cur = adj[idx].get(i);
				if (visit[cur.b])
					continue;
				if (dis[cur.b] > dis[idx] + cur.w)
					dis[cur.b] = dis[idx] + cur.w;
			}
		}
	}

	static class Edge {
		int a, b, w;

		public Edge(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

	}
}
