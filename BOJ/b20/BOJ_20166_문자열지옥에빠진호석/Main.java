package b20.BOJ_20166_문자열지옥에빠진호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, dp[][][];
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}

		String[] str = new String[K];
		for (int i = 0; i < K; i++) {
			str[i] = br.readLine();
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int result = 0;
			dp = new int[N][M][str[i].length()];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (str[i].charAt(0) == map[r][c])
						result += dfs(r, c, 0, str[i]);
				}
			}
			answer.append(result + "\n");
		}
		System.out.println(answer);

	}

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private static int dfs(int r, int c, int depth, String str) {
		if (depth == str.length() - 1) {
			return 1;
		}
		int result = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr == -1)
				nr = N - 1;
			else if (nr == N)
				nr = 0;

			if (nc == -1)
				nc = M - 1;
			else if (nc == M)
				nc = 0;

			if (depth + 1 < str.length() && dp[nr][nc][depth + 1] != 0)
				result += dp[nr][nc][depth + 1];
			else if (map[nr][nc] == str.charAt(depth + 1))
				result += dfs(nr, nc, depth + 1, str);

		}
		return dp[r][c][depth] = result;
	}

}
