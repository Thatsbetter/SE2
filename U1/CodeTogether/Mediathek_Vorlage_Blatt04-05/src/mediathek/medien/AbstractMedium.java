package mediathek.medien;

import mediathek.fachwerte.Geldbetrag;

public abstract class AbstractMedium implements Medium
{
    protected String _titel;
    protected String _kommentar;

    protected AbstractMedium(String titel, String kommentar)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";

        _titel = titel;
        _kommentar = kommentar;

    }

    public abstract String getMedienBezeichnung();

    public void setTitel(String titel)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        _titel = titel;
    }

    public String getTitel()
    {
        return _titel;
    }

    public void setKommentar(String kommentar)
    {
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        _kommentar = kommentar;
    }

    public String getKommentar()
    {
        return _kommentar;
    }

    public String getFormatiertenString()
    {
        return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + _titel
                + "\n" + "    " + "Kommentar: " + _kommentar + "\n";
    }

    public Geldbetrag berechneMietgebuehr(int mietTage)
    {
        assert mietTage > 0 : "Vorbedingung verletzt = mietTage > 0";

        Geldbetrag gebuehr = new Geldbetrag(mietTage * 300);

        return gebuehr;
    }
}
