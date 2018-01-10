import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Asus on 2018-01-04.
 */
public class ZapytanieContext {
    //Tu łączymy się z BD

    public void przeslijZapytanie(Zapytanie zapytanie) {
    }

    public ArrayList<Zapytanie> getZapytania() {
        //Pobieramy z bazy zapytania. Muszą mieć w sobie i pozycję zamówienia a pozycja zamówienia musi mieć zamówienie, do któego się odnosi
        return null;
    }

    public Zapytanie zatwierdzZapytanie(Zapytanie zapytanie) {
        zapytanie.zmienStatusZapytania(StatusZapytania.zatwierdzone);
        zapytanie.getPozycja().setTerminRealizacji(zapytanie.getTerminRealizacji());
        zapytanie.getPozycja().getZamowienie().aktualizuj();
        //update zamówienia, pozycji i tego zapytania
        return zapytanie;
    }

    public Zapytanie odrzucZapytanie(Zapytanie zapytanie) {
        zapytanie.zmienStatusZapytania(StatusZapytania.odrzucone);
        Zamowienie zamowienie = zapytanie.getPozycja().getZamowienie();
        zapytanie.getPozycja().setZamowienie(null);
        zamowienie.aktualizuj();
        //update tego zamówienia, tej pozycji i tego zapytania
        return zapytanie;
    }
}
