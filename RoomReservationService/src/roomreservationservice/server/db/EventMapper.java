package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;

public class EventMapper {

	/**
	 * Die Klasse EventMapper wird nur einmal instantiiert (Singleton). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	private static EventMapper eventMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code> neue Instanzen dieser Klasse zu
	 * erzeugen.
	 */
	protected EventMapper() {
	}

	/**
	 * Diese Methode liefert die zu verwendende Instanz des EventMappers zurück.
	 * <p>
	 * Wenn der EventMapper gebraucht wird, muss er über diese Methode <code>EventMapper.eventMapper()</code> aufgerufen
	 * werden. Das erzeugen einer neuen Instanz wäre falsch. Es soll immer nur die hier erstellte benutzt werden. Die
	 * Methode prüft, ob bereits eine Instanz vorhanden ist. Falls ja, gibt sie diese zurück, falls nicht, wird eine
	 * neue erstellt.
	 * 
	 * 
	 * @return Das <code>EventMapper</code>-Objekt.
	 */
	public static EventMapper eventMapper() {
		if (eventMapper == null) {
			eventMapper = new EventMapper();
		}
		return eventMapper;
	}

	/**
	 * Suchen einer Belegung anhand der ID. Zurückgegebe wird genau der eine Einladung, dem die ID zugewiesen ist.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Event-Objekt, das dem übergebenen Schlüssel entspricht, null bei nicht vorhandenem DB-Tupel.
	 */
	public Event findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM events " + "WHERE id=" + id);

			/*
			 * Da die ID der Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis
			 * vorliegt.
			 */
			if (resultSet.next()) {
				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				String topic = resultSet.getString("topic");
				Timestamp startDate = resultSet.getTimestamp("start_date");
				Timestamp endDate = resultSet.getTimestamp("end_date");
				User organizer = getOrganizerOfEvent(resultSet.getInt("event_organizer"));
				Room room = getRoomOfEvent(resultSet.getInt("event_room"));
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int eventID = resultSet.getInt("id");
				Vector<User> invitees = getInviteesOfEvent(eventID);

				/**
				 * ...und anschließend an den Konstruktor für ein neues Invitation-Objekt übergeben. Es wäre zwar auch
				 * möglich mit einem entsprechendem Konstruktor einen leeres Invitation-Objekt zu erstellen und dann
				 * diekt alle nötigen Attribute per Set-Methode zu setzen, allerdings läuft man dann Gefahr, dass man
				 * bei einem Mapper ein Attribut vergisst und halbfertige Objekte erstellt. Daher gibt es hier diesen
				 * Konstruktor, der alle Attribute fordert.
				 */
				Event event = new Event(topic, startDate, endDate, organizer, room, invitees, creationDate, eventID);
				// Zuletzt wird das Event-Objekt zurückgegebn.
				return event;
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
	 * Auslesen aller Events.
	 * 
	 * @return Ein Vektor mit Event-Objekten, die sämtliche Räume beinhaltet.
	 */
	public Vector<Event> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("SELECT * FROM events " + " ORDER BY id");

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// Für jeden Eintrag im Suchergebnis wird nun ein Room-Objekt erstellt.
				while (resultSet.next()) {

					// Für jeden Eintrag wird die findByKey-Methode aufgerufen, die das Event-Obejekt zurückliefert.
					Event event = findByKey(resultSet.getInt("id"));

					// Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(event);
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
	 * Einfügen eines neuen Event-Datensatzes in die DB.
	 * 
	 * @param event
	 *            Event User-Objekt, dass eingefügt werden soll.
	 * 
	 * @return Das aktualisierte Event-Objekt (hat jetzt von der DB eine ID bekommen).
	 */
	public Event insert(Event event) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM events ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * Das User-Objekt erhält den bisher maximalen, nun um 1 erhöhten Primärschlüssel.
				 */
				event.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO events (id, start_date, end_date, topic, event_organizer, event_room, creation_date) VALUES ("
						+ event.getId()
						+ ", '"
						+ event.getStartDate()
						+ "', '"
						+ event.getEndDate()
						+ "', '"
						+ event.getTopic()
						+ "', "
						+ event.getOrganizer().getId()
						+ ", "
						+ event.getRoom().getId()
						+ ", '" + event.getCreationDate() + "')");
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

		return event;
	}

	/**
	 * Update eines Event-Datensatzes in der Datenbank. Das Erstellungsdatum wird hier jedoch nicht aktualisiert, da
	 * dies nach der Erstellung einer Instanz ein fester Wert ist.
	 * 
	 * @param event
	 *            das Event-Objekt, dessen DB-Eintrag aktualisiert werden soll.
	 * @return Das als Parameter übergebene Objekt
	 */
	public Event update(Event event) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE events SET start_date= '" + event.getStartDate() + "', end_date= '"
					+ event.getEndDate() + "', '" + event.getTopic() + "', " + event.getOrganizer().getId() + ", "
					+ event.getRoom().getId() + " WHERE id= " + event.getId());

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

