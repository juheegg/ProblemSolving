package b01.BOJ_1697_숨바꼭질;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, K;
	static int answer;
	static boolean visit[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		answer = Integer.MAX_VALUE;
		visit = new boolean[100001];

		if (N <= K)
			System.out.println(bfs());
		else
			System.out.println(N - K);
	}

	private static int bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.add(N);
		visit[N] = true;

		int step = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				int cur = que.poll();

				if (cur == K)
					return step;

				if (check(cur - 1) && !visit[cur - 1]) {
					que.add(cur - 1);
					visit[cur - 1] = true;
				}

				if (check(cur + 1) && !visit[cur + 1]) {
					que.add(cur + 1);
					visit[cur + 1] = true;
				}

				if (check(cur * 2) && !visit[cur * 2]) {
					que.add(cur * 2);
					visit[cur * 2] = true;
				}
			}
			step++;
		}
		return 0;
	}

	private static boolean check(int i) {
		if (i >= 0 && i < 100001)
			return true;
		return false;
	}

}
