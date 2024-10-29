package parrillatv;

import java.util.Collection;
import java.util.SortedSet;

public interface SugerenciasTV {
    public SortedSet<ProgramaTV> sugerencias(Collection<ProgramaTV> collection);
}
