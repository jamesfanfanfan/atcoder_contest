package ABC.abc137;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        main.E(sc);
    }
    void E(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int[][] arr = new int[m][];
        Map<Integer,Map<Integer,Integer>> mp = new HashMap<>();
        for(int i=0;i<n;i++) mp.put(i,new HashMap<>());
        for(int i=0;i<m;i++){
            arr[i] = new int[]{in.nextInt()-1,in.nextInt()-1,p-in.nextInt()};
            mp.get(arr[i][0]).put(arr[i][1],arr[i][2]);
        }
        long[] dp = new long[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int st = arr[j][0];
                int ed = arr[j][1];
                int cst = arr[j][2];
                if(dp[st]==Integer.MAX_VALUE) continue;
                dp[ed] = Math.min(dp[ed],dp[st]+cst);
                //System.out.println(st+" "+ed+" "+cst);
            }
            //System.out.println(Arrays.toString(dp)+" "+mp);
        }
        long v = dp[n-1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int st = arr[j][0];
                int ed = arr[j][1];
                int cst = arr[j][2];
                if(dp[st]!=Integer.MAX_VALUE&&dp[ed]>dp[st]+cst){
                    dp[ed] = Integer.MIN_VALUE;
                }
            }
        }
        //System.out.println(v+" "+dp[n-1]);
        if(v!=dp[n-1]){
            System.out.println(-1);
            return;
        }
        System.out.println(Math.max(0,-dp[n-1]));


    }

    void D(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int[] red = new int[n];
        int[] days = new int[n];
        for(int i=0;i<n;i++){
            days[i] = in.nextInt();
            red[i] = in.nextInt();
        }
        List<Integer> ls = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0;i<n;i++) {
            ls.add(i);
        }
        for(int i=0;i<m;i++) set.add(i);
        Collections.sort(ls,(a,b)->{
            return red[b]-red[a];
        });
        int res = 0;
        for(int i:ls){
            int reward = red[i];
            int day = days[i];
            if(day>m) continue;
            int dif = m-day;
            Integer pos = set.floor(dif);
            if(pos!=null){
                res+=reward;
                set.remove(pos);
            }
        }
        System.out.println(res);
    }

}
