package mediathek.startup;

import java.io.File;

import javax.swing.SwingUtilities;

import mediathek.services.DateiLeseException;
import mediathek.services.DatenEinleser;
import mediathek.services.KundenstammService;
import mediathek.services.KundenstammServiceImpl;
import mediathek.services.MedienbestandService;
import mediathek.services.MedienbestandServiceImpl;
import mediathek.services.VerleihService;
import mediathek.services.VerleihServiceImpl;
import mediathek.werkzeuge.MediathekWerkzeug;

/**
 * Startet die Hauptanwendung mit grafischer OberflÃ¤che.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class StartUpMediathek_Blatt_04_05
{

    private static final File KUNDEN_DATEI = new File(
            "./bestand/kundenstamm.txt");
    private static final File MEDIEN_DATEI = new File(
            "./bestand/medienbestand.txt");

    private static KundenstammService _kundenstamm;
    private static MedienbestandService _medienbestand;
    private static VerleihService _verleihService;

    /**
     * Main-Methode, mit der die Anwendung gestartet wird.
     */
    public static void main(String[] args)
    {
        pruefeObAssertionsAktiviert();
        erstelleServices();

        final MediathekWerkzeug mediathekWerkzeug = new MediathekWerkzeug(
                _medienbestand, _kundenstamm, _verleihService);

        // Dies ist die korrekte Art eine Swing-Anwendnung zu starten.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                mediathekWerkzeug.zeigeFenster();
            }
        });

    }

    /**
     * Erstellt die Services und lÃ¤dt die Daten.
     */
    private static void erstelleServices()
    {
        try
        {
            DatenEinleser datenEinleser = new DatenEinleser(MEDIEN_DATEI,
                    KUNDEN_DATEI);
            datenEinleser.leseDaten();
            _medienbestand = new MedienbestandServiceImpl(
                    datenEinleser.getMedien());
            _kundenstamm = new KundenstammServiceImpl(
                    datenEinleser.getKunden());
            _verleihService = new VerleihServiceImpl(_kundenstamm,
                    _medienbestand, datenEinleser.getVerleihkarten());
        }
        catch (DateiLeseException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * prueft, ob -ea als Default VM Argument hinterlegt ist
     */
    private static void pruefeObAssertionsAktiviert()
    {

        boolean sindAssertionsAktiv = false;
        assert sindAssertionsAktiv = true;
        if (!sindAssertionsAktiv)
        {
            System.err.println("Assertions sind nicht eingeschaltet!\n"
                    + " Schalte sie ein, indem du unter Window -> Preferences-> Java -> installed JREs das genutzte JRE anwÃ¤hltst, auf Edit klickst und bei Default VM Arguments \"-ea\" eintrÃ¤gst.");
        }
    }

}
