package b15.BOJ_15961_회전초밥;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, d, k, c, cho[];
	static int visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cho = new int[2 * n - 1];
		visited = new int[d + 1];

		for (int i = 0; i < n; i++)
			cho[i] = Integer.parseInt(br.readLine());
		for (int i = n; i < 2 * n - 1; i++)
			cho[i] = cho[i - n];

		int answer = 0;

		int cnt = 1;
		visited[c]++;
		for (int i = 0; i < k; i++) {
			if (visited[cho[i]] == 0)
				cnt++;
			visited[cho[i]]++;
		}

		int front = 0;
		int rear = k;
		while (rear < 2 * n - 1) {
			if (visited[cho[front]] == 1)
				cnt--;
			visited[cho[front++]]--;
			if (visited[cho[rear]] == 0)
				cnt++;
			visited[cho[rear++]]++;

			answer = Math.max(cnt, answer);
		}

		System.out.println(answer);
	}

}