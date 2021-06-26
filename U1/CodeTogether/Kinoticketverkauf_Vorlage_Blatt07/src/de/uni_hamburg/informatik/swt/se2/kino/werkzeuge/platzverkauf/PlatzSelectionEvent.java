package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Event, das Ã¼ber eine Ã„nderung bei der Auswahl von PlÃ¤tzen informiert. Dieses
 * Event wird von einem {@link JPlatzplan} ausgelÃ¶st, wenn der Benutzer in dem
 * Platzplan PlÃ¤tze auswÃ¤hlt. Das Event enthÃ¤lt die Menge der PlÃ¤tze, die jetzt
 * ausgewÃ¤hlt sind.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class PlatzSelectionEvent extends EventObject
{
    private Set<Platz> _ausgewaehltePlaetze;

    /**
     * Erstellt ein neues PlatzSelectionEvent.
     * 
     * @param source Das Objekt, von dem das Ereignis ausgelÃ¶st wurde.
     * @param ausgewaehltePlaetze die Menge der ausgewÃ¤hlten PlÃ¤tze.
     */
    public PlatzSelectionEvent(Object source, Set<Platz> ausgewaehltePlaetze)
    {
        super(source);
        _ausgewaehltePlaetze = new HashSet<Platz>(ausgewaehltePlaetze);
    }

    /**
     * Gibt die Menge der nach diesem Ereignis ausgewÃ¤hlten PlÃ¤tze zurÃ¼ck.
     */
    public Set<Platz> getAusgewaehltePlaetze()
    {
        return _ausgewaehltePlaetze;
    }

    @Override
    public String toString()
    {
        return "PlatzSelectionEvent[source=" + source + "]";
    }

    private static final long serialVersionUID = 1L;
}
