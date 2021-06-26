package mediathek.medien;

import mediathek.fachwerte.Geldbetrag;

/**
 * Ein Medium definiert Eigenschaften, die alle Medien unserer Mediathek
 * gemeinsam haben. Der Titel eines Mediums dient als eindeutige Identifikation.
 * Ein Medium kann ausgeliehen und zurÃ¼ckgegeben werden.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public interface Medium
{

    /**
    * Berechnet die MietgebÃ¼hr in Eurocent fÂ¨ur eine angegebene Mietdauer
    * in Tagen
    *
    * @param mietTage
    * Die Anzahl der Ausleihtage eines Mediums
    * @return Die Mietgeb Â¨uhr in Eurocent als Geldbetrag
    *
    * @require mietTage > 0
    *
    * @ensure result != null
    */
    Geldbetrag berechneMietgebuehr(int mietTage);

    /**
     * Gibt einen formatierten Text mit allen Eigenschaften des Mediums zurÃ¼ck.
     * Jedes Attribute steht in einer eigenen Zeile mit der Form "Attributename:
     * Attributwert". Hinweis: Ein Zeilenumbruch wird durch den Character '\n'
     * dargestellt.
     * 
     * @return Eine TextreprÃ¤sentation des Mediums.
     * 
     * @ensure result != null
     */
    String getFormatiertenString();

    /**
     * Gibt den Kommentar zu diesem Medium zurÃ¼ck.
     * 
     * @return Den Kommentar zu diesem Medium.
     * 
     * @ensure result != null
     */
    String getKommentar();

    /**
     * Ã„ndert den Kommentar des Mediums
     * 
     * @param kommentar Ein Kommentar zu diesem Medium
     * 
     * @require kommentar != null
     * @ensure getKommentar() == kommentar
     */
    public void setKommentar(String kommentar);

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
     * Ã„ndert den Titel des Mediums.
     * 
     * @param titel Der Titel des Mediums
     * 
     * @require titel != null
     * @ensure getTitel() == titel
     */
    public void setTitel(String titel);

}
