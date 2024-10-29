package notas;

import java.time.Period;
import java.util.Objects;

public class Estudiante {
    public final String dni;
    public final String nombre;
    public final double nota;

    public Estudiante(String estudianteDni, String estudianteNombre, double estudianteNota) throws EstudianteException {
        dni = estudianteDni;
        nombre = estudianteNombre;
        nota = estudianteNota;
        if (nota < 0) {
            throw new EstudianteException("Calificación negativa");
        }
    }

    public Estudiante(String estudianteDni, String estudianteNombre) {
        dni = estudianteDni;
        nombre = estudianteNombre;
        nota = 0;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDni() {
        return dni;
    }
    public double getCalificacion() {
        return nota;
    }

    public boolean equals(Object o) {
        return (o instanceof Estudiante)
                && nombre.equals(((Estudiante) o).getNombre())
                && dni.equalsIgnoreCase(((Estudiante) o).getDni());
    }

    public int hashCode() {
        return Objects.hash(dni.toLowerCase(), nombre);
    }

    public String toString() {
        return nombre+" ("+dni+")";
    }
}
