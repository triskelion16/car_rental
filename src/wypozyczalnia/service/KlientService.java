package wypozyczalnia.service;

import java.util.List;

import wypozyczalnia.dane.KlientDAO;
import wypozyczalnia.klasy.Klient;

public class KlientService {
	private KlientDAO baza;
	
	public KlientService() throws Exception {
		this.baza = new KlientDAO();
	}
	
	
	public void dodajKlienta(Klient k) throws Exception {
		this.baza.dodajKlienta(k);
	}
	
	public void aktualizujKlienta(Klient k) throws Exception {
		this.baza.aktualizujKlienta(k);
	}
	
	public void usunKlienta(int id) throws Exception {
		this.baza.usunKlienta(id);
	}
	
	public void pobierzKlientow() throws Exception {
		
		List<Klient> listaKlientow = baza.pobierzKlientow(0);
		
		if(listaKlientow == null) System.out.println("* W bazie danych nie ma ¿adnych klientów *");
		
		else {
			System.out.println("\n***     Lista klientów     ***");
			System.out.println("------------------------------\n");
			System.out.println("ID\tImiê i nazwisko\t\t Adres\t\t\t\t\t Pesel\n");
			for(Klient klient: listaKlientow) {
				System.out.print(klient.getIdKlienta() + "\t");
				System.out.print(klient.getImie() + " " + klient.getNazwisko() + "\t\t");
				System.out.print(klient.getAdres() + "\t\t");
				System.out.println(klient.getPesel());
			}
		}
	}
	
	public Klient pobierzKlienta(int id) throws Exception {
		return this.baza.pobierzKlienta(id);
	}
}
