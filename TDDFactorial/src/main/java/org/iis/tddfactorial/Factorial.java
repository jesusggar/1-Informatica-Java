package org.iis.tddfactorial;

public class Factorial {
    int result = 1;
    public int compute(int value) {
        if ((value==0)||(value==1)){
            return 1;
        } else {
            for (int i = 2; i <= value; ++i) {
                result = result * i;
            }
        }
        return result;
    }
}
