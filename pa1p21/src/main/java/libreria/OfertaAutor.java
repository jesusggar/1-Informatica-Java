package libreria;

import java.util.Arrays;

public class OfertaAutor implements OfertaFlex{
    private double porcDescuento;
    private String[] autoresOferta;

    public OfertaAutor(double desc, String[] autores) {
        porcDescuento = desc;
        autoresOferta = autores;
    }

    public double getDescuento(Libro libro) {
        double descuento = 0;
        for (int i = 0; i < autoresOferta.length; ++i){
            if (autoresOferta[i].equalsIgnoreCase(libro.getAutor())) {
                descuento = porcDescuento;
            }
        }
        return descuento;
    }

    @Override
    public String toString() {
        return porcDescuento+"%"+ Arrays.toString(autoresOferta);
    }
}
