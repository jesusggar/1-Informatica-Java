package practicaMockito.practicaSensores;

public interface ISensorTemperatura {

	/*
	 * @return el nombre del sensor (asumimos que es unico en cada gestor)
	 */
	public String getNombre();

	/*
	 * Se asume que este metodo solo devuelve informacion fiable si el sensor esta disponible
	 * @return la temperatura en grados Celsius medida por el sensor
	 */
	public float getTemperaturaCelsius();

	/*
	 * @return si el sensor esta disponible o no
	 */
	public boolean disponible();

}