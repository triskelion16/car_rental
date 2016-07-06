package wypozyczalnia.interfejs;

import java.util.Date;

import wypozyczalnia.klasy.Akcja;
import wypozyczalnia.klasy.Klient;
import wypozyczalnia.klasy.Samochod;
import wypozyczalnia.klasy.Wypozycz;
import wypozyczalnia.service.KlientService;
import wypozyczalnia.service.SamochodService;
import wypozyczalnia.service.WypozyczService;

public class WypozyczalniaForm extends Wyjatki {
	private WypozyczService wypozyczSrv;
	
	
	public WypozyczalniaForm() throws Exception {
		super();
		this.wypozyczSrv = new WypozyczService();
	}
	
	
	//------- Wypo¿yczanie samochodu -------------
	
	public void noweZlecenie() throws Exception {
		Wypozycz wypozycz = new Wypozycz();
		SamochodService samochodSrv = new SamochodService();
		KlientService klientSrv = new KlientService();
		int pomocnicza = 0;
		boolean nieMaWBazie = true;
		double kwotaBrutto = 0;
		
		Date dataWypozycz = new Date();
		long dzienWypozycz = dataWypozycz.getTime()/60/60/24/1000;
		
		
		
		System.out.println("\n\n************************************");
		System.out.println("***   Dodawanie nowego zlecenia  ***\n\n");
		
		if(samochodSrv.pobierzDostepneSamochody()) {
			while(nieMaWBazie) {
				System.out.println("Podaj ID samochodu: ");
				pomocnicza = stringToIntBez0();
				Samochod samochodWypozyczany = samochodSrv.pobierzSamochod(pomocnicza);
			
				if(samochodWypozyczany != null && samochodWypozyczany.isCzyWypozyczony() == false && samochodWypozyczany.isCzyZepsuty() == false) {
					nieMaWBazie = false;
					kwotaBrutto = samochodWypozyczany.getCenaWypozyczenia();
					
					samochodWypozyczany.setIdSamochodu(pomocnicza);  //  
					samochodWypozyczany.setCzyWypozyczony(true);     //  Ustawia wypozyczony na true
					samochodSrv.czyWypozyczony(samochodWypozyczany); //
				}
				else System.out.println("* W bazie danych nie istnieje samochód o podanym ID lub nie mo¿na go wypo¿yczyæ *");
			}
			wypozycz.setIdSamochod(pomocnicza);  
		
			
			klientSrv.pobierzKlientow();
			nieMaWBazie = true;
			while(nieMaWBazie) {
				System.out.println("Podaj ID klienta lub wybierz '0' aby dodaæ nowego: ");
				pomocnicza = stringToInt();
				if(pomocnicza == 0) {
					KlientForm klientF = new KlientForm();
					klientF.dodajKlienta();
					klientF.wyswietlListeKlientow();
				} else {
					Klient klientWyp = klientSrv.pobierzKlienta(pomocnicza);
					if(klientWyp != null) nieMaWBazie = false;
					else System.out.println("* W bazie danych nie istnieje klient o podanym ID *");
				}
			}
			wypozycz.setIdKlient(pomocnicza);   
		
			System.out.println("* 10% zni¿ki je¿eli samochód bêdzie wypo¿yczony ponad 3 dni *");
			System.out.println("* 15% zni¿ki je¿eli samochód bêdzie wypo¿yczony ponad 7 dni *");
			System.out.println("* 20% zni¿ki je¿eli samochód bêdzie wypo¿yczony ponad 14 dni *");
			System.out.println("Podaj na ile dni samochód bêdzie wypo¿yczony: ");
			pomocnicza = stringToIntBez0();
			wypozycz.setJakDlugo(pomocnicza);
			
			dataWypozycz.getTime();
			wypozycz.setKwotaDoZaplaty(Akcja.obliczWypozyczBrutto(kwotaBrutto, pomocnicza));
			wypozycz.setDataWypozycz(dzienWypozycz);
			
			wypozyczSrv.noweZlecenie(wypozycz);
			
			
			Klient kl = klientSrv.pobierzKlienta(wypozycz.getIdKlient());
			Samochod sam = samochodSrv.pobierzSamochod(wypozycz.getIdSamochod());
			
			
			
			System.out.println("\n\n\n*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("**                 Raport wypo¿yczenia               ***\n");
			System.out.println("Data wygenerowania raportu: " + dataWypozycz);
			System.out.println("\n--------------------- Dane klienta ---------------------\n");
			System.out.println("ID w bazie danych:    " + kl.getIdKlienta());
			System.out.println("Imiê i nazwisko:      " + kl.getImie() + " " + kl.getNazwisko());
			System.out.println("Adres:                " + kl.getAdres());
			System.out.println("Pesel:                " + kl.getPesel());
			System.out.println("\n-------------------- Dane samochodu --------------------\n");
			System.out.println("ID w bazie danych:    "+ sam.getIdSamochodu());
			System.out.println("Marka i model:        "+ sam.getMarkaSamochodu() + " " + sam.getModelSamochodu());
			System.out.println("Nr rejestracujny:     " + sam.getNrRejestracyjnySamochodu());
			System.out.println("Aktualny przebieg:    " + sam.getPrzebiegSamochodu() + " km");
			System.out.println("\n--------------------- Podsumowanie ---------------------\n");
			System.out.println("Deklarowana liczba dni wypo¿yczenia: "+ wypozycz.getJakDlugo() + "dni\n");
			System.out.println("Kwota do zap³aty:     " + wypozycz.getKwotaDoZaplaty() + " z³\n");
			System.out.println("w tym:                1000.0 z³ kaucji");
			System.out.println("                      " + Akcja.obliczVat(wypozycz.getKwotaDoZaplaty()) + " z³ podatku Vat\n");
			System.out.println("*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("*********************************************************\n");
		}
	}
	
	
	//-------- Zwrot samochodu ----------------
	
	public void zwrotSamochodu() throws Exception {
		SamochodService samochodSrv = new SamochodService();
		KlientService klientSrv = new KlientService();
		
		Wypozycz wyp = null;
		Klient kl = null;
		Samochod sam = null;
		
		int idZlecenia = 0;
		int akcja = -1;
		boolean nieMaWBazie = true;
		boolean wyjdz = false;
		int przebieg = 0;
		int przejechal = 0;
		boolean uszkodzony = false;
		double kaucja = 0;
		
		Date data = new Date();
		long dataZwrotu = data.getTime()/60/60/24/1000;
		long dniWypozyczenia = 0;
		
		
		System.out.println("\n\n************************************");
		System.out.println("***        Zwrot samochodu       ***\n");
		
		if(wypozyczSrv.pobierzZlecenia()) {
			 
			 while(nieMaWBazie) {
				System.out.println("\nPodaj ID zlecenia: ");
				idZlecenia = stringToIntBez0();
					
				wyp = wypozyczSrv.pobierzZlecenie(idZlecenia);
				if(wyp != null) {
					kl = klientSrv.pobierzKlienta(wyp.getIdKlient());
					sam = samochodSrv.pobierzSamochod(wyp.getIdSamochod());
					System.out.println("\n\n\n***************************************************************");
					System.out.println("Wybrano nastêpuj¹ce zlecenie:\n");
					System.out.println("ID zlecenia\tImiê i nazwisko klienta\t\tMarka i model samochodu");
					
					System.out.print(wyp.getIdWypozycz() + "\t\t");
					System.out.print(kl.getImie() + " " + kl.getNazwisko() + "\t\t\t");
					System.out.println(sam.getMarkaSamochodu() + " " + sam.getModelSamochodu() + "\n");
					nieMaWBazie = false;
					}
					else System.out.println("* W bazie danych nie istnieje zlecenie o podanym ID *");
			 }
					
			//------------  Stan techniczny -----------
			System.out.println("\nCzy zwracany samochód zosta³ uszkodzony? \n");
			System.out.println("1 - samochód jest sprawny");
			System.out.println("0 - samochód zosta³ uszkodzony");
					
					
			while(akcja != 0 || akcja != 1) {
				akcja = stringToInt();
						
				switch(akcja) {
					case 0:
						sam.setIdSamochodu(sam.getIdSamochodu());  //  
						sam.setCzyZepsuty(true);                   //  Ustawia zepsuty na true
						samochodSrv.czyZepsuty(sam);               //
						kaucja = -1000;
						uszkodzony = true;
						wyjdz = true;
						break;
								
					case 1:
						wyjdz = true;
						break;
								
					default:
						System.out.println("*** B³¹d! Podana wartoœæ jest nieprawid³owa! ***");
				}
				if(wyjdz) break;
			}
					
			//----------  Podaj przebieg auta  ------------------
			System.out.print("\n\nStan licznika w chwili wypo¿yczenia: " + sam.getPrzebiegSamochodu() + " km");
			System.out.println("\nPodaj aktualny stan licznika:");
			przebieg = stringToIntBez0();
			przejechal = przebieg - sam.getPrzebiegSamochodu();
			
			sam.setIdSamochodu(sam.getIdSamochodu());
			sam.setPrzebiegSamochodu(przebieg);
			samochodSrv.editPrzebieg(sam);
			
					
			//Czy oddany w terminie
			dniWypozyczenia = dataZwrotu - wyp.getDataWypozycz();
			
			if(dniWypozyczenia < 0) {
				kaucja = kaucja + dniWypozyczenia * sam.getCenaWypozyczenia();
			}
			
					
			//Wyœwietl raport
			System.out.println("\n\n\n*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("**                    Raport zwrotu                   ***\n");
			System.out.println("Data wygenerowania raportu: " + data);
			System.out.println("\n--------------------- Dane klienta ---------------------\n");
			System.out.println("ID w bazie danych:    " + kl.getIdKlienta());
			System.out.println("Imiê i nazwisko:      " + kl.getImie() + " " + kl.getNazwisko());
			System.out.println("Adres:                " + kl.getAdres());
			System.out.println("Pesel:                " + kl.getPesel());
			System.out.println("\n-------------------- Dane samochodu --------------------\n");
			System.out.println("ID w bazie danych:    "+ sam.getIdSamochodu());
			System.out.println("Marka i model:        "+ sam.getMarkaSamochodu() + " " + sam.getModelSamochodu());
			System.out.println("Nr rejestracujny:     " + sam.getNrRejestracyjnySamochodu());
			System.out.println("Aktualny przebieg:    " + sam.getPrzebiegSamochodu() + " km");
			System.out.println("Przejechane zosta³o:  " + przejechal + " km");
			System.out.println("\n--------------------- Podsumowanie ---------------------\n");
			System.out.println("Deklarowana liczba dni wypo¿yczenia: " + wyp.getJakDlugo() + " dni");
			System.out.println("Faktyczna liczba dni wypo¿yczenia:   " + dniWypozyczenia + " dni\n");
			if(uszkodzony) System.out.println("Samochód zosta³ uszkodzony przez klienta! (Zosta³ oddany do naprawy)\n");
			System.out.println("Wp³acono:             " + wyp.getKwotaDoZaplaty() + " z³");
			System.out.println("w tym:                1000.0 z³ kaucji\n\n");
			
			if(kaucja < -1000) System.out.println("**********   Kwota do dop³aty: " + ((1000 + kaucja)*-1) + "z³.   **********");
			else  System.out.println("**********    Kwota do zwrotu: " + (1000 + kaucja) + "z³.    **********");
				
			System.out.println("\n\n*********************************************************");
			System.out.println("*********************************************************");
			System.out.println("*********************************************************\n");	
					
			sam.setIdSamochodu(sam.getIdSamochodu()); //
			sam.setCzyWypozyczony(false);             //  czyWypozyczony = false
			samochodSrv.czyWypozyczony(sam);          //
			
			wypozyczSrv.usunZlecenie(idZlecenia);
			} 
	 }
	
	
	
	
	//--------- Usuwanie zlecenia z bazy danych -----------
	
	public void usunZlecenie() throws Exception {
		 if(wypozyczSrv.pobierzZlecenia()) {
			 SamochodService samochodSrv = new SamochodService();
			 int pomocnicza = 0;
			
			System.out.println("\n***   Usuwanie zlecenia z bazy danych  ***");
			System.out.println("-------------------------------------------\n");
			System.out.println("Podaj ID zlecenia które ma zostaæ usuniête: ");
			
			pomocnicza = stringToIntBez0();
			Wypozycz usunZlecenie = wypozyczSrv.pobierzZlecenie(pomocnicza);
			
			Samochod idSamochodu = samochodSrv.pobierzSamochod(usunZlecenie.getIdSamochod());
			idSamochodu.setIdSamochodu(usunZlecenie.getIdSamochod());
			idSamochodu.setCzyWypozyczony(false);
			samochodSrv.czyWypozyczony(idSamochodu);
			
			wypozyczSrv.usunZlecenie(pomocnicza);
			
			System.out.println("*** Pomyœlnie usuniêto zlecenie z bazy danych ***");
		 }
	}
}
