
import buses.Criterio;
import buses.EnMatricula;
import buses.PorLinea;
import buses.Servicio;

import java.io.IOException;
import java.io.PrintWriter;

public class MainBuses {
    public static void main(String [] args) {
        Servicio servicio = new Servicio("Malaga");
        try {
        	System.out.println(servicio.getCiudad());
            servicio.leeBuses("buses.txt");
            Criterio cr1 = new PorLinea(21); // Puede simplificarse?
            servicio.guarda("linea21.txt", cr1);
            System.out.println("Autobuses de la linea 21");
            servicio.guarda(new PrintWriter(System.out,true), cr1);
            Criterio cr2 = new EnMatricula("29"); // Puede simplificarse?
            servicio.guarda("contiene29.txt", cr2);
            System.out.println("Autobuses cuya matricula contine 29");
            servicio.guarda(new PrintWriter(System.out,true), cr2);
        } catch ( IOException e) {
            System.err.println("No existe el fichero de entrada");
        }
    }
}
