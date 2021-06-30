package OA.TikTok_OA;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
    public static List<Integer> common_elements(List<Integer> arr1, List<Integer> arr2) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> mp1 = new HashMap<>();
        Map<Integer, Integer> mp2 = new HashMap<>();
        for(int i : arr1){
            mp1.put(i, mp1.getOrDefault(i, 0) + 1);
        }
        for(int i : arr2){
            mp2.put(i, mp2.getOrDefault(i, 0) + 1);
        }
        for(int i : mp1.keySet()){
            int v = mp1.get(i);
            int j = mp2.getOrDefault(i, 0);
            for (int k = 0; k < j; k++) {
                //res.add(i);a
            }
        }
        Collections.sort(res);
        return res;
    }


}
