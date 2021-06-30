package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/1138/B
// for this kind of problem, should first thinking the sequence (I would like to say this is a put item problem)
// the size of the input is quite confusing;
// should first figure out how many type which four
//kind of some habit : decide to divide pure clowns and pure acrobats
//for type 11, we could see that it could boost either of the division, so we add 11 to those who are in great need
//for type 00, we could see that it coule remove one from either of the division, but notice, when it comes to remove, we should see we could only remove
// pure clowns or pure acrobats (it is obvious but sometime would be ignored)

public class Circus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        char[] crr = in.next().toCharArray();
        char[] arr = in.next().toCharArray();
        int ta = 0, tb = 0, tc = 0, rm = 0;
        LinkedList<Integer>[] qs = new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            qs[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == '1' && crr[i] == '0'){
                ta++;
                qs[0].add(i);
            }else if (arr[i] == '1' && crr[i] == '1'){
                tb++;
                qs[1].add(i);
            }else if(arr[i] == '0' && crr[i] == '1') {
                tc++;
                qs[2].add(i);
            }else{
                rm++;
                qs[3].add(i);
            }
        }
        LinkedList<Integer> clownNumber = qs[2];
        LinkedList<Integer> acrobatsNumber = qs[0];
        int iniCl = clownNumber.size(), iniAc = acrobatsNumber.size();
        while (qs[1].size() > 0){
            if (clownNumber.size() > acrobatsNumber.size()){
                acrobatsNumber.add(qs[1].poll());
            }else{
                clownNumber.add(qs[1].poll());
            }
        }
        int ac = acrobatsNumber.size(), cl = clownNumber.size();
        while (qs[3].size() > 0){
            if (ac > cl){
                if (iniAc > 0){
                    ac--;
                    iniAc--;
                    clownNumber.add(acrobatsNumber.poll());
                    acrobatsNumber.add(qs[3].poll());
                }else{
                    break;
                }
            }else if (ac < cl){
                if (iniCl > 0){
                    cl--;
                    iniCl--;
                    acrobatsNumber.add(clownNumber.poll());
                    clownNumber.add(qs[3].poll());
                }else{
                    break;
                }
            }else{
                acrobatsNumber.add(qs[3].poll());
                clownNumber.add(qs[3].poll());
            }
        }
        if (ac != cl){
            out.println(-1);
        }else{
            while (!clownNumber.isEmpty()){
                out.print((clownNumber.poll() + 1)+" ");
            }
            out.println();
        }
    }
}
