package KakaoBlindRecruitment2021.P72410_신규아이디추천;

public class Solution {

	static public String solution(String new_id) {
		String answer = "";
		answer = rule1(new_id);
		answer = rule2(answer);
		answer = rule3(answer);
		answer = rule4(answer);
		answer = rule5(answer);
		answer = rule6(answer);
		answer = rule7(answer);
		return answer;
	}

	private static String rule7(String str) {
		if (str.length() > 2)
			return str;
		char repeat = str.charAt(str.length() - 1);
		while (str.length() < 3) {
			str += repeat;
		}
		return str;
	}

	private static String rule6(String str) {
		String result = str.substring(0, Math.min(str.length(), 15));
		if (result.charAt(result.length() - 1) == '.')
			result = result.substring(0, result.length() - 1);
		return result;
	}

	private static String rule5(String str) {
		if (str.length() == 0)
			return "a";
		return str;
	}

	private static String rule4(String str) {
		if (str.length() > 0 && str.charAt(0) == '.')
			str = str.substring(1);
		if (str.length() > 0 && str.charAt(str.length() - 1) == '.')
			str = str.substring(0, str.length() - 1);
		return str;
	}

	private static String rule3(String str) {
		StringBuilder result = new StringBuilder();
		int dotCnt = 0;
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (cur == '.') {
				dotCnt++;
			} else if (dotCnt > 0) {
				result.append("." + cur);
				dotCnt = 0;
			} else {
				result.append(cur);
			}
		}
		if (dotCnt > 0)
			result.append(".");
		return result.toString();
	}

	private static String rule2(String str) {
		String regex = "[^a-z0-9-_.]";
		return str.replaceAll(regex, "");
	}

	private static String rule1(String str) {
		return str.toLowerCase();
	}

	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdef-_.ghijklm."));
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(solution("z-+.^."));
		System.out.println(solution("=.="));
		System.out.println(solution("123_.def"));
		System.out.println(solution("abcdefghijklmn.p"));
		System.out.println(solution("=.="));
	}

}
