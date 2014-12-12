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

	/*
	 * Zero-Argument-Konstruktor. Wird von GWT benötigt. Zum Erstellen der Objekte bitte einen der beiden anderen
	 * Konstuktoren verwenden.
	 */
	private Invitation() {
	};
	
	/**
	 * Der Konstruktor der Klasse Invitation. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt
	 * werden soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. Wenn das Objekt schon
	 * existiert und nur aus wiederhergestellt werden soll (zum Beispiel aus der DB), dann bitte den Konstruktor
	 * verwenden, der zusätzlich den originalen Erstellugnszeitpunkt entgegen nimmt.
	 * 
	 * Standardmäßig ist der Teilnahmestatus auf <code>false</code> gesetzt, daher muss er hier nicht übergeben werden.
	 * 
	 * @param eventId
	 *            Die ID der zugehörigen Belegung.
	 * @param inviteeId
	 *            Die ID des zugehörigen eingeladenen Nutzers.
	 */
	public Invitation(int eventId, int inviteeId) {
		this.eventId = eventId;
		this.inviteeId = inviteeId;
		setCreationDate();

	}

	/**
	 * Ein zweiter Konstruktor für die Klasse Invitation. Dieser soll verwendet werden, wenn ein Objekt bereits ein
	 * Erstellungsdatum und eine ID besitzt und diese nicht neu erstellt werden müssen. Dies der Fall, wenn ein Objekt
	 * aus einem DB-Eintrag wiederhergestellt werden soll.
	 * 
	 * @param eventId
	 *            Die ID der zugehörigen Belegung.
	 * @param invitee
	 *            Die ID des zugehörigen eingeladenen Nutzers.
	 * @param creationDate
	 *            Der originale Erstellnugszeitpunkt des Objekts.
	 * @param invitationId
	 *            Die ID des Objekts aus der DB.
	 */
	public Invitation(int eventId, int participationStatus, int inviteeId, Timestamp creationDate, int invitationId) {
		this.eventId = eventId;
		this.participationStatus = participationStatus;
		this.inviteeId = inviteeId;
		setCreationDate(creationDate);
		this.id = invitationId;
	}

	// Attribute

	/**
	 * <p>
	 * <b>0 = flase, 1 = true </b>
	 * </p>
	 * Der Teilnahmestatus einer Einladung. Standardmäßig ist dieser auf <code>0</code> gesetzt. Um in Verbindung mit
	 * der DB nicht immer zwischen boolean und int umwandeln zu müssen, sollen hier direkt nur Integers verwendet
	 * werden.
	 */
	private int participationStatus = 0;

	/**
	 * Jede Einladung bezieht auf eine Belegung. Der Verweis auf die entsprechende Belegung wird in dieser Variabe
	 * gespeichert.
	 */
	private int eventId = 0;

	/**
	 * Jede Einladung ist einem Nutzer zugeordnet. Der Verweis auf diesen wird in dieser Variable gespeichert.
	 */
	private int inviteeId = 0;

	// Methoden

	/**
	 * Auslesen des Teilnahmestatuses
	 * 
	 * @return Der Teilnahmestatus für eine Einladung (wahr oder falsch).
	 */
	public int getParticipationStatus() {
		return participationStatus;
	}

	/**
	 * Setzen des neuen Teilnahmestatusses der Einladung.
	 * 
	 * @param participationStatus
	 *            Der neue Teilnahmestatus für diese Einladung (wahr oder falsch).
	 */
	public void setParticipationStatus(int participationStatus) {
		this.participationStatus = participationStatus;
	}

	/**
	 * Auslesen des der Belegungs-ID zugehörigen Events.
	 * 
	 * @return Die ID der zugehörigen Belegung.
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * Setzen der Belegungs-ID für eine Einladung.
	 * 
	 * @param eventId
	 *            Die zugehörige Belegungs-ID.
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * Auslesen der ID des der Einladung zugehörigen Nutzers.
	 * 
	 * @return Die ID des der Einladung zugehörigen Nuzters.
	 */
	public int getInviteeId() {
		return inviteeId;
	}

	/**
	 * Setzen der ID des eingeladenen Nutzers.
	 * 
	 * @param inviteeId
	 *            Die ID des der Einladung zugewiesen Nutzers.
	 */
	public void setInviteeId(int inviteeId) {
		this.inviteeId = inviteeId;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um die zugehörige Belegungs-ID, die zugehörigen
	 * Nutzer-ID und den Teilnahmestatus dessen erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Einladung für Event-ID: " + getEventId() + lineBreak + "Eingeladener-ID: "
				+ getInviteeId() + lineBreak + "Teilnahmestatus: " + getParticipationStatus() + lineBreak;
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
