import java.time.LocalDate;
import java.util.Date;

public class Zapytanie {

	static int current_id = 0;
	private int id;
	private StatusZapytania status;
	private LocalDate terminReal;
	private String notatka;
	private PozycjaZamowienia pozycja;
	private int ilosc;

	public Zapytanie(LocalDate terminRealizacji) {
		this.id = current_id;
		this.terminReal = terminRealizacji;
		current_id++;
	}

	public void setPozycja(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
	}

	public void zmienStatusZapytania(StatusZapytania status) {
		// TODO - implement Zapytanie.zmienStatusZapytania
		throw new UnsupportedOperationException();
	}

	public StatusZapytania getStatus() {
		return status;
	}

	public LocalDate getTerminRealizacji() {
		return terminReal;
	}

	public Towar getTowar() {
		return pozycja.getTowar();
	}
}