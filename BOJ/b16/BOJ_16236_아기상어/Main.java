package b16.BOJ_16236_아기상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, map[][], shark[];
	static int answer, totFish;
	static Queue<int[]> checkQue, pq;
	static int dr[] = { -1, 0, 0, 1 };
	static int dc[] = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		shark = new int[4];
		checkQue = new LinkedList<>();
		pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0])
					return a[1] - b[1];
				return a[0] - b[0];
			}
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					shark = new int[] { i, j, 2, 0 };
				} else if (map[i][j] > 0)
					totFish++;
			}
		}
		while (totFish > 0) {
			if (!searchFish())
				break;
		}

		System.out.println(answer);
	}

	private static boolean searchFish() {
		checkQue.clear();
		checkQue.offer(new int[] { shark[0], shark[1] });

		boolean visited[][] = new boolean[N][N];
		visited[shark[0]][shark[1]] = true;
		int cnt = 0;
		while (!checkQue.isEmpty()) {
			pq.clear();
			pq.addAll(checkQue);
			checkQue.clear();
			checkQue.addAll(pq);

			int size = pq.size();
			for (int i = 0; i < size; i++) {
				int r = pq.peek()[0];
				int c = pq.poll()[1];

				if (map[r][c] > 0 && map[r][c] < shark[2]) {
					totFish--;
					map[r][c] = 0;
					answer += cnt;
					shark[0] = r;
					shark[1] = c;
					if (++shark[3] == shark[2]) {
						shark[2]++;
						shark[3] = 0;
					}

					return true;
				}
			}
			cnt++;
			size = checkQue.size();
			for (int i = 0; i < size; i++) {
				int r = checkQue.peek()[0];
				int c = checkQue.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc))
						continue;
					if (visited[nr][nc])
						continue;

					if (map[nr][nc] <= shark[2]) {
						visited[nr][nc] = true;
						checkQue.add(new int[] { nr, nc });
					}
				}
			}
		}

		return false;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

}
