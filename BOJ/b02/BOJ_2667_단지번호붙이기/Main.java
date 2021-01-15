package b02.BOJ_2667_단지번호붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

	static int N, map[][];
	static int addr, cnt;
	static LinkedList<Integer> result;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		addr = 2;
		result = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = line.charAt(j) - '0';
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == 1) {
					cnt = 1;
					dfs(i, j);
					result.offer(cnt);
					addr++;
				}

		System.out.println(addr - 2);
		Collections.sort(result);
		for (int n : result)
			System.out.println(n);
	}

	private static void dfs(int r, int c) {
		map[r][c] = addr;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (check(nr, nc) && map[nr][nc] == 1) {
				cnt++;
				dfs(nr, nc);
			}
		}

	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
