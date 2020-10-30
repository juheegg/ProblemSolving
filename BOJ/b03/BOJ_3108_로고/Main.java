package b03.BOJ_3108_로고;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, rect[][];
	static int select[], parents[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		rect = new int[N][4];
		parents = new int[N];
		select = new int[2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				rect[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		comb(0, 0);

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (i == find(i))
				answer++;
		}

		System.out.println(Arrays.toString(parents));

		boolean flag = false;

		for (int i = 0; i < N; i++) {
			if (zeroCheck(i))
				flag = true;
		}

		if (flag)
			answer--;

		System.out.println(answer);
	}

	private static boolean zeroCheck(int i) {
		int[] r = rect[i];

		if ((r[0] == 0 || r[2] == 0) && r[1] <= 0 && 0 <= r[3])
			return true;
		if ((r[1] == 0 || r[3] == 0) && r[0] <= 0 && 0 <= r[2])
			return true;

		return false;
	}

	private static void comb(int cnt, int start) {
		if (cnt == 2) {
			System.out.println(select[0] + " & " + select[1]);
			if (check())
				union(select[0], select[1]);
			return;
		}
		for (int i = start; i < N; i++) {
			select[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void union(int a, int b) {
		if (find(a) == find(b))
			return;
		parents[b] = a;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean check() {
		int[] r1 = rect[select[0]];
		int[] r2 = rect[select[1]];

		// r2가 안에 있음
		if (r1[0] < r2[0] && r1[1] < r2[1] && r1[2] > r2[2] && r1[3] > r2[3]) {
			System.out.println(1);
			return false;
		}
		// r1이 안에 있음
		if (r1[0] > r2[0] && r1[1] > r2[1] && r1[2] < r2[2] && r1[3] < r2[3]) {
			System.out.println(2);
			return false;
		}
		if (r1[2] < r2[0] || r1[0] > r2[2] || r1[3] < r2[1] || r1[1] > r2[3]) {
			System.out.println(3);
			return false;
		}
		System.out.println(0);
		return true;
	}

}
