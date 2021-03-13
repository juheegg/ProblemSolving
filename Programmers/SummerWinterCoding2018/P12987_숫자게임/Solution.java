package SummerWinterCoding2018.P12987_숫자게임;

import java.util.Arrays;

public class Solution {

	public static int solution(int[] A, int[] B) {
		int answer = 0;
		
		int N = A.length;
		Arrays.sort(A);
		Arrays.sort(B);
		
		int b = 0;
		for (int a = 0; a < N; a++) {
			while(b < N && A[a] >= B[b]) {
				b++;
			}
			
			if(b >= N)
				break;
			
			b++;
			answer++;
			
		}
		
		return answer;
	}

	public static void main(String[] args) {
//		int[] A = { 5, 1, 3, 7 };
//		int[] B = { 8, 2, 6, 2 };
		int[] A = {2,2,2,2};
		int[] B = {1,1,1,1};
		System.out.println(solution(A, B));
	}

}
