
public class Videospiel implements Medium
{
    private String _titel;
    private String _kommentar;
    private String _system;

    public Videospiel(String titel, String kommentar, String system)
    {
        assert titel != null : "Vorbedingung verletzt: titel != null";
        assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
        assert system != null : "Vorbedingung verletzt: system != null";

        _titel = titel;
        _kommentar = kommentar;
        _system = system;

    }

    @Override
    public String getKommentar()
    {
        // TODO Auto-generated method stub
        return _kommentar;
    }

    @Override
    public String getMedienBezeichnung()
    {
        // TODO Auto-generated method stub
        return "Videospiel";
    }

    @Override
    public String getTitel()
    {
        // TODO Auto-generated method stub
        return _titel;
    }

    public String getSystem()
    {
        return _system;
    }

    @Override
    public String getFormatiertenString()
    {
        return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + _titel
                + "\n" + "    " + "Kommentar: " + _kommentar + "\n" + "    "
                + "System: " + _system + "\n";
    }
}
