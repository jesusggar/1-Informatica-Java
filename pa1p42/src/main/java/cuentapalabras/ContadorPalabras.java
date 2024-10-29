package cuentapalabras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ContadorPalabras {
    protected List<PalabraEnTexto> palabras;

    public ContadorPalabras() {
        palabras = new ArrayList<>();
    }
    private int esta(String pal) {
        PalabraEnTexto auxiliar = new PalabraEnTexto(pal);
        return palabras.indexOf(auxiliar);
    }

    protected void incluye(String pal) {
        if (pal.isEmpty()) {
            // No hacer nada
        } else if (esta(pal) == -1) {
            PalabraEnTexto palabra = new PalabraEnTexto(pal);
            palabras.add(palabra);
        } else {
            palabras.get(esta(pal)).incrementa();
        }
    }

    private void incluyeTodas(String linea, String del) {
        String[] separar = linea.split(del);
        for (int i = 0; i < separar.length; ++i) {
            incluye(separar[i]);
        }
    }

    public void incluyeTodas(String[] texto, String del) {
        for (int i = 0; i < texto.length; ++i) {
            incluyeTodas(texto[i], del);
        }
    }

    public void incluyeTodasFichero(String nomFich, String del) throws IOException {
        try {
            Scanner scanner = new Scanner(new File(nomFich));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                incluyeTodas(linea, del);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public PalabraEnTexto encuentra(String pal) throws NoSuchElementException{
        for (int i = 0; i < palabras.size(); ++i) {
            if (palabras.get(i).getPalabra().equalsIgnoreCase(pal))
                return palabras.get(i);
        }
        throw new NoSuchElementException("No existe la palabra "+pal);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        StringJoiner stringJoiner = new StringJoiner(" - ");
        for (int i = 0; i < palabras.size(); ++i) {
            stringJoiner.add(palabras.get(i).toString());
        }
        stringBuilder.append(stringJoiner);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void presentaPalabras(String fichero) throws FileNotFoundException {
        try {
            PrintWriter printWriter = new PrintWriter(new File(fichero));
            presentaPalabras(printWriter);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void presentaPalabras(PrintWriter pw) {
        for (int i = 0; i < palabras.size(); ++i) {
            pw.println(palabras.get(i).toString());
        }
    }
}
