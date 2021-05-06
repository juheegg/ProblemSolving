package KakaoBlindRecruitment2018.P17681_비밀지도;

public class Solution {

	static public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		boolean[][] result = new boolean[n][n];
		checkMap(result, n, arr1);
		checkMap(result, n, arr2);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				if (result[i][j])
					sb.append("#");
				else
					sb.append(" ");
			}
			answer[i] = sb.toString();
		}
		return answer;
	}

	private static void checkMap(boolean[][] result, int n, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int target = arr[i];
			int mod = (int) Math.pow(2, n - 1);
			for (int j = 0; j < n; j++) {
				if (target / mod == 1)
					result[i][j] = true;
				target %= mod;
				mod /= 2;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 20, 28, 18, 11 };
		int[] a2 = { 30, 1, 21, 17, 28 };
		String[] r = solution(5, a1, a2);
		for (String s : r)
			System.out.println(s);
	}

}
