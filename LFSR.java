/*
    Names: Daniel Lyalin; Answesa Brahmachary
    Compilation: javac LFSR.java
    Execution: java LFSR.java

    Problem Statement:
        Simulate a LFSR.  Your first task is to write a class that simulates the operation of a LFSR by implementing the following API:

        public class LFSR
        -------------------------------------------------------------------------------------------------------------------
            LFSR(String seed, int tap)  //  create LFSR with the given initial seed and tap
            int step()                      //  simulate one step and return the least significant (rightmost) bit as 0 or 1
            int generate(int k)             //  simulate k steps and return k-bit integer
            String toString()                  //  return a string representation of the LFSR
*/
import java.util.ArrayList;

public final class LFSR {
    
     // instance fields
     private String seed;
     private int tap;
     private ArrayList<Integer> register = new ArrayList<Integer>();

    // constructor
    public LFSR(String seed, int tap) {
        this.seed = seed;
        for (int i = 0; i < seed.length(); i++) {
            register.add(Integer.parseInt(seed.substring(i,i+1)));
        }
        this.tap = tap;
    }

    // The step() method simulates one step of the LFSR and returns the least 
    // significant (rightmost) bit as an integer (0 or 1).
    public int step() {
        ArrayList<Integer> copy = new ArrayList<Integer>();
        for (int i = 1; i < register.size(); i++) { 
            copy.add(register.get(i));
        }
        int tapNum = register.get(register.size()-tap-1);
        int begin = register.get(0);
        if (tapNum == 1 ^ begin == 1) {
            copy.add(1);
        }
        else {
            copy.add(0);
        }
        register.clear();
        register.addAll(copy);
        return register.get(register.size()-1);
    }

    // The method generate() takes an integer k as an argument and returns a k-bit integer 
    // obtained by simulating k steps of the LFSR. 
    public int generate(int k) {
        int r = 0;
        for (int i = 0; i < k; i++) {
            r = (r*2) + step();
        }
        return r;
    }

    @Override 
    public String toString() {
        String ans = "";
        for (int i = 0; i < register.size(); i++) {
            ans += register.get(i);
        }
        return ans;
    }

}

