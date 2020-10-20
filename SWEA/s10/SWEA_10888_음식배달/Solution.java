package s10.SWEA_10888_음식배달;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, map[][];
	static int totres, answer;
	static boolean select[], visit[][];
	static List<int[]> res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			res = new ArrayList<>();
			totres = 0;
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1) {
						res.add(new int[] { i, j });
						totres++;
					}
				}
			}

			select = new boolean[totres];

			subset(0);

			System.out.println("#" + tc + " " + answer);

		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	private static void subset(int cnt) {
		if (cnt == totres) {
			Queue<int[]> que = new LinkedList<>();
			int sum = 0;
			visit = new boolean[N][N];
			for (int i = 0; i < totres; i++) {
				if (select[i]) {
					int[] tmp = res.get(i);
					que.add(tmp);
					sum += map[tmp[0]][tmp[1]];
					visit[tmp[0]][tmp[1]] = true;
				}
			}

			if (que.isEmpty())
				return;
			

			if (sum > answer)
				return;

			int step = 0;

			while (!que.isEmpty()) {
				step++;
				int size = que.size();
				for (int i = 0; i < size; i++) {
					int r = que.peek()[0];
					int c = que.poll()[1];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (!check(nr, nc) || visit[nr][nc])
							continue;

						if (map[nr][nc] == 1)
							sum += step;

						visit[nr][nc] = true;
						que.add(new int[] { nr, nc });
					}
				}
			}
			System.out.println(sum);

			answer = Math.min(answer, sum);
			return;
		}

		select[cnt] = true;
		subset(cnt + 1);
		select[cnt] = false;
		subset(cnt + 1);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
