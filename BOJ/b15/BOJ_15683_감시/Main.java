package b15.BOJ_15683_감시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer, selectDir[], map[][], copied[][];
	static List<CCTV> list;
	static final int WALL = 6, O = 9, X = 0;
	static int[][][] typeDirs = { {}, { { 1 }, { 2 }, { 3 }, { 0 } }, { { 2, 3 }, { 0, 1 } },
			{ { 0, 2 }, { 1, 2 }, { 1, 3 }, { 3, 0 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != X && map[i][j] != WALL)
					list.add(new CCTV(i, j, map[i][j]));
			}
		}
		selectDir = new int[list.size()];
		answer = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int depth) {
		if (depth == list.size()) {
			copied = copy();
			for (int i = 0; i < list.size(); i++) {
				draw(list.get(i), selectDir[i]);
			}
			answer = Math.min(answer, getCnt());
			return;
		}
		for (int i = 0; i < typeDirs[list.get(depth).type].length; i++) {
			selectDir[depth] = i;
			dfs(depth + 1);
		}
	}

	private static int getCnt() {
		int result = 0;
		for (int[] is : copied) {
			for (int i : is)
				if (i == X)
					result++;
		}
		return result;
	}

	private static void draw(CCTV cctv, int selected) {
		int[] dir = typeDirs[cctv.type][selected];
		for (int d = 0; d < dir.length; d++) {
			int r = cctv.r;
			int c = cctv.c;
			while (check(r, c) && copied[r][c] != WALL) {
				if (copied[r][c] == X)
					copied[r][c] = O;
				r += dr[dir[d]];
				c += dc[dir[d]];
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

	private static int[][] copy() {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, result[i], 0, M);
		}
		return result;
	}

	static class CCTV {
		int r, c, type;

		public CCTV(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

	}
}
