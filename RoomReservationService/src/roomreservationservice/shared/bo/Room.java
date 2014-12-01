package roomreservationservice.shared.bo;

/**
 * Klasse, die einen Raum im Raumplanungssystem abbilden soll.
 */
public class Room extends BusinessObject {
	
	
	private static final long serialVersionUID = 4742285358709100741L;
	
	
	// Attribute 
	
	/**
	 * Der Name des Raums.
	 */
	private String roomName = "";
	/**
	 * Die Kapazität des Raums.
	 */
	private int roomCapacity = 0;
	
	
	
	// Methoden 
	
	/**
	 * Augeben des Raumnamens.
	 * @return roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * Setzen des Raumnamens.
	 * @param newRoomName
	 */
	public void setRoomName(String newRoomName) {
		 this.roomName = newRoomName;
	}
	
	/**
	 * Ausgeben der Raumkapazität.
	 * @return roomCapacity
	 */
	public int getRoomCapacity() {
		return roomCapacity;
	}
	
	/**
	 * Setzen der Raumkapazität.
	 * @param newRoomCapacity
	 */
	public void setRoomCapacity(int newRoomCapacity) {
		 this.roomCapacity = newRoomCapacity;
	}
	
	
	
	/**
	   * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird und um den Raumnamen und die Raumkapazität des Raums erweitert wird.
	   */
	  @Override
	public String toString() {
	    return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate() + "Raumname: " + this.getRoomName() + "Raumkapazität: "+ this.getRoomCapacity();

	  }
}
