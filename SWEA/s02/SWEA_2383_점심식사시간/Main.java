package s02.SWEA_2383_점심식사시간;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T, N, answer, select[], map[][];
	static List<Node> entrance, people;

	static final int PEO = 1, ENT = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			entrance = new ArrayList<>();
			people = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == PEO)
						people.add(new Node(i, j));
					else if (map[i][j] > PEO)
						entrance.add(new Node(i, j));
				}
			}
			select = new int[people.size()];
			dfs(0);

			result.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(result);
	}

	private static void dfs(int depth) {
		if (depth == people.size()) {
			int[][] use = new int[entrance.size()][100];
			for (int eidx = 0; eidx < entrance.size(); eidx++) {
				List<Distance> list = new ArrayList<>();
				for (int pidx = 0; pidx < people.size(); pidx++) {
					if (select[pidx] != eidx)
						continue;
					int dis = getDis(entrance.get(eidx), people.get(pidx));
					list.add(new Distance(pidx, dis));
				}
				Collections.sort(list);
				int time = 0;
				for (int i = 0; i < list.size(); i++) {
					Distance cur = list.get(i);
					Node entNode = entrance.get(eidx);
					int start = Math.max(time, cur.dis + 1);
					int end = start + map[entNode.r][entNode.c];
					for (int t = start; t < end; t++) {
						use[eidx][t]++;
						if (use[eidx][t] == 3)
							time = t + 1;
					}
				}
			}
			int max = 0;
			for (int i = 0; i < use.length; i++) {
				for (int j = 0; j < use[i].length; j++) {
					if (use[i][j] != 0)
						max = Math.max(max, j);
				}
			}
//			System.out.println(max + 1);
//			if (max == 7)
//				System.out.println();
			answer = Math.min(answer, max + 1);
			return;
		}
		for (int i = 0; i < entrance.size(); i++) {
			select[depth] = i;
			dfs(depth + 1);
		}
	}

	private static int getDis(Node a, Node b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	static class Distance implements Comparable<Distance> {
		int no, dis;

		public Distance(int no, int dis) {
			super();
			this.no = no;
			this.dis = dis;
		}

		@Override
		public String toString() {
			return "People [no=" + no + ", dis=" + dis + "]";
		}

		@Override
		public int compareTo(Distance o) {
			return this.dis - o.dis;
		}

	}

}
