package b17.BOJ_17837_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static final int RED = 1, BLUE = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		List<Node> nodes = new ArrayList<>();
		List<Integer>[][] mapList = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapList[i][j] = new ArrayList<>();
			}
		}
		nodes.add(new Node());
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			nodes.add(new Node(r, c, d));
			mapList[r][c].add(i);
		}

		int time = 0;
		while (time++ < 1000) {
			boolean endFlag = false;
			for (int i = 1; i <= K; i++) {
				Node cur = nodes.get(i);
				int nr = cur.r + dr[cur.d];
				int nc = cur.c + dc[cur.d];
				int d = cur.d;
				if (!check(nr, nc) || map[nr][nc] == BLUE) {
					d = d % 2 == 1 ? d + 1 : d - 1;
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
				}
				cur.d = d;
				if (!check(nr, nc) || map[nr][nc] == BLUE) 
					continue;
				else if (map[nr][nc] == RED) {
					List<Integer> src = mapList[cur.r][cur.c];
					List<Integer> dest = mapList[nr][nc];
					int size = src.size();
					for (int s = size - 1; s >= 0; s--) {
						int no = src.get(s);
						nodes.get(no).r = nr;
						nodes.get(no).c = nc;
						dest.add(no);
						src.remove(s);
						if (dest.size() >= 4)
							endFlag = true;
						if (no == i)
							break;
					}
				} else {
					List<Integer> src = mapList[cur.r][cur.c];
					List<Integer> dest = mapList[nr][nc];
					boolean flag = false;
					int size = src.size();
					for (int s = 0; s < size; s++) {
						int no = src.get(s);
						if (no == i)
							flag = true;
						if (flag) {
							nodes.get(no).r = nr;
							nodes.get(no).c = nc;
							dest.add(no);
							src.remove(s);
							if (dest.size() >= 4)
								endFlag = true;
							if (src.size() <= s--)
								break;
						}

					}
				}
			}
			if (endFlag)
				break;
		}
		System.out.println(time > 1000 ? -1 : time);
	}

	private static boolean check(int r, int c) {
		if (r > 0 && r <= N && c > 0 && c <= N)
			return true;
		return false;
	}

	static class Node {
		int r, c, d;

		public Node() {
		};

		public Node(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

}
