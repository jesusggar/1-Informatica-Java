//--------------------------------------------------------------------------

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import libreria.Libro;
import libreria.Libreria;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

//--------------------------------------------------------------------------

public class TestRunnerPr12 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestLibro {
		private Libro lb1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Libro JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Libro JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lb1 = new Libro("Isaac Asimov", "Foundation", 7.30);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibroCtorTest1() {
			assertAll("LibroCtorTest1",
					() -> assertEquals("Isaac Asimov", lb1.getAutor(), "\n> Error: new Libro(\"Isaac Asimov\", Foundation, 7.30): Autor:"),
					() -> assertEquals("Foundation", lb1.getTitulo(), "\n> Error: new Libro(\"Isaac Asimov\", Foundation, 7.30): Titulo:"),
					() -> assertEquals(7.30, lb1.getPrecioBase(), 1e-6, "\n> Error: new Libro(\"Isaac Asimov\", Foundation, 7.30): BasePrice:"),
					() -> assertEquals(10.00, Libro.getIVA(), 1e-6, "\n> Error: new Libro(\"Isaac Asimov\", Foundation, 7.30): PorcIva:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibroFinalPriceTest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("Foundation", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			assertEquals(8.03, lb1.getPrecioFinal(), 1e-6, "\n> Error: lb1.getPrecioFinal(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibrosetIVATest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("Foundation", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			Libro.setIVA(20.00);
			double ivaActual = Libro.getIVA();
			Libro.setIVA(10.00);
			assertEquals(20.00, ivaActual, 1e-6, "\n> Error: Libro.setIVA(20.00): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibroToStringTest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("Foundation", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			assertEquals(normalize("(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03)"),
						 normalize(lb1.toString()),
						 "\n> Error: lb1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestLibreria {
		private Libreria lr1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Libreria JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Libreria JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lr1 = new Libreria();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		// Carlos (19/03/19) // Vicente (21/03/19)
//		private static final Class arrayLibroClass = (new ArrayList<Libro>()).getClass();
//		private static ArrayList<Libro> getArrayLibros(Libreria lb) {
//			ArrayList<Libro> libs = null;
//			boolean encontrado = false;
//			try {
//				Class LibreriaClass = lb.getClass();
//				java.lang.reflect.Field[] LibreriaFields = LibreriaClass.getDeclaredFields();
//				int i = 0;
//				while ((i < LibreriaFields.length)
//					   &&(LibreriaFields[i].getType() != arrayLibroClass)) {
//					++i;
//				}
//				if (i < LibreriaFields.length) {
//					LibreriaFields[i].setAccessible(true);
//					libs = (ArrayList<Libro>)LibreriaFields[i].get(lb);
//					encontrado = true;
//				}
//			} catch (Throwable e) {
//				fail("\n> Error: getArrayLibros has thrown an exception " + e);
//			}
//			if (! encontrado) {
//				fail("\n> Error: la clase Libreria no contiene un array de Libros Libro[]");
//			}
//			if (libs == null) {
//				fail("\n> Error: el array de Libros no se ha creado correctamente");
//			}
//			return libs;
//		} 
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaCtorTest1() {
			Libreria lr2 = new Libreria();
			assertEquals(normalize("[]"), normalize(lr2.toString()),"\n> Error: new Libreria(): toString():");
		}

		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaAddLibroTest1() {
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro1(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaAddLibroTest2() {
			lr1.addLibro("isaac asimov", "foundation", 5.30);
			lr1.addLibro("aldous huxley", "a brave new world", 4.50);
			lr1.addLibro("william gibson", "neuromancer", 6.30);
			lr1.addLibro("george orwell", "1984", 4.20);
			lr1.addLibro("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			assertEquals(normalize("[(isaac asimov; foundation; 5.3; 10.0%; 5.83), (aldous huxley; a brave new world; 4.5; 10.0%; 4.95), (william gibson; neuromancer; 6.3; 10.0%; 6.93), (george orwell; 1984; 4.2; 10.0%; 4.62), (ray bradbury; fahrenheit 451; 5.4; 10.0%; 5.94)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro2.1(): toString()");
			//------------------------
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro2.2(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriagetPrecioFinalTest1() {
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("LibreriagetPrecioFinalTest1",
					() -> assertEquals(8.03, lr1.getPrecioFinal("Isaac Asimov", "Foundation"), 1e-6, "\n> Error: getPrecioFinal(Issac Asimov, Foundation):"),
					() -> assertEquals(7.15, lr1.getPrecioFinal("Aldous Huxley", "A Brave New World"), 1e-6, "\n> Error: getPrecioFinal(Aldous Huxley, A Brave New World):"),
					() -> assertEquals(9.13, lr1.getPrecioFinal("William Gibson", "Neuromancer"), 1e-6, "\n> Error: getPrecioFinal(William Gibson, Neuromancer):"),
					() -> assertEquals(6.82, lr1.getPrecioFinal("George Orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(George Orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getPrecioFinal("Ray Bradbury", "Fahrenheit 451"), 1e-6, "\n> addLibro(): getPrecioFinal(Ray Bradbury, Fahrenheit 451):"));

		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriagetPrecioFinalTest2() {
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("LibreriagetPrecioFinalTest2",
					() -> assertEquals(8.03, lr1.getPrecioFinal("isaac asimov", "foundation"), 1e-6, "\n> Error: getPrecioFinal(isaac asimov, Foundation):"),
					() -> assertEquals(7.15, lr1.getPrecioFinal("aldous huxley", "a brave new world"), 1e-6, "\n> Error: getPrecioFinal(aldous huxley, a brave new world):"),
					() -> assertEquals(9.13, lr1.getPrecioFinal("william gibson", "neuromancer"), 1e-6, "\n> Error: getPrecioFinal(william gibson, neuromancer):"),
					() -> assertEquals(6.82, lr1.getPrecioFinal("george orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(george orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getPrecioFinal("ray bradbury", "fahrenheit 451"), 1e-6, "\n> Error: getPrecioFinal(ray bradbury, fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriagetPrecioFinalTest3() {
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lr1.getPrecioFinal("xxx", "xxx"), "\n> Error: getPrecioFinal(xxx, xxx): No RuntimeException was thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaRemLibroTest1() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.remLibro("Isaac Asimov", "Foundation");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Isaac Asimov, Foundation");
			lr1.remLibro("Aldous Huxley", "A Brave New World");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Aldous Huxley, A Brave New World");
			lr1.remLibro("William Gibson", "Neuromancer");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(William Gibson, Neuromancer");
			lr1.remLibro("George Orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(George Orwell, 1984");
			lr1.remLibro("Ray Bradbury", "Fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Ray Bradbury, Fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaRemLibroTest2() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.remLibro("isaac asimov", "foundation");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(isaac asimov, foundation");
			lr1.remLibro("aldous huxley", "a brave new world");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(aldous huxley, a brave new world");
			lr1.remLibro("william gibson", "neuromancer");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(william gibson, neuromancer");
			lr1.remLibro("george orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(george orwell, 1984");
			lr1.remLibro("ray bradbury", "fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: remLibro(ray bradbury, fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaRemLibroTest3() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lr1.remLibro("xxx", "xxx"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaToStringTest1() {
			assertEquals(normalize("[]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaToStringTest2() {
			lr1.addLibro("Isaac Asimov", "Foundation", 7.30);
			lr1.addLibro("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addLibro("William Gibson", "Neuromancer", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestLibreriaMain {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of PruebaLibreria JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of PruebaLibreria JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void LibreriaMainMainTest1() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaLibreria.main(new String[]{});
				fail("\n> Error: main(): No exception was thrown");
			} catch (RuntimeException e) {
				assertEquals(normalize("Libro no encontrado (Isaac Newton, Arithmetica Universalis)"),
							 normalize(e.getMessage()),
							 "\n> Error: Main(): exception.getMessage()"
							 );
			} catch (Exception e) {
				fail("\n> Error: main(): exception thrown is not RuntimeException");
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("[ ( George Orwell ; 1984 ; 6.200000 ; 10.000000 % ; 6.820000 ) , ( Philip K . Dick ; ? Suenan los androides con ovejas electricas ? ; 3.500000 ; 10.000000 % ; 3.850000 ) , ( Isaac Asimov ; Fundacion e Imperio ; 9.400000 ; 10.000000 % ; 10.340000 ) , ( Ray Bradbury ; Fahrenheit 451 ; 7.400000 ; 10.000000 % ; 8.140000 ) , ( Aldous Huxley ; Un Mundo Feliz ; 6.500000 ; 10.000000 % ; 7.150000 ) , ( Isaac Asimov ; La Fundacion ; 7.300000 ; 10.000000 % ; 8.030000 ) , ( William Gibson ; Neuromante ; 8.300000 ; 10.000000 % ; 9.130000 ) , ( Isaac Asimov ; Segunda Fundacion ; 8.100000 ; 10.000000 % ; 8.910000 ) , ( Isaac Newton ; Arithmetica Universalis ; 10.500000 ; 10.000000 % ; 11.550000 ) ] [ ( Philip K . Dick ; ? Suenan los androides con ovejas electricas ? ; 3.500000 ; 10.000000 % ; 3.850000 ) , ( Isaac Asimov ; Fundacion e Imperio ; 9.400000 ; 10.000000 % ; 10.340000 ) , ( Ray Bradbury ; Fahrenheit 451 ; 7.400000 ; 10.000000 % ; 8.140000 ) , ( Isaac Asimov ; La Fundacion ; 7.300000 ; 10.000000 % ; 8.030000 ) , ( William Gibson ; Neuromante ; 8.300000 ; 10.000000 % ; 9.130000 ) , ( Isaac Asimov ; Segunda Fundacion ; 8.100000 ; 10.000000 % ; 8.910000 ) ] PrecioFinal ( Philip K . Dick , ? Suenan los androides con ovejas electricas ? ) : 3.850000 PrecioFinal ( isaac asimov , fundacion e imperio ) : 10.340000 PrecioFinal ( Ray Bradbury , Fahrenheit 451 ) : 8.140000 PrecioFinal ( Isaac Asimov , La Fundacion ) : 8.030000 PrecioFinal ( william gibson , neuromante ) : 9.130000 PrecioFinal ( Isaac Asimov , Segunda Fundacion ) : 8.910000"),
						 normalize(output),
						 "\n> Error: LibreriaTest.main():"
						 );
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestLibro.class ,
				JUnitTestLibreria.class,
				JUnitTestLibreriaMain.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--JUnitTestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestLibro.class),
						selectClass(JUnitTestLibreria.class),
						selectClass(JUnitTestLibreriaMain.class))
				.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

		summary.printTo(new PrintWriter(System.out, true));
		
//		long abortedCount = summary.getTestsAbortedCount();
//		long succeededCount = summary.getTestsFoundCount();
//		long foundCount = summary.getTestsSucceededCount();
//		long skippedCount = summary.getTestsSkippedCount();
//		long failedCount = summary.getTestsFailedCount();
//		long startedCount = summary.getTestsStartedCount();
//		if (failedCount > 0) {
//			System.out.println(">>> ------");
//			summary.getFailures().forEach(failure -> System.out.println("failure - " + failure.getException()));
//		}
//		if ((abortedCount > 0)||(failedCount > 0)||(skippedCount > 0)) {
//			System.out.println(">>> ------");
//			if (skippedCount > 0) {
//				System.out.println(">>> Error: Some tests ("+skippedCount+") were ignored");
//			}
//			if (abortedCount > 0) {
//				System.out.println(">>> Error: ("+abortedCount+") tests could not be run due to errors in other methods");
//			}
//			if (failedCount > 0) {
//				System.out.println(">>> Error: ("+failedCount+") tests failed due to errors in methods");
//			}
//		}
//		if (succeededCount == foundCount) {
//			System.out.print(">>> JUnit Test Succeeded");
//		} else {
//			System.out.print(">>> Error: JUnit Test Failed");
//		}
//		System.out.println(" (Tests: "+foundCount+", Errors: "+failedCount+", ErrorPrecond: "+abortedCount+", Ignored: "+skippedCount+")");
		
//		Result result = JUnitCore.runClasses(JUnitTestSuite.class);
//		
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		if (result.wasSuccessful()) {
//			System.out.println("JUnit Test Succeeded");
//		} else {
//			System.out.println("> Error: JUnit Test Failed");
//		}
	}
	//----------------------------------------------------------------------
	//-- Utils -------------------------------------------------------------
	//----------------------------------------------------------------------
	private static char normalizeUnicode(char ch) {
		switch (ch) {
		case '\n':
		case '\r':
		case '\t':
		case '\f':
			ch = ' ';
			break;
		case '\u00C0':
		case '\u00C1':
		case '\u00C2':
		case '\u00C3':
		case '\u00C4':
		case '\u00C5':
		case '\u00C6':
			ch = 'A';
			break;
		case '\u00C7':
			ch = 'C';
			break;
		case '\u00C8':
		case '\u00C9':
		case '\u00CA':
		case '\u00CB':
			ch = 'E';
			break;
		case '\u00CC':
		case '\u00CD':
		case '\u00CE':
		case '\u00CF':
			ch = 'I';
			break;
		case '\u00D0':
			ch = 'D';
			break;
		case '\u00D1':
			ch = 'N';
			break;
		case '\u00D2':
		case '\u00D3':
		case '\u00D4':
		case '\u00D5':
		case '\u00D6':
			ch = 'O';
			break;
		case '\u00D9':
		case '\u00DA':
		case '\u00DB':
		case '\u00DC':
			ch = 'U';
			break;
		case '\u00DD':
			ch = 'Y';
			break;
		case '\u00E0':
		case '\u00E1':
		case '\u00E2':
		case '\u00E3':
		case '\u00E4':
		case '\u00E5':
		case '\u00E6':
			ch = 'a';
			break;
		case '\u00E7':
			ch = 'c';
			break;
		case '\u00E8':
		case '\u00E9':
		case '\u00EA':
		case '\u00EB':
			ch = 'e';
			break;
		case '\u00EC':
		case '\u00ED':
		case '\u00EE':
		case '\u00EF':
			ch = 'i';
			break;
		case '\u00F0':
			ch = 'd';
			break;
		case '\u00F1':
			ch = 'n';
			break;
		case '\u00F2':
		case '\u00F3':
		case '\u00F4':
		case '\u00F5':
		case '\u00F6':
			ch = 'o';
			break;
		case '\u00F9':
		case '\u00FA':
		case '\u00FB':
		case '\u00FC':
			ch = 'u';
			break;
		case '\u00FD':
		case '\u00FF':
			ch = 'y';
			break;
		}
		return ch;
	}
    //------------------------------------------------------------------
    //private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)([eE][+-]?[0-9]+)?");
    private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?(([0-9]+[.][0-9]+([eE][+-]?[0-9]+)?)|([0-9]+[eE][+-]?[0-9]+))");
	private static String normalize_real_numbers(CharSequence csq) {
		String res = "";
		try {
			StringBuilder sbres = new StringBuilder(csq.length());
			java.util.regex.Matcher matcher = float_pattern.matcher(csq);
			int idx = 0;
			while (matcher.find()) {
				int inicio = matcher.start();
				int fin = matcher.end();
				String num1 = csq.subSequence(inicio, fin).toString();
				String formato = "%.6f";
				if (num1.contains("e") || num1.contains("E")) {
					formato = "%.6e";
				}
				double num2 = Double.parseDouble(num1);
				String num3 = String.format(java.util.Locale.UK, formato, num2);
				sbres.append(csq.subSequence(idx, inicio));
				sbres.append(num3);
				idx = fin;
			}
			sbres.append(csq.subSequence(idx, csq.length()));
			res = sbres.toString();
		} catch (Throwable e) {
			res = csq.toString();
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static String normalize(String s1) {
		int sz = s1 == null ? 16 : s1.length() == 0 ? 16 : 2*s1.length();
		StringBuilder sb = new StringBuilder(sz);
		sb.append(' ');
		if (s1 != null) {
			for (int i = 0; i < s1.length(); ++i) {
				char ch = normalizeUnicode(s1.charAt(i));
				char sbLastChar = sb.charAt(sb.length()-1);
				if (Character.isLetter(ch)) {
					if ( ! Character.isLetter(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isDigit(ch)) {
					if ((i >= 2)
						&& (s1.charAt(i-1) == '.')
						&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 ."
						sb.append('.');
					} else if ((i >= 2)
							   && ((s1.charAt(i-1) == 'e')||(s1.charAt(i-1) == 'E'))
							   && Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 e"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '+')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e +"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '-')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e -"
						sb.append("e-");
					} else if ( (sbLastChar != '-') && ! Character.isDigit(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isSpaceChar(ch)) {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
				} else {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
					sb.append(ch);
				}
			}
		}
		if (Character.isSpaceChar(sb.charAt(sb.length()-1))) {
			sb.setLength(sb.length()-1);
		}
		if ((sb.length() > 0) && Character.isSpaceChar(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return normalize_real_numbers(sb);
	}
	//----------------------------------------------------------------------
	private static final String precondMessage = "\n> Warning: the test could not be executed due to previous errors";
	//----------------------------------------------------------------------
	private static void precond(boolean expectedValue, boolean currValue) {
		if (expectedValue != currValue) {
			fail(precondMessage);
		}
	}
	private static void precond(char expectedValue, char currValue) {
		if (expectedValue != currValue) {
			fail(precondMessage);
		}
	}
	private static void precond(short expectedValue, short currValue) {
		if (expectedValue != currValue) {
			fail(precondMessage);
		}
	}
	private static void precond(int expectedValue, int currValue) {
		if (expectedValue != currValue) {
			fail(precondMessage);
		}
	}
	private static void precond(long expectedValue, long currValue) {
		if (expectedValue != currValue) {
			fail(precondMessage);
		}
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		if (Math.abs(expectedValue - currValue) > delta) {
			fail(precondMessage);
		}
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		if (Math.abs(expectedValue - currValue) > delta) {
			fail(precondMessage);
		}
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			if (expectedValue != currValue) {
				fail(precondMessage);
			}
		} else if ( ! expectedValue.equals(currValue)) {
			fail(precondMessage);
		}
	}
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	private static class SysOutCapture {
		private SysOutErrCapture systemout;
		private SysOutErrCapture systemerr;
		public SysOutCapture() {
			systemout = new SysOutErrCapture(false);
			systemerr = new SysOutErrCapture(true);
		}
		public void sysOutCapture() throws RuntimeException {
			try {
				systemout.sysOutCapture();
			} finally {
				systemerr.sysOutCapture();
			}
		}
		public String sysOutRelease() throws RuntimeException {
			String s1 = "";
			String s2 = "";
			try {
				s1 = systemout.sysOutRelease();
			} finally {
				s2 = systemerr.sysOutRelease();
			}
			return s1 + " " + s2 ;
		}
		//--------------------------
		private static class SysOutErrCapture {
			//--------------------------------
			private java.io.PrintStream sysoutOld;
			private java.io.PrintStream sysoutstr;
			private java.io.ByteArrayOutputStream baos;
			private final boolean systemErr;
			private int estado;
			//--------------------------------
			public SysOutErrCapture(boolean syserr) {
				sysoutstr = null ;
				baos = null;
				sysoutOld = null ;
				estado = 0;
				systemErr = syserr;
			}
			//--------------------------------
			public void sysOutCapture() throws RuntimeException {
				if (estado != 0) {
					throw new RuntimeException("sysOutCapture:BadState");
				} else {
					estado = 1;
					try {
						if (systemErr) {
							sysoutOld = System.err;
						} else {
							sysoutOld = System.out;
						}
						baos = new java.io.ByteArrayOutputStream();
						sysoutstr = new java.io.PrintStream(baos);
						if (systemErr) {
							System.setErr(sysoutstr);
						} else {
							System.setOut(sysoutstr);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutCapture:InternalError");
					}
				}
			}
			//--------------------------------
			public String sysOutRelease() throws RuntimeException {
				String result = "";
				if (estado != 1) {
					throw new RuntimeException("sysOutRelease:BadState");
				} else {
					estado = 0;
					try {
						if (sysoutstr != null) {
							sysoutstr.flush();
						}
						if (baos != null) {
							baos.flush();
							result = new String(baos.toByteArray()); //java.nio.charset.StandardCharsets.ISO_8859_1);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutRelease:InternalError1");
					} finally {
						try {
							if (systemErr) {
								System.setErr(sysoutOld);
							} else {
								System.setOut(sysoutOld);
							}
							if (sysoutstr != null) {
								sysoutstr.close();
								sysoutstr = null;
							}
							if (baos != null) {
								baos.close();
								baos = null;
							}
						} catch (Throwable e) {
							throw new RuntimeException("sysOutRelease:InternalError2");
						}
					}
				}
				return result;
			}
			//--------------------------------
		}
	}
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
