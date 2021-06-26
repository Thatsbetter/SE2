package mediathek.medien;

import mediathek.fachwerte.Geldbetrag;

/**
 * {@link Videospiel} ist ein {@link Medium} mit einer zusÃ¤tzlichen
 * Informationen zum kompatiblen System.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class Videospiel extends AbstractMedium
{
    /**
     * Das System, auf dem das Spiel lauffÃ¤hig ist
     */
    private String _system;

    /**
     * Initialisiert ein neues Videospiel.
     * 
     * @param titel Der Titel des Spiels
     * @param kommentar Ein Kommentar zum Spiel
     * @param system Die Bezeichnung des System
     * 
     * @require titel != null
     * @require kommentar != null
     * @require system != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getSystem() == system
     */
    public Videospiel(String titel, String kommentar, String system)
    {
        super(titel, kommentar);
        assert system != null : "Vorbedingung verletzt: system != null";

        _system = system;
    }

    @Override
    public String getMedienBezeichnung()
    {
        return "Videospiel";
    }

    /**
     * Gibt das System zurÃ¼ck, auf dem das Spiel lauffÃ¤hig ist.
     * 
     * @return Das System, auf dem das Spiel ausgefÃ¼hrt werden kann.
     * 
     * @ensure result != null
     */
    public String getSystem()
    {
        return _system;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }

    /**
     * Ã„ndert den Kommentar
     * 
     * @param kommentar Ein Kommentar zum Medium
     * 
     * @require kommentar != null
     * @ensure getKommentar() == kommentar
     */

    /**
     * Ã„ndert den Titel
     * 
     * @param titel Der Titel des Mediums
     * 
     * @require titel != null
     * @ensure getTitel() == titel
     */

    @Override
    public String getFormatiertenString()
    {

        return super.getFormatiertenString() + "    " + "System: " + _system
                + "\n";
    }

    @Override
    public Geldbetrag berechneMietgebuehr(int mietTage)
    {
        assert mietTage > 0 : "Vorbedigung verletzt mietTage >0";
        Geldbetrag gebuehr = new Geldbetrag(200);
        return gebuehr;
    }
}
