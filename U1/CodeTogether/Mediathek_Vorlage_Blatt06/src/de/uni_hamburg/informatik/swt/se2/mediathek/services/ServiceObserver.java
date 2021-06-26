package de.uni_hamburg.informatik.swt.se2.mediathek.services;

/**
 * Interface fÃ¼r Beobachter, die sich fÃ¼r Ã„nderungen eines ObservableService
 * interessieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface ServiceObserver
{

    /**
     * Diese Operation wird aufgerufen, sobald sich an an dem beobachteten
     * Service etwas relevantes geÃ¤ndert hat.
     * 
     * Implementierende Klassen mÃ¼ssen in dieser Operation auf die Aenderung
     * reagieren.
     */
    void reagiereAufAenderung();
}
