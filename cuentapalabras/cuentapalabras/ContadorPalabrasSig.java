package cuentapalabras;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContadorPalabrasSig extends ContadorPalabras{
    List<String>noSignificativas;

    public ContadorPalabrasSig(){
        super();
        noSignificativas = new ArrayList<>();
    }

    public void leeArrayNoSig(String[] palsNS){
        noSignificativas = new ArrayList<>();
        for (String pal : palsNS){
            if (pal != null){
                noSignificativas.add(pal.toUpperCase());
            }
        }
    }
    public void leeFicheroNoSig(String filNoSig, String del) throws IOException{
        try {
            File file = new File(filNoSig);
            Scanner sc = new Scanner(file);
            sc.useDelimiter(del);
            while (sc.hasNextLine()){
                anyadePalabrasNoSignificativas(sc.nextLine(), del);
            }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    private void anyadePalabrasNoSignificativas(String linea, String del){
        Scanner sc = new Scanner(linea);
        sc.useDelimiter(del);
        while (sc.hasNext()){
            noSignificativas.add(sc.next().toUpperCase());
        }
    }

    @Override
    protected void incluye(String pal){
        if (!noSignificativas.contains(pal.toUpperCase())){
            super.incluye(pal);
        }
    }
}
