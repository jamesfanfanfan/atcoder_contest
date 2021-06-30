package ABC.ABC181;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
//        Main main = new Main();
//        main.C(sc,out);
        //boolean S = false, Be = false, I = false, Bor = false;


//        for(int i=0;i<(1<<4);i++){
//            StringBuilder sb = new StringBuilder();
//            boolean[] vl = new boolean[4];
//            for(int j = 0; j < 4; j++){
//                if((i|(1<<j))==i){
//                    sb.append("T            ");
//                    vl[j] = true;
//                }else{
//                    sb.append("F            ");
//                }
//            }
//            out.println(sb.toString()+"     ans:"+ isT(vl[0], vl[1], vl[2], vl[3]));
//        }
//        boolean[][] get = bitmastService(4);
//
//
//        for(int i = 0;i < get.length; i++){
//            StringBuilder sb = new StringBuilder();
//            for(int j = 0; j < get[0].length; j++){
//                sb.append(get[i][j]+"    ");
//            }
//            sb.append("  ans: "+isT(get[i][0], get[i][1], get[i][2], get[i][3]));
//            out.println(sb.toString());
//        }
            out.println((false && false));

        out.close();



    }

    static boolean[][] bitmastService(int n){
        boolean[][] res = new boolean[1<<n][n];
        for(int i=0;i< (1 << n);i++){
            boolean[] vl = new boolean[n];
            for(int j = 0; j < n; j++){
                if((i|(1<<j))==i){
                    vl[j] = true;
                }
            }
           res[i] = vl;
        }
        return res;
    }
    static boolean isTT(boolean T, boolean G){
        return (T || G) || ( T  &&  !G);
    }
    static boolean isT(boolean S, boolean Be, boolean I, boolean Bor){
        return ( ((S || Be)  && (!I  &&  ! Bor) )  ||  ( ( !S || I ) || ( !Be || Bor ) ))  &&
                ( ((!S && !Be) || (I || Bor))  || ( ( S && !I ) && (Be && !Bor ) ));
    }
    void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(i==j||i==k||j==k) continue;
                    int[] a = arr[i];
                    int[] b = arr[j];
                    int[] c = arr[k];
                    if( Math.abs(dis(a,b)+dis(c,b)-dis(a,c))==0){
                        out.println("Yes");
                        return;
                    }else if(Math.abs(dis(a,c)+dis(c,b)-dis(a,b))==0){
                        out.println("Yes");
                        return;
                    }else if(Math.abs(dis(c,a)+dis(a,b)-dis(b,c)) ==0){
                        out.println("Yes");
                        return;
                    }
                }
            }
        }
        out.println("No");

    }
    double dis(int[] a, int[] b){
        return Math.pow(Math.pow(a[1]-b[1],2)*1.0 + Math.pow(a[0]-b[0],2),0.5);
    }

    void E(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i=0;i<n;i++){
            a[i] = in.nextInt();
        }
        for(int j=0;j<m;j++){
            b[j] = in.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        long[] sumJ = new long[n+1];
        long[] sumO = new long[n+1];
        long j = 0;
        long o = 0;
        //out.println(Arrays.toString(a));
        for(int i=0;i<n;i++){
            if(i%2==0){
                o+=a[i];
            }else{
                j+=a[i];
            }
            //out.println(o+" "+j);
        }
        sumJ[n] = j;
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            q.add(b[i]);
        }
        long min = Long.MAX_VALUE/2;
        long tot = 0;
        //out.println("ou shu:"+Arrays.toString(sumO));
        //out.println("ji shu:"+Arrays.toString(sumJ));
        //a[n] = Integer.MAX_VALUE;
        long acj = 0;
        long aco = 0;
        for (int i = 0; i < n; i++) {
            //out.println(i);

            while(!q.isEmpty() && q.peek() <= a[i]){
                int v = q.poll();
                if(i%2==0){
                    min = Math.min(min, tot + (o-aco) - (j-acj) - v);
                }else{
                    min = Math.min(min, tot - (j-acj) + (o-aco) + v);
                }
            }
            if(i%2==0){
                tot -= a[i];
                aco += a[i];
            }else{
                tot += a[i];
                acj += a[i];
            }

        }
        while(!q.isEmpty()){
            min = Math.min(min,j-o+q.poll());
        }
        out.println(min);

    }
    void D(Scanner in, PrintWriter out){
        String s = in.next();
        if(s.length() == 1){
            if(Integer.valueOf(s) % 8==0){
                out.println("Yes");
            }else{
                out.println("No");
            }
            return;
        }else if(s.length() == 2){
            if(Integer.valueOf(s) % 8==0 || Integer.valueOf(new String(new char[]{s.charAt(1),s.charAt(0)}))%8==0){
                out.println("Yes");
            }else{
                out.println("No");
            }
            return;
        }else{
            int[] cnt = new int[10];
            char[] cs = s.toCharArray();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                cnt[cs[i]-'0']++;
            }
            for (int i = 1; i < 10; i++) {
                int[] c1 = Arrays.copyOf(cnt,10);
                if(c1[i] < 1) continue;
                c1[i]--;
                for(int j = 1; j < 10 ;j++){
                    int[] c2 = Arrays.copyOf(c1,10);
                    if(c2[j] < 1) continue;
                    c2[j]--;
                    for(int k = 1; k < 10; k++){
                        int[] c3 = Arrays.copyOf(c2,10);
                        if(c3[k] < 1) continue;
                        if(((i*100+j*10+k)%8)==0){
                            out.println("Yes");
                            return;
                        }
                    }
                }
            }
        }
        out.println("No");
    }
}
