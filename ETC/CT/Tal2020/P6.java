package CT.Tal2020;

import java.io.IOException;
import java.util.Arrays;

public class P6 {

	public static void main(String[] args) throws IOException {
		int n = 764;
		
		boolean[][] flag = {{false, true, true, false, true},
				{true, false, true, false, false},
				{false, true, false, true, false},
				{false, false, true, false, false},
				{false, false, true, true, false}};
		
		int[][] D = new int[n + 1][5];
		Arrays.fill(D[1], 1);

		for (int d = 2; d <= n; d++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (flag[i][j])
						D[d][i] = (D[d][i] + D[d-1][j]) % 1000000007;
				}
			}
		}
		
		int tot = 0;
		for (int i = 0; i < 5; i++) {
			tot = (tot + D[n][i]) % 1000000007;
		}

		System.out.println(tot);
	}
	
	private static void print(int map[][]) {
		for (int[] m : map) {
			for (int i : m) {
				System.out.print(i + "\t");
			}
			System.out.println();
		}
	}
}
