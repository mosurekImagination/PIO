/**
 * Created by Asus on 2018-01-13.
 */

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Automatyzacja testów dla PU składania zamówienia.
 */
public class AppTest extends ApplicationTest {

    NoweZamowienieController vc;

    @Override
    public void start(Stage stage) throws Exception {
        NoweZamowienieController vc = new NoweZamowienieController();
        vc = (NoweZamowienieController) vc.otworzOkno("NoweZamowienie.fxml", vc.DUZE_OKNO);

    }

    public <T extends Node> T find(final String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }


    @Test
    public void testDodajPozycje() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("15", "0");
        sleep(500);
        clickOn("#btnZlozZamowienie");
    }

    @Test
    public void testZlozZamowienieBezPozycji() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnZlozZamowienie");
        sleep(500);
    }

    @Test
    public void testZlozZamowienieBezKlienta() {
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(10));
        sleep(1000);
        dodajPozycje("2", "0");
        sleep(500);
        clickOn("#btnZlozZamowienie");
        sleep(500);
    }

    @Test
    public void testUsunDodanaPozycje() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        for (int i = 0; i < 2; i++) {
            clickOn("#btnDodajTowar");
            GridPane gp_towary = find("#gpListaTowarow");
            clickOn(gp_towary.getChildren().get(6 + (i * 4)));
            dodajPozycje(Integer.toString(i + 1), Integer.toString(i));
        }
        sleep(500);
        GridPane gp = find("#gpPozycjeZamowienia");
        clickOn(gp.getChildren().get(14));
        sleep(1000);
    }

    @Test
    public void testWyslijZapytanie() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("100", "0");
        clickOn("#btnWyslijZapytanie");
        sleep(500);
        clickOn("#btnWyslij");
        sleep(1500);
        clickOn("#btnZlozZamowienie");
    }

    @Test
    public void testPodzielZamowienie(){
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        for (int i = 0; i < 3; i++) {
            clickOn("#btnDodajTowar");
            GridPane gp_towary = find("#gpListaTowarow");
            clickOn(gp_towary.getChildren().get(6 + (i * 4)));
            dodajPozycje(Integer.toString(i + 1), Integer.toString(i));
        }
        sleep(500);
        clickOn("#btnPodzielZamowienie");
        GridPane gp = find("#gpPozycjeZamowienia");
        clickOn(gp.getChildren().get(7));
        CheckBox chb = (CheckBox)gp.getChildren().get(7);
        chb.setSelected(true);
        sleep(500);
        clickOn("#btnPodzielZamowienie");
        sleep(1000);
    }

    @Test
    public void testNieMoznaPodzielicZamowienia(){
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        for (int i = 0; i < 2; i++) {
            clickOn("#btnDodajTowar");
            GridPane gp_towary = find("#gpListaTowarow");
            clickOn(gp_towary.getChildren().get(6 + (i * 4)));
            dodajPozycje(Integer.toString(i + 1), Integer.toString(i));
            clickOn("#btnPodzielZamowienie");
            if (i == 1){
                GridPane gp = find("#gpPozycjeZamowienia");
                clickOn(gp.getChildren().get(7));
                CheckBox chb = (CheckBox)gp.getChildren().get(7);
                chb.setSelected(true);
                sleep(500);
                clickOn("#btnPodzielZamowienie");
                sleep(1000);
            }
        }
        sleep(500);
        clickOn("#btnPodzielZamowienie");
        sleep(1000);
    }

    @Test
    public void testDodajZlaIlosc(){
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("15.35", "0");
        sleep(500);
    }

    @Test
    public void testDodajZlyRabat(){
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("5", "156");
        sleep(500);
    }

    @Test
    public void testDodajRabatJakoLitere(){
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("5", "s");
        sleep(500);
    }

    private void dodajPozycje(String ilosc, String rabat) {
        moveTo("#tbIlosc");
        TextField tfIlosc = find("#tbIlosc");
        tfIlosc.setText(ilosc);
        sleep(1000);
        moveTo("#tbRabat");
        TextField tfRabat = find("#tbRabat");
        tfRabat.setText(rabat);
        find("#btnDodajPozycje").setDisable(false);
        sleep(1000);
        clickOn("#btnDodajPozycje");
        sleep(1000);
    }


}
