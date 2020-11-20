package b07.BOJ_7576_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, box[][];
	static int check[][];
	static Queue<int[]> tomato;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		check = new int[N][M];
		tomato = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					tomato.offer(new int[] { i, j });
					box[i][j] = -1;
				}
			}
		}

		int day = 0;
		while (!tomato.isEmpty()) {
			int size = tomato.size();
			day++;
			for (int s = 0; s < size; s++) {
				int r = tomato.peek()[0];
				int c = tomato.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || box[nr][nc] != 0)
						continue;

					box[nr][nc] = day;
					tomato.offer(new int[] { nr, nc });
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				answer = Math.max(answer, box[i][j]);
			}
		}

		System.out.println(answer == -1 ? 0 : answer);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}
}
