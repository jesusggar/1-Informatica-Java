
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

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
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import libreria.*;

//--------------------------------------------------------------------------

public class TestRunnerPr21Ej2 {
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
			System.out.println("Start of Book JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Book JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lb1 = new Libro("Isaac Asimov", "La Fundacion", 7.30);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookCtorTest1() {
			assertAll("bookCtorTest1",
					() -> assertEquals("Isaac Asimov", lb1.getAutor(), "\n> Error: new Book(\"Isaac Asimov\", La Fundacion, 7.30): Autor:"),
					() -> assertEquals("La Fundacion", lb1.getTitulo(), "\n> Error: new Book(\"Isaac Asimov\", La Fundacion, 7.30): Titulo:"),
					() -> assertEquals(7.30, lb1.getPrecioBase(), 1e-6, "\n> Error: new Book(\"Isaac Asimov\", La Fundacion, 7.30): BasePrice:"),
					() -> assertEquals(10.00, Libro.getIVA(), 1e-6, "\n> Error: new Book(\"Isaac Asimov\", La Fundacion, 7.30): PorcIva:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookFinalPriceTest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("La Fundacion", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			assertEquals(8.03, lb1.getPrecioFinal(), 1e-6, "\n> Error: lb1.getPrecioFinal(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void booksetIVATest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("La Fundacion", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			Libro.setIVA(20.00);
			double ivaActual = Libro.getIVA();
			Libro.setIVA(10.00);
			assertEquals(20.00, ivaActual, 1e-6, "\n> Error: Libro.setIVA(20.00): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookToStringTest1() {
			precond("Isaac Asimov", lb1.getAutor());
			precond("La Fundacion", lb1.getTitulo());
			precond(7.30, lb1.getPrecioBase(), 1e-6);
			precond(10.0, Libro.getIVA(), 1e-6);
			assertEquals(normalize("(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03)"),
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
	public class JUnitTestLibroOferta {
		private LibroOferta lo1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of BookOnSale JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookOnSale JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lo1 = new LibroOferta("Isaac Asimov", "La Fundacion", 7.30, 20.0);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookCtorTest1() {
			assertAll("salesBookCtorTest1",
					() -> assertTrue(((Object)lo1 instanceof Libro), "\n> Error: BookOnSale extends Book:"),
					() -> assertEquals("Isaac Asimov", lo1.getAutor(), "\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): Author:"),
					() -> assertEquals("La Fundacion", lo1.getTitulo(), "\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): Title:"),
					() -> assertEquals(7.30, lo1.getPrecioBase(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): BasePrice:"),
					() -> assertEquals(20.00, lo1.getDescuento(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): DiscPerc:"),
					() -> assertEquals(10.00, LibroOferta.getIVA(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): VatPerc:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookFinalPriceTest1() {
			precond("Isaac Asimov", lo1.getAutor());
			precond("La Fundacion", lo1.getTitulo());
			precond(7.30, lo1.getPrecioBase(), 1e-6);
			precond(20.0, lo1.getDescuento(), 1e-6);
			precond(10.0, LibroOferta.getIVA(), 1e-6);
			assertEquals(6.424, lo1.getPrecioFinal(), 1e-6, "\n> Error: lo1.getPrecioFinal(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBooksetIVATest1() {
			precond("Isaac Asimov", lo1.getAutor());
			precond("La Fundacion", lo1.getTitulo());
			precond(7.30, lo1.getPrecioBase(), 1e-6);
			precond(20.0, lo1.getDescuento(), 1e-6);
			precond(10.0, LibroOferta.getIVA(), 1e-6);
			Libro.setIVA(20.00);
			double presentVAT = Libro.getIVA();
			Libro.setIVA(10.00);
			assertEquals(20.00, presentVAT, 1e-6, "\n> Error: Libro.setIVA(20.00): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookToStringTest1() {
			precond("Isaac Asimov", lo1.getAutor());
			precond("La Fundacion", lo1.getTitulo());
			precond(7.30, lo1.getPrecioBase(), 1e-6);
			precond(20.0, lo1.getDescuento(), 1e-6);
			precond(10.0, LibroOferta.getIVA(), 1e-6);
			assertEquals(normalize("(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995)"),
						 normalize(lo1.toString()),
						 "\n> Error: lo1.toString():");
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
			System.out.println("Start of BookStore JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookStore JUnit Test");
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
//		private static final Class arrayBookClass = (new ArrayList<Book>()).getClass();
//		private static ArrayList<Book> getArrayBooks(BookStore lb) {
//			ArrayList<Book> libs = null;
//			boolean encontrado = false;
//			try {
//				Class bookStoreClass = lb.getClass();
//				java.lang.reflect.Field[] bookStoreFields = bookStoreClass.getDeclaredFields();
//				int i = 0;
//				while ((i < bookStoreFields.length)
//					   &&(bookStoreFields[i].getType() != arrayBookClass)) {
//					++i;
//				}
//				if (i < bookStoreFields.length) {
//					bookStoreFields[i].setAccessible(true);
//					libs = (ArrayList<Book>)bookStoreFields[i].get(lb);
//					encontrado = true;
//				}
//			} catch (Throwable e) {
//				fail("\n> Error: getArrayBooks has thrown an exception " + e);
//			}
//			if (! encontrado) {
//				fail("\n> Error: la clase BookStore no contiene un array de books Book[]");
//			}
//			if (libs == null) {
//				fail("\n> Error: el array de books no se ha creado correctamente");
//			}
//			return libs;
//		} 
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreCtorTest1() {
			Libreria lr2 = new Libreria();
			assertEquals(normalize("[]"), normalize(lr2.toString()),"\n> Error: new BookStore(): toString():");
		}

		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreaddLibroTest1() {
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro1(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreaddLibroTest2() {
			lr1.addLibro("isaac asimov", "la fundacion", 5.30);
			lr1.addLibro("aldous huxley", "un mundo feliz", 4.50);
			lr1.addLibro("william gibson", "neuromante", 6.30);
			lr1.addLibro("george orwell", "1984", 4.20);
			lr1.addLibro("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			assertEquals(normalize("[(isaac asimov; la fundacion; 5.3; 10.0%; 5.83), (aldous huxley; un mundo feliz; 4.5; 10.0%; 4.95), (william gibson; neuromante; 6.3; 10.0%; 6.93), (george orwell; 1984; 4.2; 10.0%; 4.62), (ray bradbury; fahrenheit 451; 5.4; 10.0%; 5.94)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro2.1(): toString()");
			//------------------------
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addLibro2.2(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoregetPrecioFinalTest1() {
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("bookStoregetPrecioFinalTest1",
					() -> assertEquals(8.03, lr1.getPrecioFinal("Isaac Asimov", "La Fundacion"), 1e-6, "\n> Error: getPrecioFinal(Issac Asimov, La Fundacion):"),
					() -> assertEquals(7.15, lr1.getPrecioFinal("Aldous Huxley", "Un Mundo Feliz"), 1e-6, "\n> Error: getPrecioFinal(Aldous Huxley, Un Mundo Feliz):"),
					() -> assertEquals(9.13, lr1.getPrecioFinal("William Gibson", "Neuromante"), 1e-6, "\n> Error: getPrecioFinal(William Gibson, Neuromante):"),
					() -> assertEquals(6.82, lr1.getPrecioFinal("George Orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(George Orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getPrecioFinal("Ray Bradbury", "Fahrenheit 451"), 1e-6, "\n> addLibro(): getPrecioFinal(Ray Bradbury, Fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoregetPrecioFinalTest2() {
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("bookStoregetPrecioFinalTest2",
					() -> assertEquals(8.03, lr1.getPrecioFinal("isaac asimov", "la fundacion"), 1e-6, "\n> Error: getPrecioFinal(isaac asimov, La Fundacion):"),
					() -> assertEquals(7.15, lr1.getPrecioFinal("aldous huxley", "un mundo feliz"), 1e-6, "\n> Error: getPrecioFinal(aldous huxley, un mundo feliz):"),
					() -> assertEquals(9.13, lr1.getPrecioFinal("william gibson", "neuromante"), 1e-6, "\n> Error: getPrecioFinal(william gibson, neuromante):"),
					() -> assertEquals(6.82, lr1.getPrecioFinal("george orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(george orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getPrecioFinal("ray bradbury", "fahrenheit 451"), 1e-6, "\n> Error: getPrecioFinal(ray bradbury, fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoregetPrecioFinalTest3() {
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lr1.getPrecioFinal("xxx", "xxx"), "\n> Error: getPrecioFinal(xxx, xxx): No RuntimeException was thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest1() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.remLibro("Isaac Asimov", "La Fundacion");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Isaac Asimov, La Fundacion");
			lr1.remLibro("Aldous Huxley", "Un Mundo Feliz");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Aldous Huxley, Un Mundo Feliz");
			lr1.remLibro("William Gibson", "Neuromante");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(William Gibson, Neuromante");
			lr1.remLibro("George Orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(George Orwell, 1984");
			lr1.remLibro("Ray Bradbury", "Fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: remLibro(Ray Bradbury, Fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest2() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.remLibro("isaac asimov", "la fundacion");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(isaac asimov, la fundacion");
			lr1.remLibro("aldous huxley", "un mundo feliz");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(aldous huxley, un mundo feliz");
			lr1.remLibro("william gibson", "neuromante");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(william gibson, neuromante");
			lr1.remLibro("george orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: remLibro(george orwell, 1984");
			lr1.remLibro("ray bradbury", "fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: remLibro(ray bradbury, fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest3() {
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lr1.remLibro("xxx", "xxx"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreToStringTest1() {
			assertEquals(normalize("[]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreToStringTest2() {
			lr1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addLibro("William Gibson", "Neuromante", 8.30);
			lr1.addLibro("George Orwell", "1984", 6.20);
			lr1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] discountAuthors = { "george orwell", "isaac asimov" };
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestOfertaAutor {
		private OfertaAutor oa1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of DiscountAuthor JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of DiscountAuthor JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			oa1 = new OfertaAutor(20.0, discountAuthors);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void discountAuthorCtorTest1() {
			assertTrue(((Object)oa1 instanceof OfertaFlex), "\n> Error: DiscountAuthor implements OfertaFlex:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void discountAuthorgetDescuentoTest1() {
			Libro lb1 = new Libro("Isaac Asimov", "La Fundacion", 7.30);
			assertEquals(20.0, oa1.getDescuento(lb1), 1e-6, "\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDescuento(new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30)):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void discountAuthorgetDescuentoTest2() {
			Libro lb1 = new Libro("George Orwell", "1984", 6.20);
			assertEquals(20.0, oa1.getDescuento(lb1), 1e-6, "\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDescuento(new Book(\"George Orwell\", \"1984\", 6.20)):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void discountAuthorgetDescuentoTest3() {
			Libro lb1 = new Libro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			assertEquals(0.0, oa1.getDescuento(lb1), 1e-6, "\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDescuento(new Book(\"Aldous Huxley\", \"Un Mundo Feliz\", 6.50)):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void discountAuthorToStringTest1() {
			assertEquals(normalize("20.0%[george orwell, isaac asimov]"),
						 normalize(oa1.toString()),
						 "\n> Error: oa1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestOfertaPrecio {
		private OfertaPrecio op1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of OfertaPrecio JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of OfertaPrecio JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			op1 = new OfertaPrecio(20.0, 10.0);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ofertaPrecioCtorTest1() {
			assertTrue(((Object)op1 instanceof OfertaPrecio), "\n> Error: ofertaPrecio implementa OfertaFlex:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ofertaPreciogetDescuentoTest1() {
			Libro lb1 = new Libro("Isaac Asimov", "La Fundacion", 9.90);
			assertEquals(0.0, op1.getDescuento(lb1), 1e-6, "\n> Error: new OfertaPrecio(20.0, 10.0): getDescuento(new Book(\"Isaac Asimov\", \"La Fundacion\", 9.90)):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ofertaPreciogetDescuentoTest2() {
			Libro lb1 = new Libro("Isaac Asimov", "La Fundacion", 10.00);
			assertEquals(20.0, op1.getDescuento(lb1), 1e-6, "\n> Error: new OiscountPrice(20.0, 10.0): getDescuento(new Book(\"Isaac Asimov\", \"La Fundacion\", 10.00)):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ofertaPrecioToStringTest1() {
			assertEquals(normalize("20.0%(10.0)"),
						 normalize(op1.toString()),
						 "\n> Error: op1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestLibreriaOfertaFlex {
		private OfertaAutor oa1;
		private LibreriaOfertaFlex lrof1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of LibreriaOfertaFlex JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of LibreriaOfertaFlex JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			oa1 = new OfertaAutor(20.0, discountAuthors);
			lrof1 = new LibreriaOfertaFlex(oa1);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreCtorTest1() {
			assertAll(
					() -> assertTrue(((Object)lrof1 instanceof Libreria), "\n> Error: LibreriaOfertaFlex extiende Libreria:"),
					() -> assertEquals(normalize("20.0%[george orwell, isaac asimov][]"),
							 normalize(lrof1.toString()),
							 "\n> Error: new LibreriaOfertaFlex(): toString():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreGetSaleTest1() {
			assertEquals(oa1, lrof1.getOferta(), "\n> Error: getDescuento():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreSetSaleTest1() {
			LibreriaOfertaFlex lrof2 = new LibreriaOfertaFlex(oa1);
			precond(oa1, lrof2.getOferta());
			OfertaAutor oa2 = new OfertaAutor(20.0, discountAuthors);
			lrof2.setOferta(oa2);
			assertSame(oa2, lrof2.getOferta(), "\n> Error: getOferta():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreaddLibroTest1() {
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lrof1.toString()), 
					"\n> Error: addLibro1(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreaddLibroTest2() {
			lrof1.addLibro("isaac asimov", "la fundacion", 5.30);
			lrof1.addLibro("aldous huxley", "un mundo feliz", 4.50);
			lrof1.addLibro("william gibson", "neuromante", 6.30);
			lrof1.addLibro("george orwell", "1984", 4.20);
			lrof1.addLibro("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(isaac asimov; la fundacion; 5.3; 20.0%; 4.24; 10.0%; 4.664000000000001), (aldous huxley; un mundo feliz; 4.5; 10.0%; 4.95), (william gibson; neuromante; 6.3; 10.0%; 6.93), (george orwell; 1984; 4.2; 20.0%; 3.3600000000000003; 10.0%; 3.696), (ray bradbury; fahrenheit 451; 5.4; 10.0%; 5.94)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: addLibro(): toString():");
			//------------------------
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lrof1.toString()), "\n> Error: addLibro(): toString():");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoregetPrecioFinalTest1() {
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lrof1.toString()));
			//------------------------
			assertAll("flexSalesBookStoregetPrecioFinalTest1",
					() -> assertEquals(6.424, lrof1.getPrecioFinal("Isaac Asimov", "La Fundacion"), 1e-6, "\n> Error: getPrecioFinal(issac asimov, la fundacion):"),
					() -> assertEquals(7.15, lrof1.getPrecioFinal("Aldous Huxley", "Un Mundo Feliz"), 1e-6, "\n> Error: getPrecioFinal(aldous huxley, un mundo feliz):"),
					() -> assertEquals(9.13, lrof1.getPrecioFinal("William Gibson", "Neuromante"), 1e-6, "\n> Error: getPrecioFinal(william gibson, neuromante):"),
					() -> assertEquals(5.456, lrof1.getPrecioFinal("George Orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(george orwell, 1984):"),
					() -> assertEquals(8.14, lrof1.getPrecioFinal("Ray Bradbury", "Fahrenheit 451"), 1e-6, "\n> Error: getPrecioFinal(ray bradbury, fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoregetPrecioFinalTest2() {
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lrof1.toString()));
			//------------------------
			assertAll("flexSalesBookStoregetPrecioFinalTest2",
					() -> assertEquals(6.424, lrof1.getPrecioFinal("isaac asimov", "la fundacion"), 1e-6, "\n> Error: getPrecioFinal(issac asimov, la fundacion):"),
					() -> assertEquals(7.15, lrof1.getPrecioFinal("aldous huxley", "un mundo feliz"), 1e-6, "\n> Error: getPrecioFinal(aldous huxley, un mundo feliz):"),
					() -> assertEquals(9.13, lrof1.getPrecioFinal("william gibson", "neuromante"), 1e-6, "\n> Error: getPrecioFinal(william gibson, neuromante):"),
					() -> assertEquals(5.456, lrof1.getPrecioFinal("george orwell", "1984"), 1e-6, "\n> Error: getPrecioFinal(george orwell, 1984):"),
					() -> assertEquals(8.14, lrof1.getPrecioFinal("ray bradbury", "fahrenheit 451"), 1e-6, "\n> Error: getPrecioFinal(ray bradbury, fahrenheit 451):"));
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoregetPrecioFinalTest3() {
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lrof1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lrof1.getPrecioFinal("xxx", "xxx"), "\n> Error: getPrecioFinal(xxx, xxx): No RuntimeException was thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreremLibroTest1() {
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lrof1.toString()));
			//------------------------
			lrof1.remLibro("Isaac Asimov", "La Fundacion");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("Aldous Huxley", "Un Mundo Feliz");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("William Gibson", "Neuromante");
			assertEquals(
			 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
			 normalize(lrof1.toString()),
			 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("George Orwell", "1984");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
						//------------------------
			lrof1.remLibro("Ray Bradbury", "Fahrenheit 451");
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] []"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreremLibroTest2() {
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lrof1.toString()));
			//------------------------
			lrof1.remLibro("isaac asimov", "la fundacion");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("aldous huxley", "un mundo feliz");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("william gibson", "neuromante");
			assertEquals(
			 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
			 normalize(lrof1.toString()),
			 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
			lrof1.remLibro("george orwell", "1984");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
						//------------------------
			lrof1.remLibro("ray bradbury", "fahrenheit 451");
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] []"),
					 normalize(lrof1.toString()),
					 "\n> Error: remLibro(Isaac Asimov, La Fundacion): toString():");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void libreriaOfertaFlexRemLibroTest3() {
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13)]"),
					normalize(lrof1.toString()));
			//------------------------
			Exception exception = assertThrowsExactly(RuntimeException.class, () -> lrof1.remLibro("xxx", "xxx"), "\n> Error: remLibro(xxx, xxx): No RuntimeException was thrown");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreToStringTest1() {
			assertEquals(normalize("20.0%[george orwell, isaac asimov][]"),
						 normalize(lrof1.toString()),
						 "\n> Error: lrof1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void flexSalesBookStoreToStringTest2() {
			lrof1.addLibro("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addLibro("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addLibro("William Gibson", "Neuromante", 8.30);
			lrof1.addLibro("George Orwell", "1984", 6.20);
			lrof1.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("20.0%[george orwell, isaac asimov][(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0 % ; 4.96 ; 10.0 % ; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lrof1.toString()),
						 "\n> Error: lrof1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestLibreriaOfertaFlexMain {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainFlexSalesBookStore JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainFlexSalesBookStore JUnit Test");
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
		public void testMainFlexSalesBookStore() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaLibreriaOfertaFlex.main(new String[]{});
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
//					     normalize("20.0%[George Orwell, Isaac Asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Philip K. Dick; �Sue�an los androides con ovejas el�ctricas?; 3.5; 10.0%; 3.85), (Isaac Asimov; Fundaci�n e Imperio; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Isaac Asimov; La Fundaci�n; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (Isaac Asimov; Segunda Fundaci�n; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999), (Isaac Newton; Arithmetica Universalis; 10.5; 10.0%; 11.55)] 20.0%[George Orwell, Isaac Asimov] [(Philip K. Dick; �Sue�an los androides con ovejas el�ctricas?; 3.5; 10.0%; 3.85), (Isaac Asimov; Fundaci�n e Imperio; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundaci�n; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (Isaac Asimov; Segunda Fundaci�n; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999)] PrecioFinal(Philip K. Dick, �Sue�an los androides con ovejas el�ctricas?): 3.85 PrecioFinal(isaac asimov, fundaci�n e imperio): 8.272 PrecioFinal(Ray Bradbury, Fahrenheit 451): 8.14 PrecioFinal(Isaac Asimov, La Fundaci�n): 6.4239999999999995 PrecioFinal(william gibson, neuromante): 9.13 PrecioFinal(Isaac Asimov, Segunda Fundaci�n): 7.127999999999999"),
			assertEquals(normalize("20.0%[George Orwell, Isaac Asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Philip K . Dick; ¿ Sueñan los androides con ovejas electricas?; 3.5; 10.0%; 3.85), (Isaac Asimov; Fundacion e Imperio; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272) , (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14) , (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (Isaac Asimov; La Fundacion; 7.3; 20.0 %; 5.84; 10.0 %; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13) , (Isaac Asimov; Segunda Fundacion; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999) , (Isaac Newton; Arithmetica Universalis; 10.5; 10.0%; 11.55)] 20.0%[George Orwell, Isaac Asimov] [( Philip K. Dick; ¿Sueñan los androides con ovejas electricas?; 3.5; 10.0%; 3.85), (Isaac Asimov; Fundacion e Imperio; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (Isaac Asimov; Segunda Fundacion; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999)] PrecioFinal(Philip K. Dick, ¿Sueñan los androides con ovejas electricas?): 3.85 PrecioFinal(isaac asimov, fundacion e imperio): 8.272 PrecioFinal(Ray Bradbury, Fahrenheit 451): 8.14 PrecioFinal(Isaac Asimov, La Fundacion): 6.4239999999999995 PrecioFinal(william gibson, neuromante): 9.13 PrecioFinal (Isaac Asimov, Segunda Fundacion): 7.127999999999999"),
						 normalize(output),
						 "\n> Error: MainFlexSalesBookStore.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestLibro.class ,
				JUnitTestLibroOferta.class ,
				JUnitTestLibreria.class ,
				JUnitTestOfertaAutor.class ,
				JUnitTestOfertaPrecio.class ,
				JUnitTestLibreriaOfertaFlex.class ,
				JUnitTestLibreriaOfertaFlexMain.class 
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestLibro.class),
						selectClass(JUnitTestLibroOferta.class),
						selectClass(JUnitTestLibreria.class),
						selectClass(JUnitTestOfertaAutor.class),
						selectClass(JUnitTestOfertaPrecio.class),
						selectClass(JUnitTestLibreriaOfertaFlex.class),
						selectClass(JUnitTestLibreriaOfertaFlexMain.class))
				.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

//		summary.printTo(new PrintWriter(System.out, true));
		
		long abortedCount = summary.getTestsAbortedCount();
		long succeededCount = summary.getTestsFoundCount();
		long foundCount = summary.getTestsSucceededCount();
		long skippedCount = summary.getTestsSkippedCount();
		long failedCount = summary.getTestsFailedCount();
		long startedCount = summary.getTestsStartedCount();
		if (failedCount > 0) {
			System.out.println(">>> ------");
			summary.getFailures().forEach(failure -> System.out.println("failure - " + failure.getException()));
		}
		if ((abortedCount > 0)||(failedCount > 0)||(skippedCount > 0)) {
			System.out.println(">>> ------");
			if (skippedCount > 0) {
				System.out.println(">>> Error: Some tests ("+skippedCount+") were ignored");
			}
			if (abortedCount > 0) {
				System.out.println(">>> Error: ("+abortedCount+") tests could not be run due to errors in other methods");
			}
			if (failedCount > 0) {
				System.out.println(">>> Error: ("+failedCount+") tests failed due to errors in methods");
			}
		}
		if (succeededCount == foundCount) {
			System.out.print(">>> JUnit Test Succeeded");
		} else {
			System.out.print(">>> Error: JUnit Test Failed");
		}
		System.out.println(" (Tests: "+foundCount+", Errors: "+failedCount+", ErrorPrecond: "+abortedCount+", Ignored: "+skippedCount+")");

//		public static Result result = null;
//		result = JUnitCore.runClasses(JUnitTestSuite.class);
//		int rc = result.getRunCount();
//		int fc = result.getFailureCount();
//		int ac = 0;//result.getAssumptionFailureCount();
//		int ic = result.getIgnoreCount();
//		if (fc > 0) {
//			System.out.println(">>> ------");
//		}
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		if ((ac > 0)||(fc > 0)) {
//			System.out.println(">>> ------");
//			if (ac > 0) {
//				System.out.println(">>> Error: "+ac+" tests could not be executed due to previous errors");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests failed");
//			}
//		}
//		if (result.wasSuccessful()) {
//			System.out.print(">>> JUnit Test Succeeded");
//		} else {
//			System.out.print(">>> Error: JUnit Test Failed");
//		}
//		System.out.println(" ("+rc+", "+fc+", "+ac+", "+ic+")");
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
		case '\u20AC':
			ch = 'E';
			break;
		case '\u00A1':
			ch = '!';
			break;
		case '\u00AA':
			ch = 'a';
			break;
		case '\u00BA':
			ch = 'o';
			break;
		case '\u00BF':
			ch = '?';
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
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(char expectedValue, char currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(short expectedValue, short currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(int expectedValue, int currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(long expectedValue, long currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			assumeTrue(expectedValue.equals(currValue), precondMessage);
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
		assertEquals(expectedValue.length, currValue.length, msg);
		for (int i = 0; i < expectedValue.length; ++i) {
			assertEquals(msg, normalize(expectedValue[i]), normalize(currValue[i]));
		}
	}
	//----------------------------------------------------------------------
	private static void deleteFile(String filename) {
		new java.io.File(filename).delete();
	}
	private static void createFile(String filename, String inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			for (String linea : inputData) {
				pw.println(linea);
			}
		}
	}
	private static String loadFile(String filename) throws Exception {
		java.util.StringJoiner sj = new java.util.StringJoiner("\n");
		try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename))) {
			while (sc.hasNextLine()) {
				sj.add(sc.nextLine());
			}
		}
		return sj.toString();
	}
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
