package roomreservationservice.shared.bo;

import java.sql.Timestamp;

/**
 * Klasse, die eine Belegung im Raumplanungssystem abbilden soll.
 */
public class Event extends BusinessObject {

	/**
	 * Automatisch generierte UID.
	 */
	private static final long serialVersionUID = -2587927450101616053L;

	/*
	 * Zero-Argument-Konstruktor. Wird von GWT benötigt. Zum Erstellen der Objekte bitte einen der beiden anderen
	 * Konstuktoren verwenden.
	 */
	private Event() {
	};

	/**
	 * Der Konstruktor der Klasse Invitation. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt
	 * werden soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. 
	 * 
	 * @param topic
	 *            Das Belegungsthema.
	 * @param startDate
	 *            Der Startzeitpunkt der Belegung.
	 * @param endDate
	 *            Der Endzeitpunkt der Belegung.
	 * @param organizerId
	 *            ID des Nutzers, der als Organisator der Belegung eingetragen werden soll.
	 * @param roomId
	 *            ID des Raums der belegt werden soll.
	 */
	public Event(String topic, Timestamp startDate, Timestamp endDate, int organizerId, int roomId) {
		this.topic = topic;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizerId = organizerId;
		this.roomId = roomId;
		setCreationDate();
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
	 * ID des Users, der als Organisator der Belegung auftritt.
	 */
	private int organizerId = 0;

	/**
	 * ID des Raums, der der Belegung zugeordneter ist.
	 */
	private int roomId = 0;

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
	 * Ausgeben der ID des Organisators der Belegung.
	 * 
	 * @return ID des Nutzers, der als Oransator des Event eingetragen ist.
	 */
	public int getOrganizerId() {
		return organizerId;
	}

	/**
	 * Setzen der ID des Organisators der Belegung.
	 * 
	 * @param organizerId
	 *            Die ID des Nutzers, der als Organisator des Events auftritt.
	 */
	public void setOrganizerId(int organizerId) {
		this.organizerId = organizerId;
	}

	/**
	 * Ausgeben der ID des zugeordneten Raums einer Belegung.
	 * 
	 * @return Die ID des zugeordneten Raums der Belegung.
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * Setzen der ID des Raums einer Belegung.
	 * 
	 * @param roomId
	 *            ID des neuen, der Belegung zugeordneter Raums.
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um das Belegungsthema, den Start- und
	 * Endzeitpunkt, sowie die ID des Organisators der Belegung und die ID des belegten Raums erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Belegungsthema: " + getTopic() + lineBreak + "Startzeitpunkt: " + getStartDate()
				+ lineBreak + "Endzeitpunkt: " + getEndDate() + lineBreak + "Organisator-ID: " + getOrganizerId()
				+ lineBreak + "Belegter Raum-ID: " + getRoomId() + lineBreak;

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
