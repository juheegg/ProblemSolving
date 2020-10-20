package b16.BOJ_16234_인구이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R, map[][];
	static int flag[][], cnt[], sum[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		cnt = new int[2001];
		sum = new int[2001];
		flag = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while (bfs()) {
			answer++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (flag[i][j] == 0)
						continue;
					map[i][j] = sum[flag[i][j]] / cnt[flag[i][j]];
				}
			}

		}

		System.out.println(answer);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static boolean bfs() {
		cnt = new int[2001];
		sum = new int[2001];
		flag = new int[N][N];

		Queue<int[]> que = new LinkedList<>();
		boolean visit[][] = new boolean[N][N];
		int step = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j])
					continue;
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

							if (!check(nr, nc) || visit[nr][nc])
								continue;

							if (Math.abs(map[r][c] - map[nr][nc]) > R || Math.abs(map[r][c] - map[nr][nc]) < L)
								continue;

							flag[nr][nc] = step;
							cnt[step]++;
							sum[step] += map[nr][nc];
							que.add(new int[] { nr, nc });
							visit[nr][nc] = true;
						}
					}
				}
				if (cnt[step] > 0) {
					flag[i][j] = step;
					cnt[step]++;
					sum[step] += map[i][j];
					step++;
				}
			}
		}

		return step > 1 ? true : false;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
