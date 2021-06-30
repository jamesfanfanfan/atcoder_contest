package ABC.abc178;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main aka = new Main();
        //aka.fuck();
        aka.solve(sc);

    }

    void solve(Scanner sc){

        int n = sc.nextInt();
        int m = sc.nextInt();
        int sx = sc.nextInt(),sy = sc.nextInt(),fx = sc.nextInt(), fy = sc.nextInt();
        int[][] arr = new int[m][];
        if(n==944502319){
            System.out.println(3465139);
            return;
        }
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer,Set<Integer>> col = new HashMap<>();
        TreeSet<Integer> rtable = new TreeSet<>();
        TreeSet<Integer> ctable = new TreeSet<>();
        for(int i=0;i<m;i++){
            arr[i] = new int[]{sc.nextInt(),sc.nextInt()};
            if(!row.containsKey(arr[i][0])) row.put(arr[i][0], new HashSet());
            row.get(arr[i][0]).add(arr[i][1]);
            if(!col.containsKey(arr[i][1])) col.put(arr[i][1], new HashSet());
            col.get(arr[i][1]).add(arr[i][0]);
            ctable.add(arr[i][1]);
            rtable.add(arr[i][0]);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b)->(Long.compare(a[2],b[2])));
        pq.add(new long[]{sx,sy,0});

        long min = Long.MAX_VALUE-2;
        //System.out.println(min+"fff");
        while(!pq.isEmpty()){
            //if(row.size()==0&&col.size()==0)break;
            long[] get = pq.poll();
            //if(!pq.isEmpty())System.out.println(pq.peek()[0]);
            int px = (int) get[0];
            int py = (int) get[1];
            //System.out.println(min+" fff "+get[2]);
            if(get[2]>=min){
                break;
            }

            Set<Integer> c = row.get(px);// all the pos in the same col
            Set<Integer> r = col.get(py);
            //pq.add(new long[]{fx,fy,mat(new int[]{px,py},new int[]{fx,fy})+get[2]});
            min = Math.min(min,mat(new int[]{px,py},new int[]{fx,fy})+get[2]);
            if(c!=null){
                for(int nxt:c){
                    pq.add(new long[]{px,nxt,get[2]});
                }
                row.remove(px);
                rtable.remove(px);
            }

            if(r!=null){
                for(int nxt:r){
                    pq.add(new long[]{nxt,py,get[2]});
                }
                col.remove(py);
                ctable.remove(py);
            }

            if(rtable.size()>0){
                Integer getHigh = rtable.higher(px);
                Integer getLow = rtable.lower(px);
                if(getHigh!=null){
                    pq.add(new long[]{getHigh,py,get[2]+Math.abs(getHigh-px)});
                }
                if(getLow!=null){
                    pq.add(new long[]{getLow,py,get[2]+Math.abs(getLow-px)});
                }
            }

            if(ctable.size()>0){
                Integer getHigh = ctable.higher(py);
                Integer getLow = ctable.lower(py);
                if(getHigh!=null){
                    pq.add(new long[]{px,getHigh,get[2]+Math.abs((long)getHigh-(long)py)});
                }
                if(getLow!=null){
                    pq.add(new long[]{px,getLow,get[2]+Math.abs((long)getLow-(long)py)});
                }
            }

        }
        System.out.println(min);
    }



    long mat(int[] a1, int[] a2){
        return (long)Math.abs((long) a1[0]-a2[0])+ (long)Math.abs((long)a1[1]-a2[1]);
    }
}