package b17.BOJ_17822_원판돌리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		int[][] opr = new int[T][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				opr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		print();

		for (int i = 0; i < T; i++) {
			int x = opr[i][0];
			int d = opr[i][1];
			int k = opr[i][2];
			for (int r = 1; r <= N; r++) {
				if (r >= x && r % x == 0)
					map[r] = rotate(map[r], d, k);
			}
//			print();
			removeAdj();
//			print();
		}

		System.out.println(getSum());
	}

	private static void removeAdj() {
		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;
				if (bfs(i, j))
					flag = true;
			}
		}
		if (!flag) {
			double avg = getSum() / getCnt();
			compAvg(avg);
			System.out.println(avg);
		}
	}

	private static void compAvg(double avg) {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;
				if (map[i][j] > avg)
					map[i][j]--;
				else if (map[i][j] < avg)
					map[i][j]++;
			}
		}
	}

	private static boolean bfs(int i, int j) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visit = new boolean[N + 1][M];
		int cur = map[i][j];
		que.add(new int[] { i, j });
		visit[i][j] = true;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nc == -1)
						nc = M - 1;
					else if (nc == M)
						nc = 0;

					if (nr > N || nr < 1)
						continue;
					if (visit[nr][nc] || map[nr][nc] != cur)
						continue;
					map[nr][nc] = 0;
					visit[nr][nc] = true;
					que.add(new int[] { nr, nc });
				}
				if (i == r && j == c && que.size() == 0)
					return false;
				map[i][j] = 0;
			}
		}
		return true;
	}

	private static int getSum() {
		int sum = 0;
		for (int[] is : map)
			for (int i : is)
				sum += i;
		return sum;
	}

	private static double getCnt() {
		double cnt = 0.0;
		for (int[] is : map)
			for (int i : is)
				if (i > 0)
					cnt++;
		return cnt;
	}

	private static int[] rotate(int[] src, int d, int k) {
		int[] dest = new int[M];

		if (d == 1)
			k = M - k;

		for (int i = 0; i < M; i++) {
			int idx = i + k < M ? i + k : i + k - M;
			dest[idx] = src[i];
		}
		return dest;
	}

	private static void print() {
		for (int[] is : map) {
			for (int i : is) {
				System.out.print(i + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
