package roomreservationservice.shared;

import java.sql.Timestamp;

import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.User;
import roomreservationservice.shared.report.AllEventsForRoomInPeriodOfTimeReport;
import roomreservationservice.shared.report.AllEventsForUserInPeriodOfTimeReport;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Report Generator Async Schnittstelle, die RPCs ermöglicht. 
 * @author Julius Renner
 *
 */
public interface ReportGeneratorAsync {
	  void createAllEventsForRoomInPeriodOfTimeReport(Room room,Timestamp startDate, Timestamp endDate,
		      AsyncCallback<AllEventsForRoomInPeriodOfTimeReport> callback);

		  void createAllEventsForUserInPeriodOfTimeReport(User user,Timestamp startDate, Timestamp endDate,
		      AsyncCallback<AllEventsForUserInPeriodOfTimeReport> callback);

		  void init(AsyncCallback<Void> callback);


}
