package roomreservationservice.shared.bo;

import java.sql.Timestamp;

/**
 * Klasse, die einen Raum im Raumplanungssystem abbildet.
 */
public class Room extends BusinessObject {

	/**
	 * Serial UID.
	 */

	private static final long serialVersionUID = 59441838292066082L;

	/*
	 * Zero-Argument-Konstruktor. Wird von GWT benötigt. Zum Erstellen der Objekte bitte einen der beiden anderen
	 * Konstuktoren verwenden.
	 */
	private Room() {
	};
	
	/**
	 * Der Konstruktor der Klasse Room. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt werden
	 * soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. 
	 * 
	 * @param roomName
	 *            Der Name des Raums.
	 * @param roomCapacity
	 *            Die Kapazitäts des Raums.
	 */
	public Room(String roomName, int roomCapacity) {
		this.roomName = roomName;
		this.roomCapacity = roomCapacity;
		setCreationDate();
	}


	// Attribute

	/**
	 * Der Name des Raums. Standardmäßig "unknown".
	 */
	private String roomName = "unknown";
	/**
	 * Die Kapazität des Raums. Standardmäßig 0.
	 */
	private int roomCapacity = 0;

	// Methoden

	/**
	 * Augeben des Raumnamens.
	 * 
	 * @return roomName Der Name des Raums.
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * Setzen des Raumnamens.
	 * 
	 * @param roomName
	 *            Der neue Name des Raums.
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * Ausgeben der Raumkapazität.
	 * 
	 * @return roomCapacity Die Kapazität des Raums.
	 */
	public int getRoomCapacity() {
		return roomCapacity;
	}

	/**
	 * Setzen der Raumkapazität.
	 * 
	 * @param roomCapacity
	 *            Neue Kapazität des Raums
	 */
	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um den Raumnamen und die Raumkapazität des Raums
	 * erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Raumname: " + getRoomName() + lineBreak + "Raumkapazität: " + getRoomCapacity() + lineBreak;

	}

	/**
	 * Feststellen der inhaltlichen Gleichheit zweier <code>Room</code>-Objekte.
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden kann.
		 */
		if (o != null && o instanceof Room) {
			Room c = (Room) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}
