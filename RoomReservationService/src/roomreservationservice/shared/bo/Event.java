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
	 * Konstruktor der Klasse Event.
	 */
	public Event(String topic, Timestamp startDate, Timestamp endDate, User organizer, Room room, Vector<User> invitees) {
		this.topic = topic;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizer = organizer;
		this.room = room;
		this.invitees = invitees;
	}

	// Attribute

	/**
	 * Thema der Belegung.
	 */
	private String topic = "";

	/**
	 * Startzeitpunkt der Belegung.
	 */
	private Timestamp startDate = null;

	/**
	 * Endzeitpunkt der Belegung.
	 */
	private Timestamp endDate = null;

	/**
	 * User, der als Organisator der Belegung auftritt.
	 */
	private User organizer = null;

	/**
	 * Der Belegung zugeordneter Raum.
	 */
	private Room room = null;

	/**
	 * Vector mit den der Belegung zugeordneten Nutzer, also der Eingeladenen.
	 */
	private Vector<User> invitees = null;

	// Methoden

	/**
	 * Ausgeben des Beleungsthemas.
	 * 
	 * @return topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Setzten des Belegungsthemas.
	 * 
	 * @param topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Ausgeben des Startzeitpunkts einer Belegung.
	 * 
	 * @return startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Setzen des Startzeitpunks einer Beleugung.
	 * 
	 * @param startDate
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * Ausgeben des Endzeitpunkts einer Belegung.
	 * 
	 * @return endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Setzen des Endzeitpunkts einer Beleugung.
	 * 
	 * @param endDate
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * Ausgeben des Organisators der Belegung.
	 * 
	 * @return organizer
	 */
	public User getOrganizer() {
		return organizer;
	}

	/**
	 * Setzen des Organisators der Belegung.
	 * 
	 * @param organizer
	 *            Nutzer, der als Organisator des Events auftritt.
	 */
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	/**
	 * Ausgeben des Raums einer Belegung.
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
		return super.toString() + "Belegungsthema: " + this.getTopic() + "Startzeitpunkt: " + this.getStartDate()
				+ "Endzeitpunkt: " + getEndDate() + "Organisator: " + getOrganizer() + "Belegter Raum: " + getRoom()
				+ "Oranisator: " + organizer.toString() + "Eingeladene: " + invitees.toString();

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
