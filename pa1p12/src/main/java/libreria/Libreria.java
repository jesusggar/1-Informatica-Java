package libreria;
import java.util.ArrayList;

public class Libreria {
    ArrayList<Libro> libs = new ArrayList<>();

    public Libreria() {
        this.libs = new ArrayList<>();
    }

    public void addLibro(String autor, String titulo, double precio) {
        Libro libro = new Libro(autor,titulo,precio);
        this.anyadirLibro(libro);
    }

    public void remLibro(String autor, String titulo) {
        if (buscarLibro(autor, titulo) != -1) {
            libs.remove(buscarLibro(autor, titulo));
        } else {
            throw new RuntimeException("Libro no encontrado( "+autor+" , "+titulo+" )");
        }
    }

    public double getPrecioFinal(String autor, String titulo) {
        int posicion = buscarLibro(autor, titulo);
        if (posicion != -1) {
            return libs.get(posicion).getPrecioFinal();
        } else {
            throw new RuntimeException("Libro no encontrado ( "+autor+" , "+titulo+" )");
        }
    }

    @Override
    public String toString() {
        return libs.toString();
    }

    public void anyadirLibro(Libro libro) {
        int posicion = buscarLibro(libro.getAutor(), libro.getTitulo());
        if (posicion != -1) {
            libs.set(posicion, libro);
        } else {
            libs.add(libro);
        }
    }

    private int buscarLibro(String autor, String titulo) {
        for (int i = 0; i < libs.size(); i++) {
            Libro libro = libs.get(i);
            if ((libro.getAutor().equalsIgnoreCase(autor)) && (libro.getTitulo().equalsIgnoreCase(titulo))) {
                return i;
            }
        }
        return -1;
    }
}
