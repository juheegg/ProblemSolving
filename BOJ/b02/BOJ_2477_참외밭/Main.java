package b02.BOJ_2477_참외밭;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		int N = 6;
		int n[][] = new int[N][2];
		int cnt[] = new int[5];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				n[i][j] = Integer.parseInt(st.nextToken());
			}
			cnt[n[i][0]]++;
		}

		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (cnt[n[i][0]] == 1) {
				idx = i;
				break;
			}
		}
		int total = 1;
		int minus = 1;
		int flag = 0;
		for (int i = 0; i < N; i++) {
			if (idx == 6)
				idx = 0;
			if (cnt[n[idx][0]] == 1)
				total *= n[idx][1];
			else {
				if (flag == 1 || flag == 2) {
					minus *= n[idx][1];
				}
				flag++;
			}
			idx++;
		}
		System.out.println((total - minus) * K);
	}
}
