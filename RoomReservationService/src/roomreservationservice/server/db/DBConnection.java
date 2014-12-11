package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	/**
	 * 
	 * Die Klasse DBConnection wird nur einmal instantiiert (<b>Singleton</b>). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden und speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	private static Connection con = null;


	public static Connection connection() {


		// Prüfen, ob es bisher noch kein Objekt gibt, in der die Verbindung zur DB gespeichert sit
		if (con == null) {
			try {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://173.194.250.147:3306", "root",
						"mec-uch-jac-vaj-ne-ghoi-heir-om-");
				Statement stmt = con.createStatement();

				// Hier wird schonmal ausgewählt, dass die Datenbank roomreservationservice benutzt werden soll.
				stmt.executeQuery("use roomreservationservice;");

				// Abfangen verschiedener Exceptions, falls etwas schiefgeht.
			} catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		// Rückgabe der Verbindung in der Variable <code>con</code>.
		return con;
	}
}
