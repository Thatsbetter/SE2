package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarzahlungswerkzeugUI
{
    public JDialog _dialog;

    private JPanel _Titel;
    private JPanel _Betrag;
    private JPanel _buttonPanel;

    private JButton _okButton;
    private JButton _abbrechenButton;

    private JTextField textfield0;
    private JTextField _zuZahlenTextField;
    private JTextField textfield2;

    public BarzahlungswerkzeugUI()
    {
        _dialog = new JDialog();
        _dialog.setSize(300, 150);////////// am besten nach unten vor dem set visible moven 

        _Titel = new JPanel();
        JLabel label1 = new JLabel("Gesamtbetrag");
        JLabel label2 = new JLabel("Bereits Bezahlt");
        JLabel label3 = new JLabel("noch zu zahlen");
        _Titel.add(label1);
        _Titel.add(label2);
        _Titel.add(label3);

        _Betrag = new JPanel();
        textfield0 = new JTextField("10");
        _zuZahlenTextField = new JTextField("Bereits Bezahlt");
        textfield2 = new JTextField("noch zu zahlen");
        _Betrag.add(textfield0);
        _Betrag.add(_zuZahlenTextField);
        _Betrag.add(textfield2);

        _buttonPanel = new JPanel();
        _okButton = new JButton("ok");
        _abbrechenButton = new JButton("abbrechen");
        _buttonPanel.add(_okButton);
        _buttonPanel.add(_abbrechenButton);

        _dialog.add(_Titel, BorderLayout.SOUTH);
        _dialog.add(_Betrag, BorderLayout.CENTER);
        _dialog.add(_buttonPanel, BorderLayout.NORTH);

        _dialog.setVisible(true);
    }

    /*
     * schliesst das dialog fenster
     */
    public void schliesseFenster()
    {
        _dialog.dispose();
    }

    public JButton getOKButton()
    {
        return _okButton;
    }

    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    public JTextField getZuZahlenTextField()
    {
        return _zuZahlenTextField;
    }

}
