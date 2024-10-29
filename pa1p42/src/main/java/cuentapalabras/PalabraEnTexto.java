package cuentapalabras;

import java.util.Objects;

public class PalabraEnTexto {
    protected String palabra;
    protected int veces;
    public PalabraEnTexto(String p) {
        palabra = p.toUpperCase();
        veces = 1;
    }

    public void incrementa() {
        veces++;
    }

    public boolean equals(Object o) {
        return (o instanceof PalabraEnTexto)
                && palabra.equalsIgnoreCase(((PalabraEnTexto) o).getPalabra());
    }

    public String getPalabra() {
        return palabra;
    }

    public int hashCode() {
        return Objects.hash(palabra.toUpperCase());
    }

    public String toString() {
        return palabra+": "+veces;
    }
}
