package b20.BOJ_20057_마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	static int N, result, map[][];
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int[] percent = { 1, 1, 2, 2, 7, 7, 10, 10, 5 };
	static int[][] sr = { { -1, 1, -2, 2, -1, 1, -1, 1, 0 }, { -1, -1, 0, 0, 0, 0, 1, 1, 2 },
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0 }, { 1, 1, 0, 0, 0, 0, -1, -1, -2 } };
	static int[][] sc = { { 1, 1, 0, 0, 0, 0, -1, -1, -2 }, { -1, 1, -2, 2, -1, 1, -1, 1, 0 },
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2 }, { -1, 1, -2, 2, -1, 1, -1, 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int i = 0; i < 4; i++) {
//			int[][] tmp = new int[5][5];
//			for (int j = 0; j < 9; j++) {
//				tmp[2 + sr[i][j]][2 + sc[i][j]] = percent[j];
//			}
//

//		}

		int moveCnt = 0;
		int r = N / 2;
		int c = N / 2;

		for (int step = 0; step < N / 2; step++) {
			for (int d = 0; d < 4; d++) {
				if (d % 2 == 0)
					moveCnt++;
				for (int m = 0; m < moveCnt; m++) {
					r += dr[d];
					c += dc[d];
					move(r, c, d);
//					System.out.println();
				}
			}
		}

		while (c > 0) {
			c--;
			move(r, c, 0);
//			System.out.println();
		}
//		for (int r1 = 0; r1 < 5; r1++) {
//			for (int c1 = 0; c1 < 5; c1++) {
//				System.out.print(map[r1][c1] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
		System.out.println(result);
	}

	private static void move(int r, int c, int d) {
		int originSand = map[r][c];
		int totMove = 0;
		map[r][c] = 0;
		for (int s = 0; s < 9; s++) {
			int nr = r + sr[d][s];
			int nc = c + sc[d][s];
			int moveSand = (int) (originSand * (0.01 * percent[s]));
			totMove += moveSand;
			if (!check(nr, nc)) {
				result += moveSand;
				continue;
			}
			map[nr][nc] += moveSand;
		}
		r += dr[d];
		c += dc[d];
		if (!check(r, c)) {
			result += originSand - totMove;
			return;
		}
		map[r][c] += originSand - totMove;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

}
