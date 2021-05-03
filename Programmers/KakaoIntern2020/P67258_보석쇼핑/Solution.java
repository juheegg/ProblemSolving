package KakaoIntern2020.P67258_보석쇼핑;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

	static public int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}

		int min = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		HashMap<String, Integer> map = new HashMap<>();
		while (start < gems.length) {
			if (set.size() == map.size()) {
				if (min > end - start) {
					min = end - start;
					answer = new int[] { start + 1, end };
				}
				remove(map, gems[start++]);
			} else {
				if (end >= gems.length)
					break;
				add(map, gems[end++]);
			}
		}

		return answer;
	}

	private static void add(HashMap<String, Integer> map, String gem) {
		if (map.containsKey(gem)) {
			map.replace(gem, map.get(gem) + 1);
		} else {
			map.put(gem, 1);
		}
	}

	private static void remove(HashMap<String, Integer> map, String gem) {
		if (map.get(gem) == 1) {
			map.remove(gem);
		} else {
			map.replace(gem, map.get(gem) - 1);
		}
	}

	public static void main(String[] args) {
		String[] g = { "ZZZ", "YYY", "NNNN", "YYY", "BBB" };
		int[] r = solution(g);
		System.out.println(r[0] + " " + r[1]);
	}

}
