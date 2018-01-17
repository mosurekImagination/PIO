import java.sql.*;

/**
 * Klasa łącząca aplikację z bazą danych "hurtownia" znajdującą się na serwerze typu MariaDB.
 */
public class Context { 

	private Connection conn = null;

	public Connection getConnection() {
		return conn;
	}

	/**
	 * Metoda otwierająca połączenie z bazą danych.
	 */
	public void openDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/hurtownia", "root", null);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda zamykająca połączenie z bazą danych.
	 */
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
