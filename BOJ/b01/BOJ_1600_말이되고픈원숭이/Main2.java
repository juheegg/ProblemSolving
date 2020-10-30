package b01.BOJ_1600_말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	static int[] dr = { -1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1 };

	static class Monkey {
		int r, c, k;

		public Monkey(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static int K, W, H, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Monkey> que = new LinkedList<>();
		boolean visit[][][] = new boolean[H][W][K + 1];
		visit[0][0][0] = true;
		que.add(new Monkey(0, 0, 0));

		int answer = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Monkey cur = que.poll();
				
				if(cur.r == H-1 && cur.c == W-1) {
					System.out.println(answer);
					return;
				}
				
				int D = cur.k < K ? 12 : 4;
				for (int d = 0; d < D; d++) {
					int r = cur.r + dr[d];
					int c = cur.c + dc[d];
					int k = d > 3 ? cur.k + 1 : cur.k;

					if (!check(r, c) || visit[r][c][k] || map[r][c] == 1)
						continue;

					visit[r][c][k] = true;
					que.add(new Monkey(r, c, k));
				}
			}
			answer++;
		}
		System.out.println(-1);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < H && c < W)
			return true;
		return false;
	}
}
