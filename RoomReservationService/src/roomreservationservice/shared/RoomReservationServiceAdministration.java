package roomreservationservice.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;


/* 
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Raumbelegungen.
 */

public interface RoomReservationServiceAdministration extends RemoteService {
	
	/*
	 * Dient zur Initialisierung des Objekts. Diese Methode wird bei Aufruf des Systems automatisch ausgeführt.
	 */
	public void init() throws IllegalArgumentException;
	
	/*
	 * Raum anlegen
	 * @param roomName Raumname
	 * @param capacity Fassungsvermögen  
	 * @return Room ein fertiges Raumobjekt
	 * @throw IllegalArgumentException
	 */
	public Room createRoom(String roomName, int capacity)
		throws IllegalArgumentException;
	
	
	/*
	 * Nutzer anlegen
	 * @param firstName Vorname des Nutzers
	 * @param lastName Nachname des Nutzers
	 * @param email Emailadresse des Nutzers
	 * @param  accessToken
	 * @param  accessTokenSecret
	 * @return User ein fertiges Userobjekt
	 * @throw IllegalArgumentException
	 */
	public User createUser(String firstName, String lastName, String email, String accessToken, String accessTokenSecret)
	throws IllegalArgumentException;
	
	/*
	 * Belegung anlegen
	 * @param topic Thema der Belegung
	 * @param startDate Anfangszeitpunkt der Raumbelegung
	 * @param endDate Endzeitpunkt der Raumbelegung
	 * @param organizer Nutzer der diese Raumbelegung angelegt hat
	 * @param participants Eine Liste der Nutzer, die zu dieser Raumbelegung eingeladen werden
	 */
	public Event createEvent(String topic, Date startDate, Date endDate, User organizer, Vector <User> participants)
	throws IllegalArgumentException;
	
	/*
	 * Speichern eines Roomobjektes in der Datenbank
	 * @param Room Raum der gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Room room) throws IllegalArgumentException;
	
	/*
	 * Speichern eines Userobjektes in der Datenbank
	 * @param user Nutzerobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(User user) throws IllegalArgumentException;
	
	/*
	 * Speichern eines Eventobjektes in der Datenbank
	 * @param Event Eventobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Event event) throws IllegalArgumentException;
	
	/*
	 * Speichern eines Invitationobjektes in der Datenbank
	 * @param Invitation Invitationobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Invitation invitation) throws IllegalArgumentException;
	
	/*
	 * Löschen des übergebenen Roomobjektes
	 * @param room zu löschendes Raumobjekt
	 * @throws IllegalArgumentException
	 */
	public void delete(Room room) throws IllegalArgumentException;
	
	
	
	

}
