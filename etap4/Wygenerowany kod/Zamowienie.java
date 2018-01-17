import java.util.*;

public class Zamowienie {

	Collection<Sprzedawca> modyfikuj¹cy;
	Sprzedawca zak³adaj¹cy;
	Collection<View> obserwator;
	private int id;
	private StatusZamowienia status = StatusZamowienia.otwarte;
	private Data dataZlozZam = curr_date();
	private Data terminRealizacji;
	private float kwota;
	private boolean czyZatwierdzone;

	/**
	 * 
	 * @param status
	 */
	public void zmienStatus(StatusZamowienia status) {
		// TODO - implement Zamowienie.zmienStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pozycja
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement Zamowienie.dodajPozycje
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pozycja
	 */
	public void usunPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement Zamowienie.usunPozycje
		throw new UnsupportedOperationException();
	}

}