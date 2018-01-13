import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Klasa realizująca połączenie się z obiektami modelu towaru i przechowywaniem ich.
 */

public class TowaryRepository extends Observable {

	TowaryContext towaryDB;
	List<Towar> towary;
	Towar towar;

	public void pobierzListeTowarow() {
		towaryDB.getTowaryZBazy();
		// TODO - implement TowaryRepository.pobierzListeTowarow
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idTowaru
	 * @param ilosc
	 */
	public void sprawdzIloscTowaru(int idTowaru, float ilosc) {
		// TODO - implement TowaryRepository.sprawdzIloscTowaru
		throw new UnsupportedOperationException();
	}

	public void dodajTowar(Towar towar) {
		towary.add(towar);
	}

	public void setTowar(Towar towar){
		this.towar = towar;
		setChanged();
		notifyObservers(towar);
	}

	public Towar getTowar() {
		return towar;
	}

	public List<Towar> getTowary() {
		Context context = new Context();
		context.openDB();
		TowaryContext tc = new TowaryContext(context);
		ArrayList<Towar> listaTowarow = (ArrayList<Towar>) tc.getTowaryZBazy();
		context.closeDB();
		return listaTowarow;
	}
}