package 연습문제.P43105_정수삼각형;

public class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int N = triangle.length;
        int[][] max = new int[N][N];
        max[0][0] = triangle[0][0];
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(max[i][j] < max[i-1][j] + triangle[i][j])
                    max[i][j] = max[i-1][j] + triangle[i][j];
                if(max[i][j+1] < max[i-1][j] + triangle[i][j+1])
                    max[i][j+1] = max[i-1][j] + triangle[i][j+1];
            }
        }
        
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, max[N-1][i]);
        }
        return answer;
    }
}