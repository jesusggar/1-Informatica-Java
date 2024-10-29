package notas;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String nombre;
    public List<Estudiante> estudiantes = new ArrayList<>();
    public List<String> errores = new ArrayList<>();

    public Asignatura(String n, String[] ests){
        nombre = n;
        for (int i = 0; i < ests.length; ++i){
            String[] separarEstudiante = ests[i].split(";");
            if (separarEstudiante.length != 3) {
                errores.add("ERROR. Faltan datos: "+ests[i]);
            }else {
                try {
                    double nota = Double.parseDouble(separarEstudiante[2]);
                    Estudiante estudiante = new Estudiante(separarEstudiante[0], separarEstudiante[1], nota);
                    estudiantes.add(estudiante);
                } catch (NumberFormatException e) {
                    errores.add("ERROR. Calificacion no numérica: "+ests[i]);
                } catch (EstudianteException e) {
                    errores.add("ERROR. "+e.getMessage()+": "+ests[i]);
                }
            }
        }

    }

    public double getCalificacion(Estudiante est) throws EstudianteException {
        boolean existe = false;
        double calificacion = 0;
        for (int i = 0; i < estudiantes.size(); ++i) {
            if (estudiantes.get(i).equals(est)) {
                existe = true;
                calificacion = estudiantes.get(i).getCalificacion();
            }
        }
        if (!existe) {
            throw new EstudianteException("Estudiante "+est.getNombre()+" ("+est.getDni()+") no se encuentra");
        }
        return calificacion;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<String> getErrores() {
        return errores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre+": { ");
        sb.append(estudiantes);
        sb.append(" , ");
        sb.append(errores);
        sb.append(" }");
        return sb.toString();
    }

    public double getMedia() throws EstudianteException {
            if (estudiantes.size() == 0) {
                throw new EstudianteException("No hay estudiantes");
            }
            double media;
            double suma = 0;
            for (int i = 0; i < estudiantes.size(); ++i) {
                suma += estudiantes.get(i).getCalificacion();
            }
            media = suma / estudiantes.size();
            return media;

    }
}
