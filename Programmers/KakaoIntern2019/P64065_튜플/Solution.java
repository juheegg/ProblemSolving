package KakaoIntern2019.P64065_튜플;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static public int[] solution(String s) {
		int[] answer = {};

		s = s.replace("{{", "");
		s = s.replace("}}", "");

		String[] tuple = s.split("\\},\\{");

		Arrays.sort(tuple, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		answer = new int[tuple.length];
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = null;
		for (int i = 0; i < tuple.length; i++) {
			st = new StringTokenizer(tuple[i], ",");
			while (st.hasMoreTokens()) {
				int cur = Integer.parseInt(st.nextToken());
				if (set.contains(cur))
					continue;
				answer[i] = cur;
				set.add(cur);
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] a1 = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		for (int i : a1) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
