package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.vormerken;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.SubWerkzeugObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundenauflister.KundenauflisterWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.kundendetailanzeiger.KundenDetailAnzeigerWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.mediendetailanzeiger.MedienDetailAnzeigerWerkzeug;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister.VormerkMedienauflisterWerkzeug;

/**
 * Ein VormerkWerkzeug stellt die FunktionalitÃ¤t des Vormerkens fÃ¼r die
 * BenutzungsoberflÃ¤che bereit. Die UI wird durch die VormerkUI gestaltet.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class VormerkWerkzeug
{

    /**
     * Die UI-Komponente der Ausleihe.
     */
    private final VormerkUI _vormerkUI;

    /**
     * Der Service zum Ausleihen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Das Sub-Werkzeug zum Darstellen und Selektieren der Kunden.
     */
    private KundenauflisterWerkzeug _kundenAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum Darstellen und Selektieren der Medien.
     */
    private VormerkMedienauflisterWerkzeug _medienAuflisterWerkzeug;

    /**
     * Das Sub-Werkzeug zum Anzeigen der Details der selektieten Medien.
     */
    private MedienDetailAnzeigerWerkzeug _medienDetailAnzeigerWerkzeug;

    /**
     * Das Sub-Werkzeug zum Anzeigen der Details des selektieten Kunden.
     */
    private KundenDetailAnzeigerWerkzeug _kundenDetailAnzeigerWerkzeug;

    /**
     * Initialisiert ein neues VormerkWerkzeug. Es wird die BenutzungsoberflÃ¤che
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
    public VormerkWerkzeug(MedienbestandService medienbestand,
            KundenstammService kundenstamm, VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _verleihService = verleihService;

        // Subwerkzeuge erstellen
        _kundenAuflisterWerkzeug = new KundenauflisterWerkzeug(kundenstamm);
        _medienAuflisterWerkzeug = new VormerkMedienauflisterWerkzeug(
                medienbestand, verleihService);
        _medienDetailAnzeigerWerkzeug = new MedienDetailAnzeigerWerkzeug();
        _kundenDetailAnzeigerWerkzeug = new KundenDetailAnzeigerWerkzeug();

        // UI wird erzeugt.
        _vormerkUI = new VormerkUI(_kundenAuflisterWerkzeug.getUIPanel(),
                _medienAuflisterWerkzeug.getUIPanel(),
                _kundenDetailAnzeigerWerkzeug.getUIPanel(),
                _medienDetailAnzeigerWerkzeug.getUIPanel());

        // Beobachter erzeugen und an den Services registrieren
        registriereServiceBeobachter();

        // Beobachter erzeugen und an den Subwerkzeugen registrieren
        registriereSubWerkzeugBeobachter();

        // Die Vormerkaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();
    }

    /**
     * Registriert die Aktionen, die bei benachrichtigungen der Services
     * ausgefÃ¼hrt werden.
     */
    private void registriereServiceBeobachter()
    {
        registriereVormerkButtonAktualisierenAktion();
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
        registriereVormerkAktion();
    }

    /**
     * Registriert die Aktion zur Aktualisierung des Vormerkbuttons, wenn eine
     * Benachrichtigung vom Verleihservice auftaucht.
     */
    private void registriereVormerkButtonAktualisierenAktion()
    {
        _verleihService.registriereBeobachter(new ServiceObserver()
        {

            @Override
            public void reagiereAufAenderung()
            {
                aktualisiereVormerkButton();
            }
        });
    }

    /**
     * Registriert die Aktion, die ausgefÃ¼hrt wird, wenn auf den Vormerk-Button
     * gedrÃ¼ckt wird.
     */
    private void registriereVormerkAktion()
    {
        _vormerkUI.getVormerkenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    merkeAusgewaehlteMedienVor();
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
            public void reagiereAufAenderung()
            {
                zeigeAusgewaehltenKunden();
                aktualisiereVormerkButton();
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
            public void reagiereAufAenderung()
            {
                zeigeAusgewaehlteMedien();
                aktualisiereVormerkButton();
            }
        });
    }

    /**
     * ÃœberprÃ¼ft, ob ein Kunde selektiert ist und ob selektierte Medien fÃ¼r
     * diesen Kunden vorgemerkt werden kÃ¶nnen.
     * 
     * @return true, wenn vormerken mÃ¶glich ist, sonst false.
     */
    private boolean istVormerkenMoeglich()
    {
        List<Medium> medien = _medienAuflisterWerkzeug.getSelectedMedien();
        Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        // TODO  fÃ¼r Aufgabenblatt 6 (nicht lÃ¶schen): PrÃ¼fung muss noch eingebaut
        // werden. Ist dies korrekt imlpementiert, wird der Vormerk-Button gemÃ¤ÃŸ
        // der Anforderungen a), b), c) und e) aktiviert.

        boolean vormerkenMoeglich = (kunde != null) && !medien.isEmpty();

        for (Medium m : medien)
        {
            if ((kunde == null) || medien.isEmpty()
                    || !_verleihService.istVormerkenMoeglich(m)
                    || _verleihService.hatVormerkerKarte(m)
                            && _verleihService.getVormerkerKarteFuer(m)
                                .istVormerker(kunde))
            {
                vormerkenMoeglich = false;
            }
        }

        return vormerkenMoeglich;
    }

    /**
     * Merkt die ausgewÃ¤hlten Medien fÃ¼r einen Kunden vor. Diese Methode wird
     * Ã¼ber einen Listener angestoÃŸen, der reagiert, wenn der Benutzer den
     * VormerkButton drÃ¼ckt.
     */
    private void merkeAusgewaehlteMedienVor()
    {

        List<Medium> selectedMedien = _medienAuflisterWerkzeug
            .getSelectedMedien();
        Kunde selectedKunde = _kundenAuflisterWerkzeug.getSelectedKunde();
        // TODO fÃ¼r Aufgabenblatt 6 (nicht lÃ¶schen): Vormerken einbauen
        for (Medium m : selectedMedien)
        {
            _verleihService.merkeVor(selectedKunde, m);
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
    private void aktualisiereVormerkButton()
    {
        boolean istVormerkenMoeglich = istVormerkenMoeglich();
        _vormerkUI.getVormerkenButton()
            .setEnabled(istVormerkenMoeglich);
    }

    /**
     * Gibt das Panel, dass die UI-Komponente darstellt zurÃ¼ck.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _vormerkUI.getUIPanel();
    }
}
