package Bank;

public class Bank
{
	private Girokonto _girokonto;
	private int _sollZinsen;
	
	private Sparbuch _sparbuch;
	private int _habenZinsen;
	
	public Bank()
	{
		_sollZinsen = 5;
		_girokonto = new Girokonto(300, _sollZinsen);
		
		new GiroKunde(_girokonto);
		
		_girokonto.macheJahresabschluss();
		
		
		_habenZinsen = 3;
		_sparbuch = new Sparbuch(_habenZinsen);
		
		new SparKunde(_sparbuch);
		
		_sparbuch.macheJahresabschluss();
	}
}
