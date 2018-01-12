import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ListaKlientowController extends ViewController implements Initializable {

    ZamowieniaRepository pozycjaZamRepo;

    @FXML
    Button btnX;

    @FXML
    GridPane gpListaKlientow;

    @FXML
    TextField tfKlientSzukany;

    LinkedList<Button> buttons = new LinkedList<>();
    ArrayList<Klient> klienciList;
    KlienciRepository klienciRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
        klienciRepository = new KlienciRepository();
        klienciList = (ArrayList<Klient>) klienciRepository.getKlienci();  //TO TEST

        prepareData();
        fillGridView();
        setGridViewConstraints();
    }

    public void setZamowienieRepository(ZamowieniaRepository pozycjaZamRepo) {
        this.pozycjaZamRepo = pozycjaZamRepo;
    }


    public void fillGridView()
    {
        fillGridView(null);
    }

    private void wybierzKlienta(int i, ActionEvent e) {
        pozycjaZamRepo.dodajKlienta(klienciList.get(i));
        zamknijOkno(e);
    }


    public void setGridViewConstraints()
    {
        gpListaKlientow.getColumnConstraints().get(0).setMinWidth(175);

        gpListaKlientow.getColumnConstraints().get(1).setMinWidth(100);
        gpListaKlientow.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaKlientow.getColumnConstraints().get(2).setMinWidth(100);
        gpListaKlientow.getColumnConstraints().get(2).setHalignment(HPos.RIGHT);



    }
    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        zamknijOkno(e);
    }


    public void szukaj(ActionEvent event) {
        fillGridView(tfKlientSzukany.getText().toString());
    }
    public void prepareData()
    {
        klienciList.add(new Klient("Adam", 1238374839));
        klienciList.add(new Klient("Adam1", 1238374838));
        klienciList.add(new Klient("Adam2", 1238374837));
        klienciList.add(new Klient("Adam3", 1238374836));
        klienciList.add(new Klient("Adam4", 1238374835));
        klienciList.add(new Klient("Adam5", 1238374834));
    }
    public void fillGridView(String nazwaSzukana)
    {
        gpListaKlientow.getChildren().clear();
        buttons.clear();

        //ArrayList<Towar> klienciList = (ArrayList<Towar>) klienciRepository.getTowary();


        gpListaKlientow.addRow(0, new Label("Nazwa Firmy"), new Label("NIP"));

        int rowNumber = 1;

        for(int i =0; i < klienciList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            Klient klient = klienciList.get(i);
            String nazwaFirmy = klient.getNazwaFirmy();
            String nip = Formater.getNipString(klient.getNip());
            if(nip.matches(".*"+nazwaSzukana+".*") || nazwaSzukana == null || nip.matches(".*"+klient.getNip()+".*")) {
                Button wybierz = new Button("Wybierz");

                buttons.add(wybierz);
                wybierz.setOnAction(new EventHandler<ActionEvent>() {
                    private Button bRef;

                    @Override
                    public void handle(ActionEvent e) {
                        wybierzKlienta(buttons.indexOf(bRef), e);
                    }

                    private EventHandler<ActionEvent> init(Button b) {
                        bRef = b;
                        return this;
                    }
                }.init(wybierz));


                gpListaKlientow.addRow(rowNumber, new Label(nazwaFirmy), new Label(nip), wybierz);
                gpListaKlientow.getRowConstraints().add(new RowConstraints(50));

                rowNumber++;
            }
        }
    }
}
