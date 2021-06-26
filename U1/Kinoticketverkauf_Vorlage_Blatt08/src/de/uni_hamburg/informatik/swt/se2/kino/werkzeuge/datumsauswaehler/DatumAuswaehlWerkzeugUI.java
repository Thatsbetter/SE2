package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.datumsauswaehler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Die GUI des {@link DatumAuswaehlWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class DatumAuswaehlWerkzeugUI
{
    private JPanel _hauptPanel;
    private JButton _weiterButton;
    private JButton _zurueckButton;
    private JLabel _datumLabel;

    /**
     * Initialisiert die BenutzeroberflÃ¤che.
     * 
     * @param startDatumString der String, der zu Anfang als ausgewÃ¤hltes Datum
     *            angezeigt wird.
     */
    public DatumAuswaehlWerkzeugUI(String startDatumString)
    {
        _hauptPanel = erstellePanel(startDatumString);
    }

    /**
     * Erstellt das Panel.
     */
    private JPanel erstellePanel(String startDatumString)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        _datumLabel = new JLabel(startDatumString, SwingConstants.CENTER);

        GridBagConstraints constraints = new GridBagConstraints(0, 0, 2, 1,
                1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 0, 2, 0), 0, 0);
        panel.add(_datumLabel, constraints);

        Icon zurueckIcon = new ImageIcon("images/go-previous.png");
        _zurueckButton = new JButton(zurueckIcon);

        constraints = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 5), 0, 0);
        panel.add(_zurueckButton, constraints);

        Icon weiterIcon = new ImageIcon("images/go-next.png");
        _weiterButton = new JButton(weiterIcon);

        constraints = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 5, 2, 0), 0, 0);
        panel.add(_weiterButton, constraints);

        return panel;
    }

    /**
     * Gibt den Button fÃ¼r die Auswahl des vorherigen Tages zurÃ¼ck.
     */
    public JButton getZurueckButton()
    {
        return _zurueckButton;
    }

    /**
     * Gibt den Button fÃ¼r die Auswahl des nachfolgenden Tages zurÃ¼ck.
     */
    public JButton getWeiterButton()
    {
        return _weiterButton;
    }

    /**
     * Gibt das Label zurÃ¼ck, in dem das derzeit ausgewÃ¤hlte Datum angezeigt
     * wird.
     */
    public JLabel getDatumLabel()
    {
        return _datumLabel;
    }

    /**
     * Gibt das Panel zurÃ¼ck, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

}
