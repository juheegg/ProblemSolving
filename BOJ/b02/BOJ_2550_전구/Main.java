package b02.BOJ_2550_전구;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N, swt[];
	static Map<Integer, Integer> light;
	static int d[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		swt = new int[N + 1];
		light = new HashMap<>();
		d = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			swt[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			light.put(Integer.parseInt(st.nextToken()), i);
		}

		for (int i = 1; i <= N; i++) {
			d[i] = light.get(swt[i]);
		}

		List<Integer> list = new ArrayList<>();
		int[] order = new int[N + 1];
		list.add(d[1]);
		order[1] = 0;
		for (int i = 2; i <= N; i++) {
			int idx = list.size() - 1;
			if (d[i] > list.get(idx)) {
				list.add(d[i]);
			} else {
				while (idx >= 0 && d[i] < list.get(idx))
					idx--;
				list.set(idx + 1, d[i]);
			}
			order[i] = idx + 1;
		}
		List<Integer> answer = new ArrayList<>();
		int idx = list.size() - 1;
		for (int i = N; i >= 1; i--) {
			if (order[i] == idx) {
				idx--;
				answer.add(0, swt[i]);
			}
			if (idx < 0)
				break;
		}
		
		System.out.println(answer.size());
		Collections.sort(answer);
		for(int i : answer)
			System.out.print(i+" ");
	}

}
// 5 1 3 4 6 2