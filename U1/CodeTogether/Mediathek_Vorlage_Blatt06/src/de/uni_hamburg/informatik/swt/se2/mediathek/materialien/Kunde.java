package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.PLZ;

/**
 * Diese Klasse verfÃ¼gt Ã¼ber alle benÃ¶tigten Informationen zu einem Kunden.
 * Jeder Kunde hat mindestens einen Vornamen, einen Nachnamen und eine
 * Kundennummer.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class Kunde
{
    // 4 Leerzeichen fÃ¼r formatierte Strings
    private static final String SPACE = "    ";

    // Zustandsfelder fÃ¼r Kundendaten
    private Kundennummer _kundennummer;
    private String _nachname;
    private PLZ _plz;
    private String _strasse;
    private String _telefonnummer;
    private String _vorname;
    private String _wohnort;

    /**
     * Konstruktor fÃ¼r einen Kunden.
     * 
     * @param kundennummer Eine Kundennummer.
     * @param vorname Ein Vorname des Kunden.
     * @param nachname Ein Nachname des Kunden.
     * 
     * @require kundennummer != null
     * @require vorname != null
     * @require nachname != null
     * 
     * @ensure getKundennummer() == kundennummer
     * @ensure getVorname() == vorname
     * @ensure getNachname() == nachname
     */
    public Kunde(Kundennummer kundennummer, String vorname, String nachname)
    {
        assert kundennummer != null : "Vorbedingung verletzt: kundennummer != null";
        assert vorname != null : "Vorbedingung verletzt: vorname != null";
        assert nachname != null : "Vorbedingung verletzt: nachname != null";

        _vorname = vorname;
        _nachname = nachname;
        _kundennummer = kundennummer;

    }

    /**
     * Gibt einen String mit allen Informationen zum Kunden zurÃ¼ck.
     * 
     * @return einen formatierten String mit allen Informationen zum Kunden.
     *         EnthÃ¤lt ZeilenumbrÃ¼che.
     */
    public String getFormatiertenString()
    {
        String result = SPACE + "Kundennummer: " + getKundennummer().toString()
                + "\n";
        result += SPACE + "Name: " + getVorname() + " " + getNachname() + "\n";
        result += SPACE + "Telefon: " + getTelefonnummer() + "\n";
        result += SPACE + "Anschrift:\n" + getAnschrift() + "\n";
        return result;
    }

    /**
     * Gibt die Kundenummer zurÃ¼ck.
     * 
     * @ensure result != null
     */
    public Kundennummer getKundennummer()
    {
        return _kundennummer;
    }

    /**
     * Gibt den Nachnamen zurÃ¼ck.
     * 
     * @return Den Nachnamen.
     * 
     * @ensure result != null
     */
    public String getNachname()
    {
        return _nachname;
    }

    /**
     * Gibt die Postleitzahl zurÃ¼ck.
     * 
     * @return Die Postleitzahl, kann null sein.
     */
    public PLZ getPLZ()
    {
        return _plz;
    }

    /**
     * Gibt die StraÃŸe in der der Kunde wohn zurÃ¼ck.
     * 
     * @return Die StraÃŸe, kann null sein.
     */
    public String getStrasse()
    {
        return _strasse;
    }

    /**
     * Gibt die Telefonnummer des Kunden zurÃ¼ck.
     * 
     * @return Die Telefonnummer, liefert "unbekannt" wenn die Telefonnummer
     *         unbekannt ist.
     * 
     * @ensure result != null
     */
    public String getTelefonnummer()
    {
        String ergebnisText = _telefonnummer;
        if (ergebnisText == null)
        {
            ergebnisText = "unbekannt";
        }
        return ergebnisText;
    }

    /**
     * Gibt den Vornamen zurÃ¼ck.
     * 
     * @return Den Vornamen.
     * 
     * @ensure result != null
     */
    public String getVorname()
    {
        return _vorname;
    }

    /**
     * Gibt den Wohnort zurÃ¼ck.
     * 
     * @return Den Wohnort.
     */
    public String getWohnort()
    {
        return _wohnort;
    }

    /**
     * Setzt eine neue Postleitzahl.
     * 
     * @param plz Die zu setzende Postleitzahl, darf null sein.
     */
    public void setPLZ(PLZ plz)
    {
        _plz = plz;
    }

    /**
     * Setzt eine neue StraÃŸe.
     * 
     * @param strasse Die zu setzende StraÃŸe, darf null sein.
     */
    public void setStrasse(String strasse)
    {
        _strasse = strasse;
    }

    /**
     * Setzt eine neue Telefonnummer
     * 
     * @param telefonnummer Die zu setzende Telefonnummer, darf null sein.
     */
    public void setTelefonnummer(String telefonnummer)
    {
        _telefonnummer = telefonnummer;
    }

    /**
     * Setzt einen neuen Wohnort
     * 
     * @param wohnort Der zu setzende Wohnort, darf null sein.
     */
    public void setWohnort(String wohnort)
    {
        _wohnort = wohnort;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }

    /**
     * Gibt eine String-Darstellung der Anschrift des Kundens (ohne
     * ZeilenumbrÃ¼che) zurÃ¼ck.
     * 
     * @return Eine String-ReprÃ¤sentation der Anschrift ohne ZeilenumbrÃ¼che.
     * 
     * @ensure result != null
     */
    private String getAnschrift()
    {
        String result = "";
        if ((getStrasse() != null))
        {
            result += SPACE + getStrasse() + "\n";
        }
        if (getPLZ() != null)
        {
            result += SPACE + getPLZ() + " ";
        }
        if (getWohnort() != null)
        {
            result += getWohnort();
        }
        if (result.length() == 0)
        {
            result += SPACE + "unbekannt";
        }
        return result;
    }
}
