package CT.NHN2020;

import java.util.Scanner;

public class test02 {

	static int w, wall[];

	private static void solution(int day, int width, int[][] blocks) {
		w = width;
		wall = new int[width];
		int answer = 0;

		for (int d = 0; d < day; d++) {
			for (int i = 0; i < width; i++) {
				wall[i] += blocks[d][i];
			}
			answer += calcCement();
		}

		System.out.println(answer);
	}

	private static int calcCement() {
//		System.out.println(Arrays.toString(wall));

		int tmp[] = new int[w];
		System.arraycopy(wall, 0, tmp, 0, w);

		int max = 0;
		int top = 0;
		for (int i = 0; i < w; i++) {
			if (max < tmp[i]) {
				max = tmp[i];
				top = i;
			}
			tmp[i] = max;
		}

		if (top != w - 1) {
			max = 0;
			for (int i = w - 1; i > top; i--) {
				if (max < wall[i]) {
					max = wall[i];
				}
				tmp[i] = max;
			}
		}

		int cement = 0;
		for (int i = 0; i < w; i++) {
			cement += tmp[i] - wall[i];
		}

//		System.out.println(Arrays.toString(tmp));

//		System.out.println(cement);

		wall = tmp;

		return cement;
	}

	private static class InputData {
		int day;
		int width;
		int[][] blocks;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.day = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
			inputData.width = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.blocks = new int[inputData.day][inputData.width];
			for (int i = 0; i < inputData.day; i++) {
				String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
				for (int j = 0; j < inputData.width; j++) {
					inputData.blocks[i][j] = Integer.parseInt(buf[j]);
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.day, inputData.width, inputData.blocks);
	}
}
