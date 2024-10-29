import notas.*;
public class Main2 {
    static final String[] als = {
            "25653443S;Garcia Gomez, Juan;8.1",
            "23322443K;Lopez Turo, Manuel;4.3",
            "24433522M;Merlo Martinez, Juana;5.3",
            "53553421D;Santana Medina, Petra;-7.1",
            "55343442L,Godoy Molina, Marina;6.3",
            "34242432J;Fernandez Vara, Pedro;2.k",
            "42424312G;Lopez Gama, Luisa;7.1" };
    public static void main(String[] args) {
        try {
            AsignaturaMedias algebra = new AsignaturaMedias("Algebra", als);
            try {
                Estudiante al1 = new Estudiante("23322443k", "Lopez Turo, Manuel");
                Estudiante al2 = new Estudiante("34242432J", "Fernandez Vara, Pedro");
                System.out.println("Calificacion de " + al1 + ": "
                        + algebra.getCalificacion(al1));
                System.out.println("Calificacion de " + al2 + ": "
                        + algebra.getCalificacion(al2));
            } catch (EstudianteException e) {
                System.err.println(e.getMessage());
            }
            try {
                CalculoMedia m1 = new MediaAritmetica();
                CalculoMedia m2 = new MediaArmonica();
                MediaSinExtremos m3 = new MediaSinExtremos(5.0, 9.0);
                System.out.println("Media aritmética: " + algebra.getMedia(m1));
                System.out.println("Media armónica: " + algebra.getMedia(m2));
                System.out.println("Media de valores en ["+m3.getMin()+", "+m3.getMax()+"]:"+ algebra.getMedia(m3));
            } catch (EstudianteException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Estudiantes...");
            for (Estudiante estudiante : algebra.getEstudiantes()) {
                System.out.println(estudiante + ": " + estudiante.getCalificacion());
            }
            System.out.println("Errores...");
            for (String error : algebra.getErrores()) {
                System.out.println(error);
            }
            System.out.println(algebra);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}