import java.util.Observable;

/**
 * Klasa realizująca połączenie się z obiektami modelu pozycji zamówienia i przechowywaniem ich.
 */

public class PozycjeZamowieniaRepository extends Observable {

	private PozycjaZamowienia pozycja;


	/**
	 * @return zwraca pozycje zamowienia, przechowywana w klasie
	 */
	public PozycjaZamowienia getPozycja() {
		return pozycja;
	}

	/**
	 * Dodaje zapytanie do istniejącej pozycji zamówienia.
	 * @param zapytanie - zapytanie, ktore ma zostac dodane do pozycji
	 */
	public void dodajZapytanie(Zapytanie zapytanie) {
		pozycja.dodajZapytanie(zapytanie);
		setChanged();
		notifyObservers();
	}

	/**
	 * Tworzy pozycję zamówienia z podanym towarem, ilością i rabatem.
	 * @param ilosc - ilosc sztuk, z jaka ma zostac utworzona nowa pozycja
	 * @param rabat - rabat, jaki jest udzielany na pozycje
	 * @param towar - towar, ktorego dotyczy pozycja
	 */
	public void utworzPozycjeZamowienia(Towar towar,int ilosc,int rabat) {
		this.pozycja = new PozycjaZamowienia(towar);
		this.pozycja.setIlosc(ilosc);
		this.pozycja.setRabat(rabat);
		this.pozycja.setCena();
	}
}