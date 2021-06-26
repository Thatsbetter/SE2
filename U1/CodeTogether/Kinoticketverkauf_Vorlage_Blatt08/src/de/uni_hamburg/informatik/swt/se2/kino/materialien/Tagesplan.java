package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;

/**
 * Ein Tagesplan verzeichnet alle Vorstellungen, die in dem Kino an einem
 * bestimmten Tag laufen.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public class Tagesplan
{
    private Datum _tag;
    private List<Vorstellung> _vorstellungen;

    // Der Comparator wird zur Sortierung der Vorstellungen innerhalb eines
    // Tagesplans verwendet.
    private static Comparator<Vorstellung> _vergleichNachAnfangszeit = new Comparator<Vorstellung>()
    {
        @Override
        public int compare(Vorstellung v1, Vorstellung v2)
        {
            assert v1 != null : "Vorbedingung verletzt: v1 != null";
            assert v2 != null : "Vorbedingung verletzt: v2 != null";

            // Der Vergleich der Anfangszeiten reicht hier aus, weil in
            // einem Tagesplan sowieso nur Vorstellungen mit gleichem Datum
            // laufen.
            return v1.getAnfangszeit().compareTo(v2.getAnfangszeit());
        }
    };

    /**
     * Initialisiert einen neuen, leeren Tagesplan.
     * 
     * @param tag
     *            der Tag.
     * 
     * @require tag != null
     */
    public Tagesplan(Datum tag)
    {
        assert tag != null : "Vorbedingung verletzt: tag != null";

        _tag = tag;
        _vorstellungen = new ArrayList<Vorstellung>();
    }

    /**
     * Gibt das Datum zurÃ¼ck, fÃ¼r das dieser Tagesplan gilt.
     * 
     * @ensure result != null
     */
    public Datum getDatum()
    {
        return _tag;
    }

    /**
     * FÃ¼gt diesem Tagesplan eine Vorstellung hinzu.
     * 
     * @param v
     *            die Vorstellung.
     * 
     * @require v != null
     * @require die Vorstellung laeuft an dem Tag dieses Tagesplans
     */
    public void fuegeVorstellungHinzu(Vorstellung v)
    {
        assert v != null : "Vorbedingung verletzt: v != null";
        assert v.getDatum().equals(_tag) : "Vorbedingung verletzt: v.getDatum().equals(_tag)";

        _vorstellungen.add(v);
    }

    /**
     * Gibt alle Vorstellungen des Tages zurÃ¼ck. Die Vorstellungen werden
     * sortiert nach ihrer Anfangszeit zurÃ¼ckgegeben.
     * 
     * @ensure result != null
     */
    public List<Vorstellung> getVorstellungen()
    {
        List<Vorstellung> result = new ArrayList<Vorstellung>(_vorstellungen);
        Collections.sort(result, _vergleichNachAnfangszeit);
        return result;
    }
}
