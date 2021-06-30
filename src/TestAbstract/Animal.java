package TestAbstract;

import java.util.HashMap;
import java.util.Map;

public class Animal {
        public final void eat(){}

    public static void main(String[] args) throws ClassNotFoundException {
        Animal aka = null;
        int i = 0;
        Map<Integer, Integer> sp = new HashMap<>();
        sp.put(i, 0);
        Map<Animal, Integer> mp = new HashMap<>();
        mp.put(aka, 1);
        Class.forName("aka");
    }
}
