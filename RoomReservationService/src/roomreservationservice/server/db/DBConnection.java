package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.google.appengine.api.utils.SystemProperty;


public class DBConnection {
	/**
	 * 
	 * Die Klasse DBConnection wird nur einmal instantiiert (<b>Singleton</b>).
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden und speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	 private static Connection con = null;



	/**
	 * Diese statische Methode kann aufgrufen werden durch 
	 * <code>DBConnection.connection()</code>. Sie stellt die 
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>DBConnection</code> existiert.<p>
	 * 
	 * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code> 
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.<p>
	 * 
	 * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies kann
	 * z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank ausgelöst 
	 * werden - wird keine neue Verbindung aufgebaut, so dass die in einem solchen
	 * Fall die gesamte Software neu zu starten ist. In einer robusten Lösung 
	 * würde man hier die Klasse dahingehend modifizieren, dass bei einer nicht
	 * mehr funktionsfähigen Verbindung stets versucht würde, eine neue Verbindung
	 * aufzubauen. Dies würde allerdings ebenfalls den Rahmen dieses Projekts 
	 * sprengen.
	 * 
	 * @return DAS <code>DBConncetion</code>-Objekt.
	 * @see con
	 */
	public static Connection connection() {
		// Variable in der die URL zur DB gespeichert werden wird.
		String url = null;
		
		// Prüfen, ob es bisher noch kein Objekt gibt, in der die Verbindung zur DB gespeichert sit
		if ( con == null ) {
			try {
				
					/**
					 * Wenn direkt von Google App Enginge darauf zugegriffen wird,
					 * reicht es den URI zur Datenbank mit dem Prefix "jdbc:google:mysql://"
					 * anzugeben. Da die App Enginge App in der Google Entwickler Console Zugriff
					 * auf die DB bekommen hat, muss hier nicht noch das Passwort übergeben werden.
					 */
					if (SystemProperty.environment.value() ==
						SystemProperty.Environment.Value.Production) {
						Class.forName("com.mysql.jdbc.GoogleDriver");
						url = "jdbc:google:mysql://it-project-hdm:roomreservationdb?user=root";
					}
					
					/** Wenn von einem externen Netz (z.B. während der Entwicklungsphase) darauf
					  * zugegriffen werden soll, dann muss das DB-Passwort mit übergeben werden.
					  */
					else {
						Class.forName("com.mysql.jdbc.Driver");
						url = "jdbc:mysql://173.194.250.147:3306?user=root&password=mec-uch-jac-vaj-ne-ghoi-heir-om-";
					}
					
					
				 // Anschließend wir die Verbindung in der Variable <code>con</code> gespeichert.
				 con = DriverManager.getConnection(url);
				
				
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

