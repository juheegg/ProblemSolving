package KakaoIntern2020.P67260_동굴탐험;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

	public static boolean solution(int n, int[][] path, int[][] order) {
		boolean answer = true;

		final int GO = -1;

		ArrayList<Integer>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			adj[path[i][0]].add(path[i][1]);
			adj[path[i][1]].add(path[i][0]);
		}

		int[] indgr = new int[n];
		int[] next = new int[n];
		Arrays.fill(next, -1);
		for (int i = 0; i < order.length; i++) {
			indgr[order[i][1]]++;
			next[order[i][0]] = order[i][1];
		}

		Deque<Integer> que = new LinkedList<>();
		boolean[] visit = new boolean[n];
		int cnt = 0;
		if (indgr[0] > 0)
			return false;
		que.add(0);

		while (!que.isEmpty()) {
			int size = que.size();
			int possCnt = size;
			for (int s = 0; s < size; s++) {
				int cur = que.poll();
				if (visit[cur])
					continue;
				if (indgr[cur] > 0) {
					que.add(cur);
					possCnt--;
					continue;
				}
				visit[cur] = true;
				cnt++;
				if (next[cur] > GO)
					indgr[next[cur]]--;
				for (int i = 0; i < adj[cur].size(); i++) {
					int target = adj[cur].get(i);
					if (visit[target])
						continue;
					que.offerFirst(target);
				}
			}
			if (possCnt == 0)
				break;
		}

		return cnt == n;
	}

	public static void main(String[] args) {
		int[][] p = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] o = { { 8, 5 }, { 6, 7 }, { 4, 1 } };
		System.out.println(solution(9, p, o));
	}

}
