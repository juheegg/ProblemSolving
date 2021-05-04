package KakaoIntern2019.P64063_호텔방배정;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

	static long[] solution(long k, long[] room_number) {
		int N = room_number.length;
		long[] answer = new long[N];
		HashMap<Long, Long> parents = new HashMap<>();

		for (int i = 0; i < N; i++) {
			long src = room_number[i];
			ArrayList<Long> child = new ArrayList<>();
			child.add(src);
			while (parents.containsKey(src)) {
				src = parents.get(src);
				child.add(src);
			}
			answer[i] = src;
			for (long l : child) {
				parents.put(l, src + 1);
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		long[] room = { 1, 3, 4, 1, 3, 1 };
		long[] r = solution(10, room);
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i]);
		}
	}

}
