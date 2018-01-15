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

/**
 * Klasa obsługująca okienko wyboru klienta
 */
public class ListaKlientowController extends ViewController implements Initializable {

    private ZamowieniaRepository pozycjaZamRepo;

    @FXML
    Button btnX;

    @FXML
    GridPane gpListaKlientow;

    @FXML
    TextField tfKlientSzukany;

    private LinkedList<Button> buttons = new LinkedList<>();
    private ArrayList<Klient> klienciList;
    private KlienciRepository klienciRepository;

    /**
     *Metoda wywowyłana przy tworzeniu okienka, inicjalizuje początkowe wartości i wyświetla listę klientów
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
        klienciRepository = new KlienciRepository();
        klienciList = klienciRepository.getKlienci();
        fillGridView();
        setGridViewConstraints();
    }

    public void setZamowienieRepository(ZamowieniaRepository pozycjaZamRepo) {
        this.pozycjaZamRepo = pozycjaZamRepo;
    }

    /**
     * Wypełnia GridView aktualnymi wartościami
     */
    private void fillGridView()
    {
        fillGridView(null);
    }
    /**
     * Wywoływana w momencie naciśnięciu przycisku wybierz przy odpowiednim kliencie.
     * Ustawia wybranego klienta i zamyka okno
     * @param i - index wybranego parametru
     * @param e - ActionEvent naciśnięcia przycisku przy danym kliencie
     */
    private void wybierzKlienta(int i, ActionEvent e) {
        pozycjaZamRepo.dodajKlienta(klienciList.get(i));
        zamknijOkno(e);
    }

    /**
     * Ustala szerokość kolumn oraz wyrównanie tekstu
     */
    private void setGridViewConstraints()
    {
        gpListaKlientow.getColumnConstraints().get(0).setMinWidth(320);

        gpListaKlientow.getColumnConstraints().get(1).setMinWidth(100);
        gpListaKlientow.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaKlientow.getColumnConstraints().get(2).setMinWidth(100);
        gpListaKlientow.getColumnConstraints().get(2).setHalignment(HPos.RIGHT);



    }

    /**
     * Wywoływana przy zamknięciu okienka przyciskiem X lub powrót
     * @param e
     */
    @FXML
    private void powrot(javafx.event.ActionEvent e)
    {
        zamknijOkno(e);
    }

    /**
     * Wywołanie akcji szukaj na Liście klientów w z ustalonym kryterium (NIP)
     * @param event - ActionEvent naciśnięcia przycisku szukaj
     */
    public void szukaj(ActionEvent event) {
        fillGridView(tfKlientSzukany.getText());
    }

    /**
     * Wypełnia GridView informacjiami Klientów zgodnie z ustalonymi kryteriami.
     * @param nazwaSzukana - kryteria wyszukiwania, nip klienta
     */
    private void fillGridView(String nazwaSzukana)
    {
        gpListaKlientow.getChildren().clear();
        buttons.clear();

        ArrayList<Klient> klienciList = klienciRepository.getKlienci();


        gpListaKlientow.addRow(0, new Label("Nazwa Firmy"), new Label("NIP"));

        int rowNumber = 1;

        for(int i =0; i < klienciList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            Klient klient = klienciList.get(i);
            String nazwaFirmy = klient.getNazwaFirmy();
            String nip = Formater.getNipString(klient.getNip());
            if(nip.matches(".*"+nazwaSzukana+".*") || nazwaSzukana == null || nazwaSzukana.matches(".*"+klient.getNip()+".*")) {
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