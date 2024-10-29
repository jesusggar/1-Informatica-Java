package buses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servicio {
    private String ciudad;
    private List<Bus> buses;

    public Servicio(String ciudad) {
        this.ciudad = ciudad;
        buses = new ArrayList<>();
    }

    //No se si está bien, y puede que falte comprobar si separar mide 3
    public void leeBuses(String file) throws IOException {
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                incluir(linea, ",");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void incluir(String linea, String del) {
        String[] separar = linea.split(del);
        try {
            Bus bus = new Bus(Integer.parseInt(separar[0]), separar[1]);
            bus.setCodLinea(Integer.parseInt(separar[2]));
            buses.add(bus);
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error, faltan datos en "+linea);
        }catch (NumberFormatException e) {
            System.out.println("Error en dato numérico en "+linea);
        }
    }

    public List<Bus> filtra(Criterio criterio) {
        List<Bus> busList = new ArrayList<>();
        for (int i = 0; i < buses.size(); ++i) {
            if (criterio.esSeleccionable(buses.get(i))) {
                busList.add(buses.get(i));
            }
        }
        return busList;
    }

    public void guarda(String file, Criterio criterio) throws FileNotFoundException {
        try {
            PrintWriter printWriter = new PrintWriter(new File(file));
            guarda(printWriter, criterio);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void guarda(PrintWriter pw, Criterio criterio) {
        List<Bus> busesFiltrados = filtra(criterio);
        for (int i = 0; i < busesFiltrados.size(); ++i) {
            pw.println(busesFiltrados.get(i).toString());
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Bus> getBuses() {
        return buses;
    }
}

