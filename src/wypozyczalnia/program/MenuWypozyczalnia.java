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
						System.out.println("*** B��d! Podana warto�� jest nieprawid�owa! ***");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void menu() throws Exception {
		System.out.println("\n\n\n*****************************************");
		System.out.println("***      Wypo�yczenie samochodu       ***");
		System.out.println("\nDost�pne funkcje:\n");
		System.out.println("1 - Wypo�ycz samoch�d");
		System.out.println("2 - Zwrot samochodu");
		System.out.println("3 - Anuluj zlecenie");
		System.out.println("0 - Wr�� do poprzedniego menu");
		System.out.println();
	}

}
