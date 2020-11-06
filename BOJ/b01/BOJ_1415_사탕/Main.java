package b01.BOJ_1415_사탕;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	static int N;
	static List<Integer> candy;
	static long D[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		candy = new ArrayList<>();

		int candysum = 0;
		int zerocnt = 0;
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(br.readLine());
			candysum += c;
			if (c == 0)
				zerocnt++;
			else
				candy.add(c);
		}
		Collections.sort(candy);

		D = new long[candy.size()][candysum + 1];
		int[] cnt = new int[candysum + 1];

		D[0][candy.get(0)] = 1L;
		cnt[candy.get(0)]++;

		for (int c = 1; c < candy.size(); c++) {
			int start = candy.get(c) * ++cnt[candy.get(c)];
			for (int i = 1; i <= start; i++) {
				D[c][i] = D[c - 1][i];
			}
			D[c][start]++;
			for (int i = start + 1; i < candysum + 1; i++) {
				if (cnt[candy.get(c)] == 1) {
					D[c][i] = D[c - 1][i] + D[c - 1][i - candy.get(c)];
				} else {
					D[c][i] = D[c - 1][i - candy.get(c)];
				}
			}
		}

//		for (long[] i : D)
//			System.out.println(Arrays.toString(i));
//		System.out.println();

		long answer = 0;
		for (int i = 2; i <= candysum; i++) {
			if (isPrime(i))
				answer += D[candy.size() - 1][i];
		}
		System.out.println(answer * (zerocnt + 1L));
	}

	private static boolean isPrime(int n) {
		for (int d = 2; d * d <= n; d++) {
			if (n % d == 0)
				return false;
		}
		return true;
	}

}
