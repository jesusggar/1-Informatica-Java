package parrillatv;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ProgramacionTV {
    protected Map<String, SortedSet<ProgramaTV>> cadenas;

    public ProgramacionTV() {
        cadenas = new HashMap<>();
    }

    public void agregar(String cadenaTV, ProgramaTV prog) {
        cadenas.putIfAbsent(cadenaTV, new TreeSet<>());
        cadenas.get(cadenaTV).add(prog);
    }

    public void leerProgramas(String fichero) throws IOException {
        try (Scanner scanner = new Scanner(new File(fichero))) {
            while (scanner.hasNextLine()) {
                try {
                    String[] separar = scanner.nextLine().split("[>@:-]+");
                    Hora hora = new Hora(Integer.parseInt(separar[2]), Integer.parseInt(separar[3]));
                    ProgramaTV programaTV = new ProgramaTV(separar[1], hora, Integer.parseInt(separar[4]));
                    cadenas.putIfAbsent(separar[0], new TreeSet<>());
                    cadenas.get(separar[0]).add(programaTV);
                } catch (Exception e) {

                }
            }
        }
    }

    public void mostrarProgramas(String fichero) throws IOException {
        try (PrintWriter pw = new PrintWriter(new File(fichero))){
            mostrarProgramas(pw);
        }
    }

    public void mostrarProgramas(PrintWriter pw) {
        for (Map.Entry<String, SortedSet<ProgramaTV>> entradaCadena : cadenas.entrySet()) {
            String cadena = entradaCadena.getKey();
            pw.println(cadena + ":");
            for (ProgramaTV programaTV : entradaCadena.getValue()) {
                pw.println("\t" + programaTV);
            }
        }
    }

    public boolean esConsistente(String cadenaTV) {
        if (!cadenas.containsKey(cadenaTV)) {
            throw new ProgramacionTVException("No se encuentra la cadena");
        }
        boolean esConsistente = true;
        SortedSet<ProgramaTV> programas = cadenas.get(cadenaTV);
        ProgramaTV programaTVAnterior = programas.first();
        for (ProgramaTV programaTV : programas) {
            if (programaTV.getHoraInicio().diferenciaMinutos(programaTVAnterior.getHoraFin()) > 0) {
                esConsistente = false;
            }
            programaTVAnterior = programaTV;
        }
        return esConsistente;
    }

    public SortedSet<ProgramaTV> sugerencias(SugerenciasTV seleccion) {
        Collection<ProgramaTV> todosLosProgramas = new HashSet<>();
        for (SortedSet<ProgramaTV> programas : cadenas.values()) {
            todosLosProgramas.addAll(programas);
        }
        return seleccion.sugerencias(todosLosProgramas);
    }
}
