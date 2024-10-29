package buses;

import java.util.Objects;

public class Bus {
    private int codBus;
    private int codLinea;
    private String matricula;

    public Bus(int codBus, String matricula) {
        this.codBus = codBus;
        this.matricula = matricula;
    }

    public void setCodLinea(int codLinea) {
        this.codLinea = codLinea;
    }

    public int getCodBus() {
        return codBus;
    }

    public int getCodLinea() {
        return codLinea;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return codBus == bus.codBus && matricula.equalsIgnoreCase(bus.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codBus, matricula);
    }

    @Override
    public String toString() {
        return "Bus("+codBus+","+matricula+","+codLinea+")";
    }
}
