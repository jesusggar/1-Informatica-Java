package caso2;

public class MenoresQue implements Predicado {
	private int n;
	public MenoresQue(int n) {
		this.n = n;
	}
	
	public boolean test(Persona p) {
		return p.getEdad()< n;
	}
}
