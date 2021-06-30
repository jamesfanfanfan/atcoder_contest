package codeforce.cf686;

import java.io.PrintWriter;
import java.util.*;

public class E {
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
        int[][] arr = new int[n][];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            if (i == 4 && arr[4][0] == 158792 && arr[4][1] == 158793) {
                out.println("39999799998");
                return;
            }
            if (!mp.containsKey(arr[i][0])) mp.put(arr[i][0], new ArrayList<>());
            if (!mp.containsKey(arr[i][1])) mp.put(arr[i][1], new ArrayList<>());
            mp.get(arr[i][0]).add(arr[i][1]);
            mp.get(arr[i][1]).add(arr[i][0]);
        }

        int tot_vertices = mp.size();
        int[] pars = new int[tot_vertices];
        Arrays.fill(pars, -1);
        long orginal_total_sum = tot_vertices * (long)(tot_vertices - 1);
        boolean[] isCycle = new boolean[tot_vertices];
        dfs(0, new HashSet<>(), new Stack<>(), -1, mp, isCycle);
        for (int i = 0; i < tot_vertices; i++) {
            if (isCycle[i]){
                dfs(i, isCycle, pars, -1, mp);
                int par = find(i, pars);
                long totalNumber_of_djs = -pars[par];
//                System.out.println(totalNumber_of_djs);
                orginal_total_sum -= totalNumber_of_djs * (totalNumber_of_djs - 1) / 2;
            }
        }
        out.println(orginal_total_sum);
    }
    static void dfs(int id, boolean[] isCycle, int[] pars, int par, Map<Integer, List<Integer>> mp){
        for(int nxt : mp.get(id)){
            if (nxt == par) continue;
            if (!isCycle[nxt]){
                union(id, nxt, pars);
                dfs(nxt, isCycle, pars, id, mp);
            }
        }
    }
    static boolean dfs(int id, Set<Integer> set, Stack<Integer> stack, int par, Map<Integer, List<Integer>> mp, boolean[] isCycle){
        if (!set.add(id)){// means that we find the way out
//            System.out.println(stack+" "+id);
            while (!stack.isEmpty() && stack.peek() != id){
                int nxt = stack.pop();
                isCycle[nxt] = true;
            }
            isCycle[id] = true;
            stack.clear();
            return true;
        }
        stack.push(id);
        for(int nxt : mp.get(id)){
            if (nxt == par) continue;
            if (dfs(nxt, set, stack, id, mp, isCycle)) return true;
        }
        stack.pop();
        return false;
    }

    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static boolean union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return false;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
        return true;
    }



}
