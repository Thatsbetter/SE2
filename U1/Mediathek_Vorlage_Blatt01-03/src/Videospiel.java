
class Videospiel implements Medium
{
    private String _titel;
    private String _kommentar;
    private String _system;

    public Videospiel(String titel, String kommentar, String system)
    {
        _system = system;
        _titel = titel;
        _kommentar = kommentar;

    }

    @Override
    public String getKommentar()
    {
        return _kommentar;
    }

    @Override
    public String getMedienBezeichnung()
    {
        return "Videospiel";
    }

    public String getTitel()
    {
        return _titel;
    }

    public String getSystem()
    {
        return _system;
    }

    @Override
    public String getFormatiertenString()
    {
        return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + getTitel()
                + "\n" + "    " + "Kommentar: " + getKommentar() + "\n" + "    "
                + "System: " + _system + "\n";
    }

}
