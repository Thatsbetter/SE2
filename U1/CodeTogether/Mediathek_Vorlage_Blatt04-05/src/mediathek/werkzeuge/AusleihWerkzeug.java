package mediathek.werkzeuge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mediathek.fachwerte.Datum;
import mediathek.materialien.Kunde;
import mediathek.medien.Medium;
import mediathek.services.KundenstammService;
import mediathek.services.MedienbestandService;
import mediathek.services.ProtokollierException;
import mediathek.services.ServiceObserver;
import mediathek.services.VerleihService;

/**
 * Ein AusleihWerkzeug stellt die FunktionalitÃ¤t der Ausleihe fÃ¼r die
 * BenutzungsoberflÃ¤che bereit. Die UI wird durch die {@link AusleiheUI} gestaltet.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class AusleihWerkzeug
{

    /**
     * Die UI-Komponente der Ausleihe.
     */
    private final AusleiheUI _ausleiheUI;

    /**
     * Der Service zum Ausleihen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Das Sub-Werkzeug zum darstellen und selektieren der Kunden.
     */
    private KundenauflisterWerkzeug _kundenAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum darstellen und selektieren der Medien.
     */
    private AusleiheMedienauflisterWerkzeug _medienAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum anzeigen der Details der selektieten Medien.
     */
    private MedienDetailAnzeigerWerkzeug _medienDetailAnzeigerWerkzeug;

    /**
     * Das Sub-Werkzeug zum anzeigen der Details des selektieten Kunden.
     */
    private KundenDetailAnzeigerWerkzeug _kundenDetailAnzeigerWerkzeug;

    /**
     * Initialisiert ein neues AusleihWerkzeug. Es wird die BenutzungsoberflÃ¤che
     * mit den Ausleihaktionen erzeugt, Beobachter an den Services registriert
     * und die anzuzeigenden Materialien gesetzt.
     * 
     * @param medienbestand Der Medienbestand.
     * @param kundenstamm Der Kundenstamm.
     * @param verleihService Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require kundenstamm != null
     * @require verleihService != null
     */
    public AusleihWerkzeug(MedienbestandService medienbestand,
            KundenstammService kundenstamm, VerleihService verleihService)

    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _verleihService = verleihService;

        // Subwerkzeuge erstellen
        _kundenAuflisterWerkzeug = new KundenauflisterWerkzeug(kundenstamm);
        _medienAuflisterWerkzeug = new AusleiheMedienauflisterWerkzeug(
                medienbestand, verleihService);
        _medienDetailAnzeigerWerkzeug = new MedienDetailAnzeigerWerkzeug();
        _kundenDetailAnzeigerWerkzeug = new KundenDetailAnzeigerWerkzeug();

        // UI erzeugen
        _ausleiheUI = new AusleiheUI(_kundenAuflisterWerkzeug.getUIPanel(),
                _medienAuflisterWerkzeug.getUIPanel(),
                _kundenDetailAnzeigerWerkzeug.getUIPanel(),
                _medienDetailAnzeigerWerkzeug.getUIPanel());

        // Beobachter erzeugen und an den Services registrieren
        registriereServiceBeobachter();

        // Beobachter erzeugen und an den Subwerkzeugen registrieren
        registriereSubWerkzeugBeobachter();

        // Die Ausleihaktionen erzeugen und an der UI registrieren
        registriereUIAktionen();
    }

    /**
     * Registriert die Aktionen, die bei benachrichtigungen der Services
     * ausgefÃ¼hrt werden.
     */
    private void registriereServiceBeobachter()
    {
        registriereAusleihButtonAktualisierenAktion();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten Ã„nderungen in Subwerkzeugen
     * ausgefÃ¼hrt werden.
     */
    private void registriereSubWerkzeugBeobachter()
    {
        registriereKundenAnzeigenAktion();
        registriereMedienAnzeigenAktion();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgefÃ¼hrt werden.
     */
    private void registriereUIAktionen()
    {
        registriereAusleihAktion();
    }

    /**
     * Registriert die Aktion zur Aktualisierung des Ausleihbuttons, wenn eine
     * Benachrichtigung vom Verleihservice auftaucht.
     */
    private void registriereAusleihButtonAktualisierenAktion()
    {
        _verleihService.registriereBeobachter(new ServiceObserver()
        {

            @Override
            public void informiereUeberAenderung()
            {
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * Registriert die Aktion, die ausgefÃ¼hrt wird, wenn auf den Ausleih-Button
     * gedrÃ¼ckt wird.
     */
    private void registriereAusleihAktion()
    {
        _ausleiheUI.getAusleihButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (istAusleihenMoeglich())
                    {
                        leiheAusgewaehlteMedienAus();
                    }
                }
            });
    }

    /**
     * Registiert die Aktion, die ausgefÃ¼hrt wird, wenn ein Kunde ausgewÃ¤hlt
     * wird.
     */
    private void registriereKundenAnzeigenAktion()
    {
        _kundenAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
        {
            @Override
            public void informiereUeberAenderung()
            {
                zeigeAusgewaehltenKunden();
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * Registiert die Aktion, die ausgefÃ¼hrt wird, wenn ein Medium ausgewÃ¤hlt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _medienAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
        {

            @Override
            public void informiereUeberAenderung()
            {
                zeigeAusgewaehlteMedien();
                aktualisiereAusleihButton();
            }
        });
    }

    /**
     * ÃœberprÃ¼ft, ob die selektierten Medien ausgeleihen werden kÃ¶nnen und ob
     * ein Kunde selektiert ist, an den ausgeliehen werden kÃ¶nnte.
     * 
     * @return true, wenn ausleihen mÃ¶glich ist, sonst false.
     */
    private boolean istAusleihenMoeglich()
    {
        List<Medium> medien = _medienAuflisterWerkzeug.getSelectedMedien();
        Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        boolean ausleiheMoeglich = (kunde != null) && !medien.isEmpty()
                && _verleihService.sindAlleNichtVerliehen(medien);
        return ausleiheMoeglich;
    }

    /**
     * Leiht die ausgewÃ¤hlten Medien aus. Diese Methode wird Ã¼ber einen Listener
     * angestoÃŸen, der reagiert, wenn der Benutzer den Ausleihen-Button drÃ¼ckt.
     */
    private void leiheAusgewaehlteMedienAus()
    {
        List<Medium> selectedMedien = _medienAuflisterWerkzeug
            .getSelectedMedien();
        Kunde selectedKunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        Datum heute = Datum.heute();

        try
        {
            _verleihService.verleiheAn(selectedKunde, selectedMedien, heute);
        }
        catch (ProtokollierException e)
        {
            JOptionPane.showMessageDialog(null, "ProtokollierException error",
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Zeigt die Details der ausgewÃ¤hlten Medien.
     */
    private void zeigeAusgewaehlteMedien()
    {
        List<Medium> selectedMedien = _medienAuflisterWerkzeug
            .getSelectedMedien();
        _medienDetailAnzeigerWerkzeug.setMedien(selectedMedien);
    }

    /**
     * Zeigt die Details des ausgewÃ¤hlten Kunden (rechts im Fenster)
     */
    private void zeigeAusgewaehltenKunden()
    {
        Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        _kundenDetailAnzeigerWerkzeug.setKunde(kunde);
    }

    /**
     * Setzt den Ausleihbutton auf benutzbar (enabled) falls die gerade
     * selektierten Medien alle ausgeliehen werden kÃ¶nnen und ein Kunde
     * ausgewÃ¤hlt ist.
     * 
     * Wenn keine Medien selektiert sind oder wenn mindestes eines der
     * selektierten Medien bereits ausgeliehen ist oder wenn kein Kunde
     * ausgewÃ¤hlt ist, wird der Button ausgegraut.
     */
    private void aktualisiereAusleihButton()
    {
        boolean istAusleihenMoeglich = istAusleihenMoeglich();
        _ausleiheUI.getAusleihButton()
            .setEnabled(istAusleihenMoeglich);
    }

    /**
     * Gibt das Panel, dass die UI-Komponente darstellt zurÃ¼ck.
     * 
     * @return Das Panel, dass die UI-Komponente darstellt.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ausleiheUI.getUIPanel();
    }
}
