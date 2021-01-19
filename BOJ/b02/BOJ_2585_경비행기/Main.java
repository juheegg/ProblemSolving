package b02.BOJ_2585_경비행기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Point[] station;
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		station = new Point[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			station[i] = new Point(x, y);
		}

		int l = 0, r = calcFuel(0, 0, 10000, 10000);
		while (l < r) {
			int mid = (l + r) / 2;
			if (canGo(mid)) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		System.out.println(l);
	}

	private static boolean canGo(int fuel) {
		boolean[] visit = new boolean[N];

		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0, 0, 0));

		while (!que.isEmpty()) {
			Point cur = que.poll();

			if (fuel >= calcFuel(cur.x, cur.y, 10000, 10000))
				return true;

			if (cur.cnt == K)
				continue;

			for (int i = 0; i < N; i++) {
				if (visit[i] || fuel < calcFuel(cur.x, cur.y, station[i].x, station[i].y))
					continue;
				visit[i] = true;
				que.offer(new Point(station[i].x, station[i].y, cur.cnt + 1));
			}
		}

		return false;
	}

	private static int calcFuel(int x1, int y1, int x2, int y2) {
		double result = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return result / 10 == ((int) result) / 10 ? (int) result / 10 : (int) result / 10 + 1;
	}

	static class Point {
		int x, y, cnt;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

}
