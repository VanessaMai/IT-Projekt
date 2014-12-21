package roomreservationservice.server.db;

import java.sql.*;

import java.util.Vector;
import roomreservationservice.shared.bo.Room;

/**
 * Mapper-Klasse, die <code>Room</code>-Objekte auf eine relationale Datenbank abbildet. Hierfür gibt es veschiedene
 * Methoden zum Auslesen, Ändern und Löschen aus der Datenbank.
 * 
 */

public class RoomMapper {

	/**
	 * Die Klasse RoomMapper wird nur einmal instantiiert (Singleton). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	private static RoomMapper roomMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code> neue Instanzen dieser Klasse zu
	 * erzeugen.
	 */
	protected RoomMapper() {
	}

	/**
	 * Diese Methode liefert die zu verwendende Instanz des RoomMappers zurück.
	 * <p>
	 * Wenn der RoomMapper gebraucht wird, muss er über diese Methode <code>RoomMapper.roomMapper</code> aufgerufen
	 * werden. Das erzeugen einer neuen Instanz wäre falsch. Es soll immer nur die hier erstellte benutzt werden. Die
	 * Methode prüft, ob bereits eine Instanz vorhanden ist. Falls ja, gibt sie diese zurück, falls nicht, wird eine
	 * neue erstellt.
	 * 
	 * 
	 * @return Das <code>RoomMapper</code>-Objekt.
	 */
	public static RoomMapper roomMapper() {
		if (roomMapper == null) {
			roomMapper = new RoomMapper();
		}
		return roomMapper;
	}

	/**
	 * Suchen eines Raums anhand seiner ID. Zurückgegebe wird genau der eine Raum, dem die ID zugewiesen ist.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Raum-Objekt, das dem übergebenen Schlüssel entspricht, null bei nicht vorhandenem DB-Tupel.
	 */
	public Room findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM rooms " + "WHERE id=" + id);

			/*
			 * Da die ID der Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis
			 * vorliegt.
			 */
			if (resultSet.next()) {
				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				String roomName = resultSet.getString("name");
				int roomCapacity = resultSet.getInt("capacity");
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int roomID = resultSet.getInt("id");

				/**
				 * ...und anschließend an den Konstruktor für ein neues Room-Objekt übergeben.
				 */
				Room room = new Room(roomName, roomCapacity);
				
				/*
				 * Setzen der ID und des Erstellungszeitpunktes aus der DB.
				 */
				room.setId(roomID);
				room.setCreationDate(creationDate);

				// Zuletzt wird das Room-Objekt zurückgegebn.
				return room;
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
	 * Auslesen aller Räume.
	 * 
	 * @return Ein Vektor mit Room-Objekten, die sämtliche Räume beinhaltet.
	 */
	public Vector<Room> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Room> result = new Vector<Room>();
		
		try {
			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("SELECT * FROM rooms " + " ORDER BY id");

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {
				// Für jeden Eintrag im Suchergebnis wird nun ein Room-Objekt erstellt.
				do {

					// Für jeden Eintrag wird die findByKey-Methode aufgerufen, die das Room-Objekt zurückliefert.
					Room room = findByKey(resultSet.getInt("id"));

					// Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(room);
				} while (resultSet.next());

				// Rückgabe Ergebnisvektor
				return result;
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
	}

	/**
	 * Einfügen eines neuen Room-Datensatzes in die DB.
	 * 
	 * @param room
	 *            Das Room-Objekt, dass eingefügt werden soll.
	 * 
	 * @return Das aktualisierte Room-Objekt (hat jetzt von der DB eine ID bekommen).
	 */
	public Room insert(Room room) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM rooms ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * Das Room-Objekt erhält den bisher maximalen, nun um 1 erhöhten Primärschlüssel.
				 */
				room.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO rooms (id, name, capacity, creation_date) " + "VALUES (" + room.getId()
						+ ",'" + room.getRoomName() + "'," + room.getRoomCapacity() + ", '" + room.getCreationDate()
						+ "')");
				/*
				 * Rückgabe, des nun veränderten Raum Objekts. Es hat von der DB eine ID zugewiesen bekommen, die sie
				 * fortanverwendet, falls man den Datenastz zum Beispiel aus der DB löschen oder ihn updaten möchte.
				 */

				return room;
			}
			// wenn das Resultset leer ist, wird <code>null</code> zurückgegeben.
			else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Update eines Room-Datensatzes in der Datenbank. Das Erstellungsdatum wird hier jedoch nicht aktualisiert, da dies
	 * nach der Erstellung einer Instanz ein fester Wert ist.
	 * 
	 * @param room
	 *            das Room-Objekt, dessen DB-Eintrag aktualisiert werden soll.
	 * @return das als Parameter übergebene Objekt
	 */
	public Room update(Room room) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE rooms SET name= '" + room.getRoomName() + "', capacity= "
					+ room.getRoomCapacity() + " WHERE id= " + room.getId());
			
			// Um Analogie zu insert(Room room) zu wahren, geben wir das Room-Objekt wieder zurück.
			return room;

		} // SQL Exception abfangen, sollte etwas schiefgehen.
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
	 * Löschen des Datensatzes eines Room-Objekts aus der Datenbank.
	 * 
	 * @param room
	 *            Das Room-Objekt, dess DB-Datensatz gelöscht werden soll.
	 */
	public void delete(Room room) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM rooms " + "WHERE id=" + room.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Falls auf etwas verwiesen wird, das nicht vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}

	}

}
