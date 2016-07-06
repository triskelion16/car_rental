package wypozyczalnia.program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import wypozyczalnia.interfejs.KlientForm;

public class MenuKlient {
	
	public static void funkcjaKlient() throws Exception {
		
		try {
			int akcja = -1;
			KlientForm klient = new KlientForm();
			BufferedReader bufor = new BufferedReader(new InputStreamReader(System.in));
			
			menu();
			
			while(akcja != 0) {
				boolean cyfra = true;
				
				System.out.println("\nWybierz funkcjê: ");
				
				while(cyfra) {
					try {
						akcja = Integer.parseInt(bufor.readLine());
						cyfra = false;
					} catch(NumberFormatException e) {
						System.out.println("*** B³¹d! Poprawny format to wartoœæ liczbowa! ***");	
						System.out.println("\nWybierz funkcjê: ");
					}
				}// koniec: while
			
				switch(akcja) {
					case 1:
						klient.dodajKlienta();
						menu();
						break;
						
					case 2:
						klient.aktualizujKlienta();
						menu();
						break;
						
					case 3:
						klient.usunKlienta();
						menu();
						break;
						
					case 4:
						klient.wyswietlListeKlientow();
						menu();
						break;
						
					case 5:
						klient.wyszukajKlienta();
						menu();
						break;
						
					case 0:
						break;
						
					default:
						System.out.println("*** B³¹d! Podana wartoœæ jest nieprawid³owa! ***");
				}// koniec: switch
			}// koniec: while
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void menu() throws Exception {
		System.out.println("\n\n\n********************************");
		System.out.println("***      Menu 'Klient'       ***");
		System.out.println("\nDostêpne funkcje:\n");
		System.out.println("1 - Dodaj nowego klienta");
		System.out.println("2 - Edytuj istniej¹cego klienta");
		System.out.println("3 - Usuñ klienta z bazy danych");
		System.out.println("4 - Wyœwietl listê wszystkich klientów");
		System.out.println("5 - Wyszukaj klienta w bazie danych");
		System.out.println("0 - Wróæ do poprzedniego menu");
		System.out.println();
	}
}
