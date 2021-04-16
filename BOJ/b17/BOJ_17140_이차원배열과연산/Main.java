package b17.BOJ_17140_이차원배열과연산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int rCnt, cCnt, arr[][];
	static final int maxSize = 100;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()) - 1;
		int C = Integer.parseInt(st.nextToken()) - 1;
		int K = Integer.parseInt(st.nextToken());

		rCnt = 3;
		cCnt = 3;
		arr = new int[maxSize][maxSize];

		for (int i = 0; i < rCnt; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < cCnt; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = -1;
		while (++time <= maxSize) {
			if (arr[R][C] == K)
				break;
			if (rCnt >= cCnt)
				rSort();
			else
				cSort();

//			System.out.println();
//			System.out.println(rCnt + " " + cCnt);
//			for (int i = 0; i < rCnt; i++) {
//				for (int j = 0; j < cCnt; j++) {
//					System.out.print(arr[i][j] + "\t");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		System.out.println(time <= maxSize ? time : -1);

	}

	private static void rSort() {
		Map<Integer, Integer> map = new HashMap<>();
		Queue<Element> que = new PriorityQueue<>();
		int[][] tmp = new int[maxSize][maxSize];
		int maxC = 0;

		for (int r = 0; r < rCnt; r++) {
			map.clear();
			for (int c = 0; c < cCnt; c++) {
				int cur = arr[r][c];
				if (cur == 0)
					continue;
				if (map.containsKey(cur))
					map.replace(cur, map.get(cur) + 1);
				else
					map.put(cur, 1);
			}

			que.clear();
			List<Integer> keys = new ArrayList<>(map.keySet());
			for (Integer key : keys) {
				que.offer(new Element(key, map.get(key)));
			}

			int pointer = 0;
			while (!que.isEmpty()) {
				Element cur = que.poll();
				tmp[r][pointer++] = cur.num;
				tmp[r][pointer++] = cur.cnt;
				if (pointer >= maxSize)
					break;
			}
			maxC = Math.max(maxC, pointer);
		}
		arr = tmp;
		cCnt = maxC;
	}

	private static void cSort() {
		Map<Integer, Integer> map = new HashMap<>();
		Queue<Element> que = new PriorityQueue<>();
		int[][] tmp = new int[maxSize][maxSize];
		int maxR = 0;

		for (int c = 0; c < cCnt; c++) {
			map.clear();
			for (int r = 0; r < rCnt; r++) {
				int cur = arr[r][c];
				if (cur == 0)
					continue;
				if (map.containsKey(cur))
					map.replace(cur, map.get(cur) + 1);
				else
					map.put(cur, 1);
			}

			que.clear();
			List<Integer> keys = new ArrayList<>(map.keySet());
			for (Integer key : keys) {
				que.offer(new Element(key, map.get(key)));
			}

			int pointer = 0;
			while (!que.isEmpty()) {
				Element cur = que.poll();
				tmp[pointer++][c] = cur.num;
				tmp[pointer++][c] = cur.cnt;
				if (pointer >= maxSize)
					break;
			}
			maxR = Math.max(maxR, pointer);
		}
		arr = tmp;
		rCnt = maxR;

	}

	static class Element implements Comparable<Element> {
		int num, cnt;

		public Element(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Element o) {
			if (this.cnt == o.cnt)
				return this.num - o.num;
			return this.cnt - o.cnt;
		}

	}

}
