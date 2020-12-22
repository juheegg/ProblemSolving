package b04.BOJ_4577_소코반;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int r, c, box;
	static char map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int step = 0;
		while (true) {
			step++;

			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (R == 0 && C == 0)
				break;

			map = new char[R][C];
			box = 0;

			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 'w' || map[i][j] == 'W') {
						r = i;
						c = j;
						map[i][j] = map[i][j] == 'w' ? '.' : '+';
					} else if (map[i][j] == 'b')
						box++;
				}
			}

			String direction = br.readLine();
			boolean flag = false;
			for (int i = 0; i < direction.length(); i++) {
				int dir = 0;
				switch (direction.charAt(i)) {
				case 'D':
					dir = 1;
					break;
				case 'L':
					dir = 2;
					break;
				case 'R':
					dir = 3;
					break;
				}
				move(dir);
				
				if (box == 0) {
					flag = true;
					break;
				}
			}

			map[r][c] = map[r][c] == '+' ? 'W' : 'w';

			answer.append("Game " + step + ": ");
			answer.append(flag ? "complete\n" : "incomplete\n");
			for (char[] m : map) {
				for (char c : m)
					answer.append(c);
				answer.append("\n");
			}

		}

		System.out.println(answer);
	}

	private static void move(int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if (map[nr][nc] == '#') {
			return;
		} else if (map[nr][nc] == 'B' || map[nr][nc] == 'b') {
			int br = nr + dr[dir];
			int bc = nc + dc[dir];

			if (map[br][bc] == '#' || map[br][bc] == 'B' || map[br][bc] == 'b')
				return;

			map[nr][nc] = map[nr][nc] == 'B' ? '+' : '.';
			map[br][bc] = map[br][bc] == '+' ? 'B' : 'b';

			if (map[nr][nc] == '.' && map[br][bc] == 'B')
				box--;
			else if (map[nr][nc] == '+' && map[br][bc] == 'b')
				box++;
		}
		r = nr;
		c = nc;
	}
}
