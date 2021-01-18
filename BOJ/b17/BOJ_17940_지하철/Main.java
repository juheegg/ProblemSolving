package b17.BOJ_17940_지하철;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[] visit = new boolean[N];
		boolean[] corp = new boolean[N];
		int[][] adj = new int[N][N];

		for (int i = 0; i < N; i++) {
			corp[i] = Integer.parseInt(br.readLine()) == 1 ? true : false;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Station> que = new PriorityQueue<>();
		que.offer(new Station(0, 0, 0));

		while (!que.isEmpty()) {
			Station cur = que.poll();
			while (visit[cur.no])
				cur = que.poll();

			if (cur.no == M) {
				System.out.println(cur.cnt + " " + cur.cost);
				break;
			}
			visit[cur.no] = true;

			for (int i = 0; i < N; i++) {
				if (visit[i] || adj[cur.no][i] == 0)
					continue;
				que.offer(new Station(i, cur.cost + adj[cur.no][i], corp[cur.no] == corp[i] ? cur.cnt : cur.cnt + 1));
			}
		}
	}

	static class Station implements Comparable<Station> {
		int no, cost, cnt;

		public Station(int no, int cost, int cnt) {
			super();
			this.no = no;
			this.cost = cost;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Station o) {
			if (this.cnt == o.cnt)
				return this.cost - o.cost;
			return this.cnt - o.cnt;
		}

	}
}
