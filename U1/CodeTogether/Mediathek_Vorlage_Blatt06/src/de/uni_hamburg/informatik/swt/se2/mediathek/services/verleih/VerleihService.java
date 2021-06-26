package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.VormerkerKarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ObservableService;

/**
 * Der VerleihService erlaubt es, Medien auszuleihen und zurÃ¼ckzugeben.
 * 
 * FÃ¼r jedes ausgeliehene Medium wird eine neue Verleihkarte angelegt. Wird das
 * Medium wieder zurÃ¼ckgegeben, so wird diese Verleihkarte wieder gelÃ¶scht.
 * 
 * VerleihService ist ein ObservableService, als solcher bietet er die
 * MÃ¶glichkeit Ã¼ber VerleihvorgÃ¤nge zu informieren. Beobachter mÃ¼ssen das
 * Interface ServiceObserver implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface VerleihService extends ObservableService
{
    /**
     * Verleiht Medien an einen Kunden. Dabei wird fÃ¼r jedes Medium eine neue
     * Verleihkarte angelegt.
     * 
     * @param kunde Ein Kunde, an den ein Medium verliehen werden soll
     * @param medien Die Medien, die verliehen werden sollen
     * @param ausleihDatum Der erste Ausleihtag
     * 
     * @throws ProtokollierException Wenn beim Protokollieren des
     *             Verleihvorgangs ein Fehler auftritt.
     * 
     * @require kundeImBestand(kunde)
     * @require sindAlleNichtVerliehen(medien)
     * @require ausleihDatum != null
     * 
     * @ensure sindAlleVerliehenAn(kunde, medien)
     */
    void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
            throws ProtokollierException;

    /**
     * fÃ¼gt einen Kunden als Vormerker zu einer Vormerkekarte hinzu
     * 
     * @param kunde Der Kunde ,der das Medium vormerken will
     * @param medium Die medium, das vorgemerkt werden soll
     * 
     * 
     * 
     * @require kundeImBestand(kunde)
     * @require mediumImBestand(medium)
     */
    void merkeVor(Kunde kunde, Medium medium);

    /**
     * PrÃ¼ft ob die ausgewÃ¤hlten Medium fÃ¼r den Kunde ausleihbar sind
     * 
     * @param kunde Der Kunde fÃ¼r den geprÃ¼ft werden soll
     * @param medien Die medien
     * 
     * 
     * @return true, wenn das Entleihen fÃ¼r diesen Kunden mÃ¶glich ist, sonst
     *         false
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien);

    /**
     * ÃœberprÃ¼ft ob vormerken moeglich ist.
     * 
     * @param medium Ein Medium 
     * @return true wenn vormerken moeglich ist, false falls vormerken nicht moeglich ist.
     */
    boolean istVormerkenMoeglich(Medium medium);

    /**
     * Liefert den Entleiher des angegebenen Mediums.
     * 
     * @param medium Das Medium.
     * 
     * @return Den Entleiher des Mediums.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure result != null
     */
    Kunde getEntleiherFuer(Medium medium);

    /**
     * Liefert alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     * 
     * @param kunde Der Kunde.
     * @return Alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     *         Liefert eine leere Liste, wenn der Kunde aktuell nichts
     *         ausgeliehen hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Medium> getAusgelieheneMedienFuer(Kunde kunde);

    /**
     * @return Eine Listenkopie aller Verleihkarten. FÃ¼r jedes ausgeliehene
     *         Medium existiert eine Verleihkarte. Ist kein Medium verliehen,
     *         wird eine leere Liste zurÃ¼ckgegeben.
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkarten();

    /**
     * Nimmt zuvor ausgeliehene Medien zurÃ¼ck. Die entsprechenden Verleihkarten
     * werden gelÃ¶scht.
     * 
     * 
     * @param medien Die Medien.
     * @param rueckgabeDatum Das RÃ¼ckgabedatum.
     * 
     * @require sindAlleVerliehen(medien)
     * @require rueckgabeDatum != null
     * 
     * @ensure sindAlleNichtVerliehen(medien)
     */
    void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException;

    /**
    * gibt eine Liste aus vormerkerkarten
    * 
    *            
    * @return List, die alle VormerkerKarten beiinhaltet, die in Map gespeichert wurden
    * 
    * @ensure result != null
    */
    public List<VormerkerKarte> getVormerkerkarten();

    /**
     * PrÃ¼ft ob das angegebene Medium verliehen ist.
     * 
     * @param medium Ein Medium, fÃ¼r das geprÃ¼ft werden soll ob es verliehen
     *            ist.
     * @return true, wenn das gegebene medium verliehen ist, sonst false.
     * 
     * @require mediumImBestand(medium)
     */
    boolean istVerliehen(Medium medium);

    /**
     * PrÃ¼ft ob alle angegebenen Medien nicht verliehen sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien nicht verliehen sind, sonst
     *         false.
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleNichtVerliehen(List<Medium> medien);

    /**
     * PrÃ¼ft ob alle angegebenen Medien verliehen sind.
     * 
     * @param medien Eine Liste von Medien.
     * 
     * @return true, wenn alle gegebenen Medien verliehen sind, sonst false.
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleVerliehen(List<Medium> medien);

    /**
     * PrÃ¼ft, ob alle angegebenen Medien an einen bestimmten Kunden verliehen
     * sind.
     * 
     * @param kunde Ein Kunde
     * @param medien Eine Liste von Medien
     * @return true, wenn alle Medien an den Kunden verliehen sind, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    boolean sindAlleVerliehenAn(Kunde kunde, List<Medium> medien);

    /**
     * PrÃ¼ft, ob ein Medium an einen bestimmten Kunden verliehen ist.
     * 
     * @param kunde Ein Kunde
     * @param medium Ein Medium
     * @return true, wenn das Medium an den Kunden verliehen ist, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require mediumImBestand(medium)
     */
    boolean istVerliehenAn(Kunde kunde, Medium medium);

    /**
     * PrÃ¼ft ob der angebene Kunde existiert. Ein Kunde existiert, wenn er im
     * Kundenstamm enthalten ist.
     * 
     * @param kunde Ein Kunde.
     * @return true wenn der Kunde existiert, sonst false.
     * 
     * @require kunde != null
     */
    boolean kundeImBestand(Kunde kunde);

    /**
     * PrÃ¼ft ob das angebene Medium existiert. Ein Medium existiert, wenn es im
     * Medienbestand enthalten ist.
     * 
     * @param medium Ein Medium.
     * @return true wenn das Medium existiert, sonst false.
     * 
     * @require medium != null
     */
    boolean mediumImBestand(Medium medium);

    /**
     * PrÃ¼ft ob die angebenen Medien existierien. Ein Medium existiert, wenn es
     * im Medienbestand enthalten ist.
     * 
     * @param medien Eine Liste von Medien.
     * @return true wenn die Medien existieren, sonst false.
     * 
     * @require medien != null
     * @require !medien.isEmpty()
     */
    boolean medienImBestand(List<Medium> medien);

    /**
     * Gibt alle Verleihkarten fÃ¼r den angegebenen Kunden zurÃ¼ck.
     * 
     * @param kunde Ein Kunde.
     * @return Alle Verleihkarten des angebenen Kunden. Eine leere Liste, wenn
     *         der Kunde nichts entliehen hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkartenFuer(Kunde kunde);

    /**
     * Gibt die Verleihkarte fÃ¼r das angegebene Medium zurÃ¼ck, oder null wenn
     * das Medium nicht verliehen ist.
     * 
     * @param medium Ein Medium.
     * @return Die Verleihkarte fÃ¼r das angegebene Medium.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure	 (result != null)
     */
    Verleihkarte getVerleihkarteFuer(Medium medium);

    /**
     * gibt eine VormerkerKarte fÃ¼r ein Medium zurÃ¼ck
     * 
     * @param medium Ein Medium.
     * @return Die VormerkerKarte fuer das angegebene Medium.
     * 
     * @require hatVormerkerKarte(medium)
     * 
     * @ensure	 (result != null)
     */

    VormerkerKarte getVormerkerKarteFuer(Medium medium);

    /**
    * Ã¼berpruft ob ein Medium als Key in Map existiert
    * 
    * @param medium Ein Medium.
    * @return boolean ob das Medium existiert.
    * 
    * @require mediumImBestand(medium)
    * 
    *
    */

    /**
     * Ã¼berprÃ¼ft ob ein Medieum eine vormerkerkarte hat
     * 
     * @param medium Ein Medium
     * @return true wenn das Medium bereits eine vormerkerkarte hat, false wenn das Medium keien vormerkerkarte hat
     * 
     * @require mediumImBestand(medium);
     */
    boolean hatVormerkerKarte(Medium medium);

}
