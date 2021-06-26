package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

import java.util.HashSet;

public abstract class Beobachtbar
{
    HashSet<Beobachter> _beobachter = new HashSet<Beobachter>();

    /**
     * fuegt einen Beobachter der Liste hinzu. Der Beobachter wird
     * informiert, wenn sich bei diesem Werkzeug etwas Ã¤ndert.
     * 
     * @require beobachter != null
     */
    public void fuegeBeobachterHinzu(Beobachter b)
    {
        assert b != null : "Vorbedingung verletzt: beobachter != null";
        _beobachter.add(b);
    }

    /**
     * Entfernt einen Beobachter aus der Liste. 
     *
     * @require beobachter != null
     */
    public void entferneBeobachter(Beobachter b)
    {
        assert b != null : "Vorbedingung verletzt: beobachter != null";
        _beobachter.remove(b);
    }

    /**
     * Informiert alle in der Liste registrierten Beobachter Ã¼ber eine
     * Ã„nderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Ã„nderung geschehen ist, die fÃ¼r Beobachter interessant ist.
     */
    protected void meldeAenderung()
    {
        for (Beobachter b : _beobachter)
        {
            b.beachteAenderung(this);
        }
    }
}
