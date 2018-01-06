import java.util.Date;

public class PozycjeZamowieniaRepository {

	private PozycjaZamowienia pozycja;

	/**
	 *
	 * @param idPozycji
	 */
	public void usunPozycje(int idPozycji) {
		// TODO - implement PozycjeZamowieniaRepository.usunPozycje
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param pozycja
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement PozycjeZamowieniaRepository.dodajPozycje
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idPozycji
	 * @param ilosc
	 * @param rabat
	 */
	public void modyfikujPozycje(int idPozycji, int ilosc, float rabat) {
		// TODO - implement PozycjeZamowieniaRepository.modyfikujPozycje
		throw new UnsupportedOperationException();
	}

	public void przeslijPozycje() {
		// TODO - implement PozycjeZamowieniaRepository.przeslijPozycje
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idPozycji
	 * @param data
	 */
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