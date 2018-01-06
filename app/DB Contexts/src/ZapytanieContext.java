import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Asus on 2018-01-04.
 */
public class ZapytanieContext {
    //Tu łączymy się z BD

    public void przeslijZapytanie(Zapytanie zapytanie) {
        StatusZapytania status = zapytanie.getStatus();
        LocalDate terminRealizacji = zapytanie.getTerminRealizacji();
        Towar towar = zapytanie.getTowar(); // Tu wysłałabym jednak na towar, bo zapytanie nie jest jeszcze w BD
    }
}
