package libreria;

public class LibreriaOfertaFlex extends Libreria{
    private OfertaFlex ofertaF;
    public LibreriaOfertaFlex(OfertaFlex oferta) {
        ofertaF = oferta;
    }

    public void setOferta(OfertaFlex oferta) {
        ofertaF = oferta;
    }

    public OfertaFlex getOferta() {
        return ofertaF;
    }

    public void addLibro(String autor, String titulo, double precio) {
        double porcDesc = ofertaF.getDescuento(new Libro(autor,titulo,precio));
        if (porcDesc != 0) {
            anyadirLibro(new LibroOferta(autor,titulo,precio,porcDesc));
        }else {
            anyadirLibro(new Libro(autor, titulo, precio));
        }
    }

    @Override
    public String toString() {
        return ofertaF.toString()+libs.toString();
    }
}
