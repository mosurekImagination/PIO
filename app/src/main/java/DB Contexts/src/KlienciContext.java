import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 	Klasa umożliwiająca wymianę danych z relacją "klienci" w bazie danych "hurtownia".
 */
public class KlienciContext {
	
	private Context context;
	private Connection connection;
	public KlienciContext() {
		
	}
	public KlienciContext(Context context) {
		this.context = context;
		
	}

	/**
	 * Metoda pobierająca z bazy dane klientów hurtowni.
	 */
    public ArrayList<Klient> getKlientZBazy(){
    	ArrayList<Klient> klienci = new ArrayList<>();
		try {
			
			connection = context.getConnection();
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String query = "SELECT IdK,ImieK,NazwiskoK,Nip,NazwaFirmy FROM klienci";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					int idK = rs.getInt("IdK");
					String ImieK = rs.getString("ImieK");
					String NazwiskoK = rs.getString("NazwiskoK");
					int Nip = rs.getInt("Nip");
					String NazwaFirmy = rs.getString("NazwaFirmy");
					klienci.add(new Klient(idK,ImieK,NazwiskoK,Nip,NazwaFirmy));
				}
				stmt.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return klienci;
    }
}
