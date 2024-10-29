

import java.io.IOException;
import java.io.PrintWriter;

import parrillatv.ProgramacionTV;
import parrillatv.ProgramacionTVException;
import parrillatv.ProgramacionTVPublicidad;
import parrillatv.ProgramasCortos;

public class PruebaProgramacion {
	
	public static void main(String[] args) throws IOException {
		// Variable para almacenar una parrilla de programación
		ProgramacionTV parrilla;
		
		// Se inicialza parrilla con los datos del fichero programacion.txt
		parrilla = crearParrilla("programacion.txt");
		//parrilla = crearParrillaPublicidad("programacion.txt");
		
		try {
			// Se muestra la información de la parrilla en la consola de salida
			parrilla.mostrarProgramas(new PrintWriter(System.out, true));
			// Se guarda la informaci´no en el fichero parrilla.txt
			parrilla.mostrarProgramas("salida.txt");
			// Se consulta la consistencia de la parrilla de "LaSexta", que debe ser false
			// Para que sea consistente basta eliimnar la última línea del fichero.
			consistencia(parrilla, "LaSexta");
			// Se consulta la consistencia de la parrilla de "A3", que debe ser true
			consistencia(parrilla, "A3");
			// Se consulta la consistencia de la parrilla de "Cuatro", que debe producir una
			// excepción, porque la cadena Cuatro no está en la parrilla.
			consistencia(parrilla, "Cuatro");
		} catch (ProgramacionTVException pe) {
			System.err.println(pe.getMessage());
		}
		
		// Se seleccionan los programas que tienen 30 minutos o menos
		System.out.println("Los programas de menos de media hora son: \n" + parrilla.sugerencias(new ProgramasCortos(30)));
	}
	
	/**
	 * Devuelve un objeto ParrillaTV a partir de la información almacenada 
	 * en el fichero que se pasa como argumento
	 * @return	Una ParrillaTV
	 * @throws IOException 
	 */
	private static ProgramacionTV crearParrilla(String fichero) throws IOException {
		ProgramacionTV res = new ProgramacionTV();
		res.leerProgramas(fichero);
		return res;
	}
	
	/**
	 * Devuelve un objeto ParrillaTVPublicidad a partir de la información almacenada en el fichero
	 * @return Una ParrillaTVPublicidad
	 * @throws IOException 
	 */
	private static ProgramacionTV crearParrillaPublicidad(String fichero) throws IOException {		
		ProgramacionTVPublicidad res = new ProgramacionTVPublicidad();
		res.leerProgramas(fichero);
		return res;
	}
	
	private static void consistencia(ProgramacionTV parrilla, String cadena) {
		String esNoEs = parrilla.esConsistente(cadena) ? " es " : " no es ";
		System.out.println("La cadena " + cadena + esNoEs + "consistente");
	}
	
}
