package wypozyczalnia.program;

import wypozyczalnia.dane.Database;

public class Program {

	public static void main(String[] args) throws Exception {
		
		//TworzenieTabel tt = new TworzenieTabel();
		//tt.createTables();
		
		naglowek();
		
		MenuGlowne.funkcjaGlowna();
		
		Database db = new Database();
		db.closeConnection();
		
		komunikatKoncowy();
	
	}
	
	
	
	public static void naglowek() throws Exception {
		System.out.println("****************************************************************");
		System.out.println("***                 WYPO¯YCZALNIA SAMOCHODÓW                 ***");
		System.out.println("****************************************************************");
	}
	
	public static void komunikatKoncowy() throws Exception {
		System.out.println("\n****************************************");
		System.out.println("   -= Program zosta³ zakoñczony =-");
		System.out.println("****************************************");
	}
}
