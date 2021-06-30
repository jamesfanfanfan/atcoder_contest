package ABC.ABC82;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        Main main = new Main();
        main.C(sc, pw);
        pw.close();
    }
    void C(Scanner in, PrintWriter out){
        long get = in.nextLong();
        long v = get;
        List<Long> list = new ArrayList<>();
        List<Long> store = new ArrayList<>();
        long mut = 1;
        while(get > 0){
            long fk = get % 10;
            list.add(fk);
            store.add(fk * mut);
            get /= 10;
            mut *= 10;
        }
        //System.out.println(list);
        //System.out.println(store);
        if(v % 3 == 0){
            out.println(0);
            return;
        }
        long min = 1000;
        for (int i = 1; i < (1 << store.size())-1; i++) {
            List<Integer> dm = new ArrayList<>();
            long tt = v;
            for(int j = 0; j <= 18; j++){
                if((i | (1 << j)) == i){
                    //System.out.println(j);
                    tt -= store.get(j);
                    dm.add(j);
                }
            }
            if (tt % 3 == 0) min = Math.min(min, dm.size());
        }
        if(min > 20){
            out.println(-1);
        }else{
            out.println(min);
        }
    }
    void E(Scanner in, PrintWriter out){
        int h = in.nextInt();
        int w = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] bu = new int[n][2];
        int[][] bk = new int[m][2];
        int[][] seen = new int[h][w];
        for (int i = 0; i < n; i++) {
            bu[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            seen[bu[i][0]][bu[i][1]] = 1;
        }
        for (int i = 0; i < m; i++) {
            bk[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            seen[bk[i][0]][bk[i][1]] = -1;
        }
        boolean[][] find = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            boolean wp = false;
            for (int j = 0; j < w; j++) {
                if(seen[i][j] == -1){
                    wp = false;
                }else if(seen[i][j] == 1){
                    wp = true;
                }else {
                    find[i][j] |= wp;
                    //out.println(find[i][j] +" "+wp);
                }
            }
        }
        for (int i = 0; i < h; i++) {
            boolean wp = false;
            for (int j = w-1; j >= 0; j--) {
                if(seen[i][j] == -1){
                    wp = false;
                }else if(seen[i][j] == 1){
                    wp = true;
                }else {
                    find[i][j] |= wp;
                }
            }
        }
        for (int j = 0; j < w; j++) {
            boolean wp = false;
            for (int i = h - 1; i >= 0; i--) {
                if(seen[i][j] == -1){
                    wp = false;
                }else if(seen[i][j] == 1){
                    wp = true;
                }else {
                    find[i][j] |= wp;
                }
            }
        }
        for (int j = 0; j < w; j++) {
            boolean wp = false;
            for (int i = 0; i < h; i++) {
                if(seen[i][j] == -1){
                    wp = false;
                }else if(seen[i][j] == 1){
                    wp = true;
                }else {
                    find[i][j] |= wp;
                }
            }
        }
        int cnt = 0;
        for (int j = 0; j < w; j++) {
            //boolean wp = false;
            for (int i = h - 1; i >= 0; i--) {
                if (find[i][j]){
                    cnt++;
                }
            }
        }

        out.println(cnt + n);
    }
}
