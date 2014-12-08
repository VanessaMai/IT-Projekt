package roomreservationservice.shared.bo;
/**
 * Klasse, die eine Einladung im Raumplanungssystem abbilden soll.
 */
public class Invitation extends BusinessObject {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5271320310832622823L;

	
	// Attribute
	
	
	/**
	 * Der Teilnahmestatus einer Einladung. Standardmäßig ist dieser auf <code>false</code> gesetzt.
	 */
	private boolean participationStatus = false; 
	
	/**
	 * Jede Einladung bezieht ist auch eine Belegung. Diese wird in der variabel <code>event</code> gespeichert.
	 */
	private Event event = null;
	
	/**
	 * Jede Einladung ist einem Nutzer zugeordnet. Dieser wird in der Variable <code>invitee</code> gespeichert.
	 */
	private User invitee = null;

	
	
	
	// Methoden
	
	/**
	 * Auslesen des Teilnahmestatuses
	 */
	public boolean getParticipationStatus() {
		return participationStatus;
	}



	/**
	 * Setzen des neuen Teilnahmestatusses der Einladung.
	 * @param participationStatus	Der Teilnahmestatus.
	 */
	public void setParticipationStatus(boolean participationStatus) {
		this.participationStatus = participationStatus;
	}



	/**
	 * Auslesen des der Belegung zugehörigen Events.
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Setzen der der Einladung zugehörigen Belegung.
	 * @param event	Neue Belegung.
	 */
	public void setEvent(Event event) {
		this.event = event;
	}


	/**
	 * Auslesen des der Einladung zugehörogen Nutzers.
	 */
	public User getInvitee() {
		return invitee;
	}


	/**
	 * Setzen des der Einladung zugehörigen Nutzers.
	 * @param invitee	Zugehöriger Nutzer.	
	 */
	public void setInvitee(User invitee) {
		this.invitee = invitee;
	}




	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird und um die zugehörige Belegung, den zugehörigen Nutzer und den Teilnahmestatus dessen erweitert wird.
	 */
	@Override
	public String toString() {
	  return super.toString() + "Einladung für Event: " + event.toString() + "Eingeladener: " + invitee.toString() + "Teilnahmestatus: " + Boolean.toString(participationStatus);
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
	    if (o != null && o instanceof Invitation) {
	    	Invitation c = (Invitation) o;
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
