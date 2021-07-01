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
        _ui.zeigeFenster();
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
                    
                    int restBetrag = restBetragBerechnen(Integer.parseInt(eingabe));
                    
                    if(restBetrag > 0)
                    {
                    	restBetragAendern(restBetrag);
                    	_ui.setzeRestBetragLabelTitelAufRueckgeld(false);
                    	setzeOKButtonAktiv(false);
                	}
                    else
                    {
                    	// TODO Was wenn zu viel gegeben ?
                        restBetragAendern(-restBetrag);
                    	_ui.setzeRestBetragLabelTitelAufRueckgeld(true);
                    	setzeOKButtonAktiv(true);
                    }

                }
            });

    }
    
    private boolean istEingabeGueltig(String s)
    {
    	return s.matches("[1-9][0-9]{0-8}");
    }
    
    private int restBetragBerechnen(int eingabe)
    {
    	return eingabe - _preis;
    }
    
    private void restBetragAendern(int restBetrag)
    {
    	_ui.setRestBetragLabel(restBetrag);
    }
    
    public void abbrechenButtonGedrueckt()
    {
    	_ui.schliesseFenster();
        //TODO ? _ui.getEingabeTextField();

    }

    private void okButtonGedrueckt()
    {
    	informiereUeberAenderung();
        //TODO:
        //check if RestBetrag is more than GesamtBetrag if yes, set warVerkaufErfolgreich on true

    }

    private void setzeOKButtonAktiv(boolean aktiv)
    {
        _ui.getOKButton()
            .setEnabled(aktiv);
    }

}
