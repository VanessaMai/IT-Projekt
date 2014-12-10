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

	/**
	 * Der Konstruktor der Klasse Room. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt werden
	 * soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. Wenn das Objekt schon existiert
	 * und nur aus wiederhergestellt werden soll (zum Beispiel aus der DB), dann bitte den Konstruktor verwenden, der
	 * zusätzlich den originalen Erstellugnszeitpunkt entgegen nimmt.
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

	/**
	 * Ein zweiter Konstruktor für die Klasse Room. Dieser soll verwendet werden, wenn ein Objekt bereits ein
	 * Erstellungsdatum und eine ID besitzt und diese nicht neu erstellt werden müssen. Dies der Fall, wenn ein Objekt
	 * aus einem DB-Eintrag wiederhergestellt werden soll.
	 * 
	 * @param roomName
	 *            Der Name des Raums.
	 * @param roomCapacity
	 *            Die Kapazität des Raums
	 * @param creationDate
	 *            Der originale Erstellnugszeitpunkt des Objekts.
	 * @param roomID
	 *            Die ID des Objekts aus der DB.
	 */
	public Room(String roomName, int roomCapacity, Timestamp creationDate, int roomID) {
		this.roomName = roomName;
		this.roomCapacity = roomCapacity;
		setCreationDate(creationDate);
		this.id = roomID;
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
