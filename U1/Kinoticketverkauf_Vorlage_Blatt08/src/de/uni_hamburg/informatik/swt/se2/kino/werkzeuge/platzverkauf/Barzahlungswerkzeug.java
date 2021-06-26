package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

// Meine Erste Aenderung

public class Barzahlungswerkzeug
{

    public BarzahlungswerkzeugUI _UI;
    private int _preis;

    public Barzahlungswerkzeug()
    {
        _UI = new BarzahlungswerkzeugUI();
        _preis = 0;
    }

    public int getPreis()
    {
        return _preis;
    }

}
