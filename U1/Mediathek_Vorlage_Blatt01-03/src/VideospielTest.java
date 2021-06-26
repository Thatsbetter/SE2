import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VideospielTest
{

    private static final String KOMMENTAR = "Kommentar";
    private static final String TITEL = "Titel";
    private static final String VDEOSPIEL_BEZEICHNUNG = "Videospiel";
    private static final String SYSTEM = "PS4";
    private Videospiel _vs1;

    public VideospielTest()
    {
        _vs1 = getMedium();

    }

    @Test
    public void testGetMedienBezeichnung()
    {
        String vsBezeichnung = VDEOSPIEL_BEZEICHNUNG;
        assertEquals(vsBezeichnung, _vs1.getMedienBezeichnung());
    }

    @Test
    public void testKonstruktor()
    {
        assertEquals(TITEL, _vs1.getTitel());
        assertEquals(KOMMENTAR, _vs1.getKommentar());
        assertEquals(SYSTEM, _vs1.getSystem());

    }

    @Test
    public void testGetKommentar()
    {
        String vsKommentar = KOMMENTAR;
        assertEquals(vsKommentar, _vs1.getKommentar());

    }

    @Test
    public void testGetTitel()
    {
        String vsTitel = TITEL;
        assertEquals(vsTitel, _vs1.getTitel());
    }

    protected Videospiel getMedium()
    {
        return new Videospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
