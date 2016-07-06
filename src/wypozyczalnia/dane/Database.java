package wypozyczalnia.dane;

import java.sql.*;

//-----------Klasa nadrz�dna dla .DAO------------


public class Database {
	
	protected Connection conn = null;
	
	public Database() throws Exception {
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection("jdbc:sqlite:baza_wypozyczalnia.s3db");
		//System.out.println("*** Pomy�lnie utworzono po��czenie z baz� danych ***");
	}
	
	public void closeConnection() throws Exception {
		if(!this.conn.isClosed()) {
			this.conn.close();
			System.out.println("*** Po��czenie z baz� danych zosta�o pomy�lnie zamkni�te. ***");
		}
	}
}
