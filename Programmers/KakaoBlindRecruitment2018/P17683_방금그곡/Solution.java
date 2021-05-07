package KakaoBlindRecruitment2018.P17683_방금그곡;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static public String solution(String m, String[] musicinfos) {
		String answer = "(None)";

		int maxPlayTime = 0;
		for (int i = 0; i < musicinfos.length; i++) {
			StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
			int playTime = getTime(st.nextToken(), st.nextToken());
			if (maxPlayTime >= playTime)
				continue;

			String title = st.nextToken();
			ArrayList<String> totSheet = getTotMusic(playTime, st.nextToken());
			if (!contains(totSheet, m))
				continue;
			maxPlayTime = playTime;
			answer = title;
		}

		return answer;
	}

	private static boolean contains(ArrayList<String> totSheet, String m) {
		ArrayList<String> sheet = getList(m);
		if (!totSheet.containsAll(sheet))
			return false;

		for (int i = 0; i < totSheet.size(); i++) {
			if (!totSheet.get(i).equals(sheet.get(0)))
				continue;
			if (match(totSheet, sheet, i))
				return true;
		}

		return false;
	}

	private static boolean match(ArrayList<String> totSheet, ArrayList<String> sheet, int start) {
		if (totSheet.size() < start + sheet.size())
			return false;
		for (int i = 0; i < sheet.size(); i++) {
			if (!totSheet.get(start + i).equals(sheet.get(i)))
				return false;
		}
		return true;
	}

	private static ArrayList<String> getTotMusic(int playTime, String sheet) {
		ArrayList<String> list = getList(sheet);

		ArrayList<String> result = new ArrayList<>();
		for (int p = 0; p < playTime; p++) {
			result.add(list.get(p % list.size()));
		}

		return result;
	}

	private static ArrayList<String> getList(String sheet) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < sheet.length(); i++) {
			String cur = sheet.charAt(i) + "";
			if (i + 1 < sheet.length() && sheet.charAt(i + 1) == '#') {
				cur += "#";
				i++;
			}
			list.add(cur);
		}
		return list;
	}

	private static int getTime(String start, String end) {
		int startH = Integer.parseInt(start.substring(0, 2));
		int endH = Integer.parseInt(end.substring(0, 2));
		int startM = Integer.parseInt(start.substring(3, 5));
		int endM = Integer.parseInt(end.substring(3, 5));

		if (startH == endH)
			return endM - startM;
		int result = 60 - startM;
		result += endM;
		result += 60 * (endH - (startH + 1));
		return result;
	}

	public static void main(String[] args) {
		String[] m = { "12:00,13:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		System.out.println(solution("ABCDEFG", m));
	}

}
