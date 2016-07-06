package wypozyczalnia.dane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import wypozyczalnia.klasy.Samochod;

public class SamochodDAO extends Database {
	
	public SamochodDAO() throws Exception {
		super();
	}
	
	//------------------- Dodanie samochodu ------------------------------------
	
	public void dodajSamochod(Samochod samochod) throws Exception {
		
		if(samochod == null)
			throw new Exception("Nie mo¿na zapisaæ obiektu 'Samochód', który nie istnieje!");
		
		String insertQuery = "INSERT INTO samochody(marka, model, nrRejestracyjny, przebieg, cenaWypozyczenia, czyWypozyczony, czyZepsuty) VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = this.conn.prepareStatement(insertQuery);
		stmt.setString(1, samochod.getMarkaSamochodu());
		stmt.setString(2, samochod.getModelSamochodu());
		stmt.setString(3, samochod.getNrRejestracyjnySamochodu());
		stmt.setInt(4, samochod.getPrzebiegSamochodu());
		stmt.setDouble(5, samochod.getCenaWypozyczenia());
		stmt.setBoolean(6, samochod.isCzyWypozyczony());
		stmt.setBoolean(7, samochod.isCzyZepsuty());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("*** Pomyœlnie dodano samochód do bazy danych ***");
	}
	
	//-------------------- Aktualizacja samochodu -------------------------------
	
	public void aktualizujSamochod(Samochod samochod) throws Exception {
		if(samochod == null)
			throw new Exception("Obiekt 'Samochód' nie mo¿e byæ pusty!");
		
		String updateQuery = "UPDATE samochody SET marka = ?, model = ?, nrRejestracyjny = ?, przebieg = ?, cenaWypozyczenia = ?, czyWypozyczony = ?, czyZepsuty = ? WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(updateQuery);
		stmt.setString(1, samochod.getMarkaSamochodu());
		stmt.setString(2, samochod.getModelSamochodu());
		stmt.setString(3, samochod.getNrRejestracyjnySamochodu());
		stmt.setInt(4, samochod.getPrzebiegSamochodu());
		stmt.setDouble(5, samochod.getCenaWypozyczenia());
		stmt.setBoolean(6, false);
		stmt.setBoolean(7, false);
		stmt.setInt(8, samochod.getIdSamochodu());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("\n*** Pomyœlnie zaktuazizowano dane samochodu w bazie danych ***");
	}
	
	//------------------------ Usuñ samochód -----------------------------------
	
	public void usunSamochod(int id) throws Exception {
		if(id == 0)
			throw new Exception("Nale¿y podaæ ID usuwanego samochodu!");
		
		String deleteQuery = "DELETE FROM samochody WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(deleteQuery);
		stmt.setInt(1, id);
		
		stmt.execute();
		stmt.close();
		
		System.out.println("*** Pomyœlnie usuniêto samochód z bazy danych ***");
	}
	
	//-------------- Lista samochodów ---------------------------
	
	public List<Samochod> pobierzSamochody(int id) throws Exception {
		List<Samochod> listaSamochodow = new ArrayList<Samochod>();
		
		String selectQuery = "SELECT * FROM samochody";
		
		if(id > 0) selectQuery += " WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
		
		if(id > 0) stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Samochod s = new Samochod();
			s.setIdSamochodu(rs.getInt("id"));
			s.setMarkaSamochodu(rs.getString("marka"));
			s.setModelSamochodu(rs.getString("model"));
			s.setNrRejestracyjnySamochodu(rs.getString("nrRejestracyjny"));
			s.setPrzebiegSamochodu(rs.getInt("przebieg"));
			s.setCenaWypozyczenia(rs.getDouble("cenaWypozyczenia"));
			s.setCzyWypozyczony(rs.getBoolean("czyWypozyczony"));
			s.setCzyZepsuty(rs.getBoolean("czyZepsuty"));
			
			listaSamochodow.add(s);
		}
		
		rs.close();
		stmt.close();
		
