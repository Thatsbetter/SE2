package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;

/**
 * Interface fÃ¼r Beobachter, die sich fÃ¼r Ã„nderungen eines ObservableSubWerkzeug
 * interessieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface SubWerkzeugObserver
{
    /**
     * Diese Operation wird aufgerufen, sobald sich an an dem beobachteten
     * Werkzeug etwas relevantes geÃ¤ndert hat.
     * 
     * Implementierende Klassen mÃ¼ssen in dieser Operation auf die Aenderung
     * reagieren.
     */
    void reagiereAufAenderung();
}
