import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okienka do obslugi zapytania
 */
public class ObslugaZapytaniaController extends ViewController implements Initializable{

    @FXML
    private Button btnPowrot;
    @FXML
    private Button btnX;
    @FXML
    private Button btnZatwierdz;
    @FXML
    private Button btnOdrzuc;

    @FXML
    private Label lbNazwaTowaru;
    @FXML
    private Label lbIlosc;
    @FXML
    private Label lbTerminRealizacji;

    private ZapytaniaRepository zapRepo;

    /**
     *Metoda wywowylana przy tworzeniu okienka, inicjalizuje poczatkowe Wartosci
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeButton = btnX;
        zapRepo = new ZapytaniaRepository();
    }

    /**
     * Wywolywana przy zamknieciu okienka przyciskiem X lub powrot
     * @param e -ActionEvent przekazywany dalej do zamknięcia odpowiedniego okna
     */
    @FXML
    private void powrot(ActionEvent e)
    {
        zamknijOkno(e);
    }

    /**
     * Metoda ustawiajaca elementy okna danymi.
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
     * Zatwierdza obslugiwane zapytanie
     * @param e - ActionEvent nacisniecia przycisku Zatwierdz
     */
    @FXML
    public void zatwierdz(ActionEvent e)
    {
        zapRepo.zatwierdzZapytanie();
        powrot(e);
    }

    /**
     * Odrzuca obslugiwane zapytanie
     * @param e - ActionEvent nacisniecia przycisku Odrzuc
     */
    @FXML
    public void odrzuc(ActionEvent e)
    {
        zapRepo.odrzucZapytanie();
        powrot(e);
    }


}
