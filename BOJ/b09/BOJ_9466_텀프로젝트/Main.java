package b09.BOJ_9466_텀프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

	static int T, N, student[];
	static int depth[];
	static boolean curGroup[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			student = new int[N + 1];
			depth = new int[N + 1];
			curGroup = new boolean[N + 1];
			int tot = N;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				if (depth[i] > 0)
					continue;
				tot -= dfs(i);
			}

			answer.append(tot + "\n");
		}

		System.out.println(answer);
	}

	private static int dfs(int cur) {

		int step = 1;
		Arrays.fill(curGroup, false);

		while (cur > 0) {
			if (curGroup[cur])
				return step - depth[cur];
			if (depth[cur] > 0)
				return 0;
			depth[cur] = step++;
			curGroup[cur] = true;
			cur = student[cur];
		}

		return 0;
	}
}
