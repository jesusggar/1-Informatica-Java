package practicaMockito.practicaSensores;

public class GestorSensores {
	private final int MAX_INTENTOS;
	private final int MAX_SENSORES;

	private ISensorTemperatura listaSensores[];
	private int listaIntentosDesconectados[];
	private int primerIndiceLibre;

	public GestorSensores() {
		MAX_SENSORES = 100;
		MAX_INTENTOS = 3;
		listaSensores = new ISensorTemperatura[MAX_SENSORES];
		listaIntentosDesconectados = new int[MAX_SENSORES];
		primerIndiceLibre = 0;
	}

	/*
	 * Registra un sensor en el gestor
	 */
	public void introducirSensor(ISensorTemperatura sensor) {
		if (primerIndiceLibre >= MAX_SENSORES) {
			throw new SensorExcepcion("No se puede introducir en un gestor de sensores lleno");
		}
		listaSensores[primerIndiceLibre] = sensor;
		listaIntentosDesconectados[primerIndiceLibre] = 0;
		primerIndiceLibre++;
	}

	/*
	 * @return el numero de sensores registrados en el gestor
	 */
	public int getNumeroSensores() {
		return primerIndiceLibre;
	}

	/*
	 * Borra el sensor con el nombre especificado
	 * Si el sensor no existe se eleva una excepcion
	 */
	public void borrarSensor(String nombre) {
		int pos = posicionSensor(nombre);
		boolean existe = pos != -1;
		if (!existe) {
			throw new SensorExcepcion("Sensor " + nombre + " no existe");
		}
		borrarSensor(pos);
	}

	/*
	 * Contacta con cada uno de los sensores registrados
	 * Si se detecta que es la tercera vez que se contacta con un sensor y no 
	 * ha estado disponible ninguna de las tres veces, se borra automaticamente del gestor 
	 */
	public void contactarSensores() {
		for (int i = primerIndiceLibre - 1; i >= 0; i--) {
			ISensorTemperatura sensor = listaSensores[i];

			if (!sensor.disponible()) {
				listaIntentosDesconectados[i]++;
				if (listaIntentosDesconectados[i] >= MAX_INTENTOS) {
					borrarSensor(i);
				}
			} else {
				listaIntentosDesconectados[i] = 0;
			}
		}
	}

	/*
	 * Obtiene la temperatura media de todos los sensores registrados que estan disponibles.
	 * Si no hay sensores registrados o ninguno esta disponible se eleva una excepcion
	 */
	public float getTemperaturaMedia() {
		if (getNumeroSensores() <= 0) {
			throw new SensorExcepcion("Temperatura media no se puede calcular con 0 sensores");
		}
		
		float sumaTemperatura = 0;
		int sensoresDisponibles = 0;
		for (int i = 0; i < primerIndiceLibre; i++) {
			ISensorTemperatura sensor = listaSensores[i];
			if( sensor.disponible()) {
				sensoresDisponibles++;
				
				float temperatura = sensor.getTemperaturaCelsius();
				if (temperatura < -90 || temperatura > 60) {
					throw new SensorExcepcion("error reading sensor " + sensor.getNombre());
				}
				sumaTemperatura += temperatura;
			}
		}
		
		if( sensoresDisponibles == 0) {
			throw new SensorExcepcion("Temperatura media no se puede calcular. Ningun sensor disponibles");
		}
		sumaTemperatura /= sensoresDisponibles;
		return sumaTemperatura;
	}

	private int posicionSensor(String nombre) {
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < primerIndiceLibre) {
			encontrado = listaSensores[i].getNombre().equals(nombre);
			i++;
		}
		return encontrado ? i - 1 : -1;
	}

	private void borrarSensor(int posicion) {
		listaSensores[posicion] = listaSensores[primerIndiceLibre - 1];
		listaIntentosDesconectados[posicion] = listaIntentosDesconectados[primerIndiceLibre - 1];
		listaSensores[primerIndiceLibre - 1] = null;
		primerIndiceLibre--;
	}

}