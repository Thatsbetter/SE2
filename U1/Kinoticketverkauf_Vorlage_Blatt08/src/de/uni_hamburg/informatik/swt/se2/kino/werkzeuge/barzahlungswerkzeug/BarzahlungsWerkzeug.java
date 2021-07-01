package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

// Meine Erste Aenderung
// Die Klasse muess geaendert werden

public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{

    public BarzahlungsWerkzeugUI _ui;
    private int _preis;

    public BarzahlungsWerkzeug(int preis)
    {
        _ui = new BarzahlungsWerkzeugUI(preis);
        _preis = preis;
        registriereUIAktionen();
    }

    private void registriereUIAktionen()
    {
        _ui.getOKButton()
            .addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    okButtonGedrueckt();

                }
            });

        _ui.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    abbrechenButtonGedrueckt();

                }
            });

        _ui.getEingabeTextField()
            .addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {                  
                	String eingabe = _ui.getEingabeTextField().getText();
                	
                    if(!istEingabeGueltig(eingabe))
                    {
                    	_ui.meldeFehler("Bitte Betrag in Eurocent ohne führende Nullen angeben!");
                    	return;
                    }
                    
                    int restBetrag = _preis - Integer.parseInt(eingabe);
                    
                    if(restBetrag > 0)
                    {
                    	_ui.setRestBetragLabel(restBetrag);
                    	_ui.setRestBetragLabelTitel(BarzahlungsWerkzeugUI.RESTBETRAG);
                    	_ui.getOKButton().setEnabled(false);
                	}
                    else
                    {
                    	_ui.setRestBetragLabel(-restBetrag);
                    	_ui.setRestBetragLabelTitel(BarzahlungsWerkzeugUI.RUECKGELD);
                    	_ui.getOKButton().setEnabled(true);
                    }

                }
            });

    }
    
    private boolean istEingabeGueltig(String s)
    {
    	return s.matches("[1-9][0-9]{0,8}");
    }
    
    public void abbrechenButtonGedrueckt()
    {
    	_ui.schliesseFenster();
    }

    private void okButtonGedrueckt()
    {
    	informiereUeberAenderung();
    	_ui.schliesseFenster();
    }
    
    public void zeigeFenster()
    {
    	_ui.zeigeFenster();
    }
}
