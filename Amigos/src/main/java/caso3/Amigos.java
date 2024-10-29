package caso3;
// CON INTERFACES, LISTAS Y FUNCIONAL
import java.util.ArrayList;
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
