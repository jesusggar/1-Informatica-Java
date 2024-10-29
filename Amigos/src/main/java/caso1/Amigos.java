package caso1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// SIN INTERFACES Y CON ARRAYS
public class Amigos {
	private Persona [] personas;
	
	public Amigos(Persona [] personas) {
		this.personas = personas;
	}
	
	public Persona [] getPersonas() {
		return personas;
	}
	
	public List<Persona> mayoresQue(int n) {
		List<Persona> nueva = new ArrayList<>();
		int i = 0;
		for (Persona p: personas)
			if (p.getEdad() > n)
				nueva.add(p);

		return nueva;
	}
	
	public List<Persona> menoresQue(int n) {
		List<Persona> nueva = new ArrayList<>();
		for (Persona p: personas)
			if (p.getEdad() < n)
				nueva.add(p);
		return nueva;
	}
	
	public List<Persona> empiezanCon(String s) {
		List<Persona> nueva = new ArrayList<>();int i = 0;
		for (Persona p: personas)
			if (p.getNombre().startsWith(s))
				nueva.add(p);
		return nueva;
	}
}
