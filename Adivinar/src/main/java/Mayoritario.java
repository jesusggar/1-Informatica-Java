public class Mayoritario {
    public static Integer mayoritarioFB(int[] a) {
        Integer res = null;
        int i = 0;
        while (res == null && i < a.length) {
            if (frecuencia(a,a[i]) > a.length/2) {
                res = a[i];
            }
            i++;
        }
        return res;
    }

    private static int frecuencia(int[] a, int x) {
        int res = 0;
        for (int i = 0; i <a.length; i++) {
            if (a[i] == x) {
                res++;
            }
        }
        return res;
    }

    public static Integer mayoritarioDyV(int[] a, int ini, int fin) {
        Integer res = null;
        int n = fin-ini+1;

        if (n == 1) {
            res = a[ini];
        } else {
            int m = (ini+fin)/2;
            Integer candidatoA = mayoritarioDyV(a, ini, m);
            Integer candidatoB = mayoritarioDyV(a, m+1, fin);
            if (candidatoA == candidatoB) {
                res = candidatoA;
            } else if (frecuencia(a,candidatoA) > n/2) {
                res = candidatoA;
            } else if (frecuencia(a, candidatoB) > n/2) {
                res = candidatoB;
            }
        }
        return res;
    }
}
