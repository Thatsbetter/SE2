package mediathek.services;

/**
 * Eine ProtokollierException signalisiert, dass das Protokollieren eines
 * Verleihvorgangs fehlgeschlagen ist.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class ProtokollierException extends Exception
{

    private static final long serialVersionUID = 1L;

    /**
     * Initilaisert eine neue Exception mit der Ã¼bergebenen Fehlermeldung.
     * 
     * @param message Eine Fehlerbeschreibung.
     */
    public ProtokollierException()
    {
        super("Protokollieren fehlgeschlagen!");
    }
}
