import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Klasa realizująca połączenie się z obiektami modelu towaru i przechowywaniem ich.
 */

public class TowaryRepository extends Observable {

	List<Towar> towary;
	Towar towar;

	public void setTowar(Towar towar){
		this.towar = towar;
		setChanged();
		notifyObservers(towar);
	}

	public Towar getTowar() {
		return towar;
	}

	/**
	 * Funkcja realizująca połączenie z bazą i pobranie z niej listy towarów.
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