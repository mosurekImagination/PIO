import java.util.*;

public class Zamowienie extends Observable {

	Collection<Observer> obserwatorzy;
	private int id;
	private StatusZamowienia status = StatusZamowienia.otwarte;
	private Date dataZlozZam;
	private Date terminRealizacji;
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