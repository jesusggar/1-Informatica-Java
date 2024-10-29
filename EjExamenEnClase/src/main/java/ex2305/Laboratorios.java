package ex2305;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Laboratorios {
    private int maxLabs;
    private List<Solicitud> solicitudes;
    private SortedSet<Solicitud> erroresDeAsignacion;
    private SortedMap<Integer, SortedMap<Integer, List<Solicitud>>> asignaciones;

    public Laboratorios(int maxLabs) {
        if (maxLabs > 0) {
            this.maxLabs = maxLabs;
            solicitudes = new ArrayList<>();
            erroresDeAsignacion = new TreeSet<>();
            asignaciones = new TreeMap<>();
        } else {
            throw new LabException("Argumentos erróneos");
        }
    }

    protected SortedSet<Solicitud> getErroresDeAsignacion() {
        return erroresDeAsignacion;
    }

    protected void addSolicitud(Solicitud solicitud) {
        if (!solicitudes.contains(solicitud)) {
            solicitudes.add(solicitud);
        }
    }

    // Podría hacerse con split si no funciona
    public void addSolicitud(String datos) {
        try {
            String[] separar = datos.split("\"\\\\s*[;]\\\\s*");
            if (separar.length == 3) {
                Solicitud solicitud = new Solicitud(separar[0], Integer.parseInt(separar[1]), Integer.parseInt(separar[2]));
                addSolicitud(solicitud);
            } else {
                throw new LabException("Argumentos erróneos");
            }
        } catch (LabException e) {
            System.out.println("Argumentos erróneos");
        }
    }

    public SortedSet<Solicitud> getSolicitudesOrdenadas() {
        SortedSet<Solicitud> solicitudesOrdenadas = new TreeSet<>(new SolicitudComparator());
        solicitudesOrdenadas.addAll(solicitudes);
        return solicitudesOrdenadas;
    }

    public void asignarLabs() {
        erroresDeAsignacion.clear();
        asignaciones.clear();
        for (Solicitud solicitud : solicitudes) {
            asinarLabASolicitud(solicitud);
        }
    }

    protected void asinarLabASolicitud(Solicitud solicitud) {
        solicitud.setLab(-1);

        asignaciones.putIfAbsent(solicitud.getDiaSem(), new TreeMap<>());
        asignaciones.get(solicitud.getDiaSem()).putIfAbsent(solicitud.getFranja(), new ArrayList<>());

        List<Solicitud> listaSolicitudes = asignaciones.get(solicitud.getDiaSem()).get(solicitud.getFranja());
        if (listaSolicitudes.size() < maxLabs) {
            addSolicitud(solicitud);
            solicitud.setLab(listaSolicitudes.size() - 1);
        } else {
            erroresDeAsignacion.add(solicitud);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(Solicitudes: ");
        stringBuilder.append(solicitudes + ",");
        stringBuilder.append("ErroresDeAsignacion: ");
        stringBuilder.append(erroresDeAsignacion + ",");
        stringBuilder.append("Asignaciones: ");
        stringBuilder.append(asignaciones);
        return stringBuilder.toString();
    }

    public void cargarSolicitudesDeFichero(String fich) throws IOException {
        try (Scanner scanner = new Scanner(new File(fich))){
            while (scanner.hasNextLine()) {
                addSolicitud(scanner.nextLine());
            }
        }


    }

    public void guardarAsignacionesEnFichero(String fich) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fich))){
            mostrarAsignaciones(pw);
        }
    }

    protected void mostrarAsignaciones(PrintWriter pw) {
        for (Map.Entry<Integer, SortedMap<Integer, List<Solicitud>>> entradaDia : asignaciones.entrySet()) {
            int diaSem = entradaDia.getKey();
            for (Map.Entry<Integer, List<Solicitud>> entradaFranja : entradaDia.getValue().entrySet()) {
                int franja = entradaFranja.getKey();
                pw.println("DiaSem: " + diaSem + ", Franja: " + franja);
                List<Solicitud> solicitudList = entradaFranja.getValue();
                for (Solicitud solicitud : solicitudList) {
                    pw.println("Lab: " + solicitud.getLab() + ": " + solicitud);
                }
            }
        }

        for (Solicitud solicitud : erroresDeAsignacion) {
            pw.println(solicitud);
        }
    }
}
