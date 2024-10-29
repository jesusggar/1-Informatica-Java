import notas.Asignatura;
import notas.Estudiante;
import notas.EstudianteException;

import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaAsignatura {
    public static void main(String[] args) {
        try {
            String[] ests = {"12455666F;Lopez Perez, Pedro;6.7", "33678999D;Merlo Gomez, Isabel;5.8", "23555875G;Martinez Herrera, Lucia;9.1"};
            Asignatura PA1 = new Asignatura("PA1", ests);
            System.out.println("Media de calificaciones: "+PA1.getMedia());
            for (int i = 0; i < PA1.estudiantes.size(); ++i) {
                System.out.println(PA1.estudiantes.get(i).getDni());
            }
            Estudiante estudiante = new Estudiante("12455666F", "Lopez Lopez, Pedro");
            System.out.println(PA1.getCalificacion(estudiante));
        }catch (EstudianteException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
