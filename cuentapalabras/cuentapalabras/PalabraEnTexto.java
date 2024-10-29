package cuentapalabras;

public class PalabraEnTexto {
    private String palabra;
    private int veces;

    public PalabraEnTexto(String pal){
        palabra = pal.toUpperCase();
        veces = 1;
    }

    public void incrementa(){
        ++veces;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PalabraEnTexto pal) && (pal.hashCode() == this.hashCode());
    }

    @Override
    public int hashCode() {
        return palabra.hashCode();
    }

    @Override
    public String toString() {
        return palabra + ": " + veces;
    }
}
