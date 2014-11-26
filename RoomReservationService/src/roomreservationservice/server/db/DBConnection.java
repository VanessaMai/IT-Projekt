package roomreservationservice.server.db;

import java.sql.*;
import com.google.appengine.api.rdbms.AppEngineDriver;


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
	 * URL der Datenbank.
	 * TODO : Eigene Datenbank anlegen.
	 * URL Schema: "jdbc:google:rdbms://prof-thies.de:thies-bankproject:thies-bankproject/bankproject?user=demo&password=demo"
	 */
	private static String url = "";
	
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
		// Prüfen, ob es bisher noch keine Verbindung zur DB gab.
		if ( con == null ) {
			try {
				// Laden des DB-Treibers
				DriverManager.registerDriver(new AppEngineDriver());

				
				 // Aufbau der Verbindung mit der DB und Speicherung in der Variabel <code>con</code>.
				con = DriverManager.getConnection(url);
			} 
			// SQL-Exception abfangen, falls eine geworfen wird.
			catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}
		}
		
		// Rückgabe der Verbindung
		return con;
	}
}
