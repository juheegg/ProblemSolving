package b17.BOJ_17942_알고리즘공부;

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
		int[] study = new int[N + 1];
		boolean[] visit = new boolean[N + 1];
		Queue<Node> que = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			study[i] = Integer.parseInt(st.nextToken());
			que.add(new Node(i, study[i]));
		}

		int K = Integer.parseInt(br.readLine());
		List<Node>[] adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, c));
		}

		int answer = 0;
		for (int step = 0; step < M; step++) {
			Node cur = que.poll();
			while (visit[cur.no])
				cur = que.poll();

			visit[cur.no] = true;
			answer = Math.max(answer, cur.cost);

			for (int i = 0; i < adj[cur.no].size(); i++) {
				if (visit[adj[cur.no].get(i).no])
					continue;
				study[adj[cur.no].get(i).no] -= adj[cur.no].get(i).cost;
				que.offer(new Node(adj[cur.no].get(i).no, study[adj[cur.no].get(i).no]));

			}
		}

		System.out.println(answer);
	}

	static class Node implements Comparable<Node> {
		int no, cost;

		public Node(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

}
