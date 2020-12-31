package b01.BOJ_1261_알고스팟;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int r;
		int c;

		public Node() {
		}

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Node other = (Node) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	static int M, N, map[][], min[][];
	static boolean visited[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		min = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(min[i], N * M);
		}

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		Deque<Node> que = new LinkedList<>();

		que.offer(new Node(0, 0));
		min[0][0] = 0;

		while (!que.isEmpty()) {

			int r = que.peek().r;
			int c = que.poll().c;

//				System.out.println("visited: " + r + " " + c);
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
//					System.out.print(nr + ", " + nc);
				if (!check(nr, nc)) {
//						System.out.println("바운더리x");
					continue;
				}
				if (visited[nr][nc]) {
//						System.out.println("통과x");
					continue;
				}
//					System.out.println();
				Node nNode = new Node(nr, nc);
				visited[nr][nc] = true;
				if (map[nr][nc] == 0) {
					min[nr][nc] = Math.min(min[nr][nc], min[r][c]);
					que.addFirst(nNode);
				} else {
					min[nr][nc] = Math.min(min[nr][nc], min[r][c] + 1);
					que.add(nNode);
				}
			}
//			print();
		}
		System.out.println(min[N - 1][M - 1]);
	}

	static void print() {
		for (int[] m : min) {
			for (int i : m) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}
}
