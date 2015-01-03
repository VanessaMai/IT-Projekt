package roomreservationservice.server;

import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Logger;

import org.eclipse.jetty.util.log.Log;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import roomreservationservice.server.db.EventMapper;
import roomreservationservice.server.db.InvitationMapper;
import roomreservationservice.server.db.RoomMapper;
import roomreservationservice.server.db.UserMapper;
import roomreservationservice.server.report.ReportGeneratorImpl;
import roomreservationservice.shared.RoomReservationServiceAdministration;
import roomreservationservice.shared.RoomReservationServiceAdministrationAsync;
import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.Invitation;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;


/**
 * <p>
 * Implementierungsklasse des Interface <code>RoomReservationServiceAdministration</code>. Diese
 * Klasse ist <em>die</em> Klasse, die neben {@link ReportGeneratorImpl}
 * sämtliche Applikationslogik (oder engl. Business Logic) aggregiert. 
 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
 * sind:
 * <ol>
 * <li>{@link RoomReservationServiceAdministration}: Dies ist das <em>lokale</em> - also
 * Server-seitige - Interface, das die im System zur Verfügung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link RoomReservationServiceAdministrationAsync}: <code>RoomReservationServiceAdministrationImpl</code> und
 * <code>RoomReservationServiceAdministration</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollständig auf synchronen
 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusätzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
 * {@link RoomReservationServiceAdministrationAsync}.</li>
 * <li> {@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis für die Anbindung von <code>BankVerwaltungImpl</code> an die Runtime
 * des GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehören der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab.
 *  @see RoomReservationServiceAdministration
 * @see RoomReservationServiceAdministrationAsync
 * @see RemoteServiceServlet
 * @author Vanessa
 */


