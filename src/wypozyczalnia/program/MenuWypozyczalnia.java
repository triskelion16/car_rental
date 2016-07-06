package wypozyczalnia.program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import wypozyczalnia.interfejs.WypozyczalniaForm;

public class MenuWypozyczalnia {
	
	public static void funkcjaWypozycz() throws Exception {
		
		try {
			int akcja = -1;
			WypozyczalniaForm wypozyczalnia = new WypozyczalniaForm();
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
				}
			
				switch(akcja) {
					case 1:
						wypozyczalnia.noweZlecenie();
						menu();
						break;
						
					case 2:
						wypozyczalnia.zwrotSamochodu();
						menu();
						break;
						
					case 3:  
						wypozyczalnia.usunZlecenie();
						menu();
						break;
						
					case 0:
						break;
						
					default:
						System.out.println("*** B³¹d! Podana wartoœæ jest nieprawid³owa! ***");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void menu() throws Exception {
		System.out.println("\n\n\n*****************************************");
		System.out.println("***      Wypo¿yczenie samochodu       ***");
		System.out.println("\nDostêpne funkcje:\n");
		System.out.println("1 - Wypo¿ycz samochód");
		System.out.println("2 - Zwrot samochodu");
		System.out.println("3 - Anuluj zlecenie");
		System.out.println("0 - Wróæ do poprzedniego menu");
		System.out.println();
	}

}
