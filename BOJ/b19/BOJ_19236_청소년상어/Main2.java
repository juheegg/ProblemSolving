package b19.BOJ_19236_청소년상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	static int answer;
	static final int SHARK = -1, EMPTY = 0, N = 4;
	static int[] dr = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		Fish[][] map = new Fish[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int no = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[i][j] = new Fish(no, d);
			}
		}

		answer = 0;

		eat(0, 0, 0, map);

		System.out.println(answer);
	}

	private static void eat(int sum, int r, int c, Fish[][] map) {
		sum += map[r][c].no;
		map[r][c].no = -1;
		int sharkD = map[r][c].d;

		fishMove(map);

		boolean sharkCanMove = false;
		for (int move = 1; move <= 4; move++) {
			int nr = r + dr[sharkD] * move;
			int nc = c + dc[sharkD] * move;
			if (!check(nr, nc) || map[nr][nc].no == EMPTY)
				continue;
			sharkCanMove = true;
			map[r][c] = new Fish();
			eat(sum, nr, nc, copy(map));
		}

		if (!sharkCanMove)
			answer = Math.max(answer, sum);
	}

	private static Fish[][] copy(Fish[][] map) {
		Fish[][] newMap = new Fish[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = new Fish(map[i][j]);
			}
		}
		return newMap;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	private static void fishMove(Fish[][] map) {
		Node[] arr = new Node[17];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].no < EMPTY)
					continue;
				arr[map[i][j].no] = new Node(i, j, map[i][j]);
			}
		}
		for (int i = 1; i < arr.length; i++) {
			Node cur = arr[i];

			if (cur == null)
				continue;

			int nr = 0;
			int nc = 0;
			for (int d = 1; d <= 8; d++) {
				nr = cur.r + dr[cur.fish.d];
				nc = cur.c + dc[cur.fish.d];
				if (check(nr, nc) && map[nr][nc].no >= EMPTY)
					break;
				cur.fish.d++;
				cur.fish.d = cur.fish.d == 9 ? 1 : cur.fish.d;
			}

			if (!check(nr, nc) || map[nr][nc].no < EMPTY)
				continue;

			if (map[nr][nc].no == EMPTY) {
				Fish src = new Fish(cur.fish);
				Fish dest = new Fish();
				map[nr][nc] = src;
				map[cur.r][cur.c] = dest;
				arr[src.no] = new Node(nr, nc, src);
			} else {
				Node target = arr[map[nr][nc].no];
				Fish src = new Fish(cur.fish);
				Fish dest = new Fish(target.fish);
				map[nr][nc] = src;
				map[cur.r][cur.c] = dest;

				arr[src.no] = new Node(nr, nc, src);
				arr[dest.no] = new Node(cur.r, cur.c, dest);
			}

		}
	}

	static class Shark {
		int r, c;

		public Shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + "]";
		}

	}

	static class Fish {
		int no, d;

		public Fish() {
			super();
		}

		public Fish(int no, int d) {
			super();
			this.no = no;
			this.d = d;
		}

		public Fish(Fish fish) {
			this.no = fish.no;
			this.d = fish.d;
		}

		@Override
		public String toString() {
			return "Fish [no=" + no + ", d=" + d + "]";
		}

	}

	static class Node implements Comparable<Node> {
		int r, c;
		Fish fish;

		public Node(int r, int c, Fish fish) {
			super();
			this.r = r;
			this.c = c;
			this.fish = fish;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", " + fish + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.fish.no - o.fish.no;
		}

	}
}
