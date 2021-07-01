package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{

    public BarzahlungsWerkzeugUI _ui;
    private int _preis;
    public boolean _warErfolgreich;

    /**
     * initialisiert unser Barzahlungswerkzeug
     * 
     * @param preis der Preis der ausgewaehlten plaete
     */
    public BarzahlungsWerkzeug(int preis)
    {
        _ui = new BarzahlungsWerkzeugUI(preis);
        _preis = preis;
        registriereUIAktionen();
    }

    /**
     * fuegt den Buttons aus der UI actionlistener hinzu, damit die buttons auch auf ihre aktivierung reagieren
    
     */
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
            .getDocument()
            .addDocumentListener(new DocumentListener()
            {

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    textFieldGeaendert();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    textFieldGeaendert();

                }

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    textFieldGeaendert();
                }
            });

    }

    /**
     * wird gerufen, wenn sich die eingabe ändert und aktualisiert den Restbetrag oder zeigt eine Fehlermeldung an.
     */
    private void textFieldGeaendert()
    {
        String eingabe = _ui.getEingabeTextField()
            .getText();

        if (!istEingabeGueltig(eingabe))
        {
        	_ui.meldeFehler(
                    "Bitte Betrag in Eurocent ohne führende Nullen angeben!");
            return;
        }
        if (!eingabe.isEmpty())
        {
            int restBetrag = _preis - Integer.parseInt(eingabe);
            aktualisiereBetragsAnzeige(restBetrag);
            return;
        }
        aktualisiereBetragsAnzeige(_preis);    }
    
    

    /**
     * aktualisiert die einzelnen UI Elemente
     * 
     * @param restBetrag Der aktuelle Betrag der noch zu zahlen ist
     */
    private void aktualisiereBetragsAnzeige(int restBetrag)
    {
        if (restBetrag > 0)
        {
            _ui.setRestBetragLabel(restBetrag);
            _ui.setRestBetragLabelTitel(BarzahlungsWerkzeugUI.RESTBETRAG);
            _ui.getOKButton()
                .setEnabled(false);
        }
        else
        {
            _ui.setRestBetragLabel(-restBetrag);
            _ui.setRestBetragLabelTitel(BarzahlungsWerkzeugUI.RUECKGELD);
            _ui.getOKButton()
                .setEnabled(true);
        }
    }
    
    /**
     * Ueberprueft per RegEx ob die Eingabe gueltig ist
     * 
     * @param s Der Preis der als String uebergen wird
     * @return true wenn der string nicht mit 0 anfaengt und kleiner 10 millionen ist und keine buchstaben enthaelt
     */
    private boolean istEingabeGueltig(String s)
    {
        return s.matches("[1-9][0-9]{0,8}") || s.isEmpty();
    }

    /**
     * Schliesst unser Fenster
     */
    public void abbrechenButtonGedrueckt()
    {
        _ui.schliesseFenster();
    }

    /**
     * setzt _warErfolgreich auf true und schließt BarzahlungsUI
     */
    private void okButtonGedrueckt()
    {
        _warErfolgreich = true;
        _ui.schliesseFenster();
    }

    /**
     * Zeigt das Fenster an
     */
    public void zeigeFenster()
    {
        _ui.zeigeFenster();
    }
}
