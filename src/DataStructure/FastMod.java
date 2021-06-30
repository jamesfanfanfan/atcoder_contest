package DataStructure;

public class FastMod {

    long ksc(long a, long b, long mod){
        long res = 0;
        while(b > 0){
            if((b & 1) > 0){
                res = (res + a) % mod;
            }
            a = (a <<= 1) % mod;
            b >>= 1;
        }
        return res;
    }
}
