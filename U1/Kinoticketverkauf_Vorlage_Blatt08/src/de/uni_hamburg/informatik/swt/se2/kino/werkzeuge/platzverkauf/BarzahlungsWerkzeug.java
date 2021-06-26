package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

// Meine Erste Aenderung
// Die Klasse muess geaendert werden

public class BarzahlungsWerkzeug
{

    public BarzahlungsWerkzeugUI _UI;
    private int _preis;

    public BarzahlungsWerkzeug()
    {
        _UI = new BarzahlungsWerkzeugUI();
        _preis = 0;
    }

    public int getPreis()
    {
        return _preis;
    }

}
