package problemsConclusion.putItemProblem;


import java.io.PrintWriter;
import java.util.*;

public class Carousel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();

    }
    // forget the most important scenario,  when last position and the first position is same, using dp is right,
//    when length is even, you could always use 1 2 1 2 1 2 and would not violate the rule
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            set.add(arr[i]);
        }
        boolean[] dp = new boolean[2];
        int[][] pars = new int[n + 1][2];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            boolean[] ndp = new boolean[2];
            if (arr[i] == arr[i - 1]){
                boolean ak = dp[0] | dp[1];
                ndp[0] |= ak;
                ndp[1] |= ak;
                if (dp[0]){
                    pars[i][0] = 0;
                    pars[i][1] = 0;
                }else if (dp[1]){
                    pars[i][0] = 1;
                    pars[i][1] = 1;
                }
            }else{
                ndp[0] |= dp[1];
                if (dp[1]){
                    pars[i][0] = 1;
                }
                ndp[1] |= dp[0];
                if (dp[0]){
                    pars[i][1] = 0;
                }
            }
            dp = ndp;
        }
        if (set.size() == 1){
            out.println(1);
            for (int i = 0; i < n; i++) {
                out.print(1+" ");
            }
            out.println();
        }else if (set.size() == 2){
            out.println(2);
            int pre = 1;
            out.print(1+" ");
            for (int i = 1; i < n; i++) {
                if (arr[i] == arr[i - 1]){
                    out.print(pre+" ");
                    continue;
                }
                if (pre == 2){
                    out.print(1+" ");
                    pre = 1;
                }else{
                    out.print(2+" ");
                    pre = 2;
                }
            }
            out.println();
        }else{
            if (arr[n - 1] == arr[0]){
                out.println(2);
                int pre = 1;
                out.print(1+" ");
                for (int i = 1; i < n; i++) {
                    if (arr[i] == arr[i - 1]){
                        out.print(pre+" ");
                        continue;
                    }
                    if (pre == 2){
                        out.print(1+" ");
                        pre = 1;
                    }else{
                        out.print(2+" ");
                        pre = 2;
                    }
                }
                out.println();
                return;
            }
            if (dp[1]){
                Stack<Integer> stack = new Stack<>();
                stack.push(2);
                int par = pars[n - 1][1];
                for (int i = n - 2; stack.size() < n; i--) {
                    stack.push(par + 1);
                    par = pars[i][par];
                }
                out.println(2);
                while (!stack.isEmpty()){
                    out.print(stack.pop()+" ");
                }
                out.println();
            }else{
                out.println(3);
                int pre = 2;
                for (int i = 0; i < n - 1; i++) {
                    if (pre == 2){
                        out.print(1+" ");
                        pre = 1;
                    }else{
                        out.print(2+" ");
                        pre = 2;
                    }
                }
                out.println(3+" ");
            }
        }
    }

    static int opp(int n, int[] arr){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        boolean[] dp = new boolean[2];
        int[][] pars = new int[n + 1][2];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            boolean[] ndp = new boolean[2];
            if (arr[i] == arr[i - 1]){
                boolean ak = dp[0] | dp[1];
                ndp[0] |= ak;
                ndp[1] |= ak;
                if (dp[0]){
                    pars[i][0] = 0;
                    pars[i][1] = 0;
                }else if (dp[1]){
                    pars[i][0] = 1;
                    pars[i][1] = 1;
                }
            }else{
                ndp[0] |= dp[1];
                if (dp[1]){
                    pars[i][0] = 1;
                }
                ndp[1] |= dp[0];
                if (dp[0]){
                    pars[i][1] = 0;
                }
            }
            dp = ndp;
//            System.out.println(Arrays.toString(dp)+" "+arr[i]);
        }
        if (set.size() == 1){
            return 1;
        }else if (set.size() == 2){
            return 2;
        }else{
            if (dp[1] || arr[n - 1] == arr[0]){
                Stack<Integer> stack = new Stack<>();
                stack.push(2);
                int par = pars[n - 1][1];
                for (int i = n - 2; stack.size() < n; i--) {
                    stack.push(par + 1);
                    par = pars[i][par];
                }
                return 2;
            }else{
                return 3;
            }
        }
    }

}
