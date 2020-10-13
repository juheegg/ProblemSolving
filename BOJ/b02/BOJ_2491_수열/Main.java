package b02.BOJ_2491_수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int n[] = new int[N];
		int asc = 1;
		int desc = 1;
		int answer = 1;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			if (n[i] >= n[i - 1])
				asc++;
			else
				asc = 1;
			answer = Math.max(answer, asc);

			if (n[i] <= n[i - 1])
				desc++;
			else
				desc = 1;
			answer = Math.max(answer, desc);
		}
		
		System.out.println(answer);
	}
}
