import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ListaZapytanController extends ViewController implements Initializable,Observer {

    @FXML
    Button btnPowrot;

    @FXML
    GridPane gpListaZapytan;

    ZapytaniaRepository zapytaniaRepo;

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Zapytanie> zapytania= new ArrayList<>();

    private static Background ODRZUCONE = new Background( new BackgroundFill(Color.PALEVIOLETRED, new CornerRadii(1),
            new Insets(0.0,0.0,0.0,0.0)));
    private static Background ZATWIERDZONE = new Background( new BackgroundFill(Color.LIMEGREEN, new CornerRadii(1),
            new Insets(0.0,0.0,0.0,0.0)));
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnPowrot;

        zapytaniaRepo = new ZapytaniaRepository();
        zapytaniaRepo.pobierzZapytania();
        zapytaniaRepo.addObserver(this);

        prepareTestData();
        fillGridView();
        setGridViewConstraints();
    }

    @Override
    public void update(Observable o, Object arg) {
        gpListaZapytan.getChildren().clear();
        buttons.clear();
        fillGridView();
    }


    private void prepareTestData()
    {
        zapytania.add(new Zapytanie(LocalDate.now()));
    }

    public void fillGridView()
    {
        ArrayList<Zapytanie> zapytania = zapytaniaRepo.getZapytania();

        gpListaZapytan.addRow(0, new Label("id"),new Label("Status"), new Label("Termin Realizacji:"), new Label("Klient"), new Label("NazwaTowaru"));
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
                    zapytaniaRepo.setZapytanie(zapytania.get(buttons.indexOf(bRef)));
                    controller.setZapytanie(zapytaniaRepo);
                    System.out.println(zapytania.get(buttons.indexOf(bRef)));
                }

                private EventHandler<ActionEvent> init(Button b) {
                    bRef = b;
                    return this;
                }
            }.init(otworz));
            String id = String.valueOf(zapytanie.getId());
            String status = String.valueOf(zapytanie.getStatus());
            String terminRealizacji = String.valueOf(zapytanie.getTerminRealizacji());
            String klient = String.valueOf(zapytanie.getPozycja().getZamowienie().getKlient().getNazwaFirmy());
            String towar = String.valueOf(zapytanie.getPozycja().getTowar().getNazwa());

            Label statusLabel = new Label(status);
            if(zapytanie.getStatus()==StatusZapytania.zatwierdzone) statusLabel.setBackground(ZATWIERDZONE);
            else if(zapytanie.getStatus()==StatusZapytania.odrzucone) statusLabel.setBackground(ODRZUCONE);

            gpListaZapytan.addRow(i+1, new Label(id), statusLabel, new Label(terminRealizacji), new Label(klient), new Label(towar), new Label("ascdf"), otworz);
            gpListaZapytan.getRowConstraints().add(new RowConstraints(50));
        }
    }
    private void setGridViewConstraints()
    {
        gpListaZapytan.getColumnConstraints().get(0).setMaxWidth(30);
        gpListaZapytan.getColumnConstraints().get(0).setMinWidth(30);

       gpListaZapytan.getColumnConstraints().get(1).setMinWidth(100);
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
