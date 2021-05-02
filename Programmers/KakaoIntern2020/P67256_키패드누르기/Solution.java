package KakaoIntern2020.P67256_키패드누르기;

public class Solution {

	// 1 2 3
	// 4 5 6
	// 7 8 9
	// * 0 #

	static String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();

		Hand left = new Hand(3, 0, 'L');
		Hand right = new Hand(3, 2, 'R');

		char h = hand.equals("left") ? 'L' : 'R';

		for (int i = 0; i < numbers.length; i++) {
			int cur = numbers[i];

			switch (cur) {
			case 1:
			case 4:
			case 7:
				answer.append("L");
				left.set(cur / 3, 0);
				break;
			case 3:
			case 6:
			case 9:
				answer.append("R");
				right.set(cur / 3 - 1, 2);
				break;
			case 0:
			case 2:
			case 5:
			case 8:
				int leftDis = getDis(left, cur);
				int rightDis = getDis(right, cur);

				char target = 'L';
				if (leftDis == rightDis && h == 'R') {
					target = 'R';
				} else if (leftDis > rightDis) {
					target = 'R';
				}

				answer.append(target);
				int r = cur == 0 ? 3 : cur / 3;
				if (target == 'L') {
					left.set(r, 1);
				} else {
					right.set(r, 1);
				}

				break;
			}
		}

		return answer.toString();
	}

	private static int getDis(Hand h, int cur) {
		int r = cur == 0 ? 3 : cur / 3;
		int c = 1;
		return Math.abs(h.r - r) + Math.abs(h.c - c);
	}

	static class Hand {
		int r, c;
		char dir;

		public void set(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Hand(int r, int c, char dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

	}

	public static void main(String[] args) {
		int[] n1 = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		System.out.println(solution(n1, "right"));
		int[] n2 = { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 };
		System.out.println(solution(n2, "left"));
		int[] n3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		System.out.println(solution(n3, "right"));
	}

}
