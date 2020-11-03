package s05.SWEA_5644_무선충전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T, M, A, a[], b[];
	static int P[];
	static boolean map[][][];

	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			a = new int[M + 1];
			b = new int[M + 1];
			P = new int[A];
			map = new boolean[A][11][11];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				makeMap(i, r, c, size);
				P[i] = p;
			}

			for (boolean[][] maap : map) {
				for (boolean[] m : maap) {
					for (boolean b : m) {
						if (b)
							System.out.print("O ");
						else
							System.out.print("X ");
					}
					System.out.println();
				}
				System.out.println("------------------------");
				System.out.println();
			}

			int[] curA = { 1, 1 };
			int[] curB = { 10, 10 };
			List<Integer> alist = new ArrayList<>();
			List<Integer> blist = new ArrayList<>();

			int tot = 0;
			for (int i = 0; i <= M; i++) {
				curA[0] = curA[0] + dr[a[i]];
				curA[1] = curA[1] + dc[a[i]];
				curB[0] = curB[0] + dr[b[i]];
				curB[1] = curB[1] + dc[b[i]];

				alist.clear();
				blist.clear();
				for (int ap = 0; ap < A; ap++) {
					if (map[ap][curA[0]][curA[1]])
						alist.add(ap);
					if (map[ap][curB[0]][curB[1]])
						blist.add(ap);
				}
				tot += getMax(alist, blist);
				System.out.println(Arrays.toString(curA) + "\t" + Arrays.toString(curB) + "\t" + tot);
			}

			answer.append("#" + tc + " " + tot + "\n");
		}
		System.out.println(answer);
	}

	private static int getMax(List<Integer> alist, List<Integer> blist) {
		int max = 0;
		int asize = alist.size();
		int bsize = blist.size();
		System.out.println(alist);
		System.out.println(blist);
		if (asize != 0 && bsize != 0) {
			for (int i = 0; i < asize; i++) {
				for (int j = 0; j < bsize; j++) {
					if (alist.get(i) == blist.get(j)) {
						max = Math.max(max, P[alist.get(i)]);
					} else {
						max = Math.max(max, P[alist.get(i)] + P[blist.get(j)]);
					}
				}
			}
		} else if (bsize != 0) {
			for (int i = 0; i < bsize; i++) {
				max = Math.max(max, P[blist.get(i)]);
			}
		} else if (asize != 0) {
			for (int i = 0; i < asize; i++) {
				max = Math.max(max, P[alist.get(i)]);
			}
		}
		System.out.println(max);

		return max;
	}

	private static void makeMap(int i, int r, int c, int depth) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		map[i][r][c] = true;

		int cnt = 1;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				r = que.peek()[0];
				c = que.poll()[1];

				for (int d = 1; d <= 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || map[i][nr][nc])
						continue;

					map[i][nr][nc] = true;
					que.add(new int[] { nr, nc });

				}
			}
			if (++cnt > depth)
				return;
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 1 && r <= 10 && c >= 1 && c <= 10)
			return true;
		return false;
	}

}
