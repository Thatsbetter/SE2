package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

/**
 * Interface fÃ¼r Beobachter, die sich fÃ¼r Ã„nderungen eines Beobachtbar
 * interessieren
*/

public interface Beobachter
{

    /**
     * Diese Operation wird aufgerufen, sobald sich an an dem beobachteten
     * Subwerkzeug etwas Relevantes geÃ¤ndert hat.
     * 
     * Implementierende Klassen mÃ¼ssen in dieser Operation auf die Aenderung
     * reagieren.
     *
     * @require object != null
     */

    void beachteAenderung(Object o);
}