		// Um Analogie zu insert(Event event) zu wahren, geben wir das User-Obejekt wieder zurück.
		return event;
	}

	/**
	 * Löschen des Datensatzes eines Event-Objekts aus der Datenbank.
	 * 
	 * @param event
	 *            Das Event-Objekt, dess DB-Datensatz gelöscht werden soll.
	 */
	public void delete(Event event) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM events WHERE id= " + event.getId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, die in einem Raum stattfinden.
	 * 
	 * @param room
	 *            Der Raum, anhand dessen geprüft werden soll, ob dort etwas stattfindet
	 * @return Vector mit allen Events, die in diesem Raum stattfinden
	 */
	public Vector<Event> findAllByRoom(Room room) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM events WHERE event_room=" + room.getId());

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("id"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, zu denen ein User eingeladen wurde.
	 * 
	 * @param user
	 *            Der User, anhand dessen geprüft werden soll zu welchen Events er eingeladen wurde
	 * @return Vector mit allen Events zu denen ein User eingeladen wurde
	 */
	public Vector<Event> findAllByInvitee(User user) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt
					.executeQuery("SELECT invitation_event FROM invitations WHERE invitation_invitee= " + user.getId());

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("invitation_event"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, in denen ein User als Organisator auftritt.
	 * 
	 * @param user
	 *            Der User, anhand dessen geprüft werden soll, wo dieser als Organisator auftritt
	 * @return Vector mit allen Events in denen ein User als Organisator auftritt
	 */
	public Vector<Event> findAllByOrganizer(User user) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM events WHERE event_organizer= " + user.getId());

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("id"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, zu denen der Suchbegriff für das Belegungsthema passt.
	 * 
	 * @param topic
	 *            Suchgebriff für das Belegungsthema.
	 * @return Vector mit allen Events zu denen ein User eingeladen wurde
	 */
	public Vector<Event> findAllByTopic(String topic) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			/**
			 * Statement ausfüllen und als Query an die DB schicken. LIKE %topic% ist die SQL Syntax um auszudrücken,
			 * dass der Begriff (hier topic) als Wildcard zu verstehen ist und auch ähnliche Strings angezeigt werden
			 * sollen.
			 */
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM events WHERE topic LIKE %" + topic + "%");

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("id"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, die im ausgewählten Zeitraum stattfinden.
	 * 
	 * @param startDate
	 *            Anfangszeitpunkt des ausgewählten Zeitraums
	 * @param endDate
	 *            Endzeitpunkt des ausgewählten Zeitraums.
	 * @return Vector mit allen Events in einem bestimmten Zeitraum
	 */
	public Vector<Event> findAllForPeriodOfTime(Timestamp startDate, Timestamp endDate) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			/**
			 * Statement ausfüllen und als Query an die DB schicken. LIKE %topic% ist die SQL Syntax um auszudrücken,
			 * dass der Begriff (hier topic) als Wildcard zu verstehen ist und auch ähnliche Strings angezeigt werden
			 * sollen.
			 */
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM events WHERE start_date >='" + startDate
					+ "' AND end_date <='" + endDate + "'");

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("id"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, die im ausgewählten Zeitraum stattfinden und zu denen
	 * ein Nutzer einladen ist.
	 * 
	 * @param user
	 *            Der Nutzer, anhand dessen geprüft werden soll, ob er Einladungen für den Zeitraum besitzt
	 * @param startDate
	 *            Anfangszeitpunkt des ausgewählten Zeitraums
	 * @param endDate
	 *            Endzeitpunkt des ausgewählten Zeitraums.
	 * @return Vector mit allen Events in einem bestimmten Zeitraum zu denen ein User eingeladen wurde
	 */
	public Vector<Event> findAllByInviteeForPeriodOfTime(User user, Timestamp startDate, Timestamp endDate) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			/**
			 * Statement ausfüllen und als Query an die DB schicken.
			 */
			ResultSet resultSet = stmt
					.executeQuery("SELECT invitation_event FROM invitations RIGHT JOIN events ON invitations.invitation_event = events.id WHERE events.start_date >= '"
							+ startDate
							+ "' AND events.end_date <= '"
							+ endDate
							+ "' AND invitations.invitation_invitee = " + user.getId());

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("invitation_event"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Funktion die einen Vector mit allen Events zurückliefert, die im ausgewählten Zeitraum und im angebenen Raum
	 * stattfinden.
	 * 
	 * @param room
	 *            Der Raum, anhand dessen geprüft werden soll, ob er Belegung für den Zeitraum besitzt
	 * @param startDate
	 *            Anfangszeitpunkt des ausgewählten Zeitraums
	 * @param endDate
	 *            Endzeitpunkt des ausgewählten Zeitraums
	 * @return Vector mit allen Events in einem bestimmten Zeitraum zu denen ein User eingeladen wurde
	 */
	public Vector<Event> findAllByRoomForPeriodOfTime(Room room, Timestamp startDate, Timestamp endDate) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Event> result = new Vector<Event>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			/**
			 * Statement ausfüllen und als Query an die DB schicken.
			 */
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM events WHERE events.start_date >= '" + startDate
					+ "' AND events.end_date <= '" + endDate + "' AND event_room = " + room.getId());

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {

				// wenn ja, wird die ID ausgelesen und das dazugehörige Objekt mit Hilfe der findByKey-Methode erstellt
				Event event = findByKey(resultSet.getInt("id"));

				// Objekt zum Ergebnisvektor hinzufügen
				result.addElement(event);
				return result;
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
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}

	/**
	 * Hilfsfunktion, um das in der Tabelle per Fremdschlüssel verwiesene User-Objekt abzufragen und dieses zu holen.
	 * 
	 * @param event_organizer
	 *            Fremdschlüssel, der auf den Nutzer verweist.
	 * @return user Ein User-Objekt, dass den Datensatz repräsentiert, auf den verwiesen wurde.
	 */
	private User getOrganizerOfEvent(int event_organizer) {
		UserMapper userMapper = UserMapper.userMapper();
		User user = userMapper.findByKey(event_organizer);
		return user;
	}

	/**
	 * Hilfsfunktion, um das in der Tabelle per Fremdschlüssel verwiesene Room-Objekt abzufragen und dieses zu holen.
	 * 
	 * @param event_organizer
	 *            Fremdschlüssel, der auf den Raum verweist.
	 * @return room Ein Room-Objekt, dass den Datensatz repräsentiert, auf den verwiesen wurde.
	 */
	private Room getRoomOfEvent(int event_room) {
		RoomMapper roomMapper = RoomMapper.roomMapper();
		Room room = roomMapper.findByKey(event_room);
		return room;
	}

	/**
	 * Hilfsfunktion, um das in der Tabelle per Fremdschlüssel verwiesene User-Objekt abzufragen und dieses zu holen.
	 * Hierfür wird abgefragt, welche Nutzer in der Einladung einer Beleung stehen und die zutreffenden werden
	 * anschließend in den Ergebnisvector übernommen.
	 * 
	 * @param eventID
	 *            Die ID der Belegung.
	 * @return invitees Vector, aller User-Objekte, die eine Einladung für diese Belegung erhalten haben.
	 */
	private Vector<User> getInviteesOfEvent(int eventID) {
		// User Mapper holen.
		UserMapper userMapper = UserMapper.userMapper();

		// Ergebnis Vector vorbereiten.
		Vector<User> result = new Vector<User>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt
					.executeQuery("SELECT invitation_invitee FROM invitations WHERE invitation_event= " + eventID);

			while (resultSet.next()) {
				User user = userMapper.findByKey(resultSet.getInt("invitation_invitee"));
				result.addElement(user);
			}

			return result;
		}
		// SQL Exception abfangen, sollte etwas schiefgehen.
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
