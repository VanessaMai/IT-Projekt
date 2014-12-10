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
		}
		// SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
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
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}
}
