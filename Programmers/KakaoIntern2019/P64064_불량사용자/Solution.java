package KakaoIntern2019.P64064_불량사용자;

import java.util.HashSet;

public class Solution {

	static HashSet<String> set;

	static public int solution(String[] user_id, String[] banned_id) {
		int answer = 0;

		boolean[] use = new boolean[user_id.length];
		set = new HashSet<>();
		answer = dfs(0, user_id, banned_id, use);

		return answer;
	}

	private static int dfs(int depth, String[] user_id, String[] banned_id, boolean[] use) {
		if (depth == banned_id.length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < use.length; i++) {
				if (use[i])
					sb.append(i);
			}
			if (set.contains(sb.toString()))
				return 0;
			set.add(sb.toString());
			return 1;
		}
		int result = 0;

		for (int i = 0; i < user_id.length; i++) {
			if (use[i] || !mask(banned_id[depth], user_id[i]))
				continue;
			use[i] = true;
			result += dfs(depth + 1, user_id, banned_id, use);
			use[i] = false;
		}

		return result;
	}

	private static boolean mask(String mask, String id) {
		if (mask.length() != id.length())
			return false;
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == '*')
				continue;
			if (mask.charAt(i) != id.charAt(i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] u = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] b = { "*rodo", "*rodo", "******" };
		System.out.println(solution(u, b));
	}

}
