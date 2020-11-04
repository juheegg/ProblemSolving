package b03.BOJ_3078_좋은친구;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K, name[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		name = new int[N];

		for (int i = 0; i < N; i++) {
			name[i] = br.readLine().trim().length();
		}

		int[] window = new int[21];
		long tot = 0L;

		for (int i = 0; i <= K; i++) {
			window[name[i]]++;
		}

		for (int i = 0; i < N; i++) {
			tot += window[name[i]] - 1;
			if (i + K + 1 < N)
				window[name[i + K + 1]]++;
			if (i - K >= 0)
				window[name[i - K]]--;
		}

		System.out.println(tot / 2);

	}

}
