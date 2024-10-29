package notas;

import java.util.List;

public interface CalculoMedia {
    double calcula(List<Estudiante> estudiantes) throws EstudianteException;
}
