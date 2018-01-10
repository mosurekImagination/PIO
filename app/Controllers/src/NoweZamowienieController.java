
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

    private static final String ZLY_RABAT_KOMUNIKAT = "Podano zly rabat. Musi się mieścić miedzy 0 a 99";
    private static final String ZLA_ILOSC_KOMUNIKAT = "Podano zla ilosc. Musi byc wieksza od 0";

    ZamowieniaRepository zamowieniaRepository;
    PozycjeZamowieniaRepository pozycjaZamowieniaRepository;

    Towar towar = new Towar(1, "Sruba", 50,0.01);
    PozycjaZamowienia pozycjaZamowienia= new PozycjaZamowienia(1, towar, 100, 5);

    @FXML
    Button btnWybierz;
    @FXML
    Button btnDodajKlienta;
    @FXML
    Button btnDodajTowar;
    @FXML
    Button btnDodajPozycje;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.add(new Towar(1,"asdf",19,0.5));
        list.add(new Towar(2,"asdfa",18,0.6));
        list.add(new Towar(3,"asdfb",17,1));
        list.add(new Towar(4,"asdfc",16,1.2));
        list.add(new Towar(5,"asdfd",15,1.45));
        list.add(new Towar(6,"asdfq",14,1.15));
        list.add(new Towar(7,"asdfw",13,0.95));
        list.add(new Towar(8,"asdfe",12,0.99));
        list.add(new Towar(9,"asdfr",11,1.5));

        closeButton=btnPowrot;

        zamowieniaRepository = new ZamowieniaRepository();
        zamowieniaRepository.addObserver(this);

        fillGridView();
        setGridViewConstraints();

    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg != null && arg instanceof String){
            wyswietlKomunikat((String) arg);
        }
        else if(arg != null && arg instanceof PozycjaZamowienia){
            lbNazwaTowaru.setText(((PozycjaZamowienia) arg).getTowar().getNazwa());
        }
        else {

            for (PozycjaZamowienia pozycjaZamowienia : zamowieniaRepository.getPozycje()) {
                System.out.println(pozycjaZamowienia);
            }

            czyNiedostepnaNotify(!zamowieniaRepository.czyMoznaZamowic);
            if(zamowieniaRepository.czyMoznaZamowic)
            {
                lbSuma.setText(Float.toString(zamowieniaRepository.getSuma()));
                lbDataRealizacji.setText(zamowieniaRepository.getTerminRealizacji());
                fillGridView();
            }
        }

        System.out.println("===========================");

    }

    public void fillGridView()
    {
        gpPozycjeZamowienia.getChildren().clear();
        buttons.clear();

        ArrayList<PozycjaZamowienia> pozycjeList = (ArrayList<PozycjaZamowienia>) zamowieniaRepository.getPozycje();

        for(int i =0; i < pozycjeList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            PozycjaZamowienia pozycja = pozycjeList.get(i);
            String nazwa = pozycja.getTowar().getNazwa();
            String cena = String.valueOf(pozycja.getTowar().getCenaJn());
            String ilosc = String.valueOf(pozycja.getIlosc());
            String rabat = String.valueOf(pozycja.getRabat());
            String wartosc = String.valueOf(pozycja.getCenaPoRabacie());
            String dataRealizacji = pozycja.getTerminRealizacji() == null ? " " : pozycja.getTerminRealizacji().toString();

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


    public void fillGridView2()
    {
        gpPozycjeZamowienia.getChildren().clear();
        buttons.clear();

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



    private void usunPozycje(int index)
    {
       //gpPozycjeZamowienia.getChildren().removeIf(node -> GridPane.getRowIndex(node) == index); TO-DO
        zamowieniaRepository.usunPozycje(index);
    }

    @FXML
    private void powrot(ActionEvent e)
    {
        otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }



    @FXML
    private void wyslijZapytanie()
    {
        TworzenieZapytaniaController twController = (TworzenieZapytaniaController) otworzOkno("TworzenieZapytania.fxml", MALE_OKNO);
        twController.setPozycjaZamowienia(zamowieniaRepository);
    }

    public void czyNiedostepnaNotify(boolean czyNiedostepna)
    {
        if(czyNiedostepna) {
            lbBrakDostepnosci.setVisible(true);
            btnWyslijZapytanie.setVisible(true);
        }
        else
        {
            lbBrakDostepnosci.setVisible(false);
            btnWyslijZapytanie.setVisible(false);
        }
    }

    @FXML
    private void dodajPozycjeZamowienia()
    {
        String ilosc = tfIlosc.getText();
        String rabat = tfRabat.getText();
        if(!Walidator.czyDobryRabat(rabat)) {
            wyswietlKomunikat(ZLY_RABAT_KOMUNIKAT);
        } else if(!Walidator.czyDobraIlosc(ilosc)){
            wyswietlKomunikat(ZLA_ILOSC_KOMUNIKAT);
        }
        else {
            int iIlosc = Integer.parseInt(ilosc);
            int iRabat = Integer.parseInt(rabat);
            if(zamowieniaRepository.sprawdzDostepnoscTowaru(zamowieniaRepository.zamowienie.getIndexOstatniejPozycji(),iIlosc)) {
            }
            else {
                zamowieniaRepository.setCzyMoznaZamowic(false);
            }
            zamowieniaRepository.aktualizujPozycje(iIlosc, iRabat);

        }

    }

    @FXML void utworzPozycjeZamowienia(ActionEvent event){
        ListaTowarowController twController = (ListaTowarowController) otworzOkno("ListaTowarow.fxml", MALE_OKNO);
        twController.setZamowieniaRepository(zamowieniaRepository);
    }

    @FXML void zlozZamowienie(ActionEvent event){
        zamowieniaRepository.przeslijZamowienie();
    }

    @FXML void wybierzKlienta(ActionEvent event){
        ListaKlientowController klienciController = (ListaKlientowController) otworzOkno("ListaKlientow.fxml", MALE_OKNO);
        klienciController.setZamowienieRepository(zamowieniaRepository);
    }
}
