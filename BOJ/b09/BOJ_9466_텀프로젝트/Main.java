package b09.BOJ_9466_텀프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, student[];
	static int depth[];
	static boolean curgroup[];
	static Queue<Integer> que;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		que = new LinkedList<>();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			student = new int[N + 1];
			depth = new int[N + 1];
			curgroup = new boolean[N + 1];
			int tot = N;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				if (depth[i] > 0)
					continue;

				que.clear();
				que.offer(i);

				curgroup = new boolean[N + 1];
				curgroup[i] = true;

				int cnt = 1;
				depth[i] = cnt++;

				while (!que.isEmpty()) {
					int cur = que.poll();
					if (curgroup[student[cur]]) {
						tot -= cnt - depth[student[cur]];
						break;
					}
					if (depth[student[cur]] > 0)
						break;
					que.offer(student[cur]);
					curgroup[student[cur]] = true;
					depth[student[cur]] = cnt++;
				}
			}
			answer.append(tot + "\n");
		}
		System.out.println(answer);
	}

}
