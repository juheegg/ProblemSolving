package b17.BOJ_17954_투튜브;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] tube = new int[2][N];

		int n = 2 * N;
		tube[1][N - 1] = n--;
		tube[1][0] = n--;
		tube[0][N - 1] = n--;
		tube[0][0] = n--;

		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < N - 1; j++) {
				tube[i][j] = n--;
			}
		}

		long tot = 0L;
		long x = 0L;
		int idx = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				x += idx++;
				tot += x * tube[i][j];
			}
		}
		
		if (N == 1) {
			System.out.println("2\n1\n2");
			return;
		}
		
		System.out.println(tot);
		for (int t[] : tube) {
			for (int i : t)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
