package b20.BOJ_20191_줄임말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class Main {
 
    static int answer;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] T = br.readLine().toCharArray();
 
        List<Integer> tlist[] = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            tlist[i] = new ArrayList<>();
        }
 
        for (int i = 0; i < T.length; i++) {
            tlist[T[i] - 'a'].add(i);
        }
 
        answer = 1;
        int cur = 0;
        for (char ch : S) {
            if (cur == T.length) {
                answer++;
                cur -= T.length;
            }
 
            if (tlist[ch - 'a'].size() == 0) {
                answer = -1;
                break;
            }
 
            cur = find(tlist[ch - 'a'], cur) + 1;
        }
 
        System.out.println(answer);
    }
 
    private static int find(List<Integer> list, int cur) {
        if(list.get(list.size()-1) < cur) {
            answer++;
            return list.get(0);
        }
        int start = 0;
        int end = list.size() - 1;
        int mid = (start + end) / 2;
         
        while (end > start) {
            mid = (start + end) / 2;
            if (list.get(mid) == cur) {
                return list.get(mid);
            } else if (list.get(mid) > cur) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return list.get(end);
    }
 
}