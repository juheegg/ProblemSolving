package b02.BOJ_2206_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int r;
		int c;
		int cnt;
		boolean wall;

		public Point() {
		}

		public Point(int r, int c, int cnt, boolean wall) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", cnt=" + cnt + ", wall=" + wall + "]";
		}
	}

	static int N, M;
	static boolean map[][], walltVisited[][], wallfVisited[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		walltVisited = new boolean[N][M];
		wallfVisited = new boolean[N][M];

		String line;
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < M; c++) {
				if (line.charAt(c) == '0')
					map[r][c] = true;
				else
					map[r][c] = false;
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Point> que = new LinkedList<>();
		walltVisited[0][0] = true;
		wallfVisited[0][0] = true;
		que.offer(new Point(0, 0, 1, false));
		while (!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Point curP = que.poll();
				if (curP.r == N - 1 && curP.c == M - 1)
					return curP.cnt;

				for (int d = 0; d < 4; d++) {
					int nr = curP.r + dr[d];
					int nc = curP.c + dc[d];
					if (!check(nr, nc))
						continue;

					if (curP.wall) {
						if (!map[nr][nc] || walltVisited[nr][nc])
							continue;
						walltVisited[nr][nc] = true;
						que.offer(new Point(nr, nc, curP.cnt + 1, true));

					} else {
						if (wallfVisited[nr][nc])
							continue;
						wallfVisited[nr][nc] = true;
						que.offer(new Point(nr, nc, curP.cnt + 1, map[nr][nc] ? false : true));
					}
				}
			}
		}
		return -1;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}
}
