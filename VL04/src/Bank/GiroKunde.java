package Bank;

public class GiroKunde
{
	public GiroKunde(Girokonto girokonto)
	{
		girokonto.zahleEin(1000);
		girokonto.hebeAb(200);
		girokonto.hebeAb(900);
		girokonto.zahleEin(1000);
	}
}
