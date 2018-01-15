import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okienka do obsługi zapytania
 */
public class ObslugaZapytaniaController extends ViewController implements Initializable{

    @FXML
    Button btnPowrot;
    @FXML
    Button btnX;
    @FXML
    Button btnZatwierdz;
    @FXML
    Button btnOdrzuc;

    @FXML
    Label lbNazwaTowaru;
    @FXML
    Label lbIlosc;
    @FXML
    Label lbTerminRealizacji;

    private ZapytaniaRepository zapRepo;

    /**
     *Metoda wywowyłana przy tworzeniu okienka, inicjalizuje początkowe Wartości
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeButton = btnX;
        zapRepo = new ZapytaniaRepository();
    }

    /**
     * Wywoływana przy zamknięciu okienka przyciskiem X lub powrót
     * @param e
     */
    @FXML
    private void powrot(ActionEvent e)
    {
        zamknijOkno(e);
    }

    /**
     * Metoda ustawiająca elementy okna danymi.
     */
    public void updateView()
    {
        lbNazwaTowaru.setText(zapRepo.getZapytanie().getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(zapRepo.getZapytanie().getPozycja().getIlosc()));
        lbTerminRealizacji.setText(zapRepo.getZapytanie().getTerminRealizacji().toString());
        if(zapRepo.getZapytanie().getStatus() == StatusZapytania.zopiniowane){}
        else {
            btnOdrzuc.setVisible(false);
            btnZatwierdz.setVisible(false);
        }
    }

    public void setZapytanie(ZapytaniaRepository z)
    {
        zapRepo = z;
        updateView();
    }

    /**
     * Zatwierdza obsługiwane zapytanie
     * @param e - ActionEvent naciśnięcia przycisku Zatwierdź
     */
    @FXML
    public void zatwierdz(ActionEvent e)
    {
        zapRepo.zatwierdzZapytanie();
        powrot(e);
    }

    /**
     * Odrzuca obsługiwane zapytanie
     * @param e - ActionEvent naciśnięcia przycisku Odrzuć
     */
    @FXML
    public void odrzuc(ActionEvent e)
    {
        zapRepo.odrzucZapytanie();
        powrot(e);
    }


}
