import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.now;

/**
 * Klasa, ktora reprezentuje obiekt Zamowienia z modelu
 */
public class Zamowienie{

	private int id;
	private StatusZamowienia status;
	private LocalDate dataZlozZam;
	private LocalDate terminRealizacji;
	private double kwota;
	private boolean czyZatwierdzone;
	private List<PozycjaZamowienia> pozycjeZamowienia;
	Klient klient;

	Zamowienie(){
		this.terminRealizacji = now();
		this.dataZlozZam = now();
		this.kwota = 0;
		this.status = StatusZamowienia.otwarte;
		this.pozycjeZamowienia = new ArrayList<>();
	}
	
	Zamowienie(int id, StatusZamowienia status, LocalDate dataZlozZam, LocalDate terminRealizacji, double kwota, boolean czyZatwierdzone){
		this.id=id;
		this.status=status;
		this.dataZlozZam=dataZlozZam;
		this.terminRealizacji=terminRealizacji;
		this.kwota=kwota;
		this.czyZatwierdzone=czyZatwierdzone;
		this.pozycjeZamowienia = new ArrayList<>();
	}

	/**
	 * Dodaje pozycje do zamowienia i aktualizuje zamowienie o dodana pozycje.
	 *
	 * @param pozycja pozycja do dodania
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		pozycjeZamowienia.add(pozycja);
		aktualizuj();
	}

	/**
	 * Metoda usuwa zadana pozycje z zamowienia
	 * @param indexPozycji - indeks pozycji, ktora ma zostac usunieta
	 * @return zwraca usunieta pozycje
	 */
	public PozycjaZamowienia usunPozycje(int indexPozycji) {
		PozycjaZamowienia pozycja = pozycjeZamowienia.remove(indexPozycji);
		aktualizuj();
		return pozycja;
	}

	/**
	 * @return zwraca nastepny wolny indeks w pozycjach zamowienia
	 */
	public int getNextId(){
		return pozycjeZamowienia.size();
	}

	/**
	 *
	 * Metoda aktualizujaca ilosc i rabat towaru w danej pozycji
	 * @param indexPozycji - indeks pozycji, ktora ma zostac zaktualizowana
	 * @param ilosc - ilosc towaru, jaka ma zostac dodana do pozycji
	 * @param rabat -  rabat, jaki ma byc nalozony na towar w danej pozycji
	 */
	public void aktualizujPozycje(int indexPozycji, int ilosc, int rabat) {
		if(indexPozycji < pozycjeZamowienia.size()){
			PozycjaZamowienia pozycja = pozycjeZamowienia.get(indexPozycji);
			pozycja.setIlosc(ilosc);
			pozycja.setRabat(rabat);
			pozycja.setCena();
		} else {
			System.out.println("Podano zly indeks!");
		}

	}

	/**
	 * @param indexPozycji - indeks pozycji, ktora ma zostac zwrocona
	 * @return zwraca pozycje na zadanym indeksie w zamowieniu
	 */
	public PozycjaZamowienia getPozycjaOnIndex(int indexPozycji){
		return pozycjeZamowienia.get(indexPozycji);
	}

	/**
	 * Metoda, dodajaca zapytanie do podanej pozycji
	 *
	 * @param indexPozycji - indeks pozycji, do ktorej ma zostac dodane zapytanie
	 * @param zapytanie - zapytanie, ktore ma zostac dodane do pozycji
	 * @return zwraca dodane zapytanie
	 */
	public Zapytanie dodajZapytanieDoPozycji(int indexPozycji, Zapytanie zapytanie) {
		pozycjeZamowienia.get(indexPozycji).dodajZapytanie(zapytanie);
		zapytanie.setPozycja(pozycjeZamowienia.get(indexPozycji));
		return zapytanie;
	}

	/**
	 * Metoda, ustawiajaca klienta zamowienia
	 * @param klient - klient, ktory ma zostac dodany do zamowienia
	 */
	public void dodajKlienta(Klient klient) {
		this.klient = klient;
	}

