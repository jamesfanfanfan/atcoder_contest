package KickStart_G;

import java.util.*;

public class Solution {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Solution sv = new Solution();
        //System.out.println();
        for(int i=1;i<=num;i++){
            System.out.print("Case #"+i+": ");
            sv.D(sc);
        }
    }

    void D(Scanner in){
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = in.nextInt();
        double[][] dp = new double[n+1][n+1]; // dp[i][j] means the total sum you could get from this
        //double ans = 0;
        //for(int i=n-1;i>=1;i--) ans/=i;
        double[] psum = new double[n+1];
        for(int i=1;i<=n;i++){
            psum[i] = psum[i-1] + arr[i-1];
        }
        double[] beg = new double[n];
        double[] end = new double[n];
        for(int len=2;len<=n;len++){
            long add = 0;
            for(int i=0;i+len<=n;i++){
                int j = i+len-1;
                if(len==2){
                    dp[i][j] = arr[i]+arr[j];
                }else{
                    dp[i][j] = (beg[i] + end[j])/(len-1);
                    dp[i][j] += psum[j+1] - psum[i];
                }
                beg[i] += dp[i][j];
                end[j] += dp[i][j];
            }
        }

        System.out.println(dp[0][n-1]);
    }







    void C(Scanner in){
        int w = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[w];
        for(int i=0;i<w;i++){
            arr[i] = in.nextInt();
        }
        long[] lb = new long[w+1];
        long[] rb = new long[w+1];
        Arrays.sort(arr);
        for(int i=1;i<=w;i++){
            lb[i] = lb[i-1] + arr[i-1];
        }
        for (int i = 1; i <=w ; i++) {
            rb[i] = rb[i-1] + (n - arr[w-i]);
        }
        long min = Long.MAX_VALUE;
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        mp.put(0,0);
        for(int i=1;i<=w;i++){
            mp.put(arr[i-1],i);
        }
        mp.put(n,w);
        int len = n/2;
        for(int i=0;i<w;i++){
            System.out.println(i+" val:"+arr[i]);
            if(arr[i]+len>n){// in the right
                int l = arr[i]-len;
                int ll = mp.get(mp.lowerKey(l));
                int ml = mp.get(arr[i]);
                int mt = ml-ll;
                int res = w-mp.get(arr[i]);
                long llt = lb[ll] + ll*(long)(n-arr[i]);
                long ret = lb[w] - lb[w-res] - res*(long)arr[i];
                long mlt = rb[w-ll] - rb[w-ml] - mt*(long)(n-arr[i]);
                min = Math.min(min,llt+ret+mlt);
                System.out.println("llt:"+llt+" ret:"+ret+" mlt:"+mlt);
                System.out.println("ll:"+ll+" mt:"+mt+" res:"+res+" "+Arrays.toString(rb));

//                int ll = mp.get(mp.floorKey(l));
//                int ml = mp.get(mp.floorKey(arr[i]));
//                int mt = ml-ll;
//                int res = w-mp.get(mp.ceilingKey(arr[i]));
//                long llt = lb[]

            }else if(arr[i]+len<n){
                int r = arr[i]+len;
                System.out.println(r+" "+mp);
                int rr = w-mp.get(mp.floorKey(r)); //all the value smaller than the rightest border
                int lr = mp.get(mp.lowerKey(arr[i]));// all the value less than arr[i]
                int rl = mp.get(arr[i]);// all the value smaller or equal to arr[i]
                int md = mp.get(mp.floorKey(r))-rl;
                long lrt = rb[w]-rb[w-lr] - lr*(long)(n-arr[i]);
                long rrt = rb[rr] + rr*arr[i];
                long mdt = lb[mp.get(mp.floorKey(r))] - lb[rl] - md*arr[i];
                min = Math.min(min,lrt+rrt+mdt);
                System.out.println("lrt:"+lrt+" rrt:"+rrt+" mdt:"+mdt);
                System.out.println("rr:"+rr+" lr:"+lr+" rl:"+rl+" md:"+md);

            }
            System.out.println(min);
        }
        long cnt = 0;
        for(int i=0;i<w;i++){
            cnt+=Math.abs(arr[len/2]-arr[i]);
        }
        min = Math.min(min,cnt);

        System.out.println(min);



    }







    void B(Scanner sc){
        int n = sc.nextInt();
        int[][] cs = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                cs[i][j] = sc.nextInt();
            }
        }
        long max = 0;
        long[][] dp = new long[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0){
                    dp[i][j] = cs[i][j];
                }else{
                    dp[i][j] = cs[i][j]+dp[i-1][j-1];
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        System.out.println(max);;
    }








    void solve(Scanner in){
        String s = in.next();
        LinkedList<Integer> st = new LinkedList<>();
        LinkedList<Integer> ed = new LinkedList<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        String kick = "KICK";
        String start = "START";
        for(int i=0;i<=n-4;i++){
            if(cs[i]==kick.charAt(0)){
                boolean find = true;
                for(int j=1;j<=3;j++){
                    if(cs[i+j]!=kick.charAt(j)){
                        find = false;
                        break;
                    }
                }
                if(find) st.add(i);
            }
        }

        for(int i=0;i<=n-5;i++){
            if(cs[i]==start.charAt(0)){
                boolean find = true;
                for(int j=1;j<=4;j++){
                    if(cs[i+j]!=start.charAt(j)){
                        find = false;
                        break;
                    }
                }
                if(find) ed.add(i);
            }
        }
        //System.out.println(st+" "+ed);
        long cnt = 0;
        while(!st.isEmpty()&&!ed.isEmpty()){
            int idx = st.poll();
            while(!ed.isEmpty()&&ed.peek()<idx){
                ed.poll();
            }
            cnt += ed.size();
        }
        System.out.println(cnt);
    }
}
