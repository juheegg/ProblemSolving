package SummerWinterCoding2018;

public class P49993_스킬트리 {

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		for (int i = 0; i < skill_trees.length; i++) {
			if (canLearn(skill, skill_trees[i])) {
				answer++;
			}
		}
		return answer;
	}

	private static boolean canLearn(String skill, String target) {
		boolean result = true;
		boolean[] poss = new boolean[26];
		for (int i = 1; i < skill.length(); i++) {
			poss[skill.charAt(i) - 'A'] = true;
		}

		int skillIdx = 0;
		for (int i = 0; i < target.length(); i++) {
			if (poss[target.charAt(i) - 'A']) {
				result = false;
				break;
			} else if (skillIdx >= skill.length()) {
				break;
			} else if (skill.charAt(skillIdx) == target.charAt(i)) {
				if (++skillIdx < skill.length())
					poss[skill.charAt(skillIdx) - 'A'] = false;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };
		System.out.println(solution(skill, skill_trees));
	}

}
