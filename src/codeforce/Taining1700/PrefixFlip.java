package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://codeforces.com/contest/1381/problem/A2
public class PrefixFlip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
// two ways to think about this problem,
//    Solution 1 : let both string into all 0000000
//    Solution 2 : use the 0th index to substitute for each position, since we need to reverse each time, so we should keep track of the left and right of the position
//    should see petr's solution for this problem which is very neat
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        char[] cb = in.next().toCharArray();
        char[] ca = in.next().toCharArray();
        int inv = 0;
        int lastInversePosition = n - 1;
        List<Integer> q = new ArrayList<>();
        for(int i = n - 1; i >= 0; i--){
            char c = '1';
            if (cb[lastInversePosition] == '1' && inv % 2 == 0){
                c = cb[lastInversePosition];
            }
            if (cb[lastInversePosition] == '0' && inv % 2 == 0){
                c = cb[lastInversePosition];
            }
            if (cb[lastInversePosition] == '0' && inv % 2 == 1){
                c = '1';
            }
            if (cb[lastInversePosition] == '1' && inv % 2 == 1){
                c = '0';
            }
            if (ca[i] == c){
                if (inv % 2 == 1){
                    lastInversePosition++;
                }else{
                    lastInversePosition--;
                }
            }else{
                if (inv % 2 == 0){
                    int first = lastInversePosition - i;
                    if (cb[first] == ca[i]){
                        q.add(1);
                    }
                    q.add(i + 1);
                    lastInversePosition = first + 1;
                }else{
                    int first = lastInversePosition + i;
                    if (cb[first] != ca[i]){
                        q.add(1);
                    }
                    q.add(i + 1);
                    lastInversePosition = first - 1;
                }
                inv++;
            }
        }
        out.print(q.size()+" ");
        for(int v : q){
            out.print(v+" ");
        }
        out.println();
    }
}
