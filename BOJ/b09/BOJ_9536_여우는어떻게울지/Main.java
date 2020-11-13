package b09.BOJ_9536_여우는어떻게울지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static List<String> sound;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			String[] check = br.readLine().split(" ");

			sound = new LinkedList<>();

			String line = br.readLine();
			while (line.split(" ")[1].equals("goes")) {
				sound.add(line.split(" ")[2]);
				line = br.readLine();
			}
			
			for(String c : check) {
				boolean flag = false;
				for(String s : sound) { 
					if(c.equals(s)) {
						flag = true;
					}
				}
				
				if(!flag)
					answer.append(c + " ");
			}
			answer.append("\n");
		}

		System.out.println(answer);
	}

}
