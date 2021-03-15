package s01.SWEA_1949_등산로조성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K, answer, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;

			map = new int[N][N];

			int max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) {
						boolean[][] visit = new boolean[N][N];
						visit[r][c] = true;
						dfs(r, c, 1, map[r][c], false, visit);
					}
				}
			}
			result.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(result);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void dfs(int r, int c, int depth, int height, boolean use, boolean[][] visit) {
		boolean isEnd = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc) || visit[nr][nc])
				continue;

			visit[nr][nc] = true;
			if (height > map[nr][nc]) {
				dfs(nr, nc, depth + 1, map[nr][nc], use, visit);
				isEnd = false;
			} else if (!use && map[nr][nc] - map[r][c] + 1 <= K) {
				dfs(nr, nc, depth + 1, map[r][c] - 1, true, visit);
				isEnd = false;
			}
			visit[nr][nc] = false;
		}

		if (isEnd) {
			answer = Math.max(answer, depth);
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

}
