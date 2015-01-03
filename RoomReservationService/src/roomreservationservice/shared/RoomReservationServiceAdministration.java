package roomreservationservice.shared;

import java.sql.Timestamp;
import java.util.Vector;

import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.Invitation;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/** 
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Raumbelegungen.
 */
@RemoteServiceRelativePath("raumbelegungssystem")
public interface RoomReservationServiceAdministration extends RemoteService {
	
	/**
	 * Dient zur Initialisierung des Objekts. Diese Methode wird bei Aufruf des Systems automatisch ausgeführt.
	 */
	public void init() throws IllegalArgumentException;
	
	
	//Create Methoden für Room, User, Event
	
	/**
	 * Raum anlegen
	 * @param roomName Raumname
	 * @param capacity Fassungsvermögen  
	 * @return Room ein fertiges Raumobjekt
	 * @throw IllegalArgumentException
	 */
	public Room createRoom(String roomName, int capacity)
		throws IllegalArgumentException;
	
	
	/**
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
	
	/**
	 * Belegung anlegen
	 * @param topic Thema der Belegung
	 * @param startDate Anfangszeitpunkt der Raumbelegung
	 * @param endDate Endzeitpunkt der Raumbelegung
	 * @param room Raum in dem die Belegung stattfindet
	 * @param organizer Nutzer der diese Raumbelegung angelegt hat
	 * @param invitees Eine Liste der Nutzer, die zu dieser Raumbelegung eingeladen werden
	 */
	public Event createEvent(String topic, Timestamp startDate, Timestamp endDate, Room room, User organizer, Vector <User> invitees)
			throws IllegalArgumentException;
	
	
	//Save-Methoden für Änderungen der Room, User, Event, Invitation-Objekte
	
	/**
	 * Speichern eines Roomobjektes in der Datenbank
	 * @param Room Raum der gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Room room) throws IllegalArgumentException;
	
	/**
	 * Speichern eines Userobjektes in der Datenbank
	 * @param user Nutzerobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(User user) throws IllegalArgumentException;
	
	/**
	 * Speichern eines Eventobjektes in der Datenbank
	 * @param Event Eventobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Event event) throws IllegalArgumentException;
	
	/**
	 * Speichern eines Invitationobjektes in der Datenbank
	 * @param Invitation Invitationobjekt das gespeichert werden soll
	 * @throws IllegalArgumentException
	 */
	public void save(Invitation invitation) throws IllegalArgumentException;
	
	
	//Delete-Methoden für Room, User, Event, Invitation
	
	/**
	 * Löschen des übergebenen Roomobjektes
	 * @param room zu löschendes Raumobjekt
	 * @throws IllegalArgumentException
	 */
	public void delete(Room room) throws IllegalArgumentException;
	
	/**
	 * Löschen des übergebenen Userobjektes
	 * @param user zu löschendes Raumobjekt
	 * @throws IllegalArgumentException
	 */
	public void delete(User user) throws IllegalArgumentException;
	
	/**
	 * Löschen des übergebenen Eventobjektes
	 * @param event zu löschendes Raumobjekt
	 * @throws IllegalArgumentException
	 */
	public void delete(Event event) throws IllegalArgumentException;
	
	/**
	 * Löschen des übergebenen Invitationobjektes
	 * @param invitation zu löschendes Raumobjekt
	 * @throws IllegalArgumentException
	 */
	public void delete(Invitation invitation) throws IllegalArgumentException;
	
	
	//Getter mit verschiedenen Parametern
	
	/**
	 * Ausgeben aller Raum-Objekte in einer Liste
	 * @return Vector-Objekt mit allen vorhandenen Raum-Objekten
	 * @throws IllegalArgumentException
	 */
	public Vector<Room> getAllRooms() throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller User-Objekte in einer Liste
	 * @return Vector-Objekt mit allen vorhandenen User-Objekten
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getAllUsers() throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte in einer Liste
	 * @return Vector-Objekt mit allen vorhandenen Event-Objekten
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getAllEvents() throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Invitation-Objekte in einer Liste
	 * @return Vector-Objekt mit allen vorhanden Invitation-Objekten
	 * @throws IllegalArgumentException
	 */
	public Vector<Invitation> getAllInvitations() throws IllegalArgumentException;
	
