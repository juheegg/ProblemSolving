package b08.BOJ_8983_사냥꾼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] gun = new int[M];
		int[][] animal = new int[N][2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			gun[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				animal[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(gun);

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (L < animal[i][1])
				continue;

			int min = animal[i][0] - (L - animal[i][1]);
			long max = animal[i][0] + (L - animal[i][1]);
			int idx = lowerBound(gun, min > 0 ? min : 0);
			if (gun[idx] >= min && gun[idx] <= max)
				answer++;

		}

		System.out.println(answer);
	}

	private static int lowerBound(int[] gun, int target) {
		int l = 0;
		int r = gun.length - 1;

		while (l < r) {
			int mid = (l + r) / 2;
			if (gun[mid] < target)
				l = mid + 1;
			else
				r = mid;
		}
		return l;
	}

}
