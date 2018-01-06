import java.time.LocalDate;
import java.util.Date;

public class PozycjaZamowienia {

	Zapytanie zapytanie;
	private Towar towar;
	private int id;
	private int ilosc;
	private float cena;
	private float rabat;
	private float cenaPoRabacie;
	private LocalDate terminRealizacji;

	PozycjaZamowienia(int id,Towar produktZamawiany){
		this.id = id;
		this.towar = produktZamawiany;
	}

	PozycjaZamowienia(int id,Towar produktZamawiany,int ilosc, float rabat){
		this.id = id;
		this.towar = produktZamawiany;
		this.ilosc = ilosc;
		this.rabat = rabat;
	}

	public void dodajDane(int ilosc, float rabat, Towar towar) {
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
	}

	public float getCena() {
		return cena;
	}
}