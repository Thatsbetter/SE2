package mediathek.werkzeuge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mediathek.fachwerte.Datum;
import mediathek.materialien.Verleihkarte;
import mediathek.medien.Medium;
import mediathek.services.ProtokollierException;
import mediathek.services.ServiceObserver;
import mediathek.services.VerleihService;

/**
 * Ein RueckgabeWerkzeug stellt die FunktionalitÃ¤t der RÃ¼cknahme fÃ¼r die
 * BenutzungsoberflÃ¤che bereit. Die UI wird durch die RueckgabeUI gestaltet.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class RueckgabeWerkzeug
{
    /**
     * Die UI-Komponente der Rueckgabe.
     */
    private final RueckgabeUI _rueckgabeUI;

    /**
     * Der Service zum Ausleihen von Medien.
     */
    private final VerleihService _verleihService;

    /**
     * Initialisiert ein neues RueckgabeWerkzeug. Es wird die
     * BenutzungsoberflÃ¤che mit den RÃ¼ckgabeaktionen erzeugt, Beobachter an den
     * Services registriert und die anzuzeigenden Materialien gesetzt.
     * 
     * @param verleihService Der zu benutzenden VerleihService.
     * 
     * @require verleihService != null
     */
    public RueckgabeWerkzeug(VerleihService verleihService)

    {
        assert verleihService != null : "Vorbedingung verletzt: verleihService != null";
        _verleihService = verleihService;

        // UI wird erzeugt.
        _rueckgabeUI = new RueckgabeUI();

        // Die Beobachter werden erzeugt und an den Services registriert.
        registriereServiceBeobachter();

        // Die RÃ¼ckgabe-Aktionen werden erzeugt und an den UI-Widgets
        // registriert.
        registriereUIAktionen();

        // Die anzuzeigenden Materialien werden an der UI gesetzt.
        setzeAnzuzeigendeMaterialien();
    }

    /**
     * Setzt die Materialien an der UI, die angezeigt werden sollen.
     */
    private void setzeAnzuzeigendeMaterialien()
    {
        setzeAnzuzeigendeVerleihkarten();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgefÃ¼hrt werden.
     */
    private void registriereUIAktionen()
    {
        registriereRuecknahmeAktion();
        registriereVerleihkartenAnzeigenAktion();
    }

    /**
     * Registriert die Aktion die ausgefÃ¼hrt wird, wenn Verleihkarten selektiert
     * werden.
     */
    private void registriereVerleihkartenAnzeigenAktion()
    {
        _rueckgabeUI.getVerleihkartenAuflisterTable()
            .getSelectionModel()
            .addListSelectionListener(new ListSelectionListener()
            {

                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    zeigeAusgewaehlteVerleihkarten();
                    aktualisiereRuecknahmeButton();
                }
            });
    }

    /**
     * Registriert die RÃ¼cknahmeaktion.
     */
    private void registriereRuecknahmeAktion()
    {
        _rueckgabeUI.getRuecknahmeButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    nimmAusgewaehlteMedienZurueck();
                }
            });
    }

    /**
     * Registriert die Beobachter an den Services.
     */
    private void registriereServiceBeobachter()
    {
        _verleihService.registriereBeobachter(new ServiceObserver()
        {
            @Override
            public void informiereUeberAenderung()
            {
                setzeAnzuzeigendeVerleihkarten();
            }

        });
    }

    /**
     * Holt alle Verleihkarten vom Verleihservice und setzt diese an der UI.
     */
    private void setzeAnzuzeigendeVerleihkarten()
    {
        List<Verleihkarte> verleihkarten = _verleihService.getVerleihkarten();
        _rueckgabeUI.getVerleihkartenAuflisterTableModel()
            .setVerleihkarten(verleihkarten);
    }

    /**
     * Gibt die vom Benutzer ausgewÃ¤hlten Medien zurÃ¼ck.
     */
    private void nimmAusgewaehlteMedienZurueck()
    {
        List<Verleihkarte> verleihkarten = getSelectedVerleihkarten();
        List<Medium> medien = new ArrayList<Medium>();
        for (Verleihkarte verleihkarte : verleihkarten)
        {
            medien.add(verleihkarte.getMedium());
        }
        try
        {
            _verleihService.nimmZurueck(medien, Datum.heute());
        }
        catch (ProtokollierException e)
        {
            JOptionPane.showMessageDialog(null, "ProtokollierException error",
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Zeigt die Details der ausgewÃ¤hlten Verleihkarten an.
     */
    private void zeigeAusgewaehlteVerleihkarten()
    {
        List<Verleihkarte> selektierteVerleihkarten = getSelectedVerleihkarten();
        JTextArea _ausgewaehlteVerleihkartenTextArea = _rueckgabeUI
            .getVerleihkartenAnzeigerTextArea();
        _ausgewaehlteVerleihkartenTextArea.setText("");
        for (Verleihkarte verleihkarte : selektierteVerleihkarten)
        {
            _ausgewaehlteVerleihkartenTextArea
                .append(verleihkarte.getFormatiertenString());
            _ausgewaehlteVerleihkartenTextArea.append("--------------- \n");
        }
    }

    /**
     * Graut den RuecknahmeButton aus, wenn keine Verleihkarten selektiert sind.
     */
    private void aktualisiereRuecknahmeButton()
    {
        _rueckgabeUI.getRuecknahmeButton()
            .setEnabled(!getSelectedVerleihkarten().isEmpty());
    }

    /**
     * Gibt das Panel, dass die UI-Koponente darstellt zurÃ¼ck.
     * 
     * @return Das Panel, dass die UI-Koponente darstellt.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _rueckgabeUI.getUIPanel();
    }

    /**
     * Liefert die vom Benutzer selektierten Verleihkarten.
     * 
     * @return Die selektierten Verleihkarten.
     * 
     * @ensure result != null
     */
    private List<Verleihkarte> getSelectedVerleihkarten()
    {
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        int[] selectedRows = _rueckgabeUI.getVerleihkartenAuflisterTable()
            .getSelectedRows();
        for (int zeile : selectedRows)
        {
            Verleihkarte verleihkarte = _rueckgabeUI
                .getVerleihkartenAuflisterTableModel()
                .getVerleihkartenFuerZeile(zeile);
            result.add(verleihkarte);
        }
        return result;
    }
}
