package b20.BOJ_20057_마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] tr = { { -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 좌
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, // 하
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 우
			{ 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 } };// 상
	static int[][] tc = { { 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 }, // 좌
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 하
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, // 우
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 } };// 상
	static int[] per = { 1, 1, 2, 2, 7, 7, 10, 10, 5, -1 };

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	static int N, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int answer = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				answer += map[i][j];
			}
		}

		int r = N / 2;
		int c = N / 2;
		int d = 0;

		for (int step = 1; step < N; step++) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < step; j++) {
					r += dr[d];
					c += dc[d];
					move(r, c, d);
				}
				d = (d + 1) % 4;
			}
		}

		for (int i = 1; i < N; i++) {
			r += dr[d];
			c += dc[d];
			move(r, c, d);
		}

		for (int[] m : map) {
			for (int i : m)
				answer -= i;
		}

		System.out.println(answer);

	}

	private static void move(int r, int c, int d) {
		int sand = map[r][c];
		int totSand = sand;
		for (int t = 0; t < 9; t++) {
			int nr = r + tr[d][t];
			int nc = c + tc[d][t];

			int moveSand = sand * per[t] / 100;
			if (check(nr, nc))
				map[nr][nc] += moveSand;
			totSand -= moveSand;
		}
		map[r][c] = 0;
		int nr = r + tr[d][9];
		int nc = c + tc[d][9];
		if (check(nr, nc))
			map[nr][nc] += totSand;

	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
