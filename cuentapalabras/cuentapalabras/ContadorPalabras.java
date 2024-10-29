package cuentapalabras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.util.Locale.ENGLISH;

public class ContadorPalabras {
    private List<PalabraEnTexto> palabras;

    public ContadorPalabras(){
        palabras = new ArrayList<>();
    }
    private int esta(String pal){
        PalabraEnTexto auxiliar = new PalabraEnTexto(pal);
        return palabras.indexOf(auxiliar);
    }

    protected void incluye(String pal){
        if (pal != null){
            if (esta(pal) != -1){
                palabras.get(esta(pal)).incrementa();
            }else {
                palabras.add(new PalabraEnTexto(pal));
            }
        }
    }

    private void incluyeTodas(String linea, String del){
        Scanner sc = new Scanner(linea);
        sc.useDelimiter(del);
        sc.useLocale(ENGLISH);
        while (sc.hasNext()){
            incluye(sc.next());
        }
    }
    public void incluyeTodas(String[] texto, String del){
        for(String txt : texto){
            Scanner sc = new Scanner(txt);
            while (sc.hasNextLine()){
                incluyeTodas(sc.nextLine(), del);
            }
        }
    }
    public void incluyeTodasFichero(String nomFich, String del) throws IOException {
        try {
            File file = new File(nomFich);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){ //creo que se hace con este while, ns si se peude hacer utilizando otro metodo anterior hecho
                incluyeTodas(sc.nextLine(), del);
            }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public PalabraEnTexto encuentra(String pal) throws NoSuchElementException{
        int pos = -1;
        PalabraEnTexto palabraEnTexto = new PalabraEnTexto(pal);
        pos = (palabras.indexOf(palabraEnTexto));
        if(pos == -1){
            throw new NoSuchElementException("No existe la palabra " + pal);
        }
        return palabras.get(pos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < palabras.size(); ++i){
            sb.append(palabras.get(i).toString());
            if (i != palabras.size()-1){
                sb.append(" - ");
            }
        }
        sb.append("]");
        return sb.toString(); //idk
    }

    public void presentaPalabras(String fichero) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new File(fichero))){ //idk
            presentaPalabras(pw);
        }catch (FileNotFoundException e){
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public void presentaPalabras(PrintWriter pw){
        for (PalabraEnTexto pal : palabras){
            pw.println(pal);
        }
    }
}
