package s01.SWEA_1868_파핑파핑지뢰찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int T, N;
	static char map[][];

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '.' || getMine(i, j) != 0)
						continue;
					cnt++;
					markNum(i, j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.')
						cnt++;
				}
			}

			answer.append("#" + tc + " " + cnt + "\n");
		}

		System.out.println(answer);
	}

	private static void markNum(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { r, c });
		map[r][c] = '0';

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				r = que.peek()[0];
				c = que.poll()[1];

				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || map[nr][nc] != '.')
						continue;

					switch (getMine(nr, nc)) {
					case 0:
						que.offer(new int[] { nr, nc });
						map[nr][nc] = '0';
						break;
					default:
						map[nr][nc] = 'X';
						break;
					}
				}
			}
		}
	}

	private static int getMine(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc))
				continue;

			if (map[nr][nc] == '*')
				cnt++;
		}
		return cnt;
	}

	static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}
}
