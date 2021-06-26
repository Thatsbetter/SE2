package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.Set;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;

/**
 * Eine Vorstellung, fÃ¼r die PlÃ¤tze verkauft und storniert werden kÃ¶nnen. Die
 * Vorstellung speichert zum einen die Daten der eigentlichen Vorstellung (wann
 * und wo lÃ¤uft welcher Film) und zum anderen, welche PlÃ¤tze fÃ¼r diese
 * Vorstellung bereits verkauft wurden.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class Vorstellung
{
    private Kinosaal _kinosaal;
    private Film _film;
    private Uhrzeit _anfangszeit;
    private Uhrzeit _endzeit;
    private Datum _datum;
    private int _preis;
    private boolean[][] _verkauft;
    private int _anzahlVerkauftePlaetze;

    /**
     * Erstellt eine neue Vorstellung.
     * 
     * @param kinosaal der Kinosaal, in dem die Vorstellung laeuft.
     * @param film der Film, der in dieser Vorstellung gezeigt wird.
     * @param anfangszeit die Anfangszeit der Vorstellung.
     * @param endzeit die Endzeit der Vorstellung.
     * @param preis der Verkaufspreis in Eurocent fÃ¼r Karten zu dieser
     *            Vorstellung.
     * 
     * @require kinosaal != null
     * @require film != null
     * @require anfangszeit != null
     * @require endzeit != null
     * @require datum != null
     * @require preis > 0
     * 
     * @ensure getKinosaal() == kinosaal
     * @ensure getFilm() == film
     * @ensure getAnfangszeit() == anfangszeit
     * @ensure getEndzeit() == endzeit
     * @ensure getDatum() == datum
     * @ensure getPreis() == preis
     */
    public Vorstellung(Kinosaal kinosaal, Film film, Uhrzeit anfangszeit,
            Uhrzeit endzeit, Datum datum, int preis)
    {
        assert kinosaal != null : "Vorbedingung verletzt: saal != null";
        assert film != null : "Vorbedingung verletzt: film != null";
        assert anfangszeit != null : "Vorbedingung verletzt: anfangszeit != null";
        assert endzeit != null : "Vorbedingung verletzt: endzeit != null";
        assert datum != null : "Vorbedingung verletzt: datum != null";
        assert preis > 0: "Vorbedingung verletzt: preis > 0";

        _kinosaal = kinosaal;
        _film = film;
        _anfangszeit = anfangszeit;
        _endzeit = endzeit;
        _datum = datum;
        _preis = preis;
        _verkauft = new boolean[kinosaal.getAnzahlReihen()][kinosaal
                .getAnzahlSitzeProReihe()];
        _anzahlVerkauftePlaetze = 0;
    }

    /**
     * Gibt den Kinosaal zurÃ¼ck, in dem diese Vorstellung lÃ¤uft.
     * 
     * @ensure result != null
     */
    public Kinosaal getKinosaal()
    {
        return _kinosaal;
    }

    /**
     * Gibt den Film zurÃ¼ck, der in dieser Vorstellung gezeigt wird.
     * 
     * @ensure result != null
     */
    public Film getFilm()
    {
        return _film;
    }

    /**
     * Gibt die Uhrzeit zurÃ¼ck, zu der diese Vorstellung beginnt.
     * 
     * @ensure result != null
     */
    public Uhrzeit getAnfangszeit()
    {
        return _anfangszeit;
    }

    /**
     * Gibt die Uhrzeit zurÃ¼ck, zu der diese Vorstellung endet.
     * 
     * @ensure result != null
     */
    public Uhrzeit getEndzeit()
    {
        return _endzeit;
    }

    /**
     * Gibt das Datum zurÃ¼ck, an dem diese Vorstellung lÃ¤uft.
     * 
     * @ensure result != null
     */
    public Datum getDatum()
    {
        return _datum;
    }

    /**
     * Gibt den Verkaufspreis in Eurocent fÃ¼r Karten zu dieser Vorstellung
     * zurÃ¼ck.
     * 
     */
    public int getPreis()
    {
        return _preis;
    }

    /**
     * Gibt die Anzahl verkaufter PlÃ¤tze zurÃ¼ck.
     */
    public int getAnzahlVerkauftePlaetze()
    {
        return _anzahlVerkauftePlaetze;
    }

    /**
     * PrÃ¼ft, ob der angegebene Sitzplatz in dieser Vorstellung vorhanden ist.
     * 
     * @param platz der Sitzplatz.
     * 
     * @return <code>true</code>, falls der Platz existiert, <code>false</code>
     *         sonst.
     * 
     * @require platz != null
     */
    public boolean hatPlatz(Platz platz)
    {
        assert platz != null : "Vorbedingung verletzt: platz != null";

        return _kinosaal.hatPlatz(platz);
    }

    /**
     * PrÃ¼ft, ob alle angegebenen SitzplÃ¤tze in dieser Vorstellung vorhanden
     * sind.
     * 
     * @param plaetze die SitzplÃ¤tze.
     * 
     * @return true, falls alle PlÃ¤tze existieren, false sonst.
     * 
     * @require plaetze != null
     */
    public boolean hatPlaetze(Set<Platz> plaetze)
    {
        assert plaetze != null : "Vorbedingung verletzt: plaetze != null";

        boolean result = true;
        for (Platz p : plaetze)
        {
            result &= hatPlatz(p);
        }
        return result;
    }

    /**
     * Gibt den Gesamtpreis fÃ¼r die angegebenen PlÃ¤tze zurÃ¼cke
     * 
     * @param plaetze die SitzplÃ¤tze.
     * 
     * @return Gesamtpreis in Eurocent
     * 
     * @require hatPlaetze(plaetze)
     */
    public int getPreisFuerPlaetze(Set<Platz> plaetze)
    {
        assert hatPlaetze(plaetze) : "Vorbedingung verletzt: hatPlaetze(plaetze)";

        return _preis * plaetze.size();
    }

    /**
     * Verkauft einen Platz.
     * 
     * @param platz der Sitzplatz.
     * 
     * @require istVerkaufbar(platz)
     * 
     * @ensure !istVerkaufbar(platz)
     * @ensure istStornierbar(platz)
     */
    public void verkaufePlatz(Platz platz)
    {
        assert istVerkaufbar(platz) : "Vorbedingung verletzt: istVerkaufbar(platz)";

        _verkauft[platz.getReihe()][platz.getSitz()] = true;
        _anzahlVerkauftePlaetze++;
    }

    /**
     * Verkauft die gegebenen PlÃ¤tze.
     * 
     * @require sindVerkaufbar(plaetze)
     * 
     * @ensure !sindVerkaufbar(plaetze)
     * @ensure sindStornierbar(plaetze)
     */
    public void verkaufePlaetze(Set<Platz> plaetze)
    {
        assert sindVerkaufbar(plaetze) : "Vorbedingung verletzt: sindVerkaufbar(plaetze)";

        for (Platz platz : plaetze)
        {
            verkaufePlatz(platz);
        }
    }

    /**
     * PrÃ¼ft, ob ein gegebener Platz verkauft werden kann. DafÃ¼r wird geschaut,
     * ob der gegebene Platz bisher verkauft ist.
     * 
     * @return true, wenn ein PlÃ¤tze verkaufbar ist, sonst false.
     * 
     * @require hatPlatz(platz)
     */
    public boolean istVerkaufbar(Platz platz)
    {
        assert hatPlatz(platz) : "Vorbedingung verletzt: hatPlatz(platz)";

        return !istPlatzVerkauft(platz);
    }

    /**
     * PrÃ¼ft, ob gegebene PlÃ¤tze alle verkauft werden kÃ¶nnen. DafÃ¼r wird
     * geschaut, ob keiner der gegebenen PlÃ¤tze bisher verkauft ist.
     * 
     * @return true, wenn alle PlÃ¤tze verkaufbar sind, sonst false.
     * 
     * @require hatPlaetze(plaetze)
     */
    public boolean sindVerkaufbar(Set<Platz> plaetze)
    {
        assert plaetze != null : "Vorbedingung verletzt: plaetze != null";
        assert hatPlaetze(plaetze) : "Vorbedingung verletzt: hatPlaetze(plaetze)";

        boolean result = true;
        for (Platz platz : plaetze)
        {
            result &= istVerkaufbar(platz);
        }
        return result;
    }

    /**
     * Storniert einen Platz.
     * 
     * @param platz der Sitzplatz.
     * 
     * @require istStornierbar(platz)
     * 
     * @ensure !istStornierbar(platz)
     * @ensure istVerkaufbar(platz)
     */
    public void stornierePlatz(Platz platz)
    {
        assert istStornierbar(platz) : "Vorbedingung verletzt: istStornierbar(platz)";

        _verkauft[platz.getReihe()][platz.getSitz()] = false;
        _anzahlVerkauftePlaetze--;
    }

    /**
     * Storniert die gegebenen PlÃ¤tze.
     * 
     * @require sindStornierbar(plaetze)
     * 
     * @ensure !sindStornierbar(plaetze)
     * @ensure sindVerkaufbar(plaetze)
     */
    public void stornierePlaetze(Set<Platz> plaetze)
    {
        assert sindStornierbar(plaetze) : "Vorbedingung verletzt: sindStornierbar(plaetze)";

        for (Platz platz : plaetze)
        {
            stornierePlatz(platz);
        }
    }

    /**
     * PrÃ¼ft, ob ein gegebener Platz stornierbar ist. DafÃ¼r wird geschaut, ob
     * ein gegebener Platz verkauft ist.
     * 
     * @return true, wenn ein Platz stornierbar ist, sonst false.
     * 
     * @require hatPlaetze(plaetze)
     */
    public boolean istStornierbar(Platz platz)
    {
        assert hatPlatz(platz) : "Vorbedingung verletzt: hatPlatz(platz)";

        return istPlatzVerkauft(platz);
    }

    /**
     * PrÃ¼ft, ob die gegebenen PlÃ¤tze alle stornierbar sind. DafÃ¼r wird
     * geschaut, ob jeder gegebene Platz verkauft ist.
     * 
     * @return true, wenn alle PlÃ¤tze stornierbar sind, sonst false.
     * 
     * @require hatPlaetze(plaetze)
     */
    public boolean sindStornierbar(Set<Platz> plaetze)
    {
        assert hatPlaetze(plaetze) : "Vorbedingung verletzt: hatPlaetze(plaetze)";

        boolean result = true;
        for (Platz platz : plaetze)
        {
            result &= istStornierbar(platz);
        }
        return result;
    }

    @Override
    public String toString()
    {
        return "Vorstellung: " + _anfangszeit + ", " + _kinosaal + ", " + _film;
    }

    /**
     * Gibt an, ob ein bestimmter Platz bereits verkauft ist.
     * 
     * @param platz der Sitzplatz.
     * 
     * @return true, falls der Platz verkauft ist, false sonst.
     * 
     * @require hatPlatz(platz)
     */
    private boolean istPlatzVerkauft(Platz platz)
    {
        assert hatPlatz(platz) : "Vorbedingung verletzt: hatPlatz(platz)";

        return _verkauft[platz.getReihe()][platz.getSitz()];
    }
}
