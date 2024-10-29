package indices;

import java.io.PrintWriter;
import java.util.*;

public class IndiceLineas extends IndiceAbstracto{
    private SortedMap<String, SortedSet<Integer>> indice;

    public IndiceLineas() {
        super();
        indice = new TreeMap<>();
    }

    @Override
    public void resolver(String delimitadores) {
        indice.clear();

        for (int i = 0; i < texto.size(); ++i) {
            String frase = texto.get(i);
            int numLinea = i + 1;
            Scanner scanner = new Scanner(frase);
            scanner.useDelimiter(delimitadores);
            while (scanner.hasNext()) {
                String palabra = scanner.next().toLowerCase();
                SortedSet<Integer> lineas = indice.getOrDefault(palabra, new TreeSet<>());
                lineas.add(numLinea);
                indice.put(palabra, lineas);
            }
            scanner.close();
        }
    }

    @Override
    public void presentarIndice(PrintWriter pw) {
        for (Map.Entry<String, SortedSet<Integer>> entrada : indice.entrySet()) {
            SortedSet<Integer> lineas = entrada.getValue();
            StringJoiner sj = new StringJoiner(",", "<", ">");
            for (Integer i : lineas) {
                sj.add(Integer.toString(i));
            }
            pw.println(entrada.getKey() + " " + sj);
        }
    }
}
