package parrillatv;

public class ProgramacionTVPublicidad extends ProgramacionTV{
    public ProgramacionTVPublicidad() {
        super();
    }

    @Override
    public void agregar(String cadenaTV, ProgramaTV prog) {
        super.agregar(cadenaTV, prog);
        ProgramaTV publicidad = new ProgramaTV("Publicidad", prog.getHoraFin(), 5);
        super.agregar(cadenaTV, publicidad);
    }
}
