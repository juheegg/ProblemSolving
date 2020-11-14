package b09.BOJ_9237_이장님초대;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Integer t[];
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		t = new Integer[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(t, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, t[i] + i + 2);
		}

		System.out.println(answer);
	}

}
