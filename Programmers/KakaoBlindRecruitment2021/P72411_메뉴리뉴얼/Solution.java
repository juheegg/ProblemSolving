package KakaoBlindRecruitment2021.P72411_메뉴리뉴얼;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Solution {

	static public String[] solution(String[] orders, int[] course) {

		HashMap<String, Integer> map = new HashMap<>();
		boolean[] courseCheck = new boolean[11];
		for (int i = 0; i < course.length; i++) {
			courseCheck[course[i]] = true;
		}
		for (int i = 0; i < orders.length; i++) {
			subset(0, "", orders[i], courseCheck, map);
		}

		int[] max = new int[11];
		ArrayList<String>[] list = new ArrayList[11];
		for (int i = 0; i < 11; i++) {
			list[i] = new ArrayList<>();
		}

		Iterator itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			int cnt = map.get(key);
			if (max[key.length()] < cnt) {
				max[key.length()] = cnt;
				list[key.length()].clear();
				list[key.length()].add(key);
			} else if (max[key.length()] == cnt) {
				list[key.length()].add(key);
			}
		}

		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			if (max[i] == 1)
				continue;
			result.addAll(list[i]);
		}
		Collections.sort(result);

		return result.toArray(new String[] {});
	}

	private static void subset(int depth, String select, String tot, boolean[] courseCheck,
			HashMap<String, Integer> map) {
		if (depth == tot.length()) {
			if (!courseCheck[select.length()])
				return;

			select = sort(select);

			if (map.containsKey(select))
				map.replace(select, map.get(select) + 1);
			else
				map.put(select, 1);

			return;
		}
		subset(depth + 1, select + tot.charAt(depth), tot, courseCheck, map);
		subset(depth + 1, select, tot, courseCheck, map);
	}

	private static String sort(String str) {
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//		String[] o = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
//		int[] c = { 2, 3, 4 };
//		String[] o = { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
//		int[] c = { 2, 3, 5 };
		String[] o = { "XYZ", "XWY", "WXA" };
		int[] c = { 2, 3, 4 };
		String[] r = solution(o, c);
		for (String s : r)
			System.out.print(s + "   ");
		System.out.println();
	}

}
