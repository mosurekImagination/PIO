import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Klasa realizujaca polaczenie sie z obiektami modelu towaru i przechowywaniem ich.
 */

public class TowaryRepository extends Observable {

	List<Towar> towary;
	private Towar towar;

	/**
	 * @param towar - towar, ktory ma byc przechowywany w obiekcie
	 */
	public void setTowar(Towar towar){
		this.towar = towar;
		setChanged();
		notifyObservers(towar);
	}

	/**
	 * @return zwraca towar, ktory jest przechowywany w obiekcie
	 */
	public Towar getTowar() {
		return towar;
	}

	/**
	 * Funkcja realizujaca polaczenie z baza i pobranie z niej listy towarow.
	 */
	public List<Towar> getTowary() {
		Context context = new Context();
		context.openDB();
		TowaryContext tc = new TowaryContext(context);
		ArrayList<Towar> listaTowarow = (ArrayList<Towar>) tc.getTowaryZBazy();
		context.closeDB();
		towary = listaTowarow;
		return listaTowarow;
	}
}