import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Asus on 2018-01-04.
 */
public class ZapytanieContext {

	Context context;
	Connection connection;

	public ZapytanieContext(Context context) {
		this.context = context;
	}

	public void przeslijZapytanie(Zapytanie zapytanie) {
		StatusZapytania status = zapytanie.getStatus();
		String terminReal ="0000-00-00";
		if (zapytanie.getTerminRealizacji()!=null) {
			terminReal = zapytanie.getTerminRealizacji().toString();
		}
		String notatka = zapytanie.getNotatka();
		int idZap=0;
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "INSERT INTO zapytania (IdZap,Status,TerminReal,Notatka) VALUES (NULL, \"" + status + "\",\"" + terminReal +"\",\""+ notatka+ "\"" + ")";
				ResultSet rs = stmt.executeQuery(query); 
				stmt.close();
				Statement stmt2 = connection.createStatement();
				String query2 = "SELECT MAX(IdZap) FROM zapytania";
				ResultSet rs2 = stmt2.executeQuery(query2);
				while (rs2.next()) {
					idZap = rs2.getInt("MAX(IdZap)");
					System.out.println(idZap);
				}
				stmt2.close();
				Statement stmt3 = connection.createStatement();
				String query3 = "UPDATE pozycjezamowienia SET ZapytanieIdZap=" + idZap + " ORDER BY IdPZ DESC LIMIT 1";
				ResultSet rs3 = stmt3.executeQuery(query3);
				stmt3.close();
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		}

	public List<Zapytanie> getZapytaniaZBazy() {
		List<Zapytanie> listaZapytan = new ArrayList<Zapytanie>();
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "SELECT IdZap, zapytania.Status, TerminReal, terminRealPoz, Notatka, IdPZ, pozycjezamowienia.Ilosc, pozycjezamowienia.Cena, Rabat, CenaPoRabacie, TowarIdT,ZamowienieIdZ, towary.Cena, towary.Ilosc, towary.NazwaT, towary.Kod, towary.KategoriaIdKat, towary.MaxRabat, zamowienia.Status,zamowienia.DataZlozZam, zamowienia.TerminRealizacji,zamowienia.KwotaZ,zamowienia.CzyZatwierdzone,klienci.IdK,klienci.NazwaFirmy FROM ((((zapytania LEFT JOIN pozycjezamowienia ON Zapytania.IdZap = pozycjezamowienia.ZapytanieIdZap) LEFT JOIN towary ON pozycjezamowienia.TowarIdT=towary.IdT) LEFT JOIN zamowienia ON pozycjezamowienia.ZamowienieIdZ=zamowienia.IdZ) LEFT JOIN klienci ON zamowienia.KlientIdK = klienci.IdK)";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					
					LocalDate terminRealPoz=null;
					if(rs.getDate("terminRealPoz")!=null) {
						terminRealPoz=rs.getDate("terminRealPoz").toLocalDate();
					}
			
					int idPZ=rs.getInt("IdPZ");
					int ilosc=rs.getInt("Ilosc");
					double cena = (double)rs.getFloat("pozycjezamowienia.Cena");
					int rabat =rs.getInt("Rabat");
					double cenaPoRabacie = (double)rs.getFloat("CenaPoRabacie");
					int towarIdT = rs.getInt("TowarIdT");
					int zamowienieIdZ = rs.getInt("ZamowienieIdZ");
					double cenaT = rs.getDouble("towary.Cena");
					int iloscT = rs.getInt("towary.Ilosc");
					String nazwaT = rs.getString("towary.NazwaT");
					int kod = rs.getInt("towary.Kod");
					int idKat=rs.getInt("towary.KategoriaIdKat");
					int maxRabat=rs.getInt("towary.MaxRabat");
					
					
					
					Towar towar = new Towar(towarIdT, nazwaT, kod, idKat, cenaT, maxRabat, iloscT);
					PozycjaZamowienia pz = new PozycjaZamowienia(idPZ, ilosc, cena, rabat, cenaPoRabacie,terminRealPoz,towar);
					
						int idZap = rs.getInt("IdZap");
						StatusZapytania status = StatusZapytania.valueOf(rs.getString("zapytania.Status"));
						LocalDate terminReal=null;
						if(rs.getDate("TerminReal")!=null) {
							terminReal=rs.getDate("TerminReal").toLocalDate();
						}
						String notatka = rs.getString("Notatka");
						Zapytanie zapytanie = new Zapytanie(idZap, status, terminReal, notatka, null);
						StatusZamowienia statusZam=null;
						if (rs.getString("zamowienia.Status")!=null) {
							statusZam= StatusZamowienia.valueOf(rs.getString("zamowienia.Status"));
						}
						
						LocalDate dataZlozZam=null;
						if (rs.getDate("zamowienia.DataZlozZam")!=null) {
							dataZlozZam = rs.getDate("zamowienia.DataZlozZam").toLocalDate();
						}
						LocalDate terminRealizacji=null;
						if(rs.getDate("zamowienia.TerminRealizacji")!=null) {
							terminRealizacji =rs.getDate("zamowienia.TerminRealizacji").toLocalDate();
						}
						double kwotaZ = (double)rs.getFloat("zamowienia.KwotaZ");
						boolean czyZatwierdzone = (rs.getInt("zamowienia.czyZatwierdzone")>0)?true:false;
						
						int klientId = rs.getInt("klienci.IdK");
						String nazwaFirmy = rs.getString("klienci.NazwaFirmy");

						zapytanie.setPozycja(pz);
						pz.setZapytanie(zapytanie);
						listaZapytan.add(zapytanie);
						Zamowienie zamowienie = new Zamowienie(zamowienieIdZ,statusZam,dataZlozZam,terminRealizacji,kwotaZ,czyZatwierdzone);
						Klient klient = new Klient(klientId,nazwaFirmy);
						zamowienie.dodajKlienta(klient);
						pz.setZamowienie(zamowienie);
						PozycjaZamowieniaContext pzc = new PozycjaZamowieniaContext(context);
						pzc.getPozycjeZamowieniaZBazy(zamowienie);
					}
					
