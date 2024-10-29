public class Ej8 {
    public static boolean buscarSuma(int[] a, int x) {
        boolean encontrado = false;
        for (int i = 0; (i < a.length) || (!encontrado); ++i) {
            for (int j = i; (j < a.length) || (!encontrado); ++j) {
                if (a[i] + a[j] == x) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }
}
