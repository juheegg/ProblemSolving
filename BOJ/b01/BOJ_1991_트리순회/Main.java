package b01.BOJ_1991_트리순회;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Map<Character, Node> map;
	static StringBuilder answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		answer = new StringBuilder();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char p = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			map.put(p, new Node(l, r));
		}

		pre('A');
		answer.append("\n");
		in('A');
		answer.append("\n");
		post('A');
		
		System.out.println(answer);
	}

	private static void post(char cur) {
		char l= map.get(cur).left;
		if(l != '.') 
			post(l);
		
		char r= map.get(cur).right;
		if(r != '.')
			post(r);
		
		answer.append(cur);
	}

	private static void in(char cur) {
		char l= map.get(cur).left;
		if(l != '.') 
			in(l);
		
		answer.append(cur);
		
		char r= map.get(cur).right;
		if(r != '.')
			in(r);
	}

	private static void pre(char cur) {
		answer.append(cur);
		
		char l= map.get(cur).left;
		if(l != '.') 
			pre(l);
		
		char r= map.get(cur).right;
		if(r != '.')
			pre(r);
	}

	static class Node {
		char left, right;

		public Node(char left, char right) {
			this.left = left;
			this.right = right;
		}

	}

}
