package mediathek.medien;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mediathek.fachwerte.Geldbetrag;

//Die in den TestfÃ¤llen verwendeten assert-Anweisungen werden Ã¼ber
//einen sogenannten statischen Import bereitgestellt, zum Beispiel:
//import static org.junit.Assert.assertEquals;
//
//Um die Annotationen @Test und @Before verwenden zu kÃ¶nnen, mÃ¼ssen diese
//importiert werden, zum Beispiel:
//import org.junit.Test;

public class CDTest
{
    private static final String KOMMENTAR = "Kommentar";
    private static final String TITEL = "Titel";
    private static final String CD_BEZEICHNUNG = "CD";
    private static final String INTERPRET = "CD Interpret";
    private static final int LAENGE = 100;
    private static final String FORMATIERTER_STRING = CD_BEZEICHNUNG
            + ":\n    Titel: " + TITEL + "\n    Kommentar: " + KOMMENTAR
            + "\n    Interpret: " + INTERPRET + "\n    SpiellÃ¤nge: " + LAENGE
            + "\n";
    private CD _cd1;
    private CD _cd2;

    @Before
    // Die setUp()-Methode wird nicht mehr anhand ihres Namens erkannt, sondern
    // anhand der Annotation @Before.
    public void setUp()
    {
        _cd1 = getMedium();
        _cd2 = getMedium();
    }

    @Test
    public void testGetMedienBezeichnung()
    {
        String cdBezeichnung = CD_BEZEICHNUNG;
        assertEquals(cdBezeichnung, _cd1.getMedienBezeichnung());
    }

    @Test
    // Alle Testmethoden erhalten die Annotation @Test. DafÃ¼r mÃ¼ssen diese nicht
    // mehr mit test im Namen beginnen. Dies wird jedoch aus Gewohnheit
    // oft weiter verwendet.
    public void testKonstruktor()
    {
        assertEquals(TITEL, _cd1.getTitel());
        assertEquals(KOMMENTAR, _cd1.getKommentar());
        assertEquals(LAENGE, _cd1.getSpiellaenge());
        assertEquals(INTERPRET, _cd1.getInterpret());
    }

    @Test
    public final void testSetter()
    {
        _cd1.setTitel("Titel2");
        assertEquals("Titel2", _cd1.getTitel());
        _cd1.setKommentar("Kommentar2");
        assertEquals("Kommentar2", _cd1.getKommentar());
        _cd1.setInterpret("Interpret2");
        assertEquals("Interpret2", _cd1.getInterpret());
        _cd1.setSpiellaenge(99);
        assertEquals(99, _cd1.getSpiellaenge());
    }

    @Test
    /*
     * Von ein und der selben CD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden kÃ¶nnen.
     */
    public void testEquals()
    {
        assertFalse("Mehrere Exemplare der gleichen CD sollten ungleich sein",
                _cd1.equals(_cd2));
        assertTrue("Dasselbe Exemplare der gleichen CD sollte gleich sein",
                _cd1.equals(_cd1));
    }

    @Test
    public final void testGetFormatiertenString()
    {
        Medium medium = getMedium();
        assertNotNull(medium.getFormatiertenString());
        assertEquals(FORMATIERTER_STRING, medium.getFormatiertenString());
    }

    private CD getMedium()
    {
        return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
    }

    @Test
    /*
     * Testet ob der Betrag/Gebuehr richtig berechnet wurde
     * indem, er mit dem erwaterten Wert vergleicht.
     */
    public void testGebuehr()
    {
        Geldbetrag b1 = new Geldbetrag(300);
        Geldbetrag b2 = new Geldbetrag(600);
        Geldbetrag b3 = new Geldbetrag(3300);

        assertEquals(_cd1.berechneMietgebuehr(1), b1);
        assertEquals(_cd1.berechneMietgebuehr(2), b2);
        assertEquals(_cd1.berechneMietgebuehr(11), b3);

    }

}
