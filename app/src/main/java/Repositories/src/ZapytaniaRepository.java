import java.util.ArrayList;
import java.util.Observable;

import static java.time.LocalDate.now;

/**
 * Klasa, która odpowiada za połączenie z obiektem modelu i przechowywaniem i obsługą Zapytań
 */
public class ZapytaniaRepository extends Observable {

	private ArrayList<Zapytanie> zapytania;
	private Zapytanie zapytanie;
	
	public ZapytaniaRepository(){
		zapytania = new ArrayList<>();
		zapytania.add(new Zapytanie(now(),new PozycjaZamowienia(new Towar(1,"zawleczka",50,0.56))));
		zapytania.get(0).getPozycja().setZamowienie(new Zamowienie());
	}


	public void pobierzZapytania() {
		this.zapytania = getZapytania();
	}

	/**
	 * Funkcja pobierająca listę zapytań z bazy
	 */
	public ArrayList<Zapytanie> getZapytania() {
		Context context = new Context();
		context.openDB();
		ZapytanieContext zc = new ZapytanieContext(context);
		ArrayList<Zapytanie> listaZapytan = (ArrayList<Zapytanie>) zc.getZapytaniaZBazy();
		context.closeDB();
		return listaZapytan;
	}

	public void setZapytanie(Zapytanie zapytanie) {
		this.zapytanie = zapytanie;
	}

	public Zapytanie getZapytanie() {
		return zapytanie;
	}

	/**
	 * Funkcja, która odpowiada za zatwierdzenie zapytania i wysłania go do bazy
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
	 * Funckja, która odpowiada za odrzucenie zapytania i wysłania go do bazy
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