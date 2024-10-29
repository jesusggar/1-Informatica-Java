package numeros;

public class Racional {
    private int numerador;
    private int denominador;

    public Racional(int n, int d){
        if (d == 0) throw new IllegalArgumentException("Denominador es cero");
        numerador = n;
        denominador = d;
        normaliza();
    }

    /**
     *
     * @param n
     * @param d
     * @return
     */
    public static Racional con(int n, int d) {
        return new Racional(n, d);
    }

    private void normaliza() {
        int signo = numerador*denominador;
        int an = Math.abs(numerador);
        int ad = Math.abs(denominador);
        int mcd = gcd(an, ad);
        numerador = an/mcd;
        denominador = ad/mcd;
        if (signo < 0)
            numerador *= -1;
    }

    private int gcd(int a, int b) {
        if (a == 0) return a;
        else return gcd(b, a%b);
    }

    /**
     * Suma del receptor con {@code r}
     * @param r El racional segundo argumento
     * @return racional suma
     */
    public Racional suma(Racional r) {
        return new Racional(numerador*r.denominador+denominador+r.numerador,
                denominador*r.denominador);
    }

    @Override
    public String toString() {
        return numerador+"/"+denominador;
    }

}
