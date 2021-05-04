package KakaoIntern2019.P64062_징검다리건너기;

public class Solution {

	static int solution(int[] stones, int k) {
		int max = 0;
		for (int i = 0; i < stones.length; i++) {
			max = Math.max(stones[i], max);
		}

		int left = 0;
		int right = max;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (possible(stones, k, mid)) {
				left = mid + 1;
			} else
				right = mid - 1;
		}
		return right;
	}

	private static boolean possible(int[] stones, int k, int mid) {
		int cnt = 0;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] < mid) {
				if (++cnt >= k)
					return false;
			} else {
				cnt = 0;
			}

		}
		return true;
	}

	public static void main(String[] args) {
		int[] s = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		System.out.println(solution(s, 3));
	}

}
