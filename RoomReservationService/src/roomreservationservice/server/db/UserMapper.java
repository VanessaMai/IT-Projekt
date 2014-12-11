package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

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
			 * Da die ID der Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis
			 * vorliegt.
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
			// wenn das Resultset leer ist, wird <code>null</code> zurückgegeben.
			else {
				return null;
			}
		}
		// SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

	}

	/**
	 * Auslesen aller Nutzer.
	 * 
	 * @return Ein Vektor mit User-Objekten, die sämtliche Räume beinhaltet.
	 */
	public Vector<User> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<User> result = new Vector<User>();

		try {
			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("SELECT * FROM users " + " ORDER BY id");

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {
				// Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt erstellt.
				while (resultSet.next()) {

					// Für jeden Eintrag wird die findByKey-Methode aufgerufen, die das User-Obejekt zurückliefert.
					User user = findByKey(resultSet.getInt("id"));

					// Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(user);
				}
			}
			// wenn das Resultset leer ist, wird <code>null</code> zurückgegeben.
			else {
				return null;
			}
		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines neuen User-Datensatzes in die DB.
	 * 
	 * @param user
	 *            Das User-Objekt, dass eingefügt werden soll.
	 * 
	 * @return Das aktualisierte User-Objekt (hat jetzt von der DB eine ID bekommen).
	 */
	public User insert(User user) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM users ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * Das User-Objekt erhält den bisher maximalen, nun um 1 erhöhten Primärschlüssel.
				 */
				user.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO users (id, first_name, last_name, email, access_token, access_token_secret, creation_date) "
						+ "VALUES ("
						+ user.getId()
						+ ", '"
						+ user.getFirstName()
						+ "', '"
						+ user.getLastName()
						+ "', '"
						+ user.getEmail()
						+ "', '"
						+ user.getAccessToken()
						+ "', '"
						+ user.getAccessTokenSecret() + "', '" + user.getCreationDate() + "')");
			}
		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		/*
		 * Rückgabe, des nun veränderten Raum Objekts. Es hat von der DB eine ID zugewiesen bekommen, die sie
		 * fortanverwendet, falls man den Datenastz zum Beispiel aus der DB löschen oder ihn updaten möchte.
		 */

		return user;
	}

	/**
	 * Update eines Room-Datensatzes in der Datenbank. Das Erstellungsdatum wird hier jedoch nicht aktualisiert, da dies
	 * nach der Erstellung einer Instanz ein fester Wert ist.
	 * 
	 * @param user
	 *            das User-Objekt, dessen DB-Eintrag aktualisiert werden soll.
	 * @return Das als Parameter übergebene Objekt
	 */
	public User update(User user) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE users SET first_name= '" + user.getFirstName() + "', last_name= '"
					+ user.getLastName() + "', email= '" + user.getEmail() + "', access_token= '"
					+ user.getAccessToken() + "', access_token_secret= '" + user.getAccessTokenSecret()
					+ "' WHERE id= " + user.getId());

		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		// Wenn kein Eintrag vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

		// Um Analogie zu insert(User user) zu wahren, geben wir das User-Obejekt wieder zurück.
		return user;
	}

	/**
	 * Löschen des Datensatzes eines User-Objekts aus der Datenbank.
	 * 
	 * @param user
	 *            Das User-Objekt, dess DB-Datensatz gelöscht werden soll.
	 */
	public void delete(User user) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM users WHERE id= " + user.getId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Alle User finden, die einen bestimmen Nachnamen tragen.
	 * 
	 * @param name
	 *            Der gesuchte Nachname
	 * @return Vector mit allen Usern, die diesen Nachnamen tragen.
	 */
	public Vector<User> findAllByName(String name) {
		// DB Connection vorbereiten
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<User> result = new Vector<User>();

		try {
			// Statement vorbereiten.
			Statement stmt = con.createStatement();

			// Query durchführen und nach Einträgen suchen, bei denen der Namename dem Suchebgriff entspricht
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM users WHERE last_name = '" + name + "'");

			if (resultSet.next()) {
				User user = userMapper.findByKey(resultSet.getInt("id"));
				result.addElement(user);
			}

			return result;

		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		// Wenn kein Eintrag vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

}
