package OA;

import java.util.*;

public class SAP {
    public static void main(String args[] )
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[8];
        for(int i = 0; i < 8; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        List<int[]> q = new ArrayList<>();
        Set<String> set = new HashSet<>();
        while(set.add(Arrays.toString(arr))){
            q.add(arr);
            arr = findNext(arr);
        }
        n--;
        System.out.println(q.size());
        n %= q.size();
        arr = q.get(n);
        for(int x : arr){
            System.out.print(x+" ");
        }
        System.out.println();

    }
    static int[] findNext(int[] arr){
        int[] neo = new int[8];
        for(int i = 1; i < 7; i++){
            if(arr[i - 1] == 1 && arr[i + 1] == 1){
                neo[i] = 0;
            }else if(arr[i - 1] == 0 && arr[i + 1] == 0){
                neo[i] = 0;
            }else{
                neo[i] = 1;
            }
        }
        if(arr[1] == 0) {
            neo[0] = 0;
        }else{
            neo[0] = 1;
        }
        if(arr[6] == 0) {
            neo[7] = 0;
        }else{
            neo[7] = 1;
        }
        System.out.println(Arrays.toString(neo)+" ");
        return neo;
    }
}
