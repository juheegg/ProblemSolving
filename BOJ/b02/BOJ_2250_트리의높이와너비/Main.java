package b02.BOJ_2250_트리의높이와너비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static List<Node> tree;
	static Map<Integer, List<Integer>> list;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine().trim());
		tree = new ArrayList<>();
		list = new HashMap<>();
		cnt = 1;

		boolean[] root = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			tree.add(new Node());
			root[i] = true;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			tree.get(n).setChild(l, r);
			if (l != -1)
				root[l] = false;
			if (r != -1)
				root[r] = false;
		}

		for (int i = 1; i <= N; i++)
			if (root[i])
				dfs(tree.get(i), 1);

		int maxlevel = 1;
		int maxwidth = 1;

		for (int i = 1; i <= list.size(); i++) {
			List<Integer> cur = list.get(i);
			int tmp = cur.get(cur.size() - 1) - cur.get(0) + 1;
			if (maxwidth < tmp) {
				maxlevel = i;
				maxwidth = tmp;
			}
		}

		System.out.println(maxlevel + " " + maxwidth);
	}

	private static void dfs(Node cur, int level) {
		if (cur.left != -1)
			dfs(tree.get(cur.left), level + 1);

		cur.col = cnt++;
		if (!list.containsKey(level))
			list.put(level, new ArrayList<>());
		list.get(level).add(cur.col);

		if (cur.right != -1)
			dfs(tree.get(cur.right), level + 1);
	}

	static class Node {
		int left, right;
		int col;

		void setChild(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

}
