import java.util.Observable;
import java.util.Observer;



public class View implements Observer{

	Zamowienie obserwowany;

	public void zamknijOkno() {
		// TODO - implement View.zamknijOkno
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param komunikat
	 */
	public void wyswietlKomunikat(String komunikat) {
		// TODO - implement View.wyswietlKomunikat
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}