/**
 * Ein Medium definiert Eigenschaften, die alle Medien unserer Mediathek
 * gemeinsam haben. Der Titel eines Mediums dient als eindeutige Identifikation.
 * Ein Medium kann ausgeliehen und zurÃ¼ckgegeben werden.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
interface Medium
{

    /**
     * Gibt den Kommentar zu diesem Medium zurÃ¼ck.
     * 
     * @return Den Kommentar zu diesem Medium.
     * 
     * @ensure result != null
     */
    String getKommentar();

    /**
     * Gibt die Bezeichnung fÃ¼r die Medienart zurÃ¼ck.
     * 
     * @return Die Bezeichnung fÃ¼r die Medienart.
     * 
     * @ensure result != null
     */
    String getMedienBezeichnung();

    /**
     * Gibt den Titel des Mediums zurÃ¼ck.
     * 
     * @return Den Titel des Mediums
     * 
     * @ensure result != null
     */
    String getTitel();

    /**
     * Gibt den formatierte String des entsprechenden Mediums
     * 
     * @return Der formatierte String des entsprechenden Mediums
     * 
     * @ensure result != null
     */
    String getFormatiertenString();

}
