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

public class ListaTowarowController extends ViewController implements Initializable {

    private ZamowieniaRepository pozycjaZamRepo;

    @FXML
    Button btnX;

    @FXML
    TextField tfNazwaSzukana;

    @FXML
    GridPane gpListaTowarow;

    private LinkedList<Button> buttons = new LinkedList<>();
    private ArrayList<Towar> towaryList;
    private TowaryRepository towaryRepository;

    /**
     *Metoda wywowyłana przy tworzeniu okienka, inicjalizuje początkowe Wartości
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX;
        setGridViewConstraints();
    }

    /**
     * Wypełnia GridView aktualnymi wartościami
     */
    public void updateView()
    {
        fillGridView();
    }
    public void setZamowieniaRepository(ZamowieniaRepository pozycjaZamRepo) {
        this.pozycjaZamRepo = pozycjaZamRepo;
    }


    /**
     * Przesłonięta metoda aktualizacji GridView Towarów, wywoływana gdy nie mamy żadnych kryteriów wyszukiwania
     */
    private void fillGridView()
    {
        fillGridView(null);
    }

    /**
     * Wywoływana w momencie naciśnięciu przycisku wybierz przy odpowiednim towarze.
     * Ustawia wybrany towar i zamyka okno
     * @param i - index wybranego parametru
     * @param e - ActionEvent naciśnięcia przycisku przy danym towarze
     */
    private void wybierzTowar(int i, ActionEvent e) {
        towaryRepository.setTowar(towaryRepository.towary.remove(i));
        zamknijOkno(e);
    }

    /**
     * Ustala szerokość kolumn oraz wyrównanie tekstu
     */
    private void setGridViewConstraints()
    {
        gpListaTowarow.getColumnConstraints().get(0).setMinWidth(270);

        gpListaTowarow.getColumnConstraints().get(1).setMinWidth(80);
        gpListaTowarow.getColumnConstraints().get(1).setHalignment(HPos.CENTER);

        gpListaTowarow.getColumnConstraints().get(2).setMinWidth(80);
        gpListaTowarow.getColumnConstraints().get(2).setHalignment(HPos.CENTER);

        gpListaTowarow.getColumnConstraints().get(3).setMinWidth(70);
        gpListaTowarow.getColumnConstraints().get(3).setHalignment(HPos.RIGHT);


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

    public void setTowaryRepository(TowaryRepository towaryRepository) {
        this.towaryRepository = towaryRepository;
        towaryList = (ArrayList<Towar>) this.towaryRepository.towary;
    }

    /**
     * Wywołanie akcji szukaj na Liście towarów z ustalonym kryterium (nazwa)
     * @param event - ActionEvent naciśnięcia przycisku szukaj
     */
    public void szukaj(ActionEvent event) {
     fillGridView(tfNazwaSzukana.getText());
    }

    /**
     * Wypełnia GridView Towarów danymi
     * @param nazwaSzukana - kryteria wyszukiwania, szukana nazwa towaru
     */
    private void fillGridView(String nazwaSzukana)
    {
        gpListaTowarow.getChildren().clear();
        buttons.clear();


        int rowNumber = 1;
        gpListaTowarow.addRow(0, new Label("Nazwa"), new Label("Ilość:"), new Label("Cena j."));
        for(int i =0; i < towaryList.size(); i++)
        {

            //USTALANIE TESTOWYCH WARTOSCI
            Towar towar = towaryList.get(i);
            String nazwa = towar.getNazwa();
            if(nazwa.matches(".*"+nazwaSzukana+".*") || nazwaSzukana == null) {
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


                gpListaTowarow.addRow(rowNumber, new Label(nazwa), new Label(ilosc), new Label(cena), wybierz);
                gpListaTowarow.getRowConstraints().add(new RowConstraints(50));

                rowNumber++;
            }
        }
    }
}