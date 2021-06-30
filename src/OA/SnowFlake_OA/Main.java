package OA.SnowFlake_OA;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    public static long closestSquaredDistance(List<Integer> x, List<Integer> y) {
        // Write your code here
        int n = x.size();
        Point[] px = new Point[n];
        Point[] py = new Point[n];
        for(int i=0;i<n;i++){
            px[i] = new Point(x.get(i),y.get(i));
            py[i] = new Point(x.get(i),y.get(i));
        }
        Arrays.sort(px,(a,b)->(a.x-b.x));
        Arrays.sort(py,(a,b)->(a.y-b.y));
        return helper(px,py,n);

    }
    static long helper(Point[] px, Point[] py, int n){
        long min = Long.MAX_VALUE;
        if(n<=3){
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(dist(px[i],px[j])<min){
                        min = dist(px[i],px[j]);
                    }
                }
            }
            return min;
        }

        int mid = n/2;
        Point mp = px[mid];
        Point[] pyl = new Point[mid];
        Point[] pyr = new Point[n-mid];
        int li = 0;
        int ri = 0;
        for(int i=0;i<n;i++){
            if(py[i].x<=mp.x&&li<mid){
                pyl[li++] = py[i];
            }else{
                pyr[ri++] = py[i];
            }
        }
        Point[] pxl = new Point[mid];
        Point[] pxr = new Point[n-mid];
        for(int i=0;i<mid;i++){
            pxl[i] = px[i];
        }
        for(int i=mid;i<n;i++){
            pxr[i-mid] = px[i];
        }
        long l = helper(pxl,pyl,mid);
        long r = helper(pxr,pyr,n-mid);
        min = Math.min(l,r);
        Point[] sep = new Point[n];
        int j = 0;
        for(int i=0;i<n;i++){
            long get = (py[i].x-mp.x)*(py[i].x-mp.x);
            if(get<min){
                sep[j++] = py[i];
            }
        }
        for(int i=0;i<j;i++){
            for(int k=i+1;k<j&&(sep[k].y-sep[i].y)*(sep[k].y-sep[i].y)<min;k++){
                if(dist(sep[i],sep[k])<min){
                    min = dist(sep[i],sep[k]);
                }
            }
        }
        return min;
    }
    static long dist(Point a, Point b){
        return (long)Math.pow(a.x-b.x,2)+ (long)Math.pow(a.y-b.y,2);
    }
}
