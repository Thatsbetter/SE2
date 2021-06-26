import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VideospielTest
{
    private static final String KOMMENTAR = "Kommentar";
    private static final String TITEL = "Titel";
    private static final String SPIEL_BEZEICHNUNG = "Videospiel";
    private static final String SYSTEM = "Spiel System";

    private Videospiel _spiel1;
    private Videospiel _spiel2;

    public VideospielTest()
    {
        _spiel1 = getMedium();
        _spiel2 = getMedium();
    }

    @Test
    public void testGetMedienBezeichnung()
    {
        String spielBezeichnung = SPIEL_BEZEICHNUNG;
        assertEquals(spielBezeichnung, _spiel1.getMedienBezeichnung());
    }

    @Test
    public void testKonstruktor()
    {
        assertEquals(TITEL, _spiel1.getTitel());
        assertEquals(KOMMENTAR, _spiel1.getKommentar());
        assertEquals(SYSTEM, _spiel1.getSystem());
    }

    @Test
    /*
     * Von ein und der selben CD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden kÃ¶nnen.
     */
    public void testEquals()
    {
        assertFalse(
                "Mehrere Exemplare des gleichen Spiels sollten ungleich sein",
                _spiel1.equals(_spiel2));
        assertTrue("Dasselbe Exemplare des gleichen Spiels sollten gleich sein",
                _spiel1.equals(_spiel1));
        assertEquals(
                _spiel1.getMedienBezeichnung() + ":\n" + "    " + "Titel: "
                        + _spiel1.getTitel() + "\n" + "    " + "Kommentar: "
                        + _spiel1.getKommentar() + "\n" + "    " + "System: "
                        + _spiel1.getSystem() + "\n",
                _spiel1.getFormatiertenString());

    }

    private Videospiel getMedium()
    {
        return new Videospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
