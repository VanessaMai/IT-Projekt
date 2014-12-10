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
	@Override 
	public void init() throws IllegalArgumentException{
		this.rMapper = RoomMapper.roomMapper();
		// TODO MEthode ergänzen wenn, UserMapper-Klasse fertig ist
		//this.uMapper = UserMapper. //
		//this.eMapper = EventMapper
		//this.iMapper = InvitationMapper.
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
		// TODO Auto-generated method stub
		return null;
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
