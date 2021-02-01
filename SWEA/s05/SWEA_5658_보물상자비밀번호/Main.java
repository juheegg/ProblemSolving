package s05.SWEA_5658_보물상자비밀번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String line = br.readLine();
			line += line;

			HashSet<Integer> set = new HashSet<>();

			for (int front = 0; front < N; front++) {
				int rear = front + N / 4 - 1;
				int index = 1;
				int pw = 0;
				for (int i = rear; i >= front; i--) {
					char tmp = line.charAt(i);
					int x = 0;
					if (tmp >= 'A' && tmp <= 'F')
						x = tmp - 65 + 10;
					else
						x = Integer.parseInt(tmp + "");

					pw += x * index;
					index *= 16;
				}
				set.add(pw);
			}

			List<Integer> list = new ArrayList<>(set);
			list.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			answer.append("#" + tc + " " + list.get(K-1) + "\n");
		}
		System.out.println(answer);
	}

}
