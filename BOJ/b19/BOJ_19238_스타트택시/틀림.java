package b19.BOJ_19238_스타트택시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 틀림 {

	static int N, M, K, targetMap[][], dest[][], tr, tc;
	static final int WALL = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		targetMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				targetMap[i][j] = tmp == 0 ? 0 : WALL;
			}
		}

		st = new StringTokenizer(br.readLine());
		tr = Integer.parseInt(st.nextToken()) - 1;
		tc = Integer.parseInt(st.nextToken()) - 1;

		dest = new int[M + 1][];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int targetr = Integer.parseInt(st.nextToken()) - 1;
			int targetc = Integer.parseInt(st.nextToken()) - 1;
			int destr = Integer.parseInt(st.nextToken()) - 1;
			int destc = Integer.parseInt(st.nextToken()) - 1;
			targetMap[targetr][targetc] = i;
			dest[i] = new int[] { destr, destc };
		}

		boolean flag = true;
		for (int i = 0; i < M; i++) {
			int target = findTarget();
			if (target == -1) {
				flag = false;
				break;
			}
			int fuel = goDest(target);
			if (fuel == -1) {
				flag = false;
				break;
			}
			K += 2 * fuel;
		}

		System.out.println(flag ? K : -1);
	}

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	private static int findTarget() {
		Queue<Node> que = new PriorityQueue<>();
		que.add(new Node(tr, tc));
		boolean[][] visit = new boolean[N][N];
		visit[tr][tc] = true;

		if (targetMap[tr][tc] >= 1 && targetMap[tr][tc] <= M) {
			int target = targetMap[tr][tc];
			targetMap[tr][tc] = 0;
			return target;
		}

		while (!que.isEmpty()) {
			int size = que.size();

			if (--K < 0)
				return -1;
			Queue<Node> tmp = new PriorityQueue<>();
			for (int s = 0; s < size; s++) {
				int r = que.peek().r;
				int c = que.poll().c;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || visit[nr][nc] || targetMap[nr][nc] == WALL)
						continue;

					visit[nr][nc] = true;
					tmp.add(new Node(nr, nc));

					if (targetMap[nr][nc] < 1 || targetMap[nr][nc] > M)
						continue;

					tr = nr;
					tc = nc;
					int target = targetMap[nr][nc];
					targetMap[nr][nc] = 0;
					return target;
				}
			}
			que.addAll(tmp);
		}

		return -1;
	}

	private static int goDest(int target) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { tr, tc });
		boolean[][] visit = new boolean[N][N];
		visit[tr][tc] = true;
		int initK = K;

		if (tr == dest[target][0] && tc == dest[target][1]) {
			return initK - K;
		}

		while (!que.isEmpty()) {
			int size = que.size();

			if (--K < 0)
				return -1;

			for (int s = 0; s < size; s++) {
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || visit[nr][nc] || targetMap[nr][nc] == WALL)
						continue;

					visit[nr][nc] = true;
					que.add(new int[] { nr, nc });

					if (nr == dest[target][0] && nc == dest[target][1]) {
						tr = nr;
						tc = nc;
						return initK - K;
					}

				}
			}
		}

		return -1;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	static class Node implements Comparable<Node> {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			if (this.r == o.r)
				return this.c - o.c;
			return this.r - o.r;
		}

	}

}