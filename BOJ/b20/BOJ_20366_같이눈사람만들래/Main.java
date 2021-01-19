package b20.BOJ_20366_같이눈사람만들래;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] ball = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ball[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(ball);

		int answer = Integer.MAX_VALUE;
		for (int a = 0; a < N - 3; a++) {
			for (int b = a + 1; b < N - 2; b++) {
				for (int d = N - 1; d > b + 1; d--) {
					int c = search(ball, b + 1, d - 1, ball[a] + ball[d] - ball[b]);
					answer = Math.min(answer, Math.abs((ball[a] + ball[d]) - (ball[b] + ball[c])));
					if (c - 1 != a && c - 1 != b && c - 1 != d)
						answer = Math.min(answer, Math.abs((ball[a] + ball[d]) - (ball[b] + ball[c - 1])));
				}
			}
		}
		System.out.println(answer);
	}

	private static int search(int[] ball, int l, int r, int target) {
		while (l < r) {
			int mid = (l + r) / 2;
			if (ball[mid] < target)
				l = mid + 1;
			else
				r = mid;
		}
		return l;
	}

}
