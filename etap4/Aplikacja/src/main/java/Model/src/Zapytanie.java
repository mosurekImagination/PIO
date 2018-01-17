import java.time.LocalDate;

/**
 * Klasa, reprezentujaca obiekt zapytania z modelu
 */
public class Zapytanie {

	private static int current_id = 0;
	private int id;
	private StatusZapytania status;
	private LocalDate terminReal;
	private String notatka;
	private PozycjaZamowienia pozycja;
	private int ilosc;

	public Zapytanie(LocalDate terminRealizacji,PozycjaZamowienia pozycja) {
		this.id = current_id;
		this.terminReal = terminRealizacji;
		this.pozycja = pozycja;
		this.ilosc = pozycja.getIlosc();
		this.status = StatusZapytania.zopiniowane;
		current_id++;
	}

	public Zapytanie(int id, StatusZapytania status, LocalDate terminReal, String notatka, PozycjaZamowienia pozycja) {
		this.id=id;
		this.status=status;
		this.terminReal=terminReal;
		this.notatka=notatka;
		this.id=id;
		this.pozycja=pozycja;
	}
	
	public Zapytanie(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
		this.ilosc = pozycja.getIlosc();
	}

	public Zapytanie(LocalDate terminRealizacji){
		this.terminReal = terminRealizacji;
	}


	/**
	 * Metoda, ustawiajaca pozycje, ktorej dotyczy zapytanie
	 * @param pozycja - pozycja, ktorej dotyczy zapytanie
	 */
	public void setPozycja(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
	}

	/**
	 * Metoda sluzaca do zmiany statusu zapytania
	 * @param status - nowy status zapytania
	 */
	public void zmienStatusZapytania(StatusZapytania status) {
		this.status = status;
	}

	/**
	 * @return zwraca status zapytania
	 */
	public StatusZapytania getStatus() {
		return status;
	}

	/**
	 * @return zwraca termin realizacji umieszczony w zapytaniu
	 */
	public LocalDate getTerminRealizacji() {
		return terminReal;
	}

	/**
	 * @return zwraca towar, do ktorego odnosi sie zapytanie
	 */
	public Towar getTowar() {
		return pozycja.getTowar();
	}

	/**
	 * @param ilosc - ilosc sztuk, ktorej dotyczy zapytanie
	 */
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	/**
	 * @return zwraca id zapytania
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return zwraca pozycje zamowienia, ktorej dotyczy zapytanie
	 */
	public PozycjaZamowienia getPozycja() {
		return pozycja;
	}

	/**
	 * @return zwraca notatke dodana do zapytania
	 */
	public String getNotatka() {
		return notatka;
	}

}