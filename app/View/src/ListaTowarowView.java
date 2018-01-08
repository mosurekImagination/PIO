import java.util.List;
/**
 * Klasa widoku, wyświetlająca widok listy towarów.
 */
public class ListaTowarowView extends View {

	TowaryContext towaryContext;

	public void wyswietlListeTowarow() {
		List<Towar> towary = towaryContext.getTowaryZBazy();
		//Trzeba wsadzić te towary w widok
		throw new UnsupportedOperationException();
	}

	public Towar przekazTowar() {
		// TODO - to będzie na jakimś clicku zatwierdzającym wybór z listy i będzie zwracać towar do kontrolera
		throw new UnsupportedOperationException();
	}

}