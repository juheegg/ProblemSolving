package KakaoIntern2020.P67258_경주로건설;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static public int solution(int[][] board) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int N = board.length;

		Queue<Node> que = new LinkedList<>();
		int[][] min = new int[N][N];
		que.offer(new Node(0, 0, -1, 0));

		for (int i = 0; i < N; i++) {
			Arrays.fill(min[i], Integer.MAX_VALUE);
		}

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Node cur = que.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					int cost = cur.cost + 100;
					if (cur.d != -1 && cur.d / 2 != d / 2)
						cost += 500;

					if (!check(nr, nc, N) || board[nr][nc] == 1)
						continue;

					if (min[nr][nc] < cost)
						continue;

					que.offer(new Node(nr, nc, d, cost));
					min[nr][nc] = cost;
				}
			}
		}

		return min[N - 1][N - 1];
	}

	static boolean check(int r, int c, int N) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	static class Node implements Comparable<Node> {
		int r, c, d, cost;

		public Node(int r, int c, int d, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) {
		int[][] b = { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } };
		System.out.println(solution(b));
	}

}
