import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static jarras.Mesa.Posicion.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.platform.suite.api.Suite;

import jarras.*;

import org.junit.platform.suite.api.SelectClasses;

//--------------------------------------------------------------------------

public class TestRunnerPr11 {

	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestJarra {
		private Jarra j5;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Jug JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Jug JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			j5 = new Jarra(5);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugCtorTest1() {
			assertAll("jugCtorTest1",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: new Jug(5): Capacity:"),
					() -> assertEquals(0, j5.contenido(), "\n> Error: new Jug(5): Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugCtorTestX1() {
			Exception exception =
					assertThrowsExactly(RuntimeException.class, () -> new Jarra(-1), "\n> Error: new Jarra(-1): no exception thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugFillTest1() {
			precond(5, j5.capacidad());
			precond(0, j5.contenido());
			j5.llena();
			assertAll("jugFillTest1",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j50.llena(): Capacity:"),
					() -> assertEquals(5, j5.contenido(), "\n> Error: j50.llena(): Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugEmptyTest1() {
			j5.llena();
			precond(5, j5.capacidad());
			precond(5, j5.contenido());
			j5.vacia();
			assertAll("jugEmptyTest1",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j55.vacia(): Capacity:"),
					() -> assertEquals(0, j5.contenido(), "\n> Error: j55.vacia(): Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugPourFromTest1() {
			Jarra j2 = new Jarra(2);
			j2.llena();
			precond(5, j5.capacidad());
			precond(0, j5.contenido());
			precond(2, j2.capacidad());
			precond(2, j2.contenido());
			j5.llenaDesde(j2);
			assertAll("jugPourFromTest1",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j50.llenaDesde(j22): j5.Capacity:"),
					() -> assertEquals(2, j5.contenido(), "\n> Error: j50.llenaDesde(j22): j5.Content:"),
					() -> assertEquals(2, j2.capacidad(), "\n> Error: j50.llenaDesde(j22): j2.Capacity:"),
					() -> assertEquals(0, j2.contenido(), "\n> Error: j50.llenaDesde(j22): j2.Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugPourFromTest2() {
			Jarra j2 = new Jarra(2);
			j5.llena();
			precond(5, j5.capacidad());
			precond(5, j5.contenido());
			precond(2, j2.capacidad());
			precond(0, j2.contenido());
			j2.llenaDesde(j5);
			assertAll("JarraPourFromTest2",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j20.llenaDesde(j55): j5.Capacity:"),
					() -> assertEquals(3, j5.contenido(), "\n> Error: j20.llenaDesde(j55): j5.Content:"),
					() -> assertEquals(2, j2.capacidad(), "\n> Error: j20.llenaDesde(j55): j2.Capacity:"),
					() -> assertEquals(2, j2.contenido(), "\n> Error: j20.llenaDesde(j55): j2.Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugPourFromTest3() {
			Jarra j2 = new Jarra(2);
			j2.llena();
			precond(5, j5.capacidad());
			precond(0, j5.contenido());
			precond(2, j2.capacidad());
			precond(2, j2.contenido());
			j5.llenaDesde(j2);
			assertAll("jugPourFromTest3",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j50.llenaDesde(j22): j5.Capacity:"),
					() -> assertEquals(2, j5.contenido(), "\n> Error: j50.llenaDesde(j22): j5.Content:"),
					() -> assertEquals(2, j2.capacidad(), "\n> Error: j50.llenaDesde(j22): j2.Capacity:"),
					() -> assertEquals(0, j2.contenido(), "\n> Error: j50.llenaDesde(j22): j2.Content:"));
			j2.llena();
			precond(5, j5.capacidad());
			precond(2, j5.contenido());
			precond(2, j2.capacidad());
			precond(2, j2.contenido());
			j5.llenaDesde(j2);
			assertAll("jugPourFromTest3",
					() -> assertEquals(5, j5.capacidad(),"\n> Error: j52.llenaDesde(j22): j5.Capacity:"),
					() -> assertEquals(4, j5.contenido(), "\n> Error: j52.llenaDesde(j22): j5.Content:"),
					() -> assertEquals(2, j2.capacidad(), "\n> Error: j52.llenaDesde(j22): j2.Capacity:"),
					() -> assertEquals(0, j2.contenido(), "\n> Error: j52.llenaDesde(j22): j2.Content:"));
			j2.llena();
			precond(5, j5.capacidad());
			precond(4, j5.contenido());
			precond(2, j2.capacidad());
			precond(2, j2.contenido());
			j5.llenaDesde(j2);
			assertAll("jugPourFromTest1",
					() -> assertEquals(5, j5.capacidad(), "\n> Error: j54.llenaDesde(j22): j5.Capacity:"),
					() -> assertEquals(5, j5.contenido(), "\n> Error: j54.llenaDesde(j22): j5.Content:"),
					() -> assertEquals(2, j2.capacidad(), "\n> Error: j54.llenaDesde(j22): j2.Capacity:"),
					() -> assertEquals(1, j2.contenido(), "\n> Error: j54.llenaDesde(j22): j2.Content:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugPourFromTestX1() {
			Exception exception =
					assertThrowsExactly(RuntimeException.class, () -> j5.llenaDesde(j5), "\n> Error: j5.llenaDesde(j5): no exception thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugToStringTest1() {
			precond(5, j5.capacidad());
			precond(0, j5.contenido());
			assertEquals(normalize("J(5, 0)"), normalize(j5.toString()),
					"\n> Error: j50.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void jugToStringTest2() {
			j5.llena();
			precond(5, j5.capacidad());
			precond(5, j5.contenido());
			assertEquals(normalize("J(5, 5)"), normalize(j5.toString()),
					"\n> Error: new j55.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestMainJarra {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainJug JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainJug JUnit Test");
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
		public void mainJugsTest1() {
			SysOutCapture sysOutCapture = new SysOutCapture();
			sysOutCapture.sysOutCapture();
			EjemploUsoJarras1.main(new String[]{});
			String salida = sysOutCapture.sysOutRelease();
			assertEquals(
					normalize("J(7,7),J(4,0) J(7,3),J(4,4) J(7,3),J(4,0) J(7,0),J(4,3)"),
					normalize(salida),
					"\n> Error: EjemploUsoJarras1.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestMesa {
		private Mesa m1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Mesa JUnit Test");
		}
		@AfterAll
		public void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of Mesa JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			m1 = new Mesa(5, 2);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaCtorTest1() {
			assertAll("MesaCtorTest1",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: new Mesa(5,2): Capacity-J1:"),
					() -> assertEquals(0, m1.contenido(Izquierda), "\n> Error: new Mesa(5,2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: new Mesa(5,2): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: new Mesa(5,2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaCtorTest2() {
			Jarra j5 = new Jarra(5);
			Jarra j2 = new Jarra(2);
			precond(5, j5.capacidad());
			precond(0, j5.contenido());
			precond(2, j2.capacidad());
			precond(0, j2.contenido());
			Mesa m2 = new Mesa(j5, j2);
			assertAll("MesaCtorTest2",
					() -> assertEquals(5, m2.capacidad(Izquierda), "\n> Error: new Mesa(j5,j2): Capacity-J1:"),
					() -> assertEquals(0, m2.contenido(Izquierda), "\n> Error: new Mesa(j5,j2): Content-J1:"),
					() -> assertEquals(2, m2.capacidad(Derecha), "\n> Error: new Mesa(j5,j2): Capacity-J2:"),
					() -> assertEquals(0, m2.contenido(Derecha), "\n> Error: new Mesa(j5,j2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaCtorTest3() {
			Jarra j5 = new Jarra(5);
			Jarra j2 = new Jarra(2);
			Mesa m2 = new Mesa(j5, j2);
			j5.llena();
			j2.llena();
			precond(5, j5.capacidad());
			precond(5, j5.contenido());
			precond(2, j2.capacidad());
			precond(2, j2.contenido());
			assertAll("MesaCtorTest3",
					() -> assertEquals(5, m2.capacidad(Izquierda), "\n> Error: new Mesa(j5,j2): Capacity-J1:"),
					() -> assertEquals(5, m2.contenido(Izquierda), "\n> Error: new Mesa(j5,j2): Content-J1:"),
					() -> assertEquals(2, m2.capacidad(Derecha), "\n> Error: new Mesa(j5,j2): Capacity-J2:"),
					() -> assertEquals(2, m2.contenido(Derecha), "\n> Error: new Mesa(j5,j2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaCtorTestX1() {
			Jarra j5 = new Jarra(5);
			Exception exception =
					assertThrowsExactly(RuntimeException.class, () -> new Mesa(j5, j5), "\n> Error: new Mesa(j5,j5): no exception thrown");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaFillTest1() {
			precond(5, m1.capacidad(Izquierda));
			precond(0, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(0, m1.contenido(Derecha));
			m1.llena(Izquierda);
			assertAll("MesaFillTest1",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5020.llena(1): Capacity-J1:"),
					() -> assertEquals(5, m1.contenido(Izquierda), "\n> Error: m5020.llena(1): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5020.llena(1): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: m5020.llena(1): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaFillTest2() {
			precond(5, m1.capacidad(Izquierda));
			precond(0, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(0, m1.contenido(Derecha));
			m1.llena(Derecha);
			assertAll("MesaFillTest2",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5020.llena(2): Capacity-J1:"),
					() -> assertEquals(0, m1.contenido(Izquierda), "\n> Error: m5020.llena(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5020.llena(2): Capacity-J2:"),
					() -> assertEquals(2, m1.contenido(Derecha), "\n> Error: m5020.llena(2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaEmptyTest1() {
			m1.llena(Izquierda);
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(5, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.vacia(Izquierda);
			assertAll("MesaEmptyTest1",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5522.vacia(1): Capacity-J1:"),
					() -> assertEquals(0, m1.contenido(Izquierda), "\n> Error: m5522.vacia(1): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5522.vacia(1): Capacity-J2:"),
					() -> assertEquals(2, m1.contenido(Derecha), "\n> Error: m5522.vacia(1): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaEmptyTest2() {
			m1.llena(Izquierda);
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(5, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.vacia(Derecha);
			assertAll("MesaEmptyTest2",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5522.vacia(2): Capacity-J1:"),
					() -> assertEquals(5, m1.contenido(Izquierda), "\n> Error: m5522.vacia(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5522.vacia(2): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: m5522.vacia(2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaPourFromTest1() {
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(0, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.llenaDesde(Derecha);
			assertAll("MesaPourFromTest1",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5022.llenaDesde(2): Capacity-J1:"),
					() -> assertEquals(2, m1.contenido(Izquierda), "\n> Error: m5022.llenaDesde(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5022.llenaDesde(2): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: m5022.llenaDesde(2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaPourFromTest2() {
			m1.llena(Izquierda);
			precond(5, m1.capacidad(Izquierda));
			precond(5, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(0, m1.contenido(Derecha));
			m1.llenaDesde(Izquierda);
			assertAll("MesaPourFromTest2",
					() -> assertEquals(5, m1.capacidad(Izquierda),"\n> Error: m5520.llenaDesde(1): Capacity-J1:"),
					() -> assertEquals(3, m1.contenido(Izquierda), "\n> Error: m5520.llenaDesde(1): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5520.llenaDesde(1): Capacity-J2:"),
					() -> assertEquals(2, m1.contenido(Derecha), "\n> Error: m5520.llenaDesde(1): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaPourFromTest3() {
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(0, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.llenaDesde(Derecha);
			assertAll("MesaPourFromTest3",
					() -> assertEquals(5, m1.capacidad(Izquierda),"\n> Error: m5022.llenaDesde(2): Capacity-J1:"),
					() -> assertEquals(2, m1.contenido(Izquierda), "\n> Error: m5022.llenaDesde(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5022.llenaDesde(2): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: m5022.llenaDesde(2): Content-J2:"));
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(2, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.llenaDesde(Derecha);
			assertAll("MesaPourFromTest3",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5222.llenaDesde(2): Capacity-J1:"),
					() -> assertEquals(4, m1.contenido(Izquierda), "\n> Error: m5222.llenaDesde(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5222.llenaDesde(2): Capacity-J2:"),
					() -> assertEquals(0, m1.contenido(Derecha), "\n> Error: m5222.llenaDesde(2): Content-J2:"));
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(4, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			m1.llenaDesde(Derecha);
			assertAll("MesaPourFromTest3",
					() -> assertEquals(5, m1.capacidad(Izquierda), "\n> Error: m5422.llenaDesde(2): Capacity-J1:"),
					() -> assertEquals(5, m1.contenido(Izquierda), "\n> Error: m5422.llenaDesde(2): Content-J1:"),
					() -> assertEquals(2, m1.capacidad(Derecha), "\n> Error: m5422.llenaDesde(2): Capacity-J2:"),
					() -> assertEquals(1, m1.contenido(Derecha), "\n> Error: m5422.llenaDesde(2): Content-J2:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaToStringTest1() {
			precond(5, m1.capacidad(Izquierda));
			precond(0, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(0, m1.contenido(Derecha));
			assertEquals(normalize("M(J(5,0),J(2,0))"), normalize(m1.toString()),
					"\n> Error: m5020.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MesaToStringTest2() {
			m1.llena(Izquierda);
			m1.llena(Derecha);
			precond(5, m1.capacidad(Izquierda));
			precond(5, m1.contenido(Izquierda));
			precond(2, m1.capacidad(Derecha));
			precond(2, m1.contenido(Derecha));
			assertEquals(normalize("M(J(5,5),J(2,2))"), normalize(m1.toString()),
					"\n> Error: m5522.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestMainMesa {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainMesa JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainMesa JUnit Test");
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
		public void mainMesaTest1() {
			SysOutCapture sysOutCapture = new SysOutCapture();
			sysOutCapture.sysOutCapture();
			EjemploUsoMesa1.main(new String[]{});
			String salida = sysOutCapture.sysOutRelease();
			assertEquals(
					normalize("M(J(7,0),J(5,5)) M(J(7,5),J(5,0)) M(J(7,5),J(5,5)) M(J(7,7),J(5,3)) M(J(7,0),J(5,3)) M(J(7,3),J(5,0)) M(J(7,3),J(5,5)) M(J(7,7),J(5,1))"),
					normalize(salida),
					"\n> Error: EjemploUsoMesa1.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------

//	@RunWith(Suite.class)
//	@Suite.SuiteClasses({ JUnitTestJarra.class ,
//				JUnitTestMaincapacidad()ra.class ,
//				JUnitTestMesa.class ,
//				JUnitTestMainMesa.class
//				})
//				public static class JUnitTestSuite { /*empty*/ }

	@Suite
	@SelectClasses({JUnitTestJarra.class, JUnitTestMainJarra.class, JUnitTestMesa.class, JUnitTestMainMesa.class})
	public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--JUnitTestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request =
				LauncherDiscoveryRequestBuilder.request()
						.selectors(
								selectClass(JUnitTestJarra.class),
								selectClass(JUnitTestMesa.class),
								selectClass(JUnitTestMainJarra.class),
								selectClass(JUnitTestMainMesa.class))
						.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

//	    		summary.printTo(new PrintWriter(System.out, true));

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

//	public static Result result = null;
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
//				System.out.println(">>> Error: No se pudieron realizar "+ac+" tests debido a errores en otros metodos");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: Fallaron "+fc+" tests debido a errores en metodos");
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
		Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(char expectedValue, char currValue) {
		Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(short expectedValue, short currValue) {
		Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(int expectedValue, int currValue) {
		Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(long expectedValue, long currValue) {
		Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		Assumptions.assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		Assumptions.assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			Assumptions.assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			Assumptions.assumeTrue(expectedValue.equals(currValue), precondMessage);
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
		try (PrintWriter pw = new PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (PrintWriter pw = new PrintWriter(filename)) {
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
	//------------------------------------------------------------------
	private static Object getMemberObject(Object obj, Class objClass, Class memberClass, String memberName) {
		//--------------------------
		// OBJ puede ser NULL en caso de variable STATIC
		// OBJCLASS puede ser NULL si OBJ no es NULL
		// MEMBERCLASS no puede ser NULL
		// MEMBERNAME puede ser NULL, si solo hay una unica variable de ese tipo
		//--------------------------
		String memberId = (memberName == null ? "" : memberName)+":"+(memberClass == null ? "" : memberClass.getName());
		Object res = null;
		int idx = -1;
		try {
			if ((objClass == null)&&(obj != null)) {
				objClass = obj.getClass();
			}
			if ((objClass != null)&&(memberClass != null)) {
				int cnt = 0;
				int aux = -1;
				java.lang.reflect.Field[] objFields = objClass.getDeclaredFields();
				for (int i = 0; i < objFields.length; ++i) {
					if (memberClass.equals(objFields[i].getType())) {
						if ((memberName != null)&&(memberName.equalsIgnoreCase(objFields[i].getName()))) {
							idx = i;
						} else {
							aux = i;
						}
						++cnt;
					}
				}
				if ((idx < 0)&&(cnt == 1)) {
					idx = aux;	// si solo tiene una variable de ese tipo, no importa el nombre
				}
				if (idx >= 0) {
					objFields[idx].setAccessible(true);
					res = objFields[idx].get(obj);
				}
			}
		} catch (Throwable e) {
			fail("\n> Unexpected Error. getMemberObject["+memberId+"]: " + e);
		}
		if (idx < 0) {
			fail("\n> Error: no ha sido posible encontrar la variable ["+memberId+"]");
		}
		if (res == null) {
			fail("\n> Error: la variable ["+memberId+"] no se ha creado correctamente");
		}
		return res;
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
