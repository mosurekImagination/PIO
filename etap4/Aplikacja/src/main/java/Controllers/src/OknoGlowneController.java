import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler obslugujacy okienko tworzenia zapytania
 */
public class OknoGlowneController extends ViewController implements Initializable{
    @FXML
    private Button btnNoweZamowienie;
    @FXML
    private Button btnX;

    /**
     * Funkcja wywolywana przy tworzeniu okienka, inicjalizuje poczatkowe zmienne
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    /**
     * Funkcja wywolywana po nacisnieciu przycisku nowe zamowienie, zamyka aktualne okno i otwiera Nowe Zamowienie.
     */
    @FXML
    private void otworzNoweZamowienie(ActionEvent event)
    {
        otworzOkno("NoweZamowienie.fxml", DUZE_OKNO);
        zamknijOkno(event);
    }

    /**
     * Funkcja wywolywana po nacisnieciu przycisku zapytania, zamyka aktualne okno i otwiera liste zapytan.
     */
    @FXML
    private void otworzListeZapytan(ActionEvent event)
    {
        otworzOkno("ListaZapytan.fxml", DUZE_OKNO);
        zamknijOkno(event);
    }



}
