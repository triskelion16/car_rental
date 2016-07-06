package wypozyczalnia.program;

import java.sql.*;

public class TworzenieTabel {
	
	private Connection conn = null;
	private Statement stat = null;
	
	public void createTables() throws Exception {
		
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection("jdbc:sqlite:baza_wypozyczalnia.s3db");
		System.out.println("Pomy�lnie utworzono po��czenie z baz� danych.");
		
		
		stat = conn.createStatement();
		
		//String usunKlientowTab = "DROP TABLE klienci";
	    //stat.execute(usunKlientowTab);
		
        String createKlienciTab = "CREATE TABLE IF NOT EXISTS klienci " +
                                  "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		                  "imie       varchar(30)       NOT NULL, " +
                                  "nazwisko   varchar(30)       NOT NULL, " +
        		                  "adres      varchar(255)      NOT NULL, " +
                                  "pesel      varchar(11)       NOT NULL)";
        
        //String usunSamochodyTab = "DROP TABLE samochody";
        //stat.execute(usunSamochodyTab);
        
        
        String createSamochodyTab = "CREATE TABLE IF NOT EXISTS samochody " +
                                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		                    "marka              varchar(20)      NOT NULL, " +
                                    "model              varchar(20)      NOT NULL, " +
        		                    "nrRejestracyjny    varchar(10)      NOT NULL, " +
                                    "przebieg           int              NOT NULL, " +
        		                    "cenaWypozyczenia   real             NOT NULL, " +
                                    "czyWypozyczony     boolean          NOT NULL, " +
                                    "czyZepsuty         boolean          NOT NULL)";
        
        
        //String usunWypozyczoneTab = "DROP TABLE wypozyczone";
        //stat.execute(usunWypozyczoneTab);
        
        String createWypozyczoneTab = "CREATE TABLE IF NOT EXISTS wypozyczone " +
        		                      "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		                      "idKlient           int        NOT NULL, " +
        		                      "idSamochod         int        NOT NULL, " +
        		                      "jakDlugo           int        NOT NULL, " +
        		                      "kwotaDoZaplaty     real       NOT NULL, " +
        		                      "dataWypozycz       long                 )";
        
        
        stat.execute(createKlienciTab);
        System.out.println("Tabela 'Klienci' zosta�a utworzona pomy�lnie.");
        stat.execute(createSamochodyTab);
        System.out.println("Tabela 'Samochody'  zosta�a utworzona pomy�lnie.");
        stat.execute(createWypozyczoneTab);
        System.out.println("Tabela 'Wypo�yczone' zosta�a utworzona pomy�lnie.");
        
        stat.close();
        conn.close();
        System.out.println("Po��czenie zosta�o pomy�lnie zamkni�te.\n");
    }
}