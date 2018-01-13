import java.time.LocalDate;
import java.util.Date;

public class PozycjaZamowienia {

	Zapytanie zapytanie;
	private Towar towar;
	private int id;
	private int ilosc;
	private double cena;
	private int rabat;
	private double cenaPoRabacie;
	private LocalDate terminRealizacji;
	private Zamowienie zamowienie;

	PozycjaZamowienia(Towar produktZamawiany){
		this.towar = produktZamawiany;
	}

	PozycjaZamowienia(int id,Towar produktZamawiany,int ilosc, int rabat){
		this.id = id;
		this.towar = produktZamawiany;
		this.ilosc = ilosc;
		this.rabat = rabat;
	}
	PozycjaZamowienia(int id,int ilosc,double cena, int rabat, double cenaPoRabacie, LocalDate terminRealizacji,Towar towar){
		this.id=id;
		this.ilosc=ilosc;
		this.cena=cena;
		this.rabat=rabat;
		this.cenaPoRabacie=cenaPoRabacie;
		this.terminRealizacji=terminRealizacji;
		this.towar=towar;
		
	}

	public void dodajDane(int ilosc, int rabat, Towar towar) {
		this.ilosc = ilosc;
		this.rabat = rabat;
		this.towar = towar;
	}

	public void dodajZapytanie(Zapytanie zapytanie) {
		this.zapytanie = zapytanie;
	}

	public void zmienDateRealizacji(LocalDate data) {
		this.terminRealizacji = data;
	}

	public int getId(){
		return id;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public void setRabat(int rabat) {
		this.rabat = rabat;
	}

	public Towar getTowar() {
		return towar;
	}

	public void setTerminRealizacji(LocalDate terminRealizacji){
		this.terminRealizacji = terminRealizacji;
	}

	public LocalDate getTerminRealizacji(){
		return terminRealizacji;
	}

	public void setCena() {
		this.cena = this.towar.getCenaJn() * this.ilosc;
		this.cenaPoRabacie = cena - (cena * rabat / 100);
	}

	public double getCena() {
		return cena;
	}

	public int getIlosc() {
		return ilosc;
	}

	public Zamowienie getZamowienie() {
		return zamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}

	public String toString(){
		return "Pozycja: " + ilosc + "," + "cena";
	}

	public int getRabat() {
		return rabat;
	}

	public double getCenaPoRabacie() {
		return cenaPoRabacie;
	}

	public Zapytanie getZapytanie() {
		return zapytanie;
	}

	public void setZapytanie(Zapytanie zapytanie) {
		this.zapytanie=zapytanie;
		
	}
}