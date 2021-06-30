package Leetcode_weekly_contest;

public class DailyChallange {



    public int superpalindromesInRange(String l, String r) {
        int cnt = 0;
        for(int i = 1; i <= 1e6; i++){
            StringBuilder sb = new StringBuilder(i);
            sb.reverse();
            String fk = sb.toString();
            String s = i + fk;
            if (s.compareTo(r) > 0) break;
            if (s.compareTo(l) >= 0) cnt++;
            for (int j = 0; j <= 9; j++) {
                s = i + j + fk;
                if (s.compareTo(l) >= 0 && s.compareTo(r) <= 0) cnt++;
            }
        }
        return cnt;
    }


}
