import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 	Klasa umo¿liwiaj¹ca wymianê danych z relacj¹ "zamowienia" w bazie danych "hurtownia".
 */
public class ZamowienieContext {
	private Context context;
	private Connection connection;

	public ZamowienieContext(Context context) {
		this.context = context;
	}
	/**
	 * 	Metoda przesy³aj¹ca do bazy dane zamówienia.
	 */
	public void przeslijZamowienie(Zamowienie zamowienie) {
		String status = zamowienie.getStatus().toString();
		String dataZlozZam = "0000-00-00";
		if (zamowienie.getDataZlozZam()!=null) {
			dataZlozZam = zamowienie.getDataZlozZam().toString();
		}
		String terminRealizacji = "0000-00-00";
		if (zamowienie.getTerminRealizacji()!=null) {
			terminRealizacji = zamowienie.getTerminRealizacji().toString();
		}
		float kwota = (float)zamowienie.getSuma();
		boolean czyZatwierdzone = zamowienie.getCzyZatwierdzone();
		int idK = zamowienie.getKlient().getId(); 
		try {
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "INSERT INTO zamowienia (IdZ,Status,DataZlozZam,TerminRealizacji,KwotaZ,CzyZatwierdzone,KlientIdK) VALUES (NULL, \"" + status + "\",\"" + dataZlozZam +"\",\""+ terminRealizacji + "\"," + kwota + "," + czyZatwierdzone+"," +idK +")";
				ResultSet rs = stmt.executeQuery(query); 
				stmt.close();
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PozycjaZamowieniaContext pozycjaZamowieniaContext = new  PozycjaZamowieniaContext(context);
		pozycjaZamowieniaContext.przeslijPozycjeZamowienia(zamowienie);
		
	}
}
