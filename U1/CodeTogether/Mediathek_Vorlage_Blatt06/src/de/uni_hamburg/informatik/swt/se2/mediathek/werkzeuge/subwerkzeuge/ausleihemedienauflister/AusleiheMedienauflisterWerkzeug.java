package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.ausleihemedienauflister;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.ObservableSubWerkzeug;

/**
 * Ein AusleiheMedienauflisterWerkzeug ist ein Werkzeug zum auflisten von Medien
 * mit ihren Verleihinformationen.
 * 
 * Das Werkzeug ist beobachtbar und informiert darÃ¼ber, wenn sich die Selektion
 * in der Medienliste Ã¤ndert.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class AusleiheMedienauflisterWerkzeug extends ObservableSubWerkzeug
{
    private AusleiheMedienauflisterUI _ui;
    private MedienbestandService _medienbestand;
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues AusleiheMedienauflisterWerkzeug. Es wird die
     * BenutzungsoberflÃ¤che zum Darstellen der Medien erzeugt.
     * 
     * @param medienbestand Der Medienbestand.
     * @param verleihService Der Verleih-Service.
     * 
     * @require medienbestand != null
     * @require verleihService != null
     */
    public AusleiheMedienauflisterWerkzeug(MedienbestandService medienbestand,
            VerleihService verleihService)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

        _medienbestand = medienbestand;
        _verleihService = verleihService;

        // UI wird erzeugt.
        _ui = new AusleiheMedienauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die Beobachter werden erzeugt und an den Servies registriert.
        registriereServiceBeobachter();

        // Die anzuzeigenden Materialien werden in den UI-Widgets gesetzt.
        setzeAnzuzeigendeMedien();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgefÃ¼hrt werden.
     */
    private void registriereUIAktionen()
    {
        registriereMedienAnzeigenAktion();
    }

    /**
     * Holt und setzt die Medieninformationen.
     */
    private void setzeAnzuzeigendeMedien()
    {
        List<Medium> medienListe = _medienbestand.getMedien();
        List<AusleiheMedienFormatierer> medienFormatierer = new ArrayList<AusleiheMedienFormatierer>();
        for (Medium medium : medienListe)
        {
            boolean istVerliehen = _verleihService.istVerliehen(medium);
            // TODO OK? fÃ¼r Aufgabenblatt 6 (nicht lÃ¶schen): Falls ein Vormerker fÃ¼r
            // ein Medium existiert, muss dieser hier ermittelt werden.
            // Ist dies korrekt implementiert, erscheint in der Ausleiheansicht
            // der Name des Vormerkers, an den ein Medium ausgeliehen werden
            // darf, gemÃ¤ÃŸ Anforderung d).

            Kunde ersterVormerker = null;

            if (_verleihService.hatVormerkerKarte(medium))
            {
                ersterVormerker = _verleihService.getVormerkerKarteFuer(medium)
                    .getVormerker(0);
            }

            medienFormatierer.add(new AusleiheMedienFormatierer(medium,
                    istVerliehen, ersterVormerker));
        }
        _ui.getMedienAuflisterTableModel()
            .setMedien(medienFormatierer);
    }

    /**
     * Registiert die Aktion, die ausgefÃ¼hrt wird, wenn ein Medium ausgewÃ¤hlt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _ui.getMedienAuflisterTable()
            .getSelectionModel()
            .addListSelectionListener(new ListSelectionListener()
            {
                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    informiereUeberAenderung();
                }
            });
    }

    /**
     * Registriert die Beobacheter fÃ¼r die Services.
     */
    private void registriereServiceBeobachter()
    {
        ServiceObserver beobachter = new ServiceObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                // Wenn ein Service eine Ã„nderung mitteilt, dann wird
                // die angezeigte Liste aller Medien aktualisiert:
                setzeAnzuzeigendeMedien();
            }
        };
        _medienbestand.registriereBeobachter(beobachter);
        _verleihService.registriereBeobachter(beobachter);
    }

    /**
     * Gibt die Liste der vom Benutzer selektierten Medien zurÃ¼ck.
     * 
     * @return Die Liste der vom Benutzer selektierten Medien.
     * 
     * @ensure result != null
     */
    public List<Medium> getSelectedMedien()
    {
        List<Medium> result = new ArrayList<Medium>();
        int[] selectedRows = _ui.getMedienAuflisterTable()
            .getSelectedRows();
        AusleiheMedienTableModel ausleiheMedienTableModel = _ui
            .getMedienAuflisterTableModel();
        for (int zeile : selectedRows)
        {
            if (ausleiheMedienTableModel.zeileExistiert(zeile))
            {
                Medium medium = ausleiheMedienTableModel
                    .getMediumFuerZeile(zeile);
                result.add(medium);
            }
        }
        return result;
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurÃ¼ck.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
}
