package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

import java.util.HashSet;

/**
 * 
 * @author Taha
 * 
 * Die Klasse Beobachtbar ist ein abstaktes Interface für alle Klassen, die beobachtet
 * werden müssen
 * 
 *
 */

public abstract class Beobachtbar
{
    HashSet<Beobachter> _beobachter = new HashSet<Beobachter>();

    /**
     * fügt einen Beobachter in die Liste von Beobachtern hinzu
     * 
     * @param b als ein Beobachter Objekt, das in die Liste hinzugefügt werden muss.
     * ensure b != null
     * 
     */
    public void fuegeBeobachterHinzu(Beobachter b)
    {
        assert b != null;
        _beobachter.add(b);
    }

    /**
    * löscht einen Beobachter aus der Liste von Beobachtern.
    * 
    * @param b als ein Beobachter Objekt, das aus der Liste gelöscht werden muss.
    * ensure b != null
    * 
    */

    public void entferneBeobachter(Beobachter b)
    {
        assert b != null;
        _beobachter.remove(b);
    }

    /**
     * Informiert alle an diesem Subwerkzeug registrierten Beobachter über eine
     * Änderung.
     * 
     */
    protected void meldeAenderung()
    {

        for (Beobachter b : _beobachter)
        {
            b.beachteAenderung(this);
        }
    }
}
