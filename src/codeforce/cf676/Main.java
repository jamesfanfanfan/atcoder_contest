package codeforce.cf676;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Main main = new Main();
        int t = in.nextInt();
        for(int i=0;i<t;i++){
            main.D(in, out);
        }
    }
    class node{
        int x;
        int y;
        long cst;
        node(int x, int y, long cst){
            this.x = x;
            this.y = y;
            this.cst = cst;
        }
    }
    int[][] dirs = new  int[][]{{1,1},{0,1},{-1,0},{-1,-1},{0,-1},{1,0}};
    void D (Scanner in, PrintWriter out){
        int[] arr = new int[6];
        int x = in.nextInt();
        int y = in.nextInt();
        for(int i=0;i<6;i++){
            arr[i] = in.nextInt();
        }
        
    }
}
