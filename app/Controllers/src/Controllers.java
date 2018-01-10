import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


public class Controllers {

	KlienciRepository klienci;
	PozycjeZamowieniaRepository pozycjaZamowienia;
	TowaryRepository towar;
	ZamowieniaRepository zamowienia;
	ZapytaniaRepository zapytania;



	public void dodajNowegoKlienta() {
		// TODO - implement Controllers.dodajNowegoKlienta
		throw new UnsupportedOperationException();
	}

	public void utworzNoweZamowienie() {
		// TODO - implement Controllers.utworzNoweZamowienie
		throw new UnsupportedOperationException();
	}

	public void utworzNowegoKlienta() {
		// TODO - implement Controllers.utworzNowegoKlienta
		throw new UnsupportedOperationException();
	}

	public void utworzPozycjeZamowienia() {
		// TODO - implement Controllers.utworzPozycjeZamowienia
		throw new UnsupportedOperationException();
	}

	public void utworzNoweZapytanie(LocalDate terminRealizacji) {
		// TODO - to powinno tworzyć nowe zapytanie z datą, dodać je do bieżącej pozycji i zamówienia
		zamowienia.dodajZapytanie(getPozycjaId(),terminRealizacji);
	}

	public void usunPozycje() {
		// TODO - implement Controllers.usunPozycje
		throw new UnsupportedOperationException();
	}

	public void utworzListeTowarow() {
		// TODO - implement Controllers.utworzListeTowarow
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idTowaru
	 * @param ilosc
	 * @param rabat
	 */
	public void obsluzPozycjeZamowienia(int idTowaru, int ilosc, float rabat) {
		// TODO - implement Controllers.obsluzPozycjeZamowienia
		throw new UnsupportedOperationException();
	}

	public void wyswietlOknoModyfikacji() {
		// TODO - implement Controllers.wyswietlOknoModyfikacji
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param ilosc
	 * @param rabat
	 * @param idPozycji
	 */
	public void obsluzModyfikacjePozycji(int ilosc, float rabat, int idPozycji) {
		// TODO - implement Controllers.obsluzModyfikacjePozycji
		throw new UnsupportedOperationException();
	}

	public void wyswietlPozycjeDoZaznaczenia() {
		// TODO - implement Controllers.wyswietlPozycjeDoZaznaczenia
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param pozycjeZamowienia
	 */
	public void utworzNoweZamowienie(PozycjaZamowienia pozycjeZamowienia) {
		// TODO - implement Controllers.utworzNoweZamowienie
		throw new UnsupportedOperationException();
	}

	public void przeslijDane() {
		// TODO - implement Controllers.przeslijDane
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idZapytania
	 * @param status
	 */
	public void zmienStatusZapytania(int idZapytania, StatusZapytania status) {
		// TODO - implement Controllers.zmienStatusZapytania
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idZapytania
	 */
	public void wyswietlZapytanie(int idZapytania) {
		// TODO - implement Controllers.wyswietlZapytanie
		throw new UnsupportedOperationException();
	}

/*	public String wybierzTowar() {
		//ListaTowarow listaTowarowView = new ListaTowarow();
		//listaTowarowView.wyswietlListeTowarow();
		Towar towar = listaTowarowView.przekazTowar();
		PozycjaZamowienia pozycja = zamowienia.utworzPozycjeZamowienia(towar);
		pozycjaZamowienia.setPozycja(pozycja);
		return towar.toString();
	}*/

	public void dodajPozycje(int ilosc, int rabat) {
		zamowienia.aktualizujPozycje(ilosc,rabat);
	}

	private int getPozycjaId(){
		return pozycjaZamowienia.getPozycja().getId();
	}

	public void anulujOstatniaPozycje() {
		zamowienia.usunPozycje(getPozycjaId());
	}

	public void dodajKlientaDoZamowienia(Klient klient) {
		zamowienia.dodajKlienta(klient);
	}
}