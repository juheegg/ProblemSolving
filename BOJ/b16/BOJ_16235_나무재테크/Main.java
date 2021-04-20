package b16.BOJ_16235_나무재테크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, map[][], s2d2[][], dead[][];
	static List<Tree> trees;

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static final boolean SUMMER = true;
	static final boolean WINTER = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		s2d2 = new int[N + 1][N + 1];
		dead = new int[N + 1][N + 1];
		trees = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = 5;
				s2d2[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x, y, age));
		}

		for (int i = 0; i < K; i++) {
			spring();
			add(SUMMER);
			fall();
			add(WINTER);
		}
		System.out.println(trees.size());
	}

	private static void spring() {
		Collections.sort(trees);
		List<Tree> tmp = new ArrayList<>();
		dead = new int[N + 1][N + 1];
		int size = trees.size();
		for (int i = 0; i < size; i++) {
			Tree cur = trees.get(i);
			if (map[cur.r][cur.c] >= cur.age) {
				map[cur.r][cur.c] -= cur.age;
				cur.age++;
				tmp.add(cur);
			} else {
				dead[cur.r][cur.c] += cur.age / 2;
			}
		}
		trees = tmp;
	}

	private static void fall() {
		int size = trees.size();
		for (int i = 0; i < size; i++) {
			Tree cur = trees.get(i);
			if (cur.age >= 5 && cur.age % 5 == 0) {
				for (int d = 0; d < 8; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (!check(nr, nc))
						continue;
					trees.add(new Tree(nr, nc, 1));
				}
			}
		}
	}

	private static void add(boolean flag) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += flag ? dead[i][j] : s2d2[i][j];
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r > 0 && r <= N && c > 0 && c <= N)
			return true;
		return false;
	}

	static class Tree implements Comparable<Tree> {

		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}

	}

}
