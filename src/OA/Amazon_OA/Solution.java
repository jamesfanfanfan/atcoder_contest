package OA.Amazon_OA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] c1 = new int[]{2,4,1,6,5,9,7};
        int[] c2 = new int[]{4,3,2,6,1};
        int[] c3 = new int[]{2,1,6,4,3,7};
        solution(c3);
    }

    static int solution(int[] arr){
        int cnt = 0;
        int n = arr.length;
        int[] cp = Arrays.copyOf(arr, n);
        Arrays.sort(cp);
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(cp[i], i);
        }
        int max = 0;
        System.out.println(mp);
        for (int i = 0; i < n; i++) {
            int idx = mp.get(arr[i]);
            max = Math.max(max,idx);
            if (max <= i){
                cnt++;
            }
        }
        return cnt++;
    }
}
