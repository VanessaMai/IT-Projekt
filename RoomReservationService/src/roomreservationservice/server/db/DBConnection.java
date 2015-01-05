package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ThisExpression;

import roomreservationservice.server.ServersideSettings;
import sun.util.logging.resources.logging;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Klasse, mit der die Verbindung zur Datenbank hergestellt wird.
 * 
 * @author Julius Renner
 */

public class DBConnection {
	/**
	 * 
	 * Die Klasse DBConnection wird nur einmal instantiiert (<b>Singleton</b>). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden und speichert die
	 * einzige Instanz dieser Klasse.
	 */
	private static Connection con = null;	
	

	public static Connection connection() {
		
		/**
		 * Serverseitigen Loggerinstanz holen.
		 */
		Logger logger = ServersideSettings.getLogger();
		
		// Prüfen, ob es bisher noch kein Objekt gibt, in der die Verbindung zur DB gespeichert sit
		if (con == null) {
			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
					/*
					 * Wenn die Applikation bereits auf App Engine deployt wurde, kann direkt darauf zugegriffen werden,
					 * ohne angabe eines Passworts, denn die Zugriffsrechts wurden bereits in der Konsole eingeräumt
					 */
					Class.forName("com.mysql.jdbc.GoogleDriver");
					con = DriverManager.getConnection("jdbc:google:mysql://it-project-hdm:roomreservationdb?user=root");
					logger.info("Verbindung mit Cloud SQL DB hergestellt.");
				} else {
					/*
					 * <p> Wenn lokal entwickelt werden soll, hier die Daten für die lokale DB eintragen. Falls von einem
					 * anderen Netzwerk aus auf die Cloud SQL-DB zugegriffen werden soll, passiert das mit diesen Daten: </p>
					 * 
					 * <code>con = DriverManager.getConnection("jdbc:mysql://173.194.250.147:3306", "root",
					 * "mec-uch-jac-vaj-ne-ghoi-heir-om-");</code>
					 * 
					 * <p> Hinweis: Damit das klappt, muss die aktuelle
					 * IP-Adresse des Computers, von den man das machen möchte, in der Konsole auf die Whitelist gesetzt
					 * werden. Dieser Eintrag ist dann jeweils nur 24h gültig. Wenn das benötigt wird, bitte an Julius
					 * Renner wenden.</p>
					 */
					Class.forName("com.mysql.jdbc.Driver");
					// Daten für lokale DB
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
					logger.info("Verbindung mit non Cloud SQL DB hergestellt.");

				}

				// Leeres Statement vorbereiten
				Statement stmt = con.createStatement();
				// Hier wird ausgewählt, dass die Datenbank roomreservationservice benutzt werden soll.
				stmt.executeQuery("use roomreservationservice;");

				// Abfangen verschiedener Exceptions, falls etwas schiefgeht.
			} catch (SQLException e) {
			    logger.severe("Fehler bei DB-Query: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				logger.severe("Fehler: " + e.getMessage());
		}
			
		// Rückgabe der Verbindung in der Variable <code>con</code>.
		return con;
	}
		return con;
		}
	}