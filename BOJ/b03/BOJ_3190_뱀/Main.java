package b03.BOJ_3190_뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, K, L, map[][], dir[][];
	static Deque<int[]> snake;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}

		L = Integer.parseInt(br.readLine());
		dir = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			dir[i][0] = Integer.parseInt(st.nextToken());
			if ('L' == st.nextToken().charAt(0))
				dir[i][1] = -1;
			else
				dir[i][1] = 1;
		}

		int time = 0;
		int head = 1;
		int dCnt = 0;

		snake = new LinkedList<int[]>();
		snake.addFirst(new int[] { 1, 1 });
		map[1][1] = 1;

		while (!snake.isEmpty()) {
			time++;
			int r = snake.peekFirst()[0] + dr[head];
			int c = snake.peekFirst()[1] + dc[head];

			if (!check(r, c) || map[r][c] == 1)
				break;

			snake.addFirst(new int[] { r, c });
			if (map[r][c] != 2) {
				map[snake.peekLast()[0]][snake.peekLast()[1]] = 0;
				snake.pollLast();
			}
			map[r][c] = 1;

			if (dCnt < L && dir[dCnt][0] == time) {
				head += dir[dCnt++][1];
				if (head == -1)
					head = 3;
				else if (head == 4)
					head = 0;
			}
		}

		System.out.println(time);
	}

	private static boolean check(int r, int c) {
		if (r >= 1 && r <= N && c >= 1 && c <= N)
			return true;
		return false;
	}

}
