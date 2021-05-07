package KakaoBlindRecruitment2021.P72412_순위검색;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	static public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		HashMap<String, ArrayList<Integer>> map = new HashMap<>();

		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i]);
			String[] inform = new String[4];
			for (int j = 0; j < 4; j++) {
				inform[j] = st.nextToken();
			}
			int score = Integer.parseInt(st.nextToken());
			add(map, inform, score);
		}

		for (int i = 0; i < query.length; i++) {
			StringTokenizer st = new StringTokenizer(query[i]);
			String q = "";
			for (int j = 0; j < 7; j++) {
				String cur = st.nextToken();
				if (cur.equals("and"))
					continue;
				q += cur;
			}
			int score = Integer.parseInt(st.nextToken());
			if (!map.containsKey(q)) {
				answer[i] = 0;
				continue;
			}
			answer[i] = map.get(q).size() - lowerBound(map.get(q), score);
		}

		return answer;
	}

	private static int lowerBound(ArrayList<Integer> list, int score) {
		Collections.sort(list);

		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = (left + right) / 2;
			if (list.get(mid) < score) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	private static void add(HashMap<String, ArrayList<Integer>> map, String[] inform, int score) {
		check("-" + "-" + "-" + "-", score, map);
		check("-" + "-" + "-" + inform[3], score, map);
		check("-" + "-" + inform[2] + "-", score, map);
		check("-" + "-" + inform[2] + inform[3], score, map);
		check("-" + inform[1] + "-" + "-", score, map);
		check("-" + inform[1] + "-" + inform[3], score, map);
		check("-" + inform[1] + inform[2] + "-", score, map);
		check("-" + inform[1] + inform[2] + inform[3], score, map);
		check(inform[0] + "-" + "-" + "-", score, map);
		check(inform[0] + "-" + "-" + inform[3], score, map);
		check(inform[0] + "-" + inform[2] + "-", score, map);
		check(inform[0] + "-" + inform[2] + inform[3], score, map);
		check(inform[0] + inform[1] + "-" + "-", score, map);
		check(inform[0] + inform[1] + "-" + inform[3], score, map);
		check(inform[0] + inform[1] + inform[2] + "-", score, map);
		check(inform[0] + inform[1] + inform[2] + inform[3], score, map);

	}

	private static void check(String str, int score, HashMap<String, ArrayList<Integer>> map) {
		if (!map.containsKey(str))
			map.put(str, new ArrayList<>());
		map.get(str).add(score);
	}

	public static void main(String[] args) {
		String[] i = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] q = { "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250", "- and backend and senior and - 150",
				"- and - and - and chicken 100", "- and - and - and - 150" };
		int[] r = solution(i, q);
		for (int n : r)
			System.out.println(n);
	}

}
