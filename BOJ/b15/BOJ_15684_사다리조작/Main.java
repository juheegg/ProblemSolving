package b15.BOJ_15684_사다리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, H, answer;
	static boolean ladder[][];
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		answer = INF;
		ladder = new boolean[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
		}

		if (getOddCnt() <= 3)
			dfs(0);

		System.out.println(answer == INF ? -1 : answer);
	}

	private static int getOddCnt() {
		int result = 0;
		for (int c = 1; c < N; c++) {
			int cnt = 0;
			for (int r = 1; r <= H; r++) {
				if (ladder[r][c])
					cnt++;
			}
			if (cnt % 2 == 1)
				result++;
		}
		return result;
	}

	private static void dfs(int depth) {
		if (answer <= depth || depth > 3)
			return;
		if (check()) {
			answer = Math.min(answer, depth);
			return;
		}

		for (int r = 1; r <= H; r++) {
			for (int c = 1; c < N; c++) {
				if (!canPut(r, c) || ladder[r][c])
					continue;
				ladder[r][c] = true;
				dfs(depth + 1);
				ladder[r][c] = false;
			}
		}
	}

	private static boolean canPut(int r, int c) {
		if (c != 1 && ladder[r][c - 1])
			return false;
		if (c != N && ladder[r][c + 1])
			return false;
		return true;
	}

	private static boolean check() {
		int col = 0;
		for (int c = 1; c <= N; c++) {
			col = c;
			for (int r = 1; r <= H; r++) {
				if (col != 1 && ladder[r][col - 1])
					col--;
				else if (ladder[r][col])
					col++;
			}
			if (col != c)
				return false;
		}
		return true;
	}

}
