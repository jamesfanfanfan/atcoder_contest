package codeforce.ECR101;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solveE(sc, pw);
        }
        pw.close();
    }

    static void solveE(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1') cnt++;
        }
        int fin = Math.min(22, k);
        int end = k - fin;
        int j = n - 1;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if(cs[i] == '0'){
                pre[i] = 0;
            }else if (i == 0 || pre[i - 1] == -1){
                pre[i] = -1;
            }else{
                pre[i] = pre[i - 1] + 1;
            }
        }
        Set<Integer> set = new HashSet<>();
        int v = 0;
        for (int i = 0; i < fin; i++,j--) {
            //v <<= 1;
            if (cs[j] == '0'){
                v |= (1<<i);
            }
        }
        //out.println("fin:"+fin);
        //out.println(v);
        if(! (k > fin && pre[j] != -1 && pre[j] <= k - fin - 1)){
            set.add(v);
        }
        for (int i = n - 1; j >= end; i--,j--) {
            v>>=1;
            if(cs[j] == '0'){
                v |= (1 << (fin - 1));
            }
            //out.println(j+" " +v);
            if(! (k > fin && pre[j] != -1 && pre[j] <= k - fin - 1)){
                set.add(v);
            }
        }
        boolean find = false;
        int i = 0;
        for(; i < (1 << fin); i++){
            if (!set.contains(i)){
                find = true;
                break;
            }
        }
        String res = Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder();
        while (sb.length() < k - res.length()){
            sb.append("0");
        }
        sb.append(res);
        res = sb.toString();
        if (find){
            out.println("YES");
            out.println(res);
        }else{
            out.println("NO");
        }
    }
    static void solveA(Scanner in, PrintWriter out){
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        int cnt = 0;
        int idxle = s.indexOf('(');
        int idxri = s.indexOf(')');
        if (idxri == 0 || n % 2 != 0 || idxle == n - 1){
            out.println("NO");
        }else{
            out.println("YES");
        }
    }
    static void solveB(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] r = new int[n];
        int[] preR = new int[n + 1];
        int max2 = 0;
        for (int i = 0; i < n; i++) {
            r[i] = in.nextInt();
            preR[i + 1] = preR[i] + r[i];
            max2 = Math.max(max2, preR[i + 1]);
        }
        int m = in.nextInt();
        int[] l = new int[m];
        int[] preL = new int[m + 1];
        int max1 = 0;
        for (int i = 0; i < m; i++) {
            l[i] = in.nextInt();
            preL[i + 1] = preL[i] + l[i];
            max1 = Math.max(max1, preL[i + 1]);
        }

        out.println((max1 + max2));
        
    }

    static void solveC(Scanner in, PrintWriter out){// this is a good problem to be remembered
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int min = arr[0] + 1 - k;
        int max = arr[0] + k - 1;

        boolean find = true;
        //out.println("-------------------");
        for (int i = 1; i < n - 1; i++) {
            int le = arr[i];
            int ri = arr[i] + k - 1;
            min = Math.max(min, le);
            max = Math.min(max, ri);
            //out.println(min+" "+max);
            if (min > max){
                find = false;
                break;
            }
            max = max + k - 1;
            min = min - k + 1;
            //out.println(min+" "+max);
        }
        int le = arr[n - 1];
        //out.println("final:"+min+" "+max);
        if (le < min || le > max){
            find = false;
        }
        if (!find){
            out.println("NO");
        }else{
            out.println("YES");
        }
    }
    static void solveD(Scanner in, PrintWriter out){
        int n = in.nextInt();
        List<int[]> res = new ArrayList<>();
        int ini = n;
        int v_ini = n;
        for (int i = n - 1; i > 2; i--) {
            if (i <= Math.sqrt(v_ini)){
                res.add(new int[]{ini, i});
                v_ini = (v_ini - 1) / i + 1;
            }
            if(i > 2)res.add(new int[]{i, ini});
        }
        if(v_ini > 2) res.add(new int[]{ini,2});
        res.add(new int[]{ini,2});
        res.add(new int[]{ini,2});
        out.println(res.size());
        for(int[] x : res){
            out.println(x[0]+" "+x[1]);
        }
    }


}
