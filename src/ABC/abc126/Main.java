package ABC.abc126;


import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        Main main = new Main();
//        main.E(sc, pw);
//
        char[] arr = "abc".toCharArray();
        main.test(arr);
        System.out.println(Arrays.toString(arr));
        pw.close();
    }
    void test(char aka[]){
        aka = new char[3];
    }

    void E(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
        }

        int[] par = new int[n+1];
        Arrays.fill(par, -1);
        for(int[] a : arr){
            uf(par, a[0], a[1]);
        }
        int res = n;
        for (int i = 1; i <= n; i++) {
            if(par[i] < 0){
                res += par[i];
                res +=1 ;
            }
        }
        out.println(res);

    }

    int find(int[] par, int i){
        if(par[i] < 0){
            return i;
        }
        return (par[i] = find(par,par[i]));
    }
    void uf(int[] par, int i, int j){
        int pi = find(par, i);
        int pj = find(par, j);
        if(pi == pj) return;
        if(par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
    }
}
