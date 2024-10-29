package parrillatv;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProgramasCortos implements SugerenciasTV{
    private int durMaxima;

    public ProgramasCortos(int durMaxima) {
        this.durMaxima = durMaxima;
    }

    public SortedSet<ProgramaTV> sugerencias(Collection<ProgramaTV> collection) {
        SortedSet<ProgramaTV> programasOrdenados = new TreeSet<>(new Comparator<ProgramaTV>() {
            @Override
            public int compare(ProgramaTV o1, ProgramaTV o2) {
                int cmp = o1.getNombre().compareToIgnoreCase(o2.getNombre());
                if (cmp == 0) {
                    cmp = Integer.compare(o1.getDuracion(), o2.getDuracion());
                    if (cmp == 0) {
                        cmp = o1.getHoraInicio().compareTo(o2.getHoraInicio());
                    }
                }
                return cmp;
            }
        });
        for (ProgramaTV programaTV : collection) {
            if (programaTV.getDuracion() < durMaxima) {
                programasOrdenados.add(programaTV);
            }
        }
        return programasOrdenados;
    }
}
