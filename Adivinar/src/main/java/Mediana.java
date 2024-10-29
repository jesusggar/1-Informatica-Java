public class Mediana {
    public static int mediana(int[] a, int[] b) {

    }

    private static int medianaDyV(int[] a, int inia, int fina,
                                  int[] b, int inib, int finb) {
        int n = fina - inia + 1;
        if (n <= 2) {
            int[] auxa = new int[n];
            int[] auxb = new int[n];
            for (int i = 0; i < auxa.length; i++) {
                auxa[i] = a[inia+i];
                auxb[i] = a[inib+i];
            }
            return medianaVectoresFB(auxa,auxb);
        } else {
            int ma = a[(inia + fina)/2];
            int mb = b[(inib + finb)/2];
            if (ma < mb) {
                return medianaDyV(a, ma, fina, b, inib, mb);
            } else if (ma > mb) {
                return medianaDyV(a, inia, ma, b, mb, finb);
            } else { // ma == mb
                return ma;
            }
        }
    }
}
