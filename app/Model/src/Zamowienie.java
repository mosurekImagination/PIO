import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.now;

public class Zamowienie{

	private int id;
	private StatusZamowienia status;
	private LocalDate dataZlozZam;
	private LocalDate terminRealizacji;
	private float kwota;
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

	/**
	 *
	 * @param status
	 */
	public void zmienStatus(StatusZamowienia status) {
		// TODO - implement Zamowienie.zmienStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * Dodaje pozycję do zamówienia i aktualizuje zamówienie o dodaną pozycję.
	 *
	 * @param pozycja pozycja do dodania
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		pozycjeZamowienia.add(pozycja);
	}

	public void usunPozycje(int indexPozycji) {
		pozycjeZamowienia.remove(indexPozycji);
		aktualizuj();
	}

	public int getNextId(){
		return pozycjeZamowienia.size();
	}

	public void aktualizujPozycje(int indexPozycji, int ilosc, int rabat) {
		PozycjaZamowienia pozycja = pozycjeZamowienia.get(indexPozycji);
		pozycja.setIlosc(ilosc);
		pozycja.setRabat(rabat);
		pozycja.setCena();
	}

	public PozycjaZamowienia getPozycjaOnIndex(int indexPozycji){
		return pozycjeZamowienia.get(indexPozycji);
	}

	public Zapytanie dodajZapytanieDoPozycji(int indexPozycji, Zapytanie zapytanie) {
		pozycjeZamowienia.get(indexPozycji).dodajZapytanie(zapytanie);
		zapytanie.setPozycja(pozycjeZamowienia.get(indexPozycji));
		return zapytanie;
	}

	public void dodajKlienta(Klient klient) {
		this.klient = klient;
	}

	public void aktualizuj() {
		aktualizujKwote();
		aktualizujTerminRealizacji();
	}

	private void aktualizujTerminRealizacji() {
		for(PozycjaZamowienia pozycja:pozycjeZamowienia) {
			LocalDate dataPozycji = pozycja.getTerminRealizacji();
			if (dataPozycji == null) {
				terminRealizacji = null;
			} else if(terminRealizacji != null && isAfterTermin(dataPozycji)) {
				terminRealizacji = pozycja.getTerminRealizacji();
			}
		}
	}

	public boolean isAfterTermin(LocalDate data){
		return data.isAfter(terminRealizacji);
	}

	public void ustalTerminRealizacji(LocalDate data) {
		if (terminRealizacji == null || data.isAfter(terminRealizacji)) terminRealizacji = data;
	}

	private void aktualizujKwote() {
		kwota = 0;
		for (PozycjaZamowienia pozycja:pozycjeZamowienia) {
			kwota += pozycja.getCena();
		}
	}

	public List<PozycjaZamowienia> getPozycjeZamowienia() {
		return pozycjeZamowienia;
	}

	public int getSize(){
		return pozycjeZamowienia.size();
	}

	public LocalDate getTerminRealizacji(){
		return terminRealizacji;
	}

	public int getIndexOstatniejPozycji() {
		return getSize()-1;
	}

	public List<PozycjaZamowienia> getPozycje() {
		return pozycjeZamowienia;
	}

	public void przeslijDoBazy() {
		//Tu wysyłamy stworzone zamówienie do bazy
	}

	public float getSuma() {
		return kwota;
	}

	public Klient getKlient() {
		return klient;
	}
}