package notas;

import java.util.List;

public class MediaArmonica implements CalculoMedia{
    @Override
    public double calcula(List<Estudiante> estudiantes) throws EstudianteException {
        if (estudiantes.size() == 0) {
            throw new EstudianteException("No hay estudiantes");
        }
        double suma = 0;
        for (int i = 0; i < estudiantes.size(); ++i) {
            if (estudiantes.get(i).getCalificacion() > 0) {
                suma += 1/estudiantes.get(i).getCalificacion();
            }
        }
        return estudiantes.size()/suma;
    }
}
