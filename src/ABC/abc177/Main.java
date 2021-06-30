package ABC.abc177;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        m.solve(sc);
    }
    int[] gp;
    //Integer[][] aka;
    //char[][] arr;
    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    boolean[][] aka;
    void solve(Scanner sc){
        Map<Integer,Set<Integer>> mp = new HashMap<>();
        int h=sc.nextInt(),w=sc.nextInt(),m=sc.nextInt();
        int[][] arr = new int[m][2];
        for(int i=0;i<m;i++){
            arr[i] = new int[]{sc.nextInt()-1,sc.nextInt()-1};
        }
        int[][] col = new int[w][2];
        int[][] row = new int[h][2];
        for(int i=0;i<w;i++){
            col[i] = new int[]{i,0};
        }
        for(int i=0;i<h;i++){
            row[i] = new int[]{i,0};
            mp.put(i,new HashSet<Integer>());
        }
        for(int i=0;i<m;i++){
            int k = arr[i][0];
            int p = arr[i][1];
            col[p][1]++;
            row[k][1]++;
            mp.get(k).add(p);
        }
        //System.out.println(row[0][1]+" "+row[2][1]+ " col:"+col[0][1]+" "+col[2][1]);
        Arrays.sort(col,(a,b)->(b[1]-a[1]));
        int max = 0;
        for(int i=0;i<h;i++){
            int find = 0;
            for(int j=0;i<w;j++){
                if(!mp.get(i).contains(col[j][0])){
                    find = Math.max(find,col[j][1]+row[i][1]);
                    break;
                }else if(col[j][1]+row[i][1]-1<find){
                    break;
                }else{
                    find = col[j][1]+row[i][1]-1;
                }
            }
            max = Math.max(max,find);
        }
        System.out.println(max);
    }

    int find(int v){
        if(gp[v]<0) return v;
        //System.out.println(v);
        return gp[v] = find(gp[v]);
    }

    void g(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa==pb) return;
        if(gp[pa]<gp[pb]){
            gp[pa]+=gp[pb];
            gp[pb] = pa;
        }else{
            gp[pb]+=gp[pa];
            gp[pa] =pb;
        }
    }
    static long cmp(int[] a, int[] b){
        return (long)Math.abs(a[0]-b[0])+(long)Math.abs(a[1]-b[1]);
    }
}