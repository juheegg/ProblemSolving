package b01.BOJ_1592_영식이와친구들;

import java.util.Scanner;

public class Main {

	static int N, M, L;
	static int answer, cnt[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();

		answer = 0;
		cnt = new int[N + 1];

		int ball = 1;

		while (++cnt[ball] < M) {
			answer++;
			if (cnt[ball] % 2 == 1) {
				ball = (ball + L - 1) % N + 1;
			} else {
				ball = ball - L > 0 ? ball - L : ball - L + N;
			}
		}
		
		System.out.println(answer);
	}
}
