package libreria;

public class OfertaPrecio implements OfertaFlex{

    private double porcDescuento;
    private double umbralPrecio;

    public OfertaPrecio(double desc, double umbral) {
        porcDescuento = desc;
        umbralPrecio = umbral;
    }

    public double getDescuento(Libro libro) {
        if (libro.getPrecioBase() >= umbralPrecio) {
            return porcDescuento;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return porcDescuento+"%("+umbralPrecio+")";
    }
}
