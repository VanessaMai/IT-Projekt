package roomreservationservice.shared;

import java.sql.Timestamp;

import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;
import roomreservationservice.shared.report.AllEventsForRoomInPeriodOfTimeReport;
import roomreservationservice.shared.report.AllEventsForUserInPeriodOfTimeReport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
   *BankAdministrationImpltungImpl} notwendig. Bitte diese Methode direkt nach der
   * Instantiierung aufrufen.
   * 
   * @throws IllegalArgumentException
   */
  public void init() throws IllegalArgumentException;

  public abstract AllEventsForRoomInPeriodOfTimeReport createAllEventsForRoomInPeriodOfTimeReport(Room room,Timestamp startDate, Timestamp endDate) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>AllAccountsOfAllCustomersReport</code>-Reports.
   * Dieser Report-Typ stellt sämtliche Konten aller Kunden dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  public abstract AllEventsForUserInPeriodOfTimeReport createAllEventsForUserInPeriodOfTimeReport(User user,Timestamp startDate, Timestamp endDate)
      throws IllegalArgumentException;
}