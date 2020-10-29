package b01.BOJ_1238_파티;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, X, cost[][];
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		cost = new int[N + 1][N + 1];
		int a, b, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			cost[a][b] = c;
		}

		int toMin[] = new int[N + 1];
		int fromMin[] = new int[N + 1];
		Arrays.fill(toMin, INF);
		Arrays.fill(fromMin, INF);
		toMin[X] = 0;
		fromMin[X] = 0;

		boolean fVisit[] = new boolean[N + 1];
		boolean tVisit[] = new boolean[N + 1];

		int tIdx, tCost, fIdx, fCost;

		for (int i = 0; i < N; i++) {
			tCost = INF;
			tIdx = 0;
			fCost = INF;
			fIdx = 0;
			for (int j = 1; j <= N; j++) {
				if (!tVisit[j] && tCost > toMin[j]) {
					tCost = toMin[j];
					tIdx = j;
				}
				if (!fVisit[j] && fCost > fromMin[j]) {
					fCost = fromMin[j];
					fIdx = j;
				}
			}

			fVisit[fIdx] = true;
			tVisit[tIdx] = true;

			for (int j = 1; j <= N; j++) {
				if (!tVisit[j] && cost[j][tIdx] != 0 && toMin[j] > toMin[tIdx] + cost[j][tIdx]) {
					toMin[j] = toMin[tIdx] + cost[j][tIdx];
				}
				if (!fVisit[j] && cost[fIdx][j] != 0 && fromMin[j] > fromMin[fIdx] + cost[fIdx][j]) {
					fromMin[j] = fromMin[fIdx] + cost[fIdx][j];
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, toMin[i] + fromMin[i]);
		}
		System.out.println(answer);
	}

}
