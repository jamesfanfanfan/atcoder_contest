package DataStructure;

import java.util.Scanner;

public class LCM {
    void solve(Scanner in){
        long a = in.nextInt();
        long b = in.nextInt();
        long ans = 0;
        for(int i=1;i<=b;i++){
            if((i*a)%b==0){
                System.out.println(i*a);
                return;
            }
        }

    }
}
