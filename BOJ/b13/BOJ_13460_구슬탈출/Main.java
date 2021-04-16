package b13.BOJ_13460_구슬탈출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		answer = Integer.MAX_VALUE;

		Ball red = null;
		Ball blue = null;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'R') {
					map[i][j] = '.';
					red = new Ball(i, j);
				}
				if (map[i][j] == 'B') {
					map[i][j] = '.';
					blue = new Ball(i, j);
				}
			}
		}

		dfs(red, blue, 0);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void dfs(Ball red, Ball blue, int depth) {
		if (depth == 10 || depth >= answer)
			return;

		for (int d = 0; d < 4; d++) {
			Ball movedRed = moveBall(red, d);
			Ball movedBlue = moveBall(blue, d);

			if (movedBlue.hall)
				continue;
			if (movedRed.hall) {
				answer = Math.min(answer, depth + 1);
				return;
			}

			if (isSamePlace(red, movedRed) && isSamePlace(blue, movedBlue))
				continue;

			if (isSamePlace(movedRed, movedBlue)) {
				if (movedRed.dis < movedBlue.dis) {
					movedBlue.r -= dr[d];
					movedBlue.c -= dc[d];
				} else {
					movedRed.r -= dr[d];
					movedRed.c -= dc[d];
				}
			}

			dfs(new Ball(movedRed.r, movedRed.c), new Ball(movedBlue.r, movedBlue.c), depth + 1);
		}
	}

	private static boolean isSamePlace(Ball a, Ball b) {
		if (a.r == b.r && a.c == b.c)
			return true;
		return false;
	}

	private static Ball moveBall(Ball ball, int d) {
		Ball movedBall = new Ball(ball.r, ball.c);
		while (map[movedBall.r][movedBall.c] != '#') {
			if (map[movedBall.r][movedBall.c] == 'O') {
				movedBall.hall = true;
				break;
			}
			movedBall.dis++;
			movedBall.r += dr[d];
			movedBall.c += dc[d];
		}

		if (map[movedBall.r][movedBall.c] == '#') {
			movedBall.dis--;
			movedBall.r -= dr[d];
			movedBall.c -= dc[d];
		}

		return movedBall;
	}

	static class Ball {
		int r, c, dis;
		boolean hall;

		public Ball(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Ball [r=" + r + ", c=" + c + ", dis=" + dis + ", hall=" + hall + "]";
		}

	}
}
