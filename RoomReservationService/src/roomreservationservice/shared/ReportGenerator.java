package roomreservationservice.shared;

import java.sql.Timestamp;

import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;
import roomreservationservice.shared.report.AllEventsForRoomInPeriodOfTimeReport;
import roomreservationservice.shared.report.AllEventsForUserInPeriodOfTimeReport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Report Generator Schnittstelle. 
 * @author Julius Renner
 *
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
   * RoomReservationServiceAdministrationImpl notwendig. Bitte diese Methode direkt nach der
   * Instantiierung aufrufen.
   * 
   * @throws IllegalArgumentException
   */
  public void init() throws IllegalArgumentException;
  
  /**
   * Erstellen eines <code>AllEventsForRoomInPeriodOfTimeReport</code>-Reports.
   * In diesem Report werden alle Belegungen, die einem Raum zugeordnet sind und im angegebenen Zeitraum liegen, angezeigt.
   * 
   * @return Das Reportobjekt
   * @see AllEventsForRoomInPeriodOfTimeReport
   */
  public abstract AllEventsForRoomInPeriodOfTimeReport createAllEventsForRoomInPeriodOfTimeReport(Room room,Timestamp startDate, Timestamp endDate) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>AllEventsForUserInPeriodOfTimeReport</code>-Reports.
   * In diesem Report werden alle Belegungen, die einem User zugeordnet sind und im angegebenen Zeitraum liegen, angezeigt.
   * 
   * @return Das Reportobjekt
   * @see AllEventsForUserInPeriodOfTimeReport
   */
  public abstract AllEventsForUserInPeriodOfTimeReport createAllEventsForUserInPeriodOfTimeReport(User user,Timestamp startDate, Timestamp endDate)
      throws IllegalArgumentException;
  
}