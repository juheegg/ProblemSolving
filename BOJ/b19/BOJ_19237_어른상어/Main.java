package b19.BOJ_19237_어른상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static final int SMELL = 0, NO = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][][] map = new int[N][N][2];
		List<Shark> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j][NO] = Integer.parseInt(st.nextToken());
				if (map[i][j][NO] != 0) {
					list.add(new Shark(i, j, map[i][j][NO]));
					map[i][j][SMELL] = K;
				}
			}
		}
		Collections.sort(list);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list.get(i).d = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			int[][] dir = new int[5][5];
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					dir[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			list.get(i).dir = dir;
		}

		Queue<Shark> que = new LinkedList<>(list);
		int time = 0;
		while (que.size() > 1) {
			if (time++ > 1000)
				break;
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Shark cur = que.poll();
				int[] dir = cur.dir[cur.d];
				boolean flag = false;
				for (int d = 1; d <= 4; d++) {
					int nr = cur.r + dr[dir[d]];
					int nc = cur.c + dc[dir[d]];
					if (!check(nr, nc))
						continue;
					if (map[nr][nc][SMELL] > 0)
						continue;
					flag = true;
					cur.r = nr;
					cur.c = nc;
					cur.d = dir[d];
					que.offer(cur);
					break;
				}
				if (flag)
					continue;
				for (int d = 1; d <= 4; d++) {
					int nr = cur.r + dr[dir[d]];
					int nc = cur.c + dc[dir[d]];
					if (!check(nr, nc))
						continue;
					if (map[nr][nc][NO] != cur.no)
						continue;
					cur.r = nr;
					cur.c = nc;
					cur.d = dir[d];
					que.offer(cur);
					break;
				}
			}
			for (int s = 0; s < size; s++) {
				Shark cur = que.poll();
				if (map[cur.r][cur.c][SMELL] == K + 1)
					continue;
				map[cur.r][cur.c][SMELL] = K + 1;
				map[cur.r][cur.c][NO] = cur.no;
				que.add(cur);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j][SMELL] == 0)
						continue;
					if (--map[i][j][SMELL] == 0)
						map[i][j][NO] = 0;
				}
			}
		}

		System.out.println(time > 1000 ? -1 : time);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	static class Shark implements Comparable<Shark> {
		int r, c, no, d, dir[][];

		public Shark(int r, int c, int no) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
		}

		@Override
		public int compareTo(Shark o) {
			return this.no - o.no;
		}

	}

}
