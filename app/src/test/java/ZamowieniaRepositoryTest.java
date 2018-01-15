import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;

import static java.time.LocalDate.now;

/**
 * Created by Asus on 2018-01-06.
 */

public class ZamowieniaRepositoryTest extends TestCase{

    ZamowieniaRepository zamRepo;

    public void setUp() throws Exception {
        zamRepo = new ZamowieniaRepository();
        Zamowienie zamowienie = new Zamowienie();
        zamRepo.zamowienie = zamowienie;
    }

    public void testUtworzPozycjeZamowieniaZTowarem() throws Exception {
        Towar towar = new Towar(2,"Wkladki",870,0.85);
        PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);

        assertEquals(0,zamRepo.utworzPozycjeZamowienia(towar).getId());
        assertEquals(pozycja.getTowar(),zamRepo.utworzPozycjeZamowienia(towar).getTowar());
    }

    public void testAktualizujPozycjeZDobraIloscia() throws Exception {
        Towar towar = new Towar(2,"Wkladki",870,0.85);
        PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
        zamRepo.utworzPozycjeZamowienia(towar);
        Double kwotaPozycji = 340*0.85;
        Double kwotaPozycjiPoRabacie = kwotaPozycji * 0.95;
        Double kwotaZamowienia = zamRepo.zamowienie.getSuma() + kwotaPozycjiPoRabacie;

        zamRepo.aktualizujPozycje(340,5);
        PozycjaZamowienia dodanaPozycja = zamRepo.zamowienie.getPozycjaOnIndex(0);

        assertEquals(kwotaPozycji,dodanaPozycja.getCena());
        assertEquals(kwotaPozycjiPoRabacie,dodanaPozycja.getCenaPoRabacie());
        assertEquals(now(),dodanaPozycja.getTerminRealizacji());
        assertEquals(kwotaZamowienia,zamRepo.zamowienie.getSuma());
    }

    public void testAktualizujPozycjeZZlaIloscia() throws Exception {
        Towar towar = new Towar(2,"Wkladki",870,0.85);
        PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
        zamRepo.utworzPozycjeZamowienia(towar);
        Double kwotaPozycji = 1000*0.85;
        Double kwotaPozycjiPoRabacie = kwotaPozycji * 0.95;
        Double kwotaZamowienia = zamRepo.zamowienie.getSuma() + kwotaPozycjiPoRabacie;

        zamRepo.aktualizujPozycje(1000,5);
        PozycjaZamowienia dodanaPozycja = zamRepo.zamowienie.getPozycjaOnIndex(0);

        assertEquals(kwotaPozycji,dodanaPozycja.getCena());
        assertEquals(kwotaPozycjiPoRabacie,dodanaPozycja.getCenaPoRabacie());
        assertEquals(null,dodanaPozycja.getTerminRealizacji());
        assertEquals(kwotaZamowienia,zamRepo.zamowienie.getSuma());
    }

    public void testsprawdzZeTowarDostepny() throws Exception {
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.25),25,5));
        assertEquals(true,zamRepo.sprawdzDostepnoscTowaru(0,25));
    }

    public void testsprawdzZeTowarNiedostepny() throws Exception {
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.25),25,5));
        assertEquals(false,zamRepo.sprawdzDostepnoscTowaru(0,38));
    }

    public void testusunPozycje() throws Exception {
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.25),25,5,LocalDate.of(2018,1,19)));
        LocalDate dataPrzedUsunieciem = zamRepo.zamowienie.getTerminRealizacji();
        Double kwotaPrzedUsunieciem = zamRepo.zamowienie.getSuma();

        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(1,new Towar(0,"Wkrety",85,1),47,2));
        zamRepo.zamowienie.usunPozycje(1);

        assertEquals(1,zamRepo.zamowienie.getSize());
        assertEquals(dataPrzedUsunieciem,zamRepo.zamowienie.getTerminRealizacji());
        assertEquals(kwotaPrzedUsunieciem,zamRepo.zamowienie.getSuma());
    }

}