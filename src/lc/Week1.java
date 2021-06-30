package lc;

import java.util.*;

public class Week1 {



    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            if (!mp.containsKey(x)) mp.put(x, new ArrayList<>());
            mp.get(x).add(i);
        }
        boolean[] seen = new boolean[n + 1];
        Set<Integer> set = new HashSet<>();
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        int[] dirs = new int[]{-1, 1};
        while (!q.isEmpty()){
            int[] get = q.poll();
            if (get[0] == n - 1) return get[1];

            for(int dir : dirs){
                int pi = dir + get[0];
                if (pi < 0 || pi >= n || set.contains(arr[pi])) continue;
                q.add(new int[]{pi, get[1] + 1});
            }
            if (!set.contains(arr[get[0]])){
                for(int x : mp.get(arr[0])){
                    if (x == get[0]) continue;
                    q.add(new int[]{x, get[1] + 1});
                }
            }
            set.add(arr[get[0]]);
        }
        return -1;
    }
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        String ak = "aeiouAEIOU";
        int l = 0, r = 0;
        for (int i = 0; i < n / 2; i++) {
            if (ak.indexOf(cs[i]) != -1){
                l++;
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (ak.indexOf(cs[i]) != -1){
                r++;
            }
        }
        return r == l;
    }
    public int eatenApples(int[] apples, int[] days) {
        int n = days.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
        int cnt = 0;
        int i = 0;
        for (; i < n; i++) {
            int day = i + 1;
            while (!pq.isEmpty() && pq.peek()[1] < day){
                pq.poll();
            }
            if (apples[i] > 0){
                pq.add(new int[]{apples[i], day + days[i] - 1});
            }
            if (!pq.isEmpty()){
                int[] arr = pq.poll();
                cnt++;
                arr[0]--;
                if (arr[0] > 0) pq.add(arr);
            }
        }
        while(!pq.isEmpty()){
            int day = i + 1;
            while (!pq.isEmpty() && pq.peek()[1] < day){
                pq.poll();
            }
            if (!pq.isEmpty()){
                int[] arr = pq.poll();
                cnt++;
                arr[0]--;
                if (arr[0] > 0) pq.add(arr);
            }
            i++;
        }
        return cnt;
    }
    public int[] findBall(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        for (int j = 0; j < m; j++) {
            int idx = j;
            boolean find = true;
            for (int i = 0; i < n; i++) {
                if (grid[i][idx] == 1){
                    if (grid[i][idx + 1] == 1){
                        idx++;
                    }else{
                        find = false;
                        break;
                    }
                }else{
                    if (grid[i][idx + 1] == -1){
                        idx--;
                    }else{
                        find = false;
                        break;
                    }
                }
            }
            if (find) res[j] = 1;
        }
        return res;
    }
    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int res = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Integer> signs = new Stack<>();
        int sign = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == ' ') continue;
            if (cs[i] == '('){
                signs.push(sign);
                nums.push(sum);
                sum = 0;
            }else if (cs[i] == ')'){
                if (!signs.isEmpty() && signs.peek() == -1){
                    sum *= -1;
                }
                sum += nums.peek();
                nums.pop();
                nums.push(sum);
            }else if (cs[i] == '+'){
                sign = 1;
            }else if (cs[i] == '-'){
                sign = -1;
            }else{
               int idx = i;
               int val = 0;
               while (idx < n && isDigit(cs[idx])){
                   val *= 10;
                   val += (cs[idx] - '0');
                   idx ++;
               }
               i = idx - 1;
               val *= sign;
               sum += val;
            }
        }
        return sum;
    }

    boolean isDigit(char c){
        if (c >= '0' && c <= '9') return true;
        return false;
    }

























    public int numDecodings(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n ; i++) {
            for (int j = i; j > 0; j--) {
                if (cs[j - 1] == '0'){
                    continue;
                }
                dp[i] += dp[j - 1];
                if (j - 2 > 0 && cs[j - 2] == '2' && cs[j - 1] <= '6'){
                    dp[i] += dp[j - 2];
                }else if (j - 2 > 0 && cs[j - 2] == '1'){
                    dp[i] += dp[j - 2];
                }
            }
        }
        return dp[n];

    }
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            }else{
                return a[0] - b[0];
            }
        });
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n ; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];
            if (!stack.isEmpty()){
                int pl = intervals[stack.peek()][0];
                int pr = intervals[stack.peek()][1];
                if (r < pr){
                    continue;
                }
            }
            stack.push(i);
        }
        return stack.size();
    }

    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0){
                pre[i] = pre[i - 1] + 1;
            }else{
                pre[i] = pre[i - 1];
            }
        }
        LinkedList<Integer> le = new LinkedList<>();
        LinkedList<Integer> ri = new LinkedList<>();
        int cnt = 0;
        int j = 0;
        int res = 0;
        int tot = 0;
        while (j < n && cnt < k){
            if (nums[j] == 1){
                if (le.size() > (k - 1) / 2){
                    ri.add(j);
                }else{
                    le.add(j);
                }
                cnt++;
            }
            j++;
        }
        for(int x : le){
            tot += Math.abs(pre[x] - pre[le.getLast()]);
        }
        for(int x : ri){
            tot += Math.abs(pre[x] - pre[le.getLast()]);
        }
        res = tot;
        for (int i = 0; j < n; j++) {
            if (nums[j] == 0) continue;
            while (nums[i] == 0){
                i++;
            }
            int l = le.getLast();
            int r = ri.getFirst();
            int dif = 0;
            dif -= (pre[l] - pre[i]);
            dif += (pre[j] - pre[r]);
            dif += (le.size() - 1) * (pre[r] - pre[l]);
            dif -= (ri.size() - 1) * (pre[r] - pre[l]);
            res = Math.min(res, dif);
            le.poll();
            ri.add(j);
            le.add(ri.removeFirst());
        }
        return res;
    }
    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        int tot = 0;
        int now = 0;
        for (int i = 0; i < n; i++) {
            int[] cs = customers[i];
            now = Math.max(now, cs[0]);
            now += cs[1];
            tot += now - cs[0];
        }
        return tot * 1.0 / n;
    }
    public int countStudents(int[] students, int[] sandwiches) {
        LinkedList<Integer> q = new LinkedList<>();
        int n = students.length;
        for (int i = 0; i < n; i++) {
            q.add(students[i]);
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (!q.isEmpty() && q.peek() != sandwiches[i] && cnt < q.size()){
                q.add(q.poll());
                cnt ++;
            }
            if (cnt >= q.size()) return q.size();
            q.poll();
        }
        return q.size();
    }
    public String maximumBinaryString(String binary) {
        char[] cs = binary.toCharArray();
        int n = cs.length;
        int zeroIdex = -1;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0'){
                if(zeroIdex != -1){
                    if (i - zeroIdex == 1){
                        cs[zeroIdex] = '1';
                        zeroIdex = i;
                    }else{
                        cs[zeroIdex] = '1';
                        cs[zeroIdex + 1] = '0';
                        cs[i] = 1;
                        zeroIdex++;
                    }
                }
            }
        }
        return new String(cs);
    }
    public int specialArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0;
        int r = n;
        int res = -1;
        while(l<=r){
            int mid = (l+r)/2;
            int idx = binarySearch(nums,mid); // the index of the element greater or equal
            int tot = n-idx;
            if(mid==tot){
                return tot;
            }else if(mid>tot){ //mid should be smaller
                r = mid-1;
            }else{// mid should be larger
                l = mid+1;
            }
        }
        return -1;
    }

    /*
     we need to find the smallest index which is larger or equal to k
     */
    int binarySearch(int[] arr, int k){
        int n = arr.length;
        int l = 0;
        int r = n;
        while(l<r){// we need to find the smallest index which is larger or equal to k
            int mid = (l+r)/2;
            if(arr[mid]>=k){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int cnt1 = 0, cnt2 = 0;
        for(int i=1;i<n;i++){// first we check with the column, should only have two kinds of column
            if(board[0][0]==board[0][i]){
                for(int j=0;j<n;j++){
                    if(board[j][0]!=board[j][i]) return -1;
                }
                cnt1++;
            }else{
                for(int j=0;j<n;j++){
                    if(board[j][0]==board[j][i]) return -1;
                }
                cnt2++;
            }
        }
        if(Math.abs(cnt1-cnt2)>1) return -1;
        cnt1 = 0;
        cnt2 = 0;
        for(int i=1;i<n;i++){// we check with the row, should only have two kinds of row
            if(board[0][0]==board[i][0]){
                for(int j=0;j<n;j++){
                    if(board[0][j]!=board[i][j]) return -1;
                }
                cnt1++;
            }else{
                for(int j=0;j<n;j++){
                    if(board[0][j]==board[i][j]) return -1;
                }
                cnt2++;
            }
        }
        if(Math.abs(cnt1-cnt2)>1) return -1;
        int ans = 0;
        if(n%2==1){
            int z = 0;
            int o = 0;
            for(int i=0;i<n;i++){// check the whole row
                if(board[0][i]==0){
                    z++;
                }else{
                    o++;
                }
            }
            if(z>o){
                for(int i=1;i<n;i+=2) if(board[0][i]!=1) ans++;
            }else{
                for(int i=1;i<n;i+=2) if(board[0][i]!=0) ans++;
            }
            z = 0;
            o = 0;
            for(int i=0;i<n;i++){// check the whole first column
                if(board[i][0]==0){
                    z++;
                }else{
                    o++;
                }
            }
            if(z>o){
                for(int i=1;i<n;i+=2) if(board[0][i]!=1) ans++;
            }else{
                for(int i=1;i<n;i+=2) if(board[0][i]!=0) ans++;
            }
            return ans;
        }else{
            int row = count(board[0],n);
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = board[i][0];
            }
            row += count(arr,n);
            return row;
        }
    }
    int count(int[] arr, int n){
        int r1 = 0, r2 = 0;
        for(int i=0;i<n;i+=2){
            if(arr[i]!=1) r1++;
        }
        for(int i=1;i<n;i+=2){
            if(arr[i]!=1) r2++;
        }
        return Math.min(r1,r2);
    }

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] cnt = new int[n];
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=n-1;i>=0;i--){
                if(grid[i][j]==0){
                    sum++;
                }else{
                    break;
                }
            }
            cnt[i] = sum;
        }
        Integer[] dp = new Integer[n];
        Arrays.fill(dp,1);
        int ans = 0;
        for(int i=0;i<n;i++){
            int tot = n-i-1;
            int aka = 0;
            for(int j=0;j<n;j++){
                if(dp[i]==1) aka++;
                if(dp[i]==1&&cnt[i]>=tot){
                    dp[i] = 0;
                    break;
                }
            }
            ans += (aka-1);
        }
        return ans;
    }

    String FindLargestString(String s, int k){
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] arr = new int[26];
        for(int i=0;i<n;i++){
            arr[cs[i]-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        //int len = 0;
        for(int i=25;i>=0;i--){
            char c = (char)(i+'a');
            int len = sb.length();
            while(arr[i]>0){
                int cnt = 0;
                while(cnt<k&&arr[i]>0){
                    cnt++;
                    arr[i]--;
                    sb.append(c);
                }
                if(arr[i]==0) continue;
                boolean find = false;
                for(int j=i-1;j>=0;j--){
                    if(arr[j]>0){
                        sb.append((char)(j+'a'));
                        arr[j]--;
                        find = true;
                        break;

                    }
                }
                if(!find){// only last character would have this situation
                    StringBuilder neo = new StringBuilder();
                    for(int j=len-1;j>=0&&arr[i]>0;j--){

                    }
                }
            }


        }
        return sb.toString();
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode root = head;
        while(head!=null){
            len++;
            head = head.next;
        }
        int st = k-(k%len);
        ListNode neo = head;
        for (int i = 0; i <st; i++) {
            neo = neo.next;
        }
        ListNode ans = neo.next;
        neo.next = null;
        ListNode aka = ans;
        while(aka.next!=null){
            aka = aka.next;
        }
        aka.next = head;
        return ans;
    }
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;
        while(l<=r){
            int mid = (l+r)/2;
            if(target==nums[mid]) return mid;
            if(target>nums[mid]){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return -1;
    }

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] dp = new int[n+2]; //we regard this as the length of longest prefix of [:i] that is also a suffix
        int k = 2;
        int ml = 0;
        for(int i=1,j=0;i<n;i++){
            while(j>0&&cs[i]!=cs[j]) j = dp[j];
            if(cs[i]==cs[j]) j++;
            ml = Math.max(ml,j+1);
            dp[k++] = j;
        }
        String ss = s.substring(0,ml);
        for(int i=ml;i<n;i+=ml){
            if(!ss.equals(s.substring(i,i+ml))) return false;
        }
        return true;
    }


}
