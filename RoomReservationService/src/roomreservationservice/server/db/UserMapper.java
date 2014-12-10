package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import roomreservationservice.shared.bo.User;

public class UserMapper {
	
	/**
	 * Die Klasse UserMapper wird nur einmal instantiiert (Singleton). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	private static UserMapper userMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code> neue Instanzen dieser Klasse zu
	 * erzeugen.
	 */
	protected UserMapper() {
	}

	/**
	 * Diese Methode liefert die zu verwendende Instanz des UserMapper zurück.
	 * <p>
	 * Wenn der UserMapper gebraucht wird, muss er über diese Methode <code>UserMapper.eventMapper()</code> aufgerufen
	 * werden. Das erzeugen einer neuen Instanz wäre falsch. Es soll immer nur die hier erstellte benutzt werden. Die
	 * Methode prüft, ob bereits eine Instanz vorhanden ist. Falls ja, gibt sie diese zurück, falls nicht, wird eine
	 * neue erstellt.
	 * 
	 * 
	 * @return Das <code>UserMapper</code>-Objekt.
	 */
	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}
	
	
	
	
	/**
	 * Suchen eines Nutzers anhand der ID. Zurückgegebe wird genau der eine Einladung, dem die ID zugewiesen ist.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return User-Objekt, das dem übergebenen Schlüssel entspricht, null bei nicht vorhandenem DB-Tupel.
	 */
	public User findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM users " + "WHERE id=" + id);

			/*
			 * Da ID der Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (resultSet.next()) {
				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String accessToken = resultSet.getString("access_token");
				String accessTokenSecret = resultSet.getString("access_token_secret");
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int userID = resultSet.getInt("id");

				/**
				 * ...und anschließend an den Konstruktor für ein neues User-Objekt übergeben. Es wäre zwar auch möglich
				 * mit einem entsprechendem Konstruktor einen leeres Invitation-Objekt zu erstellen und dann diekt alle
				 * nötigen Attribute per Set-Methode zu setzen, allerdings läuft man dann Gefahr, dass man bei einem
				 * Mapper ein Attribut vergisst und halbfertige Objekte erstellt. Daher gibt es hier diesen Konstruktor,
				 * der alle Attribute fordert.
				 */
				User user = new User(firstName, lastName, email, accessToken, accessTokenSecret, creationDate, userID);

				// Zuletzt wird das User-Objekt zurückgegebn.
				return user;
			}
		}
		// SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}
}
