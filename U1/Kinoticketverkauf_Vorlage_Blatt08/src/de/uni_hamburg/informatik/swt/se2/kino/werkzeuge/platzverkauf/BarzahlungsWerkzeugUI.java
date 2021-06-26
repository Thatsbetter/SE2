package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarzahlungsWerkzeugUI
{
    public JDialog _dialog;

    private JPanel _Titel;
    private JPanel _Betrag;
    private JPanel _Buttons;

    private JTextField textfield0;
    private JTextField textfield1;
    private JTextField textfield2;
    
    

    public BarzahlungsWerkzeugUI()
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
        textfield1 = new JTextField("Bereits Bezahlt");
        textfield2 = new JTextField("noch zu zahlen");
        _Betrag.add(textfield0);
        _Betrag.add(textfield1);
        _Betrag.add(textfield2);

        _Buttons = new JPanel();
        JButton button1 = new JButton("ok");
        JButton button2 = new JButton("abbrechen");
        _Buttons.add(button1);
        _Buttons.add(button2);

        _dialog.add(_Titel, BorderLayout.SOUTH);
        _dialog.add(_Betrag, BorderLayout.CENTER);
        _dialog.add(_Buttons, BorderLayout.NORTH);

        _dialog.setVisible(true);
    }

    /*
     * schliesst das dialog fenster
     */
    public void schliesseFenster()
    {
        _dialog.dispose();
    }
    
    
}
