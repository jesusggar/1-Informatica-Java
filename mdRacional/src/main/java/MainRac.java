import numeros.Racional;

public class MainRac {
    public static void main(String[] args) {
        Racional r1 = new Racional(3,-6);
        System.out.println(r1);
        Racional r2 = Racional.con(6,9);
        System.out.println(r2);
        Racional r3 = Racional.con(3,2);
        System.out.println(r1.suma(r2));
    }
}
