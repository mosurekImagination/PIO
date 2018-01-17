import java.sql.*;

/**
 * Klasa laczaca aplikacje z baza danych "hurtownia" znajdujaca sie na serwerze typu MariaDB.
 */
public class Context { 

	private Connection conn = null;

	public Connection getConnection() {
		return conn;
	}

	/**
	 * Metoda otwierajaca polaczenie z baza danych.
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
	 * Metoda zamykajaca polaczenie z baza danych.
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