public class RoomReservationServiceAdministrationImpl extends RemoteServiceServlet
implements RoomReservationServiceAdministration{
	
	//Attribute
	
	/**
	 * Eine eindeutige ID die sich ändert, wenn an dem serialisierten Objekt 
	 * Änderungen durchgeführt wurden. Somit kann überprüft werden, 
	 * ob ein Objekt eine Zustandsänderung erfahren hat.
	 * Auto-Generated UID
	 */
	private static final long serialVersionUID = 7149271468218880267L;
	
	/**
	 * Variable für den Logger erstellen
	 */
	Logger logger;
	
	/**
	 * Referenz auf den DatenbankMapper, der Room-Objekte mit der Datenbank
	 * abgleicht.
	 */
	private RoomMapper rMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der User-Objekte mit der Datenbank
	 * abgleicht.
	 */
	private UserMapper uMapper=null;
	
	
	/**
	 * Referenz auf den DatenbankMapper, der Event-Objekte mit der Datenbank
	 * abgleicht.
	 */
	private EventMapper eMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der Invitation-Objekte mit der Datenbank
	 * abgleicht.
	 */
	private InvitationMapper iMapper = null;
	
	
	
	
	//Methoden

	
	/**
	 * NO-Argument-Constructor
	 *  * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	 * Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */
	public RoomReservationServiceAdministrationImpl() throws IllegalArgumentException{
		//Dieser Konstruktor muss einfach vorhanden sein. 
	}
	
	/**
	 * Diese Methode muss für jede Instanz von RoomReservationServiceAdministrationImpl 
	 * aufgerufen werden.
	 */
	@Override 
	public void init() throws IllegalArgumentException{
		this.rMapper = RoomMapper.roomMapper();
		this.uMapper = UserMapper.userMapper();
		this.eMapper = EventMapper.eventMapper();
		this.iMapper = InvitationMapper.invitationMapper();
		
		// Logger initialisieren
		try{
			this.logger = ServersideSettings.getLogger();
			logger.info("Logger erfolgreich instanziiert");
		} catch(Exception e) {
			logger.warning("Fehler bei der Instanziierung des Loggers" + e.getMessage());
			
		}
	}
	
	@Override
	public Room createRoom(String roomName, int capacity)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(String firstName, String lastName, String email,
			String accessToken, String accessTokenSecret)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event createEvent(String topic, Timestamp startDate, Timestamp endDate,
			Room room, User organizer, Vector<User> invitees)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Room room) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Event event) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Invitation invitation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Löschen eines Raumes aus der Datenbank
	 * Dazu müssen auch die Events in diesem Raum 
	 * gelöscht werden
	 */
	@Override
	public void delete(Room room) throws IllegalArgumentException {

		// Zuerst werden die Events gesucht, die in diesem Raum sind
		Vector<Event> eventsOfRoom = this.getEventsByRoom(room);
		
		//prüfen ob die Liste leer ist
		if(eventsOfRoom != null){
			//einzelne Events in diesem Raum werden gelöscht
			for(Event e : eventsOfRoom){
				this.delete(e);
			}
		}
		this.rMapper.delete(room);
		
	}

	/**
	 * Löschen eines Users 
	 * wenn der User gelöscht wird, dann werden auch die Events gelöscht
	 * bei denen dieser User als Organizer auftritt
	 * @param User
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delete(User user) throws IllegalArgumentException {
		// Zunächst müssen die Events gelöscht werden in denen er Organizer ist
		Vector<Event> organizedEvents = this.getEventsByOrganizer(user);
		
		//prüfen ob die Liste leer ist
		if (organizedEvents != null){
			for(Event e : organizedEvents){
				this.delete(e);
			}
		}
		
		// dann müssen noch die Invitations von diesem User zu Events gelöscht werden
		//Ergebnisvektor hierfür vorbereiten
		Vector<Invitation> invitations = this.getInvitationsByUser(user);
		
		//prüfen ob die Liste leer ist
		if (invitations != null){
			for(Invitation i : invitations){
				this.delete(i);
			}
		}
	
		
	}
	
	/**
	 * Löschen des übergegebenen Event-Objekts
	 * dazu müssen auch alle dazugehörigen Invitation-Objekte gelöscht werden
	 * @param event zu löschendes Event
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delete(Event event) throws IllegalArgumentException {
		// alle Invitations dieses Events in einer Variablen festhalten
		Vector<Invitation> invitationsOfEvent = this.getInvitationsByEvent(event);
		
		// wenn diese Liste nicht leer ist
		if (invitationsOfEvent != null){
			// dann, für jede invitation in dieser Liste
			for(Invitation i : invitationsOfEvent ){
				// invitation löschen
				this.delete(i);		
			}
		}
		// löschen des Events
		this.eMapper.delete(event);
	}
		
	

	@Override
	public void delete(Invitation invitation) throws IllegalArgumentException {
		this.iMapper.delete(invitation);
		
	}
	
	/**
	 * Gibt alle Room-Objekte in einem Vector aus
	 * Greift auf die findAll() Methode in der Mapperklasse
	 * @return Vector 
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<Room> getAllRooms() throws IllegalArgumentException {
		return this.rMapper.findAll();
	}
	
	/**
	 * Gibt alle User-Objekte in einem Vector aus
	 * Greift auf die findAll() Methode in der Mapperklasse zu
	 * @return Vector
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<User> getAllUsers() throws IllegalArgumentException {
		return this.uMapper.findAll();
	}

	/**
	 * Gibt alle Event-Objekte in einem Vector aus
	 * Greift auf die findAll() Methode in der Mapperklasse zu
	 * @return Vector
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<Event> getAllEvents() throws IllegalArgumentException {
		return this.eMapper.findAll();
	}

	@Override
	public Vector<Invitation> getAllInvitations()
			throws IllegalArgumentException {
		return this.iMapper.findAll();
	}

	@Override
	public Vector<User> getUsersByName(String name)
			throws IllegalArgumentException {

		return this.uMapper.findAllByName(name);
	}

	@Override
	public User getOrganizerOfEvent(Event event)
			throws IllegalArgumentException {
		// zuerst die OrganizerId dieses events auslesen
		int id = event.getOrganizerId();
		// mit dieser kann man den entsprechenden User finden
		return this.uMapper.findByKey(id);
		
	}

	@Override
	public Vector<User> getInviteesOfEvent(Event event)
			throws IllegalArgumentException {
		return this.uMapper.findAllInviteesOfEvent(event);
	}

	
	@Override
	public Vector<User> getUsersByParticipationStatusForEvent(Event event,
			boolean participationStatus) throws IllegalArgumentException {
		// Variable in der die Liste der User mit bestimmten Annahmestatus gespeichert werden
		Vector<User> inviteesStatus = new Vector<User>();
		// alle Einladungen dieses events
		Vector<Invitation> invitationsOfEvent = this.getInvitationsByEvent(event);
		
		// für jede einladung in dieser Liste wird der Annahmestatus ausgelesen und
		// mit dem übergebenen verglichen, falls diese übereinstimmen, wird der User 
		// dieser Einladung ausgelesen und InviteesStatus hinzugefügt
		for (Invitation i : invitationsOfEvent){
			if (i.getParticipationStatus() == participationStatus){
				inviteesStatus.add(this.uMapper.findByKey(i.getInviteeId()));
			}
		}
		return inviteesStatus;
	}

	@Override
	public Vector<Event> getEventsByRoom(Room room)
			throws IllegalArgumentException {
		return this.eMapper.findAllByRoom(room);
	}

	@Override
	public Vector<Event> getEventsByOrganizer(User user)
			throws IllegalArgumentException {
		return this.eMapper.findAllByOrganizer(user);
		
	}
	
	@Override
	public Vector<Event> getEventsByInvitees(User user)
			throws IllegalArgumentException {
		return this.eMapper.findAllByInvitee(user);
	}


	@Override
	public Vector<Event> getEventsByPeriodOfTime(Timestamp startDate, Timestamp endDate)
			throws IllegalArgumentException {
		return this.eMapper.findAllForPeriodOfTime(startDate, endDate);
	}

	@Override
	public Vector<Event> getEventsByTopic(String topic)
			throws IllegalArgumentException {	
		return this.eMapper.findAllByTopic(topic);
	}

	@Override
	public Vector<Event> getEventsByRoomForPeriodOfTime(Room room,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
			return this.eMapper.findAllByRoomForPeriodOfTime(room, startDate, endDate);
	}

	@Override
	public Vector<Event> getEventsByInviteeForPeriodOfTime(User user,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
		
		return this.eMapper.findAllByInviteeForPeriodOfTime(user, startDate, endDate);
	}
	

	@Override
	public Vector<Invitation> getInvitationsByEvent(Event event)
			throws IllegalArgumentException {
		return this.iMapper.findAllByEvent(event);
	}

	@Override
	public Vector<Invitation> getInvitationsByUser(User user)
			throws IllegalArgumentException {
		return this.iMapper.findAllByUser(user);
	}


	
	
}
