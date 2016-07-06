package wypozyczalnia.interfejs;

import wypozyczalnia.klasy.Klient;
import wypozyczalnia.service.KlientService;

public class KlientForm extends Wyjatki {
	private KlientService klientSrv;
	
	
	public KlientForm() throws Exception {
		super();
		this.klientSrv = new KlientService();
	}
	
	
	public void dodajKlienta() throws Exception {
		Klient klient = new Klient();
		
		System.out.println("\n***   Dodawanie nowego klienta  ***");
		System.out.println("-----------------------------------\n");
		System.out.println("Podaj imiê: ");
		klient.setImie(bufor.readLine());
		System.out.println("Podaj nazwisko: ");
		klient.setNazwisko(bufor.readLine());
		System.out.println("Podaj adres: ");
		klient.setAdres(bufor.readLine());
		System.out.println("Podaj pesel: ");
		klient.setPesel(bufor.readLine());
		
		klientSrv.dodajKlienta(klient);
	}
	
	public void aktualizujKlienta() throws Exception {
		Klient klient = new Klient();
		
		System.out.println("\n***   Aktualizacja danych klienta  ***");
		System.out.println("--------------------------------------\n");
		System.out.println("Podaj ID klienta który ma zostaæ zaktualizowany: ");
		
		klient.setIdKlienta(stringToIntBez0());
		
		System.out.println("Podaj  nowe imiê: ");
		klient.setImie(bufor.readLine());
		System.out.println("Podaj nowe nazwisko: ");
		klient.setNazwisko(bufor.readLine());
		System.out.println("Podaj nowy adres: ");
		klient.setAdres(bufor.readLine());
		System.out.println("Podaj nowy pesel: ");
		klient.setPesel(bufor.readLine());
		
		klientSrv.aktualizujKlienta(klient);
	}
	
	public void usunKlienta() throws Exception {
		
		System.out.println("\n***   Usuwanie klienta z bazy danych  ***");
		System.out.println("-----------------------------------------\n");
		System.out.println("Podaj ID klienta który ma zostaæ usuniêty: ");
		
		klientSrv.usunKlienta(stringToIntBez0());
	}
	
	public void wyswietlListeKlientow() throws Exception {
		klientSrv.pobierzKlientow();
	}
	
	public void wyszukajKlienta() throws Exception {
		
		System.out.println("\n***   Wyszukiwanie klienta w bazie danych  ***");
		System.out.println("----------------------------------------------\n");
		System.out.println("Podaj ID klienta który ma zostaæ znaleziony: ");
		
		Klient wyszukanyKlient = klientSrv.pobierzKlienta(stringToIntBez0());
		
		if(wyszukanyKlient == null) System.out.println("Klient o takim ID nie istnieje!");
		
		else {
		System.out.println("\n***     Dane klienta     ***");
		System.out.println("----------------------------\n");
		System.out.println("ID\tImiê i nazwisko\t\t Adres\t\t\t\t\t Pesel\n");
		
		System.out.print(wyszukanyKlient.getIdKlienta() + "\t");
		System.out.print(wyszukanyKlient.getImie() + " " + wyszukanyKlient.getNazwisko() + "\t\t");
		System.out.print(wyszukanyKlient.getAdres() + "\t\t");
		System.out.println(wyszukanyKlient.getPesel());
		}
	}
}
