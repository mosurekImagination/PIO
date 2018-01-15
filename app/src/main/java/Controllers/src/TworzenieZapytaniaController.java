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
 * Kontroler obsługujący okienko tworzenia zapytania
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
     * Funkcja wywoływana przy tworzeniu okienka, inicjalizuje początkowe zmienne
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
     * Aktualizuje okno ustawiając tekst na odpowiednij labelkach:
     * nazwa towaru i ilość towaru
     */
    public void updateView()
    {
        lbNazwaTowaru.setText(pozycje.getPozycja().getTowar().getNazwa());
        lbIlosc.setText(String.valueOf(pozycje.getPozycja().getIlosc()));
    }

    /**
     * Metoda obsługijąca akcję wciśnięcia przycisku wysłania zapytania.
     * Dodaje zapytanie z odpowiednimi wartościami, dodaje do aktualnego zamówienia i zamyka okno
     * @param e - ActionEvent naciśnięcia przycisku Wysłania zapytania
     */
    @FXML
    public void wyslijZapytanie(ActionEvent e)
    {
        boolean poprawnyInput=true;
        if(dateTermin != null) {
            if(dateTermin.getValue().isAfter(LocalDate.now().minusDays(1))) {
                pozycje.dodajZapytanie(new Zapytanie(dateTermin.getValue(), pozycje.getPozycja()));
                poprawnyInput = true;
            }
            else
            {
                poprawnyInput = false;
                wyswietlKomunikat("Nieprawodłowa data. Wybierz prawidłową datę.");
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
     * Metoda wywoływana po naciśnięciu przycisku Anuluj. Anuluje wysyłanie zapytania i zamyka okno
     * @param e - Action Event naciśnięcia przycisku Anuluj
     */
    @FXML
    public void anuluj(ActionEvent e){
        zamowieniaRepository.setCzyMoznaZamowic(true);
        zamknijOkno(e);
    }




}
