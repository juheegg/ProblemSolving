package b12.BOJ_12764_싸지방에간준하;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		Queue<User> users = new PriorityQueue<>();
		Queue<Computer> useComs = new PriorityQueue<>(new Comparator<Computer>() {

			@Override
			public int compare(Computer o1, Computer o2) {
				return o1.end - o2.end;
			}
		});
		Queue<Computer> empComs = new PriorityQueue<>(new Comparator<Computer>() {

			@Override
			public int compare(Computer o1, Computer o2) {
				return o1.number - o2.number;
			}
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			users.offer(new User(s, e));
		}

		List<Integer> cnt = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			User cur = users.poll();

			while (!useComs.isEmpty()) {
				if (cur.start >= useComs.peek().end)
					empComs.offer(useComs.poll());
				else
					break;
			}

			if (empComs.isEmpty()) {
				cnt.add(1);
				useComs.offer(new Computer(cur.end, cnt.size() - 1));
			} else {
				Computer curcom = empComs.poll();
				cnt.set(curcom.number, cnt.get(curcom.number) + 1);
				curcom.end = cur.end;
				useComs.offer(curcom);
			}
		}

		System.out.println(cnt.size());
		for (int i : cnt)
			System.out.print(i + " ");
	}

	static class User implements Comparable<User> {
		int start, end;

		public User(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(User o) {
			return this.start - o.start;
		}
	}

	static class Computer {
		int end, number;

		public Computer(int end, int number) {
			super();
			this.end = end;
			this.number = number;
		}

	}
}
