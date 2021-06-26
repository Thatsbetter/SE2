package mediathek.werkzeuge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import mediathek.medien.Medium;

/**
 * Ein AusleiheMedienTableModel hÃ¤lt Medien, und gibt fÃ¼r jede Spalte im
 * {@link AusleihWerkzeug} die dort benÃ¶tigte Information Ã¼ber das {@link Medium} in einer Zeile
 * zurÃ¼ck.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class AusleiheMedienTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = new String[] {"Medientyp",
            "Titel", "ausleihbar"};

    /**
     * Eine sortierte Map, die Medien zwischenspeichert/cached und auf boolsche
     * Werte abbildet, die angeben, ob ein {@link Medium}  entliehe ist (true) oder nicht
     * (false).
     */
    private List<AusleiheMedienFormatierer> _medienListe;

    /**
     * Initialisiert ein AusleiheMedienTableModel.
     */
    public AusleiheMedienTableModel()
    {
        _medienListe = new ArrayList<AusleiheMedienFormatierer>();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount()
    {
        return _medienListe.size();
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        AusleiheMedienFormatierer formatierer = getMedienFormatierer(row);
        Object ergebnis = null;
        switch (column)
        {
        case 0:
            ergebnis = formatierer.getMedienBezeichnung();
            break;
        case 1:
            ergebnis = formatierer.getTitel();
            break;
        case 2:
            ergebnis = formatierer.getAusleihstatus();
        }
        return ergebnis;
    }

    /**
     * Liefert ein Medium, das in der Zeile mit einer gegebenen Nummer
     * dargestellt wird.
     * 
     * @param zeile Eine Nummer einer Tabellenzeile
     * 
     * @require zeileExistiert(zeile)
     */
    public Medium getMediumFuerZeile(int zeile)
    {
        assert zeileExistiert(zeile) : "Vorbedingung verletzt: zeileExistiert(zeile)";
        return getMedienFormatierer(zeile).getMedium();
    }

    /**
     * PrÃ¼ft, ob fÃ¼r die gegebene Tabellen-Zeile ein Medium in dem TableModel
     * existiert.
     * 
     * @param zeile Die Nummer der Tabellenzeile
     */
    public boolean zeileExistiert(int zeile)
    {
        boolean result = false;
        if ((zeile < _medienListe.size()) && (zeile >= 0))
        {
            result = true;
        }
        return result;
    }

    /**
     * Setze die anzuzeigenden Medien. Nach dem Setzen wird die Tabelle
     * aktualisiert. Es wird auf einer Kopie der Liste gearbeitet.
     * 
     * @require medien != null
     */
    public void setMedien(List<AusleiheMedienFormatierer> medien)
    {
        assert medien != null : "Vorbedingung verletzt: medien != null";
        _medienListe = new ArrayList<AusleiheMedienFormatierer>(medien);

        // sortiere Medien-Liste
        Collections.sort(_medienListe,
                new AusleiheMedienFormatiererComparator());

        fireTableDataChanged();
    }

    /**
     * Liefert den Medien-Formatierer fÃ¼r einen angegebenen Index. Die
     * GÃ¼ltigkeit des Index wird nicht Ã¼berprÃ¼ft.
     * 
     * @param index Der Index eines Medien-Formatierers in der Medien-Liste.
     * @return Der Medien-Formatierer auf dem angegebenen Index.
     */
    private AusleiheMedienFormatierer getMedienFormatierer(int index)
    {
        return _medienListe.get(index);
    }

}
