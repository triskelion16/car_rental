package wypozyczalnia.klasy;

public abstract class Akcja {
	private static int kaucja = 1000;
	
	
	public static double obliczWypozyczBrutto(double cenaWypozyczenia, int ileDniDeklaracja) throws Exception {
		double kwotaWypozyczBrutto = 0;
		
		kwotaWypozyczBrutto = cenaWypozyczenia * ileDniDeklaracja;
		
		
		if(ileDniDeklaracja > 14) kwotaWypozyczBrutto = kwotaWypozyczBrutto - (kwotaWypozyczBrutto * 0.2);
		else
			if(ileDniDeklaracja > 7) kwotaWypozyczBrutto = kwotaWypozyczBrutto - (kwotaWypozyczBrutto * 0.15);
			else
				if(ileDniDeklaracja > 3) kwotaWypozyczBrutto = kwotaWypozyczBrutto - (kwotaWypozyczBrutto * 0.1);
			
		return kwotaWypozyczBrutto += kaucja;
	}
	
	public static double obliczVat(double cenaBrutto) throws Exception {
		double kwotaVat = 0;
		
		kwotaVat = (cenaBrutto - kaucja) * 0.23;
		
		return kwotaVat;
	}

}
