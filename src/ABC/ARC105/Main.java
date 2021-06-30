package ABC.ARC105;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        main.C(sc);
    }

    void C(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ws = new int[n];
        for(int i=0;i<n;i++){
            ws[i] = in.nextInt();
        }
        int[][] ps = new int[m][];
        for(int i=0;i<m;i++){
            ps[i] =  new int[]{in.nextInt(),in.nextInt()};
        }
    }







    void B(Scanner in){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);
        }
        int cnt = 0;
        while(mp.size()>1){
            int sm = mp.firstKey();
            int bg = mp.lastKey();
            int ot = bg-sm;
            cnt+=mp.get(bg);
            //System.out.println(cnt+ " "+mp);
            mp.put(ot,mp.getOrDefault(ot,0)+0);
            mp.remove(bg);
        }
        System.out.println(mp.firstKey());
    }



}
