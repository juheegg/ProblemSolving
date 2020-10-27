package CT.NHN2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class example01 {

	static int N, m[][];

	private static void solution(int sizeOfMatrix, int[][] matrix) {
		N = sizeOfMatrix;
		m = matrix;

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < sizeOfMatrix; i++) {
			for (int j = 0; j < sizeOfMatrix; j++) {
				if (matrix[i][j] == 1)
					list.add(bfs(i, j));
			}
		}
		System.out.println(list.size());
		if (list.size() != 0) {
			Collections.sort(list);
			for (int i : list)
				System.out.print(i + " ");
		}

	}

	private static int bfs(int r, int c) {
		int cnt = 0;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		m[r][c] = -1;
		cnt++;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				r = que.peek()[0];
				c = que.poll()[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc) || m[nr][nc] != 1)
						continue;

					que.add(new int[] { nr, nc });
					m[nr][nc] = -1;
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N)
			return true;
		return false;
	}

	private static class InputData {
		int sizeOfMatrix;
		int[][] matrix;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
			for (int i = 0; i < inputData.sizeOfMatrix; i++) {
				String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
				for (int j = 0; j < inputData.sizeOfMatrix; j++) {
					inputData.matrix[i][j] = Integer.parseInt(buf[j]);
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.sizeOfMatrix, inputData.matrix);
	}
}