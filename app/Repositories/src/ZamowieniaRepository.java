import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Klasa realizująca połączenie się z obiektami modelu zamówienia i przechowywaniem ich.
 */
public class ZamowieniaRepository extends Observable{


	Zamowienie zamowienie;
	boolean czyMoznaZamowic;

	ZamowieniaRepository(){
		zamowienie = new Zamowienie();
		czyMoznaZamowic = true;
	}

	@Override
	public void notifyObservers(){
		setChanged();
		super.notifyObservers();
	}

	public void dodajZamowienie(Zamowienie zamowienie) {
		// TODO - implement ZamowieniaRepository.dodajZamowienie
		throw new UnsupportedOperationException();
	}

	public boolean przeslijZamowienie() {
		zamowienie.setStatusZamowienia(StatusZamowienia.przekazane);
		zamowienie.przeslijDoBazy();

		return true;
	}

	public PozycjaZamowienia utworzPozycjeZamowienia(Towar towar) {
		PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
		zamowienie.dodajPozycje(pozycja);
		setChanged();
		notifyObservers(pozycja);
		return pozycja;
	}

	public void aktualizujPozycje(int ilosc, int rabat) {
			int indexPozycji = zamowienie.getIndexOstatniejPozycji();
			czyMoznaZamowic = sprawdzDostepnoscTowaru(indexPozycji, ilosc);
			zamowienie.aktualizujPozycje(indexPozycji, ilosc, rabat);
			if (czyMoznaZamowic) zamowienie.getPozycjaOnIndex(indexPozycji).setTerminRealizacji(LocalDate.now());
			zamowienie.aktualizuj();
			notifyObservers();

	}

	private boolean czyPoprawnaIlosc(int ilosc) {
		return ilosc > 0;
	}

	private boolean czyPoprawnyRabat(int rabat) {
		return rabat >= 0 && rabat < 100;
 	}

	public boolean sprawdzDostepnoscTowaru(int indexPozycji, int ilosc) {
		PozycjaZamowienia pozycja = zamowienie.getPozycjaOnIndex(indexPozycji);
		return pozycja.getTowar().getIlosc() >= ilosc;
	}
	public boolean sprawdzDostepnoscTowaru(Towar towar, int ilosc) {
		return towar.getIlosc() >= ilosc;
	}

	public void usunPozycje(int indexPozycji) {
		zamowienie.usunPozycje(indexPozycji);
		notifyObservers();
	}

	public void dodajZapytanie(int indexPozycji, LocalDate terminRealizacji) {
		Zapytanie zapytanie = new Zapytanie(terminRealizacji);
		zapytanie = zamowienie.dodajZapytanieDoPozycji(zamowienie.getIndexOstatniejPozycji(),zapytanie);
		notifyObservers();
	}

	public boolean getDostepnosc(){
		return czyMoznaZamowic;
	}

	public void dodajKlienta(Klient klient) {
		zamowienie.dodajKlienta(klient);
		setChanged();
		notifyObservers(klient);
	}

	public PozycjaZamowienia getOstatniaPozycja() {
		return zamowienie.getPozycjaOnIndex(zamowienie.getIndexOstatniejPozycji());
	}

	public List<PozycjaZamowienia> getPozycje() {
		return zamowienie.getPozycje();
	}

	public float getSuma() {
		return zamowienie.getSuma();
	}

	public String getTerminRealizacji() {
		String formattedDate;
		if(zamowienie.getTerminRealizacji() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			formattedDate = zamowienie.getTerminRealizacji().format(formatter);
		}
		else {formattedDate = " ";}
		return formattedDate;
	}

    public void setCzyMoznaZamowic(boolean b) {
        czyMoznaZamowic = b;
        notifyObservers();
    }

	public Zamowienie wydzielPozycje(ArrayList<Integer> indeksy) {
		Zamowienie noweZamowienie = new Zamowienie();
		noweZamowienie.setKlient(zamowienie.getKlient());
		for (Integer indeks:indeksy) {
			PozycjaZamowienia pozycja = zamowienie.usunPozycje(indeks);
			noweZamowienie.dodajPozycje(pozycja);
			notifyObservers();
		}
		noweZamowienie.aktualizuj();
		zamowienie = noweZamowienie;
		return  noweZamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
		setChanged();
		notifyObservers(this.zamowienie.getKlient());
		notifyObservers();
	}

	public void dodajPozycjeZamowienia(PozycjaZamowienia pozycjaZamowienia) {
		zamowienie.dodajPozycje(pozycjaZamowienia);
		zamowienie.aktualizuj();
		setChanged();
		notifyObservers();
	}
}