package wypozyczalnia.program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import wypozyczalnia.interfejs.SamochodForm;

public class MenuSamochod {

	public static void funkcjaSamochod() throws Exception {
		
		try {
			int akcja = -1;
			SamochodForm samochod = new SamochodForm();
			BufferedReader bufor = new BufferedReader(new InputStreamReader(System.in));
			
			menu();
			
			while(akcja != 0) {
				boolean cyfra = true;
				
				System.out.println("\nWybierz funkcj�: ");
				
				while(cyfra) {
					try {
						akcja = Integer.parseInt(bufor.readLine());
						cyfra = false;
					} catch(NumberFormatException e) {
						System.out.println("*** B��d! Poprawny format to warto�� liczbowa! ***");	
						System.out.println("\nWybierz funkcj�: ");
					}
				}
			
				switch(akcja) {
					case 1:
						samochod.dodajSamochod();
						menu();
						break;
						
					case 2:
						samochod.aktualizujSamochod();
						menu();
						break;
						
					case 3:
						samochod.usunSamochod();
						menu();
						break;
						
					case 4:
						samochod.wyswietlListeSamochodow();
						menu();
						break;
						
					case 5:
						samochod.wyszukajSamochod();
						menu();
						break;
						
					case 6:
						samochod.wyswietlListeWypozyczonychSamochodow();
						menu();
						break;
						
					case 7:
						samochod.wyswietlListeDostepnychSamochodow();
						menu();
						break;
						
					case 8:
						samochod.wyswietlListeZepsutychSamochodow();
						samochod.odbierzNaprawiony();
						menu();
						break;
						
					case 9:
						samochod.wyswietlListeDostepnychSamochodow();
						samochod.dodajDoZepsutych();
						menu();
						break;
						
					case 0:
						break;
						
					default:
						System.out.println("*** B��d! Podana warto�� jest nieprawid�owa! ***");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void menu() throws Exception {
		System.out.println("\n\n\n**********************************");
		System.out.println("***      Menu 'Samoch�d'       ***");
		System.out.println("\nDost�pne funkcje:\n");
		System.out.println("1 - Dodaj nowy samoch�d");
		System.out.println("2 - Edytuj istniej�cy samoch�d");
		System.out.println("3 - Usu� samoch�d z bazy danych");
		System.out.println("4 - Wy�wietl list� wszystkich samochod�w");
		System.out.println("5 - Wyszukaj samoch�d w bazie danych");
		System.out.println("6 - Lista samochod�w wypo�yczonych");
		System.out.println("7 - Lista samochod�w dost�pnych");
		System.out.println("8 - Samoch�d zosta� naprawiony");
		System.out.println("9 - Oddaj samoch�d do naprawy");
		System.out.println("0 - Wr�� do poprzedniego menu");
		System.out.println();
	}

}
