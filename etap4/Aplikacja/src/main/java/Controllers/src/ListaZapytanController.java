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

/**
 * Kontroler obslugujacy okienko listy zapytan
 */
public class ListaZapytanController extends ViewController implements Initializable,Observer {

    @FXML
    Button btnPowrot;

    @FXML
    GridPane gpListaZapytan;

    private ZapytaniaRepository zapytaniaRepo;

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Zapytanie> zapytania= new ArrayList<>();

    private static Background ODRZUCONE = new Background( new BackgroundFill(Color.PALEVIOLETRED, new CornerRadii(1),
            new Insets(0.0,0.0,0.0,0.0)));
    private static Background ZATWIERDZONE = new Background( new BackgroundFill(Color.LIMEGREEN, new CornerRadii(1),
            new Insets(0.0,0.0,0.0,0.0)));


     /**
     *Metoda wywowylana przy tworzeniu okienka, inicjalizuje poczatkowe Wartosci
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnPowrot;

        zapytaniaRepo = new ZapytaniaRepository();
        zapytaniaRepo.pobierzZapytania();
        zapytaniaRepo.addObserver(this);

        fillGridView();
        setGridViewConstraints();
    }

    /**
     * Metoda wywolywana po kazdej aktualizacji obiektu (Zapytania)
     * Wywoluje aktualizacje GridView
     */
    @Override
    public void update(Observable o, Object arg) {
        gpListaZapytan.getChildren().clear();
        buttons.clear();
        fillGridView();
    }

    /**
     * Wypelnia GridView Zapytan danymi
     */
    private void fillGridView()
    {
        ArrayList<Zapytanie> zapytania = zapytaniaRepo.getZapytania();

        gpListaZapytan.addRow(0, new Label("id"),new Label("Status"), new Label("Data zlozenia:"), new Label("Termin Realizacji:"), new Label("Klient"), new Label("NazwaTowaru"));
        for(int i=0; i<zapytania.size();i++)
        {
            Button otworz = new Button("Otworz");
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
            String terminRealizacji = zapytanie.getTerminRealizacji() == null ? "" : String.valueOf(zapytanie.getTerminRealizacji());
            String klient = String.valueOf(zapytanie.getPozycja().getZamowienie().getKlient().getNazwaFirmy());
            String dataZlozenia = String.valueOf(zapytanie.getPozycja().getZamowienie().getDataZlozZam());
            String towar = String.valueOf(zapytanie.getPozycja().getTowar().getNazwa());

            Label statusLabel = new Label(status);
            if(zapytanie.getStatus()==StatusZapytania.zatwierdzone) statusLabel.setBackground(ZATWIERDZONE);
            else if(zapytanie.getStatus()==StatusZapytania.odrzucone) statusLabel.setBackground(ODRZUCONE);

            gpListaZapytan.addRow(i+1, new Label(id), statusLabel, new Label(dataZlozenia), new Label(terminRealizacji), new Label(klient), new Label(towar),otworz);
            gpListaZapytan.getRowConstraints().add(new RowConstraints(50));
        }
    }

    /**
     * Ustala szerokosc kolumn oraz wyrownanie tekstu
     */
    private void setGridViewConstraints()
    {
        gpListaZapytan.getColumnConstraints().get(0).setMaxWidth(40);
        gpListaZapytan.getColumnConstraints().get(0).setMinWidth(40);
        gpListaZapytan.getColumnConstraints().get(0).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(1).setMinWidth(100);
        gpListaZapytan.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(2).setMinWidth(120);
        gpListaZapytan.getColumnConstraints().get(2).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(3).setMinWidth(120);
        gpListaZapytan.getColumnConstraints().get(3).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(4).setMinWidth(230);
        gpListaZapytan.getColumnConstraints().get(4).setHalignment(HPos.CENTER);

        gpListaZapytan.getColumnConstraints().get(5).setMinWidth(290);

       gpListaZapytan.getColumnConstraints().get(6).setMinWidth(70);
       gpListaZapytan.getColumnConstraints().get(6).setHalignment(HPos.CENTER);



    }

    /**
     * Wywolywana przy zamknieciu okienka przyciskiem X lub powrot
     * @param e - ActionEvent ww przyciskow
     */
    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }


}
