package codeforce.Edu_103;

import java.io.PrintWriter;
import java.util.*;

public class E {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        PrintWriter pw = new PrintWriter(System.out);
//        solve(sc, pw);
//        pw.close();
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for(int i = 0; i < 1e3 - 1; i++){
            sb.append('a');
        }
        sb.append('b');
        for(int i = 0; i < 8e3; i++){
            sb.append('c');
        }
        sb.append("\"");
        System.out.println(sb.toString());
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        char[][] cp = new char[n][k];
        Map<String, Integer> cntSon = new HashMap<>();
        Map<String, List<Integer>> back = new HashMap<>();
        Map<String, Integer> ak = new HashMap<>();
        for (int i = 0; i < n; i++) {
            cp[i] = in.next().toCharArray();
            back.put(new String(cp[i]), new ArrayList<>());
            ak.put(new String(cp[i]), i);
            cntSon.put(new String(cp[i]), 0);

        }
        char[][] cs = new char[m][k];
        int[] sm = new int[m];
        for (int i = 0; i < m; i++) {
            cs[i] = in.next().toCharArray();
            sm[i] = in.nextInt();
        }
        int[] ans = new int[m];
        Arrays.fill(ans, - 1);
        // bitmask to find all the pattern
        //Map<Integer, List<Integer>> all = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < (1 << k); j++) {
                char[] cpy = Arrays.copyOf(cs[i], cs[i].length);
                for (int l = 0; l < k; l++) {
                    if ((j | (1 << l)) != j){
                        cpy[l] = '_';
                    }
                }
                if (ak.containsKey(new String(cpy))){
                    back.get(new String(cpy)).add(i);
                }
            }
        }
        Map<String, List<String>> par = new HashMap<>();
        for (int i = 0; i < n; i++) {
            par.put(new String(cp[i]), new ArrayList<>());
            Set<String> fi = new HashSet<>();
            for (int j = 0; j < (1 << k); j++) {
                char[] cpy = Arrays.copyOf(cp[i], cp[i].length);
                for (int l = 0; l < k; l++) {
                    if ((j | (1 << l)) != j && cpy[l] != '_'){
                        cpy[l] = '_';
                    }
                }
                if (new String(cp[i]).compareTo(new String(cpy)) == 0 || !fi.add(new String(cpy))){
                    continue;
                }
                if (ak.containsKey(new String(cpy))){
                    par.get(new String(cp[i])).add(new String(cpy));
                    String tep = new String(cpy);
                    System.out.println("from :"+new String(cp[i])+" to:"+tep);
                    cntSon.put(tep, cntSon.getOrDefault(tep, 0) + 1);
                }
            }
        }
        LinkedList<String> q = new LinkedList<>();
        for (String key : cntSon.keySet()){
            if (cntSon.get(key) == 0) q.add(key);
        }
        System.out.println(cntSon);
        System.out.println(q);
        while (!q.isEmpty()){
            String find = q.poll();
            for (int mat : back.get(find)){
                if (ans[mat] == -1){
                    ans[mat] = ak.get(find);
                    break;
                }
            }
            for (String pa : par.get(find)){
                cntSon.put(pa, cntSon.getOrDefault(pa, 0) - 1);
                if (cntSon.get(pa) == 0){
                    q.add(pa);
                }
            }
        }
        System.out.println("back:"+back);
        System.out.println(Arrays.toString(ans));
        int[] res = new int[n];
        Arrays.fill(res, - 1);
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
        for (int i = 0; i < m; i++) {
            if (ans[i] == -1){
                out.println("NO");
                return;
            }
            res[ans[i]] = sm[i];
            set.remove(sm[i]);
        }
        Iterator<Integer> iter = set.iterator();

        for (int i = 0; i < n; i++) {
            if (res[i] == -1) {
                res[i] = iter.next();
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(res[i]+" ");
        }
        out.println();
    }
    static boolean isPrime(long n)
    {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
}
