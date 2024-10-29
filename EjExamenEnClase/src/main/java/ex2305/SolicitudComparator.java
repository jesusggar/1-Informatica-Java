package ex2305;

import java.util.Comparator;

public class SolicitudComparator implements Comparator<Solicitud> {
    @Override
    public int compare(Solicitud o1, Solicitud o2) {
        return o1.getAsignatura().compareToIgnoreCase(o2.getAsignatura());
    }
}
