package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheAlkuSaldo() {
        Varasto varasto2 = new Varasto(10, -1);
        assertEquals(0, varasto2.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheTilavuus() {
        assertEquals(0, new Varasto(-10).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriltaOikeaAlkusaldo() {
        Varasto varasto2 = new Varasto(10, 1);
        assertEquals(1, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheTilavuusAlkuSaldolla() {
        Varasto varasto2 = new Varasto(-10, 1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriMaksimiAlkusaldo() {
        Varasto varasto2 = new Varasto(10, 100);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysMaksimiinAsti() {
        Varasto varasto2 = new Varasto(10, 1);
        varasto2.lisaaVarastoon(1000);

        // jos lisätään yli kapasiteetin, pitää saldon olla yhtä suuri kuin kapasiteetti
        assertEquals(varasto2.getTilavuus(), varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void lisaaVirheMaara() {
        Varasto varasto2 = new Varasto(10, 1);
        varasto2.lisaaVarastoon(-1);

        // negatiivisen lisäyksen teko ei tee mitään
        assertEquals(1, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void ottamiselleVirheArvo() {
        assertEquals(0, varasto.otaVarastosta(-100), vertailuTarkkuus);
    }

    @Test
    public void otaKaikki() {
        Varasto varasto2 = new Varasto(10,5);
        assertEquals(0, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void testaaToString() {
        Varasto varasto2 = new Varasto(10,10);
        String vertailuStringi = "saldo = 10.0, vielä tilaa 0.0";

        assertEquals(vertailuStringi, varasto2.toString());
    }
}