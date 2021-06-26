package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

/**
 * Interface fÃ¼r Kontextwerkzeuge, die Subwerkzeuge beobachten mÃ¶chten.
 * 
 * Werkzeuge, die dieses Interface implementieren, kÃ¶nnen sich an einem
 * Subwerkzeug, das von {@link ObservableSubwerkzeug} erbt, als Beobachter
 * registrieren. Sie werden dann vom Subwerkzeug bei Ã„nderungen benachrichtigt,
 * zum Beispiel bei der Auswahl eines Materials in einer Liste durch den
 * Benutzer.
 * 
 * Damit ein Kontextwerkzeug mehrere Subwerkzeuge beobachten und auf deren
 * Nachrichten unterschiedlich reagieren kann, bietet es sich an, dieses
 * Interface in inneren Klassen (inner classes) zu implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface SubwerkzeugObserver
{
    /**
     * Reagiert auf eine Ã„nderung in dem beobachteten Subwerkzeug.
     */
    void reagiereAufAenderung();
}
