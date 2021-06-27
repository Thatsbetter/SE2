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

    private JPanel _titel;
    private JPanel _betrag;
    private JPanel _okbuttons;

    private JTextField textfield0;
    private JTextField textfield1;
    private JTextField textfield2;

    public BarzahlungswerkzeugUI()
    {
        _dialog = new JDialog();
        _dialog.setSize(300, 150);////////// am besten nach unten vor dem set visible moven 

        _titel = new JPanel();
        JLabel label1 = new JLabel("Gesamtbetrag");
        JLabel label2 = new JLabel("Bereits Bezahlt");
        JLabel label3 = new JLabel("noch zu zahlen");
        _titel.add(label1);
        _titel.add(label2);
        _titel.add(label3);

        _betrag = new JPanel();
        textfield0 = new JTextField("10");
        textfield1 = new JTextField("Bereits Bezahlt");
        textfield2 = new JTextField("noch zu zahlen");
        _betrag.add(textfield0);
        _betrag.add(textfield1);
        _betrag.add(textfield2);

        _okbuttons = new JPanel();
        JButton button1 = new JButton("ok");
        JButton button2 = new JButton("abbrechen");
        _okbuttons.add(button1);
        _okbuttons.add(button2);

        _dialog.add(_titel, BorderLayout.SOUTH);
        _dialog.add(_betrag, BorderLayout.CENTER);
        _dialog.add(_okbuttons, BorderLayout.NORTH);

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
        return getOKButton()
    }

}
