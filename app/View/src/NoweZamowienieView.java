import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
/**
 * Klasa widoku, wyświetlająca widok złożenia nowego zamówienia.
 */
public class NoweZamowienieView extends View implements Observer{

	KlienciContext klienciContext;
	Controllers kontroler;

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof ZamowieniaRepository) {
			boolean czyDostepny = ((ZamowieniaRepository) arg).getDostepnosc();
			if (!czyDostepny) wyswietlKomunikatoZapytanie();
			else {
				updateView(((ZamowieniaRepository) arg));
			}
		}


	}

	private void updateView(ZamowieniaRepository zamowieniaRepo) {
		// TODO - to ma odświeżać widok po dostaniu update z zamowienia
	}


	private void wyswietlPozycjeZamowienia() {
		// TODO - wyświetla
	}

	/**
	 *
	 * @param nip
	 */
	public void wybierzKlienta(String nip) {
		Klient klient = klienciContext.getKlientZBazy(nip);
		kontroler.dodajKlientaDoZamowienia(klient); // TODO - dodać klienta do ZamowienieRepository i do KlientRepository dla tego kontrolera

	}

//	public void dodajKlienta() {
//		// TODO - implement NoweZamowienieView.dodajKlienta
//		throw new UnsupportedOperationException();
//	}

	public void dodajNowaPozycje(int ilosc, int rabat) {
		//To powinno z jakiegoś formularza przyjść
		kontroler.dodajPozycje(ilosc,rabat);
		throw new UnsupportedOperationException();
	}

	public void wybierzTowar() {
		String towarInfo = kontroler.wybierzTowar();
		// TODO - z wybierzTowar() wychodzi string, którego trzeba wsadzić w pole z nazwą towaru
	}

	public void wyswietlKomunikatoZapytanie() {
		// TODO - Trzeba wyświetlić jakiegoś prompta, który zapyta się czy chcemy zapytanie o towar czy nie
		// TODO - Jeśli tak to trzeba stworzyć zapytanie wyswietlNoweZapytanie()
		// TODO - Jeśli nie to trzeba usunąć stworzoną pozycję zamówienia anulujPozycje()
		throw new UnsupportedOperationException();
	}

	public void wyswietlNoweZapytanie() {
		// TODO - tu trzeba wyświetlić prompta z nowym zapytaniem.
		// TODO - po konfirmie przekazujemy termin do podajTerminRealizacjiZapytania()
		throw new UnsupportedOperationException();
	}

	public void anulujPozycje(){
		kontroler.anulujOstatniaPozycje();
	}

	public void podajTerminRealizacjiZapytania(LocalDate terminRealizacji) {
		// TODO - to powinno pobierać z prompta termin
		kontroler.utworzNoweZapytanie(terminRealizacji);
	}


	public void wyswietlNowaPozycje(PozycjaZamowienia pozycja) {
		// TODO - implement NoweZamowienieView.wyswietlNowaPozycje
		throw new UnsupportedOperationException();
	}

	public void usunPozycje() {
		// TODO - implement NoweZamowienieView.usunPozycje
		throw new UnsupportedOperationException();
	}

	public void podzielZamowienie() {
		// TODO - implement NoweZamowienieView.podzielZamowienie
		throw new UnsupportedOperationException();
	}

	public void przekazZaznaczonePozycje() {
		// TODO - implement NoweZamowienieView.przekazZaznaczonePozycje
		throw new UnsupportedOperationException();
	}

	public void zlozZamowienie() {
		// TODO - implement NoweZamowienieView.zlozZamowienie
		throw new UnsupportedOperationException();
	}

	public void wyswietlZapytanie() {
		// TODO - implement NoweZamowienieView.wyswietlZapytanie
		throw new UnsupportedOperationException();
	}

}