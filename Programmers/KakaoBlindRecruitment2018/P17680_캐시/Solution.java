package KakaoBlindRecruitment2018.P17680_캐시;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static Queue<String> cache;
	static int size;

	static public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		if (cacheSize == 0)
			return cities.length * 5;

		cache = new LinkedList<>();
		size = cacheSize;
		for (int i = 0; i < cities.length; i++) {
			if (find(cities[i].toLowerCase()))
				answer += 1;
			else
				answer += 5;
		}
		return answer;
	}

	private static boolean find(String city) {
		if (cache.contains(city)) {
			cache.remove(city);
			cache.add(city);
			return true;
		}
		if (cache.size() >= size)
			cache.poll();
		cache.add(city);
		return false;
	}

	public static void main(String[] args) {
		String[] c = { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };
		System.out.println(solution(3, c));

	}

}
