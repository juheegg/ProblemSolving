package s01.SWEA_1953_탈주범검거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, M, R, C, t, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			init(br);
			answer.append("#" + tc + " " + bfs() + "\n");
		}
		System.out.println(answer);
	}

	private static void init(BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] go = { { false, true, true, false, true, false, false, true },
			{ false, true, true, false, false, true, true, false },
			{ false, true, false, true, false, false, true, true },
			{ false, true, false, true, true, true, false, false } };
	static boolean[][] reach = { go[1], go[0], go[3], go[2] };

	private static int bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { R, C });

		int visit[][] = new int[N][M];
		visit[R][C] = 1;

		int time = 0;
		int tot = 0;

		while (!que.isEmpty()) {
			int size = que.size();
			tot += size;

			if (++time == t) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						System.out.print(visit[i][j] + " ");

					}
					System.out.println();
				}
				return tot;
			}

			for (int s = 0; s < size; s++) {
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || visit[nr][nc] > 0)
						continue;

					if (go[d][map[r][c]] && reach[d][map[nr][nc]]) {
						visit[nr][nc] = visit[r][c] + 1;
						que.offer(new int[] { nr, nc });
					}
				}
			}
		}

		return tot;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}

}
