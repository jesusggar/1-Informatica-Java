package libreria;


import java.security.Signature;

public class LibroOferta extends Libro{
    private double porcDescuento;
    public LibroOferta(String autorL, String tituloL, double precioL, double porcDesc) {
        super(autorL, tituloL, precioL);
        porcDescuento = porcDesc;
    }

    public double getDescuento() {
        return porcDescuento;
    }

    protected double getBaseImponible() {
        return getPrecioBase()-getPrecioBase()*porcDescuento/100;
    }

    @Override
    public String toString() {
        return "("+getAutor()+"; "+getTitulo()+"; "+getPrecioBase()+"; "+getDescuento()+"%; "+getBaseImponible()+"; "+getIVA()+"%; "+getPrecioFinal()+")";
    }
}
