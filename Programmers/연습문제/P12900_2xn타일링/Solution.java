package 연습문제.P12900_2xn타일링;

public class Solution {

	static public int solution(int n) {
		if (n <= 2)
			return n;

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000007;
		}

		return dp[n];
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(solution(i));
		}

	}

}
