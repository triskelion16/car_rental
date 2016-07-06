package wypozyczalnia.interfejs;

import java.util.List;

import wypozyczalnia.klasy.Samochod;
import wypozyczalnia.service.SamochodService;

public class SamochodForm extends Wyjatki {
	private SamochodService samochodSrv;
	
	
	public SamochodForm() throws Exception {
		super();  
		this.samochodSrv = new SamochodService();
	}
	
	
	public void dodajSamochod() throws Exception {
		Samochod samochod = new Samochod();
		
		System.out.println("\n***   Dodawanie nowego samochodu  ***");
		System.out.println("-------------------------------------\n");
		
		System.out.println("Podaj markê: ");
		samochod.setMarkaSamochodu(bufor.readLine());
		System.out.println("Podaj model: ");
		samochod.setModelSamochodu(bufor.readLine());
		System.out.println("Podaj numer rejestracyjny: ");
		samochod.setNrRejestracyjnySamochodu(bufor.readLine());
		System.out.println("Podaj przebieg samochodu: ");
		samochod.setPrzebiegSamochodu(stringToInt());       // metoda dziedziczona po klasie Wyjatki
		System.out.println("Podaj cenê wypo¿yczenia: ");
		samochod.setCenaWypozyczenia(stringToDouble());     // metoda dziedziczona po klasie Wyjatki
		
		samochodSrv.dodajSamochod(samochod);
	}
	
	public void aktualizujSamochod() throws Exception {
		Samochod samochod = new Samochod();
		
		System.out.println("\n***   Aktualizacja danych samochodu  ***");
		System.out.println("----------------------------------------\n");
		System.out.println("Podaj ID samochodu który ma zostaæ zaktualizowany: ");
		
		samochod.setIdSamochodu(stringToIntBez0());
		
		System.out.println("Podaj  now¹ markê: ");
		samochod.setMarkaSamochodu(bufor.readLine());
		System.out.println("Podaj nowy model: ");
		samochod.setModelSamochodu(bufor.readLine());
		System.out.println("Podaj nowy numer rejestracyjny: ");
		samochod.setNrRejestracyjnySamochodu(bufor.readLine());
		System.out.println("Podaj nowy przebieg: ");
		samochod.setPrzebiegSamochodu(stringToInt());
		System.out.println("Podaj now¹ cenê wypo¿yczenia: ");
		samochod.setCenaWypozyczenia(stringToDouble());
		
		samochodSrv.aktualizujSamochod(samochod);
	}
	
	public void usunSamochod() throws Exception {
		
		System.out.println("\n***   Usuwanie samochodu z bazy danych  ***");
		System.out.println("-------------------------------------------\n");
		System.out.println("Podaj ID samochodu który ma zostaæ usuniêty: ");
		
		samochodSrv.usunSamochod(stringToIntBez0());
	}
	
	public void wyswietlListeSamochodow() throws Exception {
		samochodSrv.pobierzSamochody();
	}
	
	public void wyszukajSamochod() throws Exception {
		
		System.out.println("\n***   Wyszukiwanie samochodu w bazie danych  ***");
		System.out.println("------------------------------------------------\n");
		System.out.println("Podaj ID samochodu który ma zostaæ znaleziony: ");
		
		Samochod wyszukanySamochod = samochodSrv.pobierzSamochod(stringToIntBez0());
		
		if(wyszukanySamochod == null) System.out.println("Samochod o takim ID nie istnieje!");
		
		else {
		System.out.println("\n***     Dane samochodu     ***");
		System.out.println("------------------------------\n");
		System.out.println("ID\tMarka\t\tModel\t\tNr rejestracyjny\tPrzebieg\tCena/dobê\n");
		
		System.out.print(wyszukanySamochod.getIdSamochodu() + "\t");
		System.out.print(wyszukanySamochod.getMarkaSamochodu() + "\t\t");
		System.out.print(wyszukanySamochod.getModelSamochodu() + "\t\t");
		System.out.print(wyszukanySamochod.getNrRejestracyjnySamochodu() + "\t\t");
		System.out.print(wyszukanySamochod.getPrzebiegSamochodu() + "\t\t");
		System.out.println(wyszukanySamochod.getCenaWypozyczenia());
		}
	}
	
	public void wyswietlListeDostepnychSamochodow() throws Exception {
		samochodSrv.pobierzDostepneSamochody();
	}
	
	public void wyswietlListeWypozyczonychSamochodow() throws Exception {
		
		List<Samochod> listaSamochodow = samochodSrv.pobierzWypozyczoneSamochody();
		
		if(listaSamochodow == null) System.out.println("* Wszystkie samochody s¹ dostêpne *");
		
		else{
			System.out.println("\n***     Lista wypo¿yczonych samochodów     ***");
			System.out.println("--------------------------------\n");
			System.out.println("ID\tMarka\t\tModel\t\tNr rejestracyjny\tPrzebieg\tCena/dobê\n");
			for(Samochod samochod: listaSamochodow) {
				System.out.print(samochod.getIdSamochodu() + "\t");
				System.out.print(samochod.getMarkaSamochodu() + "\t\t");
				System.out.print(samochod.getModelSamochodu() + "\t\t");
				System.out.print(samochod.getNrRejestracyjnySamochodu() + "\t\t");
				System.out.print(samochod.getPrzebiegSamochodu() + "\t\t");
				System.out.println(samochod.getCenaWypozyczenia());
			}
		}
	}
	
	public void wyswietlListeZepsutychSamochodow() throws Exception {
		
		List<Samochod> listaSamochodow = samochodSrv.pobierzZepsuteSamochody();
		
		if(listaSamochodow == null) System.out.println("* ¯aden samochód nie jest w zepsuty *");
		
		else{
			System.out.println("\n***     Lista zepsutych samochodów     ***");
			System.out.println("--------------------------------\n");
			System.out.println("ID\tMarka\t\tModel\t\tNr rejestracyjny\tPrzebieg\tCena/dobê\n");
			for(Samochod samochod: listaSamochodow) {
				System.out.print(samochod.getIdSamochodu() + "\t");
				System.out.print(samochod.getMarkaSamochodu() + "\t\t");
				System.out.print(samochod.getModelSamochodu() + "\t\t");
				System.out.print(samochod.getNrRejestracyjnySamochodu() + "\t\t");
				System.out.print(samochod.getPrzebiegSamochodu() + "\t\t");
				System.out.println(samochod.getCenaWypozyczenia());
			}
		}
	}
	
	public void dodajDoZepsutych() throws Exception {
		Samochod samochod = new Samochod();
		
		System.out.println("\n***   Oddawanie samochodu do naprawy  ***");
		System.out.println("----------------------------------------\n");
		System.out.println("Podaj ID samochodu który ma zostaæ naprawiony: ");
		
		samochod.setIdSamochodu(stringToIntBez0());
		samochod.setCzyZepsuty(true);
		
		samochodSrv.czyZepsuty(samochod);
	}
	
	public void odbierzNaprawiony() throws Exception {
		Samochod samochod = new Samochod();
		
		System.out.println("\n***   Odbieranie samochodu z naprawy  ***");
		System.out.println("----------------------------------------\n");
		System.out.println("Podaj ID samochodu który zosta³ naprawiony: ");
		
		samochod.setIdSamochodu(stringToIntBez0());
		samochod.setCzyZepsuty(false);
		
		samochodSrv.czyZepsuty(samochod);
	}

}
