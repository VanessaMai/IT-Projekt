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
/**
 * Klasse, die Methoden bereitstellt um die Daten eines Einladungs-Objekts auf eine relationiale Datenbank abzubilden.
 * Datensäte können erstellt, geändert und gelöscht werden. Aus einem Datensatz kann zudem wieder ein Java-Objekt
 * gemacht werden.
 * 
 * @author Julius Renner
 *
 */
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
			 * Da die ID der Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein Ergebnis
			 * vorliegt.
			 */
			if (resultSet.next()) {
				// Zuerst werden die Attribute einzeln aus der DB abgefragt...
				int eventId = resultSet.getInt("invitation_event");
				boolean participationStatus = resultSet.getBoolean("participation_status");
				int inviteeId = resultSet.getInt("invitation_invitee");
				Timestamp creationDate = resultSet.getTimestamp("creation_date");
				int invitationId = resultSet.getInt("id");

				/**
				 * ...und anschließend an den Konstruktor für ein neues Invitation-Objekt übergeben.
				 */
				Invitation invitation = new Invitation(eventId, inviteeId);

				/*
				 * Setzen des Erstellungszeitpunktes, der ID und des Teilnahmestatuses aus der DB.
				 */
				invitation.setCreationDate(creationDate);
				invitation.setId(invitationId);
				invitation.setParticipationStatus(participationStatus);

				// Zuletzt wird das Room-Objekt zurückgegebn.
				return invitation;
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

			// Prüfen, ob Einträge gefunden wurden.
			if (resultSet.next()) {
				// Für jeden Eintrag im Suchergebnis wird nun ein Room-Objekt erstellt.
				do {

					// Für jeden Eintrag wird die findByKey-Methode aufgerufen, die das Invitation-Obejekt
					// zurückliefert.
					Invitation invitation = findByKey(resultSet.getInt("id"));

					// Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(invitation);
				} while (resultSet.next());

				// Ergebnisvektor zurückgeben
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
			ResultSet resultSet = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM invitations ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (resultSet.next()) {
				/*
				 * Das Invitation-Objekt erhält den bisher maximalen, nun um 1 erhöhten Primärschlüssel.
				 */
				invitation.setId(resultSet.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO invitations (id, participation_status, invitation_event, invitation_user, creation_date) "
						+ "VALUES ("
						+ invitation.getId()
						+ ", "
						+ invitation.getParticipationStatus()
						+ ", "
						+ invitation.getEventId()
						+ ", "
						+ invitation.getInviteeId()
						+ ", '"
						+ invitation.getCreationDate() + "')");
			}

			/*
			 * Rückgabe, des nun veränderten Invitation-Objekts. Es hat von der DB eine ID zugewiesen bekommen, die sie
			 * fortanverwendet, falls man den Datenastz zum Beispiel aus der DB löschen oder ihn updaten möchte.
			 */

			return invitation;
		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

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
					+ invitation.getParticipationStatus() + ", invitation_event= " + invitation.getEventId()
					+ ", invitation_invitee= " + invitation.getInviteeId() + " WHERE id= " + invitation.getId());

			// Um Analogie zu insert(Invitation invitation) zu wahren, geben wir das Invitation-Obejekt wieder zurück.
			return invitation;

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
		} // SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Wenn kein Eintrag vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Alle Einladungen finden, die zu einer bestimmten Belegeung gehören tragen.
	 * 
	 * @param event
	 *            Das Event, zu dem alle Einladungen ausgeben werden sollen
	 * @return Vector mit allen Invitations, die zum Event gehören
	 */
	public Vector<Invitation> findAllByEvent(Event event) {
		// DB Connection vorbereiten
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten.
		Vector<Invitation> result = new Vector<Invitation>();

		try {
			// Statement vorbereiten.
			Statement stmt = con.createStatement();

			// Query durchführen und nach Einträgen suchen, bei denen der Namename dem Suchebgriff entspricht
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM invitations WHERE invitation_event = "
					+ event.getId());

			if (resultSet.next()) {

				do {
					Invitation invitation = invitationMapper.findByKey(resultSet.getInt("id"));

					// Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(invitation);
				} while (resultSet.next());

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
		// Wenn kein Eintrag vorhanden ist.
		catch (NullPointerException e2) {
			e2.printStackTrace();
			return null;
		}

	}
	/**
	 * Alle Invitation zu einem User finden
	 * @param user, zu dem alle Invitationobjekte ausgegeben werden sollen
	 * @return Vector mit allen Invitation des übergegebenen Users 
	 */
	public Vector<Invitation> findAllByUser(User user){
		//DB Connection vorbereiten
		Connection con = DBConnection.connection();
		
		//Ergebnisvektor vorbereiten
		Vector<Invitation> result = new Vector<Invitation>();
		
		try {
			//Statement vorbereiten
			Statement stmt = con.createStatement();
			
			// Query durchführen und nach Einträgen suchen, bei denen der User Name dem Suchbegriff entspricht
			ResultSet resultSet = stmt.executeQuery("SELECT id FROM invitations WHERE invitation_invitee = " + user.getId());
			
			if(resultSet.next()) {
				do{
					Invitation invitation = invitationMapper.findByKey(resultSet.getInt("id"));
					
					//Hinzufügen des neuen Objekts zum Ergebnisvektor
					result.addElement(invitation);
				} while (resultSet.next());
				return result;
			}
			//wenn das Resultat leer ist, wird <code>null</code> zurückgegeben.
			else {
				return null;
			}
		} //SQL Exception abfangen, sollte etwas schiefgehen.
		catch (SQLException e1){
			e1.printStackTrace();
			return null;
		}
		//wenn kein Eintrag vorhanden ist.
		catch(NullPointerException e2){
			return null;
		}
		
		
	}

}
