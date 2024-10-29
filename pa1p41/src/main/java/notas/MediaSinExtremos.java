package notas;

import java.util.List;

public class MediaSinExtremos implements CalculoMedia{
    private double min;
    private double max;
    public MediaSinExtremos(double n, double x) {
        min = n;
        max = x;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setMin(double valor) {
        min = valor;
    }

    public void setMax(double valor) {
        max = valor;
    }

    @Override
    public double calcula(List<Estudiante> estudiantes) throws EstudianteException {
        double suma = 0;
        int estudiantesEnRango = 0;
        for (int i = 0; i <estudiantes.size(); ++i) {
             if (enRango(estudiantes.get(i))) {
                 suma += estudiantes.get(i).getCalificacion();
                 ++estudiantesEnRango;
             }
        }
        if (estudiantesEnRango == 0) {
            throw new EstudianteException("No hay estudiantes");
        }
        return suma/estudiantesEnRango;
    }

    private boolean enRango(Estudiante estudiante) {
        return estudiante.getCalificacion() >= min
                && estudiante.getCalificacion() <= max;
    }
}
