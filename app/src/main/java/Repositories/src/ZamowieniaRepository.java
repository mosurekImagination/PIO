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

	/**
	 * Przekazanie złożonego zamówienia do przesłania do klasy, obsługującej zamówienie.
	 */
	public boolean przeslijZamowienie() {
		zamowienie.setStatusZamowienia(StatusZamowienia.przekazane);
		zamowienie.przeslijDoBazy();
		return true;
	}

	/**
	 * Funkcja, tworząca nową pozycję zamówienia w zamówieniu z wybranego towaru.
	 */
	public PozycjaZamowienia utworzPozycjeZamowienia(Towar towar) {
		PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
		zamowienie.dodajPozycje(pozycja);
		setChanged();
		notifyObservers(pozycja);
		return pozycja;
	}

	/**
	 * Funkcja, aktualizująca utworzoną pozycję o zadaną ilość i rabat.
	 */
	public void aktualizujPozycje(int ilosc, int rabat) {
			int indexPozycji = zamowienie.getIndexOstatniejPozycji();
			czyMoznaZamowic = sprawdzDostepnoscTowaru(indexPozycji, ilosc);
			zamowienie.aktualizujPozycje(indexPozycji, ilosc, rabat);
			if (czyMoznaZamowic) zamowienie.getPozycjaOnIndex(indexPozycji).setTerminRealizacji(LocalDate.now());
			zamowienie.aktualizuj();
			notifyObservers();

	}

	/**
	 * Funckja sprawdzająca czy dostępna jest wybrana ilość towaru dla pozycji.
	 */
	public boolean sprawdzDostepnoscTowaru(int indexPozycji, int ilosc) {
		PozycjaZamowienia pozycja = zamowienie.getPozycjaOnIndex(indexPozycji);
		return pozycja.getTowar().getIlosc() >= ilosc;
	}

	/**
	 * Funckja, sprawdzająca dostępność towaru w zadanej ilości.
	 */
	public boolean sprawdzDostepnoscTowaru(Towar towar, int ilosc) {
		return towar.getIlosc() >= ilosc;
	}

	public void usunPozycje(int indexPozycji) {
		zamowienie.usunPozycje(indexPozycji);
		notifyObservers();
	}

	/**
	 * Funkcja, dodająca zapytanie do pozycji zamówienia z zadanym terminem realizacji.
	 */
	public void dodajZapytanie(int indexPozycji, LocalDate terminRealizacji) {
		Zapytanie zapytanie = new Zapytanie(terminRealizacji);
		zapytanie = zamowienie.dodajZapytanieDoPozycji(zamowienie.getIndexOstatniejPozycji(),zapytanie);
		notifyObservers();
	}

	/**
	 * Funkcja, dodająca klienta do zamówienia.
	 */
	public void dodajKlienta(Klient klient) {
		zamowienie.dodajKlienta(klient);
		setChanged();
		notifyObservers(klient);
	}

	public List<PozycjaZamowienia> getPozycje() {
		return zamowienie.getPozycje();
	}

	public double getSuma() {
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

	/**
	 * Funkcja odpowiadająca za utworzenie nowego zamówienia z wybranymi pozycjami.
	 */
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

	/**
	 * Funckja, która dodaje daną pozycję zamówienia do zamówienia.
	 */
	public void dodajPozycjeZamowienia(PozycjaZamowienia pozycjaZamowienia) {
		zamowienie.dodajPozycje(pozycjaZamowienia);
		setChanged();
		notifyObservers();
	}

	public boolean czyMoznaZlozycZamowienie() {
		return czyIstniejePozycja() && czyIstniejeKlient();
	}

	private boolean czyIstniejeKlient() {
		return zamowienie.klient != null;
	}

	private boolean czyIstniejePozycja() {
		return getPozycje().size() != 0;
	}
}