package KakaoIntern2019.P64061_크레인인형뽑기게임;

import java.util.Stack;

public class Solution {

	static int solution(int[][] board, int[] moves) {
		int answer = 0;

		final int N = board.length;

		Stack<Integer>[] stacks = new Stack[N];
		Stack<Integer> basket = new Stack<>();
		for (int i = 0; i < N; i++) {
			stacks[i] = new Stack<>();
		}

		for (int c = 0; c < N; c++) {
			for (int r = N - 1; r >= 0; r--) {
				if (board[r][c] == 0)
					break;
				stacks[c].push(board[r][c]);
			}
		}

		for (int i = 0; i < moves.length; i++) {
			int target = moves[i] - 1;
			if (stacks[target].isEmpty())
				continue;
			int cur = stacks[target].pop();
			if (!basket.isEmpty() && basket.peek() == cur) {
				basket.pop();
				answer += 2;
			} else {
				basket.push(cur);
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(1);
		int[][] b = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } };
		int[] m = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(123);
		System.out.println(solution(b, m));

	}

}
