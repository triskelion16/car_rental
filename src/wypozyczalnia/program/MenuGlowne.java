package wypozyczalnia.program;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MenuGlowne {
	
	public static void funkcjaGlowna() throws Exception {
		try {
			int akcja = -1;
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
						MenuWypozyczalnia.funkcjaWypozycz();
						menu();
						break;
						
					case 2:
						MenuKlient.funkcjaKlient();
						menu();
						break;
						
					case 3:
						MenuSamochod.funkcjaSamochod();
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
		System.out.println("\n\n*****************************");
		System.out.println("***      Menu g³óne       ***");
		System.out.println("\nDostêpne funkcje:\n");
		System.out.println("1 - Obs³uga klienta");
		System.out.println("0 - Zakoñcz program\n");
		System.out.println("-----------------------------");
		System.out.println("Opcje serwisowe: ");
		System.out.println("2 - Menu 'Klient'");
		System.out.println("3 - Menu 'Samochód'");
		System.out.println();
	}
	
	
	
	private static boolean czyAdm() throws Exception {
		boolean administrator = false;
		BufferedReader pamiec = new BufferedReader(new InputStreamReader(System.in));
		int haslo = 123;
		int tekst = 0;
		
		System.out.println("Podaj has³o administratora: (Has³o: 123)");
		
		try {
			tekst = Integer.parseInt(pamiec.readLine());
		} catch(NumberFormatException e) {
			System.out.println("*** Podane has³o jest nieprawid³owe! Nie masz dostêpu do tych funkcji! ***");
			funkcjaGlowna();
		}
		if(tekst != haslo) administrator = true;
		
		return administrator;
	}
}
