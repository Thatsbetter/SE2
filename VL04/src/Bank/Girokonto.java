package Bank;

public class Girokonto
{
	public void drucke(int rohsaldo, double zinsen)
	{
		String jahresabschluss = "Für das Girokonto war der Kontostand: " + (rohsaldo + zinsen)
				+ "\nDabei betrug der Saldo aus Ein- und Auszahlungen: " + rohsaldo
				+ "\nEs sind folgende Sollzinsen angefallen: " + zinsen;
		
		System.out.println(jahresabschluss);
	}
}
