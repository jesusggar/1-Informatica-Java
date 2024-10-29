package caso2;

public class MayoresQue implements Predicado {
	private int n;
	public MayoresQue(int n) {
		this.n = n;
	}
	
	public boolean test(Persona p) {
		return p.getEdad()> n;
	}
}
