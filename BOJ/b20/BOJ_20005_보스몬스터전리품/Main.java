package b20.BOJ_20005_보스몬스터전리품;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, P;
	static char map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		map = new char[M][N];
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}

		Player[] players = new Player[P];
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			players[i] = new Player(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
			setDist(players[i]);
		}

		int monsterHP = Integer.parseInt(br.readLine());

		int answer = 0;
		int turn = 0;
		while (monsterHP > 0) {
			answer = 0;
			turn++;
			for (int i = 0; i < P; i++) {
				if (players[i].dist <= turn) {
					monsterHP -= players[i].dps;
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	private static void setDist(Player player) {
		int r = -1;
		int c = -1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == player.id) {
					r = i;
					c = j;
					break;
				}
			}
			if (r != -1)
				break;
		}

		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		boolean[][] visit = new boolean[M][N];
		visit[r][c] = true;

		int cnt = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			cnt++;
			for (int s = 0; s < size; s++) {
				r = que.peek()[0];
				c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || visit[nr][nc] || map[nr][nc] == 'X')
						continue;
					if (map[nr][nc] == 'B') {
						player.dist = cnt;
						return;
					}
					visit[nr][nc] = true;
					que.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < M && c >= 0 && c < N)
			return true;
		return false;
	}

	static class Player {
		char id;
		int dps;
		int dist;

		public Player(char id, int dps) {
			super();
			this.dps = dps;
			this.id = id;
		}
	}

}
