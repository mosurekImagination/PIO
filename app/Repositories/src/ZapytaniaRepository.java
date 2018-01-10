import java.util.ArrayList;

public class ZapytaniaRepository {

	ZapytanieContext zapytanieContext;
	ArrayList<Zapytanie> zapytania;
	
	public ZapytaniaRepository(){
		zapytania = new ArrayList<>();
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
		this.zapytania = zapytanieContext.getZapytania();
	}

	public ArrayList<Zapytanie> getZapytania() {
		return new ArrayList<Zapytanie>();
	}
}