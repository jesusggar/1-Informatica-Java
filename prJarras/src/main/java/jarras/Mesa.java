package jarras;

public class Mesa {

    private Jarra jarraIzq;
    private Jarra jarraDer;
    public Mesa(Jarra jarra1, Jarra jarra2){
        if(jarra1 == jarra2) throw new RuntimeException("Las jarras no pueden ser iguales");
        this.jarraIzq = jarra1;
        this.jarraDer = jarra2;
    }

    public enum Posicion {
        Izquierda, Derecha;
    }
    public Mesa(int capacidad1, int capacidad2){
        jarraIzq = new Jarra(capacidad1);
        jarraDer = new Jarra(capacidad2);
        jarraIzq.vacia();
        jarraDer.vacia();

    }

    public int capacidad(Posicion pos){
        return (pos == Posicion.Izquierda) ? jarraIzq.capacidad() : jarraDer.capacidad();
    }

    public int contenido(Posicion pos){
        return (pos == Posicion.Izquierda) ? jarraIzq.contenido() : jarraDer.contenido();
    }

    public void llena(Posicion pos){
        if(pos == Posicion.Izquierda){
            jarraIzq.llena();
        }else{
            jarraDer.llena();
        }
    }

    public void vacia(Posicion pos){
        if(pos == Posicion.Izquierda){
            jarraIzq.vacia();
        }else {
            jarraDer.vacia();
        }
    }

    public void llenaDesde(Posicion pos){
        if(pos == Posicion.Izquierda){
            jarraDer.llenaDesde(jarraIzq);
        }else{
            jarraIzq.llenaDesde(jarraDer);
        }
    }

    @Override
    public String toString(){
        return "M("+jarraIzq.toString()+","+jarraDer.toString()+")";
    }
}
