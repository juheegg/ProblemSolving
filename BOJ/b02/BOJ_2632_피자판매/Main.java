package b02.BOJ_2632_피자판매;

import java.util.Scanner;

public class Main {
	static int P, M, N, A[], B[];
	static int pizzaA[], pizzaB[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		P = sc.nextInt();
		M = sc.nextInt();
		N = sc.nextInt();
		A = new int[M];
		B = new int[N];
		pizzaA = new int[P + 1];
		pizzaB = new int[P + 1];
		int answer = 0;

		for (int i = 0; i < M; i++)
			A[i] = sc.nextInt();
		for (int i = 0; i < N; i++)
			B[i] = sc.nextInt();

		makePiece(A, pizzaA);
		makePiece(B, pizzaB);

		for (int i = 0; i <= P; i++) {
			System.out.print("A: " + pizzaA[i]);
			System.out.println("  B: " + pizzaB[i]);
			answer += pizzaA[i] * pizzaB[P - i];
		}

		System.out.println(answer);
	}

	private static void makePiece(int[] pieces, int[] result) {
		int sum = 0, idx = 0, total = 0;
		for (int i = 0; i < pieces.length; i++) {
			sum = 0;
			idx = i;
			total += pieces[i];
			for (int j = 0; j < pieces.length - 1; j++) {
				if (sum + pieces[idx] <= P) {
					sum += pieces[idx++];
					result[sum]++;
					if (idx == pieces.length)
						idx = 0;
				} else
					break;
			}
		}
		result[0] = 1;
		if (total <= P)
			result[total] = 1;
		System.out.println(total);
	}

}
