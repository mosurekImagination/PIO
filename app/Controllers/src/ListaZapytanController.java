import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaZapytanController extends ViewController implements Initializable{

    @FXML
    Button btnPowrot;

    @FXML
    GridPane gpListaZapytan;
    ZapytaniaRepository zapytaniaRepo;

    ArrayList<Button> buttons = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnPowrot;

        zapytaniaRepo = new ZapytaniaRepository();
        zapytaniaRepo.pobierzZapytania();

        prepareTestData();
        fillGridView();
        setGridViewConstraints();
    }


    private void prepareTestData()
    {
        //zapytania.add(new Zapytanie(LocalDate.now()));
    }

    public void fillGridView()
    {
        ArrayList<Zapytanie> zapytania = zapytaniaRepo.getZapytania();

        for(int i=0; i<zapytania.size();i++)
        {
            Button otworz = new Button("Otwórz");
            Zapytanie zapytanie = zapytania.get(i);

            buttons.add(otworz);
            otworz.setOnAction(new EventHandler<ActionEvent>() {
                private Button bRef;

                @Override
                public void handle(ActionEvent e) {
                    ObslugaZapytaniaController controller = (ObslugaZapytaniaController)otworzOkno("ObslugaZapytania.fxml",MALE_OKNO);
                    controller.setZapytanie(zapytania.get(buttons.indexOf(bRef)));
                    controller.updateView();
                }

                private EventHandler<ActionEvent> init(Button b) {
                    bRef = b;
                    return this;
                }
            }.init(otworz));
            String id = String.valueOf(zapytanie.getId());
            String status = String.valueOf(zapytanie.getStatus());
            String terminRealizacji = String.valueOf(zapytanie.getTerminRealizacji());
            String klient = String.valueOf(zapytanie.getPozycja().getZamowienie().getKlient());
            gpListaZapytan.addRow(i, new Label(id), new Label(status), new Label(terminRealizacji), new Label(klient), new Label("axsdf"), new Label("ascdf"), otworz);
            gpListaZapytan.getRowConstraints().add(new RowConstraints(50));
        }
    }
    private void setGridViewConstraints()
    {
        gpListaZapytan.getColumnConstraints().get(0).setMinWidth(70);

       gpListaZapytan.getColumnConstraints().get(1).setMinWidth(50);
        gpListaZapytan.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(2).setMinWidth(120);
        gpListaZapytan.getColumnConstraints().get(2).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(3).setMinWidth(250);
        gpListaZapytan.getColumnConstraints().get(3).setHalignment(HPos.CENTER);

         gpListaZapytan.getColumnConstraints().get(4).setMinWidth(100);
        gpListaZapytan.getColumnConstraints().get(4).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(5).setMinWidth(70);
        gpListaZapytan.getColumnConstraints().get(5).setHalignment(HPos.CENTER);

       gpListaZapytan.getColumnConstraints().get(6).setMinWidth(70);
       gpListaZapytan.getColumnConstraints().get(6).setHalignment(HPos.CENTER);



    }
    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }
}
