import java.io.IOException;

/**
 * Eine DateiLeseException signalisiert, dass das Lesen aus einer Datei nicht
 * geklappt hat.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class DateiLeseException extends IOException
{

    private static final long serialVersionUID = 1L;

    /**
     * Initialisiert eine neue DateiLeseException mit der Ã¼bergebenen
     * Fehlermeldung.
     * 
     * @param message Eine beschreibung des Fehlers.
     * 
     */
    public DateiLeseException(String message)
    {
        super(message);
    }
}
