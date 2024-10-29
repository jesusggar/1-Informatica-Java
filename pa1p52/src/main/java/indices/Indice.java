package indices;

import java.io.PrintWriter;
import java.security.KeyStore;

public interface Indice {
    void agregarFrase(String frase);

    void resolver(String delimitadores);

    void presentarIndice(PrintWriter pw);

    default void presentarIndiceConsola() {
        PrintWriter pw = new PrintWriter(System.out, true);
        presentarIndice(pw);
    }
}
