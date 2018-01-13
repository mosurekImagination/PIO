import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class NoweZamowienieController extends ViewController implements Initializable, Observer{

    private static final String ZLY_RABAT_KOMUNIKAT = "Podano zly rabat. Musi siê mieœciæ miedzy 0 a 99";
    private static final String ZLA_ILOSC_KOMUNIKAT = "Podano zla ilosc. Musi byc wieksza od 0";

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
    @FXML Label lbNazwaFirmy;
    @FXML Label lbNip;

    @FXML
    TextField tfIlosc;
    @FXML
    TextField tfRabat;

    @FXML
    GridPane gpPozycjeZamowienia;


    ZamowieniaRepository zamowieniaRepository;
    PozycjeZamowieniaRepository pozycjaZamowieniaRepository;
    TowaryRepository towaryRepository;
    Boolean czyKolejneZamowienie;

    Towar towar = new Towar(1, "Sruba", 50,0.01);
    PozycjaZamowienia pozycjaZamowienia= new PozycjaZamowienia(1, towar, 100, 5);

    ArrayList<Towar> list = new ArrayList<>();
    LinkedList<Button> buttons = new LinkedList<>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    boolean wlaczonoPodzial = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeButton=btnPowrot;

        towaryRepository = new TowaryRepository();
        towaryRepository.addObserver(this);
        zamowieniaRepository = new ZamowieniaRepository();
        zamowieniaRepository.addObserver(this);
        pozycjaZamowieniaRepository = new PozycjeZamowieniaRepository();

        fillGridView();
        setGridViewConstraints();

        czyKolejneZamowienie = false;

    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg != null && arg instanceof String){
            wyswietlKomunikat((String) arg);
        }
        else if(arg != null && arg instanceof Towar){
            lbNazwaTowaru.setText(((Towar) arg).getNazwa());
        }
        else if(arg != null && arg instanceof Klient){
            lbNazwaFirmy.setText("Nazwa firmy: " + ((Klient)arg).getNazwaFirmy());
            lbNip.setText("NIP: " +Formater.getNipString(((Klient) arg).getNip()));
        }
        else {
            lbNazwaTowaru.setText("");
            tfRabat.setText("");
            tfIlosc.setText("");
            for (PozycjaZamowienia pozycjaZamowienia : zamowieniaRepository.getPozycje()) {
                System.out.println(pozycjaZamowienia);
            }

            czyNiedostepnaNotify(!zamowieniaRepository.czyMoznaZamowic);
            if(zamowieniaRepository.czyMoznaZamowic)
            {
                lbSuma.setText(Formater.formatujCene(zamowieniaRepository.getSuma()));
                lbDataRealizacji.setText(zamowieniaRepository.getTerminRealizacji());
                fillGridView();
            }
        }
    }

    public void fillGridView()
    {
        gpPozycjeZamowienia.getChildren().clear();
        buttons.clear();
        checkBoxes.clear();

        ArrayList<PozycjaZamowienia> pozycjeList = (ArrayList<PozycjaZamowienia>) zamowieniaRepository.getPozycje();

        gpPozycjeZamowienia.addRow(0, new Label(""), new Label("Nazwa"), new Label("Cena:"), new Label("Ilosc"), new Label("Rabat"), new Label("Wartoœæ:"), new Label("Data realizacji"));

        for(int i =0; i < pozycjeList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            PozycjaZamowienia pozycja = pozycjeList.get(i);
            String nazwa = pozycja.getTowar().getNazwa();
            String cena = Formater.formatujCene(pozycja.getTowar().getCenaJn());
            String ilosc = String.valueOf(pozycja.getIlosc());
            String rabat = String.valueOf(pozycja.getRabat()) + " %";

            String wartosc = Formater.formatujCene(pozycja.getCenaPoRabacie());
            String dataRealizacji = pozycja.getTerminRealizacji() == null ? " " : pozycja.getTerminRealizacji().toString();

            Button usun = new Button("Usun");
            Button edytuj = new Button("Edytuj");


            CheckBox doPodzialu = new CheckBox();
            if(!wlaczonoPodzial) doPodzialu.setVisible(false);

            edytuj.setDisable(true);

            buttons.add(usun);
            checkBoxes.add(doPodzialu);
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


            gpPozycjeZamowienia.addRow(i+1, doPodzialu, new Label(nazwa), new Label(cena), new Label(ilosc), new Label(rabat), new Label(wartosc), new Label(dataRealizacji), usun, edytuj);
            gpPozycjeZamowienia.getRowConstraints().add(new RowConstraints(50));
        }
    }


    public void setGridViewConstraints()
    {
        gpPozycjeZamowienia.getColumnConstraints().get(0).setMaxWidth(1);

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

    public void showCheckBoxes()
    {
        if(checkBoxes!=null && !checkBoxes.isEmpty()) {
            for (CheckBox c : checkBoxes) c.setVisible(true);
        }
    }


    private void usunPozycje(int index)
    {
       //gpPozycjeZamowienia.getChildren().removeIf(node -> GridPane.getRowIndex(node) == index); TO-DO
        zamowieniaRepository.usunPozycje(index);
    }

    @FXML
    private void powrot(ActionEvent e)
    {
        if(!czyKolejneZamowienie) otworzOkno("OknoGlowne.fxml", MALE_OKNO);
        zamknijOkno(e);
    }



    @FXML
    private void wyslijZapytanie()
    {
        pozycjaZamowieniaRepository.utworzPozycjeZamowienia(towaryRepository.getTowar());
        TworzenieZapytaniaController twController = (TworzenieZapytaniaController) otworzOkno("TworzenieZapytania.fxml", MALE_OKNO);
        twController.setPozycjaZamowienia(pozycjaZamowieniaRepository);
        twController.setZamowieniaRepository(zamowieniaRepository);
        twController.updateView();
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
            if(zamowieniaRepository.sprawdzDostepnoscTowaru(towaryRepository.getTowar(),iIlosc)) {
                zamowieniaRepository.utworzPozycjeZamowienia(towaryRepository.getTowar());
                zamowieniaRepository.aktualizujPozycje(iIlosc, iRabat);
            }
            else {
                zamowieniaRepository.setCzyMoznaZamowic(false);
            }
        }
    }

    @FXML void wybierzTowar(ActionEvent event){
        ListaTowarowController twController = (ListaTowarowController) otworzOkno("ListaTowarow.fxml", MALE_OKNO);
        twController.setTowaryRepository(towaryRepository);
        twController.updateView();
        twController.setZamowieniaRepository(zamowieniaRepository);
    }

    @FXML void zlozZamowienie(ActionEvent event){
        if(zamowieniaRepository.przeslijZamowienie())
        if(czyKolejneZamowienie){
            zamknijOkno(event);
        }else {
            powrot(event);
        }
        wyswietlKomunikat("Zamówienie zosta³o z³o¿one");
    }

    @FXML void wybierzKlienta(ActionEvent event){
        ListaKlientowController klienciController = (ListaKlientowController) otworzOkno("ListaKlientow.fxml", MALE_OKNO);
        klienciController.setZamowienieRepository(zamowieniaRepository);
    }

    @FXML void podzielZamowienie(){

        if(checkBoxes != null && !checkBoxes.isEmpty()) {
            showCheckBoxes();

            btnPodzielZamowienie.setText("Wygeneruj Zamówienie");
            btnPodzielZamowienie.setOnAction(e -> wygenerujNoweZamowienie());
            wlaczonoPodzial = true;
        }

    }

    @FXML
    private void wygenerujNoweZamowienie()
    {
        ArrayList<Integer> indeksyDoPodzialu = new ArrayList<>();
        for (CheckBox c : checkBoxes) {
            if (c.isSelected()) indeksyDoPodzialu.add(checkBoxes.indexOf(c));
        }
        NoweZamowienieController nZController = (NoweZamowienieController) otworzOkno("NoweZamowienie.fxml", DUZE_OKNO);
        Zamowienie zamowienie = zamowieniaRepository.wydzielPozycje(indeksyDoPodzialu);
        nZController.setNoweZamowienie(zamowienie);

    }

    public void setNoweZamowienie(Zamowienie zamowienie) {
        zamowieniaRepository = new ZamowieniaRepository();
        zamowieniaRepository.addObserver(this);
        zamowieniaRepository.setZamowienie(zamowienie);
        zamowieniaRepository.setCzyMoznaZamowic(true);
        czyKolejneZamowienie = true;
        btnPowrot = closeButton;
    }
}