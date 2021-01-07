package b02.BOJ_2515_전시장;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] d = new int[N];
		Picture[] arr = new Picture[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i] = new Picture(h, c);
		}

		Arrays.sort(arr);

		d[0] = arr[0].cost;
		for (int i = 1; i < N; i++) {
			d[i] = arr[i].cost;
			int idx = upperBound(arr, arr[i].height - S);
			if (idx > 0) {
				d[i] = Math.max(d[idx - 1] + d[i], d[i - 1]);
			} else {
				d[i] = Math.max(d[i], d[i - 1]);
			}
		}

		System.out.println(d[N - 1]);
	}

	private static int upperBound(Picture[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		int mid = (start + end) / 2;

		while (end > start) {
			mid = (start + end) / 2;
			if (arr[mid].height > target)
				end = mid;
			else
				start = mid + 1;
		}
		return end;
	}

	static class Picture implements Comparable<Picture> {
		int height, cost;

		public Picture(int height, int cost) {
			super();
			this.height = height;
			this.cost = cost;
		}

		@Override
		public int compareTo(Picture o) {
			return this.height - o.height;
		}

	}
}
