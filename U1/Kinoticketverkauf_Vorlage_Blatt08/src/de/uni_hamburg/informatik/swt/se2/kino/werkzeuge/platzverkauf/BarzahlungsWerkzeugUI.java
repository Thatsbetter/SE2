package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;

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
        _dialog.setSize(500, 500);
        
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        
        _dialog.add(north, BorderLayout.NORTH);
        _dialog.add(south, BorderLayout.SOUTH);
        
        JPanel northwest = new JPanel();
        JPanel northcenter = new JPanel();
        JPanel northeast = new JPanel();
        
        north.add(northwest, BorderLayout.WEST);
        north.add(northcenter, BorderLayout.CENTER);
        north.add(northeast, BorderLayout.EAST);
        
        JLabel preisLabel = new JLabel(Integer.toString(preis));
        JLabel preisLabelTitel = new JLabel("Gesamtbetrag");
        
        northwest.add(preisLabel, BorderLayout.NORTH);
        northwest.add(preisLabelTitel, BorderLayout.SOUTH);
        
        _eingabeTextField = new JTextField();
        JLabel eingabeLabelTitel = new JLabel("Bezahlt");
        
        northcenter.add(_eingabeTextField, BorderLayout.NORTH);
        northcenter.add(eingabeLabelTitel, BorderLayout.SOUTH);
        
        _restBetragLabel = new JLabel(Integer.toString(preis));
        _restBetragLabelTitel = new JLabel(RESTBETRAG);
        
        northeast.add(_restBetragLabel, BorderLayout.NORTH);
        northeast.add(_restBetragLabelTitel, BorderLayout.SOUTH);
        
        _okButton = new JButton("Okay");
        _okButton.setEnabled(false);
        _abbrechenButton = new JButton("Abbrechen");
        
        south.add(_okButton, BorderLayout.WEST);
        south.add(_abbrechenButton, BorderLayout.EAST);
        
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
