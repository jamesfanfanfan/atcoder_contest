package codeforce.edu104;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

// two steps to solve this problem
// first we have two equations, a^2 + b^2 = c^2 and c = a^2 - b;
//so we combine them and see that (c - 1)* c = b(b + 1) which denotes that c = b + 1
//then we replace back to the first equation and see that a ^ 2 = b + c, which means a should be odd, and a ^ 2 <= c / 2,
//then we want to make sure that if a is odd the answer is good(which I assume yes in the virtual contest), this should be good because the prerequisite of a ^ 2 = b + c is a^2 + b^2 = c^2
public class D {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        PrintWriter pw = new PrintWriter(System.out);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            solve(sc, pw);
//        }
//        pw.close();
        int v = 1|((0&0)&0);
        System.out.println(v);
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        double ok = Math.sqrt(n + n - 1);
        int v = (int)Math.floor(ok);
        out.println( (v + 1) / 2 - 1);
    }

    public int solve(String s) {
//        s = "("+s+")";
        Stack<Character> stack = new Stack<>();
        Stack<int[]> vals = new Stack<>();
        int[] neo = new int[2];
        char c = 'a';
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cur = null;
        for(int i = 0; i < n; i++){
            if (cs[i] == '('){
                if(cur != null) {
                    vals.push(cur);
                    stack.push(c);
                    cur = null;
                }
            }else if (cs[i] == ')'){
                if (vals.size() > 0){
                    int[] fm = vals.pop();
                    char cc = stack.pop();
                    cur = helper(fm, cur, cc);
                }
            }else if (cs[i] == '&' || cs[i] == '|'){
                c = cs[i];
            }else{
                if (cur == null){
                    cur = cs[i] == '1' ? new int[]{1, 0}: new int[]{0, 1};
                }else{
                    neo = cs[i] == '1' ? new int[]{1, 0}: new int[]{0, 1};
                    cur = helper(cur, neo, c);
                }
            }
        }
        if(stack.size() == 0){
            return 1;
        }
        c = stack.pop();
        int[] ans = helper(vals.pop(), vals.pop(), c);
        return Math.max(ans[0], ans[1]);
    }
    int[] helper(int[] le, int[] ri, char c){
        int[] res = new int[2];
        int lv = le[0] == 0 ? 0 : 1;
        int rv = ri[0] == 0 ? 0 : 1;
        if(c == '&'){
            if(lv == 1 && rv == 1){
                res[1] = 0;
                res[0] = Math.min(le[0], ri[0]);
            }else if(lv == 1 || rv == 1){
                res[0] = 0;
                res[1] = 1;
            }else{
                res[0] = 0;
                res[1] = Math.min(le[1] + 1, ri[1] + 1);
            }
        }else{
            if(lv == 1 && rv == 1){
                res[1] = 0;
                res[0] = Math.min(le[0] + 1, ri[0] + 1);
            }else if(lv == 1 || rv == 1){
                res[1] = 0;
                res[0] = 1;
            }else{
                //System.out.println(l+"r:"+r+" logic");
                res[0] = 0;
                res[1] = Math.min(le[1], ri[1]);
            }
        }
        return res;

    }
}
