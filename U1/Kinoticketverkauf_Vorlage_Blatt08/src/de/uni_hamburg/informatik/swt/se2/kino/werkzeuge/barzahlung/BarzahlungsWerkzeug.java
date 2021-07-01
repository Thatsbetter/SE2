package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

// Meine Erste Aenderung
// Die Klasse muess geaendert werden

public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{

    public BarzahlungsWerkzeugUI _ui;
    private int _preis;

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
            .addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String eingabe = _ui.getEingabeTextField()
                        .getText();

                    if (!istEingabeGueltig(eingabe))
                    {
                        _ui.meldeFehler(
                                "Bitte Betrag in Eurocent ohne führende Nullen angeben!");
                        return;
                    }

                    int restBetrag = _preis - Integer.parseInt(eingabe);

                    aktualisiereBetragsAnzeige(restBetrag);

                }
            });

    }

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
        return s.matches("[1-9][0-9]{0,8}");
    }

    /**
     * Schliesst unser Fenster
     */
    public void abbrechenButtonGedrueckt()
    {
        _ui.schliesseFenster();
    }

    /*
     * TODO ... und schliesst das Fenster
     */
    private void okButtonGedrueckt()
    {
        informiereUeberAenderung();
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
