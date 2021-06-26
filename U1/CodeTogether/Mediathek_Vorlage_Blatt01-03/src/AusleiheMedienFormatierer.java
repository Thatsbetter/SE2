/**
 * Der Formatierer fÃ¼r Medien in der Ausleiheansicht.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class AusleiheMedienFormatierer
{

    private final Medium _medium;

    private final boolean _istVerliehen;

    /**
     * Initialisiert einen neuen AusleiheMedienformatierer fÃ¼r das angegebene
     * Medium.
     * 
     * @param medium Das adaptierte Medium.
     * @param istVerliehen Der Verleihstatus des Mediums.
     * 
     * @require medium != null
     */
    public AusleiheMedienFormatierer(Medium medium, boolean istVerliehen)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _medium = medium;
        _istVerliehen = istVerliehen;
    }

    /**
     * Gibt das Medium des Formatieres zurÃ¼ck.
     * 
     * @return Das Medium des Formatieres.
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Gibt die Medienbezeichnung zurÃ¼ck.
     */
    public String getMedienBezeichnung()
    {
        return _medium.getMedienBezeichnung();
    }

    /**
     * Gibt den Medientitel zurÃ¼ck.
     */
    public String getTitel()
    {
        return _medium.getTitel();
    }

    /**
     * Gibt den Ausleihstatus an.
     */
    public String getAusleihstatus()
    {
        String result = "ja";
        if (_istVerliehen)
        {
            result = "nein";
        }
        return result;
    }

}
