package libreria;

public class Libro {
    protected static double porcIva = 10.0;
    protected String autor;
    protected String titulo;
    protected double precioBase;
    public Libro(String autorL, String tituloL, double precioL){
        autor = autorL;
        titulo = tituloL;
        precioBase = precioL;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    protected double getBaseImponible() {  // @Protegido
        return precioBase;
    }

    public double getPrecioFinal() {
        return this.getBaseImponible()+(this.getBaseImponible()*(porcIva/100));
    }

    @Override
    public String toString() {
        return "("+this.autor+"; "+this.titulo+"; "+this.precioBase+"; "+porcIva+"%; "+this.getPrecioFinal()+")";
    }

    public static double getIVA() {
        return porcIva;
    }

    public static void setIVA(double IVA) {
        porcIva = IVA;
    }
}
