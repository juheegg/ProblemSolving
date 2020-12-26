package b16.BOJ_16678_모독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] honor = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			honor[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(honor);

		long answer = 0;
		int cnt = 1;
		for (int i = 1; i <= N; i++) {
			if (honor[i] >= cnt)
				answer += honor[i] - cnt++;
		}

		System.out.println(answer);
	}

}
