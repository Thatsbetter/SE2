package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class BarzahlungsWerkzeugUI
{
    public JDialog _dialog;

    private JTextField _eingabeTextField;
    private JLabel _restBetragLabel;
    private JLabel _restBetragLabelTitel;
    
    private JButton _okButton;
    private JButton _abbrechenButton;
    
    private static final String RESTBETRAG = "Restbetrag";    
    private static final String RUECKGELD = "Rückgeld";

    public BarzahlungsWerkzeugUI(int preis)
    {
        _dialog = new JDialog();
        _dialog.setModal(true);
        _dialog.setSize(800, 600);
        
        JPanel panel = new JPanel(new GridLayout(3,1));
        _dialog.add(panel);

        JPanel north = new JPanel(new GridLayout(1,3));
        JPanel center = new JPanel(new GridLayout(1,3));
        JPanel south = new JPanel(new GridLayout(1,2));
        
        //TODO font-size und aligning
        
        panel.add(north);
        panel.add(center);
        panel.add(south);
        
        JLabel preisLabel = new JLabel(Integer.toString(preis));
        _eingabeTextField = new JTextField();
        _restBetragLabel = new JLabel(Integer.toString(preis));
        
        north.add(preisLabel);
        north.add(_eingabeTextField);
        north.add(_restBetragLabel);
               
        JLabel preisLabelTitel = new JLabel("Gesamtbetrag");
        JLabel eingabeLabelTitel = new JLabel("Bezahlt");
        _restBetragLabelTitel = new JLabel(RESTBETRAG);
        
        center.add(preisLabelTitel);
        center.add(eingabeLabelTitel);
        center.add(_restBetragLabelTitel);
               
        _okButton = new JButton("Okay");
        _okButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");
        
        south.add(_okButton);
        south.add(_abbrechenButton);
        
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

    public JTextField getEingabeTextField()
    {
        return _eingabeTextField;
    }
    
    public void setRestBetragLabel(int restBetrag)
    {
        _restBetragLabel.setText(Integer.toString(restBetrag));
        System.out.println(Integer.toString(restBetrag));
    }

    public void setzeRestBetragLabelTitelAufRueckgeld(boolean b)
    {
    	if(b)
    	{
    		_restBetragLabel.setText(RUECKGELD);
    	}
    	else
    	{
    		_restBetragLabel.setText(RESTBETRAG);
    	}
    }
    
    public void meldeFehler(String s)
    {
        JDialog fehlerDialog = new JDialog();
        JLabel fehlerLabel = new JLabel(s, SwingConstants.CENTER);


        fehlerDialog.setSize(300, 100);
        fehlerDialog.add(fehlerLabel, BorderLayout.CENTER);
        fehlerDialog.setModal(true);
        fehlerDialog.setVisible(true);

    	// nicht sichtbares feld wird sichtbar gemacht, oder jdialog popup mit fehlermeldung
    }
}