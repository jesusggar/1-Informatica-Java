package buses;

public class PorLinea implements Criterio{
    private int codLinea;

    public PorLinea(int codLinea) {
        this.codLinea = codLinea;
    }

    @Override
    public boolean esSeleccionable(Bus bus) {
        return bus.getCodLinea() == codLinea;
    }
}
