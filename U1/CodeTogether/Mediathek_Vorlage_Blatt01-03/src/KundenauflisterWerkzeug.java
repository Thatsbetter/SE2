import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Ein KundenauflisterWerkzeug ist ein Werkzeug zum auflisten von Kunden.
 * 
 * Das Werkzeug ist beobachtbar und informiert Ã¼ber Ã„nderungen des selektieren
 * Kunden.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class KundenauflisterWerkzeug extends ObservableSubWerkzeug
{
    private KundenauflisterUI _ui;
    private KundenstammService _kundenstamm;

    /**
     * Initialisiert ein neues KundenauflisterWerkzeug. Es wird die
     * BenutzungsoberflÃ¤che mit zum Darstellen der Kunden erzeugt.
     * 
     * @param kundenstamm Der Kundenstamm.
     * 
     * @require kundenstamm != null
     */
    public KundenauflisterWerkzeug(KundenstammService kundenstamm)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";

        _kundenstamm = kundenstamm;

        // UI wird erzeugt.
        _ui = new KundenauflisterUI();

        // Die Ausleihaktionen werden erzeugt und an der UI registriert.
        registriereUIAktionen();

        // Die anzuzeigenden Materialien werden in den UI-Widgets gesetzt.
        setzeAnzuzeigendeMaterialien();
    }

    /**
     * Registriert die Aktionen, die bei bestimmten UI-Events ausgefÃ¼hrt werden.
     */
    private void registriereUIAktionen()
    {
        registriereKundenAnzeigenAktion();
    }

    /**
     * Holt die anzuzeigenden Kunden aus den Services und setzt diese bei dem
     * TableModel.
     */
    private void setzeAnzuzeigendeMaterialien()
    {
        List<Kunde> kunden = _kundenstamm.getKunden();
        _ui.getKundenAuflisterTableModel()
            .setKunden(kunden);
    }

    /**
     * Registiert die Aktion, die ausgefÃ¼hrt wird, wenn ein Kunde ausgewÃ¤hlt
     * wird.
     */
    private void registriereKundenAnzeigenAktion()
    {
        _ui.getKundenTable()
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
     * Gibt den vom Benutzer selektierten Kunden zurÃ¼ck. Falls kein Kunde
     * selektiert ist, gebe null zurÃ¼ck.
     * 
     * @return Den aktuell selektierten Kunden, oder null wenn keine Selektion
     *         existiert.
     */
    public Kunde getSelectedKunde()
    {
        Kunde result = null;
        int selectedRow = _ui.getKundenTable()
            .getSelectedRow();
        KundenTableModel kundenTableModel = _ui.getKundenAuflisterTableModel();
        if (kundenTableModel.zeileExistiert(selectedRow))
        {
            result = kundenTableModel.getKundeFuerZeile(selectedRow);
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
