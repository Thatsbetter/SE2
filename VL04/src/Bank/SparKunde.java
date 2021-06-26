package Bank;

public class SparKunde
{
	public SparKunde(Sparbuch sparbuch)
	{
		sparbuch.zahleEin(200);
		sparbuch.zahleEin(1000);
		sparbuch.hebeAb(100);
		sparbuch.zahleEin(500);
	}
}
