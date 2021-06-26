import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Diese Klasse implementiert das Interface VerleihService. Es handelt sich
 * lediglich um eine Dummy-Implementation, um die GUI zu testen.
 * 
 * @author GUI-Team
 * @version SoSe 2021
 */
class DummyVerleihService extends AbstractObservableService
        implements VerleihService
{
    // Generator fÃ¼r Zufallszahlen und zufÃ¤llige boolsche Werte
    private static final Random RANDOM = new Random();

    private static final CD MEDIUM = new CD("Titel", "Kommentar", "Interpret",
            70);
    private static final Kundennummer KUNDENNUMMER = new Kundennummer(123456);
    private static final Kunde ENTLEIHER = new Kunde(KUNDENNUMMER, "Vorname",
            "Nachname");
    private static final Verleihkarte VERLEIHKARTE = new Verleihkarte(ENTLEIHER,
            MEDIUM, Datum.heute());

    public DummyVerleihService(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand)
    {
    }

    @Override
    /**
     * Kontrolliert Kunden nicht wirklich, gibt ergebnisListe.
     */
    public List<Medium> getAusgelieheneMedienFuer(Kunde kunde)
    {
        List<Medium> ergebnisListe = new ArrayList<Medium>();
        ergebnisListe.add(MEDIUM);
        return ergebnisListe;
    }

    /**
     * returned einfach ENTLEIHER ohne wirklich zu gucken wer das medium hat.
     */
    @Override
    public Kunde getEntleiherFuer(Medium medium)
    {
        return ENTLEIHER;
    }

    @Override
    /**
     * returned einfach einen dummy
     */
    public Verleihkarte getVerleihkarteFuer(Medium medium)
    {
        return VERLEIHKARTE;
    }

    @Override
    /**
     * gibt eine liste zurÃ¼ck. wird grade neu erstellt und eine verleihkarte wird erstellt
     */
    public List<Verleihkarte> getVerleihkarten()
    {
        List<Verleihkarte> ergebnisListe = new ArrayList<Verleihkarte>();
        ergebnisListe.add(VERLEIHKARTE);
        return ergebnisListe;
    }

    @Override
    /**
     * gibt einen RANDOM boolean zurÃ¼ck.
     */
    public boolean istVerliehen(Medium medium)
    {
        return RANDOM.nextBoolean();
    }

    @Override
    /**
     * macht nichts
     */
    public void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
    {
    }

    @Override
    /**
     * gibt einen RANDOM boolean zurÃ¼ck.
     */
    public boolean sindAlleNichtVerliehen(List<Medium> medien)
    {
        return RANDOM.nextBoolean();
    }

    @Override
    /**
     * gibt einen RANDOM boolean zurÃ¼ck.
     */
    public boolean sindAlleVerliehen(List<Medium> medien)
    {
        return RANDOM.nextBoolean();
    }

    @Override
    /**
     *macht nichts.
     */
    public void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
    {
    }

    @Override
    /**
     * guckt ob der Kunde im Bestand ist.
     */
    public boolean kundeImBestand(Kunde kunde)
    {
        return ENTLEIHER.equals(kunde);
    }

    @Override
    /**
    * guckt ob das medium im Bestand ist.
    */
    public boolean mediumImBestand(Medium medium)
    {
        return MEDIUM.equals(medium);
    }

    @Override
    /**
     * methode ist einfach richtig, ganz einfach.punkt
     */
    public boolean medienImBestand(List<Medium> medien)
    {
        boolean result = true;
        for (Medium medium : medien)
        {
            if (!mediumImBestand(medium))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    /**
     * eine neue liste wird erstellt und eine Verleihkarte wird hinzugefÃ¼gt,
     *  anstatt wirklich die Verleihkarten des
     * Kunden zu Ã¼berprÃ¼fen.
     */
    public List<Verleihkarte> getVerleihkartenFuer(Kunde kunde)
    {
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        result.add(VERLEIHKARTE);
        return result;
    }

    @Override
    /**
     * returned false.
     */
    public boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien)
    {
        return false;
    }
}
