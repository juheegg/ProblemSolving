package b17.BOJ_17220_마약수사대;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static boolean visit[];
	static List<Integer> root, rel[];
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int tmp[][] = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				tmp[i][j] = st.nextToken().charAt(0) - 'A';
			}
		}

		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		visit = new boolean[N];
		for (int i = 0; i < X; i++) {
			int x = st.nextToken().charAt(0) - 'A';
			visit[x] = true;
		}

		rel = new ArrayList[N];
		for (int i = 0; i < N; i++)
			rel[i] = new ArrayList<>();
		root = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			rel[tmp[i][0]].add(tmp[i][1]);
			if (!root.contains(tmp[i][0]))
				root.add(tmp[i][0]);
		}

		for (int i = 0; i < M; i++) {
			root.remove(new Integer(tmp[i][1]));
		}

		for (int i = 0; i < root.size(); i++) {
			if (!visit[root.get(i)]) {
				answer--;
				dfs(root.get(i));
			}
		}

		System.out.println(answer);
	}

	private static void dfs(int x) {
		answer++;
		visit[x] = true;
		for (int i = 0; i < rel[x].size(); i++) {
			if (!visit[rel[x].get(i)])
				dfs(rel[x].get(i));
		}
	}
}
