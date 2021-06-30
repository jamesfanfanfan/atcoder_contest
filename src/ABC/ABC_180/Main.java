package ABC.ABC_180;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Main main = new Main();
//        main.E(sc);
        int[] arr = new int[20];
        for(int i=0;i<14;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    int mod = (int)1e9+7;
    void F(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int l = in.nextInt();
    }

    //Map<Integer, List<Integer>> mp = new HashMap<>();
    void E(Scanner in){
        int n = in.nextInt();
        int[][] arr = new int[n][];
        for(int i=0;i<n;i++) arr[i] = new int[]{in.nextInt(),in.nextInt(),in.nextInt()};
        int[][] dis = new int[n+1][n+1];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                dis[i][j] = Math.abs(arr[i][0]-arr[j][0]) + Math.abs(arr[i][1]-arr[j][1]) + Math.max(0,arr[j][2]-arr[i][2]);
                dis[j][i] = Math.abs(arr[i][0]-arr[j][0]) + Math.abs(arr[i][1]-arr[j][1]) + Math.max(0,arr[i][2]-arr[j][2]);
            }
        }
        System.out.println(dfs(1,0,n,dis));

    }
    Long[][] dp = new Long[1<<17][18];

    long dfs(int v, int pos, int tot, int[][] dis){
        if(dp[v][pos]!=null) return dp[v][pos];
        int cnt = 0;
        for(int i=0;i<17;i++){
            if((v|1<<i)==v) cnt++;
        }
        if(tot==cnt) {
            return dis[pos][0];
        }
        long res = Long.MAX_VALUE;
        for(int nxt=0;nxt<tot;nxt++){
            if((v|1<<nxt)==v){
                continue;
            }
            int s = v|(1<<nxt);
            res = Math.min(res,dfs(s,nxt,tot,dis)+dis[pos][nxt]);
        }
        return (dp[v][pos] = res);
    }







    void D(Scanner in){
        long x = in.nextLong();
        long y = in.nextLong();
        long a = in.nextInt();
        long b = in.nextInt();
        //Map<Long,Long> mp = new HashMap<>();
        //System.out.println(dfs(x,a,b,y));
        BigInteger bx = new BigInteger(Long.toString(x));
        BigInteger by = new BigInteger(Long.toString(y));
        BigInteger ba = new BigInteger(Long.toString(a));
        BigInteger bb = new BigInteger(Long.toString(b));
        long cnt = 0;
        while(bx.compareTo(by)<0&&(bx.multiply(ba).compareTo(bx.add(bb)))<=0){
            if(bx.multiply(ba).compareTo(by)>=0){
                break;
            }
            cnt++;
            bx = bx.multiply(ba);
        }
//        System.out.println(bx.longValue()+" cnt:"+cnt);
//        System.out.println(by.subtract(bx).divide(bb).longValue());
        long lx = bx.longValue();
        long aka = (y-lx)/b;
        if((y-lx)%b==0){
            aka--;
        }
        System.out.println((cnt+aka));

    }

}
