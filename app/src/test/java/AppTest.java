/**
 * Created by Asus on 2018-01-13.
 */

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Automatyzacja testów dla PU składania zamówienia.
 */
public class AppTest extends ApplicationTest {

    private static final String ZLY_RABAT_KOMUNIKAT = "Podano zly rabat. Rabat musi być liczbą całkowitą od 0 a 99";
    private static final String ZLA_ILOSC_KOMUNIKAT = "Podano zla ilosc. Ilość musi być liczbą całkowitą wiekszą od 0";

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
        dodajPozycje("1", "0");
        sleep(500);
        GridPane gp_pozycje = find("#gpPozycjeZamowienia");
        assertEquals(gp_pozycje.impl_getRowCount(), 2);
        assertTrue(!find("#btnZlozZamowienie").isDisabled());

    }

    @Test
    public void testZlozZamowienie() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        sleep(500);
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje("1", "0");
        sleep(500);
        GridPane gp_pozycje = find("#gpPozycjeZamowienia");
        clickOn("#btnZlozZamowienie");
        Label label = find("#lbMessage");
        assertEquals(label.getText(),"Zamowienie zostalo zlozone");

    }

    @Test
    public void testZlozZamowienieBezPozycji() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        sleep(500);
        assertTrue(find("#btnZlozZamowienie").isDisabled());
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
        assertTrue(find("#btnZlozZamowienie").isDisabled());
    }

    @Test
    public void testUsunDodanaPozycje() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        clickOn(gp_towary.getChildren().get(6));
        dodajPozycje(Integer.toString(1), Integer.toString(0));
        sleep(500);
        GridPane gp = find("#gpPozycjeZamowienia");
        clickOn(gp.getChildren().get(14));
        sleep(1000);
        assertEquals(gp.getChildren().size(), 7);
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
        assertTrue(!find("#btnZlozZamowienie").isDisabled());
    }

    @Test
    public void testPodzielZamowienie() {
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
        CheckBox chb = (CheckBox) gp.getChildren().get(7);
        chb.setSelected(true);
        sleep(500);
        Button podziel = find("#btnPodzielZamowienie");
        assertEquals("Wygeneruj Zamowienie", podziel.getText());
    }

    @Test
    public void testNieMoznaPodzielicZamowienia() {
        clickOn("#btnWybierz");
        GridPane gp_klienci = find("#gpListaKlientow");
        clickOn(gp_klienci.getChildren().get(4));
        clickOn("#btnDodajTowar");
        GridPane gp_towary = find("#gpListaTowarow");
        clickOn(gp_towary.getChildren().get(6 + (1 * 4)));
        dodajPozycje(Integer.toString(1 + 1), Integer.toString(1));
        assertTrue(find("#btnPodzielZamowienie").isDisabled());

    }

    @Test
    public void testDodajZlaIlosc() {
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
        assertEquals(find("#lbMessage"),ZLA_ILOSC_KOMUNIKAT);
    }

    @Test
    public void testDodajZlyRabat() {
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
        assertEquals(find("#lbMessage"),ZLY_RABAT_KOMUNIKAT);
    }

    @Test
    public void testDodajRabatJakoLitere() {
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
        assertEquals(find("#lbMessage"),ZLY_RABAT_KOMUNIKAT);
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
