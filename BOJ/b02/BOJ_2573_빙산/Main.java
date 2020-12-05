package b02.BOJ_2573_빙산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][];
	static int tot;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					tot++;
			}
		}

		int answer = 0;
		int ice = 0;
		while ((ice = getPiece()) == 1) {
			answer++;
			melt();
		}

		System.out.println(ice == 0 ? 0 : answer);
	}

	private static void melt() {
		int[][] tmp = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					continue;
				tmp[r][c] = map[r][c];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc))
						continue;
					if (map[nr][nc] == 0)
						tmp[r][c]--;
				}
				tmp[r][c] = tmp[r][c] < 0 ? 0 : tmp[r][c];
			}
		}
		map = tmp;
	}

	private static int getPiece() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || visit[i][j])
					continue;
				result++;
				que.clear();
				que.add(new int[] { i, j });
				visit[i][j] = true;

				while (!que.isEmpty()) {
					int size = que.size();
					for (int s = 0; s < size; s++) {
						int r = que.peek()[0];
						int c = que.poll()[1];
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];

							if (!check(nr, nc) || visit[nr][nc] || map[nr][nc] == 0)
								continue;
							que.add(new int[] { nr, nc });
							visit[nr][nc] = true;
						}
					}
				}
			}
		}

		return result;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
