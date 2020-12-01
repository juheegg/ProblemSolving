package b01.BOJ_1753_최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int V, E, K;
	static List<Edge> list[];

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		int a, b, w;
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, w));
		}

		getDijkstra();
	}

	private static void getDijkstra() {
		boolean[] visit = new boolean[V + 1];
		int[] min = new int[V + 1];
		Arrays.fill(min, INF);
		min[K] = 0;

		int cur, val;
		for (int i = 0; i < V; i++) {
			cur = 1;
			val = INF;
			for (int j = 1; j <= V; j++) {
				if (visit[j])
					continue;
				if (val > min[j]) {
					val = min[j];
					cur = j;
				}
			}

			visit[cur] = true;

			Edge target;
			for (int j = 0; j < list[cur].size(); j++) {
				target = list[cur].get(j);
				if (visit[target.dest])
					continue;
				if (min[target.dest] > min[cur] + target.w) {
					min[target.dest] = min[cur] + target.w;
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			System.out.println(min[i] < INF ? min[i] : "INF");
		}

	}

	static class Edge {
		int dest, w;

		public Edge(int dest, int w) {
			super();
			this.dest = dest;
			this.w = w;
		}
	}
}
