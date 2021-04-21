package b19.BOJ_19237_어른상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 틀린거 {

	static int N, K;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][][] smell = new int[N][N][2];
		Shark[] sharks = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				smell[i][j][0] = Integer.parseInt(st.nextToken());
				if (smell[i][j][0] > 0) {
					smell[i][j][1] = K;
					sharks[smell[i][j][0]] = new Shark(i, j, smell[i][j][0]);
				}

			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}

		Queue<Shark> que = new LinkedList<>();
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					sharks[i].order[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			que.add(sharks[i]);
		}

		int time = 0;
		while (que.size() > 1) {
			if (++time > 1000)
				break;
			System.out.println(time);
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Shark cur = que.poll();
				int[] order = cur.order[cur.d];
				boolean flag = true;
				int nr = 0;
				int nc = 0;
				int nextD = 0;
				for (int d = 1; d <= 4; d++) {
					nr = cur.r + dr[order[d]];
					nc = cur.c + dc[order[d]];
					if (!check(nr, nc))
						continue;
					if (smell[nr][nc][0] > 0 && smell[nr][nc][0] < cur.no) {
						if (smell[nr][nc][1] == K + 1) {
							flag = false;
							break;
						}
						continue;
					}
					if (smell[nr][nc][0] == cur.no) {
						nextD = d;
						continue;
					}
					if (smell[nr][nc][0] > cur.no)
						continue;
					nextD = order[d];
					break;
				}
				if (!flag)
					continue;

				nr = cur.r + dr[nextD];
				nc = cur.c + dc[nextD];
				cur.r = nr;
				cur.c = nc;
				cur.d = nextD;
				que.offer(cur);

				smell[nr][nc][0] = cur.no;
				smell[nr][nc][1] = K + 1;
			}
			goTime(smell);
		}

		System.out.println(time > 1000 ? -1 : time);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	private static void goTime(int[][][] smell) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (smell[i][j][1] == 0)
					continue;
				if (--smell[i][j][1] == 0)
					smell[i][j][0] = 0;
			}
		}
	}

	static class Shark {
		int r, c, d, no, order[][];

		public Shark(int r, int c, int no) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
			order = new int[5][5];
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int[] is : order) {
				for (int i : is) {
					sb.append(i + " ");
				}
				sb.append("\n");
			}
			return "Shark [r=" + r + ", c=" + c + ", d=" + d + ", no=" + no + "]" + "\n" + sb;
		}

	}
}
