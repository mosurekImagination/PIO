
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class NoweZamowienieController extends ViewController implements Initializable, Observer{

    ZamowieniaRepository zamowieniaRepository;
    Towar towar = new Towar(1, "Sruba", 50);
    PozycjaZamowienia pozycjaZamowienia= new PozycjaZamowienia(1, towar, 100, 0.21f);

    @FXML
    Button btnWybierz;
    @FXML
    Button btnDodajKlienta;
    @FXML
    Button btnDodajTowar;
    @FXML
    Button btnPozycje;
    @FXML
    Button btnWyslijZapytanie;
    @FXML
    Button btnPodzielZamowienie;
    @FXML
    Button btnZlozZamowienie;
    @FXML
    Button btnPowrot;

    @FXML
    Label lbNazwaTowaru;
    @FXML
    Label lbBrakDostepnosci;
    @FXML
    Label lbNrZamowienia;
    @FXML
    Label lbSuma;
    @FXML
    Label lbDataRealizacji;

    @FXML
    TextField tfPodajNip;
    @FXML
    TextField tfIlosc;
    @FXML
    TextField tfRabat;

    ArrayList<String> list = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.add("1");
        list.add("12");
        list.add("123");
        closeButton=btnPowrot;
        zamowieniaRepository = new ZamowieniaRepository();

    }

    @FXML
    private void powrot(ActionEvent e)
    {
        otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    private void wyslijZapytanie()
    {
        TworzenieZapytaniaController twController = (TworzenieZapytaniaController) otworzOkno("TworzenieZapytania.fxml", MALE_OKNO);
        twController.setPozycjaZamowienia(pozycjaZamowienia);
        twController.updateView();
    }

    @FXML
    private void dodajPozycjeZamowienia()
    {
     
    }
}
