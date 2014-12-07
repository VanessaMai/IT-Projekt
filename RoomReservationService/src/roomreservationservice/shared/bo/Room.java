package roomreservationservice.shared.bo;

/**
 * Klasse, die einen Raum im Raumplanungssystem abbildet.
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
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * Setzen des Raumnamens.
	 * @param roomName	Neuer Name des Raums.
	 */
	public void setRoomName(String roomName) {
		 this.roomName = roomName;
	}
	
	/**
	 * Ausgeben der Raumkapazität.
	 */
	public int getRoomCapacity() {
		return roomCapacity;
	}
	
	/**
	 * Setzen der Raumkapazität.
	 * @param roomCapacity	Neue Kapazität des Raums
	 */
	public void setRoomCapacity(int roomCapacity) {
		 this.roomCapacity = roomCapacity;
	}
	
	
	
	/**
	   * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird und um den Raumnamen und die Raumkapazität des Raums erweitert wird.
	   */
	  @Override
	public String toString() {
	    return super.toString() + "Raumname: " + this.getRoomName() + "Raumkapazität: "+ this.getRoomCapacity();

	  }

	  
	  /**
	   * Feststellen der inhaltlichen Gleichheit zweier
	   * <code>Room</code>-Objekte.
	   */
	@Override
	public boolean equals(Object o) {
	    /*
	     * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden
	     * kann.
	     */
	    if (o != null && o instanceof Room) {
	    	Room c = (Room) o;
	      try {
	        return super.equals(c);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }
	  
}