				stmt.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();}
		return listaZapytan;
	}
	
	 public Zapytanie zatwierdzZapytanie(Zapytanie zapytanie) {
	        //zapytanie.zmienStatusZapytania(StatusZapytania.zatwierdzone);
	        zapytanie.getPozycja().setTerminRealizacji(zapytanie.getTerminRealizacji());
	        System.out.println(zapytanie.getPozycja().getTerminRealizacji());
	        zapytanie.getPozycja().getZamowienie().aktualizuj();
	        String terminRealZap ="0000-00-00";
			if (zapytanie.getTerminRealizacji()!=null) {
				terminRealZap = zapytanie.getTerminRealizacji().toString();
			}
			
	        try {
				connection = context.getConnection();
				if (connection != null) {
					Statement stmt = connection.createStatement();
					String query = "UPDATE zapytania SET status= \"zatwierdzone\" WHERE IdZap=" + zapytanie.getId();
					ResultSet rs = stmt.executeQuery(query);
					stmt.close();
					Statement stmt2 = connection.createStatement();
					String query2 = "UPDATE pozycjezamowienia SET terminRealPoz=" + "\"" + terminRealZap + "\" WHERE ZapytanieIdZap IN (SELECT IdZap FROM zapytania WHERE IdZap=" + zapytanie.getId()+")";
					ResultSet rs2 = stmt2.executeQuery(query2);
					stmt2.close();
					zapytanie.getPozycja().getZamowienie().aktualizuj();
					String terminRealZam = "0000-00-00";
						if (zapytanie.getPozycja().getZamowienie().getTerminRealizacji()!=null) {
							terminRealZam = zapytanie.getPozycja().getZamowienie().getTerminRealizacji().toString();
						}
						System.out.println(zapytanie.getPozycja().getZamowienie().getTerminRealizacji());
					double kwota = zapytanie.getPozycja().getZamowienie().getSuma();
					Statement stmt3 = connection.createStatement();
					String query3 = "UPDATE zamowienia SET kwotaZ=" + "\"" + kwota + "\", TerminRealizacji=" + "\"" + terminRealZam + "\" WHERE IdZ IN (SELECT ZamowienieIdZ FROM pozycjezamowienia WHERE ZapytanieIdZap IN(SELECT IdZap FROM zapytania WHERE IdZap=" + zapytanie.getId()+"))";
					System.out.println(query3);
					ResultSet rs3 = stmt3.executeQuery(query3);
					stmt3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();}
	        
	        return zapytanie;
	    }

	    public Zapytanie odrzucZapytanie(Zapytanie zapytanie) {
	        //zapytanie.zmienStatusZapytania(StatusZapytania.odrzucone);
	        zapytanie.getPozycja().setTerminRealizacji(null);
	        zapytanie.getPozycja().getZamowienie().aktualizuj();
	        System.out.println("kwota1 " + zapytanie.getPozycja().getZamowienie().getSuma());
	        System.out.println(zapytanie.getPozycja().getZamowienie().getPozycje());
	        zapytanie.getPozycja().getZamowienie().getPozycje().removeIf(e -> (e.getId()==zapytanie.getPozycja().getId()));
	        System.out.println(zapytanie.getPozycja().getZamowienie().getPozycje());
	        zapytanie.getPozycja().getZamowienie().aktualizuj();
	        System.out.println("kwota2 " + zapytanie.getPozycja().getZamowienie().getSuma());
	        int idZ = zapytanie.getPozycja().getZamowienie().getId();
	        double kwota = zapytanie.getPozycja().getZamowienie().getSuma();
	        String termin = "0000-00-00";
	        if (zapytanie.getPozycja().getZamowienie().getTerminRealizacji()!=null) {
	        	termin = zapytanie.getPozycja().getZamowienie().getTerminRealizacji().toString();
			}
	        zapytanie.getPozycja().setZamowienie(null);
	        try {
				connection = context.getConnection();
				if (connection != null) {
					Statement stmt = connection.createStatement();
					String query = "UPDATE zapytania SET status= \"odrzucone\" WHERE IdZap=" + zapytanie.getId();
					ResultSet rs = stmt.executeQuery(query);
					stmt.close();
					Statement stmt2 = connection.createStatement();
					String query2 = "UPDATE pozycjezamowienia SET terminRealPoz=" + "\"" + termin + "\", ZamowienieIdZ=NULL WHERE ZapytanieIdZap IN (SELECT IdZap FROM zapytania WHERE IdZap=" + zapytanie.getId()+")";
					ResultSet rs2 = stmt2.executeQuery(query2);
					stmt2.close();
					Statement stmt3 = connection.createStatement();
					String query3 = "UPDATE zamowienia SET kwotaZ=" + "\"" + kwota + "\", TerminRealizacji="  + termin + " WHERE IdZ=" +idZ;
					System.out.println(query3);
					ResultSet rs3 = stmt3.executeQuery(query3);
					stmt3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();}
	        
	        return zapytanie;
	    }
}
