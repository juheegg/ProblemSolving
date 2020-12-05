package b20.BOJ_20165_인내의도미노장인호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R, map[][];
	static boolean domino[][];

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		domino = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(domino[i], true);
		}

		int answer = 0;

		for (int t = 0; t < R *2; t++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			if (t % 2 == 1) {
				domino[r][c] = true;
			} else {
				char tmp = st.nextToken().trim().charAt(0);
				int d = 0;
				switch (tmp) {
				case 'W':
					d = 1;
					break;
				case 'S':
					d = 2;
					break;
				case 'N':
					d = 3;
					break;
				}

				if (!domino[r][c])
					continue;

				int leng = map[r][c];
				for (int i = 0; i < leng; i++) {
					int nr = r + dr[d] * i;
					int nc = c + dc[d] * i;
					if (!check(nr, nc))
						break;
					if(!domino[nr][nc])
						continue;
					answer++;
					domino[nr][nc] = false;
					leng = Math.max(leng, i + map[nr][nc]);
				}
			}
		}

		System.out.println(answer);
		StringBuilder sb = new StringBuilder();
		for (boolean[] d:domino) {
			for (boolean b : d) {
				if (b)
					sb.append("S ");
				else
					sb.append("F ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
