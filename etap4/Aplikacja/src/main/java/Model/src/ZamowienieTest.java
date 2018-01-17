import junit.framework.TestCase;

import java.time.LocalDate;

/**
 * Created by Asus on 2018-01-06.
 */
public class ZamowienieTest extends TestCase {

    Zamowienie zamowienie;

    public void setUp() throws Exception {
        zamowienie = new Zamowienie();
    }

    public void testdodajPozycje() throws Exception {
        zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,1.3),25,5));
        assertEquals(1,zamowienie.getSize());
    }

    public void testusunPozycje() throws Exception {
        zamowienie.dodajPozycje(new PozycjaZamowienia(0,new Towar(0,"Srubki",30,0.5),25,5));
        zamowienie.usunPozycje(0);
        assertEquals(0,zamowienie.getSize());
    }

    public void testgetNextId() throws Exception {
        int nextId = zamowienie.getNextId();
        assertEquals(0,nextId);

    }

    public void testdodajKlienta() throws Exception {
        zamowienie.dodajKlienta(new Klient());
        assertNotNull(zamowienie.klient);
    }

    public void testUstalWczesniejszyTerminrealizacji() throws Exception {
        LocalDate date = LocalDate.of(2019,1,6);
        LocalDate date2 = LocalDate.of(2018,6,12);
        zamowienie.ustalTerminRealizacji(date);
        zamowienie.ustalTerminRealizacji(date2);
        assertEquals(date,zamowienie.getTerminRealizacji());

    }

    public void testUstalPozniejszyTerminrealizacji() throws Exception {
        LocalDate date = LocalDate.of(2019,8,16);
        zamowienie.ustalTerminRealizacji(date);
        assertEquals(date,zamowienie.getTerminRealizacji());

    }


}