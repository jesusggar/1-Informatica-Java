package caso2;
// CON INTERFACES Y CON ARRAYS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Amigos {
	private Persona [] personas;
	
	public Amigos(Persona [] personas) {
		this.personas = personas;
	}
	
	public Persona [] getPersonas() {
		return personas;
	}
	
	public List<Persona> filtra(Predicado pred) {
		List<Persona> nueva = new ArrayList<>();
		for (Persona p: personas)
			if (pred.test(p))
				nueva.add(p);
		return nueva;
	}	
}
