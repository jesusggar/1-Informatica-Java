package practicaMockito.practicaSensores;

@SuppressWarnings("serial")
public class SensorExcepcion extends RuntimeException {

  public SensorExcepcion(String mensaje) {
    super(mensaje);
  }
}