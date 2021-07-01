package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BarzahlungsWerkzeugUI
{
    public JDialog _dialog;

    private JTextField _eingabeTextField;
    private JLabel _restBetragLabel;
    private JLabel _restBetragLabelTitel;

    private JButton _okButton;
    private JButton _abbrechenButton;

    public static final String RESTBETRAG = "Restbetrag";
    public static final String RUECKGELD = "Rückgeld";

    /**
     * erzeugt das Panel, dass zur Barzahlung verwendet wird
     * 
     * @param preis der zu zahlende Preis um die Ausgewaehlten Plaetze zu ergattern
     */
    public BarzahlungsWerkzeugUI(int preis)
    {
        _dialog = new JDialog();
        // TODO dialog font size 

        JPanel panel = new JPanel(new GridLayout(3, 1));
        _dialog.add(panel);

        JPanel north = new JPanel(new GridLayout(1, 3));
        JPanel center = new JPanel(new GridLayout(1, 3));
        JPanel south = new JPanel(new GridLayout(1, 2));

        panel.add(north);
        panel.add(center);
        panel.add(south);

        JLabel preisLabel = new JLabel(Integer.toString(preis),
                SwingConstants.CENTER);
        preisLabel.setVerticalAlignment(JLabel.BOTTOM);

        //TODO eingabe centered?
        _eingabeTextField = new JTextField(SwingConstants.CENTER);
        _restBetragLabel = new JLabel(Integer.toString(preis),
                SwingConstants.CENTER);
        _restBetragLabel.setVerticalAlignment(JLabel.BOTTOM);

        north.add(preisLabel);
        north.add(_eingabeTextField);
        north.add(_restBetragLabel);

        JLabel preisLabelTitel = new JLabel("Gesamtbetrag",
                SwingConstants.CENTER);
        preisLabelTitel.setVerticalAlignment(JLabel.TOP);
        JLabel eingabeLabelTitel = new JLabel("Bezahlt", SwingConstants.CENTER);
        eingabeLabelTitel.setVerticalAlignment(JLabel.TOP);
        _restBetragLabelTitel = new JLabel(RESTBETRAG, SwingConstants.CENTER);
        _restBetragLabelTitel.setVerticalAlignment(JLabel.TOP);

        center.add(preisLabelTitel);
        center.add(eingabeLabelTitel);
        center.add(_restBetragLabelTitel);

        _okButton = new JButton("Okay");
        _okButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");

        south.add(_okButton);
        south.add(_abbrechenButton);
    }

    /**
     * Schliesst das JDialog Fenster und macht Ressourcen frei
     */
    public void schliesseFenster()
    {
        _dialog.dispose();
    }


    /**
     * Zeigt das JDialog Fenster an 
     */
    public void zeigeFenster()
    {
        _dialog.setModal(true);
        _dialog.setSize(600, 300);
        _dialog.setLocationRelativeTo(null);
        _dialog.setVisible(true);
    }

    /**
    * Aendert den angezeigten Restbetrag der noch zu zahlen ist
    * 
    * @param restBetrag der noch zu zahlenden Restbetrag
    */
    public void setRestBetragLabel(int restBetrag)
    {
        _restBetragLabel.setText(Integer.toString(restBetrag));
    }

    /**
     * Setzt die Anzeige auf Restbetrag oder Rueckgeld, je nachdem ob man genug geld bekommen hat
     * 
     * @param s Restbetrag oder Rueckgeld
     */
    public void setRestBetragLabelTitel(String s)
    {
        if (s == RESTBETRAG || s == RUECKGELD) _restBetragLabelTitel.setText(s);
    }

    /**
     * oeffnet einen JDialog welcher den User ueber einen im Parameter definierten fehler informiert
     * 
     * @param s Der anzuzeigende Fehlermeldungstext
     */
    public void meldeFehler(String s)
    {
        JDialog fehlerDialog = new JDialog();
        JLabel fehlerLabel = new JLabel(s, SwingConstants.CENTER);

        fehlerDialog.setSize(550, 150);
        fehlerDialog.add(fehlerLabel, BorderLayout.CENTER);
        fehlerDialog.setModal(true);
        fehlerDialog.setLocationRelativeTo(null);
        fehlerDialog.setVisible(true);
    }

    public void undoTextfeld(String s)
    {
    	_eingabeTextField = new JTextField(s.substring(0, s.length()-3));
    }
    
    public JButton getOKButton()
    {
        return _okButton;
    }

    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    public JTextField getEingabeTextField()
    {
        return _eingabeTextField;
    }
}
