
import javafx.application.Platform;
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
import java.time.LocalDate;
import java.util.*;


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

    @FXML
    GridPane gpPozycjeZamowienia;

    ArrayList<Towar> list = new ArrayList<>();
    LinkedList<Button> buttons = new LinkedList<>();

    public void fillGridView()
    {
        for(int i =0; i < list.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            towar = list.get(i);
            String nazwa = towar.getNazwa();
            String cena = String.valueOf(towar.getCenaJn());
            String ilosc = String.valueOf(i);
            String rabat = String.valueOf(Math.random()*1).substring(0,4);
            String wartosc = String.valueOf(i*towar.getCenaJn());
            String dataRealizacji = LocalDate.now().toString();

            Button usun = new Button("Usun");
            Button edytuj = new Button("Edytuj");
            edytuj.setDisable(true);

                buttons.add(usun);
                usun.setOnAction(new EventHandler<ActionEvent>() {
                    private Button bRef;

                    @Override
                    public void handle(ActionEvent e) {
                        usunPozycje(buttons.indexOf(bRef));
                    }

                    private EventHandler<ActionEvent> init(Button b) {
                        bRef = b;
                        return this;
                    }
                }.init(usun));


            gpPozycjeZamowienia.addRow(i, new Label(nazwa), new Label(cena), new Label(ilosc), new Label(rabat), new Label(wartosc), new Label(dataRealizacji), usun, edytuj);
            gpPozycjeZamowienia.getRowConstraints().add(new RowConstraints(50));
        }
    }

    private void usunPozycje(int index)
    {
        buttons.clear();
//        gpPozycjeZamowienia.getChildren().removeIf(node -> GridPane.getRowIndex(node) == index); TO-DO
        list.remove(index);
        gpPozycjeZamowienia.getChildren().clear();
        fillGridView();
    }

    public void setGridViewConstraints()
    {
        gpPozycjeZamowienia.getColumnConstraints().get(0).setMinWidth(250);

        gpPozycjeZamowienia.getColumnConstraints().get(1).setMinWidth(100);
        gpPozycjeZamowienia.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(2).setMinWidth(70);
        gpPozycjeZamowienia.getColumnConstraints().get(2).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(3).setMinWidth(100);
        gpPozycjeZamowienia.getColumnConstraints().get(3).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(4).setMinWidth(100);
        gpPozycjeZamowienia.getColumnConstraints().get(4).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(5).setMinWidth(120);
        gpPozycjeZamowienia.getColumnConstraints().get(5).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(6).setMinWidth(70);
        gpPozycjeZamowienia.getColumnConstraints().get(6).setHalignment(HPos.CENTER);

        gpPozycjeZamowienia.getColumnConstraints().get(7).setMinWidth(70);
        gpPozycjeZamowienia.getColumnConstraints().get(7).setHalignment(HPos.CENTER);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.add(new Towar(1,"asdf",19));
        list.add(new Towar(2,"asdfa",18));
        list.add(new Towar(3,"asdfb",17));
        list.add(new Towar(4,"asdfc",16));
        list.add(new Towar(5,"asdfd",15));
        list.add(new Towar(6,"asdfq",14));
        list.add(new Towar(7,"asdfw",13));
        list.add(new Towar(8,"asdfe",12));
        list.add(new Towar(9,"asdfr",11));

        fillGridView();
        setGridViewConstraints();
        
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

    public void czyNiedostepnaNotify(boolean czyNiedostepna)
    {
        if(czyNiedostepna) {
            lbBrakDostepnosci.setVisible(false);
            btnWyslijZapytanie.setVisible(false);
        }
        else
        {
            lbBrakDostepnosci.setVisible(true);
            btnWyslijZapytanie.setVisible(true);
        }
    }

    @FXML
    private void dodajPozycjeZamowienia()
    {
    }

    @FXML
    private void dodajPozycje()
    {
    }

}
