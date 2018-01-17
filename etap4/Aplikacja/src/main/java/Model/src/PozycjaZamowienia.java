import java.time.LocalDate;

/**
 *  Klasa, reprezentujaca obiekt pozycji zamowienia z modelu.
 */
public class PozycjaZamowienia {

	private Zapytanie zapytanie;
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
		setCena();
	}
	PozycjaZamowienia(int id,Towar produktZamawiany,int ilosc, int rabat,LocalDate terminRealizacji){
		this.id = id;
		this.towar = produktZamawiany;
		this.ilosc = ilosc;
		this.rabat = rabat;
		this.terminRealizacji = terminRealizacji;
		setCena();
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

	/**
	 * Metoda dodajaca powstale zapytanie do pozycji zamowienia
	 * @param zapytanie - zapytanie do dodania
	 */
	public void dodajZapytanie(Zapytanie zapytanie) {
		this.zapytanie = zapytanie;
	}

	/**
	 * @return zwraca id pozycji zamowienia
	 */
	public int getId(){
		return id;
	}

	/**
	 * Metoda, ustawiajaca ilosc pozycji zamowienia
	 * @param ilosc - liczba sztuk towaru w danej pozycji
	 */
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	/**
	 * Metoda, ustawiajaca rabat danej pozycji
	 * @param rabat - procentowy rabat na towar w pozycji
	 */
	public void setRabat(int rabat) {
		this.rabat = rabat;
	}

	/**
	 * @return zwraca towar pozycji
	 */
	public Towar getTowar() {
		return towar;
	}

	/**
	 * Metoda, ustawiajaca termin realizacji pozycji
	 * @param terminRealizacji - ustalony termin realizacji dla danej pozycji
	 */
	public void setTerminRealizacji(LocalDate terminRealizacji){
		this.terminRealizacji = terminRealizacji;
	}

	/**
	 * @return zwraca termin realizacji pozycji
	 */
	public LocalDate getTerminRealizacji(){
		return terminRealizacji;
	}

	/**
	 * Metoda, wyliczajaca kwote pozycji zamowienia na podstawie rabatu i ilosci towaru
	 */
	public void setCena() {
		this.cena = this.towar.getCenaJn() * this.ilosc;
		this.cenaPoRabacie = cena - (cena * rabat / 100);
	}

	/**
	 * @return zwraca cene towaru bez uwzglednienia rabatu
	 */
	public double getCena() {
		return cena;
	}

	/**
	 * @return zwraca ilosc towaru w pozycji
	 */
	public int getIlosc() {
		return ilosc;
	}

	/**
	 * @return zwraca zamowienie, do ktorego odnosi sie pozycja
	 */
	public Zamowienie getZamowienie() {
		return zamowienie;
	}

	/**
	 * @param zamowienie - zamowienie, do ktorego przypisujemy dana pozycje
	 */
	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}

	public String toString(){
		return "Pozycja: " + ilosc + "," + "cena";
	}

	/**
	 * @return zwraca rabat na dana pozycje
	 */
	public int getRabat() {
		return rabat;
	}

	/**
	 * @return zwraca wartosc pozycji z uwzglednieniem rabatu
	 */
	public double getCenaPoRabacie() {
		return cenaPoRabacie;
	}

	/**
	 * @return zwraca zapytanie, ktore dotyczny danej pozycji
	 */
	public Zapytanie getZapytanie() {
		return zapytanie;
	}

	public void setZapytanie(Zapytanie zapytanie) {
		this.zapytanie=zapytanie;
		
	}
}