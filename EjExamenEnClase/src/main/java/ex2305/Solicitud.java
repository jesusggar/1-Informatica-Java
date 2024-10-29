package ex2305;

import java.util.Objects;

public class Solicitud implements Comparable<Solicitud>{
    String asignatura;
    int diaSem;
    int franja;
    int lab;

    public Solicitud(String asignatura, int diaSem, int franja) {
        if ((diaSem>0) && (diaSem <8) || (franja>0) && (franja<4)) {
            this.asignatura = asignatura;
            this.diaSem = diaSem;
            this.franja = franja;
            lab = -1;
        }
        else {
            throw new LabException("Argumentos erróneos");
        }
    }

    public String getAsignatura() {
        return asignatura;
    }

    public int getDiaSem() {
        return diaSem;
    }

    public int getFranja() {
        return franja;
    }

    public int getLab() {
        return lab;
    }

    public void setDiaSem(int dia){
        if ((diaSem>0) && (diaSem <8)) {
            diaSem = dia;
        }else {
            throw new LabException("Argumentos erróneos");
        }
    }

    public void setFranja(int franja) {
        if (franja < 1 || franja > 3) {
            throw new LabException("Argumentos erróneos");
        }
        this.franja = franja;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public String toString() {
        return "("+asignatura+", "+diaSem+", "+franja+", "+lab+")";
    }

    // Falta el IgnoreCase (ya no)
    @Override
    public boolean equals(Object o) {
        Solicitud solicitud = (Solicitud) o;
        return diaSem == solicitud.diaSem && franja == solicitud.franja && asignatura.equalsIgnoreCase(solicitud.asignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignatura.toUpperCase(), diaSem, franja);
    }

    @Override
    public int compareTo(Solicitud o) {
        int cmp = Integer.compare(this.diaSem, o.diaSem);
        if (cmp == 0) {
            cmp = Integer.compare(this.franja, o.franja);
            if (cmp == 0) {
                cmp = asignatura.compareToIgnoreCase(o.asignatura);
            }
        }
        return cmp;
    }
}
