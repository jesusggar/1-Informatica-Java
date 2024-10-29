package parrillatv;

import java.util.Comparator;
import java.util.Objects;

public class ProgramaTV implements Comparable<ProgramaTV>, Comparator<ProgramaTV> {
    private String nombre;
    private int duracion;
    private Hora horaInicio;

    public ProgramaTV(String nombre, Hora hora, int duracion) {
        if (duracion < 0) {
            throw new ProgramacionTVException();
        }
        this.nombre = nombre;
        this.horaInicio = hora;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public Hora getHoraInicio() {
        return horaInicio;
    }

    public Hora getHoraFin() {
        Hora horaFin = horaInicio;
        horaFin.incrementar(duracion);
        return horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramaTV that = (ProgramaTV) o;
        return duracion == that.duracion && nombre.equalsIgnoreCase(that.nombre) && Objects.equals(horaInicio, that.horaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toUpperCase(), duracion, horaInicio);
    }

    @Override
    public int compareTo(ProgramaTV o) {
        int cmp = this.horaInicio.compareTo(o.horaInicio);
        if (cmp == 0) {
            cmp = Integer.compare(this.duracion, o.duracion);
            if (cmp == 0) {
                cmp = this.nombre.compareToIgnoreCase(o.nombre);
            }
        }
        return cmp;
    }

    @Override
    public String toString() {
        return nombre.toUpperCase() + horaInicio + "-" + duracion;
    }

    @Override
    public int compare(ProgramaTV o1, ProgramaTV o2) {
        int cmp = o1.nombre.compareToIgnoreCase(o2.nombre);
        if (cmp == 0) {
            cmp = Integer.compare(o1.duracion, o2.duracion);
            if (cmp == 0) {
                cmp = o1.horaInicio.compareTo(o2.horaInicio);
            }
        }
        return cmp;
    }
}
