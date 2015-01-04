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
 * Implementierungsklasse des Interface
 * <code>RoomReservationServiceAdministration</code>. Diese Klasse ist
 * <em>die</em> Klasse, die neben {@link ReportGeneratorImpl} sämtliche
 * Applikationslogik (oder engl. Business Logic) aggregiert. Diese Klasse steht
 * mit einer Reihe weiterer Datentypen in Verbindung. Dies sind:
 * <ol>
 * <li>{@link RoomReservationServiceAdministration}: Dies ist das
 * <em>lokale</em> - also Server-seitige - Interface, das die im System zur
 * Verfügung gestellten Funktionen deklariert.</li>
 * <li>{@link RoomReservationServiceAdministrationAsync}:
 * <code>RoomReservationServiceAdministrationImpl</code> und
 * <code>RoomReservationServiceAdministration</code> bilden nur die
 * Server-seitige Sicht der Applikationslogik ab. Diese basiert vollständig auf
 * synchronen Funktionsaufrufen. Wir müssen jedoch in der Lage sein,
 * Client-seitige asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres
 * Interface, das in der Regel genauso benannt wird, wie das synchrone
 * Interface, jedoch mit dem zusätzlichen Suffix "Async". Es steht nur mittelbar
 * mit dieser Klasse in Verbindung. Die Erstellung und Pflege der Async
 * Interfaces wird durch das Google Plugin semiautomatisch unterstützt. Weitere
 * Informationen unter {@link RoomReservationServiceAdministrationAsync}.</li>
 * <li> {@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis für die Anbindung von <code>BankVerwaltungImpl</code> an die Runtime
 * des GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * Diese Klasse bedient sich sogenannter Mapper-Klassen. Sie gehören der
 * Datenbank-Schicht an und bilden die objektorientierte Sicht der
 * Applikationslogik auf die relationale organisierte Datenbank ab.
 * 
 * @see RoomReservationServiceAdministration
 * @see RoomReservationServiceAdministrationAsync
 * @see RemoteServiceServlet
 * @author Vanessa
 */

public class RoomReservationServiceAdministrationImpl extends
		RemoteServiceServlet implements RoomReservationServiceAdministration {

	// Attribute

	/**
	 * Eine eindeutige ID die sich ändert, wenn an dem serialisierten Objekt
	 * Änderungen durchgeführt wurden. Somit kann überprüft werden, ob ein
	 * Objekt eine Zustandsänderung erfahren hat. Auto-Generated UID
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
	private UserMapper uMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Event-Objekte mit der Datenbank
	 * abgleicht.
	 */
	private EventMapper eMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Invitation-Objekte mit der
	 * Datenbank abgleicht.
	 */
	private InvitationMapper iMapper = null;

	// Methoden

	/**
	 * NO-Argument-Constructor *
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines
	 * anderen Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */
	public RoomReservationServiceAdministrationImpl()
			throws IllegalArgumentException {
		// Dieser Konstruktor muss einfach vorhanden sein.
	}

	/**
	 * Diese Methode muss für jede Instanz von
	 * RoomReservationServiceAdministrationImpl aufgerufen werden.
	 */
	public void init() throws IllegalArgumentException {
		/**
		 * Die RoomReservationServiceAdministration besitzt einen vollständigen
		 * Satz von Mappern, mit deren Hilfe sie mit der Datenbank kommunizieren
		 * kann.
		 */

		/**
		 * Mapper für den Raum.
		 */
		this.rMapper = RoomMapper.roomMapper();
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
		this.iMapper = InvitationMapper.invitationMapper();

		// Logger initialisieren
		try {
			this.logger = ServersideSettings.getLogger();
			logger.info("Logger erfolgreich instanziiert");
		} catch (Exception e) {
			logger.warning("Fehler bei der Instanziierung des Loggers"
					+ e.getMessage());

		}
	}

	/**
	 * Übergeben eines neuen Raumes an den rMapper. Dies führt zu einem
	 * Speichern des neuen Raumes in der Datenbank.
	 * 
	 * HINWEIS: Änderungen an Room-Objekten müssen stets durch Aufruf von
	 * {@link #save(Room room)} in die Datenbank transferiert werden.
	 * 
	 * @see save(Room room)
	 */
	@Override
	public Room createRoom(String roomName, int capacity)
			throws IllegalArgumentException {
		try {
			Room newRoom = new Room(roomName, capacity);
			/*
			 * Abspeichern des Raum-Objektes in der Datenbank mithilfe von
			 * {@link #insert(Room room)}
			 */
			logger.info("Raum wird in die Datenbank eingetragen");
			return this.rMapper.insert(newRoom);
		} catch (Exception e) {
			logger.warning("Fehler bei der Raumerstellung" + e.getMessage());
			return null;
		}
	}

	/**
	 * Übergabe eines neuen Nutzers an den uMapper. Dies führt zu einem
	 * Speichern des neuen Nutzers in der Datenbank.
	 * 
	 * Existiert bereits ein User mit derselben Emailadresse, kann der neue User
	 * nicht hinzugefügt werden HINWEIS: Änderungen an Nutzer-Objekten müssen
	 * stets durch Aufruf von {@link #save(User user)} in die Datenbank
	 * transferiert werden.
	 * 
	 * @see save(User user)
	 */
	@Override
	public User createUser(String firstName, String lastName, String email,
			String accessToken, String accessTokenSecret)
			throws IllegalArgumentException {
		try {
			User user = new User(firstName, lastName, email, accessToken,
					accessTokenSecret);

			// Überprüfen ob es bereits einen User mit der gleichen Emailadresse
			// gibt
			Vector<User> existingUsers = this.getAllUsers();

			/*
			 * lokale Variable der den Wert speichert, ob es bereits einen User
			 * mit derselben EmailAdresse gibt oder nicht Dieser Wert ist
			 * standardmäßig auf false gesetzt. True bedeutet, diese
			 * Emailadresse existiert bereits in der DB und der User kann dann
			 * nicht gespeichert werden
			 */
			boolean ifExist = false;
			// Überprüfung für jeden User in der Datenbank nur wenn es überhaupt
			// User gibt
			if (existingUsers != null) {
				for (User u : existingUsers) {
					// die beiden Strings der Emailadresse werden verglichen
					if (u.getEmail().equals(email)) {
						/*
						 * stimmen diese überein, wird ifExist auf true gesetzt
						 * und die for-Schleife wird beendet
						 */
						ifExist = true;
						logger.info("EmailAdresse ist bereits vorhanden");
						break;
					}
				}
				if (ifExist == true) {
					logger.info("User wird nicht hinzugefügt");
					return null;
				} else {
					/**
					 * Abspeichern des Nutzer-Objektes in der Datenbank mithilfe
					 * von {@link #insert(User user)}
					 */
					logger.info("User wird in Datenbank eingetragen");
					return this.uMapper.insert(user);
				}
			}
			return this.uMapper.insert(user);

		} catch (Exception e) {
			logger.warning("User konnte nicht hinzugefügt werden"
					+ e.getMessage());
			return null;
		}

	}

	/**
	 * Übergabe einer neuen Reservierung an den eMapper. Dies führt zu einem
	 * Speichern der neuen Veranstaltung in der Datenbank.
	 * 
	 * HINWEIS: Änderungen an Veranstaltungs-Objekten müssen stets durch Aufruf
	 * von {@link #save(Event event)} in die Datenbank transferiert werden.
	 * 
	 * @see save(Event event)
	 */
	@Override
	public Event createEvent(String topic, Timestamp startDate,
			Timestamp endDate, Room room, User organizer, Vector<User> invitees)
			throws IllegalArgumentException {

		try {
			/*
			 * nur wenn Raum zum Zeitraum startDate bis endDate nicht bereits
			 * belegt ist dh. wenn der Vector der durch die folgende Methode
			 * erzeugt wird nicht leer ist
			 */
			if (this.getEventsByRoomForPeriodOfTime(room, startDate, endDate) != null) {

				// nur wenn die Teilnehmerzahl kleiner oder gleich der Kapazität
				// ist,
				// kann das Event erstellt werden
				if (invitees.size() <= room.getRoomCapacity()) {

					// ID des Organisators erhalten.
					int organizerId = organizer.getId();
					// ID des Raumes erhalten.
					int roomId = room.getId();
					Event event = new Event(topic, startDate, endDate, roomId,
							organizerId);
					// Übergabe der Belegung an die DB ohne eingeladene Nutzer.
					eMapper.insert(event);
					// zugehörige Einladung mit zugehörigen Belegung und
					// eingeladenen Nutzer
					// an den iMapper weitergeben und in DB abspeichern mithilfe
					// von
					// {@link #insert(Invitation invitation)}
					for (User i : invitees) {
						this.createInvitation(event.getId(), i.getId());
					}
					// Methode beenden.
					return event;
				} else {
					logger.info("Teilnehmerzahl übersteigt Kapazität des Raumes, Event wird nicht hinzugefügt");
					return null;
				}
			} else {
				logger.info("Raum bereits belegt. Event wird nicht hinzugefügt");
				return null;
			}

		} catch (Exception e) {
			logger.warning("Fehler bei Erstellung von Event" + e.getMessage());
			return null;
		}

	}

	@Override
	public Invitation createInvitation(int eventId, int userId)
			throws IllegalArgumentException {
		try {
			Invitation invitation = new Invitation(eventId, userId);

			return this.iMapper.insert(invitation);
		} catch (Exception e) {
			logger.warning("Fehler bei der Erstellung von Einladung"
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * speichern eines Raumes.
	 * 
	 * @param room
	 * @throws IllegalArgumentException
	 */
	@Override
	public void save(Room room) throws IllegalArgumentException {
		try {
			logger.info("Änderung wird gespeichert");
			rMapper.update(room);
		} catch (Exception e) {
			logger.warning("Änderung konnte nicht durchgeführt werden"
					+ e.getMessage());
		}
	}

	/**
	 * speichern eines Nutzers.
	 * 
	 * @param user
	 * @throws IllegalArgumentException
	 */
	@Override
	public void save(User user) throws IllegalArgumentException {
		try {
			logger.info("Änderung wird gespeichert");
			uMapper.update(user);
		} catch (Exception e) {
			logger.warning("Änderung konnte nicht durchgeführt werden"
					+ e.getMessage());
		}

	}

	/**
	 * speichern einer Reservierung/Veranstaltung.
	 * 
	 * @param event
	 * @throws IllegalArgumentException
	 */
	@Override
	public void save(Event event) throws IllegalArgumentException {
		try {
			logger.info("Änderung wird gespeichert");
			eMapper.update(event);
		} catch (Exception e) {
			logger.warning("Änderung konnte nicht durchgeführt werden"
					+ e.getMessage());
		}
	}

	/**
	 * speichern einer Einladung.
	 * 
	 * @param invitation
	 * @throws IllegalArgumentException
	 */
	@Override
	public void save(Invitation invitation) throws IllegalArgumentException {

		// Variable in der gespeichert wird, ob der User bereits an einer
		// anderen Veranstaltung teilnimmt zur gleichen Zeit
		try {
			boolean ifBusy = false;

			// Wenn er sowieso absagt, ist es egal
			if (invitation.getParticipationStatus() == true) {

				Vector<Invitation> invitations = this.getInvitationsByUser(this
						.getUserById(invitation.getInviteeId()));
				for (Invitation i : invitations) {
					if (getEventById(i.getEventId()).getStartDate() == getEventById(
							invitation.getEventId()).getStartDate()
							&& i.getParticipationStatus() == true) {
						ifBusy = true;
						break;
					}

				}

				if (ifBusy == false) {
					logger.info("Zusage wird gespeichert");
					iMapper.update(invitation);
				} else {
					logger.info("Zusage nicht möglich, User nimmt an anderer Belegung teil");
					return;

				}

			} else {
				logger.info("Zusage wird gespeichert");
				iMapper.update(invitation);
			}
		} catch (Exception e) {
			logger.warning("Änderung konnte nicht durchgeführt werden"
					+ e.getMessage());
		}
	}

	/**
	 * Löschen eines Raumes aus der Datenbank Dazu müssen auch die Events in
	 * diesem Raum gelöscht werden
	 */
	@Override
	public void delete(Room room) throws IllegalArgumentException {

		try {
			// Zuerst werden die Events gesucht, die in diesem Raum sind
			Vector<Event> eventsOfRoom = this.getEventsByRoom(room);

			// prüfen ob die Liste leer ist
			if (eventsOfRoom != null) {
				// einzelne Events in diesem Raum werden gelöscht
				for (Event e : eventsOfRoom) {
					logger.info("Event mit ID " + e.getId() + " wird gelöscht");
					this.delete(e);
				}
			}
			logger.info("Raum wird gelöscht");
			this.rMapper.delete(room);
		} catch (Exception e) {
			logger.warning("Raum konnte nicht gelöscht werden" + e.getMessage());
		}
	}

	/**
	 * Löschen eines Users wenn der User gelöscht wird, dann werden auch die
	 * Events gelöscht bei denen dieser User als Organizer auftritt
	 * 
	 * @param User
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delete(User user) throws IllegalArgumentException {

		try {// Zunächst müssen die Events gelöscht werden in denen er Organizer
				// ist
			Vector<Event> organizedEvents = this.getEventsByOrganizer(user);

			// prüfen ob die Liste leer ist
			if (organizedEvents != null) {
				for (Event e : organizedEvents) {
					logger.info("Event mit der ID " + e.getId()
							+ " wird gelöscht");
					this.delete(e);
				}
			}

			// dann müssen noch die Invitations von diesem User zu Events
			// gelöscht
			// werden
			// Ergebnisvektor hierfür vorbereiten
			Vector<Invitation> invitations = this.getInvitationsByUser(user);

			// prüfen ob die Liste leer ist
			if (invitations != null) {
				for (Invitation i : invitations) {
					logger.info("Invitation von User wird gelöscht");
					this.delete(i);
				}
			}
		} catch (Exception e) {
			logger.warning("User konnte nicht gelöscht werden" + e.getMessage());

		}
	}

	/**
	 * Löschen des übergegebenen Event-Objekts dazu müssen auch alle
	 * dazugehörigen Invitation-Objekte gelöscht werden
	 * 
	 * @param event
	 *            zu löschendes Event
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delete(Event event) throws IllegalArgumentException {
		try {

			// alle Invitations dieses Events in einer Variablen festhalten
			Vector<Invitation> invitationsOfEvent = this
					.getInvitationsByEvent(event);

			// wenn diese Liste nicht leer ist
			if (invitationsOfEvent != null) {
				// dann, für jede invitation in dieser Liste
				for (Invitation i : invitationsOfEvent) {
					// invitation löschen
					logger.info("Invitation wird gelöscht");
					this.delete(i);

				}
			}
			// löschen des Events
			logger.info("Event wird gelöscht");
			this.eMapper.delete(event);

		} catch (Exception e) {
			logger.warning("Event konnte nicht gelöscht werden"
					+ e.getMessage());
		}
	}

	@Override
	public void delete(Invitation invitation) throws IllegalArgumentException {
		try {
			logger.info("Invitation wird gelöscht");
			this.iMapper.delete(invitation);
		} catch (Exception e) {
			logger.warning("Invitation konnte nicht gelöscht werden"
					+ e.getMessage());
		}

	}

	/**
	 * Gibt alle Room-Objekte in einem Vector aus Greift auf die findAll()
	 * Methode in der Mapperklasse
	 * 
	 * @return Vector
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<Room> getAllRooms() throws IllegalArgumentException {

		try {
			return this.rMapper.findAll();
		} catch (Exception e) {
			logger.warning("Räume konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * Gibt alle User-Objekte in einem Vector aus Greift auf die findAll()
	 * Methode in der Mapperklasse zu
	 * 
	 * @return Vector
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<User> getAllUsers() throws IllegalArgumentException {
		try {
			return this.uMapper.findAll();
		} catch (Exception e) {
			logger.warning("User konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	/**
	 * Gibt alle Event-Objekte in einem Vector aus Greift auf die findAll()
	 * Methode in der Mapperklasse zu
	 * 
	 * @return Vector
	 * @throws IllegalArgumentException
	 */
	@Override
	public Vector<Event> getAllEvents() throws IllegalArgumentException {
		try {
			return this.eMapper.findAll();
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<Invitation> getAllInvitations()
			throws IllegalArgumentException {
		try {
			return this.iMapper.findAll();
		} catch (Exception e) {
			logger.warning("Invitations konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Room getRoomById(int id) throws IllegalArgumentException {
		try {
			return this.rMapper.findByKey(id);
		} catch (Exception e) {
			logger.warning("Raum konnte nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public User getUserById(int id) throws IllegalArgumentException {
		try {
			return this.uMapper.findByKey(id);
		} catch (Exception e) {
			logger.warning("User konnte nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Event getEventById(int id) throws IllegalArgumentException {
		try {
			return this.eMapper.findByKey(id);
		} catch (Exception e) {
			logger.warning("Event konnte nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Invitation getInvitationById(int id) throws IllegalArgumentException {
		try {
			return this.iMapper.findByKey(id);
		} catch (Exception e) {
			logger.warning("Invitation konnte nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<User> getUsersByName(String name)
			throws IllegalArgumentException {
		try {
			return this.uMapper.findAllByName(name);
		} catch (Exception e) {
			logger.warning("User konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public User getOrganizerOfEvent(Event event)
			throws IllegalArgumentException {
		try {
			// zuerst die OrganizerId dieses events auslesen
			int id = event.getOrganizerId();
			// mit dieser kann man den entsprechenden User finden
			return this.uMapper.findByKey(id);
		} catch (Exception e) {
			logger.warning("Organizer konnte nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<User> getInviteesOfEvent(Event event)
			throws IllegalArgumentException {
		try {
			return this.uMapper.findAllInviteesOfEvent(event);
		} catch (Exception e) {
			logger.warning("User konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<User> getUsersByParticipationStatusForEvent(Event event,
			boolean participationStatus) throws IllegalArgumentException {
		try {
			return this.uMapper.findAllUserByParticipationStatusForEvent(event,
					participationStatus);
		} catch (Exception e) {
			logger.warning("User konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<Event> getEventsByRoom(Room room)
			throws IllegalArgumentException {
		try {
			return this.eMapper.findAllByRoom(room);
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<Event> getEventsByOrganizer(User user)
			throws IllegalArgumentException {
		try {
			return this.eMapper.findAllByOrganizer(user);
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public Vector<Event> getEventsByInvitees(User user)
			throws IllegalArgumentException {
		try {
			return this.eMapper.findAllByInvitee(user);
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Event> getEventsByPeriodOfTime(Timestamp startDate,
			Timestamp endDate) throws IllegalArgumentException {
		try {
			return this.eMapper.findAllForPeriodOfTime(startDate, endDate);
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Event> getEventsByTopic(String topic)
			throws IllegalArgumentException {
		try {
			return this.eMapper.findAllByTopic(topic);
		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Event> getEventsByRoomForPeriodOfTime(Room room,
			Timestamp startDate, Timestamp endDate)
			throws IllegalArgumentException {
		try {
			return this.eMapper.findAllByRoomForPeriodOfTime(room, startDate,
					endDate);

		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Event> getEventsByInviteeForPeriodOfTime(User user,
			Timestamp startDate, Timestamp endDate)
			throws IllegalArgumentException {

		try {
			return this.eMapper.findAllByInviteeForPeriodOfTime(user,
					startDate, endDate);

		} catch (Exception e) {
			logger.warning("Events konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Invitation> getInvitationsByEvent(Event event)
			throws IllegalArgumentException {
		try {
			return this.iMapper.findAllByEvent(event);
		} catch (Exception e) {
			logger.warning("Invitations konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Vector<Invitation> getInvitationsByUser(User user)
			throws IllegalArgumentException {
		try {
			return this.iMapper.findAllByUser(user);
		} catch (Exception e) {
			logger.warning("Invitations konnten nicht ausgegeben werden"
					+ e.getMessage());
			return null;
		}
	}

}