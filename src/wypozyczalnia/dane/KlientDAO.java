package wypozyczalnia.dane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import wypozyczalnia.klasy.Klient;

public class KlientDAO extends Database {

	public KlientDAO() throws Exception {
		super();
	}
	
	//------------------- Dodanie klienta ------------------------------------
	
	public void dodajKlienta(Klient klient) throws Exception {
		
		if(klient == null)
			throw new Exception("Nie mo¿na zapisaæ obiektu 'Klient', który nie istnieje!");
		
		String insertQuery = "INSERT INTO klienci(imie, nazwisko, adres, pesel) VALUES(?,?,?,?)";
		
		PreparedStatement stmt = this.conn.prepareStatement(insertQuery);
		stmt.setString(1, klient.getImie());
		stmt.setString(2, klient.getNazwisko());
		stmt.setString(3, klient.getAdres());
		stmt.setString(4, klient.getPesel());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("*** Pomyœlnie dodano klienta do bazy danych ***");
	}
	
	//-------------------- Aktualizacja klienta -------------------------------
	
	public void aktualizujKlienta(Klient klient) throws Exception {
		if(klient == null)
			throw new Exception("Obiekt 'Klient' nie mo¿e byæ pusty!");
		
		String updateQuery = "UPDATE klienci SET imie = ?, nazwisko = ?, adres = ?, pesel = ? WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(updateQuery);
		stmt.setString(1, klient.getImie());
		stmt.setString(2, klient.getNazwisko());
		stmt.setString(3, klient.getAdres());
		stmt.setString(4, klient.getPesel());
		stmt.setInt(5, klient.getIdKlienta());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("\n*** Pomyœlnie zaktuazizowano klienta w bazie danych ***");
	}
	
	//------------------------ Usuñ klienta -----------------------------------
	
	public void usunKlienta(int id) throws Exception {
		if(id == 0)
			throw new Exception("Nale¿y podaæ ID usuwanego klienta!");
		
		String deleteQuery = "DELETE FROM klienci WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(deleteQuery);
		stmt.setInt(1, id);
		
		stmt.execute();
		stmt.close();
		
		System.out.println("*** Pomyœlnie usuniêto klienta z bazy danych ***");
	}
	
	//-------------- Lista klientów ---------------------------
	
	public List<Klient> pobierzKlientow(int id) throws Exception {
		List<Klient> listaKlientow = new ArrayList<Klient>();
		
		String selectQuery = "SELECT * FROM klienci";
		
		if(id > 0) selectQuery += " WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
		
		if(id > 0) stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Klient k = new Klient();
			k.setIdKlienta(rs.getInt("id"));
			k.setImie(rs.getString("imie"));
			k.setNazwisko(rs.getString("nazwisko"));
			k.setAdres(rs.getString("adres"));
			k.setPesel(rs.getString("pesel"));
			
			listaKlientow.add(k);
		}
		
		rs.close();
		stmt.close();
		
		return listaKlientow;
	}
	
	//--------------------- Klient -----------------------
	
	public Klient pobierzKlienta(int id) throws Exception  {
		List<Klient> listaKlientow = this.pobierzKlientow(id);
		
		if(listaKlientow.size() > 0) return listaKlientow.get(0);
		
		else return null;
	}
	
}// koniec: Class
