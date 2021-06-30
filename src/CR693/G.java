package CR693;

import java.io.PrintWriter;
import java.util.*;

class node{
    int begin;
    int end;
    TreeMap<Integer, node> map = new TreeMap<>();
    node(int begin, int end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    public boolean equals(Object obj) {
        node get = (node) obj;
        return Integer.compare(this.end, get.end) >= 0;
    }
}
public class G {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        PrintWriter pw = new PrintWriter(System.out);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            solve(sc, pw);
//        }
//        pw.close();
        int[][] inp = new int[][]{{2,3},{2,5},{1,8}, {1,18}, {20, 25}};
        int[] nums = new int[]{2, 5, 7, 22};
        Inteveral_cover(inp, nums);
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][2];
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        Map<Integer, Set<Integer>> par = new HashMap<>();

        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            if (!mp.containsKey(arr[i][0])) mp.put(arr[i][0], new HashSet<>());
            if (!mp.containsKey(arr[i][1])) par.put(arr[i][1], new HashSet<>());
            mp.get(arr[i][0]).add(arr[i][1]);
            par.get(arr[i][1]).add(arr[i][0]);
        }
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        LinkedList<Integer> q = new LinkedList<>();
        q.add(0);
        for (int len = -1; !q.isEmpty(); len++) {
            for (int i = q.size(); i > 0; i--) {
                int idx = q.poll();
                if (dis[idx] != Integer.MAX_VALUE) continue;
                dis[idx] = len;
                for(int x : mp.get(idx)){
                    q.add(x);
                }
            }
        }
        int[][] dp = new int[n + 1][2];

    }
//    class node{
//        int begin;
//        int end;
//        TreeMap<Integer, node> map = new TreeMap<>();
//        node(int begin, int end){
//            this.begin = begin;
//            this.end = end;
//        }
//        @Override
//        public boolean equals(Object obj) {
//            node get = (node) obj;
//            return Integer.compare(this.end, get.end) >= 0;
//        }
//    }
    static void Inteveral_cover(int[][] intervals, int[] nums){
        node root = new node(-1, -1);
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        Stack<node> stack = new Stack<>();
        for(int[] x : intervals){
            int l = x[0];
            int r = x[1];
            node neo = new node(l, r);
            while (!stack.isEmpty() && stack.peek().begin >= l){
                neo.map.put(stack.peek().end, stack.pop());
            }
            stack.push(neo);
        }
        while (!stack.isEmpty()){
            root.map.put(stack.peek().end, stack.pop());
        }
        System.out.println(root.map.size());
        for (int x : nums){
            int[] ans = new int[2];
            dfs(ans, x, root);
            System.out.println("x:"+x+" "+ Arrays.toString(ans)+" lenth:"+(ans[1] - ans[0] + 1));
        }

    }
    static void dfs(int[] ans, int val, node root){
        TreeMap<Integer, node> mp = root.map;
        Integer get = mp.ceilingKey(val);
        ans[0] = root.begin;
        ans[1] = root.end;
        //System.out.println(val+" "+get);
        if (get == null || mp.get(get).begin > val){
            return;
        }else{
            dfs(ans, val, mp.get(get));
        }
    }
}
