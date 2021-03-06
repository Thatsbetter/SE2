package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

import java.util.HashSet;
import java.util.Set;

/**
 * Basisklasse fÃ¼r Subwerkzeuge, die ihr Kontextwerkzeug bei Ã„nderungen
 * benachrichtigen mÃ¶chten.
 * 
 * Diese Klasse implementiert die Schnittstelle, Ã¼ber die sich Beobachter an dem
 * Subwerkzeug registrieren kÃ¶nnen. In der Regel wird es genau ein beobachtendes
 * Kontextwerkzeug geben. In Ausnahmen koennen es auch mehrere sein, wenn
 * indirekte Kontextwerkzeuge ebenfalls beobachten. Diese Klasse erlaubt beides.
 * 
 * Erbende Klassen rufen die Methode {@link #informiereUeberAenderung()} auf, um
 * alle Beobachter zu benachrichtigen. Erbende Klassen mÃ¼ssen dokumentieren, in
 * welchen FÃ¤llen sie ihre Beobachter informieren.
 * 
 * Diese Klasse entspricht der Klasse "Beobachtbar" im Beobachter-Muster.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public abstract class ObservableSubwerkzeug
{
    private Set<SubwerkzeugObserver> _alleBeobachter;

    /**
     * Initialisiert ein beobachtbares Subwerkzeug.
     */
    public ObservableSubwerkzeug()
    {
        _alleBeobachter = new HashSet<SubwerkzeugObserver>();
    }

    /**
     * Registriert einen Beobachter an diesem Subwerkzeug. Der Beobachter wird
     * informiert, wenn sich bei diesem Werkzeug etwas Ã¤ndert.
     * 
     * @require beobachter != null
     */
    public void registriereBeobachter(SubwerkzeugObserver beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _alleBeobachter.add(beobachter);
    }

    /**
     * Entfernt einen Beobachter dieses Subwerkzeugs. Wenn der Beobachter gar
     * nicht registriert war, passiert nichts.
     */
    public void entferneBeobachter(SubwerkzeugObserver beobachter)
    {
        _alleBeobachter.remove(beobachter);
    }

    /**
     * Informiert alle an diesem Subwerkzeug registrierten Beobachter Ã¼ber eine
     * Ã„nderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Ã„nderung geschehen ist, die fÃ¼r Beobachter interessant ist.
     */
    protected void informiereUeberAenderung()
    {
        for (SubwerkzeugObserver beobachter : _alleBeobachter)
        {
            beobachter.reagiereAufAenderung();
        }
    }
}
