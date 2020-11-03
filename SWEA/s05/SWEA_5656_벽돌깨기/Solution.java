package s05.SWEA_5656_벽돌깨기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, W, H, map[][];
	static int min, select[], tmp[][];
	static boolean visit[][];

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			select = new int[N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0);
			answer.append("#" + tc + " " + min + "\n");
		}
		System.out.println(answer);
	}

	private static void comb(int cnt) {
		if (cnt == N) {
			tmp = new int[H][W];
			for (int j = 0; j < H; j++) {
				System.arraycopy(map[j], 0, tmp[j], 0, W);
			}

			for (int i = 0; i < N; i++) {
				bomb(i);
				move();
			}

			int tot = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (tmp[i][j] > 0)
						tot++;
				}
			}
			min = Math.min(min, tot);
			return;
		}
		for (int i = 0; i < W; i++) {
			select[cnt] = i;
			comb(cnt + 1);
		}
	}

	private static void move() {
		int tmptmp[][] = new int[H][W];
		for (int c = 0; c < W; c++) {
			int tmpr = H - 1;
			for (int r = H - 1; r >= 0; r--) {
				if (!visit[r][c] && tmp[r][c] > 0)
					tmptmp[tmpr--][c] = tmp[r][c];
			}
		}
		tmp = tmptmp;
	}

	private static void bomb(int i) {
		visit = new boolean[H][W];
		Queue<int[]> que = new LinkedList<>();
		for (int r = 0; r < H; r++) {
			if (tmp[r][select[i]] > 0) {
				visit[r][select[i]] = true;
				que.offer(new int[] { r, select[i] });
				break;
			}
		}

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int brick = tmp[r][c];
					for (int depth = 1; depth < brick; depth++) {
						int nr = r + dr[d] * depth;
						int nc = c + dc[d] * depth;

						if (!check(nr, nc))
							break;
						if (visit[nr][nc])
							continue;

						visit[nr][nc] = true;
						if (tmp[nr][nc] > 1)
							que.offer(new int[] { nr, nc });
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < H && c < W)
			return true;
		return false;
	}

}
