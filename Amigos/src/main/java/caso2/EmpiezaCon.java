package caso2;

public class EmpiezaCon implements Predicado {
	private String s;
	public EmpiezaCon(String s) {
		this.s = s;
	}
	
	public boolean test(Persona p) {
		return p.getNombre().startsWith(s);
	}
}
