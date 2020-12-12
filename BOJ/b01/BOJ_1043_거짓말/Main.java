package b01.BOJ_1043_거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Queue<Integer> que = new LinkedList<>();
		int[] party = new int[M];
		List<Integer> adj[] = new ArrayList[N+1];
		boolean[] visit = new boolean[N+1];
		boolean[] truth = new boolean[N+1];

		st = new StringTokenizer(br.readLine());
		int truthN = Integer.parseInt(st.nextToken());
		for (int i = 0; i < truthN; i++) {
			int x = Integer.parseInt(st.nextToken());
			que.add(x);
			visit[x] = true;
			truth[x] = true;
		}

		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int curn = Integer.parseInt(st.nextToken());
			int[] cur = new int[curn];
			for (int j = 0; j < curn; j++) {
				cur[j] = Integer.parseInt(st.nextToken());
			}
			party[i] = cur[0];

			for (int j = 0; j < curn - 1; j++) {
				for (int k = j + 1; k < curn; k++) {
					adj[cur[j]].add(cur[k]);
					adj[cur[k]].add(cur[j]);
				}
			}
		}
		
		while(!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				int cur = que.poll();
				for (int i = 0; i < adj[cur].size(); i++) {
					int target = adj[cur].get(i);
					if(visit[target])
						continue;
					que.add(target);
					visit[target] = true;
					truth[target] = true;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < M; i++) {
			if(!truth[party[i]])
				answer++;
		}
		System.out.println(answer);
	}

}
