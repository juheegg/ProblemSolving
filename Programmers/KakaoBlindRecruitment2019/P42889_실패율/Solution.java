package KakaoBlindRecruitment2019.P42889_실패율;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	static public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];

		int tot = stages.length;
		for (int i = 0; i < tot; i++) {
			if (stages[i] > N)
				continue;
			answer[stages[i] - 1]++;
		}

		double clear = tot;
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			if (clear == 0) {
				pq.add(new Node(i + 1, 0));
				continue;
			}
			pq.add(new Node(i + 1, answer[i] / clear));
			clear -= answer[i];
		}

		for (int i = 0; i < N; i++) {
			answer[i] = pq.poll().no;
		}

		return answer;
	}

	static class Node implements Comparable<Node> {
		int no;
		double fail;

		public Node(int no, double fail) {
			this.no = no;
			this.fail = fail;
		}

		@Override
		public int compareTo(Node o) {
			if (this.fail == o.fail)
				return this.no - o.no;
			return -Double.compare(this.fail, o.fail);
		}

	}

	public static void main(String[] args) {
//		int[] result = solution(5, new int[] { 2, 1, 2, 6, 2, 4, 3, 3 });
//		int[] result = solution(7, new int[] { 2, 1, 2, 6, 2, 4, 3, 3 });
		int[] result = solution(4, new int[] { 4, 4, 4, 4, 4 });
		for (int i : result)
			System.out.print(i + " ");
	}

}
