package caso1;

public class Persona  {

	private String nombre;
	private int edad;
	
	public Persona(String n, int e) {
		nombre = n;
		edad = e;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	@Override
	public String toString() {
		return nombre + ":" + edad;
	}

}









