package b14.BOJ_14500_테트로미노;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* dfs */

public class Main {

	static int N, M, map[][];
	static int answer;
	static boolean visit[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		answer = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visit[r][c] = true;
				dfs(1, map[r][c], r, c, true);
				dfs(1, map[r][c], r, c, false);
				visit[r][c] = false;
			}
		}

		System.out.println(answer);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void dfs(int depth, int sum, int r, int c, boolean flag) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc) || visit[nr][nc])
				continue;
			visit[nr][nc] = true;
			if (flag)
				dfs(depth + 1, sum + map[nr][nc], nr, nc, flag);
			else
				dfs(depth + 1, sum + map[nr][nc], r, c, flag);
			visit[nr][nc] = false;
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
