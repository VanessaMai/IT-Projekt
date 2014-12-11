package roomreservationservice.shared.bo;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * Klasse, die eine Belegung im Raumplanungssystem abbilden soll.
 */
public class Event extends BusinessObject {

	/**
	 * Automatisch generierte UID.
	 */
	private static final long serialVersionUID = -2587927450101616053L;

	/**
	 * Der Konstruktor der Klasse Invitation. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt
	 * werden soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. Wenn das Objekt schon
	 * existiert und nur aus wiederhergestellt werden soll (zum Beispiel aus der DB), dann bitte den Konstruktor
	 * verwenden, der zusätzlich den originalen Erstellugnszeitpunkt entgegen nimmt.
	 * 
	 * @param topic
	 *            Das Belegungsthema.
	 * @param startDate
	 *            Der Startzeitpunkt der Belegung.
	 * @param endDate
	 *            Der Endzeitpunkt der Belegung.
	 * @param organizer
	 *            Der User, der als Organisator der Belegung eingetragen werden soll.
	 * @param room
	 *            Der Raum der belegt werden soll.
	 * @param invitees
	 *            Ein Vector mit allen eingeladenen Nutzern. Diese sollen später alle eine Einladung erhalten.
	 */
	public Event(String topic, Timestamp startDate, Timestamp endDate, User organizer, Room room, Vector<User> invitees) {
		this.topic = topic;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizer = organizer;
		this.room = room;
		this.invitees = invitees;
	}

	/**
	 * Ein zweiter Konstruktor für die Klasse Event. Dieser soll verwendet werden, wenn ein Objekt bereits ein
	 * Erstellungsdatum und eine ID besitzt und diese nicht neu erstellt werden müssen. Dies der Fall, wenn ein Objekt
	 * aus einem DB-Eintrag wiederhergestellt werden soll.
	 * 
	 * @param topic
	 *            Das Belegungsthema.
	 * @param startDate
	 *            Der Startzeitpunkt der Belegung.
	 * @param endDate
	 *            Der Endzeitpunkt der Belegung.
	 * @param organizer
	 *            Der User, der als Organisator der Belegung eingetragen werden soll.
	 * @param room
	 *            Der Raum der belegt werden soll.
	 * @param invitees
	 *            Ein Vector mit allen eingeladenen Nutzern. Diese sollen später alle eine Einladung erhalten.
	 * @param creationDate
	 *            Der originale Erstellnugszeitpunkt des Objekts.
	 * @param eventID
	 *            Die ID des Objekts aus der DB.
	 */
	public Event(String topic, Timestamp startDate, Timestamp endDate, User organizer, Room room,
			Vector<User> invitees, Timestamp creationDate, int eventID) {
		this.topic = topic;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizer = organizer;
		this.room = room;
		this.invitees = invitees;
		setCreationDate(creationDate);
		this.id = eventID;
	}

	// Attribute

	/**
	 * Thema der Belegung.
	 */
	private String topic = "unknown";

	/**
	 * Startzeitpunkt der Belegung.
	 */
	private Timestamp startDate;

	/**
	 * Endzeitpunkt der Belegung.
	 */
	private Timestamp endDate;

	/**
	 * User, der als Organisator der Belegung auftritt.
	 */
	private User organizer;

	/**
	 * Der Belegung zugeordneter Raum.
	 */
	private Room room;

	/**
	 * Vector mit den der Belegung zugeordneten Nutzer, also der Eingeladenen.
	 */
	private Vector<User> invitees;

	// Methoden

	/**
	 * Ausgeben des Beleungsthemas.
	 * 
	 * @return topic Das Beleungsthema.
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Setzten des Belegungsthemas.
	 * 
	 * @param topic
	 *            Das Belegungsthema.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Ausgeben des Startzeitpunkts einer Belegung.
	 * 
	 * @return startDate Der Startzeitpunkt der Belegung.
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Setzen des Startzeitpunks einer Beleugung.
	 * 
	 * @param startDate
	 *            Der Startzeitpunkt der Belegung.
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * Ausgeben des Endzeitpunkts einer Belegung.
	 * 
	 * @return endDate Der Endzeitpunkt der Belegung.
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Setzen des Endzeitpunkts einer Beleugung.
	 * 
	 * @param endDate
	 *            Der Endzeitpunkt der Belegung.
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * Ausgeben des Organisators der Belegung.
	 * 
	 * @return organizer Der Nutzer, der als Oransator des Event eingetragen ist.
	 */
	public User getOrganizer() {
		return organizer;
	}

	/**
	 * Setzen des Organisators der Belegung.
	 * 
	 * @param organizer
	 *            Der Nutzer, der als Organisator des Events auftritt.
	 */
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	/**
	 * Ausgeben des zugeordneten Raums einer Belegung.
	 * 
	 * @return room Der zugeordnete Raum der Belegung.
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Setzen des Raums einer Belegung
	 * 
	 * @param room
	 *            Neuer, der Belegung zugeordneter Raum.
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * Auslesen der Eingeladenen einer Belegung.
	 * 
	 * @return invitess Vector mit allen der Belegung zugeordneten Nutzern.
	 */
	public Vector<User> getInvitees() {
		return invitees;
	}

	/**
	 * Setzer des Vectors mit den Eingeladenen einer Belegung.
	 * 
	 * @param invitees
	 *            Vector mit den Eingeladenen einer Belegung.
	 */
	public void setInvitees(Vector<User> invitees) {
		this.invitees = invitees;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um das Belegungsthema, den Start- und
	 * Endzeitpunkt, sowie den Organisator der Belegung und den belegten Raum erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Belegungsthema: " + getTopic() + lineBreak + "Startzeitpunkt: " + getStartDate()
				+ lineBreak + "Endzeitpunkt: " + getEndDate() + lineBreak + "Organisator: "
				+ getOrganizer().getFirstName() + " " + getOrganizer().getLastName() + " (User-ID: "
				+ getOrganizer().getId() + ")" + lineBreak + "Belegter Raum: " + getRoom().getRoomName()
				+ " (Raum-ID: " + getRoom().getId() + ")" + lineBreak + "Eingeladene: " +  lineBreak 
				+ makeReadableUserList(getInvitees())
				+ lineBreak;

	}

	/**
	 * Kleine Hilfsfunktion, die aus einem Vector mit User einen String macht, in dem jeder User mit Vor-, Nachname und
	 * seiner ID aufgeführt ist. Dies wird hier getan um bei toString()-Methoden mit User-Vektoren diese in gut lesbarer
	 * Form ausgeben zu können.
	 * 
	 * @param userVector
	 *            Der Vector mit User-Objekten, der in einen lesbaren String umgewandelt werden soll.
	 * @return Ein String mit einer kurzen und lesbaren Repräsenation der enthaltentn User.
	 */
	private String makeReadableUserList(Vector<User> userVector) {
		StringBuilder stringBuilder = new StringBuilder();

		for (User user : userVector) {
			stringBuilder.append("- " + user.getFirstName() + " " + user.getLastName() + " (User-ID: " + user.getId() + ")"
					+ lineBreak);
		}
		String result = stringBuilder.toString();
		return result;
	}

	/**
	 * Feststellen der inhaltlichen Gleichheit zweier <code>Event</code>-Objekte.
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden kann.
		 */
		if (o != null && o instanceof Event) {
			Event c = (Event) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}
