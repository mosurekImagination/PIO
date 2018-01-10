import java.util.ArrayList;

import static java.time.LocalDate.now;

public class ZapytaniaRepository {

	ZapytanieContext zapytanieContext;
	ArrayList<Zapytanie> zapytania;
	
	public ZapytaniaRepository(){
		zapytania = new ArrayList<>();
		zapytania.add(new Zapytanie(now(),new PozycjaZamowienia(new Towar(1,"zawleczka",50,0.56))));
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
}