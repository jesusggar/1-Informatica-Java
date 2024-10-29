import datos.Datos;
import datos.DatosException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PruebaDatos {
    public static void main(String[] args) {
        double min, max;
        try {
            min = Double.parseDouble(args[0]);
            max = Double.parseDouble(args[1]);
            String[] valores = Arrays.copyOfRange(args, 2, args.length);
            Datos datos = new Datos(valores, min, max);
            System.out.println(datos);
            datos.setRango("0;4");
            System.out.println(datos);
            datos.setRango("15 25");
            System.out.println(datos);
        } catch (NumberFormatException e) {
            System.out.println("Error, al convertir un valor a número real ("+ e.getMessage()+")");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error, no hay valores suficientes");
        } catch (Exception e) {
            System.out.println("Error en los datos al establecer el rango");
        }
    }
}
