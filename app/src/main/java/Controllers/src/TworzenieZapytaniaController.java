import javafx.event.ActionEvent;
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

    PozycjeZamowieniaRepository pozycje;
    ZamowieniaRepository zamowieniaRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX; pozycje = new PozycjeZamowieniaRepository();
    }

    public void setPozycjaZamowienia(PozycjeZamowieniaRepository zp)
    {
        pozycje=zp;
    }
    public void setZamowieniaRepository(ZamowieniaRepository zr)
    {
        zamowieniaRepository=zr;
    }


    public void updateView()
    {
        lbNazwaTowaru.setText(pozycje.getPozycja().getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(pozycje.getPozycja().getIlosc()));
    }

    @FXML
    public void wyslijZapytanie(ActionEvent e)
    {
        if(dateTermin != null) {
            pozycje.dodajZapytanie(new Zapytanie(dateTermin.getValue(),pozycje.getPozycja()));
        }
        else
        {
            pozycje.dodajZapytanie(new Zapytanie(pozycje.getPozycja()));
        }

        pozycje.getPozycja().getZapytanie().zmienStatusZapytania(StatusZapytania.wyslane);
        zamowieniaRepository.setCzyMoznaZamowic(true);
        zamowieniaRepository.dodajPozycjeZamowienia(pozycje.getPozycja());
        zamknijOkno(e);
    }

    @FXML
    public void anuluj(ActionEvent e){
        zamowieniaRepository.setCzyMoznaZamowic(true);
        zamknijOkno(e);
    }




}
