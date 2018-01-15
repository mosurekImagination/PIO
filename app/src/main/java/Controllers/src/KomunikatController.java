import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class KomunikatController extends ViewController implements Initializable{

    @FXML
    Label lbKomunikat;
    @FXML
    Button btnX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }


    public void setKomunikat(String komunikat)
    {
        lbKomunikat.setText(komunikat);
    }
}
