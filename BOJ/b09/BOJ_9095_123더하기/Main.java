package b09.BOJ_9095_123더하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, nums[];
	static int result[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[i]);
		}

		result = new int[Math.max(max + 1, 4)];
		result[1] = 1;
		result[2] = 2;
		result[3] = 4;

		if (max > 3) {
			for (int i = 4; i < max + 1; i++) {
				result[i] = result[i - 1] + result[i - 2] + result[i - 3];
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.println(result[nums[i]]);
		}

	}
}
