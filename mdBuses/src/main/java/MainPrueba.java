import buses.Servicio;

import java.io.IOException;

public class MainPrueba {
    public static void main(String[] args) throws IOException {
        try {
            Servicio servicio = new Servicio("Málaga");
            System.out.println(servicio.getCiudad());
            servicio.leeBuses("buses.txt");
            for (int i = 0; i < servicio.getBuses().size(); ++i) {
                System.out.println(servicio.getBuses().get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
