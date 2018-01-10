import java.util.ArrayList;
import java.util.Observable;

import static java.time.LocalDate.now;

public class ZapytaniaRepository extends Observable {

	ZapytanieContext zapytanieContext;
	ArrayList<Zapytanie> zapytania;
	Zapytanie zapytanie;
	
	public ZapytaniaRepository(){
		zapytania = new ArrayList<>();
		zapytania.add(new Zapytanie(now(),new PozycjaZamowienia(new Towar(1,"zawleczka",50,0.56))));
		zapytania.get(0).getPozycja().setZamowienie(new Zamowienie());
		zapytanieContext = new ZapytanieContext();
	}
	
	public void setZapytania(ArrayList<Zapytanie> zapytania){
		this.zapytania = zapytania;
	}

	/**
	 *
	 * @param zapytanie
	 */
	public void wyslijZapytanie(Zapytanie zapytanie) {
		// TODO - implement ZapytaniaRepository.wyslijZapytanie
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param idZapytania
	 * @param status
	 */
	public void zmienStatusZapytania(int idZapytania, StatusZapytania status) {
		// TODO - implement ZapytaniaRepository.zmienStatusZapytania
		throw new UnsupportedOperationException();
	}

	public void przeslijZapytanie(Zapytanie zapytanie) {
		zapytanieContext.przeslijZapytanie(zapytanie);
	}

	public void pobierzZapytania() {
		this.zapytania = getZapytania();
	}

	public ArrayList<Zapytanie> getZapytania() {
		return zapytania;
	}

	public void setZapytanie(Zapytanie zapytanie) {
		this.zapytanie = zapytanie;
	}

	public Zapytanie getZapytanie() {
		return zapytanie;
	}

	public void zatwierdzZapytanie() {
		zapytanieContext.zatwierdzZapytanie(zapytanie);
		setChanged();
		notifyObservers();
	}

	public void odrzucZapytanie() {
		zapytanieContext.odrzucZapytanie(zapytanie);
		setChanged();
		notifyObservers();
	}
}