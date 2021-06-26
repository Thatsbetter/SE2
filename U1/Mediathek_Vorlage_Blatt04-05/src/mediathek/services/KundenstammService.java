package mediathek.services;

import java.util.List;

import mediathek.fachwerte.Kundennummer;
import mediathek.materialien.Kunde;

/**
 * Ein Kundenstamm ist ein Service, der die Menge von Kunden verwaltet. Auf
 * Kunden kann über ihre {@link Kundennummer} zugegriffen werden.
 * 
 * KundenstammService ist ein ObservableService, als solcher bietet er die
 * Möglichkeit über Änderungen am Kundenstamm zu informieren. Beobachter müssen
 * das Interface  {@link ServiceObserver} implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface KundenstammService extends ObservableService
{

    /**
     * Entfernt einen Kunden aus dem Kundenbestand.
     * 
     * @param kunde Ein zu entfernender Kunde.
     * 
     * @require enthaeltKunden(kunde)
     * @ensure !enthaeltKunden(kunde)
     */
    void entferneKunden(Kunde kunde);

    /**
     * Gibt Auskunft, ob ein Kunde im Kundenbestand enthalten ist.
     * 
     * @param kunde Ein Kunde
     * @return true, wenn Kunde im Kundenbestand enthalten ist, andernfalls
     *         false.
     * 
     * @require kunde != null
     */
    boolean enthaeltKunden(Kunde kunde);

    /**
     * Fügt einen weiteren Kunden in den Kundenbestand ein.
     * 
     * @param neuerKunde Ein neuer Kunde.
     * 
     * @require !enthaeltKunden(neuerKunde)
     * @ensure enthaeltKunden(neuerKunde)
     */
    void fuegeKundenEin(Kunde neuerKunde);

    /**
     * Liefert alle vorhandenen Kunden. Wenn es keine Kunden gibt, wird eine
     * leere Liste zurückgegeben.
     * 
     * @return Eine Kopie der Liste mit allen vorhandenen Kunden.
     * 
     * @ensure result != null
     */
    List<Kunde> getKunden();

    /**
     * Liefert zur übergebenen Kundennummer einen Kunden, wenn es einen
     * passenden Eintrag im Kundenstamm gibt.
     * 
     * @param kundennummer Eine Kundennummer
     * @return Einen Kunden oder null, wenn es keinen passenden Eintrag gibt.
     * 
     * @require kundennummer != null
     */
    Kunde getKunden(Kundennummer kundennummer);

}
