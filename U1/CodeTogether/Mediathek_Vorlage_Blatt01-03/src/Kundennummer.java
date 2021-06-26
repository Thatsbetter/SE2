/**
 * Mit Kundennummern kÃ¶nnen Kunden eindeutig indentifiziert werden. Jede
 * Kundennummer ist 6 Zeichen lang.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
public final class Kundennummer
{
    /**
     * int-ReprÃ¤sentation der Kundenummer
     */
    private final int _kundennummer;

    /**
     * WÃ¤hlt eine Kundennummer, mit der angebenen Zahl, aus.
     * 
     * @param kundennummer Eine gÃ¼ltige Zahl.
     * 
     * @require istGueltig(kundennummer)
     */
    public Kundennummer(int kundennummer)
    {
        assert istGueltig(
                kundennummer) : "Vorbedingung verletzt: istGueltig(kundennummer)";

        _kundennummer = kundennummer;
    }

    /**
     * PrÃ¼ft, ob eine gegebene Zahl eine gÃ¼ltige Kundennummer ist. Eine
     * Kundennummer ist gÃ¼ltig, wenn sie 6-stellig (dezimal) ist. Der verwendete
     * RegulÃ¤re Ausdruck zur ÃœberprÃ¼fung lautet:"[0-9]{6}"
     * 
     * @param kundennummer Ein zu Ã¼beprÃ¼fende Zahl.
     * @return true, wenn der RegulÃ¤re Ausdruck passt, ansonsten false.
     */
    public static boolean istGueltig(int kundennummer)
    {
        return String.valueOf(kundennummer)
            .matches("[0-9]{6}");
    }

    /**
     * Zwei Kundennummern sind gleich wenn ihre Zahlenkombinationen gleich sind.
     * 
     * @param obj Ein anderes Objekt.
     * @return true, wenn die die Zahlenkombinationen gleich sind, ansonsten
     *         false.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Kundennummer)
        {
            Kundennummer vergleichsKundennummer = (Kundennummer) obj;
            result = (_kundennummer == vergleichsKundennummer._kundennummer);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        return _kundennummer;
    }

    @Override
    public String toString()
    {
        return String.valueOf(_kundennummer);
    }

}
