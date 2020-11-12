package b10.BOJ_10026_적록색약;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static char pic[][];
	static int rgb, rb;
	static boolean visit[][];

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pic = new char[N][];
		rgb = 0;
		rb = 0;

		String line;
		for (int i = 0; i < N; i++) {
			pic[i] = br.readLine().toCharArray();
		}

		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					bfs(i, j, false);
					rgb++;
				}
			}
		}

		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					bfs(i, j, true);
					rb++;
				}
			}
		}

		System.out.println(rgb + " " + rb);

	}

	private static void bfs(int r, int c, boolean flag) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { r, c });
		visit[r][c] = true;

		char cur = pic[r][c];

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				r = que.peek()[0];
				c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || visit[nr][nc])
						continue;

					if (cur == pic[nr][nc]
							|| (flag && ((cur == 'R' && pic[nr][nc] == 'G') || (cur == 'G' && pic[nr][nc] == 'R')))) {
						que.offer(new int[] { nr, nc });
						visit[nr][nc] = true;
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
