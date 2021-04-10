package b02.BOJ_2186_문자판;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static char map[][], str[];
	static int dp[][][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		str = br.readLine().toCharArray();
		dp = new int[N][M][str.length];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				Arrays.fill(dp[i][j], -1);

		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == str[0])
					answer += dfs(1, r, c);
			}
		}
		System.out.println(answer);
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	private static int dfs(int cnt, int r, int c) {

		if (cnt == str.length)
			return 1;

		if (dp[r][c][cnt] > -1)
			return dp[r][c][cnt];

		dp[r][c][cnt] = 0;
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * k;
				int nc = c + dc[d] * k;

				if (!check(nr, nc))
					continue;

				if (map[nr][nc] == str[cnt])
					dp[r][c][cnt] += dfs(cnt + 1, nr, nc);
				else if (cnt + 1 < str.length)
					dp[nr][nc][cnt + 1] = 0;
			}
		}
		return dp[r][c][cnt];
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}

}