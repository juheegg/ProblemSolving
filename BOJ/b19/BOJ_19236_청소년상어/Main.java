package b19.BOJ_19236_청소년상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int answer;
	static final int N = 4;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int[][] map = new int[N][N];
		Fish[] fishes = new Fish[N * N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = no;
				fishes[no] = new Fish(dir - 1, i, j);
			}
		}

		answer = 0;
		dfs(map, fishes, 0, 0, 0);
		System.out.println(answer);
	}

	private static void dfs(int[][] map, Fish[] fishes, int sharkR, int sharkC, int tot) {
		tot += map[sharkR][sharkC];
		int d = fishes[map[sharkR][sharkC]].dir;
		fishes[map[sharkR][sharkC]] = null;
		map[sharkR][sharkC] = -1;

		move(map, fishes);
		map[sharkR][sharkC] = 0;

		int canMoveCnt = 0;
		for (int i = 1; i <= N; i++) {
			int r = sharkR + dr[d] * i;
			int c = sharkC + dc[d] * i;
			if (!check(r, c))
				break;
			if (map[r][c] == 0)
				continue;
			canMoveCnt++;
			dfs(copy(map), copy(fishes), r, c, tot);
		}

		if (canMoveCnt == 0)
			answer = Math.max(answer, tot);
	}

	private static Fish[] copy(Fish[] fishes) {
		Fish[] copied = new Fish[17];
		for (int i = 1; i < 17; i++) {
			if (fishes[i] == null)
				continue;
			copied[i] = new Fish(fishes[i].dir, fishes[i].r, fishes[i].c);
		}
		return copied;
	}

	private static int[][] copy(int[][] map) {
		int[][] copied = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, copied[i], 0, N);
		}
		return copied;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	private static void move(int[][] map, Fish[] fishes) {
		for (int i = 1; i <= 16; i++) {
			Fish cur = fishes[i];
			if (cur == null)
				continue;

			int d = cur.dir;
			int nr = cur.r + dr[d];
			int nc = cur.c + dc[d];
			boolean flag = false;
			for (int step = 0; step < 8; step++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if (check(nr, nc) && map[nr][nc] >= 0) {
					flag = true;
					break;
				}
				d = (d + 1) % 8;
			}
			if (!flag)
				continue;
			int tmpNo = map[nr][nc];
			map[nr][nc] = i;
			map[cur.r][cur.c] = tmpNo;
			if (fishes[tmpNo] != null)
				fishes[tmpNo].setPosition(cur.r, cur.c);
			cur.setPosition(nr, nc);
			cur.dir = d;
		}
	}

	static class Fish {
		int dir, r, c;

		public Fish(int dir, int r, int c) {
			super();
			this.dir = dir;
			this.r = r;
			this.c = c;
		}

		public void setPosition(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
