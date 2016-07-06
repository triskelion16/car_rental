package wypozyczalnia.interfejs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Wyjatki {
	protected BufferedReader bufor; 
	
	
	public Wyjatki() {
		bufor = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	public int stringToInt() throws Exception {
		int liczba = 0;
		boolean prawidlowa = true;
		
		while(prawidlowa) {
			try {
				liczba = Integer.parseInt(bufor.readLine());
				if(liczba < 0) {
					System.out.println("*** B³¹d! Liczba nie mo¿e byæ ujemna ***");
					System.out.println("Wpisz ponownie: ");
				}
				else prawidlowa = false;
			} catch(NumberFormatException e) {
				System.out.println("*** B³¹d! Poprawny format to wartoœæ liczbowa! (liczba ca³kowita) ***");	
				System.out.println("Wpisz ponownie: ");
			}
		}
		return liczba;
	}
	
	
	public double stringToDouble() throws Exception {
		double liczba = 0;
		boolean prawidlowa = true;
		
		while(prawidlowa) {
			try {
				liczba = Double.parseDouble(bufor.readLine());
				if(liczba < 0 || liczba == 0) throw new Exception("*** B³¹d! Liczba musi byæ wiêksza od zera ***");
				else prawidlowa = false;
			} catch(NumberFormatException e) {
				System.out.println("*** B³¹d! Poprawny format to wartoœæ liczbowa! (kropka zamiast przecinku!) ***");	
				System.out.println("Wpisz ponownie: ");
			}
		}	
		return liczba;
	}
	
	public int stringToIntBez0() throws Exception {
		int liczba = 0;
		boolean prawidlowa = true;
		
		while(prawidlowa) {
			try {
				liczba = Integer.parseInt(bufor.readLine());
				if(liczba <= 0) {
					System.out.println("*** B³¹d! Liczba musi byæ wiêksza od zera ***");
					System.out.println("Wpisz ponownie: ");
				}
				else prawidlowa = false;
			} catch(NumberFormatException e) {
				System.out.println("*** B³¹d! Poprawny format to wartoœæ liczbowa! (liczba ca³kowita) ***");	
				System.out.println("Wpisz ponownie: ");
			}
		}
		return liczba;
	}

}
