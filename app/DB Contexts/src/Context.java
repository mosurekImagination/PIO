import java.sql.*;

/**
 * Created by Asus on 2018-01-04.
 */
public class Context {
	// Bazowa klasa do połączenia się z bazą danych. View, żeby pobrać np.
	// listę Towarów albo sprawdzić klientów połączy się z Contextem
	// i sprawdzi w bazie danych i wyświetli klienta/towary

	private Connection conn = null;

	public Connection getConnection() {
		return conn;
	}

	public void openDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/hurtownia", "root", "");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
	}

}
