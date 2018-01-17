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

	/**
	 * Metoda, ktora ma powiadomic obserwatorow obiektu o zmienionym stanie
	 */
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
	 * @param towar - towar, na jaki ma byc utowrzona nowa pozycja
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
	 * @param rabat - rabat, ktory zostal udzielony na dana pozycje
	 * @param ilosc - ilosc sztuk towaru zamawianego w danej pozycji
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
	 * @param indexPozycji - indeks pozycji, dla ktorej ilosc towaru ma byc sprawdzona
	 * @param ilosc - ilosc sztuk towaru, jaka ma byc dodana do pozycji
	 * @return zwraca czy jest wystarczajaca ilosc sztuk towaru
	 */
	public boolean sprawdzDostepnoscTowaru(int indexPozycji, int ilosc) {
		PozycjaZamowienia pozycja = zamowienie.getPozycjaOnIndex(indexPozycji);
		return sprawdzDostepnoscTowaru(pozycja.getTowar(),ilosc);
	}

	/**
	 * Funckja, sprawdzająca dostępność towaru w zadanej ilości.
	 * @param towar - towar, dla ktorego sprawdzamy ilosc towaru
	 * @param ilosc - ilosc sztuk towaru, ktora sprawdzamy
	 * @return zwraca czy jest wystarczajaca ilosc podanego towaru
	 */
	public boolean sprawdzDostepnoscTowaru(Towar towar, int ilosc) {
		return towar.getIlosc() >= ilosc;
	}

	/**
	 * Metoda sluzaca do usuwania zadanej pozycji z zamowienia
	 * @param indexPozycji - indeks pozycji, ktora ma zostac usunieta
	 */
	public void usunPozycje(int indexPozycji) {
		zamowienie.usunPozycje(indexPozycji);
		notifyObservers();
	}


	/**
	 * Funkcja, dodająca klienta do zamówienia.
	 * @param klient - klient, który ma zostać dodany do zamowienia
	 */
	public void dodajKlienta(Klient klient) {
		zamowienie.dodajKlienta(klient);
		setChanged();
		notifyObservers(klient);
	}

	/**
	 * @return zwraca pozycje zamowienia przechowywanego w obiekcie
	 */
	public List<PozycjaZamowienia> getPozycje() {
		return zamowienie.getPozycje();
	}

	/**
	 * @return zwraca kwote zamowienia
	 */
	public double getSuma() {
		return zamowienie.getSuma();
	}

	/**
	 * @return zwraca termin realizacji zamowienia w postaci stringa
	 */
	public String getTerminRealizacji() {
		String formattedDate;
		if(zamowienie.getTerminRealizacji() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			formattedDate = zamowienie.getTerminRealizacji().format(formatter);
		}
		else {formattedDate = " ";}
		return formattedDate;
	}

	/**
	 * Ustawia flage czyMoznaZamowic na zadana wartosc
	 */
	public void setCzyMoznaZamowic(boolean b) {
        czyMoznaZamowic = b;
        notifyObservers();
    }

	/**
	 * Funkcja odpowiadająca za utworzenie nowego zamówienia z wybranymi pozycjami.
	 * @param indeksy - indeksy z aktualnego zamowienia, ktore maja zostac przeniesione do nwoego
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

	/**
	 * @param zamowienie - zamowienie, przechowywane w obiekcie klasy
	 */
	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
		setChanged();
		notifyObservers(this.zamowienie.getKlient());
		notifyObservers();
	}

	/**
	 * Funckja, która dodaje daną pozycję zamówienia do zamówienia.
	 * @param pozycjaZamowienia  - pozycja zamowienia, ktora ma zostac dodana do zamowienia
	 */
	public void dodajPozycjeZamowienia(PozycjaZamowienia pozycjaZamowienia) {
		zamowienie.dodajPozycje(pozycjaZamowienia);
		setChanged();
		notifyObservers();
	}

	/**
	 * Metoda sprawdzajaca czy mozna zlozyc zamowienie
	 * @return zwraca wartosc true albo false
	 */
	public boolean czyMoznaZlozycZamowienie() {
		return czyIstniejePozycja() && czyIstniejeKlient();
	}

	/**
	 * @return zwraca informacje czy do zamowieniu dodano klienta
	 */
	private boolean czyIstniejeKlient() {
		return zamowienie.klient != null;
	}

	/**
	 * @return zwraca informacje czy zamowienie nie jest puste
	 */
	private boolean czyIstniejePozycja() {
		return getPozycje().size() != 0;
	}
}