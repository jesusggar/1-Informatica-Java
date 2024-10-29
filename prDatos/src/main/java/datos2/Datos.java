package datos2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Datos {
    protected List<Double> datos = new ArrayList<>();
    protected List<String> errores = new ArrayList<>();
    protected double min;
    protected double max;

    public Datos(String[] dts, double minimo, double maximo) {
        for (int i = 0; i < dts.length; ++i) {
            try {
                double dato = Double.parseDouble(dts[i]);
                datos.add(dato);
            }catch (NumberFormatException e) {
                errores.add(dts[i]);
            }

        }
        min = minimo;
        max = maximo;
    }

    public double calcMedia() throws DatosException{
        double suma = 0;
        double total = 0;
        int numerosEnRango = 0;
        boolean hayDatos = false;
        for (int i = 0; i < datos.size(); ++i) {
            if (enRango(datos.get(i))) {
                suma += datos.get(i);
                hayDatos = true;
                ++numerosEnRango;
            }
        }
        if (!hayDatos) {
            throw new DatosException("No hay datos en el rango especificado");
        }
        total = suma/numerosEnRango;
        return total;
    }

    public double calcDesvTipica() throws DatosException{
        double suma = 0;
        double total;
        int numerosEnRango = 0;
        boolean hayDatos = false;
        for (int i = 0; i < datos.size(); ++i) {
            if (enRango(datos.get(i))) {
                suma += pow(datos.get(i)-calcMedia(),2);
                hayDatos = true;
                ++numerosEnRango;
            }
        }
        if (hayDatos == false) {
            throw new DatosException("No hay datos en el rango especificado");
        }
        total = suma/numerosEnRango;
        total = sqrt(total);
        return total;
    }

    public void setRango(String newRango) throws DatosException {
        try {
            int posicionPuntoComa = newRango.indexOf(';');
            String newMin = newRango.substring(0, posicionPuntoComa);
            String newMax = newRango.substring(posicionPuntoComa+1);
            min = Double.parseDouble(newMin);
            max = Double.parseDouble(newMax);
        } catch (Exception e) {
            throw new DatosException("Error en los datos al establecer el rango");
        }
    }

    public List<Double> getDatos() {
        return datos;
    }

    public List<String> getErrores() {
        return errores;
    }

    @Override
    public String toString() {
        try {
            return "Min: "+min+", Max: "+max+","+datos+","+errores+", Media: "+calcMedia()+", DesvTipica: "+calcDesvTipica();
        }catch (Exception e) {
            return "Min: "+min+", Max: "+max+","+datos+","+errores+", Media: ERROR, DesvTipica: ERROR";
        }
    }


    public boolean enRango(double dato) {
        return (dato >= min)&&(dato <= max);
    }
}
