package b13.BOJ_13335_트럭;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] trucks = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		int time = 0;
		int totWeight = 0;
		int pointer = 0;
		Queue<Truck> que = new LinkedList<>();
		while (true) {
			time++;
			if (que.size() == W) {
				if (que.peek().no == N - 1)
					break;
				totWeight -= que.poll().w;
			}
			int cur = 0;
			if (pointer < N && totWeight + trucks[pointer] <= L)
				cur = trucks[pointer++];
			que.add(new Truck(cur, cur == 0 ? 0 : pointer - 1));
			totWeight += cur;

		}

		System.out.println(time);
	}

	static class Truck {
		int w, no;

		public Truck(int w, int no) {
			super();
			this.w = w;
			this.no = no;
		}

	}

}
