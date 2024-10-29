import libreria.Libreria;

public class PruebaLibreria {
    public static void main(String[] args) {
        Libreria lib1 = new Libreria();
        lib1.addLibro("george orwell", "1984", 8.20);
        lib1.addLibro("Philip K. Dick", "?Sueñan los androides con ovejas eléctricas?", 3.50);
        lib1.addLibro("Isaac Asimov", "Fundación e Imperio", 9.40);
        lib1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
        lib1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
        lib1.addLibro("Isaac Asimov", "La Fundación", 7.30);
        lib1.addLibro("William Gibson", "Neuromante", 8.30);
        lib1.addLibro("Isaac Asimov", "Segunda Fundación", 8.10);
        lib1.addLibro("Isaac Newton", "arithmetica universalis", 7.50);
        lib1.addLibro("George Orwell", "1984", 6.20);
        lib1.addLibro("Isaac Newton", "Arithmetica Universalis", 10.50);
        System.out.println(lib1);
        lib1.remLibro("George Orwell", "1984");
        lib1.remLibro("Aldous Huxley", "Un Mundo Feliz");
        lib1.remLibro("Isaac Newton", "Arithmetica Universalis");
        System.out.println(lib1);
        System.out.println("PrecioFinal(Philip K. Dick, ?Sueñan los androides con ovejas eléctricas?): "+lib1.getPrecioFinal("Philip K. Dick", "?Sueñan los androides con ovejas eléctricas?") );;
        System.out.println("PrecioFinal(isaac asimov, fundación e imperio): "+lib1.getPrecioFinal("isaac asimov", "fundación e imperio"));
        System.out.println("PrecioFinal(Ray Bradbury, Fahrenheit 451): "+lib1.getPrecioFinal("Ray Bradbury", "Fahrenheit 451"));
        System.out.println("PrecioFinal(Isaac Asimov, La Fundación): "+lib1.getPrecioFinal("Isaac Asimov", "La Fundación"));
        System.out.println("PrecioFinal(william gibson, neuromante): "+lib1.getPrecioFinal("william gibson", "neuromante"));
        System.out.println("PrecioFinal(Isaac Asimov, Segunda Fundación): "+lib1.getPrecioFinal("Isaac Asimov", "Segunda Fundación"));
        System.out.println("PrecioFinal(Isaac Newton, Arithmetica Universalis):"+lib1.getPrecioFinal("Isaac Newton", "Arithmetica Universalis"));
    }
}
