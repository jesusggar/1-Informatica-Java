package indices;

import java.io.PrintWriter;
import java.util.*;

public class IndicePosicionesEnLineas extends IndiceAbstracto{
    private SortedMap<String, SortedMap<Integer, SortedSet<Integer>>> indice;

    public IndicePosicionesEnLineas() {
        super();
        indice = new TreeMap<>();
    }

    @Override
    public void resolver(String delimitadores) {
        indice.clear();

        for (int i = 0; i < texto.size(); ++i) {
            String frase = texto.get(i);
            Integer numLinea = i + 1;
            Scanner scanner = new Scanner(frase);
            scanner.useDelimiter(delimitadores);
            int posicion = 1;
            while (scanner.hasNext()) {
                String palabra = scanner.next().toLowerCase();
                SortedMap<Integer, SortedSet<Integer>> lineas = indice.getOrDefault(palabra, new TreeMap<>());
                SortedSet<Integer> posiciones = lineas.getOrDefault(numLinea, new TreeSet<>());
                posiciones.add(posicion);
                lineas.put(numLinea, posiciones);
                indice.put(palabra, lineas);
                ++posicion;
            }
            scanner.close();
        }

    }

    @Override
    public void presentarIndice(PrintWriter pw) {
        for (Map.Entry<String, SortedMap<Integer, SortedSet<Integer>>> entrada : indice.entrySet()) {
            pw.println(entrada.getKey());
            SortedMap<Integer, SortedSet<Integer>> lineas = entrada.getValue();
            for (Map.Entry<Integer, SortedSet<Integer>> entradaLinea : lineas.entrySet()) {
                SortedSet<Integer> posiciones = entradaLinea.getValue();
                StringJoiner sj = new StringJoiner(",", "<", ">");
                pw.print(entradaLinea.getKey());
                for (Integer i : posiciones) {
                    sj.add(Integer.toString(i));
                }
            }
        }
    }
}
