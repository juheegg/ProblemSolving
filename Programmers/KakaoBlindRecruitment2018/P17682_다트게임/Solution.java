package KakaoBlindRecruitment2018.P17682_다트게임;

import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static public int solution(String dartResult) {
		int answer = 0;
		StringTokenizer st = new StringTokenizer(dartResult, "SDT*#", true);
		Stack<Integer> score = new Stack<>();

		while (st.hasMoreTokens()) {
			String cur = st.nextToken();
			char c = cur.charAt(0);
			if (c >= '0' && c <= '9') {
				score.add(Integer.parseInt(cur));
			} else if (c == 'D') {
				score.push((int) Math.pow(score.pop(), 2));
			} else if (c == 'T') {
				score.push((int) Math.pow(score.pop(), 3));
			} else if (c == '*') {
				int curScore = score.pop();
				if (!score.isEmpty()) {
					int beforeScore = score.pop();
					score.push(beforeScore * 2);
				}
				score.push(curScore * 2);
			} else if (c == '#') {
				score.push(score.pop() * -1);
			}
		}

		while (!score.isEmpty())
			answer += score.pop();

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
	}

}
