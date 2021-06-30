package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class PetyaandExam {
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
        int n = in.nextInt(), t = in.nextInt(), a = in.nextInt(), b = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr[i][1] = in.nextInt();
        }
        Arrays.sort(arr, (s, v) -> (s[1] - v[1]));
        long mandTime = 0;
        int easy = 0, hard = 0, mdProblems = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (arr[i][0] == 0){
                easy++;
            }else{
                hard++;
            }
            set.add(arr[i][1]);
        }
        long max = 0;
        TreeMap<Integer, List<Integer>> mp = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            if (!mp.containsKey(arr[i][1])) mp.put(arr[i][1], new ArrayList<>());
            mp.get(arr[i][1]).add(i);
        }
        for(int time : mp.keySet()){
            List<Integer> ls = mp.get(time);
            if (set.add(time - 1) && time - 1 >= mandTime){
                int proTime = time - 1;
                long rem = proTime - mandTime;
                long add = Math.min(easy, rem / a);
                rem -= add * a;
                add += Math.min(hard, rem / b);
                max = Math.max(max, add + mdProblems);
            }
            if (time > t) break;
            for(int idx : ls){
                if (arr[idx][0] == 0){
                    easy--;
                    mandTime += a;
                }else{
                    hard--;
                    mandTime += b;
                }
            }
            mdProblems += ls.size();
            if (time >= mandTime){
                long rem = time - mandTime;
                long add = Math.min(easy, rem / a);
                rem -= add * a;
                add += Math.min(hard, rem / b);
                max = Math.max(max, add + mdProblems);
            }
        }
        if (t >= mandTime){
            long rem = t - mandTime;
            long add = Math.min(easy, rem / a);
            rem -= add * a;
            add += Math.min(hard, rem / b);
            max = Math.max(max, add + mdProblems);
        }
        out.println(max);
    }
}
