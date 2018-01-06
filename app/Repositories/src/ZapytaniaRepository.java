public class ZapytaniaRepository {

	ZapytanieContext zapytanieContext;

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
}