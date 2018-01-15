import junit.framework.TestCase;
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

    public void testUtworzPozycjeZamowienia() throws Exception {
        Towar towar = new Towar(2,"Wkladki",870,0.85);
        PozycjaZamowienia pozycja = new PozycjaZamowienia(towar);
        assertEquals(0,zamRepo.utworzPozycjeZamowienia(towar).getId());
        assertEquals(pozycja.getTowar(),zamRepo.utworzPozycjeZamowienia(towar).getTowar());
    }

    public void testsprawdzDostepnoscTowaru() throws Exception {
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.25),25,5));
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(1,new Towar(0,"Wkrety",85,1),47,2));
        assertEquals(true,zamRepo.sprawdzDostepnoscTowaru(0,25));
        assertEquals(false,zamRepo.sprawdzDostepnoscTowaru(0,38));
    }

    public void testusunPozycje() throws Exception {
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.25),25,5));
        zamRepo.zamowienie.dodajPozycje(new PozycjaZamowienia(1,new Towar(0,"Wkrety",85,1),47,2));
        zamRepo.zamowienie.usunPozycje(1);
        assertEquals(1,zamRepo.zamowienie.getSize());
    }

}