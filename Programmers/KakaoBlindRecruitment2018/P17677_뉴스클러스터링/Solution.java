package KakaoBlindRecruitment2018.P17677_뉴스클러스터링;

import java.util.HashMap;
import java.util.Iterator;

public class Solution {

	static public int solution(String str1, String str2) {
		HashMap<String, Integer> map1 = getMap(str1.toLowerCase());
		HashMap<String, Integer> map2 = getMap(str2.toLowerCase());

		if(map1.size() == 0 && map2.size() == 0)
			return 1;
		
		int intersection = getIntersection(map1, map2);
		int union = getUnion(map1, map2);
		
		return (int) ((double) intersection / union * 65536);
	}

	private static int getUnion(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
		int result = 0;

		Iterator<String> itr = map1.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (map2.containsKey(key)) {
				result += Math.max(map1.get(key), map2.get(key));
			} else {
				result += map1.get(key);
			}
		}

		itr = map2.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (map1.containsKey(key))
				continue;
			result += map2.get(key);
		}

		return result;
	}

	private static int getIntersection(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
		int result = 0;

		Iterator<String> itr = map1.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (!map2.containsKey(key))
				continue;
			result += Math.min(map1.get(key), map2.get(key));
		}
		return result;
	}

	static private HashMap<String, Integer> getMap(String s) {
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length() - 1; i++) {
			if (!isLetter(s.charAt(i)) || !isLetter(s.charAt(i + 1)))
				continue;
			String cur = s.substring(i, i + 2);
			if (map.containsKey(cur)) {
				map.put(cur, map.get(cur) + 1);
			} else
				map.put(cur, 1);
		}
		return map;
	}

	static private boolean isLetter(char c) {
		if (c >= 'a' && c <= 'z')
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
	}

}
