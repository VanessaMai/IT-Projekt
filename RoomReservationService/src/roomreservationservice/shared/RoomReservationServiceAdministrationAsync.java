package roomreservationservice.shared;

import java.util.Date;
import java.util.Vector;

import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.Invitation;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * Das asynchrone Gegenstück zu dem Interface RoomReservationServiceAdministration
 * Dies wird semiautomatisch vom Google Plug In erstellt
 * Mehr Informationen: {@link RoomReservationServiceAdministration}
 * @author vanessa
 *
 */
public interface RoomReservationServiceAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createRoom(String roomName, int capacity, AsyncCallback<Room> callback);

	void createUser(String firstName, String lastName, String email,
			String accessToken, String accessTokenSecret,
			AsyncCallback<User> callback);

	void createEvent(String topic, Date startDate, Date endDate, Room room,
			User organizer, Vector<User> invitees, AsyncCallback<Event> callback);

	void save(Room room, AsyncCallback<Void> callback);

	void save(User user, AsyncCallback<Void> callback);

	void save(Event event, AsyncCallback<Void> callback);

	void save(Invitation invitation, AsyncCallback<Void> callback);

	void delete(Room room, AsyncCallback<Void> callback);

	void delete(User user, AsyncCallback<Void> callback);

	void delete(Event event, AsyncCallback<Void> callback);

	void delete(Invitation invitation, AsyncCallback<Void> callback);

	void getAllRooms(AsyncCallback<Vector<Room>> callback);

	void getAllUsers(AsyncCallback<Vector<User>> callback);

	void getAllEvents(AsyncCallback<Vector<Event>> callback);

	void getAllInvitations(AsyncCallback<Vector<Invitation>> callback);

	void getUsersByName(String name, AsyncCallback<Vector<User>> callback);

	void getUsersByParticipationStatusForEvent(Event event,
			boolean participationStatus, AsyncCallback<Vector<User>> callback);

	void getEventsByRoom(Room room, AsyncCallback<Vector<Event>> callback);

	void getEventsByUser(User user, AsyncCallback<Vector<Event>> callback);

	void getEventsByPeriodOfTime(Date startDate, Date endDate,
			AsyncCallback<Vector<Event>> callback);

	void getEventsByTopic(String topic, AsyncCallback<Vector<Event>> callback);

	void getEventsByRoomForPeriodOfTime(Room room, Date startDate,
			Date endDate, AsyncCallback<Vector<Event>> callback);

	void getEventsByUserForPeriodOfTime(User user, Date startDate,
			Date endDate, AsyncCallback<Vector<Event>> callback);

}
