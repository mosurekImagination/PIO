import java.util.ArrayList;
import java.util.Observable;

import static java.time.LocalDate.now;

/**
 * Klasa, ktora odpowiada za polaczenie z obiektem modelu,przechowywaniem i obsluga Zapytan
 */
public class ZapytaniaRepository extends Observable {

	private ArrayList<Zapytanie> zapytania;
	private Zapytanie zapytanie;
	
	public ZapytaniaRepository(){
		zapytania = new ArrayList<>();
		zapytania.add(new Zapytanie(now(),new PozycjaZamowienia(new Towar(1,"zawleczka",50,0.56))));
		zapytania.get(0).getPozycja().setZamowienie(new Zamowienie());
	}


	/**
	 * Metoda pobierajaca zapytania do listy zapytan, przechowywanych w obiekcie
	 */
	public void pobierzZapytania() {
		this.zapytania = getZapytania();
	}

	/**
	 * Funkcja pobierajaca liste zapytan z bazy
	 */
	public ArrayList<Zapytanie> getZapytania() {
		Context context = new Context();
		context.openDB();
		ZapytanieContext zc = new ZapytanieContext(context);
		ArrayList<Zapytanie> listaZapytan = (ArrayList<Zapytanie>) zc.getZapytaniaZBazy();
		context.closeDB();
		return listaZapytan;
	}

	/**
	 * @param zapytanie - zapytanie, ktore ma byc przechowywane w obiekcie
	 */
	public void setZapytanie(Zapytanie zapytanie) {
		this.zapytanie = zapytanie;
	}

	/**
	 * @return zwraca zapytanie, przechowywane w obiekcie klasy
	 */
	public Zapytanie getZapytanie() {
		return zapytanie;
	}

	/**
	 * Funkcja, ktora odpowiada za zatwierdzenie zapytania i wyslania go do bazy
	 */
	public void zatwierdzZapytanie() {
		Context context = new Context();
		context.openDB();
		ZapytanieContext zc = new ZapytanieContext(context);
		zc.zatwierdzZapytanie(zapytanie);
		context.closeDB();
		setChanged();
		notifyObservers();
	}

	/**
	 * Funckja, ktora odpowiada za odrzucenie zapytania i wyslania go do bazy
	 */
	public void odrzucZapytanie() {
		Context context = new Context();
		context.openDB();
		ZapytanieContext zc = new ZapytanieContext(context);
		zc.odrzucZapytanie(zapytanie);
		context.closeDB();
		setChanged();
		notifyObservers();
	}
}