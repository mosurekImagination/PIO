import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2018-01-04.
 */
public class KlienciContext {
	
	Context context;
	Connection connection;
	public KlienciContext() {
		
	}
	public KlienciContext(Context context) {
		this.context = context;
		
	}

	
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
