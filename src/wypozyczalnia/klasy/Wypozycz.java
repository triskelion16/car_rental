package wypozyczalnia.klasy;

public class Wypozycz {
	private int idWypozycz;
	private int idKlient;  
	private int idSamochod;
	private double kwotaDoZaplaty;
	private long dataWypozycz;
	private int jakDlugo;
	
	
	public Wypozycz () throws Exception { }
	
	public Wypozycz (int idKlient, int idSamochod) throws Exception {
		this.setIdKlient(idKlient);
		this.setIdSamochod(idSamochod);
	}

	
	public int getIdWypozycz() {
		return idWypozycz;
	}

	public void setIdWypozycz(int idWypozycz) {
		this.idWypozycz = idWypozycz;
	}
	
	public int getIdKlient() {
		return idKlient;
	}

	public void setIdKlient(int idKlient) {
		this.idKlient = idKlient;
	}

	public int getIdSamochod() {
		return idSamochod;
	}

	public void setIdSamochod(int idSamochod) {
		this.idSamochod = idSamochod;
	}

	public double getKwotaDoZaplaty() {
		return kwotaDoZaplaty;
	}

	public void setKwotaDoZaplaty(double kwotaDoZaplaty) {
		this.kwotaDoZaplaty = kwotaDoZaplaty;
	}

	public long getDataWypozycz() {
		return dataWypozycz;
	}

	public void setDataWypozycz(long dataWypozycz) {
		this.dataWypozycz = dataWypozycz;
	}

	public int getJakDlugo() {
		return jakDlugo;
	}

	public void setJakDlugo(int jakDlugo) {
		this.jakDlugo = jakDlugo;
	}

}
