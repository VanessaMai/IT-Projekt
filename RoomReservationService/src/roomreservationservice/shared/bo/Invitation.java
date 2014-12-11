package roomreservationservice.shared.bo;

import java.sql.Timestamp;

/**
 * Klasse, die eine Einladung im Raumplanungssystem abbilden soll.
 */
public class Invitation extends BusinessObject {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5271320310832622823L;

	/**
	 * Der Konstruktor der Klasse Invitation. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt
	 * werden soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. Wenn das Objekt schon
	 * existiert und nur aus wiederhergestellt werden soll (zum Beispiel aus der DB), dann bitte den Konstruktor
	 * verwenden, der zusätzlich den originalen Erstellugnszeitpunkt entgegen nimmt.
	 * 
	 * Standardmäßig ist der Teilnahmestatus auf <code>false</code> gesetzt, daher muss er hier nicht übergeben werden.
	 * 
	 * @param event
	 *            Die zugehörige Belegung.
	 * @param invitee
	 *            Der zugehörige eingeladene User.
	 */
	public Invitation(Event event, User invitee) {
		this.event = event;
		this.invitee = invitee;
	}

	/**
	 * Ein zweiter Konstruktor für die Klasse Invitation. Dieser soll verwendet werden, wenn ein Objekt bereits ein
	 * Erstellungsdatum und eine ID besitzt und diese nicht neu erstellt werden müssen. Dies der Fall, wenn ein Objekt
	 * aus einem DB-Eintrag wiederhergestellt werden soll.
	 * 
	 * @param event
	 *            Die zugehörige Belegung.
	 * @param invitee
	 *            Der zugehörige eingeladene User.
	 * @param creationDate
	 *            Der originale Erstellnugszeitpunkt des Objekts.
	 * @param invitationID
	 *            Die ID des Objekts aus der DB.
	 */
	public Invitation(Event event, User invitee, Timestamp creationDate, int invitationID) {
		this.event = event;
		this.invitee = invitee;
		setCreationDate(creationDate);
		this.id = invitationID;
	}

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
	 * 
	 * @return participationStatus Der Teilnahmestatus (wahr oder falsch).
	 */
	public boolean getParticipationStatus() {
		return participationStatus;
	}

	/**
	 * Setzen des neuen Teilnahmestatusses der Einladung.
	 * 
	 * @param participationStatus
	 *            Der neue Teilnahmestatus (wahr oder falsch).
	 */
	public void setParticipationStatus(boolean participationStatus) {
		this.participationStatus = participationStatus;
	}

	/**
	 * Auslesen des der Belegung zugehörigen Events.
	 * 
	 * @return event Das zugehöroge Event.
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Setzen der der Einladung zugehörigen Belegung.
	 * 
	 * @param event
	 *            Die zugehörige Belegung.
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Auslesen des der Einladung zugehörogen Nutzers.
	 * 
	 * @return invitee Der der Einladung zugehörige Nuzter.
	 */
	public User getInvitee() {
		return invitee;
	}

	/**
	 * Setzen des der Einladung zugehörigen Nutzers.
	 * 
	 * @param invitee
	 *            Der Nutzer der Einladung zugewiesen werden soll.
	 */
	public void setInvitee(User invitee) {
		this.invitee = invitee;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um die zugehörige Belegung, den zugehörigen
	 * Nutzer und den Teilnahmestatus dessen erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Einladung für Event: " + getEvent().getTopic() + " von "
				+ getEvent().getOrganizer().getFirstName() + " " + getEvent().getOrganizer().getLastName()
				+ "(User-ID: " + getEvent().getOrganizer().getId() + ")" + lineBreak + "Eingeladener: " + getInvitee()
				+ lineBreak + "Teilnahmestatus: " + getParticipationStatus() + lineBreak;
	}

	/**
	 * Feststellen der inhaltlichen Gleichheit zweier <code>Room</code>-Objekte.
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden kann.
		 */
		if (o != null && o instanceof Invitation) {
			Invitation c = (Invitation) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}
