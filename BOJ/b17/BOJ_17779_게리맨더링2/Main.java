package b17.BOJ_17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, people[][], area[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		people = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int r = 1; r < N - 1; r++) {
			for (int c = 0; c < N - 2; c++) {
				for (int a = 1; a < N - 1; a++) {
					for (int b = 1; b < N - 1; b++) {
						if (c + a + b >= N || r - a < 0 || r + b >= N)
							continue;
						area = new int[N][N];
						checkFive(r, c, a, b);
						print();
						checkAnother(r, c, a, b);
						print();
						int dif = getMinDif();
						answer = Math.min(answer, dif);
					}
				}
			}
		}
		System.out.println(answer);
	}

	private static void print() {
		for (int[] is : area) {
			for (int i : is) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void checkFive(int r, int c, int a, int b) {
		for (int i = 0; i <= a; i++) {
			area[r - i][c + i] = 5;
			area[r + b - i][c + b + i] = 5;
		}
		for (int i = 0; i <= b; i++) {
			area[r + i][c + i] = 5;
			area[r - a + i][c + a + i] = 5;
		}

		for (int i = 0; i < a; i++) {
			int nr = r - i;
			int nc = c + i + 1;
			while (area[nr][nc] == 0) {
				area[nr][nc++] = 5;
			}
		}
		for (int i = 0; i < b; i++) {
			int nr = r + i;
			int nc = c + i + 1;
			while (area[nr][nc] == 0) {
				area[nr][nc++] = 5;
			}
		}
	}

	private static void checkAnother(int r, int c, int a, int b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (area[i][j] == 5)
					continue;
				if (i <= r && j < c + a)
					area[i][j] = 1;
				else if (i > r && j <= c + b)
					area[i][j] = 2;
				else if (i < r - a + b && j >= c + a)
					area[i][j] = 3;
				else if (i >= r - a + b && j > c + b)
					area[i][j] = 4;
			}
		}
	}

	private static int getMinDif() {
		int[] sum = new int[6];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum[area[i][j]] += people[i][j];
			}
		}

		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int i = 1; i < 6; i++) {
			min = Math.min(min, sum[i]);
			max = Math.max(max, sum[i]);
		}
		return max - min;
	}

}
