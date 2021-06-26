package mediathek.werkzeuge;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mediathek.medien.Medium;
import mediathek.services.MedienbestandService;
import mediathek.services.ServiceObserver;

/**
 * Ein MedienverwaltungMedienauflisterWerkzeug ist ein Werkzeug zum Auflisten
 * von Medien.
 * 
 * Das Werkzeug ist beobachtbar und informiert darÃ¼ber, wenn sich die Selektion
 * in der Medienliste Ã¤ndert.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class MedienverwaltungMedienauflisterWerkzeug extends ObservableSubWerkzeug
{
    private MedienverwaltungMedienauflisterUI _ui;
    private MedienbestandService _medienbestand;

    /**
     * Initialisiert ein neues MedienverwaltungMedienauflisterWerkzeug. Es wird
     * die BenutzungsoberflÃ¤che zum Darstellen der Medien erzeugt.
     * 
     * @param medienbestand Der Medienbestand.
     * 
     * @require medienbestand != null
     */
    public MedienverwaltungMedienauflisterWerkzeug(
            MedienbestandService medienbestand)
    {
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";

        _medienbestand = medienbestand;

        // UI wird erzeugt.
        _ui = new MedienverwaltungMedienauflisterUI();

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
     * Holt die Medieninformationen aus dem Service und setzt sie in der UI.
     */
    private void setzeAnzuzeigendeMedien()
    {
        _ui.getMedienAuflisterTableModel()
                .setMedien(_medienbestand.getMedien());
    }

    /**
     * Registriert die Aktion, die ausgefÃ¼hrt wird, wenn ein Medium ausgewÃ¤hlt
     * wird.
     */
    private void registriereMedienAnzeigenAktion()
    {
        _ui.getMedienAuflisterTable().getSelectionModel()
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
            public void informiereUeberAenderung()
            {
                // Wenn der Medienbestand-Service eine Ã„nderung mitteilt, dann
                // wird
                // die angezeigte Liste aller Medien aktualisiert:
                setzeAnzuzeigendeMedien();
            }
        };
        _medienbestand.registriereBeobachter(beobachter);
    }

    /**
     * Gibt das vom Benutzer selektierte Medium zurÃ¼ck.
     * 
     * @return Das vom Benutzer selektierte Medium oder null, wenn nichts
     *         selektiert ist.
     * 
     */
    public Medium getSelectedMedium()
    {
        Medium result = null;

        int selectedRow = _ui.getMedienAuflisterTable().getSelectedRow();
        MedienverwaltungMedienTableModel medienverwaltungMedienTableModel = _ui
                .getMedienAuflisterTableModel();
        if (medienverwaltungMedienTableModel.zeileExistiert(selectedRow))
        {
            result = medienverwaltungMedienTableModel
                    .getMediumFuerZeile(selectedRow);
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
