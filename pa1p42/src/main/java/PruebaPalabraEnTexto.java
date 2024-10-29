import cuentapalabras.PalabraEnTexto;

public class PruebaPalabraEnTexto {
    public static void main(String[] args) {
        PalabraEnTexto palabra1 = new PalabraEnTexto("gorra");
        PalabraEnTexto palabra2 = new PalabraEnTexto("Gorra");
        palabra1.incrementa();
        System.out.println("Palabra 1 = "+palabra1);
        System.out.println("Palabra 2 = "+palabra2);
        if (palabra1.equals(palabra2)) {
            System.out.println("Las palabras son iguales");
        } else
            System.out.println("Las palabras no son iguales");
    }


}
