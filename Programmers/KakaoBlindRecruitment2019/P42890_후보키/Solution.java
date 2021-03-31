package KakaoBlindRecruitment2019.P42890_후보키;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	static List<List<Integer>> list;

	public static int solution(String[][] relation) {
		int arrlen = relation[0].length;
		list = new ArrayList<>();
		subset(0, new boolean[arrlen], arrlen, relation);

		return getMinimum();
	}

	private static int getMinimum() {
		Collections.sort(list, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.size() - o2.size();
			}
		});

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < result.size(); j++) {
				if (list.get(i).containsAll(result.get(j))) {
					flag = false;
					break;
				}

			}
			if (flag)
				result.add(list.get(i));
		}
		return result.size();
	}

	static void subset(int depth, boolean[] select, int L, String[][] relation) {
		if (depth == L) {
			Set<Tuple> set = new HashSet<>();
			for (int i = 0; i < relation.length; i++) {
				Tuple t = new Tuple();
				for (int j = 0; j < relation[i].length; j++) {
					if (select[j])
						t.attr.add(relation[i][j]);
				}
				set.add(t);
			}
			if (set.size() == relation.length) {
				List<Integer> tmp = new ArrayList<>();
				for (int i = 0; i < L; i++) {
					if (select[i])
						tmp.add(i);
				}
				list.add(tmp);
			}
			return;
		}
		select[depth] = true;
		subset(depth + 1, select, L, relation);
		select[depth] = false;
		subset(depth + 1, select, L, relation);
	}

	static class Tuple {
		List<String> attr;

		public Tuple() {
			super();
			attr = new ArrayList<>();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((attr == null) ? 0 : attr.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tuple other = (Tuple) obj;
			if (attr == null) {
				if (other.attr != null)
					return false;
			} else if (!attr.equals(other.attr))
				return false;
			return true;
		}

	}

	public static void main(String[] args) {
		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		System.out.println(solution(relation));
	}

}
