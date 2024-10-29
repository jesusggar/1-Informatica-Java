
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import datos.*;

//--------------------------------------------------------------------------

public class TestRunnerPr31Ej1 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestDatosException {
		private DatosException de1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of DatosException JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of DatosException JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			de1 = new DatosException("Error Message 1");
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataExceptionCtorTest1() {
			assertAll("dataExceptionCtorTest1",
				() -> assertTrue(((Object)de1 instanceof RuntimeException), "\n> Error: DatosException extends RuntimeException:"),
				() -> assertEquals("Error Message 1", de1.getMessage(), "\n> Error: new DatosException(\"Error Message 1\"): getMessage():")
			);
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataExceptionCtorTest2() {
			DatosException de2 = new DatosException();
			assertAll("dataExceptionCtorTest2",
				() -> assertTrue(((Object)de2 instanceof RuntimeException), "\n> Error: DatosException extends RuntimeException:"),
				() -> assertEquals(null, de2.getMessage(), "\n> Error: new DatosException(): getMessage():")
			);
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataExceptionToStringTest1() {
			precond("Error Message 1", de1.getMessage());
			assertEquals(normalize("datos.DatosException : Error Message 1"),
						 normalize(de1.toString()),
						 "\n> Error: de1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] inputData = {
		"5.5", "9.5", "Joe", "10.5", "Mariah", "12.5", "13.5",
		"Frank", "17.5", "20.5", "Anna", "25.5", "John", "Dolly"
	};
	private static final double[] inputValues = { 5.5, 9.5, 10.5, 12.5, 13.5, 17.5, 20.5, 25.5 };
	private static final String[] inputErrors = { "Joe", "Mariah", "Frank", "Anna", "John", "Dolly" };
	private static double[] list2arrayDouble(java.util.List<Double> al) {
		double[] dtdt = new double[al.size()];
		for (int i = 0; i < dtdt.length; ++i) {
			dtdt[i] = al.get(i);
		}
		return dtdt;
	}
	private static String[] list2arrayString(java.util.List<String> al) {
		return al.toArray(new String[0]);
	}
	private static void precond(Datos dt) {
		double[] dtdt = list2arrayDouble(dt.getDatos());
		String[] dterr = list2arrayString(dt.getErrores());
		precond(inputValues, dtdt, 1e-6);
		precond(inputErrors, dterr);
	}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestData {
		private Datos dt1;
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of Data JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of Data JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			dt1 = new Datos(inputData, 10.0, 20.0);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataCtorTest1() {
			double[] dtdt = list2arrayDouble(dt1.getDatos());
			String[] dterr = list2arrayString(dt1.getErrores());
			assertArrayEquals(inputValues, dtdt, 1e-6, "\n> Error: new Data(): dt1.getDatos():");
			assertArrayEquals(inputErrors, dterr, "\n> Error: new Data(): dt1.getErrores():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataCalcAverageTest1() throws Exception {
			precond(dt1);
			assertEquals(13.5, dt1.calcMedia(), 1e-6, "\n> Error: Min: 10 ; Max: 20 ; dt1.calcMedia():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataCalcAverageTest2() {
			precond(dt1);
			Exception exception = 
					assertThrows(DatosException.class, () -> new Datos(inputData, 0.0, 4.0).calcMedia(), "\n> Error: Min: 0 ; Max: 4 ; calcMedia(): No exception was thrown");
			assertEquals("No hay datos en el rango especificado", exception.getMessage(), "\n> Error: Min: 0 ; Max: 4 ; calcMedia(): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataCalcStandardDeviationTest1() throws Exception {
			precond(dt1);
			assertEquals(2.54951, dt1.calcDesvTipica(), 1e-6, "\n> Error: Min: 10 ; Max: 20 ; dt1.calcDesvTipica():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataCalcStandardDeviationTest2() {
			precond(dt1);
			Exception exception =
					assertThrows(DatosException.class, () -> new Datos(inputData, 0.0, 4.0).calcDesvTipica(), "\n> Error: Min: 0 ; Max: 4 ; calcDesvTipica(): No exception was thrown");
			assertEquals("No hay datos en el rango especificado", exception.getMessage(), "\n> Error: Min: 0 ; Max: 4 ; calcDesvTipica(): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataSetRangeTest1() throws Exception {
			precond(dt1);
			dt1.setRango(" 5.7 ; 13.7 ");
			assertEquals(11.5, dt1.calcMedia(), 1e-6, "\n> Error: Min: 5.7 ; Max: 13.7 ; dt1.calcMedia():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataSetRangeTest2() {
			Exception exception = 
					assertThrows(DatosException.class, () -> dt1.setRango(" 10 "), "\n> Error: dt1.setRango(\" 10 \"): No exception was thrown");
			assertEquals("Error en los datos al establecer el rango", exception.getMessage(), "\n> Error: dt1.setRango(\" 10 \"): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataSetRangeTest3() {
			precond(dt1);
			Exception exception = 
					assertThrows(DatosException.class, () -> dt1.setRango(" 10 ; xxx "), "\n> Error: dt1.setRango(\" 10 ; xxx \"): No exception was thrown");
			assertEquals("Error en los datos al establecer el rango", exception.getMessage(), "\n> Error: dt1.setRango(\" 10 ; xxx \"): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataSetRangeTest4() {
			precond(dt1);
			Exception exception = 
					assertThrows(DatosException.class, () -> dt1.setRango(" xxx ; 10 "), "\n> Error: dt1.setRango(\" xxx ; 10 \"): No exception was thrown");
			assertEquals("Error en los datos al establecer el rango", exception.getMessage(), "\n> Error: dt1.setRango(\" xxx ; 10 \"): exception.getMessage():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataToStringTest1() {
			precond(dt1);
			assertEquals(normalize("Min: 10.0, Max: 20.0, [ 5.5, 9.5, 10.5, 12.5, 13.5, 17.5, 20.5, 25.5 ], [ Joe, Mariah, Frank, Anna, John, Dolly ], Media: 13.5, DesvTipica: 2.5495097567963922"),
						 normalize(dt1.toString()), "\n> Error: dt1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void dataToStringTest2() {
			precond(dt1);
			Datos dt2 = new Datos(inputData, 0.0, 4.0);
			//------------------------
			assertEquals(normalize("Min: 0.0, Max: 4.0, [ 5.5, 9.5, 10.5, 12.5, 13.5, 17.5, 20.5, 25.5 ], [ Joe, Mariah, Frank, Anna, John, Dolly ], Media: ERROR, DesvTipica: ERROR"),
						 normalize(dt2.toString()),
						 "\n> Error: dt2.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainData {
		@BeforeAll
		public void beforeAll() {
			// Code executed before the first test method
			System.out.println("Start of MainData JUnit Test");
		}
		@AfterAll
		public void afterAll() {
			// Code executed after the last test method
			System.out.println("End of MainData JUnit Test");
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
		public void MainDataMainTest1() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaDatos.main(new String[]{"10", "20", "5", "9", "Joe", "10", "Mariah", "12", "13", "Frank", "17", "20", "Anna", "25", "John", "Dolly"});
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("Min : 10.0 , Max : 20.0 , [ 5.0 , 9.0 , 10.0 , 12.0 , 13.0 , 17.0 , 20.0 , 25.0 ] , [ Joe , Mariah , Frank , Anna , John , Dolly ] , Media : 14.4 , DesvTipica : 3.6110940170535577 Min : 0.0 , Max : 4.0 , [ 5.0 , 9.0 , 10.0 , 12.0 , 13.0 , 17.0 , 20.0 , 25.0 ] , [ Joe , Mariah , Frank , Anna , John , Dolly ] , Media : ERROR , DesvTipica : ERROR  Error en los datos al establecer el rango"),
						 normalize(output),
						 "\n> Error: MainData.main(\"10\", \"20\", \"5\", \"9\", \"Joe\", \"10\", \"Mariah\", \"12\", \"13\", \"Frank\", \"17\", \"20\", \"Anna\", \"25\", \"John\", \"Dolly\"):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MainDataMainTest2() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaDatos.main(new String[]{"10"});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("Error , no hay valores suficientes"),
						 normalize(salida),
						 "\n> Error: MainData.main(\"10\"):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void MainDataMainTest3() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				PruebaDatos.main(new String[]{"10", "hello", "5", "9", "Joe", "10", "Mariah", "12", "13", "Frank", "17", "20", "Anna", "25", "John", "Dolly"});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("Error, al convertir un valor a numero real (For input string: \"hello\")"),
						 normalize(salida),
						 "\n> Error: MainData.main(\"10\", \"hello\", \"5\", \"9\", \"Joe\", \"10\", \"Mariah\", \"12\", \"13\", \"Frank\", \"17\", \"20\", \"Anna\", \"25\", \"John\", \"Dolly\"):");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({JUnitTestDatosException.class ,
				JUnitTestData.class ,
				JUnitTestMainData.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestDatosException.class),
						selectClass(JUnitTestData.class),
						selectClass(JUnitTestMainData.class))
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
