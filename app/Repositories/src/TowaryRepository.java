import java.util.List;

public class TowaryRepository {

	TowaryContext towaryDB;
	List<Towar> towary;

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
}