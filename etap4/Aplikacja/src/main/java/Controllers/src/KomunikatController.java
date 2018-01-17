import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa sluzaca do wyswietlania nowych okienek z okreslonym komunikatem
 */
public class KomunikatController extends ViewController implements Initializable{

    @FXML
    Label lbKomunikat;
    @FXML
    Button btnX;

    /**
     *Metoda wywowylana przy tworzeniu okienka
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    /**
     * Ustawia tekst komunikatu w wyswietlanym oknie
     * @param komunikat - komunikat do wyswietlenia
     */
    public void setKomunikat(String komunikat)
    {
        lbKomunikat.setText(komunikat);
    }
}
