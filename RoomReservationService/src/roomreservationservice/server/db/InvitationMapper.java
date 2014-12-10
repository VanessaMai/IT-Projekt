package roomreservationservice.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.Invitation;
import roomreservationservice.shared.bo.User;

public class InvitationMapper {
	/**
	 * Die Klasse InvitationMapper wird nur einmal instantiiert (Singleton). Diese Variable ist durch den Bezeichner
	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 */
	private static InvitationMapper invitationMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code> neue Instanzen dieser Klasse zu
	 * erzeugen.
	 */
	protected InvitationMapper() {
	}

	/**
	 * Diese Methode liefert die zu verwendende Instanz des InvitationMappers zurück.
	 * <p>
	 * Wenn der InvitationMapper gebraucht wird, muss er über diese Methode
	 * <code>InvitationMapper.invitationMapper()</code> aufgerufen werden. Das erzeugen einer neuen Instanz wäre falsch.
	 * Es soll immer nur die hier erstellte benutzt werden. Die Methode prüft, ob bereits eine Instanz vorhanden ist.
	 * Falls ja, gibt sie diese zurück, falls nicht, wird eine neue erstellt.
	 * 
	 * 
	 * @return Das <code>InvitationMapper</code>-Objekt.
	 */
	public static InvitationMapper invitationMapper() {
		if (invitationMapper == null) {
			invitationMapper = new InvitationMapper();
		}
		return invitationMapper;
	}

	/**
	 * Suchen einer Einladung anhand der ID. Zurückgegebe wird genau der eine Einladung, dem die ID zugewiesen ist.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Invitation-Objekt, das dem übergebenen Schlüssel entspricht, null bei nicht vorhandenem DB-Tupel.
	 */
	public Invitation findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM invitations " + "WHERE id=" + id);

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (resultSet.next()) {
				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				Event event = getEventOfInvitation(resultSet.getInt("invitation_event"));
				User invitee = getUserOfInvitation(resultSet.getInt("invitation_invitee"));
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int invitationID = resultSet.getInt("id");

				/**
				 * ...und anschließend an den Konstruktor für ein neues Invitation-Objekt übergeben. Es wäre zwar auch
				 * möglich mit einem entsprechendem Konstruktor einen leeres Invitation-Objekt zu erstellen und dann
				 * diekt alle nötigen Attribute per Set-Methode zu setzen, allerdings läuft man dann Gefahr, dass man
				 * bei einem Mapper ein Attribut vergisst und halbfertige Objekte erstellt. Daher gibt es hier diesen
				 * Konstruktor, der alle Attribute fordert.
				 */
				Invitation invitation = new Invitation(event, invitee, creationDate, invitationID);

				// Zuletzt wird das Room-Objekt zurückgegebn.
				return invitation;
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
	 * Auslesen aller Einladungen.
	 * 
	 * @return Ein Vektor mit Invitation-Objekten, die sämtliche Räume beinhaltet.
	 */
	public Vector<Invitation> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Invitation> result = new Vector<Invitation>();

		try {
			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("SELECT * FROM invitations " + " ORDER BY id");

			// Für jeden Eintrag im Suchergebnis wird nun ein Room-Objekt erstellt.
			while (resultSet.next()) {

				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				Event event = getEventOfInvitation(resultSet.getInt("invitation_event"));
				User invitee = getUserOfInvitation(resultSet.getInt("invitation_invitee"));
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int invitationID = resultSet.getInt("id");

				/**
				 * ...und anschließend an den Konstruktor für ein neues Invitation-Objekt übergeben. Es wäre zwar auch
				 * möglich mit einem entsprechendem Konstruktor einen leeres Invitation-Objekt zu erstellen und dann
				 * diekt alle nötigen Attribute per Set-Methode zu setzen, allerdings läuft man dann Gefahr, dass man
				 * bei einem Mapper ein Attribut vergisst und halbfertige Objekte erstellt. Daher gibt es hier diesen
				 * Konstruktor, der alle Attribute fordert.
				 */
				Invitation invitation = new Invitation(event, invitee, creationDate, invitationID);

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(invitation);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines neuen Invitation-Datensatzes in die DB.
	 * 
	 * @param room
	 *            Das Invitation-Objekt, dass eingefügt werden soll.
	 * 
	 * @return Das aktualisierte Invitation-Objekt (hat jetzt von der DB eine ID bekommen).
	 */
	public Invitation insert(Invitation invitation) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM invitations ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * Das Invitation-Objekt erhält den bisher maximalen, nun um 1 erhöhten Primärschlüssel.
				 */
				invitation.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO invitations (id, participation_status, invitation_event, invitation_user, creation_date) "
						+ "VALUES ("
						+ invitation.getId()
						+ ", "
						+ getBooleanRepresentationAsInt(invitation.getParticipationStatus())
						+ ", "
						+ invitation.getEvent().getId()
						+ ", "
						+ invitation.getInvitee().getId()
						+ ", '"
						+ invitation.getCreationDate() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Rückgabe, des nun veränderten Invitation-Objekts. Es hat von der DB eine ID zugewiesen bekommen, die sie
		 * fortanverwendet, falls man den Datenastz zum Beispiel aus der DB löschen oder ihn updaten möchte.
		 */

		return invitation;
	}

	/**
	 * Update eines Invitation-Datensatzes in der Datenbank.
	 * 
	 * @param room
	 *            das Invitation-Objekt, dessen DB-Eintrag aktualisiert werden soll.
	 * @return invitation Das als Parameter übergebene Objekt
	 */
	public Invitation update(Invitation invitation) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE invitations SET id= " + invitation.getId() + ", participation_status= "
					+ getBooleanRepresentationAsInt(invitation.getParticipationStatus()) + ", invitation_event= "
					+ invitation.getEvent().getId() + ", invitation_user= " + invitation.getInvitee().getId()
					+ ", creation_date= '" + invitation.getCreationDate() + "')");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(Room room) zu wahren, geben wir das Room-Obejekt wieder zurück.
		return invitation;
	}

