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

	public Zapytanie(LocalDate terminRealizacji,PozycjaZamowienia pozycja) {
		this.id = current_id;
		this.terminReal = terminRealizacji;
		this.pozycja = pozycja;
		this.ilosc = pozycja.getIlosc();
		this.status = StatusZapytania.zopiniowane;
		current_id++;
	}

	public Zapytanie(int id, StatusZapytania status, LocalDate terminReal, String notatka, PozycjaZamowienia pozycja) {
		this.id=id;
		this.status=status;
		this.terminReal=terminReal;
		this.notatka=notatka;
		this.id=id;
		this.pozycja=pozycja;
	}
	
	public Zapytanie(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
		this.ilosc = pozycja.getIlosc();
	}

	public Zapytanie(LocalDate terminRealizacji){
		this.terminReal = terminRealizacji;
	}



	public void setPozycja(PozycjaZamowienia pozycja){
		this.pozycja = pozycja;
	}

	public void zmienStatusZapytania(StatusZapytania status) {
		this.status = status;
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

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public int getId() {
		return id;
	}

	public PozycjaZamowienia getPozycja() {
		return pozycja;
	}

	public String getNotatka() {
		return notatka;
	}

}