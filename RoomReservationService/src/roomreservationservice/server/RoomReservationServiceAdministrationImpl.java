package roomreservationservice.server;

import java.sql.Timestamp;
import java.util.Vector;

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
	public void init() throws IllegalArgumentException{
		 /**
	     * Die RoomReservationServiceAdministration besitzt einen vollständigen Satz
	     * von Mappern, mit deren Hilfe sie mit der Datenbank
	     * kommunizieren kann.
	     */
		
		/**
		 * Mapper für den Raum.
		 */
		this.rMapper = RoomMapper.roomMapper();
<<<<<<< HEAD
		/**
		 * Mapper für den Nutzer.
		 */
		this.uMapper = UserMapper.userMapper();
		/**
		 * Mapper für die Reservierung.
		 */
		this.eMapper = EventMapper.eventMapper();
		/**
		 * Mapper für die Einladung.
		 */
=======
		this.uMapper = UserMapper.userMapper();
		this.eMapper = EventMapper.eventMapper();
>>>>>>> refs/heads/master
		this.iMapper = InvitationMapper.invitationMapper();
	}
	
	/**
	   * Übergeben eines neuen Raumes an den rMapper. Dies führt zu einem Speichern des
	   * neuen Raumes in der Datenbank.
	   * 
	   *HINWEIS: Änderungen an Room-Objekten müssen stets durch Aufruf
	   * von {@link #save(Room room)} in die Datenbank transferiert werden.
	   * 
	   * @see save(Room room)
	   */
	public Room createRoom(String roomName, int roomCapacity)
			throws IllegalArgumentException {
		Room room = new Room("neuer Raum", 1);
		room.setRoomName(roomName);
		room.setRoomCapacity(roomCapacity);;
		/**
		 * Abspeichern des Raum-Objektes in der Datenbank mithilfe von 
		 * {@link #insert(Room room)}
		 */
		return this.rMapper.insert(room);
	}

	/**
	   * Übergabe eines neuen Nutzers an den uMapper. Dies führt zu einem Speichern des
	   * neuen Nutzers in der Datenbank.
	   * 
	   *HINWEIS: Änderungen an Nutzer-Objekten müssen stets durch Aufruf
	   * von {@link #save(User user)} in die Datenbank transferiert werden.
	   * 
	   * @see save(User user)
	   */
	public User createUser(String firstName, String lastName, String email,
			String accessToken, String accessTokenSecret)
			throws IllegalArgumentException {
		User user = new User("Vorname","Nachname", "Email", "accessToken", "accessTokenSecret");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAccessToken(accessToken);
		user.setAccessTokenSecret(accessTokenSecret);
		/**
		 * Abspeichern des Nutzer-Objektes in der Datenbank mithilfe von 
		 * {@link #insert(User user)}
		 */
		return this.uMapper.insert(user);
	}

	/**
	   * Übergabe einer neuen Reservierung an den eMapper. Dies führt zu einem Speichern der
	   * neuen Veranstaltung in der Datenbank.
	   * 
	   *HINWEIS: Änderungen an Veranstaltungs-Objekten müssen stets durch Aufruf
	   * von {@link #save(Event event)} in die Datenbank transferiert werden.
	   * 
	   * @see save(Event event)
	   */
	public Event createEvent(String topic, Timestamp startDate, Timestamp endDate,
			Room room, User organizer, Vector<User> invitees)
			throws IllegalArgumentException {
		//Probleme mit @Timestamp @author Anh Duc
		Event event = new Event("topic", Timestamp.valueOf("2014-01-01"), Timestamp.valueOf("2014-12-31"), room, organizer, invitees);
		event.setTopic(topic);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setOrganizer(organizer);
		event.setInvitees(invitees);
		/**
		 * Abspeichern des Veranstaltungs-Objektes in der Datenbank mithilfe von 
		 * {@link #insert(Event event)}
		 */
		return this.eMapper.insert(event);
	}

	/**
	 * speichern eines Raumes.
	 * @param room
	 * @throws IllegalArgumentException
	 */
	public void save(Room room) throws IllegalArgumentException {
		rMapper.update(room);	
	}

	/**
	 * speichern eines Nutzers.
	 * @param user
	 * @throws IllegalArgumentException
	 */
	public void save(User user) throws IllegalArgumentException {
		uMapper.update(user);	
	}

	/**
	 * speichern einer Reservierung/Veranstaltung.
	 * @param event
	 * @throws IllegalArgumentException
	 */
	public void save(Event event) throws IllegalArgumentException {
		eMapper.update(event);	
	}

	/**
	 * speichern einer Einladung.
	 * @param invitation
	 * @throws IllegalArgumentException
	 */
	public void save(Invitation invitation) throws IllegalArgumentException {
		iMapper.update(invitation);
	}
	/**
	 * Löschen eines Raumes aus der Datenbank
	 * Dazu müssen auch die Events in diesem Raum 
	 * gelöscht werden
	 */
	@Override
	public void delete(Room room) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Event event) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Invitation invitation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Room> getAllRooms() throws IllegalArgumentException {
		//return this.rMapper.findAll();
	}

	@Override
	public Vector<User> getAllUsers() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getAllEvents() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Invitation> getAllInvitations()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<User> getUsersByName(String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<User> getUsersByParticipationStatusForEvent(Event event,
			boolean participationStatus) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByRoom(Room room)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByUser(User user)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByPeriodOfTime(Timestamp startDate, Timestamp endDate)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByTopic(String topic)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByRoomForPeriodOfTime(Room room,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEventsByUserForPeriodOfTime(User user,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
