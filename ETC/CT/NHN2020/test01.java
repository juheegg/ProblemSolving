package CT.NHN2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test01 {

	private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames,
			int[] numOfMovesPerGame) {

		boolean[] fast = new boolean[numOfAllPlayers];
		for (int i = 0; i < numOfQuickPlayers; i++) {
			fast[namesOfQuickPlayers[i] - 'A'] = true;
		}
		System.out.println(Arrays.toString(fast));

		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		for (int i = 1; i < numOfAllPlayers; i++) {
			map.put(i, 0);
		}

		int tagger = 0;
		int loc = 0;
		int[] seat = new int[numOfAllPlayers - 1];
		for (int i = 0; i < numOfAllPlayers - 1; i++) {
			seat[i] = i + 1;
		}

		for (int i = 0; i < numOfGames; i++) {
			loc += numOfMovesPerGame[i] % (numOfAllPlayers - 1);

			if (loc > numOfAllPlayers)
				loc -= numOfAllPlayers - 1;
			else if (loc < 0)
				loc = numOfAllPlayers + loc - 1;
			System.out.println(i + "--------------");
			System.out.println("이동: " + numOfMovesPerGame[i]);
			System.out.println("위치: " + loc);
			for (int j = 0; j < seat.length; j++) {
				System.out.print(((char) (seat[j] + 'A')) + " ");
			}
			System.out.println();

			System.out.println((seat[loc]) + " " + fast[seat[loc]]);
			System.out.print("술래: " + ((char) (tagger + 'A')) + " ----> ");
			if (!fast[seat[loc]]) {
				int tmp = tagger;
				tagger = seat[loc];
				seat[loc] = tmp;
			}
			map.replace(tagger, map.get(tagger) + 1);
			System.out.println(((char) (tagger + 'A')));
			System.out.println();
		}

		for (int i = 0; i < seat.length; i++) {
			System.out.print((char) (seat[i] + 'A'));
			System.out.println(" " + map.get(seat[i]));
		}
		System.out.print((char) (tagger + 'A'));
		System.out.println(" " + map.get(tagger));
	}

	private static class InputData {
		int numOfAllPlayers;
		int numOfQuickPlayers;
		char[] namesOfQuickPlayers;
		int numOfGames;
		int[] numOfMovesPerGame;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
			inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
			System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0,
					inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

			inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
			inputData.numOfMovesPerGame = new int[inputData.numOfGames];
			String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
			for (int i = 0; i < inputData.numOfGames; i++) {
				inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers,
				inputData.numOfGames, inputData.numOfMovesPerGame);

	}
}
