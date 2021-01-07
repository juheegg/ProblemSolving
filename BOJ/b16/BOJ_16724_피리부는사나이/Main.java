package b16.BOJ_16724_피리부는사나이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] map;
	static int[][][] parents;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		parents = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				parents[i][j] = new int[] { i, j };
			}
		}

		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				dfs(i, j);
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (parents[i][j][0] == i && parents[i][j][1] == j)
					answer++;
			}
		}

		System.out.println(answer);
	}

	private static void dfs(int i, int j) {
		visit[i][j] = true;
		int r = i;
		int c = j;
		switch (map[r][c]) {
		case 'U':
			r--;
			break;
		case 'D':
			r++;
			break;
		case 'L':
			c--;
			break;
		case 'R':
			c++;
			break;
		}
		if (!check(r, c))
			return;

		union(r, c, i, j);
		if (!visit[r][c])
			dfs(r, c);

	}

	private static void union(int i, int j, int r, int c) {
		int[] rootij = find(i, j);
		int[] rootrc = find(r, c);
		if (rootij[0] == rootrc[0] && rootij[1] == rootrc[1])
			return;
		parents[r][c] = rootij;
	}

	private static int[] find(int r, int c) {
		if (parents[r][c][0] == r && parents[r][c][1] == c)
			return parents[r][c];
		return parents[r][c] = find(parents[r][c][0], parents[r][c][1]);
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
