package tenka1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        main.E(sc);
    }
    void E(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int l = n/2;
        int r = n/2+1;
        if(n%2==0){
            while(m>0){
                if((r-l)!=n/2){
                    System.out.println(l+" "+r);
                }else{
                    m++;
                }
                l--;r++;
                m--;
            }
        }else{
            while(m>0){
                System.out.println(l+" "+r);
                l--;r++;
                m--;
            }
        }
    }

    void D(Scanner in){
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        if(n==2){
            System.out.println(Math.abs(arr[0]-arr[1]));
            return;
        }else if(n==3){
            System.out.println(Math.max((arr[2]+arr[1]-arr[0]*2),(arr[2]*2-arr[1]-arr[0])));
            return;
        }

        long[] dp = new long[n];
        long tot = 0;
        for (int i = 0; i < n; i++) {
            tot += arr[i];
            dp[i] = tot;
        }
        long res = 0;
        int mid = n/2;
        long l = dp[mid-2];
        long r = tot-dp[mid];
        if(n%2==1){
            long aka = (r*2-l*2-arr[mid]-arr[mid-1]);
            l = dp[mid-1];
            r = tot-dp[mid+1];
            aka = Math.max(aka,(r*2-l*2+arr[mid]+arr[mid+1]));
            System.out.println(aka);
        }else{
            System.out.println((r*2-l*2+arr[mid]-arr[mid-1]));
        }
    }


}
