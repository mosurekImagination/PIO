import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Kontroler obslugujacy okienko tworzenia zapytania
 */
public class TworzenieZapytaniaController extends ViewController implements Initializable{

    @FXML
    Button btnX;

    @FXML
    Label lbIlosc;

    @FXML
    Label lbNazwaTowaru;

    @FXML
    Button btnAnuluj;

    @FXML
    Button btnWyslij;

    @FXML
    DatePicker dateTermin;

    private PozycjeZamowieniaRepository pozycje;
    private ZamowieniaRepository zamowieniaRepository;


    /**
     * Funkcja wywolywana przy tworzeniu okienka, inicjalizuje poczatkowe zmienne
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton = btnX; pozycje = new PozycjeZamowieniaRepository();
    }

    public void setPozycjaZamowienia(PozycjeZamowieniaRepository zp)
    {
        pozycje=zp;
    }
    public void setZamowieniaRepository(ZamowieniaRepository zr)
    {
        zamowieniaRepository=zr;
    }

    /**
     * Aktualizuje okno ustawiajac tekst na odpowiednij labelkach:
     * nazwa towaru i ilosc towaru
     */
    public void updateView()
    {
        lbNazwaTowaru.setText(pozycje.getPozycja().getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(pozycje.getPozycja().getIlosc()));
    }

    /**
     * Metoda obslugijaca akcje wcisniecia przycisku wyslania zapytania.
     * Dodaje zapytanie z odpowiednimi wartosciami, dodaje do aktualnego zamowienia i zamyka okno
     * @param e - ActionEvent nacisniecia przycisku Wyslania zapytania
     */
    @FXML
    public void wyslijZapytanie(ActionEvent e)
    {
        boolean poprawnyInput=true;
        if(dateTermin.getValue() != null) {
            if(dateTermin.getValue().isAfter(LocalDate.now().minusDays(1))) {
                pozycje.dodajZapytanie(new Zapytanie(dateTermin.getValue(), pozycje.getPozycja()));
                poprawnyInput = true;
            }
            else
            {
                poprawnyInput = false;
                wyswietlKomunikat("Nieprawodlowa data. Wybierz prawidlowa date.");
            }
        }
        else
        {
            pozycje.dodajZapytanie(new Zapytanie(pozycje.getPozycja()));
        }

        if(poprawnyInput) {
            pozycje.getPozycja().getZapytanie().zmienStatusZapytania(StatusZapytania.wyslane);
            zamowieniaRepository.setCzyMoznaZamowic(true);
            zamowieniaRepository.dodajPozycjeZamowienia(pozycje.getPozycja());
            zamknijOkno(e);
        }
    }

    /**
     * Metoda wywolywana po nacisnieciu przycisku Anuluj. Anuluje wysylanie zapytania i zamyka okno
     * @param e - Action Event nacisniecia przycisku Anuluj
     */
    @FXML
    public void anuluj(ActionEvent e){
        zamowieniaRepository.setCzyMoznaZamowic(true);
        zamknijOkno(e);
    }




}
