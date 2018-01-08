import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OknoGlowneController extends ViewController implements Initializable{
    @FXML
    Button btnNoweZamowienie;
    @FXML
    Button btnX;

    @FXML
    private void otworzNoweZamowienie(ActionEvent event)
    {
        otworzOkno("NoweZamowienie.fxml", DUZE_OKNO);
        zamknijOkno(event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    @FXML
    private void otworzListeZapytan(ActionEvent event)
    {
        otworzOkno("ListaZapytan.fxml", DUZE_OKNO);
        zamknijOkno(event);
    }

}
