import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 	Klasa umozliwiajaca wymiane danych z relacja "towary" w bazie danych "hurtownia".
 */
public class TowaryContext {

	private Context context;
	private Connection connection;

	public TowaryContext(Context context) {
		this.context = context;
	}

	/**
	 * Metoda pobierajaca z bazy dane towarow.
	 */
	public List<Towar> getTowaryZBazy() {
		List<Towar> listaTowarow = new ArrayList<Towar>();
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "SELECT IdT,NazwaT,Kod,KategoriaIdKat,Cena,MaxRabat,Ilosc FROM towary";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					int idT = rs.getInt("IdT");
					String nazwaT = rs.getString("NazwaT");
					int kod = rs.getInt("Kod");
					int kategoriaIdKat = rs.getInt("KategoriaIdKat");
					double cena = rs.getDouble("Cena");
					int maxRabat = rs.getInt("MaxRabat");
					int Ilosc = rs.getInt("Ilosc");
					listaTowarow.add(new Towar(idT, nazwaT, kod, kategoriaIdKat, cena, maxRabat, Ilosc));
				}
				stmt.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTowarow;
	}
	

	/**
	 * Metoda aktualizujaca w bazie danych ilosc towarow.
	 */
	public void aktualizujStan(PozycjaZamowienia pozycjaZamowienia) {
		int idT=pozycjaZamowienia.getTowar().getId();
		int iloscWPoz = pozycjaZamowienia.getIlosc();
		int ilosc=0;
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "SELECT ilosc FROM towary WHERE idT=" + idT; 
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					ilosc = rs.getInt("ilosc");
				}
				stmt.close();
				int nowaIlosc = ilosc - iloscWPoz;
				Statement stmt2 = connection.createStatement();
				String query2 = "UPDATE towary SET ilosc=" + nowaIlosc + " WHERE idT=" + idT; 
				ResultSet rs2 = stmt2.executeQuery(query2);
				stmt2.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
