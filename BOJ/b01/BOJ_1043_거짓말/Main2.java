package b01.BOJ_1043_거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int truthn = Integer.parseInt(st.nextToken());
		for (int i = 0; i < truthn; i++) {
			union(0, Integer.parseInt(st.nextToken()));
		}

		int[] party = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int curlen = Integer.parseInt(st.nextToken());
			int a, b;
			a = Integer.parseInt(st.nextToken());
			party[i] = a;
			for (int j = 0; j < curlen; j++) {
				b = Integer.parseInt(st.nextToken());
				union(a, b);
				a = b;
			}
		}
		
		int answer = 0;
		for (int i = 0; i < M; i++) {
			if(find(party[i]) != 0)
				answer++;
		}
		System.out.println(answer);
	}

	private static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		if (ar == br)
			return;

		parent[b] = a;
	}

	private static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

}
