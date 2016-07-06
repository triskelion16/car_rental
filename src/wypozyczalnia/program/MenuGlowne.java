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
				
				System.out.println("\nWybierz funkcj�: ");
				
				while(cyfra) {
					try {
						akcja = Integer.parseInt(bufor.readLine());
						cyfra = false;
					} catch(NumberFormatException e) {
						System.out.println("*** B��d! Poprawny format to warto�� liczbowa! ***");	
						System.out.println("\nWybierz funkcj�: ");
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
						System.out.println("*** B��d! Podana warto�� jest nieprawid�owa! ***");
						
				}// koniec: switch
			}// koniec: while
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void menu() throws Exception {
		System.out.println("\n\n*****************************");
		System.out.println("***      Menu g��ne       ***");
		System.out.println("\nDost�pne funkcje:\n");
		System.out.println("1 - Obs�uga klienta");
		System.out.println("0 - Zako�cz program\n");
		System.out.println("-----------------------------");
		System.out.println("Opcje serwisowe: ");
		System.out.println("2 - Menu 'Klient'");
		System.out.println("3 - Menu 'Samoch�d'");
		System.out.println();
	}
	
	
	
	private static boolean czyAdm() throws Exception {
		boolean administrator = false;
		BufferedReader pamiec = new BufferedReader(new InputStreamReader(System.in));
		int haslo = 123;
		int tekst = 0;
		
		System.out.println("Podaj has�o administratora: (Has�o: 123)");
		
		try {
			tekst = Integer.parseInt(pamiec.readLine());
		} catch(NumberFormatException e) {
			System.out.println("*** Podane has�o jest nieprawid�owe! Nie masz dost�pu do tych funkcji! ***");
			funkcjaGlowna();
		}
		if(tekst != haslo) administrator = true;
		
		return administrator;
	}
}
