
//--------------------------------------------------------------------------


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
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

import cuentapalabras.*;

//--------------------------------------------------------------------------

public class TestRunnerPr42 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestPalabraEnTexto {
		private PalabraEnTexto pt1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Inicio de PalabraEnTexto JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("Fin de PalabraEnTexto JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			pt1 = new PalabraEnTexto(new String("palabra"));
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void PalabraEnTextoCtorTest1() {
			assertEquals(normalize("PALABRA: 1"),
						 normalize(pt1.toString()),
						 "\n> Error: an1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void PalabraEnTextoIncrementTest2() throws Exception {
			pt1.incrementa();
			assertEquals(normalize("PALABRA: 2"),
						 normalize(pt1.toString()),
						 "\n> Error: an1.incrementa(); an1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void PalabraEnTextoEqualsTest1() throws Exception {
			PalabraEnTexto an2 = new PalabraEnTexto("palabra");
			assertTrue(pt1.equals(an2), "\n> Error: an1.equals(an2): ");
			an2.incrementa();
			assertTrue(pt1.equals(an2), "\n> Error: an1.equals(an2): ");
			//------------------------
			PalabraEnTexto an3 = new PalabraEnTexto("PALABRA");
			assertTrue(pt1.equals(an3), "\n> Error: an1.equals(an3): ");
			an3.incrementa();
			assertTrue(pt1.equals(an3), "\n> Error: an1.equals(an3): ");
			//------------------------
			PalabraEnTexto an4 = new PalabraEnTexto("otra palabra");
			assertFalse(pt1.equals(an4), "\n> Error: an1.equals(an4): ");
			//------------------------
			assertFalse(pt1.equals(null), "\n> Error: an1.equals(null): ");
			assertFalse(pt1.equals("Esto es un String"), "\n> Error: an1.equals(\"Esto es un String\"): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void PalabraEnTextoHashCodeTest1() throws Exception {
			int an1HashCode = pt1.hashCode();
			//------------------------
			PalabraEnTexto an2 = new PalabraEnTexto("palabra");
			assertEquals(an1HashCode, an2.hashCode(), "\n> Error: an2.hashCode(): ");
			an2.incrementa();
			assertEquals(an1HashCode, an2.hashCode(), "\n> Error: an2.hashCode(): ");
			//------------------------
			PalabraEnTexto an3 = new PalabraEnTexto("PALABRA");
			assertEquals(an1HashCode, an3.hashCode(), "\n> Error: an3.hashCode(): ");
			an3.incrementa();
			assertEquals(an1HashCode, an3.hashCode(), "\n> Error: an3.hashCode(): ");
			//------------------------
			PalabraEnTexto an4 = new PalabraEnTexto("otra palabra");
			assertNotEquals(an1HashCode, an4.hashCode(), "\n> Error: an4.hashCode(): ");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainPalabraEnTexto {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Inicio de PruebaPalabraEnTexto JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("Fin de PruebaPalabraEnTexto JUnit Test");
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
		public void MainPalabraEnTextoMainTest1() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaPalabraEnTexto.main(new String[]{});
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("Palabra 1 = GORRA : 2 Palabra 2 = GORRA : 1 Las palabras son iguales"),
						 normalize(output),
						 "\n> Error: PruebaPalabraEnTexto.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
		private static final String[] inputData = {
				"Guerra tenia una jarra y Parra tenia una perra, ",
				"pero la perra de Parra rompio la jarra de Guerra.",
				"Guerra pego con la porra a la perra de Parra. ",
				"!Oiga usted buen hombre de Parra! ",
				"Por que ha pegado con la porra a la perra de Parra.",
				"Porque si la perra de Parra no hubiera roto la jarra de Guerra,",
				"Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,\\?!]+";
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestContadorPalabras {
		private ContadorPalabras cp1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Inicio de ContadorPalabras JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("Fin de ContadorPalabras JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			cp1 = new ContadorPalabras();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasCtorTest1() {
			assertEquals(normalize("[]"),
						 normalize(cp1.toString()),
						 "\n> Error: cp1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasCtorTest2() {
			ContadorPalabras cp2 = new ContadorPalabras();
			assertEquals(normalize("[]"),
						 normalize(cp2.toString()),
						 "\n> Error: cp1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasincluyeTodasTest1() {
			cp1.incluyeTodas(inputData, delimiters);
			assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
						 normalize(cp1.toString()),
						 "\n> Error: incluyeTodas() ; toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasincluyeTodasFicheroTest1() throws Exception {
			try {
				createFile("inputData.txt", inputData);
				cp1.incluyeTodasFichero("inputData.txt", delimiters);
				assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
							 normalize(cp1.toString()),
							 "\n> Error: incluyeTodasFichero() ; toString():");
			} finally {
				deleteFile("inputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasFindTest1() {
			cp1.incluyeTodas(inputData, delimiters);
			precond(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
					normalize(cp1.toString()));
			assertAll("ContadorPalabrasFindTest1",
				() -> assertEquals(normalize("GUERRA: 5"),
						           normalize(cp1.encuentra("guerra").toString()), 
						           "\n> Error: cp1.encuentra(guerra):"),
				() -> assertEquals(normalize("JARRA: 3"),
						           normalize(cp1.encuentra("jarra").toString()),
						           "\n> Error: cp1.encuentra(woodchuck):"),
				() -> assertEquals(normalize("ROTO: 1"),
						           normalize(cp1.encuentra("roto").toString()), 
						           "\n> Error: cp1.encuentra(could):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasFindTest2() {
			cp1.incluyeTodas(inputData, delimiters);
			precond(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
					normalize(cp1.toString()));
			Exception exception = assertThrowsExactly(NoSuchElementException.class, 
												() -> cp1.encuentra("xxx"));
//			try {
//				cp1.incluyeTodas(inputData, delimiters);
//				precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
//						normalize(cp1.toString()));
//				PalabraEnTexto pal = cp1.encuentra("xxx");
//				fail("\n> Error: encuentra(xxx): No exception thrown");
//			} catch (java.util.NoSuchElementException e) {
//				//assertEquals("\n> Error: encuentra(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
//			} catch (Exception e) {
//				fail("\n> Error: encuentra(xxx): the exception thrown was not NoSuchElementException");
//			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasFindTest3() {
			Exception exception = assertThrows(NoSuchElementException.class, 
					() -> cp1.encuentra("xxx"));
			assertEquals("No existe la palabra xxx", exception.getMessage(), "\n> Error: encuentra(xxx): exception.getMessage():");
//			try {
//				PalabraEnTexto pal = cp1.encuentra("xxx");
//				fail("\n> Error: encuentra(xxx): No exception thrown");
//			} catch (java.util.NoSuchElementException e) {
//				//assertEquals("\n> Error: encuentra(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
//			} catch (Exception e) {
//				fail("\n> Error: encuentra(xxx): the exception thrown was not NoSuchElementException");
//			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasPresentWordsPWTest1() throws Exception {
			try {
				cp1.incluyeTodas(inputData, delimiters);
				precond(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
						normalize(cp1.toString()));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentaPalabras(pw);
				}
				assertEquals(normalize("GUERRA: 5 TENIA: 2 UNA: 2 JARRA: 3 Y: 1 PARRA: 7 PERRA: 6 PERO: 1 LA: 10 DE: 8 ROMPIO: 1 PEGO: 1 CON: 3 PORRA: 3 A: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 SI: 1 NO: 2 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentaPalabras():");
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasPresentWordsFichTest1() throws Exception {
			try {
				cp1.incluyeTodas(inputData, delimiters);
				precond(normalize("[GUERRA: 5 - TENIA: 2 - UNA: 2 - JARRA: 3 - Y: 1 - PARRA: 7 - PERRA: 6 - PERO: 1 - LA: 10 - DE: 8 - ROMPIO: 1 - PEGO: 1 - CON: 3 - PORRA: 3 - A: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - SI: 1 - NO: 2 - HUBIERA: 2 - ROTO: 1]"),
						normalize(cp1.toString()));
				cp1.presentaPalabras("outputData.txt");
				assertEquals(normalize("GUERRA: 5 TENIA: 2 UNA: 2 JARRA: 3 Y: 1 PARRA: 7 PERRA: 6 PERO: 1 LA: 10 DE: 8 ROMPIO: 1 PEGO: 1 CON: 3 PORRA: 3 A: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 SI: 1 NO: 2 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentaPalabras():");
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainContadorPalabras {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Inicio de MainContadorPalabras JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("Fin de MainContadorPalabras JUnit Test");
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
		public void MainContadorPalabrasMainTest1() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaContadorPalabras.main(new String[]{});
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("[ ESTA : 2 - ES : 2 - LA : 2 - PRIMERA : 1 - FRASE : 2 - DEL : 1 - EJEMPLO : 1 - Y : 1 - SEGUNDA : 1 ]"),
						 normalize(output),
						 "\n> Error: MainContadorPalabras.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
		private static final String[] noSig = {
			"Con", "La", "A", "De", "NO", "SI", "y", "una"
		};
		private static final String[] noSigInputData = {
			"Con La A De \n NO SI y una"
		};
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestContadorPalabrasSig {
		private ContadorPalabrasSig cp1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Inicio de ContadorPalabrasSig JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("Fin de ContadorPalabrasSig JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			cp1 = new ContadorPalabrasSig();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void ContadorPalabrasSigCtorTest1() {
			assertTrue(((Object)cp1 instanceof ContadorPalabras), "\n> Error: ContadorPalabrasSig extends ContadorPalabras:");
			assertEquals(normalize("[]"),
						 normalize(cp1.toString()),
						 "\n> Error: cp1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigLeeArrayNoSigTest1() {
			precond(normalize("[]"),
					normalize(cp1.toString()));
			cp1.leeArrayNoSig(noSig);
			assertEquals(normalize("[]"),
						 normalize(cp1.toString()),
						 "\n> Error: cp1.toString():");
			cp1.incluyeTodas(inputData, delimiters);
			assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
						 normalize(cp1.toString()),
						 "\n> Error: incluyeTodas() ; toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigLeeFicheroNoSigTest1() throws Exception {
			try {
				precond(normalize("[]"),
						normalize(cp1.toString()));
				createFile("noSigInputData.txt", noSigInputData);
				cp1.leeFicheroNoSig("noSigInputData.txt", delimiters);
				assertEquals(normalize("[]"),
							 normalize(cp1.toString()),
							 "\n> Error: cp1.toString():");
				cp1.incluyeTodas(inputData, delimiters);
				assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
							 normalize(cp1.toString()),
							 "\n> Error: incluyeTodas() ; toString():");
			} finally {
				deleteFile("noSigInputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigIncluyeTodasTest1() {
			precond(normalize("[]"),
					normalize(cp1.toString()));
			cp1.leeArrayNoSig(noSig);
			cp1.incluyeTodas(inputData, delimiters);
			assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
						 normalize(cp1.toString()),
						 "\n> Error: incluyeTodas() ; toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigIncluyeTodasFicheroTest1() throws Exception {
			try {
				precond(normalize("[]"),
						normalize(cp1.toString()));
				cp1.leeArrayNoSig(noSig);
				createFile("inputData.txt", inputData);
				cp1.incluyeTodasFichero("inputData.txt", delimiters);
				assertEquals(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
							 normalize(cp1.toString()),
							 "\n> Error: incluyeTodasFichero() ; toString():");
			} finally {
				deleteFile("inputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigEncuentraTest1() {
			precond(normalize("[]"),
					normalize(cp1.toString()));
			cp1.leeArrayNoSig(noSig);
			cp1.incluyeTodas(inputData, delimiters);
			precond(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
					normalize(cp1.toString()));
			assertAll(() -> assertEquals(normalize("GUERRA: 5"),
						                 normalize(cp1.encuentra("guerra").toString()),
						                 "\n> Error: cp1.encuentra(guerra):"),
					  () -> assertEquals(normalize("JARRA: 3"),
						                 normalize(cp1.encuentra("jarra").toString()),
						                 "\n> Error: cp1.encuentra(jarra):"),
 			          () -> assertEquals(normalize("ROTO: 1"),
						                 normalize(cp1.encuentra("roto").toString()),
						                 "\n> Error: cp1.encuentra(roto):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigEncuentraTest2() {
			precond(normalize("[]"),
					normalize(cp1.toString()));
			cp1.leeArrayNoSig(noSig);
			cp1.incluyeTodas(inputData, delimiters);
			precond(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
					normalize(cp1.toString()));
			Exception exception = assertThrowsExactly(NoSuchElementException.class, 
					() -> cp1.encuentra("xxx"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigEncuentraTest3() {
			try {
				PalabraEnTexto pal = cp1.encuentra("xxx");
				fail("\n> Error: encuentra(xxx): No se lanzo ninguna excepcion");
			} catch (java.util.NoSuchElementException e) {
				//assertEquals("\n> Error: encuentra(xxx): exception.getMessage():", "No existe la palabra xxx", e.getMessage());
			} catch (Exception e) {
				fail("\n> Error: encuentra(xxx): la excepcion lanzada no es NoSuchElementException");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigPresentaPalabrasPWTest1() throws Exception {
			try {
				precond(normalize("[]"),
						normalize(cp1.toString()));
				cp1.leeArrayNoSig(noSig);
				cp1.incluyeTodas(inputData, delimiters);
				precond(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
						normalize(cp1.toString()));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentaPalabras(pw);
				}
				assertEquals(normalize("GUERRA: 5 TENIA: 2 JARRA: 3 PARRA: 7 PERRA: 6 PERO: 1 ROMPIO: 1 PEGO: 1 PORRA: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentaPalabrasPW():");
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void contadorPalabrasSigPresentaPalabrasFichTest1() throws Exception {
			try {
				precond(normalize("[]"),
						normalize(cp1.toString()));
				cp1.leeArrayNoSig(noSig);
				cp1.incluyeTodas(inputData, delimiters);
				precond(normalize("[GUERRA: 5 - TENIA: 2 - JARRA: 3 - PARRA: 7 - PERRA: 6 - PERO: 1 - ROMPIO: 1 - PEGO: 1 - PORRA: 3 - OIGA: 1 - USTED: 1 - BUEN: 1 - HOMBRE: 1 - POR: 1 - QUE: 1 - HA: 1 - PEGADO: 2 - PORQUE: 1 - HUBIERA: 2 - ROTO: 1]"),
						normalize(cp1.toString()));
				cp1.presentaPalabras("outputData.txt");
				assertEquals(normalize("GUERRA: 5 TENIA: 2 JARRA: 3 PARRA: 7 PERRA: 6 PERO: 1 ROMPIO: 1 PEGO: 1 PORRA: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")),
							 "\n> Error: presentaPalabrasFich():");
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestContadorPalabras.class ,
				JUnitTestContadorPalabrasSig.class ,
				JUnitTestPalabraEnTexto.class ,
				JUnitTestMainContadorPalabras.class ,
				JUnitTestMainPalabraEnTexto.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestContadorPalabras.class),
						selectClass(JUnitTestContadorPalabrasSig.class),
						selectClass(JUnitTestPalabraEnTexto.class),
						selectClass(JUnitTestMainContadorPalabras.class),
						selectClass(JUnitTestMainContadorPalabras.class))
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
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
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
	//------------------------------------------------------------------
	private static String normalizeListStr(String listaStr, String delims, String prefix, String suffix) {
		listaStr = listaStr.trim();
		String res = listaStr;
		try {
			if (prefix.length() > 0 && listaStr.startsWith(prefix)) {
				listaStr = listaStr.substring(prefix.length());
			}
			if (suffix.length() > 0 && listaStr.endsWith(suffix)) {
				listaStr = listaStr.substring(0, listaStr.length()-suffix.length());
			}
			listaStr = listaStr.trim();
			java.util.List<String> lista = new java.util.ArrayList<>(java.util.List.of(listaStr.split(delims)));
			lista.sort(null);
			res = lista.toString();
		} catch (Throwable e) {
			// ignorar
		}
		return res;
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
	//------------------------------------------------------------------
	private static void precond(int[] expectedValue, int[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void precond(double[] expectedValue, double[] currValue, double delta) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i], delta);
			}
		}
	}
	private static <T> void precond(T[] expectedValue, T[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void precondNorm(String[] expectedValue, String[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precondNorm(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, java.util.List<String> expectedValue, java.util.List<String> currValue) {
		assertEquals(expectedValue.size(), currValue.size(), msg);
		for (int i = 0; i < expectedValue.size(); ++i) {
			assertEquals(normalize(expectedValue.get(i)), normalize(currValue.get(i)), msg);
		}
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
	//--TestRunner-End---------------------------------------------------
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
