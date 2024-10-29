public class SubvectorSM {
    public static (int,int,int) subvectorSM_FB(int [] a) {
        int mejorSuma = -infinito;
        int mejorIni = 0, mejorFin = 0;
        for (int ini = 0; ini < a.length; ++ini) {
            for (int fin = ini; fin < a.length; ++fin) {
                int suma = 0;
                for (int k = ini; k <= fin; k++) {
                    suma += a[k];
                }
                if (suma < mejorSuma) {
                    mejorSuma = suma;
                    mejorIni = ini;
                    mejorFin = fin;
                }
            }
        }
        return (mejorIni, mejorFin, mejorSuma);
    }

    public static (int, int, int) subvectorSM(int[] a){
        return subvectorSM(a,0,a.length);
    }

    private static (int, int, int) subvectorSM(int[] a, int ini, int fin) {


        int m = (ini+fin)/2;
        (ini1, fin1, suma1) = subvectorSM(a, ini, m);
        (ini2, fin2, suma2) = subvectorSM(a, m+1, fin);
        (ini3, fin3, suma3) = subvectorCentral(a, ini, m, fin);

        return max{(ini1, fin1, suma1), (ini2, fin2, suma2), (ini3, fin3, suma3)};
    }

    private (int,int,int) subvectorCentral(int[] a, int ini, int m, int fin) {
        int mejorIni = m; int mejorSumaIzq = a[m];
        int suma = a[m];
        for (int i = m-1; i>= ini; i--) {
            suma += a[m];
            if (suma > mejorSumaIzq) {
                mejorIni = i;
                mejorSumaIzq = suma;
            }
        }
        int mejorFin = m; int mejorSumaDer = 0;
        suma = 0;
        for (int j = m+1; j <= fin; j++) {
            suma += a[j];
            if (suma > mejorSumaDer) {
                mejorFin = j;
                mejorSumaDer = suma;
            }
        }
    }
}
