package Bank;

import java.util.ArrayList;
import java.util.List;

public class Sparbuch
{
	private List<Integer> _kontobewegungen;
	private int _zinsSatz;
	
	public Sparbuch(int zinsSatz)
	{
		_kontobewegungen = new ArrayList<Integer>();
		_zinsSatz = zinsSatz;
	}
	

	public void zahleEin(int betrag)
	{
		_kontobewegungen.add(betrag);
	}

	public void hebeAb(int betrag)
	{
		if(betrag <= berechneRohsaldo())
		{
			_kontobewegungen.add(- betrag);
		}
		else
		{
			System.out.println("Sparbücher können nicht überzogen werden");
		}
	}
	
	public int berechneRohsaldo()
	{
		int kontostand = 0;
		
		for (Integer kontoBewegung : _kontobewegungen)
		{
			kontostand += kontoBewegung;
		}
		
		return kontostand;
	}
	
	public double berechneZinsen()
	{
		double zinsen = 0;
		int kontostand = 0;
		
		for(int i = 0; i < _kontobewegungen.size(); ++i)
		{
			kontostand = kontostand + _kontobewegungen.get(i);
			
			if (kontostand > 0)
			{
				zinsen = zinsen + kontostand * _zinsSatz / 100;
			} 
		}
		
		return zinsen;
	}
	
	public void macheJahresabschluss()
	{
		// Rohsaldo ermitteln
		int rohsaldo = berechneRohsaldo();
		
		// Zinsen berechnen
		double zinsen = berechneZinsen();
		
		// Jahresabschluss drucken
		drucke(rohsaldo, zinsen);
	}

	public double gibKontostand()
	{
		return berechneRohsaldo() + berechneZinsen();
	}
	
	public void drucke(int rohsaldo, double zinsen)
	{
		String jahresabschluss = "Für das Sparbuch war der Kontostand: " + (rohsaldo + zinsen)
				+ "\nDabei betrug der Saldo aus Ein- und Auszahlungen: " + rohsaldo
				+ "\nEs sind folgende Habenzinsen angefallen: " + zinsen;
		
		System.out.println(jahresabschluss);
	}
}