	/**
	 * Suchen eines User-Objektes, dessen Name bekannt ist
	 * @param name Name eines User-Objektes (kann vor oder Nachname sein)
	 * @return Vector-Objekt mit allen Customer-Objekten, welche die Suchkriterien erfüllen
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getUsersByName(String name) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller User-Objekte, die zu einem Event-Objekt gehören und 
	 * einen bestimmten Annahmestatus in der Einladung haben
	 * @param event Raumbelegung zu der die User-Objekte gehören
	 * @param participationStatus der Status der Einladung zu einer bestimmten Belegung, true oder false
	 * @return Vector Objekten mit den User-Objekten, welche die Suchkriterien erfüllen
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getUsersByParticipationStatusForEvent(Event event, boolean participationStatus)
			throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben von UserObjekten die zu einem Event eingeladen sind
	 * 
	 * @param event
	 * @return Vector mit User Objekten
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getInviteesOfEvent(Event event)
			throws IllegalArgumentException;
	
	/**
	 * Ausgeben eines UserObjekts, der Veranstalter einer Belegung ist
	 * @param event
	 * @return User-Objekt
	 * @throws IllegalArgumentException
	 */
	public User getOrganizerOfEvent(Event event) throws IllegalArgumentException;
	
	
	/**
	 * Ausgeben aller Belegungen in einem Raum
	 * @param room Raum-Objekt in dem die Belegungen sind
	 * @return Vector-Objekt mit den Event-Objekten, welche die Suchkriterien erfüllen
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByRoom(Room room) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte von dem übergebenen User in einer Liste
	 * User können Teilnehmer, als auch Organisator sein. 
	 * @param user Teilnehmer oder Organisator eines Events
	 * @return Vector-Objekt mit den Event-Objekten, welche die User enthalten
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByInvitees(User user) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte von dem übergebenen User in einer Liste
	 * User können Teilnehmer, als auch Organisator sein. 
	 * @param user Teilnehmer oder Organisator eines Events
	 * @return Vector-Objekt mit den Event-Objekten, welche die User enthalten
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByOrganizer(User user) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte zum übergebenen Zeitraum in einer Liste
	 * @param startDate Startzeitpunkt des Zeitraums in der die Belegung stattfindet
	 * @param endDate Endzeitpunkt des Zeitraums
	 * @return Vector-Objekt mit Events, welche die Suchkriterien erfüllen
	 * throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByPeriodOfTime(Timestamp startDate, Timestamp endDate) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte mit übergebenem Thema 
	 * @param topic Thema einer Belegung
	 * @return Vector-Objekt mit Event-Objekten mit dem übergebenen Thema
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByTopic(String topic) throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte im übergebenen Raum zur übergebenen Zeit. 
	 * @param room Raum-Objekt in dem die Belegungen sind
	 * @param startDate Startzeitpunkt des Zeitraums in der die Belegung stattfindet
	 * @param endDate Endzeitpunkt des Zeitraums
	 * @return Vector-Objekt mit Events, welche die Suchkriterien erfüllen
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByRoomForPeriodOfTime(Room room, Timestamp startDate, Timestamp endDate) 
			throws IllegalArgumentException;
	
	/**
	 * Ausgeben aller Event-Objekte des übergebenen User zur übergebenen Zeit. 
	 * User können sowohl Teilnehmer als auch Organisator sein.
	 * @param user Teilnehmer oder Organisator eines Events
	 * @param startDate Startzeitpunkt des Zeitraums in der die Belegung stattfindet
	 * @param endDate Endzeitpunkt des Zeitraums
	 * @return Vector-Objekt mit Events, welche die Suchkriterien erfüllen
	 * @throws IllegalArgumentException
	 */
	public Vector<Event> getEventsByUserForPeriodOfTime(User user, Timestamp startDate, Timestamp endDate) 
			throws IllegalArgumentException;
	

	
	/** 
	 * Ausgeben aller Invitation-Objekte zu einem Event
	 * wird z.B. bei der Delete-Methode von Events benötigt
	 * @param event Event-Objekt
	 * @return Vector Objekt mit Invitations, welche die Suchkriterien erfüllen 
	 * @throws IllegalException
	 */
	public Vector<Invitation> getInvitationsByEvent(Event event)
			throws IllegalArgumentException;
	
	
}
	
	