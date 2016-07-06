package wypozyczalnia.service;

import java.util.List;

import wypozyczalnia.dane.SamochodDAO;
import wypozyczalnia.klasy.Samochod;

public class SamochodService {
private SamochodDAO baza;
	
	public SamochodService() throws Exception {
		this.baza = new SamochodDAO();
	}
	
	
	public void dodajSamochod(Samochod s) throws Exception {
		this.baza.dodajSamochod(s);
	}
	
	public void aktualizujSamochod(Samochod s) throws Exception {
		this.baza.aktualizujSamochod(s);
	}
	
	public void usunSamochod(int id) throws Exception {
		this.baza.usunSamochod(id);
	}
	
	public void pobierzSamochody() throws Exception {             
		List<Samochod> listaSamochodow = baza.pobierzSamochody(0);
		
		System.out.println("\n***     Lista wszystkich samochodów     ***");
		System.out.println("--------------------------------\n");
		System.out.println("ID\tMarka\t\tModel\t\tNr rejestracyjny\tPrzebieg\tCena/dobê\tWypo¿yczony\tZepsuty\n");
		
		for(Samochod samochod: listaSamochodow) {
			System.out.print(samochod.getIdSamochodu() + "\t");
			System.out.print(samochod.getMarkaSamochodu() + "\t\t");
			System.out.print(samochod.getModelSamochodu() + "\t\t");
			System.out.print(samochod.getNrRejestracyjnySamochodu() + "\t\t");
			System.out.print(samochod.getPrzebiegSamochodu() + "\t\t");
			System.out.print(samochod.getCenaWypozyczenia() + "\t\t");
			System.out.print(samochod.isCzyWypozyczony() + "\t\t");
			System.out.println(samochod.isCzyZepsuty());
		}
	}
	
	public Samochod pobierzSamochod(int id) throws Exception {
		return this.baza.pobierzSamochod(id);
	}
	
	public boolean pobierzDostepneSamochody() throws Exception {
		boolean dostepne = true;
		List<Samochod> listaSamochodow = baza.pobierzDostepneSamochody();
		
		if(listaSamochodow == null) {
			System.out.println("* ¯aden samochód nie jest dostêpny *");
			dostepne = false;
		}
		
		else{
			System.out.println("\n***     Lista dostêpnych samochodów     ***");
			System.out.println("--------------------------------\n");
			System.out.println("ID\tMarka\t\tModel\t\tNr rejestracyjny\tPrzebieg\tCena/dobê\n");
			for(Samochod samochod: listaSamochodow) {				
				System.out.print(samochod.getIdSamochodu() + "\t");
				System.out.print(samochod.getMarkaSamochodu() + "\t\t");
				System.out.print(samochod.getModelSamochodu() + "\t\t");
				System.out.print(samochod.getNrRejestracyjnySamochodu() + "\t\t");
				System.out.print(samochod.getPrzebiegSamochodu() + "\t\t");
				System.out.println(samochod.getCenaWypozyczenia());
			}
		}
		return dostepne;
	}
	
	public List<Samochod> pobierzWypozyczoneSamochody() throws Exception {
		return this.baza.pobierzWypozyczoneSamochody();
	}
	
	public List<Samochod> pobierzZepsuteSamochody() throws Exception {
		return this.baza.pobierzZepsuteSamochody();
	}
	
	
	public void czyWypozyczony(Samochod s) throws Exception {
		this.baza.czyWypozyczony(s);
	}
	
	public void czyZepsuty(Samochod s) throws Exception {
		this.baza.czyZepsuty(s);
	}
	
	public void editPrzebieg(Samochod s) throws Exception {
		this.baza.editPrzebieg(s);
	}
}
