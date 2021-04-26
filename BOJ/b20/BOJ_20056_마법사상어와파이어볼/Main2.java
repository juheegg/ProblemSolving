package b20.BOJ_20056_마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	static int N;
	static List<Ball> list;
	static Queue<Ball>[][] queMap;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		queMap = new LinkedList[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Ball(r - 1, c - 1, m, s, d));
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				queMap[i][j] = new LinkedList<>();
			}
		}

		for (int step = 0; step < K; step++) {
			for (int i = 0; i < list.size(); i++) {
				move(list.get(i));
			}
			list.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int size = queMap[i][j].size();
					if (size == 1)
						list.add(queMap[i][j].poll());
					else if (size > 1)
						make(i, j);
				}
			}
		}

		System.out.println(getTotalM());
	}

	private static void make(int r, int c) {
		Queue<Ball> que = queMap[r][c];
		int totM = 0;
		int totS = 0;
		int size = que.size();
		boolean even = true;
		boolean odd = true;

		while (!que.isEmpty()) {
			Ball cur = que.poll();
			totM += cur.m;
			totS += cur.s;
			even &= cur.d % 2 == 0 ? true : false;
			odd &= cur.d % 2 == 1 ? true : false;
		}

		if (totM / 5 == 0)
			return;

		int start = 1;
		if (even || odd)
			start = 0;
		for (int i = start; i < 8; i += 2) {
			list.add(new Ball(r, c, totM / 5, totS / size, i));
		}
	}

	private static void move(Ball ball) {
		ball.r = check(ball.r + dr[ball.d] * ball.s);
		ball.c = check(ball.c + dc[ball.d] * ball.s);

		queMap[ball.r][ball.c].offer(ball);

	}

	private static int check(int i) {
		i %= N;
		return i >= 0 ? i : N + i;
	}

	private static int getTotalM() {
		int result = 0;
		for (int i = 0; i < list.size(); i++)
			result += list.get(i).m;
		return result;
	}

	static class Ball {
		int r, c, m, s, d;

		public Ball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Ball [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}

}
