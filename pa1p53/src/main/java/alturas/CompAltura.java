package alturas;

import java.util.Comparator;

public class CompAltura implements Comparator<Pais> {
    @Override
    public int compare(Pais o1, Pais o2) {
        return Double.compare(o1.getAltura(), o2.getAltura());
    }
}
