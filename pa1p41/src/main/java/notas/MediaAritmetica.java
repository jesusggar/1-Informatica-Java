package notas;
import java.util.List;

public class MediaAritmetica implements CalculoMedia{
    @Override
    public double calcula(List<Estudiante> estudiantes) throws EstudianteException {
        if (estudiantes.size() == 0) {
            throw new EstudianteException("No hay estudiantes");
        }
        double suma = 0;
        for (int i = 0; i < estudiantes.size(); ++i) {
            suma += estudiantes.get(i).getCalificacion();
        }
        return suma/estudiantes.size();
    }
}
