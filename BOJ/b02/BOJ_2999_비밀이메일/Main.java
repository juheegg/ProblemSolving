package b02.BOJ_2999_비밀이메일;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String enc = sc.nextLine().trim();

		int N = enc.length();
		int R = 0, C = 0;
		for (int i = N; i > 0; i--) {
			R = i;
			C = N / R;
			if (R <= C && R * C == N)
				break;
		}
		
		char[][] table = new char[R][C];

		int idx = 0;
		for (int c = 0; c < C; c++) {
			for (int r = 0; r < R; r++) {
				table[r][c] = enc.charAt(idx++);
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(table[r][c]);
			}
		}
	}

}
