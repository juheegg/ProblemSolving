package b03.BOJ_3678_카탄의개척자;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, n[];
	static int tile[];
	static boolean use[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		n = new int[N];
		use = new boolean[6];

		int max = 0;
		for (int i = 0; i < N; i++) {
			n[i] = sc.nextInt();
			max = Math.max(max, n[i]);
		}

		tile = new int[max + 1];
		int[] tmp = { 0, 1, 2, 3, 4, 5, 2, 3 };

		if (max < 8) {
			tile = tmp;
		} else {
			System.arraycopy(tmp, 0, tile, 0, 8);
			solution();
		}

		for (int i = 0; i < N; i++) {
			System.out.println(tile[n[i]]);
		}
	}

	private static void solution() {
		int cnt = 8;
		int under = 6;
		int line = 2;
		int[] colorCnt = { 0, 1, 2, 2, 1, 1 };
		while (cnt < tile.length) {
			for (int i = 0; i < 6; i++) {
				int x = line;
				if (i == 0)
					x -= 1;
				else if (i == 5)
					x += 1;
				for (int l = 0; l < x; l++) {
					Arrays.fill(use, false);
					use[tile[cnt - 1]] = true;
					use[tile[cnt - under]] = true;
					if (l != 0)
						use[tile[cnt - under - 1]] = true;

					int color = 0;
					int min = Integer.MAX_VALUE;
					for (int j = 1; j <= 5; j++) {
						if (use[j])
							continue;
						if (min > colorCnt[j]) {
							min = colorCnt[j];
							color = j;
						}
					}
					colorCnt[color]++;
					tile[cnt] = color;
					System.out.println();
					if (++cnt >= tile.length)
						return;
				}
				under++;
			}
			line++;
		}
	}

}
