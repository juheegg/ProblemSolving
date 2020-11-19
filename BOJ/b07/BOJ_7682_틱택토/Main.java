package b07.BOJ_7682_틱택토;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static char[] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		board = br.readLine().toCharArray();
		int x = 0, o = 0;
		int xline = 0, oline = 0;
		while (board.length == 9) {
			x = 0;
			o = 0;
			for (int i = 0; i < 9; i++) {
				if (board[i] == 'X')
					x++;
				else if (board[i] == 'O')
					o++;
			}

			xline = success(board, 'X');
			oline = success(board, 'O');

//			System.out.println(xline);
//			System.out.println(oline);
			if (x - 1 == o) {
				if (oline != 0)
					sb.append("invalid\n");
				else if (xline == 0 && x < 5)
					sb.append("invalid\n");
				else
					sb.append("valid\n");
			} else if (x == o) {
				if (oline == 1 && xline == 0)
					sb.append("valid\n");
				else
					sb.append("invalid\n");

			} else {
				sb.append("invalid\n");
			}

			board = br.readLine().toCharArray();
		}

		System.out.println(sb);
	}

	static int[][] line = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };

	private static int success(char[] board, char c) {
		int result = 0;

		boolean flag = true;
		for (int i = 0; i < 8; i++) {
			flag = true;
			for (int j = 0; j < 3; j++) {
				if (board[line[i][j]] != c)
					flag = false;
			}
			if (flag)
				result++;
		}
		return result;
	}

}