	/**
	 * Löschen des Datensatzes eines Room-Objekts aus der Datenbank.
	 * 
	 * @param room
	 *            Das Room-Objekt, dess DB-Datensatz gelöscht werden soll.
	 */
	public void delete(Invitation invitation) {
		// DB-Connection holen.
		Connection con = DBConnection.connection();

		try {
			// Leeres Statement vorbereiten.
			Statement stmt = con.createStatement();

			// Löschung durchführen.
			stmt.executeUpdate("DELETE FROM invitations " + "WHERE id= " + invitation.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<User> findAllUserByParticipationStatusForEvent(Event event, Boolean participationStatus) {
		// Vorbereiten des Ergebnisvectors.
		Vector<User> result = new Vector<User>();

		// User-Mapper vorbereiten
		UserMapper userMapper = UserMapper.userMapper();

		// Boolschen Wert in Datenbankfreundlichen Integer-Wert umwandeln.
		int participationStatusAsInt = getBooleanRepresentationAsInt(participationStatus);

		// DB-Connection holen.
		Connection con = DBConnection.connection();

		try {

			// Leeres Statement vorbereiten.
			Statement stmt = con.createStatement();

			// Query ausführen.
			ResultSet resultSet = stmt.executeQuery("SELECT invitation_invitee FROM invitations " + "WHERE invitation_event= "
					+ event.getId() + " AND participation_status = " + participationStatusAsInt);

			while (resultSet.next()) {
				User user = userMapper.findByKey(resultSet.getInt("invitation_invitee"));
				result.addElement(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvector zurückgeben.
		return result;
	}

	/**
	 * Hilfsfunktion, um das in der Tabelle per Fremdschlüssel verwiesene Event-Objekt abzufragen und dieses zu holen.
	 * 
	 * @param invitation_event
	 *            Fremdschlüssel, der auf den Belegungseintrag verweist.
	 * @return event Ein Event-Objekt, dass den Datensatz repräsentiert, auf den verwiesen wurde.
	 */
	private Event getEventOfInvitation(int invitation_event) {
		EventMapper eventMapper = EventMapper.eventMapper();
		Event event = eventMapper.findByKey(invitation_event);
		return event;
	}

	/**
	 * Hilfsfunktion, um das in der Tabelle per Fremdschlüssel verwiesene User-Objekt abzufragen und dieses zu holen.
	 * 
	 * @param invitation_event
	 *            Fremdschlüssel, der auf den Belegungseintrag verweist.
	 * @return user Ein User-Objekt, dass den Datensatz repräsentiert, auf den verwiesen wurde.
	 */
	private User getUserOfInvitation(int invitation_invitee) {
		UserMapper userMapper = UserMapper.userMapper();
		User user = userMapper.findByKey(invitation_invitee);
		return user;
	}

	/**
	 * Hilfsfunktion, um die boolschen Werte als Integer darzustellen.
	 * <p>
	 * 0 = false und 1 = true
	 * </p>
	 * In dieser Form werden sie dann in die DB geschrieben.
	 * 
	 * @param b
	 *            Der boolsche Wert, der als Integer repräsentiert werden soll.
	 * @return intRepresentation Repräsentation des boolschen Wertes als Integer (0 = false, 1 = true);
	 */
	private int getBooleanRepresentationAsInt(boolean b) {
		int intRepresentation;
		if (b = false) {
			intRepresentation = 0;
		} else {
			intRepresentation = 1;
		}
		return intRepresentation;
	}

}
