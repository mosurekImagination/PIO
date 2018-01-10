import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

/**
 * Klasa realizująca połączenie się z obiektami modelu pozycji zamówienia i przechowywaniem ich.
 */

public class PozycjeZamowieniaRepository extends Observable {

	private PozycjaZamowienia pozycja;


	public void usunPozycje(int idPozycji) {
		// TODO - implement PozycjeZamowieniaRepository.usunPozycje
		throw new UnsupportedOperationException();
	}


	public void modyfikujPozycje(int idPozycji, int ilosc, float rabat) {
		// TODO - implement PozycjeZamowieniaRepository.modyfikujPozycje
		throw new UnsupportedOperationException();
	}

	public void przeslijPozycje() {
		// TODO - implement PozycjeZamowieniaRepository.przeslijPozycje
		throw new UnsupportedOperationException();
	}

	public void zmienDateRealizacji(int idPozycji, Date data) {
		// TODO - implement PozycjeZamowieniaRepository.zmienDateRealizacji
		throw new UnsupportedOperationException();
	}

	public void setPozycja(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
	}

	public PozycjaZamowienia getPozycja() {
		return pozycja;
	}

	public void dodajZapytanie(Zapytanie zapytanie) {
		pozycja.dodajZapytanie(zapytanie);
		setChanged();
		notifyObservers();
	}

	public void utworzPozycjeZamowienia(Towar towar) {
		PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
		this.pozycja = pozycja;
	}

	public void aktualizujPozycje(int iIlosc, int iRabat) {
//		int indexPozycji = zamowienie.getIndexOstatniejPozycji();
//		czyMoznaZamowic = sprawdzDostepnoscTowaru(indexPozycji, ilosc);
//		zamowienie.aktualizujPozycje(indexPozycji, ilosc, rabat);
//		if (czyMoznaZamowic) zamowienie.getPozycjaOnIndex(indexPozycji).setTerminRealizacji(LocalDate.now());
//		zamowienie.aktualizuj();
//		notifyObservers();
	}
}