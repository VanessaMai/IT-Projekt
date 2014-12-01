package roomreservationservice.shared.bo;

import java.util.Date;
import java.util.Vector;


/**
 * Klasse, die eine Belegung im Raumplanungssystem abbilden soll.
 */
public class Event extends BusinessObject {

	/**
	 * Automatisch generierte UID.
	 */
	private static final long serialVersionUID = -2587927450101616053L;

	// Attribute 
	private String topic = "";
	private Date startDate = null;
	private Date endDate = null;
	private User organizer = null;
	private Room room = null;
	private Vector invitees = null;
	// TODO Vector Deklaration
	
	// Methoden
	
	
	
	/**
	 * Ausgeben des Beleungsthemas. 
	 * @return topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Setzten des Belegungsthemas. 
	 * @param topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Ausgeben des Startzeitpunkts einer Belegung.
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Setzen des Startzeitpunks einer Beleugung.
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Ausgeben des Endzeitpunkts einer Belegung.
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Setzen des Endzeitpunkts einer Beleugung.
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Ausgeben des Organisators der Belegung. 
	 * @return organizer
	 */
	public User getOrganizer() {
		return organizer;
	}

	/**
	 * Setzen des Organisators der Belegung.
	 * @param organizer
	 */
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	/**
	 * Ausgeben des Raums einer Belegung.
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Setzen des Raums einer Belegung
	 * @param room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird und um  das Belegungsthema, den Start- und Endzeitpunkt, sowie den Organisator der Belegung und den belegten Raum erweitert wird.
	 */
	@Override
	public String toString() {
	  return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate() + "Belegungsthema: " + this.getTopic() + "Startzeitpunkt: "+ this.getStartDate() + "Endzeitpunkt: " + getEndDate() + "Organisator: " + getOrganizer() + "Belegter Raum: " + getRoom();

	  }
}
