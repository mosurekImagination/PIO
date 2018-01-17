import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * 	Klasa umozliwiajaca wymiane danych z relacja "pozycjezamowienia" w bazie danych "hurtownia".
 */
public class PozycjaZamowieniaContext {

	private Context context;
	private Connection connection;

	public PozycjaZamowieniaContext(Context context) {
		this.context = context;
	}
	/**
	 * 	Metoda przesylajaca do bazy dane pozycji zamowienia.
	 */
	public void przeslijPozycjeZamowienia(Zamowienie zamowienie) {
		List<PozycjaZamowienia> pozycjeZamowienia = zamowienie.getPozycje();
		for (PozycjaZamowienia pozycjaZamowienia : pozycjeZamowienia) {
			Zapytanie zapytanie = null;
			int idZ = 0;
			if (pozycjaZamowienia.getZapytanie() != null) {
				zapytanie = pozycjaZamowienia.getZapytanie();
				idZ = pozycjaZamowienia.getZapytanie().getId();
			}
			int idT = pozycjaZamowienia.getTowar().getId();
			int ilosc = pozycjaZamowienia.getIlosc();
			float cena = (float)pozycjaZamowienia.getCena();
			int rabat = pozycjaZamowienia.getRabat();
			float cenaPoRabacie = (float)pozycjaZamowienia.getCenaPoRabacie();
			String terminRealPoz = "0000-00-00";
			if (pozycjaZamowienia.getTerminRealizacji() != null) {
				terminRealPoz = pozycjaZamowienia.getTerminRealizacji().toString();
			}
			int idZam = 0;
			try {
				connection = context.getConnection();
				if (connection != null) {
					Statement stmt = connection.createStatement();
					String query = "SELECT MAX(IdZ) FROM zamowienia";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						idZam = rs.getInt("MAX(IdZ)");
					}
					stmt.close();
					Statement stmt2 = connection.createStatement();
					String query2 = "INSERT INTO pozycjezamowienia (IdPZ,Ilosc,Cena,Rabat,CenaPoRabacie,ZapytanieIdZap,TowarIdT,ZamowienieIdZ,terminRealPoz) VALUES (NULL, "
							+ ilosc + "," + cena + "," + rabat + "," + cenaPoRabacie + ", NULL," + idT + "," + idZam
							+ "," + "\"" + terminRealPoz + "\")";
					ResultSet rs2 = stmt2.executeQuery(query2);
					stmt2.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (pozycjaZamowienia.getZapytanie() != null) {
				ZapytanieContext zc = new ZapytanieContext(context);
				zc.przeslijZapytanie(zapytanie);
			} else {
				TowaryContext tc = new TowaryContext(context);
				tc.aktualizujStan(pozycjaZamowienia);
			}
		}

	}
	/**
	 * Metoda pobierajaca z bazy dane pozycji zamowienia.
	 */
	public List<PozycjaZamowienia> getPozycjeZamowieniaZBazy(Zamowienie zamowienie) {
		List<PozycjaZamowienia> pozycjeZamowienia = new ArrayList<PozycjaZamowienia>();
		int idZam = zamowienie.getId();
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "SELECT IdPZ, pozycjezamowienia.Ilosc,pozycjezamowienia.Cena, Rabat, CenaPoRabacie, terminRealPoz, ZapytanieIdZap, Status, TerminReal, Notatka, IdT, NazwaT, Kod, KategoriaIdKat, towary.Cena,towary.MaxRabat,towary.Ilosc FROM ((zapytania RIGHT JOIN pozycjezamowienia ON zapytania.IdZap=pozycjezamowienia.ZapytanieIdZap)JOIN towary ON pozycjezamowienia.TowarIdT=towary.IdT) WHERE ZamowienieIdZ="
						+ idZam;
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					int idPZ = rs.getInt("IdPZ");
					int ilosc = rs.getInt("pozycjezamowienia.Ilosc");
					double cena = (double)rs.getFloat("pozycjezamowienia.Cena");
					int rabat = rs.getInt("Rabat");
					double cenaPoRabacie =(double)rs.getFloat("CenaPoRabacie");
					LocalDate terminRealPoz = null;
					if (rs.getDate("terminRealPoz")!=null) {
						terminRealPoz = rs.getDate("terminRealPoz").toLocalDate();
					}
					int idT = rs.getInt("IdT");
					String nazwaT = rs.getString("NazwaT");
					int kod = rs.getInt("Kod");
					int katId = rs.getInt("KategoriaIdKat");
					double cenaT = rs.getDouble("towary.Cena");
					int maxRab = rs.getInt("towary.MaxRabat");
					int iloscT = rs.getInt("towary.Ilosc");

					int idZap = 0;

					PozycjaZamowienia pz = new PozycjaZamowienia(idPZ, ilosc, cena, rabat, cenaPoRabacie, terminRealPoz,
							new Towar(idT, nazwaT, kod, katId, cenaT, maxRab, iloscT));

					if (rs.getInt("ZapytanieIdZap") != 0) {
						idZap = rs.getInt("ZapytanieIdZap");
						StatusZapytania status = StatusZapytania.valueOf(rs.getString("Status"));
						LocalDate terminReal = null;
						if (rs.getDate("TerminReal")!=null) {
							terminReal = rs.getDate("TerminReal").toLocalDate();
						}
						String notatka = rs.getString("Notatka");

						Zapytanie zapytanie = new Zapytanie(idZap, status, terminReal, notatka, null);
						zapytanie.setPozycja(pz);
						pz.setZapytanie(zapytanie);
					}
					zamowienie.dodajPozycje(pz);
					pz.setZamowienie(zamowienie);
					pozycjeZamowienia.add(pz);
				}
				stmt.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pozycjeZamowienia;
	}
}
