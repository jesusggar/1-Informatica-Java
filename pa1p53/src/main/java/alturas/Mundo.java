package alturas;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Mundo {
    private List<Pais> paises;

    public Mundo() {
        paises = new ArrayList<>();
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void cargar(String fich) throws IOException {
        try {
            Scanner scanner = new Scanner(new File("fich.txt"));
            while (scanner.hasNextLine()) {
                String[] separar = scanner.nextLine().split(",");
                if (separar.length == 3) {
                    Pais pais = new Pais(separar[0], separar[1], Double.valueOf(separar[2]));
                    paises.add(pais);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Revisar que el formato de salida esté bien
    public static <K, V> void presentaEnConsola(Map<K,V> map) {
        PrintWriter pw = new PrintWriter(System.out, true);
        for (Map.Entry<K,V> entrada : map.entrySet()) {
            pw.println(entrada);
        }
    }

    public Map<String, Integer> numeroDePaisesPorContinente() {
        Map<String, Integer> numPaisesPorCont = new HashMap<>();
        for (Pais pais : paises) {
            String continente = pais.getContinente();
            int numPaises = numPaisesPorCont.getOrDefault(continente, 0);
            numPaisesPorCont.put(continente, numPaises + 1);
        }
        return numPaisesPorCont;
    }

    public Map<Double, List<Pais>> paisesPorAltura() {
        Map<Double, List<Pais>> paisesPorAltura = new HashMap<>();
        for (Pais pais : paises) {
            double altura = truncarDecimal(pais.getAltura(), 1);
            paisesPorAltura.putIfAbsent(altura, new ArrayList<>());
            paisesPorAltura.get(altura).add(pais);
        }
        return paisesPorAltura;
    }

    public Map<String, List<Pais>> paisesPorContinente() {
        Map<String, List<Pais>> paisesPorContinente = new TreeMap<>();
        for (Pais pais : paises) {
            String continente = pais.getContinente();
            paisesPorContinente.putIfAbsent(continente, new ArrayList<>());
            paisesPorContinente.get(continente).add(pais);
        }
        return  paisesPorContinente;
    }

    private double truncarDecimal(double numero, int decimales) {
        double factor = Math.pow(10, decimales);
        return Math.floor(numero * factor) / factor;
    }



    // Segunda parte




    public Set<Pais> paisesOrdenadosPorAltura() {
        Set<Pais> paisesOrdenados = new TreeSet<>(new CompAltura());
        paisesOrdenados.addAll(paises);
        return paisesOrdenados;
    }

    public Map<String, Set<Pais>> paisesPorContinenteAltura() {
        Map<String, Set<Pais>> paisesPorContinenteAltura = new TreeMap<>();
        for (Pais pais : paises) {
            String continente = pais.getContinente();
            paisesPorContinenteAltura.putIfAbsent(continente, new TreeSet<>(new CompAltura()));
            paisesPorContinenteAltura.get(continente).add(pais);
        }
        return paisesPorContinenteAltura;
    }

    public Map<String, Set<Pais>> paisesPorContinenteAlturaDec() {
        Map<String, Set<Pais>> paisesPorContinenteAlturaDec = new TreeMap<>();
        for (Pais pais : paises) {
            String continente = pais.getContinente();
            paisesPorContinenteAlturaDec.putIfAbsent(continente, new TreeSet<>(new CompAltura().reversed()));
            paisesPorContinenteAlturaDec.get(continente).add(pais);
        }
        return paisesPorContinenteAlturaDec;
    }

    public Map<Character, Set<Pais>> paisesPorInicial() {
        Map<Character, Set<Pais>> paisesPorInicial = new TreeMap<>();
        for (Pais pais : paises) {
            char inicial = pais.getNombre().charAt(0);
            paisesPorInicial.putIfAbsent(inicial, new TreeSet<>(Comparator.comparing(Pais::getNombre)));
            paisesPorInicial.get(inicial).add(pais);
        }
        return paisesPorInicial;
    }

    public Map<String, Double> mediaPorContinente() {
        Map<String, Double> mediaPorContinente = new TreeMap<>();
        Map<String, List<Pais>> paisesPorContinente = paisesPorContinente();
        for (Map.Entry<String, List<Pais>> entry : paisesPorContinente.entrySet()) {
            String continente = entry.getKey();
            List<Pais> paisesEnContinente = entry.getValue();
            double alturaTotal = 0.0;
            for (Pais pais : paisesEnContinente) {
                alturaTotal += pais.getAltura();
            }
            double alturaMedia = alturaTotal / paisesEnContinente.size();
            mediaPorContinente.put(continente, alturaMedia);
        }
        return mediaPorContinente;
    }

    public List<String> continentesConMasPaises() {
        Map<String, Integer> numPaisesPorCont = numeroDePaisesPorContinente();
        List<String> continentesConMasPaises = new ArrayList<>();
        if (!numPaisesPorCont.isEmpty()) {
            int maxNumPaises = Collections.max(numPaisesPorCont.values());
            for (Map.Entry<String, Integer> entrada : numPaisesPorCont.entrySet()) {
                if (entrada.getValue() == maxNumPaises) {
                    continentesConMasPaises.add(entrada.getKey());
                }
            }
        }
        return continentesConMasPaises;
    }
}
