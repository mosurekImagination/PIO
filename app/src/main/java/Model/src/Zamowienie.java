import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.now;

/**
 * Klasa, która reprezentuje obiekt Zamówienia z modelu
 */
public class Zamowienie{

	private int id;
	private StatusZamowienia status;
	private LocalDate dataZlozZam;
	private LocalDate terminRealizacji;
	private double kwota;
	private boolean czyZatwierdzone;
	private List<PozycjaZamowienia> pozycjeZamowienia;
	Klient klient;

	Zamowienie(){
		this.terminRealizacji = now();
		this.dataZlozZam = now();
		this.kwota = 0;
		this.status = StatusZamowienia.otwarte;
		this.pozycjeZamowienia = new ArrayList<>();
	}
	
	Zamowienie(int id, StatusZamowienia status, LocalDate dataZlozZam, LocalDate terminRealizacji, double kwota, boolean czyZatwierdzone){
		this.id=id;
		this.status=status;
		this.dataZlozZam=dataZlozZam;
		this.terminRealizacji=terminRealizacji;
		this.kwota=kwota;
		this.czyZatwierdzone=czyZatwierdzone;
		this.pozycjeZamowienia = new ArrayList<>();
	}

	/**
	 * Dodaje pozycję do zamówienia i aktualizuje zamówienie o dodaną pozycję.
	 *
	 * @param pozycja pozycja do dodania
	 */
	public void dodajPozycje(PozycjaZamowienia pozycja) {
		pozycjeZamowienia.add(pozycja);
		aktualizuj();
	}

	public PozycjaZamowienia usunPozycje(int indexPozycji) {
		PozycjaZamowienia pozycja = pozycjeZamowienia.remove(indexPozycji);
		aktualizuj();
		return pozycja;
	}

	public int getNextId(){
		return pozycjeZamowienia.size();
	}

	public void aktualizujPozycje(int indexPozycji, int ilosc, int rabat) {
		PozycjaZamowienia pozycja = pozycjeZamowienia.get(indexPozycji);
		pozycja.setIlosc(ilosc);
		pozycja.setRabat(rabat);
		pozycja.setCena();
	}

	public PozycjaZamowienia getPozycjaOnIndex(int indexPozycji){
		return pozycjeZamowienia.get(indexPozycji);
	}

	public Zapytanie dodajZapytanieDoPozycji(int indexPozycji, Zapytanie zapytanie) {
		pozycjeZamowienia.get(indexPozycji).dodajZapytanie(zapytanie);
		zapytanie.setPozycja(pozycjeZamowienia.get(indexPozycji));
		return zapytanie;
	}

	public void dodajKlienta(Klient klient) {
		this.klient = klient;
	}

	public void aktualizuj() {
		aktualizujKwote();
		aktualizujTerminRealizacji();
	}

	private void aktualizujTerminRealizacji() {
		terminRealizacji = now();
		for(PozycjaZamowienia pozycja:pozycjeZamowienia) {
			LocalDate dataPozycji = pozycja.getTerminRealizacji();
			if (dataPozycji == null) {
				terminRealizacji = null;
			} else if(terminRealizacji != null && isAfterTermin(dataPozycji)) {
				terminRealizacji = pozycja.getTerminRealizacji();
			}
		}
	}

	public boolean isAfterTermin(LocalDate data){
		return data.isAfter(terminRealizacji);
	}

	public void ustalTerminRealizacji(LocalDate data) {
		if (terminRealizacji == null || data.isAfter(terminRealizacji)) terminRealizacji = data;
	}

	private void aktualizujKwote() {
		kwota = 0;
		for (PozycjaZamowienia pozycja:pozycjeZamowienia) {
			kwota += pozycja.getCenaPoRabacie();
		}
	}

	public List<PozycjaZamowienia> getPozycjeZamowienia() {
		return pozycjeZamowienia;
	}

	public int getSize(){
		return pozycjeZamowienia.size();
	}

	public LocalDate getTerminRealizacji(){
		return terminRealizacji;
	}

	public int getIndexOstatniejPozycji() {
		return getSize()-1;
	}

	public List<PozycjaZamowienia> getPozycje() {
		return pozycjeZamowienia;
	}

	public void przeslijDoBazy() {
		Context context = new Context();
		context.openDB();
		ZamowienieContext zc = new ZamowienieContext(context);
		zc.przeslijZamowienie(this);
		
	}

	public double getSuma() {
		return kwota;
	}

	public Klient getKlient() {
		return klient;
	}

	public void setStatusZamowienia(StatusZamowienia status) {
		this.status = status;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	public StatusZamowienia getStatus() {
		return status;
	}

	public LocalDate getDataZlozZam() {
		return dataZlozZam;
	}

	public boolean getCzyZatwierdzone() {
		return czyZatwierdzone;
	}

	public int getId() {
		return id;
	}
}