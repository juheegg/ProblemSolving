package b02.BOJ_2468_안전영역;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 안전 영역 */

public class Main {

	static int N, map[][];
	static int answer, cnt, visited[][], hmax;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer = Integer.MIN_VALUE;
		hmax = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				hmax = Math.max(hmax, map[i][j]);
			}
		}

		for (int height = 0; height < hmax; height++) {
			cnt = 0;
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > height && visited[i][j] == 0) {
						cnt++;
						visited[i][j] = 1;
						dfs(i, j, height);
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int height) {

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc) && map[nr][nc] > height && visited[nr][nc] == 0) {
				visited[nr][nc] = 1;
				dfs(nr, nc, height);
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}
}