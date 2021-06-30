package ABC.ARC106;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        //pw.println("ss");
        Main arc = new Main();
        arc.D(sc);
    }


    void D(Scanner in){
        int t = in.nextInt();
        String[] ss = new String[t];
        for(int i=0;i<t;i++){
            ss[i] = in.next();
        }
        String cmp = "atcoder";
        char[] ps = cmp.toCharArray();
        // first we find the largest else we return -1;
        // we store each character in the back
        int vk = 1;
        for(String s: ss){
            Map<Character, LinkedList<Integer>> mp = new HashMap<>();
            char[] cs = s.toCharArray();
            int n = cs.length;
            for(int i=0;i<cs.length;i++){
                if(!mp.containsKey(cs[i])) mp.put(cs[i],new LinkedList<>());
                mp.get(cs[i]).add(i);
            }
            //SegTree sg = new SegTree(cs.length*2);

            int ans = Integer.MAX_VALUE;
            for(int k=0;k<8;k++){// at k place we choose larger
                Integer[] at = new Integer[n];
                Arrays.fill(at,-1);
                StringBuilder sb = new StringBuilder();
                int cnt = 0;

                for(int i=0;i<7;i++){
                    boolean find = false;
                    for(int j=0;j<n;j++){
                        if(at[j]==0) continue;
                        if(i==k){// need to find a larger
                            if(cs[j]>ps[i]){
                               find = true;
                               ans = Math.min(ans,cnt);
                               sb.append(cs[j]);
                               break;
                            }
                        }else{// find a equal or larger
                            if(cs[j]>ps[i]){
                                find = true;
                                ans = Math.min(ans,cnt);
                                sb.append(cs[j]);
                                break;
                            }else if(cs[j]==ps[i]){
                                at[j] = 0;
                                find = true;
                                sb.append(cs[j]);
                                break;
                            }
                        }
                        cnt++;
                    }
                    //if(k==1) System.out.println(sb.toString()+" jdfnsdj");
                    if(cmp.substring(0,sb.length()).compareTo(sb.toString())<0){
                        ans = Math.min(ans,cnt);
                        break;
                    }
                    //System.out.println(sb.toString());
                    if(!find) break;
                }
               // System.out.println(sb.toString()+" "+k+" "+cnt);
                if(cmp.compareTo(sb.toString())==0&&s.length()>7){
                    ans = Math.min(ans,cnt);
                }
            }
            if(ans==Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                //System.out.println(ans+" "+vk+" "+s);
                System.out.println(ans);
            }
            vk++;
        }


    }




    void C(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();

        if(m<0||m==n||m==n-1){
            System.out.println(-1);
        }else if(m>0){
            int ed = 0;
            for(int i=0;i<3*(n-1);i+=3){
                System.out.println((i+2)+" "+(i+3));
                if(i/3==(n-m-2)) {
                    //System.out.println("fff");
                    ed = i+1;
                }
            }
            System.out.println(ed+" "+(4*n+2));
        }else{
            for(int i=0;i<2*n;i+=2){
                System.out.println((i+1)+" "+(i+2));
            }
        }

    }



    void B(Scanner in, PrintWriter out){
        //System.out.println("fff");
        int n = in.nextInt();
        int m = in.nextInt();
        long[] arr = new long[n];

        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }
        for(int i=0;i<n;i++){
            arr[i] -= in.nextInt();
        }

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            edges[i] = new int[]{in.nextInt()-1,in.nextInt()-1};
        }
        long[] seen = new long[n];
        int[] par = new int[n];
        Arrays.fill(par,-1);
        boolean[] find = new boolean[n];
        for(int i=0;i<m;i++){
            int l = edges[i][0];
            int r = edges[i][1];
            union(par,l,r);
        }
        for(int[] edge : edges){
            int l = edge[0];
            int r = edge[1];
            int p = find(par,l);
            if(!find[l])seen[p] += arr[l];
            if(!find[r])seen[p] += arr[r];
            find[l] = find[r] = true;
        }
        for(int i=0;i<n;i++){
            if(seen[i]!=0){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");


    }

    int find(int[] par, int i){
        if(par[i]<0) return i;
        return (par[i] = find(par,par[i]));
    }

    void union(int[] par, int i, int j){
        int pi = find(par,i);
        int pj = find(par,j);
        if(pi==pj) return;
        if(par[pi]<par[pj]){
            par[pi]+=par[pj];
            par[pj] = pi;
        }else{
            par[pj]+=par[pi];
            par[pi] = pj;
        }
    }

}


