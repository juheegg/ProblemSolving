package s05.SWEA_5643_키순서;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, M;
	static int student[][];
	static boolean comp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			init(br);

			for (int i = 1; i <= N; i++) {
				bfs(i);
			}

			int tot = 0;
			for (int i = 1; i <= N; i++) {
				if (student[i][0] + student[i][1] == N - 1)
					tot++;
			}

			answer.append("#" + tc + " " + tot + "\n");
		}
		System.out.println(answer);
	}

	private static void bfs(int target) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(target);
		boolean visit[] = new boolean[N + 1];
		visit[target] = true;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				int cur = que.poll();
				for (int i = 1; i <= N; i++) {
					if (visit[i] || !comp[cur][i])
						continue;
					student[target][0]++;
					student[i][1]++;
					visit[i] = true;
					que.offer(i);
				}
			}
		}
	}

	private static void init(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		student = new int[N + 1][2];
		comp = new boolean[N + 1][N + 1];

		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			comp[a][b] = true;
		}
	}

}
