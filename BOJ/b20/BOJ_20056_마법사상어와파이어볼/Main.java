package b20.BOJ_20056_마법사상어와파이어볼;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Queue<Ball>[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new LinkedList[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			checkNull(map, r, c);
			map[r][c].offer(new Ball(m, s, d));
		}

		for (int i = 0; i < K; i++) {
			move();
			divide();
		}

		System.out.println(getResult());
	}

	private static int getResult() {
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null || map[r][c].isEmpty())
					continue;
				Queue<Ball> que = map[r][c];
				while (!que.isEmpty()) {
					answer += que.poll().m;
				}
			}
		}
		return answer;
	}

	private static void checkNull(Queue<Ball>[][] que, int r, int c) {
		if (que[r][c] == null)
			que[r][c] = new LinkedList<>();
	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	private static void move() {
		Queue<Ball>[][] tmp = new LinkedList[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null || map[r][c].isEmpty())
					continue;

				Queue<Ball> que = map[r][c];
				while (!que.isEmpty()) {
					Ball ball = que.poll();
					int nr = rangeCheck(r + dr[ball.d] * ball.s);
					int nc = rangeCheck(c + dc[ball.d] * ball.s);

					checkNull(tmp, nr, nc);
					tmp[nr][nc].offer(ball);
				}
			}
		}

		map = tmp;
	}

	private static int rangeCheck(int i) {
		i -= i / N * N;
		if (i < 0)
			return N + i;
		return i;
	}

	private static void divide() {
		Queue<Ball>[][] tmp = new LinkedList[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null)
					continue;

				if (map[r][c].size() < 2) {
					checkNull(tmp, r, c);
					tmp[r][c].addAll(map[r][c]);
					continue;
				}

				Queue<Ball> que = map[r][c];

				int m = 0;
				int s = 0;
				int cnt = 0;
				boolean even = isEven(que.peek().d);
				boolean dir = true;

				while (!que.isEmpty()) {
					Ball ball = que.poll();
					m += ball.m;
					s += ball.s;
					cnt++;
					if (even != isEven(ball.d))
						dir = false;
				}

				if (m / 5 == 0)
					continue;

				for (int i = 0; i < 4; i++) {
					checkNull(tmp, r, c);
					tmp[r][c].offer(new Ball(m / 5, s / cnt, (dir ? 0 : 1) + 2 * i));
				}
			}
		}

		map = tmp;
	}

	private static boolean isEven(int n) {
		return n % 2 == 0 ? true : false;
	}

	static class Ball {
		int m, s, d;

		public Ball(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}

}