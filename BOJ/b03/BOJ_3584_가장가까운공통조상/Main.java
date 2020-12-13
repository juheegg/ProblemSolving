package b03.BOJ_3584_가장가까운공통조상;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] parents = new int[N + 1];

			for (int i = 0; i <= N; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				parents[c] = p;
			}

			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			Stack<Integer> aAnc = new Stack<>();
			Stack<Integer> bAnc = new Stack<>();

			while (parents[A] != A) {
				aAnc.push(A);
				A = parents[A];
			}
			aAnc.push(A);

			while (parents[B] != B) {
				bAnc.push(B);
				B = parents[B];
			}
			bAnc.push(B);

			int answer = 0;
			int ap = aAnc.pop();
			int bp = bAnc.pop();
			while (ap == bp) {
				answer = ap;
				if (aAnc.isEmpty() || bAnc.isEmpty())
					break;
				ap = aAnc.pop();
				bp = bAnc.pop();
			}

			result.append(answer + "\n");
		}
		System.out.println(result);
	}

}
