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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ListaTowarowController extends ViewController implements Initializable {

    ZamowieniaRepository pozycjaZamRepo;

    @FXML
    Button btnX;

    @FXML
    GridPane gpListaTowarow;

    LinkedList<Button> buttons = new LinkedList<>();
    ArrayList<Towar> towaryList;
    TowaryRepository towaryRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
    }

    public void updateView()
    {
        //TO TEST
        fillGridView();
        setGridViewConstraints();
    }
    public void setZamowieniaRepository(ZamowieniaRepository pozycjaZamRepo) {
        this.pozycjaZamRepo = pozycjaZamRepo;
    }


    public void fillGridView()
    {
        gpListaTowarow.getChildren().clear();
        buttons.clear();

        //ArrayList<Towar> towaryList = (ArrayList<Towar>) towaryRepository.getTowary();
        towaryList.add(new Towar(1,"srubka1", 100, 1.5f));
        towaryList.add(new Towar(2,"srubka2", 100, 1.5f));
        towaryList.add(new Towar(1,"srubka3", 100, 1.5f));
        towaryList.add(new Towar(2,"srubka4", 100, 1.5f));
        towaryList.add(new Towar(1,"srubka5", 100, 1.5f));
        towaryList.add(new Towar(2,"srubka", 100, 1.5f));
        towaryList.add(new Towar(1,"srubka", 100, 1.5f));
        towaryList.add(new Towar(2,"srubka", 100, 1.5f));

        gpListaTowarow.addRow(0, new Label("Nazwa"), new Label("Dostepna Ilość"), new Label("Cena j."));
        for(int i =0; i < towaryList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            Towar towar = towaryList.get(i);
            String nazwa = towar.getNazwa();
            String ilosc = String.valueOf(towar.getIlosc());
            String cena = Formater.formatujCene(towar.getCenaJn());

            Button wybierz = new Button("Wybierz");

            buttons.add(wybierz);
            wybierz.setOnAction(new EventHandler<ActionEvent>() {
                private Button bRef;

                @Override
                public void handle(ActionEvent e) {
                    wybierzTowar(buttons.indexOf(bRef), e);
                }

                private EventHandler<ActionEvent> init(Button b) {
                    bRef = b;
                    return this;
                }
            }.init(wybierz));


            gpListaTowarow.addRow(i+1, new Label(nazwa), new Label(ilosc), new Label(cena), wybierz);
            gpListaTowarow.getRowConstraints().add(new RowConstraints(50));
        }
    }

    private void wybierzTowar(int i, ActionEvent e) {
        towaryRepository.setTowar(towaryList.get(i));
      //  pozycjaZamRepo.utworzPozycjeZamowienia(towaryList.get(i)); //zastąpić repo
        zamknijOkno(e);
    }

    public void setGridViewConstraints()
    {
        gpListaTowarow.getColumnConstraints().get(0).setMinWidth(175);

        gpListaTowarow.getColumnConstraints().get(1).setMinWidth(100);
        gpListaTowarow.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaTowarow.getColumnConstraints().get(2).setMinWidth(100);
        gpListaTowarow.getColumnConstraints().get(2).setHalignment(HPos.CENTER);

        gpListaTowarow.getColumnConstraints().get(3).setMinWidth(70);
        gpListaTowarow.getColumnConstraints().get(3).setHalignment(HPos.RIGHT);


    }
    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        zamknijOkno(e);
    }


    public void setTowaryRepository(TowaryRepository towaryRepository) {
        this.towaryRepository = towaryRepository;
        towaryList = (ArrayList<Towar>) towaryRepository.getTowary();
    }
}
