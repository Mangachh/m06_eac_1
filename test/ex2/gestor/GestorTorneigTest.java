/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ex2.gestor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author joan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestorTorneigTest {

    private static final String TORNEIG_XML = "./torneig.xml";
    private static final String TORNEIG_XML_ACTUALITZAT = "./torneig_actualitzat.xml";

    private final GestorTorneig gestorTorneig = new GestorTorneig();

    public GestorTorneigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void aTestGetMillorJugador() throws GestorTorneigException {
        System.out.println("getMillorJugador");
        String fitxer = TORNEIG_XML;
        String expResult = "Anna T.;2;29";
        String result = gestorTorneig.getMillorJugador(fitxer);
        assertEquals(expResult, result);
    }

    /**
     * Test of afegirJugador method, of class GestorTorneig.
     *
     * @throws ex2.gestor.GestorTorneigException
     */
    @Test
    public void bTestAfegirJugador() throws GestorTorneigException {
        System.out.println("afegirJugador");
        String jugador = "Andrea M.;1;22";
        String origen = TORNEIG_XML;
        String desti = TORNEIG_XML_ACTUALITZAT;

        gestorTorneig.afegirJugador(jugador, origen, desti);
    }

    @Test
    public void cTestGetCategoria() throws GestorTorneigException {
        System.out.println("testGetCategoria");
        String desti = TORNEIG_XML_ACTUALITZAT;

        String expResult = "A";

        String result = gestorTorneig.getCategoria(desti);
        assertEquals(expResult, result);
    }

    @Test
    public void dTestGetMillorJugador() throws GestorTorneigException {
        System.out.println("getMillorJugador");
        String fitxer = TORNEIG_XML_ACTUALITZAT;
        String expResult = "Andrea M.;1;22";
        String result = gestorTorneig.getMillorJugador(fitxer);
        assertEquals(expResult, result);
    }

    @Test
    public void eTestAfegirJugador() throws GestorTorneigException {
        System.out.println("afegirJugador");
        String jugador = "Joan C.;33;22";
        String origen = TORNEIG_XML;
        String desti = TORNEIG_XML_ACTUALITZAT;

        gestorTorneig.afegirJugador(jugador, origen, desti);
    }

    @Test
    public void fTestGetCategoria() throws GestorTorneigException {
        System.out.println("testGetCategoria");
        String expResult = "B";
        String desti = TORNEIG_XML_ACTUALITZAT;

        String result = gestorTorneig.getCategoria(desti);
        assertEquals(expResult, result);
    }

    @Test
    public void gTestAfegirJugador() throws GestorTorneigException {

        System.out.println("afegirJugador");
        String jugador = "Jordi L.;115;22";
        String origen = TORNEIG_XML;
        String desti = TORNEIG_XML_ACTUALITZAT;

        gestorTorneig.afegirJugador(jugador, origen, desti);
    }

    /**
     * Test of getCategoria method, of class GestorTorneig.
     *
     * @throws ex2.gestor.GestorTorneigException
     */
    @Test
    public void hTestGetCategoria() throws GestorTorneigException {
        System.out.println("getCategoria");
        String fitxer = TORNEIG_XML_ACTUALITZAT;
        String expResult = "C";
        String result = gestorTorneig.getCategoria(fitxer);
        assertEquals(expResult, result);

    }


}
