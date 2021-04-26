package b17.BOJ_17143_낚시왕;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, answer;
	static Shark[][] s;

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static int[] change = { 0, 2, 1, 4, 3 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		s = new Shark[R][C];
		answer = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			speed = d <= 2 ? speed % (2 * R - 2) : speed % (2 * C - 2);
			s[r][c] = new Shark(speed, d, z);

		}

		for (int c = 0; c < C; c++) {
			removeShark(c);
			moveShark();
		}

		System.out.println(answer);
	}

	private static void moveShark() {
		Shark[][] tmp = new Shark[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (s[i][j] == null)
					continue;
				Shark cur = s[i][j];
				int r = i;
				int c = j;
				for (int k = 0; k < cur.s; k++) {
					r += dr[cur.d];
					c += dc[cur.d];
					if (!check(r, c)) {
						cur.d = change[cur.d];
						r += dr[cur.d] * 2;
						c += dc[cur.d] * 2;
					}
				}
				if (tmp[r][c] == null || tmp[r][c].z < cur.z)
					tmp[r][c] = cur;
			}
		}
		s = tmp;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C)
			return true;
		return false;
	}

	private static void removeShark(int c) {
		for (int r = 0; r < R; r++) {
			if (s[r][c] == null)
				continue;
			answer += s[r][c].z;
			s[r][c] = null;
			return;
		}
	}

	static class Shark {
		int s, d, z;

		public Shark(int s, int d, int z) {
			super();
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [s=" + s + ", d=" + d + ", z=" + z + "]";
		}

	}
}
