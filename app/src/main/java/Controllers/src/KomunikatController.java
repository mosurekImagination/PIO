import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa służąca do wyświetlania nowych okienek z określonym komunikatem
 */
public class KomunikatController extends ViewController implements Initializable{

    @FXML
    Label lbKomunikat;
    @FXML
    Button btnX;

    /**
     *Metoda wywowyłana przy tworzeniu okienka
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    /**
     * Ustawia tekst komunikatu w wyświetlanym oknie
     * @param komunikat - komunikat do wyświetlenia
     */
    public void setKomunikat(String komunikat)
    {
        lbKomunikat.setText(komunikat);
    }
}
