package wypozyczalnia.dane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import wypozyczalnia.klasy.Wypozycz;

public class WypozyczDAO extends Database {
	
	public WypozyczDAO() throws Exception {
		super();
	}
	
	
	//------------------- Dodanie zlecenia ------------------------------------
	
	public void dodajZlecenie(Wypozycz wypozycz) throws Exception {
		
		if(wypozycz == null)
			throw new Exception("Nie mo¿na zapisaæ obiektu , który nie istnieje!");
		
		String insertQuery = "INSERT INTO wypozyczone(idKlient, idSamochod, kwotaDoZaplaty, dataWypozycz, jakDlugo) VALUES(?,?,?,?,?)";
		
		PreparedStatement stmt = this.conn.prepareStatement(insertQuery);
		stmt.setInt(1, wypozycz.getIdKlient());
		stmt.setInt(2, wypozycz.getIdSamochod());
		stmt.setDouble(3, wypozycz.getKwotaDoZaplaty());
		stmt.setLong(4, wypozycz.getDataWypozycz()); //(4,(Date) wypozycz.getDataWypozycz());
		stmt.setInt(5, wypozycz.getJakDlugo());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("*** Pomyœlnie dodano zlecenie do bazy danych ***");
	}
	
	//-------------- Lista zleceñ ---------------------------
	
	public List<Wypozycz> pobierzZlecenia(int id) throws Exception {
		List<Wypozycz> listaZlecen = new ArrayList<Wypozycz>();
			
		String selectQuery = "SELECT * FROM wypozyczone";
		
		if(id > 0) selectQuery += " WHERE id = ?";
		
		PreparedStatement stmt = this.conn.prepareStatement(selectQuery);
		
		if(id > 0) stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			Wypozycz w = new Wypozycz();
			w.setIdWypozycz(rs.getInt("id"));
			w.setIdKlient(rs.getInt("idKlient"));
			w.setIdSamochod(rs.getInt("idSamochod"));
			w.setKwotaDoZaplaty(rs.getDouble("kwotaDoZaplaty"));
			w.setDataWypozycz(rs.getLong("dataWypozycz"));
			w.setJakDlugo(rs.getInt("jakDlugo"));
				
			listaZlecen.add(w);
		}
			
		rs.close();
		stmt.close();
			
		return listaZlecen;
	}	
	
	//----------------- Zlecenie -----------------------------
	
		public Wypozycz pobierzZlecenie(int id) throws Exception  {
			List<Wypozycz> listaZlecen = this.pobierzZlecenia(id);
			
			if(listaZlecen.size() > 0) return listaZlecen.get(0);
			
			else return null;
		}
		
	//------------------------ Usuñ zlecenie -----------------------------------
		
	public void usunZlecenie(int id) throws Exception {
		if(id == 0)
			throw new Exception("Nale¿y podaæ ID usuwanego zlecenia!");
			
		String deleteQuery = "DELETE FROM wypozyczone WHERE id = ?";
			
		PreparedStatement stmt = this.conn.prepareStatement(deleteQuery);
		stmt.setInt(1, id);
			
		stmt.execute();
		stmt.close();
	}

}
