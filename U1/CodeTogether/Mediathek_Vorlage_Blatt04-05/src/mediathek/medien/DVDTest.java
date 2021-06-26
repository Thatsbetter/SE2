package mediathek.medien;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DVDTest
{
    private static final String KOMMENTAR = "Kommentar";
    private static final String TITEL = "Titel";
    private static final String BEZEICHNUNG = "DVD";
    private static final int LAENGE = 100;
    private static final String REGISSEUR = "DVD Regisseur";
    private static final String FORMATIERTER_STRING = BEZEICHNUNG
            + ":\n    Titel: " + TITEL + "\n    Kommentar: " + KOMMENTAR
            + "\n    Regisseur: " + REGISSEUR + "\n    Laufzeit: " + LAENGE
            + "\n";
    private DVD _dvd1;
    private DVD _dvd2;

    @Before
    public void setUp()
    {
        _dvd1 = getMedium();
        _dvd2 = getMedium();
    }

    @Test
    public void testGetMedienBezeichnung()
    {
        String dvdBezeichnung = BEZEICHNUNG;
        assertEquals(dvdBezeichnung, _dvd1.getMedienBezeichnung());
    }

    @Test
    public void testKonstruktor()
    {
        assertEquals(TITEL, _dvd1.getTitel());
        assertEquals(KOMMENTAR, _dvd1.getKommentar());
        assertEquals(LAENGE, _dvd1.getLaufzeit());
        assertEquals(REGISSEUR, _dvd1.getRegisseur());
    }

    @Test
    public final void testSetter()
    {
        _dvd1.setKommentar("Kommentar2");
        assertEquals("Kommentar2", _dvd1.getKommentar());
        _dvd1.setTitel("Titel2");
        assertEquals("Titel2", _dvd1.getTitel());
        _dvd1.setLaufzeit(90);
        assertEquals(90, _dvd1.getLaufzeit());
        _dvd1.setRegisseur("Regisseur2");
        assertEquals("Regisseur2", _dvd1.getRegisseur());
    }

    @Test
    /*
     * Von ein und der selben DVD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden kÃ¶nnen.
     */
    public void testEquals()
    {
        assertFalse("Mehrere Exemplare der gleichen DVD sollten ungleich sein",
                _dvd1.equals(_dvd2));
        assertTrue("Dasselbe Exemplare der gleichen DVD sollte gleich sein",
                _dvd1.equals(_dvd1));
    }

    @Test
    public final void testGetFormatiertenString()
    {
        Medium medium = getMedium();
        assertNotNull(medium.getFormatiertenString());
        assertEquals(FORMATIERTER_STRING, medium.getFormatiertenString());
    }

    protected DVD getMedium()
    {
        return new DVD(TITEL, KOMMENTAR, REGISSEUR, LAENGE);
    }

    public void testGebuehr()
    {
        assertEquals(_dvd1.berechneMietgebuehr(1), 300);
        assertEquals(_dvd1.berechneMietgebuehr(2), 600);
        assertEquals(_dvd1.berechneMietgebuehr(11), 3300);

    }
}
