import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaTowarowController extends ViewController implements Initializable {

    ZamowieniaRepository pozycjaZamRepo;

    @FXML
    Button btnX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;

    }

    public void setTowarRepository(ZamowieniaRepository pozycjaZamRepo) {
        this.pozycjaZamRepo = pozycjaZamRepo;
    }

    @FXML
    void wyslijTowar(ActionEvent e){
        //Towar towar= new Towar(dane z wybranego towaru)
        Towar towar = new Towar(2, "Wtyczka", 10,0.2);
        pozycjaZamRepo.utworzPozycjeZamowienia(towar);
        zamknijOkno(e);
    }


}
