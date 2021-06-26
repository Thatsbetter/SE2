package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.util.EventListener;

/**
 * Interface eines Listeners, der bei Ã„nderungen der Platzauswahl benachrichtigt
 * wird.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
interface PlatzSelectionListener extends EventListener
{
    /**
     * Wird aufgerufen, wenn sich die Auswahl geÃ¤ndert hat.
     * 
     * @param event das Event, das die Ã„nderung beschreibt.
     */
    void auswahlGeaendert(PlatzSelectionEvent event);
}
