package b15.BOJ_15685_드래곤커브;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int dragon[][] = new int[N][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				dragon[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] map = new boolean[101][101];
		List<int[]> list = new ArrayList<>();

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		for (int i = 0; i < N; i++) {
			list.clear();
			int x = dragon[i][0];
			int y = dragon[i][1];
			list.add(new int[] { x, y });
			map[x][y] = true;

			int px = x + dx[dragon[i][2]];
			int py = y + dy[dragon[i][2]];
			list.add(new int[] { px, py });
			map[px][py] = true;

			for (int j = 0; j < dragon[i][3]; j++) {
				px = list.get(list.size() - 1)[0];
				py = list.get(list.size() - 1)[1];
				for (int l = list.size() - 2; l >= 0; l--) {
					x = px + (py - list.get(l)[1]);
					y = py - (px - list.get(l)[0]);
					list.add(new int[] { x, y });
					map[x][y] = true;
				}
			}
		}

		int tot = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
					tot++;
			}
		}
		System.out.println(tot);
	}

}
