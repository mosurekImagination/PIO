import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaZapytanController extends ViewController implements Initializable{

    @FXML
    Button btnPowrot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnPowrot;
    }

    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }
}
