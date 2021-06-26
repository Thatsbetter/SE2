package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.ArrayList;
import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkerKarte
{

    // Eigenschaften einer VormerkerKarte
    private final List<Kunde> _vormerkerListe;
    private final Medium _medium;
    public static final int MAXVORMERKER = 3;

    /**  * Initialisert eine neue VormerkerKarte mit den gegebenen Daten.
         * 
         * @param vormerker Ein Kunde, der das Medium ausgeliehen hat.
         * @param medium Ein verliehene Medium.
    
         * 
         * @require vormerker != null
         * @require medium != null
         * 
         * @ensure #getVormerker() == vormerker
         * @ensure #getMedium() == medium
         */
    public VormerkerKarte(Kunde vormerker, Medium medium)
    {

        assert medium != null : "Vorbedingung verletzt: medium != null";
        assert vormerker != null : "Vorbedingung verletzt: vormerker != null";

        _medium = medium;
        _vormerkerListe = new ArrayList<Kunde>(MAXVORMERKER);
        _vormerkerListe.add(vormerker);
    }

    /**
     * Gibt den Vormerker zurück.
     * 
     * @return den Kunden, der das Medium entliehen hat.
     * 
     * @require 
     * @ensure result != null
     */
    public Kunde getVormerker(int position)
    {
        return _vormerkerListe.get(position);
    }

    /**
     * Gibt das Medium, dessen Vormerkung auf der Karte vermerkt ist, zurück.
     * 
     * @return Das Medium, dessen Vormerkung auf dieser Karte vermerkt ist.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
    * Gibt die aktuelle Länge der Liste von Vormerkern zurück.
    * 
    * @return die Länge der Vormerkerliste zurück.
    * 
    * @ensure result != null
    */
    public int getAktuelleListenLaenge()
    {
        return _vormerkerListe.size();
    }

    /**
     * Es wird der Vormerker, der in der ersten Stelle ist, rausgelöscht, und alle andere eine
     * eine Stelle nach vorne geschoben
     * 
     * 
     *
     * @ensure ind(0) == 0, wenn size =1
     */
    public void rueckeVor()
    {
        if (getAktuelleListenLaenge() > 1)
        {
            _vormerkerListe.remove(0);
        }
        else if (getAktuelleListenLaenge() == 1)
        {
            _vormerkerListe.set(0, null);
        }

    }

    /**
     * Gibt Die Maximale Anzahl an Personen die auf einer Karte Vorgemerkt werden koennen zurück.
     * 
     * @return Die Maximal Anzahl an Personen auf einer Karte.
     * 
     * @ensure result != null
     */
    public void addVormerker(Kunde kunde)
    {
        for (Kunde k : _vormerkerListe)
        {
            if (k.equals(kunde)) // überprüft ob der Kunde bereits in der Liste vorhanden ist.
            {
                return;
            }
            if (!(_vormerkerListe.size() < MAXVORMERKER)) // überprüft ob ein freier platz vorhanden ist
            {
                return;
            }
        }
        _vormerkerListe.add(kunde);

    }

    /**
     * Gibt züruck, ob ein Kunde Vormerker ist
     * @param kunde 
     * @return true , falls kunde Vormerker ist
     * 
     * 
     */
    public boolean istVormerker(Kunde kunde)
    {
        for (Kunde k : _vormerkerListe)
        {
            if (k == kunde)
            {
                return true;
            }
        }
        return false;
    }

}
