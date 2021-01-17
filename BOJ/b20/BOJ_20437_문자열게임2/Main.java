package b20.BOJ_20437_문자열게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			int n = Integer.parseInt(br.readLine());

			List<Integer>[] list = new ArrayList[26];
			for (int j = 0; j < list.length; j++) {
				list[j] = new ArrayList<>();
			}

			for (int j = 0; j < str.length(); j++) {
				char cur = str.charAt(j);
				list[cur - 'a'].add(j);
			}

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < 26; j++) {
				if (list[j].size() < n)
					continue;
				for (int k = 0; k <= list[j].size() - n; k++) {
					int cur = list[j].get(k + n - 1) - list[j].get(k) + 1;
					min = Math.min(min, cur);
					max = Math.max(max, cur);
				}
			}

			if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE)
				answer.append("-1\n");
			else
				answer.append(min + " " + max + "\n");

		}
		System.out.println(answer);
	}

}
