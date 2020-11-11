package b04.BOJ_4485_녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, map[][];
	static int min[][];
	static Queue<Point> que;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		int cnt = 1;
		while (N > 0) {
			map = new int[N][N];
			min = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					min[i][j] = Integer.MAX_VALUE;
				}
			}

			que = new PriorityQueue<>();
			que.offer(new Point(0, 0, map[0][0]));
			min[0][0] = map[0][0];

			while (!que.isEmpty()) {
				Point cur = que.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (!check(nr, nc) || cur.rupee + map[nr][nc] >= min[nr][nc])
						continue;

					que.offer(new Point(nr, nc, cur.rupee + map[nr][nc]));
					min[nr][nc] = cur.rupee + map[nr][nc];
				}
			}

			answer.append("Problem " + cnt++ + ": " + min[N - 1][N - 1] + "\n");

			N = Integer.parseInt(br.readLine());
		}

		System.out.println(answer);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	static class Point implements Comparable<Point> {
		int r, c, rupee;

		public Point(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", rupee=" + rupee + "]";
		}

		@Override
		public int compareTo(Point o) {
			return this.rupee - o.rupee;
		}

	}
}
