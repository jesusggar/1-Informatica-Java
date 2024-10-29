package cuentapalabras;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContadorPalabrasSig extends ContadorPalabras{
    public List<String> noSignificativas;

    public ContadorPalabrasSig() {
        super();
        noSignificativas = new ArrayList<>();
    }

    public void leeArrayNoSig(String[] palsNS) {
        noSignificativas.clear();
        for (int i = 0; i < palsNS.length; ++i) {
            if (!palsNS[i].isEmpty())
                noSignificativas.add(palsNS[i].toUpperCase());
        }
    }

    public void leeFicheroNoSig(String filNoSig, String del) throws IOException{
        noSignificativas.clear();
        try {
            Scanner scanner = new Scanner(new File(filNoSig));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                anyadePalabrasNoSignificativas(linea, del);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anyadePalabrasNoSignificativas(String linea, String del) {
        String[] separar = linea.split(del);
        for (int i = 0; i < separar.length; ++i) {
            if (!separar[i].isEmpty()) {
                noSignificativas.add(separar[i].toUpperCase());
            }
        }
    }

    @Override
    protected void incluye(String pal) {
        if (!noSignificativas.contains(pal.toUpperCase())) {
            super.incluye(pal);
         }
    }
}
