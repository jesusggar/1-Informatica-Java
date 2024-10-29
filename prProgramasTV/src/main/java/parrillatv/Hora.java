package parrillatv;

import java.util.Objects;

/**
 * Clase que representa instantes de tiempo en un día, indicando hora y minuto, 
 * siempre en el rango [0..23] y [0..59], respectivamente.
 * 
 * @author POO
 *
 */
public class Hora implements Comparable<Hora> {
	/**
	 * Atributo para representar la hora (debe ser un valor entero entre 0 y 23)
	 */
	private int hh;
	
	/**
	 * Atributo para representar los minutos (debe ser un valor entero entre 0 y 59)
	 */
	private int mm;
	
	/**
	 * Constructor para crear objetos de la clase, a partir de dos enteros. En caso de no estar en el rango adecuado,
	 * se obtiene la hora equivalente, considerando las horas, módulo 24. Si alguno de los parámetros es negativo 
	 * se lanza una excepción ProgramacionTVException.
	 * 
	 * @param h	Hora (entre 0 y 23)
	 * @param m	Minuto (entre 0 y 59)
	 */
	public Hora(int h, int m) {
		if (h<0 || m<0) throw new ProgramacionTVException("La hora o los minutos no pueden ser negativos");
		hh = (h + m/60) % 24;
		mm = m % 60;
	}
	
	/**
	 * Método para obtener las horas
	 * 
	 * @return	Devuelve las horas del objeto Hora
	 */
	public int getHora() {
		return hh;
	}
	
	/** 
	 * Método para obtener los minutos
	 * 	
	 * @return	Devuelve los minutos del objeto Hora
	 */
	public int getMinuto() {
		return mm;
	}
	
	/**
	 * Método para incrementar la hora en los minutos indicados como parámetro.
	 * La hora siempre se mantiene entre 0 y 23, y el minuto entre 0 y 59.
	 * @param minutos	Minutos en los que se incrementa la hora
	 */
	public void incrementar(int minutos) {
		hh = (hh + (minutos + mm) / 60) % 24;
		mm = (minutos + mm) % 60;
	}
	
	/** 
	 * Dos instantes temporales se consideran iguales cuando coinciden hora y minutos.
	 */
	@Override
	public boolean equals(Object o) {
		boolean res = o instanceof Hora;
		Hora hora = res ? (Hora) o : null;
		return res && hh == hora.hh && mm == hora.mm;
	}
	
	/**
	 * Redefinición del método hashCode, consistentemente a la redefinición de equals.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(hh,mm);
	}

	
	/**
	 * Orden natural por el que una hora es menor que otra cuando es anterior cronológicamente.
	 */
	@Override
	public int compareTo(Hora hora) {
		int res = Integer.compare(hh, hora.hh);
		if (res == 0) 
			res = Integer.compare(mm, hora.mm);
		return res;
	}
	
	/**
	 * Devuelve la diferencia en minutos entre el objeto receptor y la hora que se pasa como argumento.
	 * 
	 * @param hora	Hora
	 * @return	Diferencia en minutos
	 */
	public int diferenciaMinutos(Hora hora) {
		int minutos = hh * 60 + mm;
		int minutosHora = hora.hh * 60 + hora.mm;
		return Math.abs(minutos-minutosHora);
	}
	
	/**
	 * Representación textual del objeto con el formato:
	 * 		[hh:mm]
	 */
	@Override
	public String toString() {
		String h = (hh<10 ? "0" : "") + hh;
		String m = (mm<10 ? "0" : "") + mm;
		return "[" + h + ":" + m + "]";
	}

}
