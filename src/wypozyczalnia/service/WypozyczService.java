package wypozyczalnia.service;

import java.util.List;

import wypozyczalnia.dane.WypozyczDAO;
import wypozyczalnia.klasy.Klient;
import wypozyczalnia.klasy.Samochod;
import wypozyczalnia.klasy.Wypozycz;

public class WypozyczService {
	private WypozyczDAO baza;
	
	
	public WypozyczService() throws Exception {
		this.baza = new WypozyczDAO();
	}
	
	
	
	public void noweZlecenie(Wypozycz w) throws Exception {
		this.baza.dodajZlecenie(w);
	}
	
	
	public boolean pobierzZlecenia() throws Exception {
		List<Wypozycz> listaZlecen = baza.pobierzZlecenia(0);
		boolean saZlecenia = true;
		
		if(listaZlecen.size() <= 0) {
			System.out.println("* W bazie danych nie ma ¿adnych zleceñ *");
			saZlecenia = false;
		}
		
		else{
			KlientService klientSrv = new KlientService();
			SamochodService samochodSrv = new SamochodService();
			
			System.out.println("\n*** Lista aktywnych zleceñ ***");
			System.out.println("----------------------------------------\n\n");
			System.out.println("ID zlecenia\tImiê i nazwisko klienta\t\tMarka i model samochodu\n");
			
			for(Wypozycz wyp: listaZlecen) {
				Klient kl = klientSrv.pobierzKlienta(wyp.getIdKlient());
				Samochod sam = samochodSrv.pobierzSamochod(wyp.getIdSamochod());
				
				System.out.print(wyp.getIdWypozycz() + "\t\t");
				System.out.print(kl.getImie() + " " + kl.getNazwisko() + "\t\t\t");
				System.out.println(sam.getMarkaSamochodu() + " " + sam.getModelSamochodu());
			}
		}
		return saZlecenia;
	}
	
	public Wypozycz pobierzZlecenie(int id) throws Exception {
		return this.baza.pobierzZlecenie(id);
	}
	
	public void usunZlecenie(int id) throws Exception {
		this.baza.usunZlecenie(id);
	}

}
