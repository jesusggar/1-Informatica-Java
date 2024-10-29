package indices;

import java.io.PrintWriter;
import java.util.*;

public class IndiceContador extends IndiceAbstracto{
    private SortedMap<String, Integer> indice;

    public IndiceContador() {
        super();
        indice = new TreeMap<>();
    }

    @Override
    public void resolver(String delimitadores) {
        indice.clear();

        for (String frase : texto) {
            Scanner scanner = new Scanner(frase);
            scanner.useDelimiter(delimitadores);

            while (scanner.hasNext()) {
                String palabra = scanner.next().toLowerCase();
                indice.put(palabra, indice.getOrDefault(palabra, 0) + 1);
            }
            scanner.close();
        }
    }

    @Override
    public void presentarIndice(PrintWriter pw) {
        for (Map.Entry<String, Integer> entrada : indice.entrySet()) {
            pw.println(entrada.getKey() + " " + entrada.getValue());
        }
    }
}
