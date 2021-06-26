package mediathek.services;

import java.util.List;

import mediathek.medien.Medium;

/**
 * Ein Medienbestand-Service ist ein Service, der alle zur VerfÃ¼gung stehenden
 * Medien enthÃ¤lt. Diese kÃ¶nnen in den Bestand eingepflegt, gelÃ¶scht und
 * verÃ¤ndert werden. Zu einem bestimmten Titel kann es mehrere Medien-Objekte im
 * Bestand geben. So kann z.B. die gleiche CD mehrfach vorhanden sein.
 * 
 * MedienbestandService ist ein ObservableService, als solcher bietet er die
 * MÃ¶glichkeit Ã¼ber Ã„nderungen am Medienbestand zu informieren. Beobachter
 * mÃ¼ssen das Interface ServiceObserver implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface MedienbestandService extends ObservableService
{

    /**
     * Entfernt ein Medium aus dem Medienbestand, z.B. wenn es verloren gegangen
     * ist oder so veraltet, dass es von den Mediathek-Kunden nicht mehr
     * nachgefragt wird.
     * 
     * @param medium Ein zu entfernendes Medium
     * 
     * @require enthaeltMedium(medium)
     * @ensure !enthaeltMedium(medium)
     */
    void entferneMedium(Medium medium);

    /**
     * Gibt Auskunft, ob ein Medium im Medienbestand enthalten ist.
     * 
     * @param medium Ein Medium
     * @return true, wenn Medium im Medienbestand enthalten ist, andernfalls
     *         false.
     * 
     * @require medium != null
     */
    boolean enthaeltMedium(Medium medium);

    /**
     * FÃ¼gt ein weiteres, neu angeschafftes Medium in den Bestand ein. Jedes
     * Exemplar im Bestand reprÃ¤sentiert ein real existierendes Medium. Ist ein
     * Medium mehrfach angeschafft worden, so muss ein weiteres Exemplar mit
     * denselben Eigenschaften eingepflegt werden.
     * 
     * @param neuesMedium Ein neues Medium
     * 
     * @require !enthaeltMedium(neuesMedium)
     * @ensure enthaeltMedium(neuesMedium)
     */
    void fuegeMediumEin(Medium neuesMedium);

    /**
     * Liefert alle vorhandenen Medien.
     * 
     * @return Eine Kopie der Liste mit allen vorhandenen Medien.
     * 
     * @ensure result != null
     */
    List<Medium> getMedien();

    /**
     * Informiert diesen Service darÃ¼ber, dass Medien von einem Werkzeug
     * geÃ¤ndert wurden. Eine Implementation wird daraufhin wahrscheinlich alle
     * ServiceBeobachter darÃ¼ber informieren.
     */
    void medienWurdenGeaendert();

}
