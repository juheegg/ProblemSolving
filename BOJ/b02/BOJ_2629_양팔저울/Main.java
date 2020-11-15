package b02.BOJ_2629_양팔저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, T, weight[], testcase[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		weight = new int[N + 1];

		int tot = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			tot += weight[i];
		}

		T = Integer.parseInt(br.readLine());
		testcase = new int[T];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			testcase[i] = Integer.parseInt(st.nextToken());

		}

		// D: row번째 단계에서 만들수 있는 col에 가장 가까운 무게
		int[][] D = new int[N + 1][tot + 1];

		// val: 각 단계마다 만들 수 있는 무게
		// 0: i번째 추를 아예 안 쓸경우
		// 1: 왼쪽에 놓을 경우
		// 2: 오른쪽에 놓을 경우
		int val[] = new int[3];

		// i: 몇 번쩨 추를 사용할 건지
		for (int i = 1; i <= N; i++) {
			// j: j그램을 만드는 게 목표!
			for (int j = 1; j <= tot; j++) {

				// 이번 단계의 추를 안쓰니까 이전 단계의 무게 가져옴
				val[0] = D[i - 1][j];

				// 이번 단계의 추를 왼쪽에 놓으니까 오른쪽에는 j+weight[i]만큼의 무게가 필요함
				if (tot >= j + weight[i])
					val[1] = D[i - 1][j + weight[i]] - weight[i];
				// j+weight[i]가 모든 추의 합(tot)보다 커지면 어차피 못만드니까 걍 val[0] 넣기
				else
					val[1] = val[0];

				// 이번단계 추가 목표 무게보다 작으면 필요한 나머지 부분(j - weight[i]그램만큼)을 가져오기
				if (j > weight[i])
					val[2] = weight[i] + D[i - 1][j - weight[i]];
				// 이번단계 추가 목표무게보다 크면 초과하는 만큼(weight[i] - j)을 빼기
				else
					val[2] = weight[i] - D[i - 1][weight[i] - j];

				// val에 저장된 무게들과 j의 차이를 구해서 그 차이가 가장 적은 val값을 D에 저장
				int mindiff = Integer.MAX_VALUE, value = 0;
				for (int v = 0; v < 3; v++) {
					if (mindiff > Math.abs(val[v] - j)) {
						mindiff = Math.abs(val[v] - j);
						value = val[v];
					}
				}

				D[i][j] = value;
			}
		}

		for (int t = 0; t < T; t++) {
			if (testcase[t] > tot)
				System.out.print("N ");
			else if (testcase[t] == D[N][testcase[t]])
				System.out.print("Y ");
			else
				System.out.print("N ");
		}

	}

}