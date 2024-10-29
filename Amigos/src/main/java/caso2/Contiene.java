package caso2;

public class Contiene implements Predicado {
	private String s;
	public Contiene(String s) {
		this.s = s;
	}
	
	public boolean test(Persona p) {
		return p.getNombre().contains(s);
	}
}
