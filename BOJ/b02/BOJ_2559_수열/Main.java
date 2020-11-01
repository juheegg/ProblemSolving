package b02.BOJ_2559_수열;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int[] nums = new int[N];
		int sum = 0;
		int max = 0;

		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
			if (i < K)
				sum += nums[i];
		}
		
		max = sum;
		for (int i = K; i < N; i++) {
			sum -= nums[i-K];
			sum += nums[i];
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}

}
