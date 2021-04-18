package b20.BOJ_20161_모노미노도미노2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int answer;
	static boolean map[][];
	static final int size = 10;
	static final int mid = 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine().trim());
		int[][] blocks = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				blocks[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		map = new boolean[size][size];

		for (int i = 0; i < N; i++) {
			green(blocks[i]);
			blue(blocks[i]);
		}

		System.out.println(answer);
		System.out.println(getBlockCnt());
	}

	private static int getBlockCnt() {
		int answer = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j])
					answer++;
			}
		}
		return answer;
	}

	private static void blue(int[] block) {
		moveBlockBlue(block);
		checkScoreAndMoveBlue();
		checkTopBlue();
	}

	private static void moveBlockBlue(int[] block) {
		int t = block[0];
		int x = block[1];
		int y = block[2];

		if (t == 2)
			y++;

		for (int j = y; j < size; j++) {
			if (map[x][j]) {
				y = j - 1;
				break;
			}
			if (j == size - 1)
				y = j;
		}

		if (t == 3) {
			for (int j = block[2]; j < size; j++) {
				if (map[x + 1][j]) {
					y = Math.min(y, j - 1);
					break;
				}
			}
		}

		map[x][y] = true;
		switch (t) {
		case 2:
			map[x][y - 1] = true;
			break;
		case 3:
			map[x + 1][y] = true;
			break;
		}
	}

	private static void checkScoreAndMoveBlue() {
		for (int j = size - 1; j > size - 5; j--) {
			boolean flag = true;
			for (int i = 0; i < 4; i++) {
				if (!map[i][j])
					flag = false;
			}
			if (flag) {
				answer++;
				moveBlueTo(j++, 1);
			}
		}
	}

	private static void checkTopBlue() {
		int cnt = 0;
		for (int j = mid; j < mid + 2; j++) {
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				if (map[i][j])
					flag = true;
			}
			if (flag)
				cnt++;
		}
		if (cnt > 0)
			moveBlueTo(size - 1, cnt);
		for (int j = 0; j < cnt; j++) {
			for (int i = 0; i < 4; i++) {
				map[i][5 - j] = false;
			}
		}
	}

	private static void moveBlueTo(int target, int cnt) {
		for (int j = target; j >= mid + cnt; j--) {
			for (int i = 0; i < 4; i++) {
				map[i][j] = map[i][j - cnt];
			}
		}
	}

	private static void green(int[] block) {
		moveBlockGreen(block);
		checkScoreAndMoveGreen();
		checkTopGreen();
	}

	private static void moveBlockGreen(int[] block) {
		int t = block[0];
		int x = block[1];
		int y = block[2];

		if (t == 3)
			x++;

		for (int i = x; i < size; i++) {
			if (map[i][y]) {
				x = i - 1;
				break;
			}
			if (i == size - 1)
				x = i;
		}

		if (t == 2) {
			for (int i = block[1]; i < size; i++) {
				if (map[i][y + 1]) {
					x = Math.min(x, i - 1);
					break;
				}
			}
		}

		map[x][y] = true;
		switch (t) {
		case 2:
			map[x][y + 1] = true;
			break;
		case 3:
			map[x - 1][y] = true;
			break;
		}

	}

	private static void checkScoreAndMoveGreen() {
		for (int i = size - 1; i > size - 5; i--) {
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (!map[i][j])
					flag = false;
			}
			if (flag) {
				answer++;
				moveGreenTo(i++, 1);
			}
		}
	}

	private static void checkTopGreen() {
		int cnt = 0;
		for (int i = mid; i < mid + 2; i++) {
			boolean flag = false;
			for (int j = 0; j < 4; j++) {
				if (map[i][j])
					flag = true;
			}
			if (flag)
				cnt++;
		}

		if (cnt > 0)
			moveGreenTo(size - 1, cnt);
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < 4; j++) {
				map[5 - i][j] = false;
			}
		}
	}

	private static void moveGreenTo(int target, int cnt) {
		for (int i = target; i >= mid + cnt; i--) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = map[i - cnt][j];
			}
		}
	}

}
