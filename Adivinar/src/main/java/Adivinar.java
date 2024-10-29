public class Adivinar {
    public static int adivinarFB(int n) {
        int opcion = 0;
        int intentos = 0;
        while (opcion <= n && acierto(opcion) != 0){
            opcion++;
            intentos++;
        }
        return intentos;
    }

    public static int adivinar(int n) {
        return adivinar(0, n);
    }

    public static int adivinar(int min, int max){
        int intentos = 1;
        int m = (min+max)/2;
        int res = acierto(m);
        if (res > 0) {
            intentos += adivinar(min, m-1);
        } else if (res < 0) {
            intentos += adivinar(m+1, max);
        }
        return intentos;
    }
}
