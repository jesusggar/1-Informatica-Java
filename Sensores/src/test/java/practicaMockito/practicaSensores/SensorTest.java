package practicaMockito.practicaSensores;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SensorTest {
    private GestorSensores gestorSensores;

    @BeforeEach
    void setUp() {
        gestorSensores = new GestorSensores();
    }

    @AfterEach
    void tearDown() {
        gestorSensores = null;
    }
    @Test
    public void inicialmenteElNumeroDeSensoresDelGestorEsCero() {
        assertEquals(0, gestorSensores.getNumeroSensores(), "El número inicial de sensores no es 0");
    }

    @Test
    public void siSeBorraUnSensorNoExistenteSeElevaExcepcion() {
        SensorExcepcion excepcion =
                assertThrows(SensorExcepcion.class, () -> gestorSensores.borrarSensor("sensor"));
        assertEquals("Sensor sensor no existe", excepcion.getMessage(), "No se lanza la excepción de borrarSensor");
    }

    @Test
    public void siSeObAeneLaTemperaturaMediaEnUnGestorVacioSeElevaExcepcion() {
        SensorExcepcion excepcion =
                assertThrows(SensorExcepcion.class, () -> gestorSensores.getTemperaturaMedia());
        assertEquals("Temperatura media no se puede calcular con 0 sensores", excepcion.getMessage(), "No se lanza la excepción de temperaturaMedia");
    }

    @Test
    public void siSeIntroduceUnSensorEnUnGestorLlenoSeElevaExcepcion() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        for (int i = 1; i <= 100; ++i) {
            gestorSensores.introducirSensor(sensorTemperatura);
        }
        SensorExcepcion excepcion =
                assertThrows(SensorExcepcion.class, () -> gestorSensores.introducirSensor(sensorTemperatura));
        assertEquals("No se puede introducir en un gestor de sensores lleno", excepcion.getMessage(), "No se lanza la excepción de introducirSensor");
    }

    @Test
    public void siSeBorraUnSensorDelGestorSeDecrementaEnUnoElNumeroDeSensores() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        gestorSensores.introducirSensor(sensorTemperatura);
        int numeroInicial = gestorSensores.getNumeroSensores();
        when(sensorTemperatura.getNombre()).thenReturn("sensor");
        gestorSensores.borrarSensor(sensorTemperatura.getNombre());
        assertEquals(numeroInicial-1, gestorSensores.getNumeroSensores(), "No se borran los sensores correctamente");
    }

    @Test
    public void siAlgunSensorTieneTemperaturaFueraDeRangoObtenerLaTemperaturaMediaElevaUnaExcepcion() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        gestorSensores.introducirSensor(sensorTemperatura);
        when(sensorTemperatura.getNombre()).thenReturn("sensor");
        when(sensorTemperatura.getTemperaturaCelsius()).thenReturn(70F);
        when(sensorTemperatura.disponible()).thenReturn(true);
        SensorExcepcion excepcion =
                assertThrows(SensorExcepcion.class, () -> gestorSensores.getTemperaturaMedia());
        assertEquals("error reading sensor "+sensorTemperatura.getNombre(), excepcion.getMessage(), "No se detectan los sensores con temperaturas fuera de rango");
    }

    @Test
    public void laTemperaturaMediaDeTresSensoresObtenidaATravesDelGestorEsCorrecta() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        for (int i = 1; i <= 3; ++i) {
            gestorSensores.introducirSensor(sensorTemperatura);
        }
        when(sensorTemperatura.disponible()).thenReturn(true);
        when(sensorTemperatura.getTemperaturaCelsius()).thenReturn(20F);
        when(sensorTemperatura.getNombre()).thenReturn("sensor");
        assertEquals(20F, gestorSensores.getTemperaturaMedia(), "No se calcula la temperatura media correctamente");
    }

    @Test
    public void siSeContactaTresVecesConSensoresDisponiblesNoSeBorraNinguno() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        gestorSensores.introducirSensor(sensorTemperatura);
        when(sensorTemperatura.getNombre()).thenReturn("sensor");
        when(sensorTemperatura.disponible()).thenReturn(true);
        for (int i = 1; i <=3; ++i){
            gestorSensores.contactarSensores();
        }
        assertEquals(1, gestorSensores.getNumeroSensores(), "El número de sensores tras contactar 3 veces con sensores disponiblesno es correcto");
    }

    @Test
    public void siSeContactaTresVecesConUnSensorNoDisponibleSeBorraDelGestor() {
        ISensorTemperatura sensorTemperatura = mock(ISensorTemperatura.class);
        gestorSensores.introducirSensor(sensorTemperatura);
        when(sensorTemperatura.getNombre()).thenReturn("sensor");
        when(sensorTemperatura.disponible()).thenReturn(false);
        for (int i = 1; i <=3; ++i){
            gestorSensores.contactarSensores();
        }
        assertEquals(0, gestorSensores.getNumeroSensores(), "El número de sensores tras contactar 3 veces con sensores no disponibles no es correcto");
    }
}