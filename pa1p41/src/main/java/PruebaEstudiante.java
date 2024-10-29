import notas.Estudiante;
import notas.EstudianteException;

public class PruebaEstudiante {
    public static void main(String[] args) {
        try {
            Estudiante estudiante1 = new Estudiante("22456784F","Gonzalez Perez, Juan", 5.5);
            Estudiante estudiante2 = new Estudiante("33456777S","Gonzalez Perez, Juan", 3.4);
            System.out.println(estudiante1.getNombre());
            System.out.println(estudiante1.getCalificacion());
            System.out.println(estudiante2.getNombre());
            System.out.println(estudiante2.getCalificacion());
            boolean iguales = estudiante1.equals(estudiante2);
            System.out.println(iguales);
        } catch (EstudianteException e) {
            System.out.println(e.getMessage());
        }

    }
}
