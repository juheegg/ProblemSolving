package b17.BOJ_17142_연구소3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer, map[][], select[];
	static List<Node> list;
	static final int WALL = -1, VIRUS = -2, EMPTY = 0, INF = Integer.MAX_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		select = new int[M];
		answer = INF;
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
				if (map[i][j] == VIRUS)
					list.add(new Node(i, j));
			}
		}

		comb(0, 0);
		System.out.println(answer == INF ? -1 : answer);
	}

	private static void comb(int depth, int start) {
		if (depth == M) {
			Queue<Node> que = new LinkedList<>();
			boolean[][] visit = new boolean[N][N];
			int[][] copied = copy();

			for (int i = 0; i < M; i++) {
				Node cur = list.get(select[i]);
				que.add(cur);
				visit[cur.r][cur.c] = true;
			}

			int time = 0;

			while (!que.isEmpty()) {
				int size = que.size();
				time++;
				for (int s = 0; s < size; s++) {
					int r = que.peek().r;
					int c = que.poll().c;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!check(nr, nc) || copied[nr][nc] == WALL || copied[nr][nc] > EMPTY || visit[nr][nc])
							continue;
						if (copied[nr][nc] == EMPTY)
							copied[nr][nc] = time;
						visit[nr][nc] = true;
						que.add(new Node(nr, nc));
					}
				}
				answer = Math.min(answer, getMaxTime(copied));
			}
			return;
		}
		for (int i = start; i < list.size(); i++) {
			select[depth] = i;
			comb(depth + 1, i + 1);
		}

	}

	private static int getMaxTime(int[][] copied) {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copied[i][j] == EMPTY)
					return INF;
				result = Math.max(result, copied[i][j]);
			}
		}
		return result;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	private static int[][] copy() {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, result[i], 0, N);
		}
		return result;
	}

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
