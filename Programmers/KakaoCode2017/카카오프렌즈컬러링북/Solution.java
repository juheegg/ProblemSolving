package KakaoCode2017.카카오프렌즈컬러링북;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int M, N;
	static final int NOT = 0, VISIT = -1;

	static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		M = m;
		N = n;

		int[][] copy = new int[m][n];
		for (int k = 0; k < picture.length; k++) {
			System.arraycopy(picture[k], 0, copy[k], 0, n);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (copy[i][j] == NOT || copy[i][j] == VISIT)
					continue;
				numberOfArea++;
				int size = getSize(i, j, copy);
				maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private static int getSize(int i, int j, int[][] picture) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { i, j });
		int curColor = picture[i][j];
		picture[i][j] = VISIT;

		int cnt = 1;
		while (!que.isEmpty()) {
			int queSize = que.size();
			for (int s = 0; s < queSize; s++) {
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || picture[nr][nc] == NOT || picture[nr][nc] == VISIT)
						continue;
					if (curColor != picture[nr][nc])
						continue;
					cnt++;
					picture[nr][nc] = VISIT;
					que.add(new int[] { nr, nc });
				}
			}
		}
		return cnt;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < M && c >= 0 && c < N)
			return true;
		return false;
	}

	public static void main(String[] args) {
//		int[][] p = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 }, { 0, 0, 0, 3 } };
//		int[] a = solution(6, 4, p);
		int[][] p = { { 0, 1, 0 }, { 1, 1, 0 }, { 0, 0, 0 } };
		int[] a = solution(3, 3, p);
		System.out.println(a[0] + " " + a[1]);
	}

}
