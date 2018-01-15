import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

/**
 * Klasa realizująca połączenie się z obiektami modelu pozycji zamówienia i przechowywaniem ich.
 */

public class PozycjeZamowieniaRepository extends Observable {

	private PozycjaZamowienia pozycja;

	public void setPozycja(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
	}

	public PozycjaZamowienia getPozycja() {
		return pozycja;
	}

	/**
	 * Dodaje zapytanie do istniejącej pozycji zamówienia.
	 */
	public void dodajZapytanie(Zapytanie zapytanie) {
		pozycja.dodajZapytanie(zapytanie);
		setChanged();
		notifyObservers();
	}

	/**
	 * Tworzy pozycję zamówienia z podanym towarem, ilością i rabatem.
	 */
	public void utworzPozycjeZamowienia(Towar towar,int ilosc,int rabat) {
		PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
		this.pozycja = pozycja;
		this.pozycja.setIlosc(ilosc);
		this.pozycja.setRabat(rabat);
		this.pozycja.setCena();
	}
}