package s05.SWEA_5650_핀볼게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, map[][];
	static int wormhole[][][], blockDir[][];

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		// 상0 하1 좌2 우3
		blockDir = new int[6][];
		blockDir[1] = new int[] { 1, 3, 0, 2 };
		blockDir[2] = new int[] { 3, 0, 1, 2 };
		blockDir[3] = new int[] { 2, 0, 3, 1 };
		blockDir[4] = new int[] { 1, 2, 3, 0 };
		blockDir[5] = new int[] { 1, 0, 3, 2 };

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			wormhole = new int[11][2][2];
			boolean[] wormCheck = new boolean[11];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {
						if (!wormCheck[map[i][j]]) {
							wormhole[map[i][j]][0] = new int[] { i, j };
							wormCheck[map[i][j]] = true;
						} else
							wormhole[map[i][j]][1] = new int[] { i, j };
					}
				}
			}

			int maxScore = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						continue;

					for (int dir = 0; dir < 4; dir++) {
						int r = i;
						int c = j;
						int d = dir;
						int score = 0;

						while (true) {
							r += dr[d];
							c += dc[d];

							if (r == i && c == j)
								break;
							if (r >= 0 && r < N && c >= 0 && c < N && map[r][c] == -1)
								break;

							if (r < 0 || r >= N || c < 0 || c >= N) {
								score++;
								d = blockDir[5][d];
							} else if (map[r][c] >= 1 && map[r][c] <= 5) {
								score++;
								d = blockDir[map[r][c]][d];
							} else if (map[r][c] >= 6) {
								int whole = map[r][c];
								if (wormhole[whole][0][0] == r && wormhole[whole][0][1] == c) {
									r = wormhole[whole][1][0];
									c = wormhole[whole][1][1];
								} else {
									r = wormhole[whole][0][0];
									c = wormhole[whole][0][1];
								}
							}
						}
						maxScore = Math.max(maxScore, score);
					}
				}
			}
			answer.append("#" + tc + " " + maxScore + "\n");
		}
		System.out.println(answer);
	}

}
