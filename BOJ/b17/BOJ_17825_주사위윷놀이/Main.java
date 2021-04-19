package b17.BOJ_17825_주사위윷놀이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer, dice[];
	static boolean[] visit;
	static final int stage = 10;
	static final int size = 32;
	static int[] score = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 13, 16, 19, 22,
			24, 28, 27, 26, 25, 30, 35, 40 };
	static int[] next = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 21, 22, 28, 24, 28, 26,
			27, 28, 29, 30, 31, 32 };
	static int[] first = { 1, 2, 3, 4, 5, 20, 7, 8, 9, 10, 23, 12, 13, 14, 15, 25, 17, 18, 19, 31, 21, 22, 28, 24, 28,
			26, 27, 28, 29, 30, 31, 32 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = 0;
		dice = new int[stage];
		visit = new boolean[size];

		for (int i = 0; i < stage; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		int[] piece = { 0, 0, 0, 0 };
		dfs(0, 0, piece);
		System.out.println(answer);
	}

	private static void dfs(int depth, int sum, int[] piece) {
		if (depth == 10) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (piece[i] == size)
				continue;
			int cur = piece[i];
			for (int j = 0; j < dice[depth]; j++) {
				if (cur >= size)
					break;
				if (j == 0)
					cur = first[cur];
				else
					cur = next[cur];
			}
			if (cur >= size) {
				visit[piece[i]] = false;
				dfs(depth + 1, sum, copyPiece(piece, i, size));
				visit[piece[i]] = true;
				continue;
			}
			if (visit[cur])
				continue;
			visit[piece[i]] = false;
			visit[cur] = true;
			dfs(depth + 1, sum + score[cur], copyPiece(piece, i, cur));
			visit[cur] = false;
			visit[piece[i]] = true;
		}

	}

	private static int[] copyPiece(int[] piece, int i, int cur) {
		int[] copied = new int[4];
		System.arraycopy(piece, 0, copied, 0, 4);
		copied[i] = cur;
		return copied;
	}
}
