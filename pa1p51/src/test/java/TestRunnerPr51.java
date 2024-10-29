
//--------------------------------------------------------------------------
//----------------------------------------------------------------------


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

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

import rank.*;

//--------------------------------------------------------------------------

public class TestRunnerPr51 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestLink {
		private Link p1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Link JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Link JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			p1 = new Link("AAA", "BBB");
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void linkCtorTest1() {
			assertAll("linkCtorTest1",
				() -> assertEquals("AAA",
						 p1.getOrigin(),
						 "\n> Error: p1.getOrigin():"),
				() -> assertEquals("BBB",
						 p1.getLinked(),
						 "\n> Error: p1.getLinked():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void linkEqualsTest1() throws Exception {
			precond("AAA", p1.getOrigin());
			precond("BBB", p1.getLinked());
			//----------------------
			assertAll("linkEqualsTest1",
				() -> {
					Link p2 = new Link("AAA", "BBB");
					assertTrue(p1.equals(p2), "\n> Error: p1.equals(p2): ");
			//------------------------
					assertTrue(p1.equals((Object)p2), "\n> Error: p1.equals((Object)p2): ");
				},
			//------------------------
				() -> {
					Link p3 = new Link("aaa", "bbb");
					assertTrue(p1.equals(p3), "\n> Error: p1.equals(p3): ");
				},
			//------------------------
				() -> {
					Link p4 = new Link("AAA", "CCC");
					assertFalse(p1.equals(p4), "\n> Error: p1.equals(p4): ");
				},
			//------------------------
				() -> {
					Link p5 = new Link("CCC", "BBB");
					assertFalse(p1.equals(p5), "\n> Error: p1.equals(p5): ");
				},
			//------------------------
				() -> assertFalse(p1.equals(null), "\n> Error: p1.equals(null): "),
				() -> assertFalse(p1.equals("Esto es un String"), "\n> Error: p1.equals(\"Esto es un String\"): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void linkHashCodeTest1() throws Exception {
			precond("AAA", p1.getOrigin());
			precond("BBB", p1.getLinked());
			//----------------------
			int p1HashCode = p1.hashCode();
			//------------------------
			assertAll("linkHashCodeTest1",
					() -> {
						Link p2 = new Link("AAA", "BBB");
						assertEquals(p1HashCode, p2.hashCode(), "\n> Error: p2.hashCode(): ");
					},
			//------------------------
					() -> {
						Link p3 = new Link("aaa", "bbb");
						assertEquals(p1HashCode, p3.hashCode(), "\n> Error: p3.hashCode(): ");
					},
			//------------------------
					() -> {
						Link p4 = new Link("AAA", "CCC");
						assertNotEquals(p1HashCode, p4.hashCode(), "\n> Error: p4.hashCode(): ");
					},
			//------------------------
					() -> {
						Link p5 = new Link("CCC", "BBB");
						assertNotEquals(p1HashCode, p5.hashCode(), "\n> Error: p5.hashCode(): ");
					}
					);
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void linkToStringTest1() {
			precond("AAA", p1.getOrigin());
			precond("BBB", p1.getLinked());
			assertEquals(normalize("AAA->BBB"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestSite {
		private Site p1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Site JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Site JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			p1 = new Site("AAA");
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteCtorTest1() {
			assertEquals("AAA",
						 p1.getName(),
						 "\n> Error: p1.getName():");
			assertEquals(0.0,
						 p1.getRank(), 0.00001, "\n> Error: p1.getRank():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteCtorTest2() {
			assertTrue(((Object)p1 instanceof Comparable<?>), "\n> Error: Site implements Comparable<?>:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteAddRankTest1() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			//----------------------
			p1.addRank(13.5);
			assertAll("siteAddRankTest1",
					() -> assertEquals("AAA",
							p1.getName(),
							"\n> Error: p1.getName(); p1.addRank(13.5):"),
					() -> assertEquals(13.5,
							p1.getRank(), 0.00001,
							"\n> Error: p1.getRank(); p1.addRank(13.5):"));
			//----------------------
			p1.addRank(4.7);
			assertAll("siteAddRankTest1b",
					() -> assertEquals("AAA",
							p1.getName(),
							"\n> Error: p1.getName(); p1.addRank(4.7):"),
					() -> assertEquals(18.2,
							p1.getRank(), 0.00001,
							"\n> Error: p1.getRank(); p1.addRank(4.7):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteEqualsTest1() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			assertAll("siteEqualsTest1",
				//----------------------
				() -> {
					Site p2 = new Site("AAA");
					assertTrue(p1.equals(p2), "\n> Error: p1.equals(p2): ");
					//------------------------
					assertTrue(p1.equals((Object)p2), "\n> Error: p1.equals((Object)p2): ");
				},
				//------------------------
				() -> {
					Site p3 = new Site("aaa");
					assertTrue(p1.equals(p3), "\n> Error: p1.equals(p3): ");
				},
				//------------------------
				() -> {
					Site p4 = new Site("AAA"); p4.addRank(7);
					assertTrue(p1.equals(p4), "\n> Error: p1.equals(p4): ");
				},
				//------------------------
				() -> {
					Site p5 = new Site("CCC");
					assertFalse(p1.equals(p5), "\n> Error: p1.equals(p5): ");
				},
				//------------------------
				//------------------------
				() -> assertFalse(p1.equals(null), "\n> Error: p1.equals(null): "),
				() -> assertFalse(p1.equals("Esto es un String"), "\n> Error: p1.equals(\"Esto es un String\"): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteHashCodeTest1() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			//----------------------
			int p1HashCode = p1.hashCode();
			assertAll("siteHashCodeTest1",
			//------------------------
				() -> { 
					Site p2 = new Site("AAA");
					assertEquals(p1HashCode, p2.hashCode(), "\n> Error: p2.hashCode(): ");
					},
			//------------------------
				() -> {
					Site p3 = new Site("aaa");
					assertEquals(p1HashCode, p3.hashCode(), "\n> Error: p3.hashCode(): ");
				},
			//------------------------
				() -> {
					Site p4 = new Site("AAA"); p4.addRank(7);
					assertEquals(p1HashCode, p4.hashCode(), "\n> Error: p4.hashCode(): ");
				},
			//------------------------
				() -> {
					Site p5 = new Site("CCC");
					assertNotEquals(p1HashCode, p5.hashCode(), "\n> Error: p5.hashCode(): ");
				});
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteCompareToTest1() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			assertAll("siteCompareToTest1",
				//----------------------
				() -> {
					Site p2 = new Site("AAA");
					assertEquals(0, p1.compareTo(p2), "\n> Error: p1.compareTo(p2): ");
				},
				//------------------------
				() -> {
					Site p3 = new Site("aaa");
					assertEquals(0, p1.compareTo(p3), "\n> Error: p1.compareTo(p3): ");
				},
				//------------------------
				() -> {
					Site p4 = new Site("AAA"); p4.addRank(7);
					assertEquals(0, p1.compareTo(p4), "\n> Error: p1.compareTo(p4): ");
				},
				//------------------------
				() -> {
					Site p5 = new Site("BBB");
					assertTrue(p1.compareTo(p5) < 0, "\n> Error: p1.compareTo(p5): ");
					assertTrue(p5.compareTo(p1) > 0, "\n> Error: p5.compareTo(p1): ");
				},
				//------------------------
				() -> {
					Site p6 = new Site("bbb");
					assertTrue(p1.compareTo(p6) < 0, "\n> Error: p1.compareTo(p6): ");
					assertTrue(p6.compareTo(p1) > 0, "\n> Error: p6.compareTo(p1): ");
				});
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteToStringTest1() {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			assertEquals(normalize("AAA(0.00000)"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteToStringTest2() {
			p1.addRank(7.12345678);
			precond("AAA", p1.getName());
			precond(7.12345678, p1.getRank(), 0.00001);
			assertEquals(normalize("AAA(7.12346)"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteToStringTest3() {
			p1.addRank(7.1234444);
			precond("AAA", p1.getName());
			precond(7.1234444, p1.getRank(), 0.00001);
			assertEquals(normalize("AAA(7.12344)"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] inputData = {
			"I->C",
			"J->C",
			"A->C",
			"A->D",
			"B->C",
			"B->F",
			"D->F",
			"E->B",
			"E->H",
			"F->G",
			"F->H",
			"G->E",
			"G->H",
		};
	public static String normalizeWeb(String wstr) {
		try {
			StringBuilder sb = new StringBuilder(normalize(wstr));
			int webidx = sb.indexOf("Web (");
			while (webidx >= 0) {
				webidx += 5;
				for (int i = 0; i < 2; ++i) {
					int inicio = sb.indexOf("[", webidx);
					if (inicio > webidx) {
						int fin = sb.indexOf("]", inicio+1);
						if (fin > inicio) {
							webidx = fin;
							String nuevo = normalizeListStr(sb.substring(inicio, fin+1), "\\s*,\\s*", "[", "]");
							sb.replace(inicio, fin+1, nuevo);
							webidx = inicio + nuevo.length();
						}
					}
				}
				webidx = sb.indexOf("Web (", webidx);
			}
			wstr = sb.toString();
		} catch (Exception ex) {
			// ignore
		}
		return normalize(wstr);
	}
	private static final String inputList = normalizeWeb("Web([A(0.00000), B(0.00000), C(0.00000), D(0.00000), E(0.00000), F(0.00000), G(0.00000), H(0.00000), I(0.00000), J(0.00000)], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E])");
	/*private*/ static final String[] inputSites = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
	};
	private static final String inputListClicks = normalizeWeb("Web([A(0.50000), B(0.67156), C(1.29288), D(0.62500), E(0.68627), F(0.98038), G(0.74509), H(1.10293), I(0.50000), J(0.50000)], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E])");
	public static Web createWeb() throws Exception {
		Web web1 = new Web();
		try {
			for (String arc: inputData) {
				web1.addLink(arc);
			}
		} catch (Exception e) {
			// ignorar
		}
		return web1;
	}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestWeb {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Web JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Web JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webCtorTest1() throws Exception {
			Web web2 = new Web();
			assertEquals(normalize("Web([], [])"),
						 normalize(web2.toString()),
						 "\n> Error: web2.Ctor(); web2.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webAddLinkTest1() throws Exception {
			Web web1 = createWeb();
			assertEquals(inputList,
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.Ctor(); web.addLink(); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webAddLinkTest2() throws Exception {
			Web web2 = new Web();
			Exception exception = assertThrows(IllegalArgumentException.class, () -> web2.addLink("AA;BB"),
					"\n> Error: web2.addLink(AA;BB): No exception thrown");
			assertTrue(exception.getMessage().contains("AA;BB"), "\n> Error: web2.addLink(AA;BB): exception.getMessage().contains(AA;BB):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSiteTest1() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			for (String st : inputSites) {
				Site s = web1.getSite(st);
				assertAll("webGetSiteTest1",
					() -> assertEquals(st,
							 s.getName(),
							 "\n> Error: web.getSite("+st+").getName():"),
					() -> assertEquals(0.0,
							 s.getRank(), 0.00001,
							 "\n> Error: web.getSite("+st+").getRank():"));
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSiteTest2() throws Exception {
			Web web2 = new Web();
			Exception exception = assertThrows(NoSuchElementException.class, () -> web2.getSite("XXX"), 
					"\n> Error: web2.getSite(XXX): No exception thrown");
			assertTrue(exception.getMessage().contains("XXX"), "\n> Error: web2.getSite(XXX): exception.getMessage().contains(XXX):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSiteTest3() throws Exception {
			Web web1 = createWeb();
			Exception exception = assertThrows(NoSuchElementException.class, () -> web1.getSite("XXX"), 
					"\n> Error: web1.getSite(XXX): No exception thrown");
			assertTrue(exception.getMessage().contains("XXX"), "\n> Error: web1.getSite(XXX): exception.getMessage().contains(XXX):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetNamesTest1() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			java.util.TreeSet<String> st = new java.util.TreeSet<>(web1.getNames());
			assertEquals(normalize("[A, B, C, D, E, F, G, H, I, J]"),
						 normalize(st.toString()),
						 "\n> Error: web.getNames():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetNamesTest2() throws Exception {
			Web web1 = new Web();
			precond(normalize("Web([], [])"),
					normalize(web1.toString()));
			java.util.TreeSet<String> st = new java.util.TreeSet<>(web1.getNames());
			assertEquals(normalize("[]"),
						 normalize(st.toString()),
						 "\n> Error: web.getNames():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webClickTest1() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			for (String st : inputSites) {
				web1.click(st);
			}
			assertEquals(inputListClicks,
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.click("+java.util.Arrays.toString(inputSites)+"); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webClickTest2() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			web1.click("XXX");
			assertEquals(inputList,
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.click(XXX); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webSimulateClickTest1() throws Exception {
			Web web1 = createWeb();
			//----------------------
			((java.util.Random)getMemberObject(web1, Web.class, java.util.Random.class, "rndm")).setSeed(1);
			//----------------------
			precond(inputList,
					normalizeWeb(web1.toString()));
			web1.simulateClick(100);
			assertEquals(normalizeWeb("Web([A(5.00000), B(4.87248), C(12.96808), D(5.25000), E(7.49017), F(9.84308), G(7.96075), H(11.32339), I(6.00000), J(5.00000)], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E])"),
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.simulateClick(); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSitesByNameTest1() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			java.util.SortedSet<Site> st = web1.getSitesByName();
			assertEquals(normalize("[A(0.00000), B(0.00000), C(0.00000), D(0.00000), E(0.00000), F(0.00000), G(0.00000), H(0.00000), I(0.00000), J(0.00000)]"),
						 normalize(st.toString()),
						 "\n> Error: web.getSitesByName():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSitesByRankTest1() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			java.util.SortedSet<Site> st = web1.getSitesByRank();
			assertEquals(normalize("[A(0.00000), B(0.00000), C(0.00000), D(0.00000), E(0.00000), F(0.00000), G(0.00000), H(0.00000), I(0.00000), J(0.00000)]"),
						 normalize(st.toString()),
						 "\n> Error: web.getSitesByRank():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webGetSitesByRankTest2() throws Exception {
			Web web1 = createWeb();
			precond(inputList,
					normalizeWeb(web1.toString()));
			//----------------------
			for (String st : inputSites) {
				web1.click(st);
			}
			precond(inputListClicks,
					normalizeWeb(web1.toString()));
			//----------------------
			java.util.SortedSet<Site> st = web1.getSitesByRank();
			assertEquals(normalize("[C(1.29288), H(1.10293), F(0.98038), G(0.74509), E(0.68627), B(0.67156), D(0.62500), A(0.50000), I(0.50000), J(0.50000)]"),
						 normalize(st.toString()),
						 "\n> Error: web.getSitesByRank():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestSiteExtended {
		private SiteExtended p1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of SiteExtended JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of SiteExtended JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			p1 = new SiteExtended("AAA");
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedCtorTest1() {
			assertEquals("AAA",
						 p1.getName(),
						 "\n> Error: p1.getName():");
			assertEquals(0.0,
						 p1.getRank(), 0.00001,
						 "\n> Error: p1.getRank():");
			assertTrue(p1.isValid(), "\n> Error: p1.isValid():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedCtorTest2() {
			assertTrue(((Object)p1 instanceof Site), "\n> Error: SiteExtended extends Site:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedSetValidTest1() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			precond(true, p1.isValid());
			//----------------------
			p1.setValid(false);
			assertEquals("AAA",
						 p1.getName(),
						 "\n> Error: p1.getName(); p1.setValid(false):");
			assertEquals(0.0,
						 p1.getRank(), 0.00001,
						 "\n> Error: p1.getRank(); p1.setValid(false):");
			assertFalse(p1.isValid(), "\n> Error: p1.isValid(); p1.setValid(false):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedSetValidTest2() throws Exception {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			precond(true, p1.isValid());
			//----------------------
			p1.setValid(false);
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			precond(false, p1.isValid());
			//----------------------
			p1.setValid(true);
			assertEquals("AAA",
						 p1.getName(),
						 "\n> Error: p1.getName(); p1.setValid(true):");
			assertEquals(0.0,
						 p1.getRank(), 0.00001,
						 "\n> Error: p1.getRank(); p1.setValid(true):");
			assertTrue(p1.isValid(), "\n> Error: p1.isValid(); p1.setValid(true):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest1() {
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			precond(true, p1.isValid());
			assertEquals(normalize("AAA(0.00000)"),
						 normalize(p1.toString()),"\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest1X() {
			p1.setValid(false);
			precond("AAA", p1.getName());
			precond(0.0, p1.getRank(), 0.00001);
			precond(false, p1.isValid());
			assertEquals(normalize("AAA(0.00000)*"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest2() {
			p1.addRank(7.12345678);
			precond("AAA", p1.getName());
			precond(7.12345678, p1.getRank(), 0.00001);
			precond(true, p1.isValid());
			assertEquals(normalize("AAA(7.12346)"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest2X() {
			p1.setValid(false);
			p1.addRank(7.12345678);
			precond("AAA", p1.getName());
			precond(7.12345678, p1.getRank(), 0.00001);
			precond(false, p1.isValid());
			assertEquals(normalize("AAA(7.12346)*"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest3() {
			p1.addRank(7.1234444);
			precond("AAA", p1.getName());
			precond(7.1234444, p1.getRank(), 0.00001);
			precond(true, p1.isValid());
			assertEquals(normalize("AAA(7.12344)"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedToStringTest3X() {
			p1.setValid(false);
			p1.addRank(7.1234444);
			precond("AAA", p1.getName());
			precond(7.1234444, p1.getRank(), 0.00001);
			precond(false, p1.isValid());
			assertEquals(normalize("AAA(7.12344)*"),
						 normalize(p1.toString()),
						 "\n> Error: p1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String inputListClicksExtended = normalizeWeb("Web([A(0.00000)*, B(0.67059), C(0.66764), D(0.50000), E(0.68235), F(0.91764), G(0.72941), H(1.08234), I(0.00000)*, J(0.00000)*], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E])");
	public static WebExtended createWebExtended() throws Exception {
		WebExtended web1 = new WebExtended();
		try {
			for (String arc: inputData) {
				web1.addLink(arc);
			}
		} catch (Exception e) {
			// ignorar
		}
		return web1;
	}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestWebExtended {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of WebExtended JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of WebExtended JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedCtorTest1() throws Exception {
			WebExtended web2 = new WebExtended();
			assertEquals(normalize("Web([], [])"),
						 normalize(web2.toString()),
						 "\n> Error: web2.Ctor(); web2.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void siteExtendedCtorTest2() {
			WebExtended web2 = new WebExtended();
			assertTrue(((Object)web2 instanceof Web), "\n> Error: WebExtended extends Web:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedAddLinkTest1() throws Exception {
			WebExtended web1 = createWebExtended();
			assertEquals(inputList,
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.Ctor(); web.addLink(); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedGetSiteTest1() throws Exception {
			WebExtended web1 = createWebExtended();
			precond(inputList,
					normalizeWeb(web1.toString()));
			for (String st : inputSites) {
				Site s = web1.getSite(st);
				SiteExtended sx = (SiteExtended)s;
				assertAll ("webExtendedGetSiteTest1",
					() -> assertTrue(((Object)s instanceof SiteExtended), "\n> Error: WebExtended extends Web:"),
					() -> assertEquals(st,
							 sx.getName(),
							 "\n> Error: web.getSite("+st+").getName():"),
					() -> assertEquals(0.0,
							 sx.getRank(), 0.00001,
							 "\n> Error: web.getSite("+st+").getRank():"),
					() -> assertTrue(sx.isValid(), "\n> Error:  web.getSite("+st+").isValid():"));
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedSwitchSiteWithNameTest1() throws Exception {
			WebExtended web1 = createWebExtended();
			precond(inputList,
					normalizeWeb(web1.toString()));
			for (String st : inputSites) {
				web1.switchSiteWithName(st);
				Site s = web1.getSite(st);
				SiteExtended sx = (SiteExtended)s;
				assertAll("webExtendedSwitchSiteWithNameTest1",
					() -> assertTrue(((Object)s instanceof SiteExtended),"\n> Error: WebExtended extends Web:"),
					() -> assertEquals(st,
							 sx.getName(),
							 "\n> Error: web.switchSiteWithName("+st+") ; web.getSite("+st+").getName():"),
					() -> assertEquals(0.0,
							 sx.getRank(), 0.00001,
							 "\n> Error: web.switchSiteWithName("+st+") ; web.getSite("+st+").getRank():"),
					() -> assertFalse(sx.isValid(),
							"\n> Error:  web.switchSiteWithName("+st+") ; web.getSite("+st+").isValid():"));
			}
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedClickTest1() throws Exception {
			WebExtended web1 = createWebExtended();
			web1.switchSiteWithName("A");
			web1.switchSiteWithName("I");
			web1.switchSiteWithName("J");
			precond(normalizeWeb("Web([A(0.00000)*, B(0.00000), C(0.00000), D(0.00000), E(0.00000), F(0.00000), G(0.00000), H(0.00000), I(0.00000)*, J(0.00000)*], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E])"),
					normalizeWeb(web1.toString()));
			for (String st : inputSites) {
				web1.click(st);
			}
			assertEquals(inputListClicksExtended,
						 normalizeWeb(web1.toString()),
						 "\n> Error: web1.click("+java.util.Arrays.toString(inputSites)+"); web1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void webExtendedClickTest2() throws Exception {
			WebExtended web1 = createWebExtended();
			precond(inputList,
					normalizeWeb(web1.toString()));
			web1.click("XXX");
			assertEquals(inputList,
					normalizeWeb(web1.toString()),
						 "\n> Error: web1.click(XXX); web1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestMainRank {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainRank JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainRank JUnit Test");
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
		public void mainRankMainTest1() {
			//----------------------
			((java.util.Random)getMemberObject(null, Web.class, java.util.Random.class, "rndm")).setSeed(1);
			//----------------------
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainRank2.main(new String[]{});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalizeWeb("Web([A(0.00000)*, B(0.00000), C(0.00000), D(0.00000), E(0.00000), F(0.00000), G(0.00000), H(0.00000), I(0.00000)*, J(0.00000)*], [B->C, F->G, G->H, A->C, D->F, F->H, A->D, E->H, B->F, J->C, I->C, E->B, G->E]) Paginas ordenadas alfabeticamente [A(0.00000)*, B(266.08527), C(265.01901), D(202.00000), E(264.34399), F(357.51901), G(289.37903), H(423.30829), I(0.00000)*, J(0.00000)*] Paginas ordenadas por rank [H(423.30829), F(357.51901), G(289.37903), B(266.08527), C(265.01901), E(264.34399), D(202.00000), A(0.00000)*, I(0.00000)*, J(0.00000)*]"),
						 normalizeWeb(salida),
						 "\n> Error: MainRank.main():");
		}
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestLink.class ,
				JUnitTestSite.class ,
				JUnitTestWeb.class,
				JUnitTestSiteExtended.class ,
				JUnitTestWebExtended.class ,
				JUnitTestMainRank.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestLink.class),
						selectClass(JUnitTestSite.class),
						selectClass(JUnitTestWeb.class),
						selectClass(JUnitTestSiteExtended.class),
						selectClass(JUnitTestWebExtended.class),
						selectClass(JUnitTestMainRank.class))
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
//				System.out.println(">>> Error: "+ac+" tests where not executed due to errors in other methods");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests tests failed due to errors in methods");
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
				String formato = "%.5f";
				if (num1.contains("e") || num1.contains("E")) {
					formato = "%.5e";
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