	/**
	 * Metoda, ktora aktualizuje kwote i termin realizacji zamowienia
	 */
	public void aktualizuj() {
		aktualizujKwote();
		aktualizujTerminRealizacji();
	}

	/**
	 * Metoda, ktora na podstawie pozycji zamowienia ustala jego termin realizacji
	 */
	private void aktualizujTerminRealizacji() {
		terminRealizacji = now();
		for(PozycjaZamowienia pozycja:pozycjeZamowienia) {
			LocalDate dataPozycji = pozycja.getTerminRealizacji();
			if (dataPozycji == null) {
				terminRealizacji = null;
			} else if(terminRealizacji != null && isAfterTermin(dataPozycji)) {
				terminRealizacji = pozycja.getTerminRealizacji();
			}
		}
	}

	/**
	 * Metoda sprawdzajaca czy podana data jest pozniejsza niz termin realizacji zamowienia
	 * @param data - data do sprawdzenia
	 * @return zwraca czy data jest pozniej niz termin realizacji
	 */
	public boolean isAfterTermin(LocalDate data){
		return data.isAfter(terminRealizacji);
	}

	/**
	 * Metoda, ktora na podstawie nowej daty ustala termin realizacji
	 * @param data - data, ktora chcemy porownac z aktualnym terminem realizacji
	 */
	public void ustalTerminRealizacji(LocalDate data) {
		if (terminRealizacji == null || data.isAfter(terminRealizacji)) terminRealizacji = data;
	}

	/**
	 * Metoda, ktora aktualizuje kwote zamowienia na podstawie pozycji zamowienia
	 */
	private void aktualizujKwote() {
		kwota = 0;
		for (PozycjaZamowienia pozycja:pozycjeZamowienia) {
			kwota += pozycja.getCenaPoRabacie();
		}
	}

	/**
	 * @return zwraca ilosc pozycji zamowienia w zamowieniu
	 */
	public int getSize(){
		return pozycjeZamowienia.size();
	}

	/**
	 * @return zwraca termin realizacji zamowienia
	 */
	public LocalDate getTerminRealizacji(){
		return terminRealizacji;
	}

	/**
	 * @return zwraca indeks ostatnio dodanej pozycji
	 */
	public int getIndexOstatniejPozycji() {
		return getSize()-1;
	}

	/**
	 * @return zwraca liste, zawierajaca pozycje zamowienia
	 */
	public List<PozycjaZamowienia> getPozycje() {
		return pozycjeZamowienia;
	}

	/**
	 * Metoda, laczaca sie z klasa, ktora odpowiada za przeslanie zamowienia do bazy
	 */
	public void przeslijDoBazy() {
		Context context = new Context();
		context.openDB();
		ZamowienieContext zc = new ZamowienieContext(context);
		zc.przeslijZamowienie(this);
		
	}

	/**
	 * @return zwraca kwote zamowienia
	 */
	public double getSuma() {
		return kwota;
	}

	/**
	 * @return zwraca klienta zamowienia
	 */
	public Klient getKlient() {
		return klient;
	}

	/**
	 * Zmienia status zamowienia na zadany
	 * @param status - status zamowienia, jaki ma miec zamowienie
	 */
	public void setStatusZamowienia(StatusZamowienia status) {
		this.status = status;
	}

	/**
	 * @param klient - klient, zamawiajacy dane zamowienie
	 */
	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	/**
	 * @return zwraca biezacy status zamowienia
	 */
	public StatusZamowienia getStatus() {
		return status;
	}

	/**
	 * @return zwraca date zlozenia zamowienia
	 */
	public LocalDate getDataZlozZam() {
		return dataZlozZam;
	}

	/**
	 * @return zwraca informacje czy zamowienie zostalo zatwierdzone
	 */
	public boolean getCzyZatwierdzone() {
		return czyZatwierdzone;
	}

	/**
	 * @return zwraca id zamowienia
	 */
	public int getId() {
		return id;
	}
}