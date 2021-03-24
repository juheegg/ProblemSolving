package l00.L_93_RestoreIpAddresses;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	static List<String> list;

	public static List<String> restoreIpAddresses(String s) {
		list = new ArrayList<>();

		dfs(0, s, new int[4]);

		return list;
	}

	static void dfs(int depth, String s, int[] ip) {
		if (4 - depth > s.length() || (4 - depth) * 3 < s.length())
			return;
		if (depth == 4) {
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				if (i != 0)
					result.append(".");
				result.append(ip[i]);
			}
			list.add(result.toString());
			return;
		}

		StringBuilder number = new StringBuilder();
		for (int i = 0; i < (s.length() < 3 ? s.length() : 3); i++) {
			number.append(s.charAt(i));
			if (!canIP(number.toString()))
				continue;
			ip[depth] = Integer.parseInt(number.toString());
			dfs(depth + 1, s.substring(i + 1), ip);
		}

	}

	private static boolean canIP(String str) {
		switch (str.length()) {
		case 2:
			if (str.charAt(0) == '0')
				return false;
			break;
		case 3:
			if (str.charAt(0) == '0')
				return false;
			if (Integer.parseInt(str) > 255)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		List<String> result = restoreIpAddresses("101023");
		for (String str : result)
			System.out.println(str);
	}

}
