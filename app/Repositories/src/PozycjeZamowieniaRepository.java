import java.util.Date;

/**
 * Klasa realizująca połączenie się z obiektami modelu pozycji zamówienia i przechowywaniem ich.
 */

public class PozycjeZamowieniaRepository {

	private PozycjaZamowienia pozycja;


	public void usunPozycje(int idPozycji) {
		// TODO - implement PozycjeZamowieniaRepository.usunPozycje
		throw new UnsupportedOperationException();
	}


	public void dodajPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement PozycjeZamowieniaRepository.dodajPozycje
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
}