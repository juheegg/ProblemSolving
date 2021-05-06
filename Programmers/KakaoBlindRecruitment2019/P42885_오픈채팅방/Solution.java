package KakaoBlindRecruitment2019.P42885_오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	static public String[] solution(String[] record) {
		HashMap<String, String> nicknames = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < record.length; i++) {
			StringTokenizer st = new StringTokenizer(record[i]);
			if (st.nextToken().equals("Leave"))
				continue;
			String uid = st.nextToken();
			String curNickname = st.nextToken();
			nicknames.put(uid, curNickname);
		}

		for (int i = 0; i < record.length; i++) {
			StringTokenizer st = new StringTokenizer(record[i]);
			String act = st.nextToken();
			if (act.equals("Change"))
				continue;
			String msg = nicknames.get(st.nextToken());
			msg += act.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.";
			list.add(msg);
		}

		return list.toArray(new String[] {});
	}

	public static void main(String[] args) {
		String[] r = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		String[] result = solution(r);
		for (String s : result)
			System.out.println(s);

	}

}
