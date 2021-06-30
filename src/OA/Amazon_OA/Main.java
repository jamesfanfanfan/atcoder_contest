package OA.Amazon_OA;

import java.util.*;

public class Main {

    public static int multiprocessorSystem(int[] ability, int processes){
        // WRITE YOUR BRILLIANT CODE HERE
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int x : ability){
            pq.add(x);
        }
        int cnt = 0;
        while (processes > 0){
            int v = pq.poll();
            processes -= v;
            v = v / 2;
            if (v > 0) pq.add(v);
            cnt++;
        }
        return cnt;
    }

    int removeProduct(int num, ArrayList<Integer> ids, int rem)
    {
        // WRITE YOUR CODE HERE
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            int v = ids.get(i);
            mp.put(v, mp.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });
        for(int k : mp.keySet()){
            pq.add(new int[]{k, mp.get(k)});
        }
        for (int i = 0; i < rem; i++) {
            pq.poll();
        }
        return pq.size();
    }
    class Connection{
        String firstTown;
        String secondTown;
        int cost;
        Connection(){

        }
        Connection(String t1, String t2, int c){
            firstTown = t1;
            secondTown = t2;
            cost = c;
        }
    }

    public ArrayList<Connection> minimumCostConnection(int num,
                                                       ArrayList<Connection> connections)
    {
        // WRITE YOUR CODE HERE
        PriorityQueue<Connection> pq = new PriorityQueue<>((a, b)  -> {
            return Integer.compare(a.cost, b.cost);
        });
        Map<String, String> mp = new HashMap<>();
        Map<String, Integer> pars = new HashMap<>();
        for (Connection c : connections) {
            pq.add(c);
            String s1 = c.firstTown;
            String s2 = c.secondTown;
            if (!mp.containsKey(s1)){
                mp.put(s1, s1);
                pars.put(s1, 1);
            }
            if (!mp.containsKey(s2)){
                mp.put(s2, s2);
                pars.put(s2, 1);
            }
        }


        ArrayList<Connection> res = new ArrayList<>();
        while (!pq.isEmpty()){
            Connection c = pq.poll();
            if (find(mp, c.firstTown).equals(find(mp, c.secondTown))){
                continue;
            }else{
                union(mp, c.firstTown, c.secondTown, pars);
                res.add(c);
            }
        }
        return res;

    }
    String find(Map<String, String> mp, String v){
        if (mp.get(v).equals(v)){
            return v;
        }
        String get = find(mp, mp.get(v));
        mp.put(v, get);
        return get;
    }
    void union(Map<String, String> mp, String v1, String v2, Map<String, Integer> pars){
        String p1 = find(mp, v1);
        String p2 = find(mp, v2);
        if (p1.equals(p2)) return;
        int val1 = pars.get(p1);
        int val2 = pars.get(p2);
        if (val1 > val2){
            pars.put(p1, pars.getOrDefault(p1, 0) + val2);
            pars.remove(p2);
            mp.put(p2, p1);
        }else{
            pars.put(p2, pars.getOrDefault(p2, 0) + val1);
            pars.remove(p1);
            mp.put(p1, p2);
        }
    }
}
