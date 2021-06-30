package codeforce.cf726;

import java.io.PrintWriter;
import java.util.Scanner;
//this is odd and even problem, for this kind of problem you should observe the start and end of the game
// for the end of the game: it would 2 * k where k is prime, and we remove k then the remaining k is prime and we win
// so when we have 2 in the hand, we could make it disappear, the our opponent is losing because for a odd number you remove something
// would definitely resulting the even situation which means 2 is back and we have control of the game
//there is one scenario that I did not consider in the contest is that when we have 2 * 2 * 2 * 2 * 2, one situation, we make it odd * even
// then it comes back to former situation, however, we can still reach 2 * 2 * 2 * 2, which means who reach 2 * 3 or 2 * 2 first, who will win
//let's say we have 2 * 2 * 2, we can see that no mather which situation we meet, we lose, then we make to 2 * 2 * 2 * 2, we made it to 2 * 2 * 2 then we win

public class D {
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
        if (n % 2 == 0){
            int k = 0;
            while (n > 1 && n % 2 == 0){
                n /= 2;
                k++;
            }
            if (n > 1){
                out.println("Alice");
            }else{
                if (k % 2 == 0){
                    out.println("Alice");
                }else{
                    out.println("Bob");
                }
            }
        }else{
            out.println("Bob");
        }
    }
}
