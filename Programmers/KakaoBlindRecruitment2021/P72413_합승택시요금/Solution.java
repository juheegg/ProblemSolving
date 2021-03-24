package KakaoBlindRecruitment2021.P72413_합승택시요금;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	static final int INF = Integer.MAX_VALUE;

	// 지금: 한점에서 S까지 + A까지 + B까지
	// => S부터 모든 거리, A부터 모든 거리 , B부터 모든 거리 다 구해서 합이 최소인 점 찾기
	// 더 빨라질듯
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = INF;

		List<Node>[] adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < fares.length; i++) {
			int src = fares[i][0];
			int dest = fares[i][1];
			int c = fares[i][2];
			adj[src].add(new Node(dest, c));
			adj[dest].add(new Node(src, c));
		}

		int[][] cost = new int[3][n + 1];
		dijkstra(cost[0], n, s, adj);
		dijkstra(cost[1], n, a, adj);
		dijkstra(cost[2], n, b, adj);

		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 0; j < 3; j++) {
				sum += cost[j][i];
			}
			answer = Math.min(answer, sum);
		}

		return answer;
	}

	static void dijkstra(int[] cost, int n, int start, List<Node>[] adj) {

		for (int i = 1; i <= n; i++) {
			cost[i] = INF;
		}

		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[n + 1];

		cost[start] = 0;
		pq.add(new Node(start, cost[start]));

		for (int step = 0; step < n; step++) {
			Node cur = pq.poll();

			while (cur != null && visit[cur.no])
				cur = pq.poll();

			if (cur == null)
				break;

			visit[cur.no] = true;

			for (int i = 0; i < adj[cur.no].size(); i++) {
				Node target = adj[cur.no].get(i);
				if (visit[target.no])
					continue;
				if (cost[target.no] > target.c + cur.c) {
					cost[target.no] = target.c + cur.c;
					pq.add(new Node(target.no, target.c + cur.c));
				}
			}

		}
	}

	static class Node implements Comparable<Node> {
		int no, c;

		public Node(int no, int c) {
			super();
			this.no = no;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}

	}

	public static void main(String[] args) {
//		int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
//				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
//		System.out.println(solution(6, 4, 6, 2, fares));
//		int[][] fares = new int[][] { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } };
//		System.out.println(solution(7,3,4,1, fares));
		int[][] fares = new int[][] { { 2, 6, 6 }, { 6, 3, 7 }, { 4, 6, 7 }, { 6, 5, 11 }, { 2, 5, 12 }, { 5, 3, 20 },
				{ 2, 4, 8 }, { 4, 3, 9 } };
		System.out.println(solution(6, 4, 5, 6, fares));
	}

}
