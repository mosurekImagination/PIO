import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TworzenieZapytaniaController extends ViewController implements Initializable{

    @FXML
    Button btnX;

    @FXML
    Label lbIlosc;

    @FXML
    Label lbNazwaTowaru;

    @FXML
    Button btnAnuluj;

    @FXML
    Button btnWyslij;

    @FXML
    DatePicker dateTermin;

    PozycjaZamowienia pozycjaZamowienia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    public void setPozycjaZamowienia(PozycjaZamowienia pz)
    {
        pozycjaZamowienia = pz;
    }

    public void updateView()
    {
        lbNazwaTowaru.setText(pozycjaZamowienia.getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(pozycjaZamowienia.getIlosc()));
    }

    @FXML
    public void wyslijZapytanie()
    {
        if(dateTermin != null)
           System.out.print(dateTermin.getValue());
        else
        {
            ;
        }
    }




}
