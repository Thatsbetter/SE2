package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.hauptwerkzeug;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * Das Hauptfenster fÃ¼r die Anwendung
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class MediathekUI
{
    // Die Bezeichner der Buttons, werden auch als SchlÃ¼ssel fÃ¼r das CardLayout
    // verwendet.
    public static final String AUSLEIHE = "Ausleihe-Ansicht";
    public static final String NAME = "SE2-Mediathek";
    public static final String RUECKGABE = "RÃ¼ckgabe-Ansicht";
    public static final String VORMERKEN = "Vormerk-Ansicht";

    private JButton _ausleiheButton;
    private JPanel _ausleihePanel;
    private JPanel _menuPanel;
    private CardLayout _anzeigeLayout;
    private JPanel _anzeigePanel;

    private JButton _rueckgabeButton;
    private JPanel _rueckgabePanel;
    private JPanel _spacerPanel;
    private JLabel _titelLabel;

    private JButton _vormerkButton;
    private JPanel _vormerkPanel;

    private JFrame _frame;

    /**
     * Initialisiert die OberflÃ¤che der Mediathek
     * 
     * @param ausleihePanel Das Panel der Ausleihe.
     * @param rueckgabePanel Das Panel der Rueckgabe.
     * 
     * @require ausleihePanel != null
     * @require rueckgabePanel != null
     * @require vormerkPanel != null
     */
    public MediathekUI(JPanel ausleihePanel, JPanel rueckgabePanel,
            JPanel vormerkPanel)
    {
        assert ausleihePanel != null : "Vorbedingung verletzt: ausleihePanel != null";
        assert rueckgabePanel != null : "Vorbedingung verletzt: rueckgabePanel != null";
        assert vormerkPanel != null : "Vorbedingung verletzt: vormerkPanel != null";
        _ausleihePanel = ausleihePanel;
        _rueckgabePanel = rueckgabePanel;
        _vormerkPanel = vormerkPanel;
        initGUI();
    }

    /**
     * Erzeugt das Anzeige-Panel, in dem die Panels fÃ¼r die verschiedenen
     * Ansichten (wie beispielsweise das RÃ¼ckgabe-Panel) Ã¼ber ein CardLayout
     * angezeigt und versteckt werden kÃ¶nnen.
     * 
     * FÃ¼gt die Panels fÃ¼r die verschiedenen Ansichten in das Anzeige-Panel ein.
     */
    private void erzeugeAnzeigePanel()
    {
        _anzeigePanel = new JPanel();
        _anzeigeLayout = new CardLayout();
        _anzeigePanel.setLayout(_anzeigeLayout);

        // Beim CardLayout wird jeweils das Widget und ein SchlÃ¼ssel Ã¼bergeben.
        // Beispiel: Widget = _ausleihePanel. SchlÃ¼ssel = AUSLEIHE.
        _anzeigePanel.add(_ausleihePanel, AUSLEIHE);
        _anzeigePanel.add(_rueckgabePanel, RUECKGABE);
        _anzeigePanel.add(_vormerkPanel, VORMERKEN);
        _frame.getContentPane()
            .add(_anzeigePanel, BorderLayout.CENTER);
    }

    /**
     * Erzeugt den Ausleih-Button fÃ¼r das MenÃ¼
     */
    private void erzeugeAusleiheButton()
    {
        _ausleiheButton = new JButton();
        initialisiereMenuButton(_ausleiheButton, AUSLEIHE);
        _ausleiheButton.setSelected(true);
    }

    /**
     * Erzeugt den RÃ¼ckgabe-Button fÃ¼r das MenÃ¼.
     */
    private void erzeugeRueckgabeButton()
    {
        _rueckgabeButton = new JButton();
        initialisiereMenuButton(_rueckgabeButton, RUECKGABE);
    }

    /**
     * Erzeugt den Vormerken-Button fÃ¼r das MenÃ¼
     */
    private void erzeugeVormerkAnsichtButton()
    {
        _vormerkButton = new JButton();
        initialisiereMenuButton(_vormerkButton, VORMERKEN);
    }

    /**
     * Erzeugt das MenÃ¼ mit Ausleih- und RÃ¼ckgabe-Button und Titel.
     */
    private void erzeugeMenuPanel()
    {
        _menuPanel = new JPanel();
        FlowLayout auswahlPanelLayout = new FlowLayout();
        auswahlPanelLayout.setAlignment(FlowLayout.LEFT);
        _menuPanel.setLayout(auswahlPanelLayout);
        _frame.getContentPane()
            .add(_menuPanel, BorderLayout.NORTH);
        _menuPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        erzeugeAusleiheButton();
        erzeugeRueckgabeButton();
        erzeugeVormerkAnsichtButton();
        erzeugeTitel();
    }

    /**
     * Initialisiert einen MenÃ¼-Button.
     * 
     * @param button Der Button.
     * @param buttonText Der Text der auf dem Button stehen soll.
     */
    private void initialisiereMenuButton(JButton button, String buttonText)
    {
        _menuPanel.add(button);
        button.setText(buttonText);
        button.setPreferredSize(new java.awt.Dimension(180, 50));
        button.setFont(UIConstants.BUTTON_FONT);
        button.setBackground(UIConstants.BUTTON_COLOR);
    }

    /**
     * Erzeugt den Titel fÃ¼r das MenÃ¼.
     */
    private void erzeugeTitel()
    {
        _spacerPanel = new JPanel();
        _menuPanel.add(_spacerPanel);
        _spacerPanel.setPreferredSize(new java.awt.Dimension(100, 10));
        _spacerPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        _titelLabel = new JLabel();
        _menuPanel.add(_titelLabel);
        _titelLabel.setText(NAME);
        _titelLabel.setBackground(UIConstants.BACKGROUND_COLOR);
        _titelLabel.setFont(UIConstants.TITLE_FONT);
        _titelLabel.setForeground(UIConstants.TITLE_COLOR);
    }

    /**
     * Initialisiert die GUI, setze das Layout, fÃ¼ge die Widget ein, setze
     * Listener, die auf Benutzeraktionen reagieren.
     */
    private void initGUI()
    {
        _frame = new JFrame(NAME);
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        _frame.getContentPane()
            .setBackground(UIConstants.BACKGROUND_COLOR);
        _frame.setTitle(NAME);
        erzeugeAnzeigePanel();
        erzeugeMenuPanel();
        _frame.pack();
        _frame.setSize(1100, 768);
        zeigeAn(AUSLEIHE);
    }

    /**
     * Aktualisiert das Layout.
     */
    private void aktualisiereLayout()
    {
        _frame.validate();
    }

    /**
     * Zeigt das Werkzeug mit dem angebenen Namen.
     * 
     * @param werkzeugName Der Name eines Werkzeugs.
     * 
     * @require werkzeugName != null
     */
    private void zeigeAn(String werkzeugName)
    {
        assert werkzeugName != null : "Vorbedingung verletzt: werkzeugName != null";
        _anzeigeLayout.show(_anzeigePanel, werkzeugName);
        JButton buttonToSelect;
        JButton buttonToDeselect;
        if (werkzeugName.equals(AUSLEIHE))
        {
            buttonToSelect = getAusleiheButton();
            buttonToDeselect = getSelectedButton();
        }
        else if (werkzeugName.equals(RUECKGABE))
        {
            buttonToSelect = getRueckgabeButton();
            buttonToDeselect = getSelectedButton();
        }
        else if (werkzeugName.equals(VORMERKEN))
        {
            buttonToSelect = getVormerkButton();
            buttonToDeselect = getSelectedButton();
        }
        else
        {
            throw new IllegalArgumentException(
                    "Werkzeugname unbekannt: " + werkzeugName);
        }
        buttonToDeselect.setSelected(false);
        buttonToDeselect.setBackground(UIConstants.BUTTON_COLOR);
        buttonToSelect.setSelected(true);
        buttonToSelect.setBackground(UIConstants.BUTTON_SELECTED_COLOR);
        aktualisiereLayout();
    }

    /**
     * Gibt den aktuell selektierten Menu-Button urÃ¼ck.
     * 
     * @return Den aktuell selektierten Menu-Button.
     */
    private JButton getSelectedButton()
    {
        JButton result = _ausleiheButton;
        for (int i = 0; i < _menuPanel.getComponentCount(); i++)
        {
            Component component = _menuPanel.getComponent(i);
            if (component instanceof JButton)
            {
                JButton button = (JButton) component;
                if (button.isSelected())
                {
                    result = button;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Gibt den Ausleihe-Button zurÃ¼ck.
     * 
     * @return Den Ausleihe-Button.
     */
    public JButton getAusleiheButton()
    {
        return _ausleiheButton;
    }

    /**
     * Gibt den Rueckgabe-Button zurÃ¼ck.
     * 
     * @return Den Rueckgabe-Button.
     */
    public JButton getRueckgabeButton()
    {
        return _rueckgabeButton;
    }

    /**
     * Gibt den Vormerk-Ansicht-Button zurÃ¼ck.
     * 
     * @return Den Vormerk-Ansicht-Button.
     */
    public JButton getVormerkButton()
    {
        return _vormerkButton;
    }

    /**
     * Gibt das JFrame der UI zurÃ¼ck.
     * 
     * @return Das JFrame der UI.
     */
    public JFrame getUIFrame()
    {
        return _frame;
    }

    /**
     * Zeigt die Ausleihe-Sicht
     */
    public void zeigeAusleihe()
    {
        zeigeAn(MediathekUI.AUSLEIHE);
    }

    /**
     * Zeigt die RÃ¼ckgabe-Sicht
     */
    public void zeigeRueckgabe()
    {
        zeigeAn(MediathekUI.RUECKGABE);
    }

    /**
     * Zeigt die Vormerk-Ansicht
     */
    public void zeigeVormerkAnsicht()
    {
        zeigeAn(MediathekUI.VORMERKEN);
    }

    /**
     * Zeigt das Mediathek-Hauptfenster an.
     */
    public void zeigeFenster()
    {
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
}
