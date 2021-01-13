package b03.BOJ_3865_학회원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = 0;
		StringBuilder answer = new StringBuilder();

		while ((N = Integer.parseInt(br.readLine())) != 0) {
			Map<String, String> group = new HashMap<>();
			Set<String> member = new HashSet<>();
			Set<String> checkedGroup = new HashSet<>();

			st = new StringTokenizer(br.readLine(), ":");
			st.nextToken();
			String line = st.nextToken();

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine(), ":");
				group.put(st.nextToken(), st.nextToken());
			}

			Queue<String> que = new LinkedList<>();

			st = new StringTokenizer(line, ",.");
			while (st.hasMoreTokens())
				que.offer(st.nextToken());

			while (!que.isEmpty()) {
				String cur = que.poll();
				if (checkedGroup.contains(cur))
					continue;
				if (!group.containsKey(cur))
					member.add(cur);
				else {
					st = new StringTokenizer(group.get(cur), ",.");
					while (st.hasMoreTokens())
						que.offer(st.nextToken());
					checkedGroup.add(cur);
				}
			}

			answer.append(member.size() + "\n");
		}

		System.out.println(answer);
	}

}
