package b19.BOJ_19238_스타트택시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, map[][];
	static Node taxi, dest[];
	static final int wall = -1;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		taxi = new Node(r - 1, c - 1);

		dest = new Node[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = i;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dest[i] = new Node(r - 1, c - 1);
		}

		for (int i = 1; i <= M; i++) {
			int no = findCustomer();
			if (no < 1)
				K = -1;
			if (K < 0)
				break;

			int dist = getDist(dest[no]);
			if (dist < 0) {
				K = -1;
				break;
			}
			if ((K -= dist) < 0)
				break;
			K += 2 * dist;
		}

		System.out.println(K < 0 ? -1 : K);
	}

	private static int findCustomer() {
		Queue<Node> que = new LinkedList<>();
		Queue<Node> customerQue = new PriorityQueue<>();
		boolean[][] visit = new boolean[N][N];

		que.add(taxi);
		visit[taxi.r][taxi.c] = true;
		int dist = 0;
		int result = 0;

		if (map[taxi.r][taxi.c] > 0) {
			result = map[taxi.r][taxi.c];
			map[taxi.r][taxi.c] = 0;
			return result;
		}

		while (!que.isEmpty()) {
			int size = que.size();
			dist++;
			for (int i = 0; i < size; i++) {
				int r = que.peek().r;
				int c = que.poll().c;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || visit[nr][nc] || map[nr][nc] == wall)
						continue;
					visit[nr][nc] = true;
					if (map[nr][nc] > 0)
						customerQue.offer(new Node(nr, nc));
					que.offer(new Node(nr, nc));
				}
			}
			if (customerQue.size() > 0) {
				taxi = customerQue.poll();
				result = map[taxi.r][taxi.c];
				map[taxi.r][taxi.c] = 0;
				K -= dist;
				return result;
			}
		}
		return result;
	}

	private static int getDist(Node target) {
		Queue<Node> que = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];

		que.add(taxi);
		visit[taxi.r][taxi.c] = true;
		int dist = -1;

		while (!que.isEmpty()) {
			int size = que.size();
			dist++;
			for (int i = 0; i < size; i++) {
				int r = que.peek().r;
				int c = que.poll().c;
				if (r == target.r && c == target.c) {
					taxi = target;
					return dist;
				}
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || visit[nr][nc] || map[nr][nc] == wall)
						continue;
					visit[nr][nc] = true;
					que.offer(new Node(nr, nc));
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
