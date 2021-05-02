package KakaoBlindRecruitment2020.P60057_문자열압축;

public class Solution {

	static int solution(String s) {
		int answer = Integer.MAX_VALUE;

		for (int len = 1; len <= s.length(); len++) {
			int idx = 0;
			int cnt = 1;
			String pre = "";
			String cur = "";
			StringBuilder result = new StringBuilder();
//			System.out.println(pre);
			while (idx + len <= s.length()) {
				cur = s.substring(idx, idx + len);
//				System.out.println(pre + "    " + cur);

				if (pre.equals(cur)) {
					cnt++;
				} else {
					if (cnt > 1)
						result.append(cnt);
					result.append(pre);

					cnt = 1;
					pre = cur;
				}

				idx += len;
			}
			if (cnt > 1)
				result.append(cnt);
			result.append(pre);
			if (idx < s.length())
				result.append(s.substring(idx));
//			System.out.println(result);
			answer = Math.min(answer, result.length());
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution("a"));
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
	}

}
