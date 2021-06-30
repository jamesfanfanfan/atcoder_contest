package CR696;

import java.io.PrintWriter;
import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        Map<Integer, LinkedList<Integer>> mp = new HashMap<>();

        for (int i = 0; i < 2 * n - 1; i++) {
            if (!mp.containsKey(arr[i])) mp.put(arr[i], new LinkedList<>());
            mp.get(arr[i]).add(i);
        }

        //System.out.println(mp);
        for(int key : mp.keySet()){
            boolean find = true;
            Map<Integer, LinkedList<Integer>> neo = new HashMap<>();
            for(int k : mp.keySet()){
                neo.put(k, new LinkedList<>(mp.get(k)));
            }
            List<int[]> res = new ArrayList<>();
            boolean[] seen = new boolean[2 * n];
            //System.out.println(neo+" "+mp);
            int ip = neo.get(key).poll();
            seen[ip] = true;
            if (neo.get(key).size() == 0) neo.remove(key);
            int pre = arr[2 * n - 1];
            for (int i = 2 * n - 2; i >= 0 ; i--) {
                if (seen[i]) continue;
                //System.out.println(arr[i]+" "+neo+" "+mp);
                neo.get(arr[i]).removeLast();
                if (neo.get(arr[i]).size() == 0){
                    neo.remove(arr[i]);
                }
                int nxt = pre - arr[i];
                if (!neo.containsKey(nxt)){
                    find = false;
                    break;
                }else{
                    int idx = neo.get(nxt).poll();
                    if (neo.get(nxt).size() == 0) neo.remove(nxt);
                    seen[idx] = true;
                    res.add(new int[]{arr[i], pre - arr[i]});
                    pre = arr[i];

                }

            }
            if (find){
                out.println("YES");
                out.println((arr[2 * n - 1] + key));
                out.println(arr[2 * n - 1]+" "+key);
                for(int[] aka : res){
                    out.println(aka[0]+" "+aka[1]);
                }
                return;
            }
        }
        out.println("NO");


    }

}
