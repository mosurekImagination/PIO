import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ObslugaZapytaniaController extends ViewController implements Initializable{

    @FXML
    Button btnPowrot;
    @FXML
    Button btnX;

    @FXML
    Label lbNazwaTowaru;
    @FXML
    Label lbIlosc;
    @FXML
    Label lbTerminRealizacji;

    Zapytanie zapytanie;
    ZapytanieContext zapytanieContext;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    @FXML
    private void powrot(ActionEvent e)
    {
        zamknijOkno(e);
    }

    public void updateView()
    {
        lbNazwaTowaru.setText(zapytanie.getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(zapytanie.getPozycja().getIlosc()));
        lbTerminRealizacji.setText(zapytanie.getTerminRealizacji().toString());
    }

    public void setZapytanie(Zapytanie z)
    {
        zapytanie=z;
    }

    @FXML
    private void zatwierdz()
    {
    zapytanie = zapytanieContext.zatwierdzZapytanie(zapytanie);
    }

    @FXML
    private void odrzuc()
    {
        zapytanie = zapytanieContext.odrzucZapytanie(zapytanie);

    }


}