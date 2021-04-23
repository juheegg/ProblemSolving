package b20.BOJ_20058_마법사상어와파이어스톰;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	static int totLen, map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		totLen = (int) Math.pow(2, N);
		map = new int[totLen][totLen];
		int[] L = new int[Q];

		for (int i = 0; i < totLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < totLen; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < Q; i++) {
			int len = (int) Math.pow(2, L[i]);
			int[][] tmp = new int[totLen][totLen];
			for (int r = 0; r < totLen / len; r++) {
				for (int c = 0; c < totLen / len; c++) {
					rotate(tmp, r * len, c * len, len);
				}
			}
			map = tmp;
			melt();
		}

		System.out.println(getIceCnt() + " \n" + getMaxSize());
	}

	private static void print() {
		for (int[] is : map) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
		System.out.println();
	}

	private static int getMaxSize() {
		boolean[][] visit = new boolean[totLen][totLen];
		int max = 0;
		for (int i = 0; i < totLen; i++) {
			for (int j = 0; j < totLen; j++) {
				if (visit[i][j] || map[i][j] == 0)
					continue;
				int cnt = bfs(i, j, visit);
				max = Math.max(max, cnt);
			}
		}
		return max;
	}

	private static int bfs(int r, int c, boolean[][] visit) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		visit[r][c] = true;
		int cnt = 0;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				cnt++;
				r = que.peek()[0];
				c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || visit[nr][nc])
						continue;
					if (map[nr][nc] == 0)
						continue;
					que.add(new int[] { nr, nc });
					visit[nr][nc] = true;
				}
			}
		}

		return cnt;
	}

	private static int getIceCnt() {
		int result = 0;
		for (int i = 0; i < totLen; i++) {
			for (int j = 0; j < totLen; j++) {
				result += map[i][j];
			}
		}
		return result;
	}

	private static void melt() {
		int[][] dest = new int[totLen][totLen];
		for (int i = 0; i < totLen; i++) {
			for (int j = 0; j < totLen; j++) {
				dest[i][j] = map[i][j];
				if (map[i][j] == 0)
					continue;
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (!check(nr, nc))
						continue;
					if (map[nr][nc] > 0)
						cnt++;
				}
				if (cnt < 3)
					dest[i][j]--;

			}
		}
		map = dest;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < totLen && c >= 0 && c < totLen)
			return true;
		return false;
	}

	private static void rotate(int[][] tmp, int r, int c, int len) {
//		System.out.println(r + " " + c + " " + len);
		int dr = r;
		for (int sc = c; sc < c + len; sc++) {
			int dc = c;
			for (int sr = r + len - 1; sr >= r; sr--) {
				tmp[dr][dc++] = map[sr][sc];
			}
			dr++;
		}
	}

}
