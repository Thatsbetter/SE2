package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Meine Erste Aenderung
// Die Klasse muess geaendert werden

public class Barzahlungswerkzeug
{

    public BarzahlungswerkzeugUI _ui;
    private int _preis;

    public Barzahlungswerkzeug()
    {
        _ui = new BarzahlungswerkzeugUI();
        _preis = 0;
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

        _ui.getZuZahlenTextField()
            .addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //TODO:
                    // add  istBetragGueltig()
                    // add restBetragBerechnen()

                }
            });

    }

    private void abbrechenButtonGedrueckt()
    {
        _ui.getZuZahlenTextField();

    }

    private void okButtonGedrueckt()
    {

    }

    private void setzeOKButtonAktiv(boolean aktiv)
    {

    }

    public int getPreis()
    {
        return _preis;
    }

}
