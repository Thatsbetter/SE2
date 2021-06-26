package mediathek.medien;

import mediathek.fachwerte.Geldbetrag;

public abstract class AbstractMedium implements Medium
{
    protected String _kommentar;

    protected String _titel;

    public AbstractMedium(String titel, String kommentar)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        this._kommentar = kommentar;
        this._titel = titel;

    }

    public String getKommentar()
    {
        return _kommentar;
    }

    public void setKommentar(String kommentar)
    {
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        _kommentar = kommentar;
    }

    public String getTitel()
    {
        return _titel;
    }

    public void setTitel(String titel)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        _titel = titel;
    }

    public String getFormatiertenString()
    {
        return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + _titel
                + "\n" + "    " + "Kommentar: " + _kommentar + "\n" + "    ";
    }

    abstract public String getMedienBezeichnung();

    public Geldbetrag berechneMietgebuehr(int mietTage)
    {
        assert mietTage > 0;

        int mietGebuehr = mietTage * 300;
        Geldbetrag gebuehr = new Geldbetrag(mietGebuehr);

        return gebuehr;
    }

}
