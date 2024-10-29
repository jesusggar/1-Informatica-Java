package jarras;

public class Jarra {
    private final int capacidad;
    private int contenido;

    public Jarra(int capacidad) {
        if (capacidad <= 0) throw new RuntimeException("El valor capacidad no puede ser 0");
        this.capacidad = capacidad;
        this.contenido = 0;
    }

    public int capacidad() {
        return capacidad;
    }

    public int contenido() {
        return contenido;
    }

    public void llena() {
        contenido = capacidad;
    }

    public void vacia() {
        contenido = 0;
    }

    public void llenaDesde(Jarra jarra2) {
        if (this == jarra2) {
            throw new RuntimeException("No se puede llenar desde una misma jarra");
        }
        int restante = capacidad - contenido;
        if (jarra2.contenido <= restante) {
            contenido += jarra2.contenido;
            jarra2.contenido = 0;
        }else {
            contenido = capacidad;
            jarra2.contenido -= restante;
        }
    }

    @Override
    public String toString() {
        return "J("+capacidad+","+contenido+")";
    }


}
