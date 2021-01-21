package b01.BOJ_1931_회의실배정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int start;
		int end;

		public Node() {
		}

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if (end == o.end)
				return start - o.start;
			return end - o.end;
		}

	}

	static int N;
	static Node meeting[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		meeting = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meeting[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(meeting);

		int last = meeting[0].end;
		int answer = 1;
		for (int i = 1; i < N; i++) {
			if (meeting[i].start < last)
				continue;
			answer++;
			last = meeting[i].end;
		}
		System.out.println(answer);

	}

}