		return listaSamochodow;
	}
	
	//----------------- Samochód -----------------------------
	
	public Samochod pobierzSamochod(int id) throws Exception  {
		List<Samochod> listaSamochodow = this.pobierzSamochody(id);
		
		if(listaSamochodow.size() > 0) return listaSamochodow.get(0);
		
		else return null;
	}
	
	//-------------- Lista dostêpnych ---------------------------
	
		public List<Samochod> pobierzDostepneSamochody() throws Exception {
			List<Samochod> listaSamochodow = new ArrayList<Samochod>();
			
			String selectQuery = "SELECT * FROM samochody WHERE czyWypozyczony = ? AND czyZepsuty = ?";
			
			PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
			
			stmt.setBoolean(1, false);
			stmt.setBoolean(2,  false);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Samochod s = new Samochod();
				s.setIdSamochodu(rs.getInt("id"));
				s.setMarkaSamochodu(rs.getString("marka"));
				s.setModelSamochodu(rs.getString("model"));
				s.setNrRejestracyjnySamochodu(rs.getString("nrRejestracyjny"));
				s.setPrzebiegSamochodu(rs.getInt("przebieg"));
				s.setCenaWypozyczenia(rs.getDouble("cenaWypozyczenia"));
				
				listaSamochodow.add(s);
			}
			
			rs.close();
			stmt.close();
			
			if(listaSamochodow.size() == 0) return null;
			
			System.out.println();
			return listaSamochodow;
		}
		
		//-------------- Lista wypo¿yczonych ---------------------------
		
		public List<Samochod> pobierzWypozyczoneSamochody() throws Exception {
			List<Samochod> listaSamochodow = new ArrayList<Samochod>();
			
			String selectQuery = "SELECT * FROM samochody WHERE czyWypozyczony = ?";
			
			PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
			
			stmt.setBoolean(1, true);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Samochod s = new Samochod();
				s.setIdSamochodu(rs.getInt("id"));
				s.setMarkaSamochodu(rs.getString("marka"));
				s.setModelSamochodu(rs.getString("model"));
				s.setNrRejestracyjnySamochodu(rs.getString("nrRejestracyjny"));
				s.setPrzebiegSamochodu(rs.getInt("przebieg"));
				s.setCenaWypozyczenia(rs.getDouble("cenaWypozyczenia"));
				
				listaSamochodow.add(s);
			}
			
			rs.close();
			stmt.close();
			
			if(listaSamochodow.size() == 0) return null;
			
			System.out.println();
			return listaSamochodow;
		}
		
		//-------------- Lista zepsutych samochodów ---------------------------
		
			public List<Samochod> pobierzZepsuteSamochody() throws Exception {
				List<Samochod> listaSamochodow = new ArrayList<Samochod>();
					
				String selectQuery = "SELECT * FROM samochody WHERE czyZepsuty = ?";
					
				PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
				
				stmt.setBoolean(1, true);
					
				ResultSet rs = stmt.executeQuery();
					
				while(rs.next()) {
					Samochod s = new Samochod();
					s.setIdSamochodu(rs.getInt("id"));
					s.setMarkaSamochodu(rs.getString("marka"));
					s.setModelSamochodu(rs.getString("model"));
					s.setNrRejestracyjnySamochodu(rs.getString("nrRejestracyjny"));
					s.setPrzebiegSamochodu(rs.getInt("przebieg"));
					s.setCenaWypozyczenia(rs.getDouble("cenaWypozyczenia"));
						
					listaSamochodow.add(s);
				}
					
				rs.close();
				stmt.close();
				
				if(listaSamochodow.size() == 0) return null;
					
				System.out.println();
				return listaSamochodow;
			}
			
			
			//--------- set czyWypozyczony i czyZepsuty ------------
			
			public void czyWypozyczony(Samochod samochod) throws Exception {
				if(samochod == null)
					throw new Exception("* B³¹d przy próbie wypozyczenia lud oddania samochodu *");
				
				String updateQuery = "UPDATE samochody SET czyWypozyczony = ? WHERE id = ?";
				
				PreparedStatement stmt = this.conn.prepareStatement(updateQuery);
				stmt.setBoolean(1, samochod.isCzyWypozyczony());
				stmt.setInt(2, samochod.getIdSamochodu());
				
				stmt.execute();
				stmt.close();
			}
			
			public void czyZepsuty(Samochod samochod) throws Exception {
				if(samochod == null)
					throw new Exception("* B³¹d przy odbiorze lub oddaniu do warsztatu *");
				
				String updateQuery = "UPDATE samochody SET czyZepsuty = ? WHERE id = ?";
				
				PreparedStatement stmt = this.conn.prepareStatement(updateQuery);
				stmt.setBoolean(1, samochod.isCzyZepsuty());
				stmt.setInt(2, samochod.getIdSamochodu());
				
				stmt.execute();
				stmt.close();
			}
			
			//------  aktualizuj przebieg  ---------------
			
			public void editPrzebieg(Samochod samochod) throws Exception {
				if(samochod == null)
					throw new Exception("* B³¹d przy aktualizacji przebiegu samochodu *");
				
				String updateQuery = "UPDATE samochody SET przebieg = ? WHERE id = ?";
				
				PreparedStatement stmt = this.conn.prepareStatement(updateQuery);
				stmt.setInt(1, samochod.getPrzebiegSamochodu());
				stmt.setInt(2, samochod.getIdSamochodu());
				
				stmt.execute();
				stmt.close();
			}

}
