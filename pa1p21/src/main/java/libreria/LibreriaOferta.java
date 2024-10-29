package libreria;

import java.util.Arrays;

public class LibreriaOferta extends Libreria{

    private double porcDescuento;
    private String[] autoresOferta;
    public LibreriaOferta(double porcDesc, String[] autorOfer){
        super();
        porcDescuento = porcDesc;
        autoresOferta = autorOfer;
    }

    public void setOferta(double newDesc, String[] newAutorOfer) {
        porcDescuento = newDesc;
        autoresOferta = newAutorOfer;
    }

    public String[] getOferta() {
        return autoresOferta;
    }

    public double getDescuento() {
        return porcDescuento;
    }

    public boolean buscarLibro(String autor, String[] autoresOfer) {
        boolean encontrado = false;
        for (int i = 0; i < autoresOferta.length; i++) {
            if (autoresOfer[i].equalsIgnoreCase(autor)){
                encontrado = true;
            }
        }
        return encontrado;
    }

    public void addLibro(String autorL, String tituloL, double precioB) {
        if (buscarLibro(autorL, autoresOferta)) {
            LibroOferta libroOfer = new LibroOferta(autorL, tituloL, precioB, porcDescuento);
            anyadirLibro(libroOfer);
        }else {
            Libro libro = new Libro(autorL, tituloL, precioB);
            anyadirLibro(libro);
        }
    }

    @Override
    public String toString() {
        return getDescuento()+"%"+ Arrays.toString(getOferta()) +libs.toString();
    }
}
