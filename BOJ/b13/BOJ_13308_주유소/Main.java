package b13.BOJ_13308_주유소;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cost[];
	static List<Edge> list[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cost = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		int a, b, distance;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, distance));
			list[b].add(new Edge(a, distance));
		}

		System.out.println(getMinCost());

	}

	private static long getMinCost() {
		Queue<Path> que = new PriorityQueue<>();
		que.offer(new Path(1, 1, 0L));

		boolean visit[][] = new boolean[N + 1][N + 1];

		while (!que.isEmpty()) {
			Path cur = que.poll();

			if (cur.a == N)
				return cur.sum;
			
			if(visit[cur.a][cur.mincostidx])
				continue;
			visit[cur.a][cur.mincostidx] = true;
			
			for (int i = 0; i < list[cur.a].size(); i++) {
				Edge e = list[cur.a].get(i);
				int mci = cost[cur.mincostidx] < cost[e.b] ? cur.mincostidx : e.b;
				que.offer(new Path(e.b, mci, cur.sum + e.d * cost[cur.mincostidx]));
			}
		}
		return 0L;
	}

	static class Edge {
		int b, d;

		public Edge(int b, int d) {
			super();
			this.b = b;
			this.d = d;
		}
	}

	static class Path implements Comparable<Path> {
		int a, mincostidx;
		long sum;

		public Path(int a, int mincostidx, long sum) {
			super();
			this.a = a;
			this.mincostidx = mincostidx;
			this.sum = sum;
		}

		@Override
		public int compareTo(Path o) {
			return Long.compare(this.sum, o.sum);
		}

		@Override
		public String toString() {
			return "Path [a=" + a + ", mincostidx=" + mincostidx + ", sum=" + sum + "]";
		}
		

	}
}
