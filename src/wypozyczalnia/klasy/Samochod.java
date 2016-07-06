package wypozyczalnia.klasy;

public class Samochod {
	private int idSamochodu;
	private String markaSamochodu;
	private String modelSamochodu;
	private String nrRejestracyjnySamochodu;
	private int przebiegSamochodu;
	private double cenaWypozyczenia;
	private boolean czyWypozyczony;
	private boolean czyZepsuty;
	
	
	public int getIdSamochodu() {
		return idSamochodu;
	}

	public void setIdSamochodu(int idSamochodu) {
		this.idSamochodu = idSamochodu;
	}

	public String getMarkaSamochodu() {
		return markaSamochodu;
	}

	public void setMarkaSamochodu(String markaSamochodu) {
		this.markaSamochodu = markaSamochodu;
	}

	public String getModelSamochodu() {
		return modelSamochodu;
	}

	public void setModelSamochodu(String modelSamochodu) {
		this.modelSamochodu = modelSamochodu;
	}

	public String getNrRejestracyjnySamochodu() {
		return nrRejestracyjnySamochodu;
	}

	public void setNrRejestracyjnySamochodu(String nrRejestracyjnySamochodu) {
		this.nrRejestracyjnySamochodu = nrRejestracyjnySamochodu;
	}
	
	public int getPrzebiegSamochodu() {
		return przebiegSamochodu;
	}

	public void setPrzebiegSamochodu(int przebiegSamochodu) {
		this.przebiegSamochodu = przebiegSamochodu;
	}
	
	public double getCenaWypozyczenia() {
		return cenaWypozyczenia;
	}

	public void setCenaWypozyczenia(double cenaWypozyczenia) {
		this.cenaWypozyczenia = cenaWypozyczenia;
	}

	public boolean isCzyWypozyczony() {
		return czyWypozyczony;
	}

	public void setCzyWypozyczony(boolean czyWypozyczony) {
		this.czyWypozyczony = czyWypozyczony;
	}

	public boolean isCzyZepsuty() {
		return czyZepsuty;
	}

	public void setCzyZepsuty(boolean czyZepsuty) {
		this.czyZepsuty = czyZepsuty;
	}
	
}
