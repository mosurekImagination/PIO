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
		aktualizuj(getSize()-1);
	}

	public void usunPozycje(int indexPozycji) {
		pozycjeZamowienia.remove(indexPozycji);
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

	public void aktualizuj(int indexPozycji) {
		aktualizujKwote(indexPozycji);
		aktualizujTerminRealizacji(indexPozycji);
	}

	private void aktualizujTerminRealizacji(int indexPozycji) {
		PozycjaZamowienia pozycja = getPozycjaOnIndex(indexPozycji);
		if (pozycja.getTerminRealizacji() == null) {terminRealizacji = null;}
		else {ustalTerminRealizacji(pozycja.getTerminRealizacji());}
	}

	public void ustalTerminRealizacji(LocalDate data) {
		if (data.isAfter(terminRealizacji)) terminRealizacji = data;
	}

	private void aktualizujKwote(int indexPozycji) {
		float wartosc = getPozycjaOnIndex(indexPozycji).getCena();
		this.kwota += wartosc;
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
}