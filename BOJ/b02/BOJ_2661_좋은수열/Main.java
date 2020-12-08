package b02.BOJ_2661_좋은수열;

import java.util.Scanner;

public class Main {

	static int N, nums[];
	static StringBuilder answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();

		nums = new int[N];
		answer = new StringBuilder();

		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			for (int i : nums) {
				answer.append(i);
			}
			return;
		}
		for (int i = 1; i <= 3; i++) {
			nums[cnt] = i;
			if (check(cnt))
				dfs(cnt + 1);
			if (answer.length() > 0)
				return;

		}
	}

	private static boolean check(int len) {
		int step = (len + 1) / 2;
		for (int i = 1; i <= step; i++) {
			int cnt = 0;
			for (int s = 0; s < i; s++) {
				if (nums[len - s] == nums[len - i - s])
					cnt++;
			}
			if (cnt == i)
				return false;
		}
		return true;
	}

}
