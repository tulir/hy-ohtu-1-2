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
    public void negativeSizeIsZero() {
        assertEquals(1, new Varasto(-5).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void validStartContent() {
        assertEquals(5, new Varasto(6, 5).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void startOverflowRemoved() {
        assertEquals(4, new Varasto(4, 5).getSaldo(), vertailuTarkkuus);
    }

	@Test
	public void negativeStartContent() {
		assertEquals(0, new Varasto(6, -5).getSaldo(), vertailuTarkkuus);
	}

	@Test
	public void negativeSizeWithStartContent() {
		assertEquals(0, new Varasto(-5, 5).getTilavuus(), vertailuTarkkuus);
	}


	@Test
	public void toStringTest() {
		assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
	}

	@Test
	public void invalidAddChangesNothing() {
		varasto.lisaaVarastoon(-100);
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	}

	@Test
	public void invalidRemoveChangesNothing() {
		varasto.otaVarastosta(-100);
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	}

	@Test
	public void overflowAddFillsStorage() {
		varasto.lisaaVarastoon(9001);
		assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
	}

	@Test
	public void overflowRemoveClearsStorage() {
		varasto.lisaaVarastoon(5);
		varasto.otaVarastosta(9001);
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	}

	@Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
