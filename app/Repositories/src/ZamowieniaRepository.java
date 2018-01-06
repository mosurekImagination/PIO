import java.time.LocalDate;
import java.util.*;

public class ZamowieniaRepository extends Observable{

	private List<Observer> observers = new ArrayList<>();

	Zamowienie zamowienie; //Tu będziemy przechowywać całe zamówienie i każdą Pozycję zamówienia tu będziemy wsadzać.
	// Potrzebujemy PozycjeZamowienia Repository w takim razie?
	boolean czyDostepny;


	public void dodajZamowienie(Zamowienie zamowienie) {
		// TODO - implement ZamowieniaRepository.dodajZamowienie
		throw new UnsupportedOperationException();
	}

	public void przeslijZamowienie() {
		// TODO - implement ZamowieniaRepository.przeslijZamowienie
		throw new UnsupportedOperationException();
	}

	public PozycjaZamowienia utworzPozycjeZamowienia(Towar towar) {
		PozycjaZamowienia pozycja = new PozycjaZamowienia(zamowienie.getNextId(),towar);
		zamowienie.dodajPozycje(pozycja);
		return pozycja;
	}

	public void aktualizujPozycje(int indexPozycji, int ilosc, int rabat) {
		czyDostepny = sprawdzDostepnoscTowaru(indexPozycji,ilosc);
		zamowienie.aktualizujPozycje(indexPozycji,ilosc,rabat);
		if (czyDostepny) zamowienie.getPozycjaOnIndex(indexPozycji).setTerminRealizacji(LocalDate.now());
		zamowienie.aktualizuj(indexPozycji);
		notifyObservers(this);
	}

	public boolean sprawdzDostepnoscTowaru(int indexPozycji, int ilosc) {
		PozycjaZamowienia pozycja = zamowienie.getPozycjaOnIndex(indexPozycji);
		return pozycja.getTowar().getIlosc() >= ilosc;
	}


	public void usunPozycje(int indexPozycji) {
		zamowienie.usunPozycje(indexPozycji);
		notifyObservers(this);
	}

	public void dodajZapytanie(int indexPozycji, LocalDate terminRealizacji) {
		Zapytanie zapytanie = new Zapytanie(terminRealizacji);
		zapytanie = zamowienie.dodajZapytanieDoPozycji(indexPozycji,zapytanie);
		notifyObservers(this);
	}

	public boolean getDostepnosc(){
		return czyDostepny;
	}

	public void dodajKlienta(Klient klient) {
		zamowienie.dodajKlienta(klient);
		notifyObservers(this);
	}

}