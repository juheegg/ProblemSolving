package b15.BOJ_15684_사다리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 틀린거 {

	static int N, M, H;
	static boolean ladder[][];
	static int cnt[];
	static boolean flag;
	static List<Integer> oddlist;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H + 1][N];
		cnt = new int[N];

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
			cnt[b]++;
		}

		int odd = 0;
		oddlist = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			if (cnt[i] % 2 != 0) {
				odd++;
				oddlist.add(i);
			}
		}

		if (N == 2) {
			if (M % 2 == 0)
				System.out.println(0);
			else
				System.out.println(1);
		} else if (odd > 3) {
			System.out.println(-1);
		} else if (odd == 0) {
			if (canReach()) {
				System.out.println(0);
			} else {
				System.out.println(-1);
			}
		} else if (makeStep(0, odd)) {
			System.out.println(odd);
		} else {
			System.out.println(-1);
		}

	}

	private static boolean makeStep(int n, int odd) {
		if (n == odd) {
			if (canReach())
				flag = true;
			return flag;
		}
		for (int i = 1; i <= H; i++) {
			int j = oddlist.get(n);
			if (ladder[i][j])
				continue;
			if ((j == 1 && !ladder[i][j + 1]) || (j == N - 1 && !ladder[i][j - 1])
					|| (!ladder[i][j - 1] && !ladder[i][j + 1])) {
				ladder[i][j] = true;
				makeStep(n + 1, odd);
				if (flag)
					return true;
				ladder[i][j] = false;
			}

		}
		return false;
	}

	private static boolean canReach() {
		for (int c = 1; c < N; c++) {
			int j = c;
			for (int i = 1; i <= H; i++) {
				if (j > 1 && ladder[i][j - 1]) {
					j--;
				} else if (j < N && ladder[i][j]) {
					j++;
				}
			}
			if (j != c)
				return false;
		}
		return true;
	}

}
