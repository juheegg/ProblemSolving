package b03.BOJ_3055_탈출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char map[][];
	static int visit[][];
	static Queue<int[]> s, water;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		init();
		int answer = 0;
		if ((answer = bfs()) > 0)
			System.out.println(answer);
		else
			System.out.println("KAKTUS");
	}

	private static int bfs() {
		while (!s.isEmpty()) {

			int size = water.size();
			for (int i = 0; i < size; i++) {
				int r = water.peek()[0];
				int c = water.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || map[nr][nc] == 'D' || map[nr][nc] == 'X' || map[nr][nc] == '*')
						continue;

					map[nr][nc] = '*';
					water.offer(new int[] { nr, nc });
				}
			}
			print();
			size = s.size();
			for (int i = 0; i < size; i++) {
				int r = s.peek()[0];
				int c = s.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc))
						continue;

					if (map[nr][nc] == 'D')
						return visit[r][c];

					if (visit[nr][nc] > 0 || map[nr][nc] != '.')
						continue;

					map[nr][nc] = 'S';
					visit[nr][nc] = visit[r][c] + 1;
					s.offer(new int[] { nr, nc });
				}
			}
			print();

		}
		return 0;
	}

	private static void print() {
		for (char[] m : map) {
			for (char c : m)
				System.out.print(c);
			System.out.println();
		}
		System.out.println();
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < R && c < C)
			return true;
		return false;
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new int[R][C];
		s = new LinkedList<>();
		water = new LinkedList<>();

		String line = null;
		for (int i = 0; i < R; i++) {
			line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					visit[i][j] = 1;
					s.offer(new int[] { i, j });
				} else if (map[i][j] == '*') {
					water.offer(new int[] { i, j });
				}
			}
		}
	}
}
